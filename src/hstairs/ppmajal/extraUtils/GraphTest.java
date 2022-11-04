package hstairs.ppmajal.extraUtils;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit3TestClass.java to edit this template
 */

import com.hstairs.ppmajal.extraUtils.DAG;

public class GraphTest {



    public static void main(String[] args) throws Exception {
        DAG dag = new DAG();
        dag.addVertex("a");
        dag.addVertex("a");
        dag.addVertex("b");
        dag.addVertex("c");
        dag.addEdge("a", "b");
//        dag.addEdges("a", "c");
        dag.addEdge("b", "c");
//        dag.addEdges("c", "a");
        
        dag.print();
        System.out.println(dag.getAncestors("a"));
    }
}