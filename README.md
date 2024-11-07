This branch extends ENHSP with novelty heuristics and multi-queue search described in the SOCS-24 publication [here](https://arxiv.org/abs/2404.05235)

**Note**: there's currently a bug in plan extraction of multi-queue methods using jumping actions found by @[matteocarde](https://github.com/matteocarde). We have removed jumping actions of the associated configurations for now as a temporary fix. The new scores with this change are 

| mq3h | mq3n | mq3h3n |
| ---- | ---- | ------ |
| 242  | 249  | 272    |

in comparison to the published version 
| mq3h | mq3n | mq3h3n |
| ---- | ---- | ------ |
| 261  | 244  | 274    |

## Setup

I'm not sure how to build from command line, so I used intellij to build an artifact (`.jar` file). 
1. mark src as 'source' in intellij
2. manually download org.junit for the extra tests
3. Add jar files in `jar_dependencies` to module dependencies, see [here](https://www.jetbrains.com/help/idea/working-with-module-dependencies.html)
4. make sure dependencies are detected correctly, next follow the steps to configure and build the `.jar` artifact in this [stackoverflow](https://stackoverflow.com/a/4901370/13531424) or [official docs](https://www.jetbrains.com/help/idea/compiling-applications.html#run_packaged_jar)
5. the default intellij shortcut for building the artifact is `alt + B`

To test if your build passed successfully with the above method, run the `sanity_check.sh` script. The jar output by intellij defaults to `out/artifacts/jpddlplus_jar/jpddlplus.jar`

If you cannot get the build to work, try using the attached `enhsp.jar` in the root. The binary is compiled with OpenJDK 17. If you are using Ubuntu, you can install the required Java Runtime Environment with
```
sudo apt install openjdk-17-jre
``` 
If you are using a different system, any JRE version `>=17` is likely to work.

## Usage
The input is a PDDL domain file `<domain>`, a PDDL problem file `<problem>`, and an ENHSP configuration `<config>`. The command line instruction is
```
java -jar enhsp.jar -o <domain> -f <problem> -planner <config>
```
where the top new configurations ordered by total coverage in the paper are
- `sat-mq3h3n`
- `sat-mq3h`
- `sat-mq3n`
- `sat-hiqb2add`
- `sat-hiqb2mrphj`
- `sat-hmd`

Note that performance of a configuration depends on the domain, and lower ranked configurations could perform better for your use case.

-----

# What is PPMaJal?

This repository contains the PPMaJal API, which is a planning manager library meant to build systems that speak the PDDL language. It supports many features that go way beyond classical planning, such as numeric representations, linear and non-linear constraints, autonomout processes, events, global constraints and other things.

This API has been used as basis for many projects, all related to Automated Planning (deterministic, non-deterministic), Replanning, Plan Execution, SMT Planning.

For more information on its applications have a look at some of the papers in [Google Scholar](https://scholar.google.com.au/citations?user=lgfpklAAAAAJ&hl=en)

The theoretical basis of the more recent advances of the library can be found in the following papers:

E. Scala, P. Haslum, S. Thiebaux: **Heuristics for Numeric Planning via Subgoaling**, IJCAI 2016

E. Scala, M. Ramirez, P. Haslum, S. Thiebaux: **Numeric Planning with Disjunctive Global Constraints via SMT**, ICAPS 2016

E. Scala, P. Torasso, **Deordering and Numeric Macro Actions for Plan Repair**, IJCAI 2015

E. Scala, P. Haslum, S. Thiebaux, M. Ramirex, **Interval-Based Relaxation for General Numeric Planning**, ECAI 2016

M Ramirez, E Scala, P Haslum, S Thiebaux, **Numerical Integration and Dynamic Discretization in Heuristic Search Planning over Hybrid Domains**, arXiv 2016

Some of the planners built on top of PPMaJaL have be made public into the following bitbucket repositories:

*The SMT Planner* can be downloaded from [here](https://bitbucket.org/enricode/springroll-smt-hybrid-planner)

*The ENHSP Planner* can be downloaded from [here](https://bitbucket.org/enricode/enhsp)


## Dependencies

The library depends on a number of libs, some for the PDDL parsing, other for some standard algorithm on graphs, and some to interface the API with lp solvers:

In particular:

- [Antlr 3.4](http://www.antlr3.org) is used for parsing pddl problems. [Here](http://www.antlr3.org/download/antlr-3.4-complete.jar) the link to the actual library file that needs to be linked
- [Jgraph](http://jgrapht.org). This is for general algorithms on graphs
- [Ojalgo](http://ojalgo.org). The version used is the v40
- [Json Simple](https://github.com/fangyidong/json-simple). This is used to store information of the search space explored.

They are all open source projects, so is this library. For your convenience, the necessary jar files are all under the lib folder.

We tested also CPLEX for some of the optimisation required, but for the instances with dealt with, we did not observe substantial improvements. To use CPLEX optimization you need to link the oplall.jar file, which comes with the library. However, CPLEX is not free, so you need to buy or get an academic licence first of being allowed to download it.

## Compilation

You can use the library *off-the-shelf* but you can also decide to compile that. I am going to add a building file that should do the job for you. For the moment you should do the compilation manually (shouldn't be much of a problem though)

## Already in the box: a clone of the ENHSP planner

Besides many other things, the API has some self-contained planners such as the ENHSP planner (which stands for Expressive Numeric Heuristic Search Planner). I heavily suggest you to install the planner starting from [here](https://bitbucket.org/enricode/enhsp.-numeric-heuristic-search-pddl-planner) if you want to use the planner.

By the way you can use a version of that planner also from here. It can be invoked with:

java -jar dist/PPMaJal2.jar -o <domain_file> -f <problem_file> -h <configuration> -s <search-strategy> -gw <weight for the g-values> -hw <weight for the h-values> -break_ties <larger_g, smaller_g, arbitrary>

In particular the -h accepts the following parameters

- 1, to get h_add
- 3, to get h_max
- 112, to get Additive Interval Based Relaxation (as for ECAI 2016 paper)
- exp_gc, experimental version of something which is similar to h^m relaxation

There are also other settings, and new things in the frontend that can be accessed by running the planner without any parameter.
