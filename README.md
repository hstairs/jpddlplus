
# What is this library about?

This repository contains the JPDDLPLUS API, which is a planning manager library meant to build systems that speak the PDDL language, with a focus on the extention that deals with numeric state varibales, numeric constraints, events and processes, commonly referred as PDDL+. It supports many features that go way beyond classical planning, such as numeric representations, linear and non-linear constraints, autonomout processes, events, global constraints and other things. This is an evolution of the PPMAJAL Library, now deprecated.

This API has been used as basis for many projects, all related to Automated Planning (deterministic, non-deterministic), Replanning, Plan Execution, SMT Planning. One of the most used planning system is ENHSP, which stands for Expressive numeric Heuristic Search Planner. The new versions of ENHSP are now part of the library. Information specific to ENHSP below. Another planner that also uses this library is the SMT Planner, which can be downloaded from [here](https://bitbucket.org/enricode/springroll-smt-hybrid-planner)

For more information on its applications have a look at some of the papers in [Google Scholar](https://scholar.google.com.au/citations?user=lgfpklAAAAAJ&hl=en)

The theoretical and methodological basis included in the library can be found in the following papers:

- E. Scala, L. Bonassi: **On Using Lazy Greedy Best-First Search with Subgoaling Relaxation in Numeric Planning Problems**, ICAPS 2025
- E. Scala, M. Vallati: **Effective grounding for hybrid planning problems represented in PDDL+**, Knowledge Engineering Review 36:e9, 2021
- E. Scala, A. Saetti, I. Serina, A. E. Gerevini: **Search-Guidance Mechanisms for Numeric Planning Through Subgoaling Relaxation**, ICAPS 2020
- D. Li, E. Scala, P. Haslum, S. Bogomolov: **Effect-Abstraction Based Relaxation for Linear Numeric Planning**, IJCAI 2018
- E. Scala, P. Haslum, D. Magazzeni, S. Thiebaux: **Landmarks for Numeric Planning Problems**, IJCAI 2017
- E. Scala, P. Haslum, S. Thiebaux: **Heuristics for Numeric Planning via Subgoaling**, IJCAI 2016
- E. Scala, M. Ramirez, P. Haslum, S. Thiebaux: **Numeric Planning with Disjunctive Global Constraints via SMT**, ICAPS 2016
- E. Scala, P. Haslum, S. Thiebaux, M. Ramirez: **Interval-Based Relaxation for General Numeric Planning**, ECAI 2016
- M. Ramirez, E. Scala, P. Haslum, S. Thiebaux: **Numerical Integration and Dynamic Discretization in Heuristic Search Planning over Hybrid Domains**, arXiv 2016
- E. Scala, P. Torasso: **Deordering and Numeric Macro Actions for Plan Repair**, IJCAI 2015



Some of the planners built on top of JPDDLPLUS have be made public into the following bitbucket repositories:


*The ENHSP Planner* can be downloaded from [here](https://gitlab.com/enricos83/ENHSP-Public/-/tree/enhsp-20?ref_type=heads)


## Dependencies


The library depends on a number of libs, some for the PDDL parsing, other for some standard algorithm on graphs, and some to interface the API with lp solvers. All such libraries are in the jar_dependencies folder. Have a look inside to get an understanding on the dependencies.

## Compilation

In order to compile, just execute the command "compile" from shell. This will produce a jar file, jpddlplus.jar which can be both used as a library or as a standlone program, which corresponds to ENHSP (see below).
The other way is to use an IDE such as intelliJIdea.

## ENHSP

### Overview

ENHSP (Expressive Numeric Heuristic Search Planner) is one of the planners built on top of JPDDLPLUS. It supports:

- classical planning
- numeric planning with linear and non-linear expressions
- planning with discretised autonomous processes and events
- global constraints expressed through the PDDL `:constraint` syntax

The planner does not support the whole ADL.

More information is available on the [ENHSP homepage](https://sites.google.com/view/enhsp/).

### Features

Recent ENHSP versions include support for:

- discrete events
- universal and existential quantification in formulas
- negative preconditions
- heuristics based on landmarks and subgoaling relaxation
- search techniques for planning with autonomous processes

### Build and Run

To compile from the repository root, run `compile`.

The compilation also produces ENHSP-GUI, a simple graphical user interface developed around ENHSP.

ENHSP can then be executed with:

```bash
./enhsp -o <domain_file> -f <problem_file> -planner <configuration>
```

Available planner configurations are described on the [ENHSP usage page](https://sites.google.com/view/enhsp/home/how-to-use-it).

### Notes

- ENHSP works on PDDL 2.1, PDDL+, events, and global constraints.
- For planning with autonomous processes, execution relies on discretisation, with delta set to 1 second by default.
- Additional examples and benchmark domains are available in the repository.
- Some advanced configurations are experimental and should be used with care.

### Limitations

- General formulas in action preconditions, constraints, and goals are supported only by AIBR and subgoaling-based heuristics.
