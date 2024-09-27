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

/**
 * @author enrico
 */
public class ExtendedAddendum {

    public Double n;
    public NumFluent f;
    public BinaryOp bin;
    public boolean linear;

    public ExtendedAddendum ( ) {
        linear = true;
    }

    public ExtendedAddendum (NumFluent f, Double n) {

        this.f = f;
        this.n = n;
        linear = true;

    }

    public ExtendedAddendum (BinaryOp bin) {
        this.bin = bin;
        linear = false;
    }

    @Override
    protected Object clone ( ) {
        ExtendedAddendum ret = new ExtendedAddendum();
        if (!this.linear) {
            ret.bin = (BinaryOp) this.bin.clone();
            ret.linear = false;
        } else {
            if (ret.bin != null) {
                System.out.println("There is a problem when cloning the following addendum:" + ret);
                System.exit(-1);
            }
            if (this.f != null) {
                ret.f = (NumFluent) this.f.clone();
            } else {
                ret.f = null;
            }
//            if (this.n == null){
//                System.out.println("There is a problem when cloning the following addendum:"+ret);
//                System.exit(-1);
//            }
            if (this.n == null) {
                System.out.println("Error:" + this);
            }
            ret.n = this.n;
        }
        return ret;
    }

    @Override
    public boolean equals (Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        final ExtendedAddendum other = (ExtendedAddendum) obj;
        if (other.bin != null || this.bin != null) {
            return false;
        }
//        System.out.println("Left of the comparison:"+this);
//        System.out.println("Right of the comparison:"+obj);
        if (!other.n.equals(this.n)) {
            return false;
        }
        if (other.f == null && this.f != null) {
            return false;
        }
        if (other.f != null && this.f == null) {
            return false;
        }
        if (other.f == null && this.f == null) {
            return true;
        }

        return other.f.equals(this.f);
    }

    @Override
    public int hashCode ( ) {
        int hash = 3;
        hash = 37 * hash + (this.n != null ? this.n.hashCode() : 0);
        hash = 37 * hash + (this.f != null ? this.f.hashCode() : 0);
        return hash;
    }

    public Double scale (ExtendedAddendum obj) {
        if (this.f != obj.f) {
            return null;
        }
        Double a = this.n;
        Double b = obj.n;
        return a / b;
    }

    @Override
    public String toString ( ) {
        System.out.println("Linear:" + this.linear);
        return "Addendum{" + "n=" + n + ", f=" + f + "bin = " + bin + '}';
    }

}
