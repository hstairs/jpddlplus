/*
 * Copyright (C) 2020 enrico.
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
package com.hstairs.ppmajal.propositionalFactory;

import com.hstairs.ppmajal.problem.PDDLProblem;
import com.hstairs.ppmajal.transition.TransitionGround;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author enrico
 */
public class FDGrounder extends ExternalGrounder{
    
    public FDGrounder(PDDLProblem problem, String domainFile, String problemFile) {
        super(problem,domainFile,problemFile);
    }
    
    public Collection<TransitionGround> doGrounding(){
        
        final HashMap<String,Collection<List<String>>> groundings = new HashMap();
        final String sasFileName = "/tmp/temp.sas";
        String command = "fd --translate --sas-file "+sasFileName+" "+domainFile+" "+problemFile;
        abstractNumeric(domainFile,problemFile);
        try {
            Process process = Runtime.getRuntime().exec(command);

            process.waitFor();

            process.destroy();
            BufferedReader reader = new BufferedReader(
                    new FileReader(sasFileName));
            String line;
            boolean read = false;
            while ((line = reader.readLine()) != null) {
                if (line.contains("begin_operator")){
                    read = true;
                }else{
                    if (read){
                        String[] split = line.split(" ");
                        String actionName = split[0];
                        Collection<List<String>> get = groundings.get(actionName);
                        if (get == null){
                            get = new ArrayList();
                            groundings.put(actionName,get);
                        }
                        ArrayList<String> list = new ArrayList();
                        for(int i=1;i<split.length;i++){
                            list.add(split[i]);
                        }
                        get.add(list);
                        read = false;
                    }
                }
            }
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return extractActions(groundings);
//        System.out.println(groundings.keySet());
        
        
               
    }

    private void abstractNumeric(String domainFile, String problemFile) {
        
    }

    

    
}
