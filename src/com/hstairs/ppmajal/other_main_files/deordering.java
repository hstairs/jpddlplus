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
package com.hstairs.ppmajal.other_main_files;

import com.hstairs.ppmajal.domain.PddlDomain;
import com.hstairs.ppmajal.plan.SimplePlan;
import com.hstairs.ppmajal.problem.GroundAction;
import com.hstairs.ppmajal.problem.PDDLState;
import com.hstairs.ppmajal.problem.PddlProblem;
import com.hstairs.ppmajal.some_computatitional_tool.DomainEnhancer;
import com.hstairs.ppmajal.wrapped_planners.metricFFWrapper;
import org.jgraph.JGraph;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;
import org.jgrapht.graph.DefaultEdge;

import javax.swing.*;
import java.awt.geom.Rectangle2D;
import java.util.*;

import static com.hstairs.ppmajal.extraUtils.Utils.searchParameter;
import static com.hstairs.ppmajal.extraUtils.Utils.searchParameterValue;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.DirectedAcyclicGraph;

/**
 * *******************************************************************
 * <p>
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 * <p>
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * <p>
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 59 Temple
 * Place - Suite 330, Boston, MA 02111-1307, USA.
 * <p>
 * *******************************************************************
 * <p>
 * *******************************************************************
 * Description: Part of the PPMaJaL library
 * <p>
 * Author: Enrico Scala 2013 Contact: enricos83@gmail.com
 * <p>
 * *******************************************************************
 * <p>
 * *******************************************************************
 * Description: Part of the PPMaJaL library
 * <p>
 * Author: Enrico Scala 2013 Contact: enricos83@gmail.com
 * <p>
 * *******************************************************************
 */
/**
 * *******************************************************************
 * Description: Part of the PPMaJaL library
 *
 * Author: Enrico Scala 2013 Contact: enricos83@gmail.com
 *
 ********************************************************************
 */

/**
 *
 * @author enrico
 */
public class deordering {

    private static String domainFile;
    private static String problemFile;
    private static String planFile;
    private static Boolean graphic = false;
    private static Boolean get_macro_and_save = false;

    public static void parseInput (String[] args) {
        //Eseguibile -o domain -f problem -s solution -r tipo-repair 
        String usage = "usage:\n executable-name(java -jar...) "
                + "\n-o domain -f problem "
                + "\n-p<optional> (in case the plan is not specified, metric-ff is used to compute it) "
                + "\n-v<optional,default=false> (if selected, the deorder plan is graphically represented using jgraph";
        if (args.length < 2) {
            System.err.println("Number of parameters is low (" + args.length + ")");
            System.err.println(usage);
            System.exit(-1);
        } else {
            domainFile = searchParameterValue(args, "-o");
            problemFile = searchParameterValue(args, "-f");
            planFile = searchParameterValue(args, "-p");
            graphic = searchParameter(args, "-v");
            get_macro_and_save = searchParameter(args, "-macro");
            if (domainFile == null || problemFile == null) {
                System.err.println(usage);
                System.exit(-1);
            }

        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main (String[] args) throws Exception {
        // TODO code application logic here

        parseInput(args);

        PddlDomain dom = new PddlDomain(domainFile);

        PddlProblem prob = new PddlProblem();
        prob.parseProblem(problemFile);

        SimplePlan plan = new SimplePlan(dom, prob, true);

        metricFFWrapper p = new metricFFWrapper();

        if (planFile == null) {
            p.setTimeout(50000);
            plan.parseSolution(p.plan(domainFile, problemFile));
        } else {
            plan.parseSolution(planFile);
        }

        System.out.println(plan.toStringWithIndex());

        //HashSet a = new HashSet();
//        if (!plan.execute(prob.getInit()).satisfy(prob.getGoals())) {
//            System.out.println("Piano non valido!!");
//            System.exit(-1);
//        }
        DirectedAcyclicGraph po = plan.deorder((PDDLState) prob.getInit(), prob.getGoals(), false);
        JGraphModelAdapter m_jgAdapter = new JGraphModelAdapter(po);

        //po = this.removeUselessActions(po,plan.size()-1);
        printOrdering(po);

        //System.out.println(plan.generateMacrosFromPop(po));
        if (get_macro_and_save) {
            System.out.println("Saving Macros from Deordering");
            DomainEnhancer dEnh = new DomainEnhancer();
            //DomainEnhancer dEnh = new DomainEnhancer();
            po = plan.removeInitGoal(po);
            List c;
            c = plan.generateMacrosFromPop(po, plan.getGoalAchiever(), true, false, false);
            //System.out.println(c);
            Map m = dEnh.addMacroActions(dom, c, plan);
        }

        if (!graphic) {
            return;
        }
        //po = plan.removeInitGoal(po);        
        po.removeVertex(-1);
        DirectedAcyclicGraph totalOrder = new DirectedAcyclicGraph(DefaultEdge.class);

        for (int i = 0; i < plan.size(); i++) {
            totalOrder.addVertex(i);
        }
        for (int i = 0; i < plan.size(); i++) {
            for (int j = i + 1; j < plan.size(); j++) {
                totalOrder.addEdge(i, j);
            }
        }
        m_jgAdapter = new JGraphModelAdapter(po);

        //po = totalOrder;
        JGraph jgraph = new JGraph(m_jgAdapter);

        int x = 0;
        int y = 0;
        int counter = 0;
        for (Object o : po.vertexSet()) {
            Integer v = (Integer) o;

            GroundAction gr = plan.get(v);
            System.out.println(gr.toEcoString());
            if (counter % 2 == 0) {
                positionVertexAt(m_jgAdapter, o, 380 + y, 90 + x, plan);
                x = x + 100;
            } else {
                positionVertexAt(m_jgAdapter, o, 380 + y, 90, plan);
            }

            y = 100;
        }

// Show in Frame
        JFrame frame = new JFrame();
        frame.getContentPane().add(new JScrollPane(jgraph));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    private static void positionVertexAt (JGraphModelAdapter m_jgAdapter, Object vertex, int x, int y, SimplePlan p) {
        DefaultGraphCell cell = m_jgAdapter.getVertexCell(vertex);
        GroundAction gr = p.get((Integer) vertex);
        cell.setUserObject(gr.getName());
        Map attr = cell.getAttributes();
        Rectangle2D b = GraphConstants.getBounds(attr);

        b.setRect(x, y, 50, 50);
        GraphConstants.setBounds(attr, b);

        Map cellAttr = new HashMap();
        cellAttr.put(cell, attr);
        //m_jgAdapter.edit( cell,null, null, null, null, null );
        //m_jgAdapter.edit( cellAttr, null, null, null, null );
    }

    private static ArrayList decompose (DirectedAcyclicGraph po, int planSize) {

        ArrayList component = new ArrayList();
        HashMap<Object, Boolean> visited = new HashMap();

        for (Object v : po.vertexSet()) {
            visited.put(v, false);
        }
        //removeInitGoal(po);
        int maxSize = (int) ((float) planSize * (2 / 3.0));
        //maxSize = 1000000000;
        while (true) {
            Object endPoint = findVertexWithMinimumOutGoingArcsFromNotVisitedVertex(po, visited);
            if (endPoint == null) {
                System.out.println("Search terminated...");
                System.out.println(visited);
                break;
            }
            visited.put(endPoint, Boolean.TRUE);
            System.out.println("Generator:" + endPoint);
            while (true) {
                HashSet currentPo = new HashSet();
                currentPo.add(endPoint);

                Object vertex = findVertexWithMinimumOutGoingArcsFromEdge(po, po.incomingEdgesOf(endPoint), visited);
                if (vertex != null) {
                    Collection c = findConnectedComponent(po, null, vertex, maxSize, visited);
                    if (c.isEmpty()) {
                        break;
                    }
                    currentPo.addAll(c);
                    component.add(currentPo);
                    //System.out.println("generator:"+endPoint);

                } else {
                    System.out.println("Already finished??");
                    break;
                }
                System.out.println(currentPo);
            }

        }
        //System.out.println(component);
        return component;
    }

    private static HashSet findConnectedComponent (DirectedAcyclicGraph po, HashSet currentPo, Object o, int k, HashMap<Object, Boolean> visited) {

        if (currentPo == null) {
            currentPo = new HashSet();
        }

        if ((currentPo.size() >= k) || (o == null)) {
            System.out.println(currentPo.size());
            System.out.println("finished");
            return currentPo;
        } else {
            //currentPo.add(o);
            //i nodi che dipendono da o
            Collection c = collectSuccessors(po, o, visited);
            if (c.size() + currentPo.size() >= k) {
                //System.out.println("Source:"+o);
                //System.out.println("Too big the resulting collections...???...:"+c.size());
                for (Object v1 : c) {
                    visited.put(v1, false);
                }
                return currentPo;
            }
            currentPo.add(o);
            currentPo.addAll(c);

            Object vertex = findVertexWithMinimumOutGoingArcsFromEdge(po, po.incomingEdgesOf(o), visited);
            if (vertex != null) {
                currentPo.addAll(findConnectedComponent(po, currentPo, vertex, k, visited));
            }
        }
        return currentPo;
    }

    private static Collection collectSuccessors (DirectedAcyclicGraph po, Object o, HashMap<Object, Boolean> visited) {
        HashSet ret = new HashSet();
        if (o == null) {
            return ret;
        } else if (!visited.get(o)) {
            ret.add(o);
            visited.put(o, true);
            if (!po.outgoingEdgesOf(o).isEmpty()) {
                for (Object edge : po.outgoingEdgesOf(o)) {
                    ret.addAll(collectSuccessors(po, po.getEdgeTarget(edge), visited));
                }
            }
        }

        return ret;
    }

    private static Object findVertexWithMinimumOutGoingArcsFromEdge (DirectedAcyclicGraph po, Set incomingEdgesOf, HashMap<Object, Boolean> visited) {
        Object best = null;
        int minimum = Integer.MAX_VALUE;
        for (Object edge : incomingEdgesOf) {
            Object v1 = po.getEdgeSource(edge);
            if (!visited.get(v1) && (best == null || po.outDegreeOf(v1) < minimum)) {
                best = v1;
                minimum = po.outDegreeOf(v1);
            }
        }
        return best;
    }

    private static Object findVertexWithMinimumOutGoingArcsFromNotVisitedVertex (DirectedAcyclicGraph po, HashMap<Object, Boolean> visited) {
        Object best = null;
        int minimum = Integer.MAX_VALUE;
        for (Object v1 : po.vertexSet()) {
            //Object v1 = po.getEdgeSource(edge);
            if (!visited.get(v1) && (best == null || po.outDegreeOf(v1) < minimum)) {
                best = v1;
                minimum = po.outDegreeOf(v1);
            }
        }
        return best;
    }

    private static void removeInitGoal (DirectedAcyclicGraph po) {

        po.removeEdge(po.edgesOf(0));
        po.removeEdge(po.edgesOf(po.vertexSet().size() - 1));
        po.removeVertex(po.vertexSet().size() - 1);
        po.removeVertex(0);
    }

    private static void decompose2 (DirectedAcyclicGraph po, int planSize) {

        removeInitGoal(po);

        int components = 1;
        do {
            ConnectivityInspector<Object, Object> a = new ConnectivityInspector<Object, Object>(po);
            System.out.println(a.connectedSets().size());
            components = a.connectedSets().size();
            System.out.println(a.connectedSets());
            if (components < planSize) {
                removeLargerAbsorberLargerProducer(po);
            }

        } while (components < planSize);
    }

    private static void removeLargerAbsorberLargerProducer (DirectedAcyclicGraph po) {
        Object absorber = null;
        Object producer = null;
        int maxAbsorber = 0;
        int maxProducer = 0;

        for (Object o : po.vertexSet()) {
            if (po.inDegreeOf(o) > maxAbsorber) {
                absorber = o;
                maxAbsorber = po.inDegreeOf(o);
            }
            if (po.outDegreeOf(o) > maxProducer) {
                producer = o;
                maxProducer = po.outDegreeOf(o);
            }

        }
        if (absorber != null) {
            Set s = new HashSet();
            for (Object edge : po.incomingEdgesOf(absorber)) {
                s.add(edge);
            }
            po.removeAllEdges(s);
        }
        if (producer != null) {
            Set s = new HashSet();
            for (Object edge : po.outgoingEdgesOf(producer)) {
                s.add(edge);
            }
            po.removeAllEdges(s);
        }

    }

    private static void printOrdering (DirectedAcyclicGraph po) {
        for (Object v : po.vertexSet()) {
            Integer action = (Integer) v;
            System.out.print(action + " ->");
            for (Object v1 : po.vertexSet()) {
                if (po.getEdge(v, v1) != null) {
                    Integer action1 = (Integer) v1;
                    System.out.print(action1 + ", ");
                }

            }
            System.out.println();
        }
    }

}
