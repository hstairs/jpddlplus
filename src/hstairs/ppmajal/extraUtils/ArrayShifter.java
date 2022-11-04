package com.hstairs.ppmajal.extraUtils;

import com.hstairs.ppmajal.conditions.Terminal;
import java.util.Collection;

public class ArrayShifter
{
    final int[] id2tid;
    final int[] tid2id;
    private final int maxId;
    private final int maxTid;

    public ArrayShifter(Collection coll) {
        int max = -1;
        for (Object terminal : coll) {
            if (terminal instanceof Integer){
                max = Math.max((Integer) terminal, max);
            }else if (terminal instanceof Terminal){
                max = Math.max(((Terminal) terminal).getId(), max);
            }
        }
        this.maxId = max+1;
        id2tid = new int[max+1];
        tid2id = new int[coll.size()];
        this.maxTid = coll.size();
        int counter = 0;
        for (Object terminal : coll) {
            if (terminal instanceof Terminal) {
                id2tid[((Terminal) terminal).getId()] = counter;
                tid2id[counter] = ((Terminal) terminal).getId();
            }else if (terminal instanceof  Integer){
                id2tid[(int) terminal] = counter;
                tid2id[counter] = (int) terminal;
            }
            counter++;
        }
    }

    public int getMaxTid() {
        return maxTid;
    }

    public int getTID(Integer t){
        return id2tid[t];
    }
}
