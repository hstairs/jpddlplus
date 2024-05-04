# What is this library about?

This repository contains the JPDDLplus API, which is a planning manager library meant to build systems that speak the PDDL language. It supports many features that go way beyond classical planning, such as numeric representations, linear and non-linear constraints, autonomout processes, events, global constraints and other things.

This API has been used as basis for many projects, all related to Automated Planning (deterministic, non-deterministic), Replanning, Plan Execution, SMT Planning.

For more information on its applications have a look at some of the papers in [Google Scholar](https://scholar.google.com.au/citations?user=lgfpklAAAAAJ&hl=en)

The theoretical basis of the more recent advances of the library can be found in the following papers:

E. Scala, P. Haslum, S. Thiebaux: **Heuristics for Numeric Planning via Subgoaling**, IJCAI 2016

E. Scala, M. Ramirez, P. Haslum, S. Thiebaux: **Numeric Planning with Disjunctive Global Constraints via SMT**, ICAPS 2016

E. Scala, P. Torasso, **Deordering and Numeric Macro Actions for Plan Repair**, IJCAI 2015

E. Scala, P. Haslum, S. Thiebaux, M. Ramirex, **Interval-Based Relaxation for General Numeric Planning**, ECAI 2016

M Ramirez, E Scala, P Haslum, S Thiebaux, **Numerical Integration and Dynamic Discretization in Heuristic Search Planning over Hybrid Domains**, arXiv 2016

Some of the planners built on top of JPDDLPlus have be made public into the following bitbucket repositories:

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

## Compilation

You can use the library *off-the-shelf* but you can also decide to compile that. I am going to add a building file that should do the job for you. For the moment you should do the compilation manually (shouldn't be much of a problem though)

## Already in the box: a clone of the ENHSP planner

Besides many other things, the API has some self-contained planners such as the ENHSP planner (which stands for Expressive Numeric Heuristic Search Planner). I heavily suggest you to install the planner starting from [here](https://bitbucket.org/enricode/enhsp.-numeric-heuristic-search-pddl-planner) if you want to use the planner.
