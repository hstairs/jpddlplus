package com.hstairs.ppmajal.search;

import com.hstairs.ppmajal.search.searchnodes.BucketPriorityQueueNode;
import com.hstairs.ppmajal.search.searchnodes.SearchNode;
import it.unimi.dsi.fastutil.objects.*;
import it.unimi.dsi.fastutil.PriorityQueue;
import org.apache.commons.lang3.NotImplementedException;

import java.util.*;

public class BucketPriorityQueue implements PriorityQueue<BucketPriorityQueueNode> {

    private LinkedList<BucketPriorityQueueNode>[] q;


    private int size;
    private int kMin;
    final TieBreaker t;

    final int precision;
    public BucketPriorityQueue(int maxDistInitial, TieBreaker t, int precision){
        q = new LinkedList[maxDistInitial];
        kMin = 0;
        size = 0;
        this.t = t;
        this.precision = precision;
    }
    public BucketPriorityQueue(int maxDistInitial, TieBreaker t){
        this(maxDistInitial,t,0);
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

    @Override
    public void enqueue(BucketPriorityQueueNode n) {
        try {
            final int f = (int)Math.floor(n.getRanks()[0]*(Math.pow(10,precision)));
            //System.out.println("Value of f" +f);
            //System.out.println("Value coming from heuristic:"+n.getRanks()[0]);
            if (f >= q.length) {
                int newSize = (q.length * 2);
                q = Arrays.copyOf(q, f > newSize ? f+1 : newSize);
            }
            if (q[f] == null) {
                q[f] = new LinkedList();
            }
            q[f].add(n);
            size++;
            kMin = (f < kMin) ? f : kMin;

        }catch (Exception e){
            System.out.println("Current exception:"+e.getMessage());
        }
    }


    public BucketPriorityQueueNode dequeue(){
        int k = kMin;
        while (k < q.length){
            if (q[k] != null && !q[k].isEmpty()){
                size--;
                if (t.tb == SearchEngine.TieBreaking.HIGHERG){
                    return q[k].pollLast();
                }else{
                    return q[k].pollFirst();
                }
            }else{
                k++;
                kMin = k;
            }
        }
        return null;
    }

    public boolean isEmpty(){
        return size == 0;
    }

}
