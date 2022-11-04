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
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author enrico
 */
public class MetricFFGrounder extends ExternalGrounder{
    public MetricFFGrounder(PDDLProblem problem, String domainFile, String problemFile) {
        super(problem,domainFile,problemFile);
    }
    
    public Collection<TransitionGround> doGrounding(){
        
        final HashMap<String,Collection<List<String>>> groundings = new HashMap();
        String command = "/home/enrico/planners/mffgrounding/ff -o "+domainFile+" -f "+problemFile+" -i 120";
        try {
            Process process = Runtime.getRuntime().exec(command);

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Action")){
                    String[] split = line.split(" ");
                    
                    String actionName = split[1];
                    Collection<List<String>> get = groundings.get(actionName);
                    if (get == null){
                        get = new ArrayList();
                        groundings.put(actionName,get);
                    }
                    ArrayList<String> list = new ArrayList();
                    for(int i=2;i<split.length;i++){
                        list.add(split[i]);
                    }
                    get.add(list);
                }
            }
            process.waitFor();

            reader.close();
            process.destroy();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return extractActions(groundings);
               
    }

    
}
