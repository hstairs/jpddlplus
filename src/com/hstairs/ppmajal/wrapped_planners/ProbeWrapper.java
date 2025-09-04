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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ProbeWrapper extends planningTool {

    private String solutionFile;

    public ProbeWrapper ( ) {
        super();
        option1 = "";       //"-O";
        option2 = "";
        planningExec = "probe";
        this.solutionFile = "out";
        this.setTimeout(100000);

//        ArrayList solution;
    }

    @Override
    public String plan ( ) {

        System.out.println("Planning...");
        this.executePlanning();
        System.out.println(outputPlanning);
        if (this.outputPlanning.toString().contains("unsolvable")) {
            this.failed = true;
            System.out.println("....UNSOLVABLE");
            return null;
        }
        if (!this.outputPlanning.toString().contains("SOLUTION 1")) {
            this.failed = true;
            System.out.println("....UNKNOWN ERROR");
            return null;
        }
        if (this.isFailed()) {
            System.out.println("....TIMEOUT");
            return null;
        }
        System.out.println("....SUCCESS");
        setTime(this.outputPlanning.toString());
        return this.solutionFile + ".1";

    }

    @Override
    public void executePlanning ( ) {
        Runtime rt = Runtime.getRuntime();
        outputPlanning = new StringBuilder();
        try {

            Runtime runtime = Runtime.getRuntime();
            System.out.println("Executing: " + planningExec + " -d " + domainFile + " -i " + problemFile + " -o " + getSolutionFile() + option1 + " " + option2);
            process = runtime.exec(planningExec + " -d " + domainFile + " -i " + problemFile + " -o " + getSolutionFile() + option1 + " " + option2);
            /* Set up process I/O. */

            Worker worker = new Worker(process);
            worker.start();
            worker.join(getTimeout());

            if (worker.exit != null) {
                BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));

                String line = null;
                while ((line = input.readLine()) != null) {
                    outputPlanning = outputPlanning.append(line).append("\n");
                    //System.out.println(outputPlanning);
                }
            } else {
                process.destroy();
                failed = true;
                this.setTimeoutFail(true);
                this.setTimePlanner((int) getTimeout());
            }

        } catch (IOException e) {
            System.out.println("Planner eccezione" + e.toString());
        } catch (InterruptedException e) {
            System.out.println("Planner eccezione" + e.toString());
        }
    }

    @Override
    public String plan (String domainFile, String problemFile) {

        //System.out.println("planning");
        this.setDomainFile(domainFile);
        this.setProblemFile(problemFile);

        return plan();

    }

    private void putSolutionInFile (String s) {

    }

    @Override
    public String adapt (String domainFile, String problemFile, String planFile) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @return the solutionFile
     */
    public String getSolutionFile ( ) {
        return solutionFile;
    }

    /**
     * @param solutionFile the solutionFile to set
     */
    public void setSolutionFile (String solutionFile) {
        this.solutionFile = solutionFile;
    }

    private void setTime (String outputPlanning) {
        Scanner sc;
        sc = new Scanner(outputPlanning);

        while (sc.hasNextLine()) {
            String test = sc.findInLine("Time: [0-9]+[.][0-9]+");
            if (test != null) {
                Scanner temp = new Scanner(test);
                this.setTimePlanner((int) (Float.parseFloat(temp.findInLine("[0-9]+[.][0-9]+")) * 1000));
                System.out.println("time" + this.getPlannerTime());
            } else {
                sc.nextLine();
            }
        }

    }

    @Override
    public void changePlannersPath ( ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
