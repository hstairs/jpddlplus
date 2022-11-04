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

import net.java.jinterval.interval.set.SetInterval;
import net.java.jinterval.interval.set.SetIntervalContext;
import net.java.jinterval.interval.set.SetIntervalContexts;
import net.sourceforge.interval.ia_math.IAMath;
import net.sourceforge.interval.ia_math.RealInterval;

/**
 * @author Enrico Scala
 */
public class HomeMadeRealInterval {

    //TO-DO get rid of Interval and use RealInterval instead
    public boolean is_not_a_number;
    private double lo;
    private double hi;


    public HomeMadeRealInterval (double i) {
        if (Double.isNaN(i)) {
            is_not_a_number = true;
            lo = Float.NaN;
            hi = Float.NaN;
        }
        lo = i;
        hi = i;
    }
    public HomeMadeRealInterval (double lo, double hi) {
        if (Double.isNaN(lo)) {
            is_not_a_number = true;
            lo = Float.NaN;
            hi = Float.NaN;
        }
        this.lo = lo;
        this.hi = hi;
    }
    

    public void set_open_interval ( ) {

        lo = -Double.MAX_VALUE;
        hi = Double.MAX_VALUE;
    }

    public HomeMadeRealInterval sum (HomeMadeRealInterval b) {
        final RealInterval ret = IAMath.add(new RealInterval(lo(), hi()),
                new RealInterval(b.lo(), b.hi()));
        return new HomeMadeRealInterval(ret.lo(),ret.hi());
    }

    public HomeMadeRealInterval subtract (HomeMadeRealInterval b) {
        final RealInterval ret = IAMath.sub(new RealInterval(lo(), hi()),
                new RealInterval(b.lo(), b.hi()));

        return new HomeMadeRealInterval(ret.lo(),ret.hi());
    }

    public HomeMadeRealInterval mult (double a) {
        if (lo == hi){
            return new HomeMadeRealInterval(lo*a);    
        }   
        final RealInterval ret = IAMath.mul(new RealInterval(lo(), hi()),
                new RealInterval(a));
        return new HomeMadeRealInterval(ret.lo(),ret.hi());
    }

    public HomeMadeRealInterval mult (HomeMadeRealInterval b) {
//        System.out.println(this);
        final RealInterval ret = IAMath.mul(new RealInterval(lo(), hi()),
                new RealInterval(b.lo(), b.hi()));
        return new HomeMadeRealInterval(ret.lo(),ret.hi());

    }

    public boolean contain_zero ( ) {
        return lo() <= 0 && hi() >= 0;
    }

    public HomeMadeRealInterval div (HomeMadeRealInterval b) {
        final RealInterval ret = IAMath.odiv(new RealInterval(lo(), hi()),
                new RealInterval(b.lo(), b.hi()));

        return new HomeMadeRealInterval(ret.lo(),ret.hi());
    }

    @Override
    public HomeMadeRealInterval clone ( ) {
        return new HomeMadeRealInterval(lo, hi);
    }

    public HomeMadeRealInterval sum (double number) {
        if (lo == hi){
            return new HomeMadeRealInterval(lo+number);
        }
                
        final RealInterval ret = IAMath.add(new RealInterval(lo(), hi()),
                new RealInterval(number, number));
        return new HomeMadeRealInterval(ret.lo(),ret.hi());
    }

    public boolean can_be_negative ( ) {
        return this.lo() < 0.0 || this.hi() < 0.0;//the second case should not happen
    }

    public boolean can_be_positive ( ) {
        return this.lo()> 0 || this.hi() > 0;
    }
    
    public HomeMadeRealInterval pown (int exp) {
        final SetIntervalContext interval = SetIntervalContexts.getFast();
        final SetInterval a = interval.numsToInterval(lo, hi);
        final SetInterval pow = interval.pown(a, exp);
        return new HomeMadeRealInterval(pow.doubleInf(),pow.doubleSup());
    }

    public HomeMadeRealInterval pow (HomeMadeRealInterval second) {

        double newLo;
        double newHi;
        if (second.lo == 0.5f) {//sqrt treatment
//            System.out.println("Debug");
            //both negative
            
            if (this.lo < 0 && this.hi < 0) {
                newLo = Float.NaN;
                return new HomeMadeRealInterval(newLo,newLo);
            }
            //the inf is negative
            if (this.lo < 0) {
                newLo = 0.0d;
                newHi = Math.pow(this.hi, second.lo);
                return new HomeMadeRealInterval(newLo,newHi);
            }
            //both positive
            newLo = Math.pow(this.lo, second.lo);
            newHi = Math.pow(this.hi, second.lo);
            
            return new HomeMadeRealInterval(newLo,newHi);

        } else {//higher integral powers
            if (this.lo > 0 || (second.lo % 2 != 0)) {
//            if (this.inf >= 0 ) {

//                System.out.println("debugLevel");
                newLo = Math.pow(this.lo, second.lo);
                newHi = Math.pow(this.hi, second.lo);

            } else if (this.hi < 0) {
                newLo = Math.pow(this.hi, second.lo);
                newHi = Math.pow(this.lo, second.lo);
            } else {
                newLo = 0.0d;
                newHi = Math.max((float) Math.pow(this.lo, second.lo), (float) Math.pow(this.hi, second.lo));
            }
            return new HomeMadeRealInterval(newLo,newHi);
        }
    }

    public HomeMadeRealInterval sin ( ) {
        final RealInterval ret = IAMath.sin(new RealInterval(lo(), hi()));
        return new HomeMadeRealInterval(ret.lo(),ret.hi());
    }

    public HomeMadeRealInterval cos ( ) {
        final RealInterval ret = IAMath.cos(new RealInterval(lo(), hi()));
        return new HomeMadeRealInterval(ret.lo(),ret.hi());

    }

    public HomeMadeRealInterval asin ( ) {
        final RealInterval ret = IAMath.asin(new RealInterval(lo(), hi()));
        return new HomeMadeRealInterval(ret.lo(),ret.hi());

    }

    public HomeMadeRealInterval acos() {
        final RealInterval ret = IAMath.acos(new RealInterval(lo(), hi()));
        return new HomeMadeRealInterval(ret.lo(), ret.hi());
    }

    public HomeMadeRealInterval tan() {
        final RealInterval ret = IAMath.tan(new RealInterval(lo(), hi()));
        return new HomeMadeRealInterval(ret.lo(), ret.hi());
    }

    public HomeMadeRealInterval atan() {
        final RealInterval ret = IAMath.atan(new RealInterval(lo(), hi()));
        return new HomeMadeRealInterval(ret.lo(), ret.hi());
    }

    public HomeMadeRealInterval abs() {

        double newLo;
        double newHi;
        if (this.contain_zero()) {
            newLo = 0.0f;
            newHi = Math.max(Math.abs(this.lo), Math.abs(this.hi));
        } else if (this.hi <= 0) {
            newLo = Math.abs(this.hi);
            newHi = Math.abs(this.lo);
        } else {
            newLo = Math.abs(this.lo);
            newHi = Math.abs(this.hi);
        }
        return new HomeMadeRealInterval(newLo, newHi);
    }

    @Override
    public String toString ( ) {
        return "(" + this.lo() + "," + this.hi() + ")";
    }

    /**
     * @return the inf
     */
    public double lo ( ) {
        return lo;
    }

    /**
     * @param inf the inf to set
     */
    public void setInf (double inf) {
        this.lo = inf;
    }

    /**
     * @return the sup
     */
    public double hi ( ) {
        return hi;
    }

    /**
     * @param sup the sup to set
     */
    public void setSup (double sup) {
        this.hi = sup;
    }

    public HomeMadeRealInterval atan2(HomeMadeRealInterval second) {
        SetIntervalContext interval = SetIntervalContexts.getFast();
        SetInterval a = interval.numsToInterval(lo, hi);
        SetInterval b = interval.numsToInterval(second.lo, second.hi);
        SetInterval atan2 = interval.atan2(a, b);
        return new HomeMadeRealInterval(atan2.doubleInf(),atan2.doubleSup());
        
    }
}
