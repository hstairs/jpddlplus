package enhsp2;

import se_util.InputFile;
import se_util.ReadSimulatedEffects;

public class mainTest {


    public static void main(String[] args) throws Exception {
    	//String[] data = {"-o","/home/valerik/examples/se/max_domains/domain_max_comb_4.pddl","-f","/home/valerik/examples/se/small_instances/max/instance_comb_4.pddl","-h","blind","-s","WAStar","-dap"};
        String[] data = {"-o","/home/valerik/examples/se/max_domains/domain_max_comb_4.pddl","-f","/home/valerik/examples/se/small_instances/max/instance_comb_4.pddl","-s","WAStar","-dap"};
        //String[] data = {"-o","/home/valerik/examples/rovers/rovers_domain.pddl","-f","/home/valerik/examples/rovers/pfile5"};
        ENHSP p = new ENHSP(false);
        p.parseInput(data);
        ReadSimulatedEffects.setDomain(InputFile.readDomain(data[1]));
        p.configurePlanner();
        p.parsingDomainAndProblem(data);
        p.planning();
    }
}
