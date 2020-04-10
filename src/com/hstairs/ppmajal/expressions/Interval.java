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
package com.hstairs.ppmajal.expressions;

import net.sourceforge.interval.ia_math.IAMath;
import net.sourceforge.interval.ia_math.RealInterval;

/**
 * @author Enrico Scala
 */
public class Interval {

    //TO-DO get rid of Interval and use RealInterval instead
    public boolean is_not_a_number;
    private PDDLNumber inf;
    private PDDLNumber sup;

    public Interval ( ) {
        super();
        is_not_a_number = false;
        inf = new PDDLNumber();
        sup = new PDDLNumber();
    }

    public Interval (Float i) {
        if (i.isNaN()) {
            is_not_a_number = true;
            inf = new PDDLNumber(Float.NaN);
            sup = new PDDLNumber(Float.NaN);
        }
        inf = new PDDLNumber(i);
        sup = new PDDLNumber(i);
    }

    public void set_open_interval ( ) {

        getInf().setNumber(-Float.MAX_VALUE);
        getSup().setNumber(Float.MAX_VALUE);
    }

    public Interval sum (Interval b) {

//        System.out.println("DEBUG: "+this);
        RealInterval ret = IAMath.add(new RealInterval(getInf().getNumber(), getSup().getNumber()),
                new RealInterval(b.getInf().getNumber(), b.getSup().getNumber()));

        Interval ret_val = new Interval();
        ret_val.setInf(new PDDLNumber((float) ret.lo()));
        ret_val.setSup(new PDDLNumber((float) ret.hi()));
        return ret_val;
    }

    public Interval subtract (Interval b) {
        RealInterval ret = IAMath.sub(new RealInterval(getInf().getNumber(), getSup().getNumber()),
                new RealInterval(b.getInf().getNumber(), b.getSup().getNumber()));

        Interval ret_val = new Interval();
        ret_val.setInf(new PDDLNumber((float) ret.lo()));
        ret_val.setSup(new PDDLNumber((float) ret.hi()));
        return ret_val;
    }

    public Interval mult (Float a) {
//        System.out.println("DEBUG:"+this);
        RealInterval ret = IAMath.mul(new RealInterval(getInf().getNumber(), getSup().getNumber()),
                new RealInterval(a, a));

        Interval ret_val = new Interval();
        ret_val.setInf(new PDDLNumber((float) ret.lo()));
        ret_val.setSup(new PDDLNumber((float) ret.hi()));

        return ret_val;
    }

    public Interval mult (Interval b) {
//        System.out.println(this);
        RealInterval ret = IAMath.mul(new RealInterval(getInf().getNumber(), getSup().getNumber()),
                new RealInterval(b.getInf().getNumber(), b.getSup().getNumber()));

        Interval ret_val = new Interval();
        ret_val.setInf(new PDDLNumber((float) ret.lo()));
        ret_val.setSup(new PDDLNumber((float) ret.hi()));

        return ret_val;

    }

    public boolean contain_zero ( ) {
        return getInf().getNumber() <= 0 && getSup().getNumber() >= 0;
    }

    //    public Interval div(Interval b) {
//        
//        Interval ret_val = new Interval();
//        Float ac = new Float(inf.getNumber()) / new Float(b.inf.getNumber());
//        Float ad = new Float(inf.getNumber()) / new Float(b.sup.getNumber());
//        Float bc = new Float(sup.getNumber()) / new Float(b.inf.getNumber());
//        Float bd = new Float(sup.getNumber()) / new Float(b.sup.getNumber());
//        ret_val.inf = new PDDLNumber(Math.min(ac, Math.min(ad, Math.min(bc, bd))));
//        ret_val.sup = new PDDLNumber(Math.max(ac, Math.max(ad, Math.max(bc, bd))));
//
//         return ret_val;
//     }
    public Interval div (Interval b) {
        RealInterval ret = IAMath.odiv(new RealInterval(getInf().getNumber(), getSup().getNumber()),
                new RealInterval(b.getInf().getNumber(), b.getSup().getNumber()));

        Interval ret_val = new Interval();
        ret_val.setInf(new PDDLNumber((float) ret.lo()));
        ret_val.setSup(new PDDLNumber((float) ret.hi()));

        return ret_val;
    }

    @Override
    public Interval clone ( ) {
        Interval ret_val = new Interval();

        ret_val.setInf(new PDDLNumber(this.getInf().getNumber()));
        ret_val.setSup(new PDDLNumber(this.getSup().getNumber()));
        return ret_val;

    }

    public Interval sum (Float number) {
        RealInterval ret = IAMath.add(new RealInterval(getInf().getNumber(), getSup().getNumber()),
                new RealInterval(number, number));

        Interval ret_val = new Interval();
        ret_val.setInf(new PDDLNumber((float) ret.lo()));
        ret_val.setSup(new PDDLNumber((float) ret.hi()));

        return ret_val;
    }

    public boolean can_be_negative ( ) {
        return this.getInf().less(0) || this.getSup().less(0);//the second case should not happen
    }

    public boolean can_be_positive ( ) {
        return this.getInf().greater(0) || this.getSup().greater(0);
    }

    public Interval pow (Interval second) {
        Interval ret = new Interval();

        if (second.inf.getNumber() == 0.5f) {//sqrt treatment
//            System.out.println("Debug");
            //both negative
            if (this.inf.getNumber() < 0 && this.sup.getNumber() < 0) {
                ret.inf.setNumber(Float.NaN);
                return ret;
            }
            //the inf is negative
            if (this.inf.getNumber() < 0) {
                ret.inf.setNumber(0.0f);
                ret.sup.setNumber(new Float(Math.pow(this.sup.getNumber(), second.inf.getNumber())));
                return ret;
            }
            //both positive
            ret.inf.setNumber(new Float(Math.pow(this.inf.getNumber(), second.inf.getNumber())));
            ret.sup.setNumber(new Float(Math.pow(this.sup.getNumber(), second.inf.getNumber())));
            return ret;

        } else {//higher integral powers
            if (this.inf.getNumber() > 0 || (second.inf.getNumber() % 2 != 0)) {
//            if (this.inf.getNumber() >= 0 ) {

//                System.out.println("debugLevel");
                ret.inf.setNumber((float) Math.pow(this.inf.getNumber(), second.inf.getNumber()));
                ret.sup.setNumber((float) Math.pow(this.sup.getNumber(), second.inf.getNumber()));
            } else if (this.sup.getNumber() < 0) {
                ret.inf.setNumber((float) Math.pow(this.sup.getNumber(), second.inf.getNumber()));
                ret.sup.setNumber((float) Math.pow(this.inf.getNumber(), second.inf.getNumber()));
            } else {
                ret.inf.setNumber(0.0f);
                ret.sup.setNumber(Math.max((float) Math.pow(this.inf.getNumber(), second.inf.getNumber()), (float) Math.pow(this.sup.getNumber(), second.inf.getNumber())));
            }
            return ret;
        }
    }

    //    public Interval pow(Interval b) {
//                
//        System.out.println("First addendum"+this);        
//        System.out.println("Second addendum"+b);
//        RealInterval ret = IAMath.power(new RealInterval(getInf().getNumber(),getSup().getNumber()), 
//                                      new RealInterval(b.getInf().getNumber(),b.getSup().getNumber()));
//        
//        Interval ret_val = new Interval();
//        ret_val.setInf(new PDDLNumber((float) ret.lo()));
//        ret_val.setSup(new PDDLNumber((float) ret.hi()));
//                System.out.println(ret_val);
//
//        return ret_val;
//    }
    public Interval sin ( ) {
        RealInterval ret = IAMath.sin(new RealInterval(getInf().getNumber(), getSup().getNumber()));

        Interval ret_val = new Interval();
        ret_val.setInf(new PDDLNumber((float) ret.lo()));
        ret_val.setSup(new PDDLNumber((float) ret.hi()));

        return ret_val;
    }

    public Interval cos ( ) {
        RealInterval ret = IAMath.cos(new RealInterval(getInf().getNumber(), getSup().getNumber()));

        Interval ret_val = new Interval();
        ret_val.setInf(new PDDLNumber((float) ret.lo()));
        ret_val.setSup(new PDDLNumber((float) ret.hi()));

        return ret_val;
    }

    public Interval asin ( ) {
        RealInterval ret = IAMath.asin(new RealInterval(getInf().getNumber(), getSup().getNumber()));

        Interval ret_val = new Interval();
        ret_val.setInf(new PDDLNumber((float) ret.lo()));
        ret_val.setSup(new PDDLNumber((float) ret.hi()));

        return ret_val;
    }

    public Interval acos ( ) {
        RealInterval ret = IAMath.acos(new RealInterval(getInf().getNumber(), getSup().getNumber()));

        Interval ret_val = new Interval();
        ret_val.setInf(new PDDLNumber((float) ret.lo()));
        ret_val.setSup(new PDDLNumber((float) ret.hi()));

        return ret_val;
    }

    public Interval abs ( ) {

        Interval ret_val = new Interval();

//        ret_val.setInf(new PDDLNumber(Float.MIN_VALUE));
//        ret_val.setSup(new PDDLNumber(Float.MAX_VALUE));
//        return ret_val;
        if (this.contain_zero()) {
            ret_val.setInf(new PDDLNumber(0));
            ret_val.setSup(new PDDLNumber(Math.max(Math.abs(this.inf.getNumber()), Math.abs(this.sup.getNumber()))));
        } else if (this.sup.getNumber() <= 0) {
            ret_val.setInf(new PDDLNumber(Math.abs(this.sup.getNumber())));
            ret_val.setSup(new PDDLNumber(Math.abs(this.inf.getNumber())));
        } else {
            ret_val.setInf(new PDDLNumber(Math.abs(this.inf.getNumber())));
            ret_val.setSup(new PDDLNumber(Math.abs(this.sup.getNumber())));
        }

        return ret_val;
    }

    @Override
    public String toString ( ) {
        return "(" + this.getInf() + "," + this.getSup() + ")";
    }

    /**
     * @return the inf
     */
    public PDDLNumber getInf ( ) {
        return inf;
    }

    /**
     * @param inf the inf to set
     */
    public void setInf (PDDLNumber inf) {
        this.inf = inf;
    }

    /**
     * @return the sup
     */
    public PDDLNumber getSup ( ) {
        return sup;
    }

    /**
     * @param sup the sup to set
     */
    public void setSup (PDDLNumber sup) {
        this.sup = sup;
    }
}
