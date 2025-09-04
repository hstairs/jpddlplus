
# What is this library about?

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

In order to compile, just execute the command "compile" from shell. This will produce a jar file, jpddlplus.jar which can be both used as a library or as a standlone program (see below).
The other way is to use an IDE such as intelliJIdea.


## Already in the box: a clone of the ENHSP planner

Besides many other things, the API has some self-contained planners such as the ENHSP planner (which stands for Expressive Numeric Heuristic Search Planner). I heavily suggest you to install the planner starting from its webpage if you want to use it.
