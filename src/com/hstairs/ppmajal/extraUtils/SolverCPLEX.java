/*
 * Copyright (C) 2010-2017 Enrico Scala. Contact: enricos83@gmail.com.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package com.hstairs.ppmajal.extraUtils;

import ilog.concert.*;
import ilog.cplex.IloCplex;
import ilog.cplex.IloCplex.Status;
import org.ojalgo.access.IntIndex;
import org.ojalgo.access.IntRowColumn;
import org.ojalgo.array.PrimitiveArray;
import org.ojalgo.netio.CharacterRing;
import org.ojalgo.netio.CharacterRing.PrinterBuffer;
import org.ojalgo.optimisation.Expression;
import org.ojalgo.optimisation.ExpressionsBasedModel;
import org.ojalgo.optimisation.Optimisation;
import org.ojalgo.optimisation.Variable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class SolverCPLEX implements Optimisation.Solver {

    public static final ExpressionsBasedModel.Integration<SolverCPLEX> INTEGRATION = new ExpressionsBasedModel.Integration<SolverCPLEX>() {

        public SolverCPLEX build (final ExpressionsBasedModel model) {

            final SolverCPLEX retVal = new SolverCPLEX();
            final IloCplex tmpDelegateSolver = retVal.getIloCplex();

            try {

                final List<Variable> tmpFreeModVars = model.getVariables();
                for (final Variable tmpModVar : tmpFreeModVars) {

                    final String tmpName = tmpModVar.getName();

                    final double tmpLower = tmpModVar.getAdjustedLowerLimit();
                    final double tmpUpper = tmpModVar.getAdjustedUpperLimit();

                    IloNumVarType tmpType = IloNumVarType.Float;
                    if (tmpModVar.isBinary()) {
                        tmpType = IloNumVarType.Bool;
                    } else if (tmpModVar.isInteger()) {
                        tmpType = IloNumVarType.Int;
                    }

                    final IloNumVar tmpSolVar = tmpDelegateSolver.numVar(tmpLower, tmpUpper, tmpType, tmpName);
                    retVal.getVariables().add(tmpSolVar);
                }

                final Set<IntIndex> tmpFixedVariables = model.getFixedVariables();

                for (final Expression tmpModExpr : model.constraints().collect(Collectors.toList())) {
                    final Expression tmpCompensated = tmpModExpr.compensate(tmpFixedVariables);

                    final IloNumExpr tmpSolExpr = SolverCPLEX.buildExpression(model, tmpCompensated, tmpDelegateSolver, retVal.getVariables());

                    SolverCPLEX.setBounds(tmpSolExpr, tmpCompensated, tmpDelegateSolver);
                }

                final Expression tmpModObj = model.objective().compensate(tmpFixedVariables);

                final IloNumExpr tmpSolObj = SolverCPLEX.buildExpression(model, tmpModObj, tmpDelegateSolver, retVal.getVariables());

                if (model.isMaximisation()) {
                    tmpDelegateSolver.addMaximize(tmpSolObj);
                } else {
                    tmpDelegateSolver.addMinimize(tmpSolObj);
                }

            } catch (final IloException exception) {
                // TODO Auto-generated catch block
                exception.printStackTrace();
            }

            return retVal;
        }

        public boolean isCapable (final ExpressionsBasedModel model) {
            return true; // CPLEX can handle anything/everything ExpressionsBasedModel can model.
        }

    };
    static final Environment ENVIRONMENT = new Environment();
    private final IloCplex myIloCplex;
    private final List<IloNumVar> myVariables;

    SolverCPLEX ( ) {

        super();

        IloCplex tmpDelegate = null;

        try {
            tmpDelegate = new IloCplex();
        } catch (final IloException ex) {
            ex.printStackTrace();
            tmpDelegate = null;
        }

        myIloCplex = tmpDelegate;
        myVariables = new ArrayList<>();
    }

    static void addLinear (final Expression source, final IloLinearNumExpr destination, final ExpressionsBasedModel model, final List<IloNumVar> variables)
            throws IloException {

        for (final IntIndex tmpKey : source.getLinearKeySet()) {

            final int tmpFreeInd = tmpKey.index;

            if (tmpFreeInd >= 0) {
                destination.addTerm(source.getAdjustedLinearFactor(tmpKey), variables.get(tmpFreeInd));
            }

        }
    }

    static void addQuadratic (final Expression source, final IloQuadNumExpr destination, final ExpressionsBasedModel model, final List<IloNumVar> variables)
            throws IloException {

        for (final IntRowColumn tmpKey : source.getQuadraticKeySet()) {

            final int tmpFreeRow = tmpKey.row;
            final int tmpFreeCol = tmpKey.column;

            if ((tmpFreeRow >= 0) && (tmpFreeCol >= 0)) {
                destination.addTerm(source.getAdjustedQuadraticFactor(tmpKey), variables.get(tmpFreeRow), variables.get(tmpFreeCol));
            }

        }
    }

    static IloNumExpr buildExpression (final ExpressionsBasedModel model, final Expression expression, final IloCplex solver, final List<IloNumVar> variables)
            throws IloException {

        if (expression.isFunctionCompound()) {

            final IloLQNumExpr tmpSolExpr = solver.lqNumExpr();

            SolverCPLEX.addQuadratic(expression, tmpSolExpr, model, variables);
            SolverCPLEX.addLinear(expression, tmpSolExpr, model, variables);

            return tmpSolExpr;

        } else if (expression.isFunctionQuadratic()) {

            final IloQuadNumExpr tmpSolExpr = solver.quadNumExpr();

            SolverCPLEX.addQuadratic(expression, tmpSolExpr, model, variables);

            return tmpSolExpr;

        } else if (expression.isFunctionLinear()) {

            final IloLinearNumExpr tmpSolExpr = solver.linearNumExpr();

            SolverCPLEX.addLinear(expression, tmpSolExpr, model, variables);

            return tmpSolExpr;
        }

        return null;
    }

    static void setBounds (final IloNumExpr expression, final Expression model, final IloCplex solver) throws IloException {

        if (model.isEqualityConstraint()) {
            solver.addEq(model.getAdjustedLowerLimit(), expression);
        } else {
            if (model.isLowerConstraint()) {
                solver.addLe(model.getAdjustedLowerLimit(), expression);
            }
            if (model.isUpperConstraint()) {
                solver.addGe(model.getAdjustedUpperLimit(), expression);
            }
        }
    }

    public void dispose ( ) {

        Solver.super.dispose();

        if (myIloCplex != null) {
            myIloCplex.end();
        }
    }

    public Result solve (final Result kickStarter) {

        try {

            final List<IloNumVar> tmpVariables = this.getVariables();

            Optimisation.State retState = Optimisation.State.UNEXPLORED;
            double retValue = Double.NaN;
            final PrimitiveArray retSolution = PrimitiveArray.make(tmpVariables.size());

            if (myIloCplex.solve()) {
                // Feasible or Optimal

                for (int i = 0; i < tmpVariables.size(); i++) {
                    retSolution.set(i, myIloCplex.getValue(tmpVariables.get(i)));
                }

                retValue = myIloCplex.getObjValue();

                retState = this.translate(myIloCplex.getStatus());

            } else {
                // Not feasible

                retState = this.translate(myIloCplex.getStatus());

            }

            return new Result(retState, retValue, retSolution);

        } catch (final IloException exception) {

            exception.printStackTrace();
        }

        return new Result(Optimisation.State.FAILED, Double.NaN, kickStarter);
    }

    @Override
    protected void finalize ( ) throws Throwable {

        this.dispose();

        super.finalize();
    }

    IloCplex getIloCplex ( ) {
        return myIloCplex;
    }

    List<IloNumVar> getVariables ( ) {
        return myVariables;
    }

    State translate (final Status status) {
        if (status.equals(Status.Bounded)) {
            return State.VALID;
        } else if (status.equals(Status.Error)) {
            return State.FAILED;
        } else if (status.equals(Status.Feasible)) {
            return State.FEASIBLE;
        } else if (status.equals(Status.Infeasible)) {
            return State.INFEASIBLE;
        } else if (status.equals(Status.InfeasibleOrUnbounded)) {
            return State.INVALID;
        } else if (status.equals(Status.Optimal)) {
            return State.OPTIMAL;
        } else if (status.equals(Status.Unbounded)) {
            return State.UNBOUNDED;
        } else if (status.equals(Status.Unknown)) {
            return State.UNEXPLORED;
        } else {
            return State.FAILED;
        }
    }

    static final class Environment {

        private final PrinterBuffer myLog = new CharacterRing().asPrinter();

        Environment ( ) {

            super();

        }

        @Override
        protected final void finalize ( ) throws Throwable {

            super.finalize();
        }

    }

}
