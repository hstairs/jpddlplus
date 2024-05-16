# What is JPDDLPLUS?

This repository contains the JPDDLPLUS API, which is a planning manager library meant to build systems that speak the PDDL language. It supports many features that go way beyond classical planning, such as numeric representations, linear and non-linear constraints, autonomout processes, events, global constraints and other things. This is an evolution of the PPMAJAL Library, now deprecated.

This API has been used as basis for many projects, all related to Automated Planning (deterministic, non-deterministic), Replanning, Plan Execution, SMT Planning.

For more information on its applications have a look at some of the papers in [Google Scholar](https://scholar.google.com.au/citations?user=lgfpklAAAAAJ&hl=en)

The theoretical basis of some of the key features of the library can be found in the following papers:

E. Scala, P. Haslum, S. Thiebaux: **Heuristics for Numeric Planning via Subgoaling**, IJCAI 2016

E. Scala, M. Ramirez, P. Haslum, S. Thiebaux: **Numeric Planning with Disjunctive Global Constraints via SMT**, ICAPS 2016

E. Scala, P. Torasso, **Deordering and Numeric Macro Actions for Plan Repair**, IJCAI 2015

E. Scala, P. Haslum, S. Thiebaux, M. Ramirex, **Interval-Based Relaxation for General Numeric Planning**, ECAI 2016

M Ramirez, E Scala, P Haslum, S Thiebaux, **Numerical Integration and Dynamic Discretization in Heuristic Search Planning over Hybrid Domains**, arXiv 2016

Some of the planners built on top of JPDDLPLUS have be made public into the following bitbucket repositories:

*The SMT Planner* can be downloaded from [here](https://bitbucket.org/enricode/springroll-smt-hybrid-planner)

*The ENHSP Planner* can be downloaded from [here](https://gitlab.com/enricos83/ENHSP-Public/-/tree/enhsp-20?ref_type=heads)


## Dependencies

The library depends on a number of libs, some for the PDDL parsing, other for some standard algorithm on graphs, and some to interface the API with lp solvers. All such libraries are in the jar_dependencies folder. Have a look inside to get an understanding on the dependencies.

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
