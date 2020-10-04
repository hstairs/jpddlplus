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
package com.hstairs.ppmajal.extraUtils;

import com.hstairs.ppmajal.conditions.PDDLObject;
import com.hstairs.ppmajal.domain.Type;
import com.hstairs.ppmajal.problem.PDDLObjects;
import com.hstairs.ppmajal.transition.TransitionSchema;
import net.sourceforge.interval.ia_math.RealInterval;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
//import static sun.org.mozilla.javascript.Token.name;

/**
 * @author enrico
 */
public class Utils {

    public static HashMap interactsWith;



    public static void dbg_print (int debug, String string) {
        if (debug > 0) {
            System.out.print(string);
        }
    }
    public static PDDLObject getObjectByName(PDDLObjects objects, String name){
        for (Object o : objects) {
            PDDLObject el = (PDDLObject) o;
            if (el.getName().equalsIgnoreCase(name)) {
                return el;
            }
        }
        return null;
    }
    public static String toPDDLSet (Collection c) {
        String ret = "";
        for (Object o : c) {
            ret += " " + o.toString();
        }
        return ret;
    }

    public static void remove_file (String name) {
        try {

            File file = new File(name);
            file.delete();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    public static String toPDDLSetWithBreak (Collection c) {
//        String ret = "";
        StringBuilder strBuilder = new StringBuilder("");

        for (Object o : c) {
            strBuilder.append(o.toString());
            strBuilder.append("\n");
        }
        return strBuilder.toString();
    }



    public static String toPDDLWithExtraObject (Collection<TransitionSchema> c) {
        String ret = "";
        for (TransitionSchema o : c) {
            throw new UnsupportedOperationException("Implement writer with extra object, for whatever that means");
//            ret += o.pddlPrintWithExtraObject() + "\n";
        }
        return ret;
    }

    public static String toPDDLTypesSet (Collection c) {
        String ret = "";
        for (Object o : c) {
            Type t = (Type) o;
            ret += " " + t.pddlString();
        }
        return ret;
    }

    public static void deleteFile (String fileName) {

        new File(fileName).delete();
    }

    public static String searchParameterValue (String[] args, String par) {

        //System.out.println("Searching option " + par);
        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].equals(par)) {
                String ret = args[++i];
                if (ret == null) {
                    return null;
                }
                if (ret.charAt(0) == '-') {
                    return null;
                }
                return ret;
            }
        }

        return null;
    }

    public static boolean searchParameter (String[] args, String par) {

        //System.out.println("Searching option " + par);
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals(par)) {
                return true;
            }
        }

        return false;
    }
    
    public static float round2(float number, int scale) {
        int pow = 10;
        for (int i = 1; i < scale; i++) {
            pow *= 10;
        }
        float tmp = number * pow;
        return ((float) ((int) ((tmp - (int) tmp) >= 0.5f ? tmp + 1 : tmp))) / pow;
    }

    public static RealInterval abs(RealInterval arg) {
        if ((arg.hi()>=0) && (arg.lo()<=0)) {
            return new RealInterval(0,Math.max(Math.abs(arg.lo()), Math.abs(arg.hi())));
        } else if (arg.hi() <= 0) {
            return new RealInterval(Math.abs(arg.hi()),Math.abs(arg.lo()));
        } else {
            return new RealInterval(Math.abs(arg.lo()),Math.abs(arg.hi()));
        }
    }

//    public static String[] searchParameterValue(String[] args, String par) {
//
//        //System.out.println("Searching option " + par);
//        for (int i = 0; i < args.length - 1; i++) {
//            if (args[i].equals(par)) {
//                String[] ret = new String[100];
//                while(true){
//                    
//                }
//                
//                String ret = args[++i];
//                if (ret == null)
//                    return null;
//                if (ret.charAt(0)=='-')
//                    return null;
//                return ret;
//            }
//        }
//
//        return null;
//    }
}
