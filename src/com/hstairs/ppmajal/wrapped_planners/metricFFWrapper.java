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

import com.hstairs.ppmajal.extraUtils.Utils;

import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class metricFFWrapper extends planningTool {

    private String memoryLimit;

    public metricFFWrapper ( ) {
        super();
        option1 = "";       //"-O";
        option2 = "";

        planningExec = "ff";
        this.setTimeout(10000);
        Utils.deleteFile(storedSolutionPath);

//        ArrayList solution;
    }


    @Override
    public int computeHeuristic ( ) {
        int output = computeHeuristic(domainFile, problemFile);
        if (output == 0) {
            throw new RuntimeException("something went wrong" + this.outputPlanning);
        }
        return output;
    }

    @Override
    public int computeHeuristic (String domainFile, String problemFile) {
        //System.out.println("planning");
        Runtime rt = Runtime.getRuntime();
        outputPlanning = new StringBuilder();
        int heuristic = 0;
        try {

//            Utility.deleteFile("temp.SOL");
            Runtime runtime = Runtime.getRuntime();


//            System.out.println("This is what I am running");
//            System.out.println("Executing: " + planningExec + domain_file_option + domainFile + problem_file_option + problemFile + " " + option1 + " " + option2);
            process = runtime.exec(planningExec + domain_file_option + domainFile + problem_file_option + problemFile + " " + option1 + " " + option2);
            /* Set up process I/O. */

            Worker worker = new Worker(process);
            worker.start();

            worker.join(getTimeout());

            if (worker.exit != null) {
                BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));

                String line = null;
                while ((line = input.readLine()) != null) {
                    if (line.contains("Heuristic")) {
                        String[] res = line.split(":");
                        heuristic = Integer.parseInt(res[1].trim());
//                        System.out.println("There you go:"+heuristic);
                    }
                    this.outputPlanning.append(line);
                }

            } else {
                process.destroy();
                failed = false;
                this.setTimeoutFail(true);
                this.setTimePlanner((int) getTimeout());
            }
            //System.out.println("Time del planner: "+ this.getPlannerTime());

        } catch (IOException e) {
            System.out.println("Planner eccezione" + e.toString());
        } catch (InterruptedException e) {
            System.out.println("Planner eccezione" + e.toString());
        }

        return heuristic;
    }


    @Override
    public String plan ( ) {
        try {
//            System.out.println("Planning...");
            this.executePlanning();
            //System.out.println(outputPlanning);
            if (this.isTimeoutFail()) {
                System.out.println("....TIMEOUT");
                return null;
            }
            if (this.outputPlanning.toString().contains("unsolvable") || (this.outputPlanning.toString().contains("goal can be simplified to FALSE"))) {
                this.failed = true;
                System.out.println("....UNSOLVABLE");
                this.findTotalTimeInFile(outputPlanning.toString());
                return null;
            }
            if (!this.outputPlanning.toString().contains("found legal plan")) {
                this.failed = false;
                this.setPlannerError(true);
                System.out.println("....UNKNOWN ERROR!!");
                this.findTotalTimeInFile(outputPlanning.toString());
                throw new RuntimeException("Planning with FF failed. Output is:" + outputPlanning);
            }

//            System.out.println("....SUCCESS");
            putSolutionInFile(this.outputPlanning.toString());
            this.findTotalTimeInFile(outputPlanning.toString());

            return this.storedSolutionPath;
        } catch (IOException ex) {
            Logger.getLogger(metricFFWrapper.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public String plan (String domainFile, String problemFile) {

        //System.out.println("planning");
        this.setDomainFile(domainFile);
        this.setProblemFile(problemFile);

        return plan();

    }

    private void putSolutionInFile (String s) throws IOException {

        Scanner sc = new Scanner(s);
        boolean atleastanaction = false;
        Writer output = new BufferedWriter(new FileWriter(storedSolutionPath));
        output.write("\n");
        while (sc.hasNextLine()) {
            if (sc.findInLine("[0-9]: ") != null && sc.findInLine("REACH-GOAL")== null) {
                //System.out.println("("+sc.nextLine()+")");
                output.write("(" + sc.nextLine() + ")\n");
                atleastanaction = true;
            } else {
                sc.nextLine();
            }
        }

        output.close();

    }

    @Override
    public String adapt (String domainFile, String problemFile, String planFile) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void findTotalTimeInFile (String s) {
        Scanner sc = new Scanner(s);

        while (sc.hasNextLine()) {
            String test = sc.findInLine("[0-9]+[.][0-9]+ seconds total time");
            if (test != null) {
                Scanner temp = new Scanner(test);
                this.setTimePlanner((int) (Float.parseFloat(temp.findInLine("[0-9]+[.][0-9]+")) * 1000));
//                System.out.println("time" + this.getPlannerTime());
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
