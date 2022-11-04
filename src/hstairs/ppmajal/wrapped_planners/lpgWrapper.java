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
package com.hstairs.ppmajal.wrapped_planners;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class lpgWrapper extends planningTool {

    protected long totalReplanningtime;

    public lpgWrapper ( ) {
        super();
        option1 = "-quality -timesteps";
        option2 = "-out temp";
        planningExec = "lpg-td-1.0";

    }

    @Override
    public String adapt (String domainFile, String problemFile, String planFile) {
        try {
            this.planningExec = "lpg-adapt";
            this.option2 = this.option2 + " -input_plan " + planFile;

            //System.out.println("planning");
            this.setDomainFile(domainFile);
            this.setProblemFile(problemFile);
            this.executePlanning();
            System.out.println(outputPlanning);

            if (this.outputPlanning.toString().contains("unsolvable") || this.outputPlanning.toString().contains("can not be reached")) {
                moveFiles(problemFile);
                this.failed = true;
                System.out.println("....UNSOLVABLE");
                return null;
            }
            if (this.isTimeoutFail()) {
                System.out.println("....TIMEOUT");
                return null;
            }
            putSolutionInFile2("temp.SOL");

            return this.storedSolutionPath;
        } catch (IOException ex) {
            Logger.getLogger(metricFFWrapper.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    @Override
    public String plan ( ) {
        try {
            this.executePlanning();
            if (this.isFailed()) {
                return null;
            }
            if (!failed) {
                if (!(new File("temp.SOL").exists())) {
                    failed = true;
                } else {
                    putSolutionInFile2("temp.SOL");
                }
            }
            return this.storedSolutionPath;
        } catch (IOException ex) {
            Logger.getLogger(metricFFWrapper.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public String plan (String domainFile, String problemFile) {
        try {
            //System.out.println("planning");
            this.setDomainFile(domainFile);
            this.setProblemFile(problemFile);
            this.executePlanning();
            //System.out.println(this.outputPlanning);
            //putSolutionInFile(this.outputPlanning);
            if (!failed) {
                if (!(new File("temp.SOL").exists())) {
                    failed = true;
                } else {
                    putSolutionInFile2("temp.SOL");
                }
            }
            return this.storedSolutionPath;
        } catch (IOException ex) {
            Logger.getLogger(metricFFWrapper.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private void putSolutionInFile (String s) throws IOException {

        Scanner sc = new Scanner(s);
        Writer output = new BufferedWriter(new FileWriter(storedSolutionPath));
        output.write("\n");
        ArrayList a = new ArrayList();
        while (sc.hasNextLine()) {
            String prova;
            prova = sc.findInLine("[0-9]:[\\s][(][a-zA-Z0-9_[-]\\s]*[)]");
            //prova = sc.findInLine("ACTION");
            if (prova != null) {
                output.write(prova + "\n");
                //System.out.println(prova.substring(3));
                //a.add(prova);
            } else {
                String time = sc.findInLine("Total time:[\\s]*[0-9]+[.][0-9]+");
                if (time != null) {
                    Float t = Float.parseFloat(time.substring(time.indexOf(":") + 1));
                    Integer msec = (int) (float) (t * 1000);
                    this.setTimePlanner(msec);
                    System.out.println("time" + this.getPlannerTime());

                }

            }

            sc.nextLine();
        }
        //System.out.println(a.size());
        output.close();
        //System.exit(-1);
    }

    private void putSolutionInFile2 (String s) throws IOException {

        Scanner sc = new Scanner(new FileReader(s));
        Writer output = new BufferedWriter(new FileWriter(storedSolutionPath));
        output.write("\n");
        //System.out.println("prova");
        //ArrayList a = new ArrayList();
        while (sc.hasNextLine()) {

            String prova;
            prova = sc.findInLine("[(][a-zA-Z0-9_[-]\\s]*[)]");
            //prova = sc.findInLine("ACTION");
            if (prova != null) {
                output.write(prova + "\n");
                //System.out.println(prova.substring(3));
                //a.add(prova);
            } else {
                String time = sc.findInLine("Time[\\s]*[0-9]+[.][0-9]+");
                if (time != null) {
                    Float t = Float.parseFloat(time.substring(time.indexOf(" ") + 1));
                    Integer msec = (int) (float) (t * 1000);
                    this.setTimePlanner(msec);
                    System.out.println("time" + this.getPlannerTime());

                }

            }

            sc.nextLine();
        }
        //System.out.println(a.size());
        output.close();
        //System.exit(-1);
    }

    /**
     * @return the totalReplanningtime
     */
    public long getTotalReplanningTimeAllowedContinualPlanningsetting ( ) {
        return totalReplanningtime;
    }

    /**
     * @param totalReplanningtime the totalReplanningtime to set
     */
    public void setTotalReplanningTimeContinualPlanningSetting (long totalReplanningtime) {
        this.totalReplanningtime = totalReplanningtime;
    }

    private void moveFiles (String problemFile) {

        try {
            File afile = new File(problemFile);

            if (afile.renameTo(new File(problemFile + "UNSOLVABLE"))) {
                System.out.println("File is moved successful!");
            } else {
                System.out.println("File is failed to move!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void changePlannersPath ( ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
