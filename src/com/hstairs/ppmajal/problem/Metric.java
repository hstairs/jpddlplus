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
package com.hstairs.ppmajal.problem;

import com.hstairs.ppmajal.expressions.Expression;

/**
 * @author enrico
 */
public class Metric {

    private Expression metExpr;
    private String optimization;

    public Metric ( ) {
        super();
    }

    public Metric (String optimization) {
        this.optimization = optimization;
    }

    /**
     * @return the metExpr
     */
    public Expression getMetExpr ( ) {
        return metExpr;
    }

    /**
     * @param metExpr the metExpr to set
     */
    public void setMetExpr (Expression metExpr) {
        this.metExpr = metExpr;
    }

    /**
     * @return the optimization
     */
    public String getOptimization ( ) {
        return optimization;
    }

    /**
     * @param optimization the optimization to set
     */
    public void setOptimization (String optimization) {
        this.optimization = optimization;
    }

    @Override
    public String toString ( ) {

        return "Metric: " + this.optimization + " " + this.metExpr + " ";

    }

    String pddlPrint ( ) {
        if (!this.optimization.equals("NO")) {
            return "(:metric " + this.optimization + " " + this.metExpr.pddlPrint(false) + ")";
        } else {
            return "";
        }
    }

    Metric unifyVariablesReferences (EPddlProblem aThis) {
        if (this.metExpr != null)
            this.metExpr = this.metExpr.unifyVariablesReferences(aThis);
        return this;
    }
}
