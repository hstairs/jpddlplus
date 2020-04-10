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

import com.hstairs.ppmajal.conditions.AndCond;
import com.hstairs.ppmajal.conditions.Comparison;
import com.hstairs.ppmajal.conditions.Condition;
import com.hstairs.ppmajal.conditions.Predicate;
import com.hstairs.ppmajal.expressions.Expression;
import com.hstairs.ppmajal.expressions.Interval;
import com.hstairs.ppmajal.expressions.NumFluent;
import com.hstairs.ppmajal.expressions.PDDLNumber;
import it.unimi.dsi.fastutil.ints.Int2IntArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;

import java.util.HashMap;

/**
 * @author enrico
 */
public class RelState extends Object {

    public Int2IntArrayMap possBollValues;//0 is negative, 1 positive, 2 both
    public Int2ObjectArrayMap<Interval> possNumValues;

    public RelState ( ) {
        super();
        possBollValues = new Int2IntArrayMap();
        possNumValues = new Int2ObjectArrayMap();
    }


    @Override
    public RelState clone ( ) {
        RelState ret_val = new RelState();
        ret_val.possBollValues = this.possBollValues.clone();
        ret_val.possNumValues = this.possNumValues.clone();
        return ret_val;
    }


    public PDDLNumber functionInfValue (NumFluent f) {
        Interval n = this.possNumValues.get(f.getId());
        if (n != null) {
            return n.getInf();
        }
        return null;
    }

    public Interval functionValues (NumFluent f) {

        if (!this.possNumValues.isEmpty()) {
            Interval a = this.possNumValues.get(f.getId());
            if (a != null) {
                return a;
            } else {
                Interval ret_val = new Interval(Float.NaN);
                return ret_val;
            }
        }
        return new Interval(Float.NaN);

    }

    public PDDLNumber functionSupValue (NumFluent f) {
        Interval a = this.possNumValues.get(f.getId());
        if (a != null) {
            return a.getSup();
        }
        return null;
    }

    public void makePositive (Predicate p) {
        Integer inter = possBollValues.get(p.getId());
        if (inter == null) {//if was negative by default
            possBollValues.put(p.getId(), 2);
        } else if (inter == 0) {//if was said to be negative
            possBollValues.put(p.getId(), 2);
        }//otherwise it was already fine
    }


    public boolean canBeTrue (Predicate p) {

        int o = this.possBollValues.getOrDefault(p.getId(), -1);
        if (o == -1) {
            return false;
        }
        return o >= 1;
    }

    public boolean canBeFalse (Predicate p) {

        int o = this.possBollValues.getOrDefault(p.getId(), -1);
        if (o == -1) {
            return true;
        }
        return o == 0 || o == 2;
    }

    public void setFunctionInfValue (NumFluent f, PDDLNumber after) {
        Interval a = this.possNumValues.get(f.getId());
        if (a != null) {
            a.setInf(after);
        }

    }

    public void setFunctionSupValue (NumFluent f, PDDLNumber after) {
        Interval a = this.possNumValues.get(f.getId());
        if (a != null) {
            a.setSup(after);
        }

    }

    public void makeNegative (Predicate p) {
        int inter = possBollValues.getOrDefault(p.getId(), -1);
        if (inter == -1) {//if was negative by default
        } else if (inter == 1) {//if was said to be positive it will also be negative
            possBollValues.put(p.getId(), 2);
        }//otherwise all good (inter == 2)
    }

    public boolean satisfy (Condition con) {

        if (con == null) {
            System.out.println(this);
            System.out.println("something wrong");
            System.exit(-1);
        }
        return con.can_be_true(this);

    }

    public void setFunctionValues (NumFluent f, Interval after) {
        this.possNumValues.put(f.getId(), after);

    }

    public float satisfaction_distance (Comparison comparison) {
        if (this.satisfy(comparison)) {
            return 0;
        }
        RelState s = this;
        Expression left = comparison.getLeft();
        Expression right = comparison.getRight();
        Interval lv = left.eval(s);
        Interval rv = right.eval(s);
        if ((lv == null) || (rv == null)) {
            return Float.MAX_VALUE;
        }
        if ((lv.getInf() == null) || (lv.getSup() == null) || (rv.getInf() == null) || (rv.getSup() == null)) {
            return Float.MAX_VALUE;//negation by failure.
        }
        if (comparison.getComparator().equals("<")) {
            return lv.getInf().getNumber() - rv.getSup().getNumber() + Float.MIN_VALUE;
        } else if (comparison.getComparator().equals("<=")) {
            return lv.getInf().getNumber() - rv.getSup().getNumber();
        } else if (comparison.getComparator().equals(">")) {
            return rv.getInf().getNumber() - lv.getSup().getNumber() + Float.MIN_VALUE;
        } else if (comparison.getComparator().equals(">=")) {
            return rv.getInf().getNumber() - lv.getSup().getNumber();
        } else if (comparison.getComparator().equals("=")) {
            if (!((lv.getInf().getNumber() > rv.getSup().getNumber()) || (rv.getInf().getNumber() > lv.getSup().getNumber()))) {
                return -Float.MIN_VALUE;
            } else if (lv.getInf().getNumber() > lv.getInf().getNumber()) {
                return lv.getInf().getNumber() - lv.getInf().getNumber();
            } else {
                return rv.getInf().getNumber() - lv.getSup().getNumber();
            }
        } else {
            System.out.println(comparison.getComparator() + "  is not supported");
        }

        return Float.MAX_VALUE;
    }

    public void update_values (HashMap subst) {
        for (final Object o : subst.keySet()) {
            if (o instanceof NumFluent) {
                NumFluent nf = (NumFluent) o;
                if (nf.has_to_be_tracked()) {
                    this.setFunctionValues(nf, (Interval) subst.get(o));
                }
            } else {
                this.possBollValues.put(((Predicate) o).getId(), (int) subst.get(o));
            }
        }
    }

    public RelState apply_with_generalized_interval_based_relaxation (GroundAction gr) {
        HashMap subst = new HashMap();
        AndCond del = gr.getDelList();
        if (del != null) {
            subst.putAll(del.apply(this));
        }
        AndCond add = gr.getAddList();
        if (add != null) {
            subst.putAll(add.apply(this));
        }

        AndCond c = gr.getNumericEffects();
//        System.out.println("GroundAction:"+this);
        subst.putAll(c.apply(this));

        if (gr.cond_effects != null) {
            AndCond c_eff = gr.cond_effects;
            subst.putAll(c_eff.apply(this));
        }

        for (final Object o : subst.keySet()) {
            if (o instanceof NumFluent) {
                NumFluent nf = (NumFluent) o;
                if (nf.has_to_be_tracked()) {

                    this.setFunctionValues(nf, (Interval) subst.get(o));

                }
            } else {
                this.possBollValues.put(((Predicate) o).getId(), (int) subst.get(o));
            }
        }
        return this;
    }


    public RelState apply (GroundAction gr) {


        HashMap subst = new HashMap();
        AndCond del = gr.getDelList();
        if (del != null) {
            del.apply(this, subst);
        }
        AndCond add = gr.getAddList();
        if (add != null) {
            add.apply(this, subst);
        }

        AndCond c = gr.getNumericEffects();
        c.apply(this, subst);

        if (gr.cond_effects != null) {
            AndCond c_eff = gr.cond_effects;
            c_eff.apply(this, subst);
        }

        this.update_values(subst);

        return this;

    }

}
