/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hstairs.ppmajal.extraUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 *
 * @author enrico
 */
public class DAG {
    Set<String> vertexes;
    HashMap<String,Set<String>> edges;
    HashMap<String,Set<String>> edgesReversed;
    String root;
    
    public DAG() {
        vertexes = new HashSet();
        edges = new HashMap();
        edgesReversed = new HashMap();
        root = null;
    }
    
    public void addVertex(String a){
        if (root == null){
            root = a;
        }
        vertexes.add(a);
        edges.put(a, new HashSet());
        edgesReversed.put(a, new HashSet());
    }
    public void addEdge(String a, String b){
        Set get = edges.get(a);
        get.add(b);
        Set<String> get1 = edgesReversed.get(b);
        get1.add(a);
       
        //check
        HashSet closed = new HashSet();
        Queue<String> queue = new LinkedList();
        queue.add(root);
        while (!queue.isEmpty()){
            String element = queue.poll();
            if (closed.contains(element)){
                throw new RuntimeException("There is a cycle in the graph");
            }
            closed.add(element);
//            System.out.println(edges);
            for (var e: edges.get(element)){
                queue.add(e);
            }
        }
        
    }
    
    
    public Set<String> getAncestors(String a){
        HashSet ret = new HashSet();
        
        Queue<String> queue = new LinkedList();
        for (var v: edgesReversed.get(a)){
            queue.add(v);
        }
        
        while (!queue.isEmpty()){
            String poll = queue.poll();
            ret.add(poll);
            for (var v: edgesReversed.get(poll)){
                queue.add(v);
            }
            
        }
        return ret;
    }
    
    
    public void print(){
        System.out.println(vertexes);
        System.out.println(edges);
        System.out.println(edgesReversed);
        
    }
    
}
