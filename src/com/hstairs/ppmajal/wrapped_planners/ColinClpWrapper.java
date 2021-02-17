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

public class ColinClpWrapper extends planningTool {

    public ColinClpWrapper ( ) {
        super();
        option1 = "";       //"-O";
        option2 = "";
        planningExec = "colin-clp";
        this.setTimeout(10000);
        Utils.deleteFile(storedSolutionPath);

//        ArrayList solution;
    }

    @Override
    public String plan ( ) {
        try {
            System.out.println("Planning...");
            this.executePlanning();
            //System.out.println(outputPlanning);
            if (this.isTimeoutFail()) {
                this.setTimeoutFail(true);
                this.failed = false;
                System.out.println("....TIMEOUT");
                this.findTotalTimeInFile(outputPlanning.toString());
                return null;
            }
            if (this.outputPlanning.toString().contains("unsolvable")) {
                this.failed = true;
                System.out.println("....UNSOLVABLE");
                this.findTotalTimeInFile(outputPlanning.toString());
                return null;
            }
            if (!this.outputPlanning.toString().contains("Solution Found")) {
                this.failed = false;
                this.setPlannerError(true);
                System.out.println("....UNKNOWN ERROR");
                this.findTotalTimeInFile(outputPlanning.toString());
                return null;
            }

            System.out.println("....SUCCESS");
            putSolutionInFile(this.outputPlanning.toString());
            this.findTotalTimeInFile(outputPlanning.toString());

            return this.storedSolutionPath;
        } catch (IOException ex) {
            Logger.getLogger(ColinClpWrapper.class.getName()).log(Level.SEVERE, null, ex);
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
        Writer output = new BufferedWriter(new FileWriter(storedSolutionPath));
        output.write("\n");
        //System.out.println("prova");
        //ArrayList a = new ArrayList();
        while (sc.hasNextLine()) {
            if (sc.findInLine("Solution Found") != null) {
                break;
            }
            sc.nextLine();
        }

        while (sc.hasNextLine()) {

            String prova;
            //prova = sc.findInLine("[0-9]+[.][0-9]+; [(][a-zA-Z0-9_[-]\\s]*[)]");
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
                    //System.out.println("time" + this.getPlannerTime());

                }

            }

            sc.nextLine();
        }
        //System.out.println(a.size());
        output.close();
        //System.exit(-1);

    }

    @Override
    public String adapt (String domainFile, String problemFile, String planFile) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void findTotalTimeInFile (String s) {
        Scanner sc = new Scanner(s);

        while (sc.hasNextLine()) {
            String time = sc.findInLine("Time[\\s]*[0-9]+[.][0-9]+");
            if (time != null) {
                Float t = Float.parseFloat(time.substring(time.indexOf(" ") + 1));
                Integer msec = (int) (float) (t * 1000);
                this.setTimePlanner(msec);
                System.out.println("time" + this.getPlannerTime());

            }
            sc.nextLine();

        }
    }

    @Override
    public void executePlanning ( ) {
        Runtime rt = Runtime.getRuntime();
        outputPlanning = new StringBuilder();
        try {

            Utility.deleteFile("temp.SOL");
            Runtime runtime = Runtime.getRuntime();
            String[] toExecute = new String[3];
            toExecute[0] = planningExec;
            toExecute[1] = domainFile;
            toExecute[2] = problemFile;
            //toExecute[0]= planningExec + "  " + domainFile + "  " + problemFile + " " + option1 + " " + option2;
            System.out.println(toExecute[0] + " " + toExecute[1] + " " + toExecute[2]);

            //process = runtime.exec("ff" + " -o travellingMetric.pddl -f  " + problemFile + " " + option1 + " " + option2);
            process = runtime.exec(planningExec + "  " + domainFile + "  " + problemFile);
            /* Set up process I/O. */

            Killer kill = new Killer(process, timeout);

            kill.start();
            System.out.println("Seeing the output");
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";
            while ((line = input.readLine()) != null) {
                outputPlanning = outputPlanning.append(line + "\n");
                //System.out.println(outputPlanning);
            }
            if (kill.isAlive()) {
                kill.stop();
            } else {

                failed = true;
                this.setTimeoutFail(true);
                this.setTimePlanner((int) getTimeout());
            }
            //System.out.println("Time del planner: "+ this.getPlannerTime());

        } catch (IOException e) {
            System.out.println("Planner eccezione " + e.toString());
        } catch (Exception e) {
            System.out.println("Planner eccezione2 " + e.toString());
        }
    }

    public void executePlanning_PROVA ( ) {
        Runtime rt = Runtime.getRuntime();
        outputPlanning = new StringBuilder();
        try {

            Utility.deleteFile("temp.SOL");
            Runtime runtime = Runtime.getRuntime();
            String toExecute = this.planningExec + " " + domainFile + " " + problemFile + " " + option1 + " " + option2;
            System.out.println("Executing: " + toExecute);
// 
//           java.lang.ProcessBuilder pb = new java.lang.ProcessBuilder(toExecute);
//
//            // Create an environment (shell variables)
//
//            // You can change the working directory
//            //pb.directory(new java.io.File("/home/enrico/Scrivania/Dropbox/Trains/problemGeneration/"));
//            // Start new process
//            java.lang.Process p = pb.start();
            process = runtime.exec(toExecute);

            BufferedReader reader
                    = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((reader.readLine()) != null) {
            }
            System.out.println("Waiting for the Planning Process till the reaching of " + this.getTimeout());
            //process.wait(getTimeout());
            process.waitFor();

            System.out.println("Planning finished");
            if (true) {
                BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));

                String line = null;
                while ((line = input.readLine()) != null) {
                    outputPlanning = outputPlanning.append(line + "\n");
                    //System.out.println(outputPlanning);
                }
            } else {
                process.destroy();
                failed = true;
                this.setTimeoutFail(true);
                this.setTimePlanner((int) getTimeout());
            }
            //System.out.println("Time del planner: "+ this.getPlannerTime());

        } catch (IOException e) {
            System.out.println("Planner eccezione" + e.toString());
        } catch (InterruptedException e) {
            System.out.println("Planner eccezione" + e.toString());
        }
    }

    @Override
    public void changePlannersPath ( ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static class ProcessRunner extends Thread {

        ProcessBuilder b;
        Process p;
        boolean done = false;

        ProcessRunner (String[] args) {
            super("ProcessRunner " + args); // got lazy here :D
            b = new ProcessBuilder(args);
        }

        @Override
        public void run ( ) {
            try {
                p = b.start();

                // Do your buffered reader and readline stuff here
                // wait for the process to complete
                p.waitFor();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            } finally {
                // some cleanup code
                done = true;
            }
        }

        int exitValue ( ) throws IllegalStateException {
            if (p != null) {
                return p.exitValue();
            }
            throw new IllegalStateException("Process not started yet");
        }

        boolean isDone ( ) {
            return done;
        }

        void abort ( ) {
            if (!isDone()) {
                // do some cleanup first
                p.destroy();
            }
        }
    }

    static class Killer extends Thread {

        ProcessBuilder b;
        Process p;
        boolean done = false;
        long timeout;

        Killer (Process p, long timeout) {
            super("killer "); // got lazy here :D
            this.p = p;
            this.timeout = timeout;
        }

        @Override
        public void run ( ) {
            try {
                sleep(this.timeout);
                p.destroy();

            } catch (Exception e) {
                System.err.println(e.getMessage());
            } finally {
                // some cleanup code
                done = true;
            }
        }
    }

}
