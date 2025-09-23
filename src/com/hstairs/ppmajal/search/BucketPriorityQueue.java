package com.hstairs.ppmajal.search;

import com.hstairs.ppmajal.search.searchnodes.BucketPriorityQueueNode;
import com.hstairs.ppmajal.search.searchnodes.SearchNode;
import it.unimi.dsi.fastutil.objects.*;
import it.unimi.dsi.fastutil.PriorityQueue;
import org.apache.commons.lang3.NotImplementedException;

import java.util.*;

public class BucketPriorityQueue implements PriorityQueue<BucketPriorityQueueNode> {

    private Object[] q;

    private int levels;
    private int size;
    private int kMin;

    private final int maxF;

    final int precision;

    public BucketPriorityQueue(int maxDistInitial){
        this(maxDistInitial,0);
    }
    public BucketPriorityQueue(int maxDistInitial, int precision) {
        this(maxDistInitial,precision,1);
    }

    public BucketPriorityQueue(int maxDistInitial, int precision, int levels ){
        kMin = 0;
        size = 0;
        this.precision = precision;
        this.levels = levels;
        if (levels == 1)
            q = new LinkedList[maxDistInitial];
        else if (levels == 2){
            q = new BucketPriorityQueue[maxDistInitial];
        }
        this.maxF = maxDistInitial;
    }


    public int size(){
        return size;
    }

    @Override
    public void clear() {
        throw new NotImplementedException("Not implemented");
    }

    @Override
    public SearchNode first() {
        throw new NotImplementedException("Not implemented");
    }

    @Override
    public BucketPriorityQueueNode last() {
        return PriorityQueue.super.last();
    }

    @Override
    public void changed() {
        PriorityQueue.super.changed();
    }

    @Override
    public Comparator<? super BucketPriorityQueueNode> comparator() {
        throw new NotImplementedException("Not implemented");
    }


    public void enqueue(BucketPriorityQueueNode n, int f){
        if (f < 0){
            throw new RuntimeException("f got negative!");
        }
        try {//System.out.println("Value of f" +f);
            if (f >= q.length) {
                q = Arrays.copyOf(q, f+2);
            }
            if (levels == 1) {
                if (q[f] == null) {
                    q[f] = new LinkedList();
                }
                ((LinkedList)q[f]).add(n);
            }else if (levels == 2){
                final int g = (int)Math.floor(n.getRanks()[1]*(Math.pow(10,precision)));
                if (q[f] == null) {
                    q[f] = new BucketPriorityQueue(this.maxF);
                }
                ((BucketPriorityQueue)q[f]).enqueue(n,g);
            }
            size++;
            kMin = (f < kMin) ? f : kMin;

        }catch (Exception e){
            System.out.println("Current exception:"+e.getMessage());
            System.out.println(q.length);
            System.out.println(q.length*2);
            System.out.println(f);
            System.exit(-1);
        }
    }
    @Override
    public void enqueue(BucketPriorityQueueNode n) {
        final int f = (int)Math.floor(n.getRanks()[0]*(Math.pow(10,precision)));
        this.enqueue(n,f);
    }


    public BucketPriorityQueueNode dequeue(){
        int k = kMin;
        while (k < q.length){
            if (q[k] != null){
                if (levels == 1) {
                    final LinkedList<BucketPriorityQueueNode> subQ = (LinkedList) q[k];
                    if (!subQ.isEmpty()){
                        size--;
                        return subQ.pollFirst();
                    }
                } else if (levels == 2){
                    final BucketPriorityQueue subQ = (BucketPriorityQueue) q[k];
                    if (!subQ.isEmpty()){
                        size--;
                        return subQ.dequeue();
                    }
                }
            }
            k++;
            kMin = k;
        }
        return null;
    }

    public boolean isEmpty(){
        return size == 0;
    }

}
