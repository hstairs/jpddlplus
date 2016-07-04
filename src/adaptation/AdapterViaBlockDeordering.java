package adaptation;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import adaptation.PlanAdapter;
import computation.DomainEnhancer;
import domain.PddlDomain;
import extraUtils.Utils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import plan.SimplePlan;
import planners.metricFFWrapper;
import planners.planningTool;
import problem.PDDLObjects;
import problem.PddlProblem;

/**
 *
 * @author enrico
 */
public class AdapterViaBlockDeordering extends PlanAdapter{

    public AdapterViaBlockDeordering(planningTool planner) {
        super(planner);
    }

    public SimplePlan adaptViaBlockDeordering(String planFile, PddlProblem problem, PddlDomain domain, SimplePlan sp) throws Exception {
        
             
        
        System.out.println("Deordering..");
        //HashMap achieveGoal = new HashMap();
        long beforeMacrogeneration = System.currentTimeMillis();
        //List c = null;
        List blocks = block_construction(problem.getPddlFilRef(),domain.getPddlFilRef(),planFile,sp,0);
        //TreeSet macros = this.pruneSmallMacros(blocks,10);
        System.out.println(blocks);
        List mac = sp.generateMacrosFromBlocks(blocks);
        //System.out.println(po);
        //System.out.println(po);
        //System.out.println(plan.toStringWithIndex());
        DomainEnhancer dEnh = new DomainEnhancer();
        Map m = dEnh.addMacroActions(domain,mac,sp);
        
        //System.out.println("time for adding in the file:" + (System.currentTimeMillis() - beforeAddingFile));

        /**/macroActionsConstructionTime = System.currentTimeMillis() - beforeMacrogeneration;
        //System.out.println("MacroConstruction Time:" + getMacroActionsConstructionTime());

        //planning using the enhanced domain
        //PddlDomain temp = new PddlDomain(dEnh.getDomainEnhancedFileName());
        
        SimplePlan new_plan = new SimplePlan(domain, problem, false);
        
        
        //PddlProblem temp = new PddlProblem();
 
        String solutionString = null;
        if (planner instanceof metricFFWrapper){
            solutionString = planner.plan(dEnh.getDomainEnhancedFileName(), problem.getPddlFilRef());
        }else{
            PDDLObjects temp  = (PDDLObjects)problem.getObjects().clone();
            problem.removeObjects(dEnh.getConstantsFound());
            problem.saveProblem("temp");
            solutionString = planner.plan(dEnh.getDomainEnhancedFileName(), "temp");
            problem.setObjects(temp);
            Utils.deleteFile("temp");
        }
        
        if (solutionString == null) {
            System.out.println("No plan");
            return null;
        }
        new_plan.parseSolutionWithMacro(solutionString, m);
        this.setEmployedMacros(new_plan.getEmployedMacro());
        new File(solutionString).delete();

        setPlannerTime(planner.getPlannerTime());
        this.setAdaptationTime(plannerTime + macroActionsConstructionTime);
        solution = new_plan;
        //System.out.println(new_plan);

        return new_plan;
        
        
    }

    
    class StreamGobbler extends Thread
    {
        InputStream is;
        String type;

        StreamGobbler(InputStream is, String type)
        {
            this.is = is;
            this.type = type;
        }

        @Override
        public void run()
        {
            try
            {
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String line=null;
                while ( (line = br.readLine()) != null)
                    System.out.println(type + ">" + line);    
                } catch (IOException ioe)
                  {
                    ioe.printStackTrace();  
                  }
        }
    }
    
    private List block_construction(String pfile, String dfile, String planFile,SimplePlan p, int min_size) throws FileNotFoundException, IOException {
        List ret = new ArrayList();
        try {
            Runtime runtime = Runtime.getRuntime();
            String block_deorder_exec = "block_deorder";
            System.out.println("Executing: " + block_deorder_exec +" "+ dfile +" "+ pfile+" "+planFile);
            Process process = runtime.exec(block_deorder_exec +" "+ dfile +" "+ pfile+" "+planFile);
            /* Cleaning output */
            StreamGobbler errorGobbler = new 
                StreamGobbler(process.getErrorStream(), "ERROR");            
            
            // any output?
            StreamGobbler outputGobbler = new 
                StreamGobbler(process.getInputStream(), "OUTPUT");
                
            // kick them off
            errorGobbler.start();
            outputGobbler.start();
                                    
            // any error???
            int exitVal = process.waitFor();
            System.out.println("ExitValue: " + exitVal);   
            
        } catch (IOException e) {
            System.out.println("Block Deorder Error" + e.toString());
        } catch (InterruptedException e) {
            System.out.println("Block Deorder Error" + e.toString());
        }
        System.out.println("Block Derdering finished");
        BufferedReader br = new BufferedReader(new FileReader("bdp.txt"));
        System.out.println("Reading bdp file");
        try {
            String line = br.readLine();
            Pattern r = Pattern.compile("b_(\\d+): \\{ (\\d+.*)+ \\}");
            while (line != null) {
                //System.out.println(line);
                Matcher m = r.matcher(line);
                if (m.find()){
                    List sub = new ArrayList();
                    //ret.add(Integer.parseInt(m.group(0)));
                    //System.out.println(m.group(2));
                    String[] sub_actions = m.group(2).split(" ");
                    int size = sub_actions.length;
                    for (int i=0;i<size;i++){
                        sub.add(Integer.parseInt(sub_actions[i]));
                    }
                    //System.out.println("Block: "+m.group(1)+" Number of elements:"+size);
                    if (size>min_size)
                        ret.add(sub);
                }
                line = br.readLine();
            }
            System.out.println("bdp file read");
        } finally {
            br.close();
        }
        
        System.out.println(ret);
        System.out.println("Number of Blocks:"+ret.size());
        return ret;
    
    }
    
}