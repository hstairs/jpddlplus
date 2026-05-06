# JPDDLPLUS

JPDDLPLUS is a Java library for modeling, parsing, grounding, and solving planning problems expressed in PDDL, with a particular focus on numeric planning and PDDL+. The codebase evolved from the older PPMaJaL library and today acts as the shared software layer behind several planners and research prototypes in automated planning.

This repository also ships with ENHSP (Expressive Numeric Heuristic Search Planner), a ready-to-run planner built on top of the library. ENHSP is an important application of JPDDLPLUS, but it is not the library itself.

## JPDDLPLUS vs ENHSP

- `JPDDLPLUS` is the reusable library. It provides parsers, domain/problem/state representations, grounding, heuristics, search engines, and integration utilities.
- `ENHSP` is a planner application built on top of JPDDLPLUS. In this repository it is exposed as a CLI jar and a GUI jar.
- If you want to embed planning capabilities in another Java system, the main APIs live under `com.hstairs.ppmajal.*` and selected integration utilities under `com.hstairs.enhsp.*`.
- If you want to solve PDDL or PDDL+ instances from the shell, use the bundled ENHSP jar.

Other systems and prototypes have also been built on top of this codebase. Examples include:

- planning with infinite-domain parameters as in Aso-Mollar, Aineto, Scala, Onaindia, *Handling Infinite Domain Parameters in Planning Through Best-First Search with Delayed Partial Expansions* (IJCAI 2025)
- SMT-based planning as in Scala, Ramirez, Haslum, Thiebaux, *Numeric Planning with Disjunctive Global Constraints via SMT* (ICAPS 2016)
- conformant planning as in Grastien, Scala, *Intelligent Belief State Sampling for Conformant Planning* (IJCAI 2017)


## What the library supports

- PDDL 2.1 style domains and problems
- numeric state variables and numeric constraints
- linear and non-linear numeric expressions
- PDDL+ style processes and events
- global constraints through the `:constraint` syntax
- grounding and search preparation for expressive planning models
- state-space search with numeric, landmark, subgoaling, novelty, and interval-based heuristics
- integration helpers for planner output parsing, execution control, and approximate-syntax translation

Full ADL support is not the goal of the project and remains partial.

## Repository map

- `src/com/hstairs/ppmajal/*`: core library code
- `src/enhsp2/*`: ENHSP entry points and planner wiring
- `src/com/hstairs/enhsp/*`: integration utilities used around ENHSP
- `examples/`: sample domains and problems, including classical, numeric, PDDL+, and iterative optimization cases
- `test/`: regression tests and small programmatic examples

## Research background and selected references

A complete publication list is available on [Enrico Scala's Google Scholar profile](https://scholar.google.com/citations?user=lgfpklAAAAAJ&hl=en). The references below are a selected subset of the papers most closely related to the functionality implemented in this repository, including recent additions from the Scholar profile.

### Recent papers

- E. Scala, L. Bonassi. [*On Using Lazy Greedy Best-First Search with Subgoaling Relaxation in Numeric Planning Problems*](https://doi.org/10.1609/icaps.v35i1.36125). ICAPS 2025.
- E. Scala, A. Saetti, I. Serina, A. E. Gerevini. *Search-Guidance Mechanisms for Numeric Planning Through Subgoaling Relaxation*. ICAPS 2020.
- E. Scala, P. Haslum, S. Thiebaux, M. Ramirez. *Subgoaling Techniques for Satisficing and Optimal Numeric Planning*. JAIR 68, 2020.

### Foundational papers

- D. Li, E. Scala, P. Haslum, S. Bogomolov. *Effect-Abstraction Based Relaxation for Linear Numeric Planning*. IJCAI 2018.
- E. Scala, P. Haslum, D. Magazzeni, S. Thiebaux. *Landmarks for Numeric Planning Problems*. IJCAI 2017.
- E. Scala, P. Haslum, S. Thiebaux. *Heuristics for Numeric Planning via Subgoaling*. IJCAI 2016.
- E. Scala, M. Ramirez, P. Haslum, S. Thiebaux. *Numeric Planning with Disjunctive Global Constraints via SMT*. ICAPS 2016.
- E. Scala, P. Haslum, S. Thiebaux, M. Ramirez. *Interval-Based Relaxation for General Numeric Planning*. ECAI 2016.
- M. Ramirez, E. Scala, P. Haslum, S. Thiebaux. *Numerical Integration and Dynamic Discretization in Heuristic Search Planning over Hybrid Domains*. arXiv, 2016.

## Building the repository

### Requirements

- JDK 16 or newer
- a standard shell environment
- no extra dependency download is required: bundled jars are in `jar_dependencies/`

### Recommended build path

The maintained build entry point in this repository is:

```bash
./compile.sh
```

This command builds:

- `enhsp25.jar`: CLI planner
- `enhsp25-gui.jar`: GUI application

The repository still contains historical NetBeans/Ant metadata and older jar artifacts, but `compile.sh` is the supported path for a fresh build.

## Using ENHSP from the command line

### Get the help text

```bash
java -jar enhsp25.jar -help
```

### Solve a numeric planning instance

```bash
java -jar enhsp25.jar \
  -o examples/pddl2_1/counters/domain.pddl \
  -f examples/pddl2_1/counters/instance_4.pddl \
  -planner sat-hmrp
```

### Solve a PDDL+ instance

```bash
java -jar enhsp25.jar \
  -o examples/pddl+/car_non_linear/domain.pddl \
  -f examples/pddl+/car_non_linear/instances/instance_1_30.0_0.1_10.0.pddl \
  -planner sat-aibr
```

### Common options

- `-planner <name>`: use a preconfigured planner setup such as `sat-aibr`, `sat-hadd`, `sat-hmrp`, or `opt-hrmax`
- `-h <name>` and `-s <name>`: manually choose heuristic and search engine instead of using `-planner`
- `-d`, `-dp`, `-de`, `-dh`, `-dv`: control discretisation deltas for planning, execution, validation, and heuristics
- `-sp <file>`: save the computed plan to a file
- `-ival -inputplan <file>`: validate an input plan internally
- `-print_actions`: print the grounded transitions
- `-aibr_debug`: enable detailed logging for the AIBR heuristic

### Examples to start from

- `examples/pddl2_1/counters`: small numeric domains
- `examples/pddl2_1/plant-watering` and `examples/pddl2_1/sailing`: larger PDDL 2.1 examples
- `examples/pddl+/car_non_linear`: non-linear PDDL+ example
- `examples/iterative_minimization`, `examples/iterative_optimization`, `examples/iterative_pddlplus`: iterative optimization and experimental workflows

## Using JPDDLPLUS as a library

The core APIs live mostly under `com.hstairs.ppmajal.*`. The quickest way to experiment is to build `enhsp25.jar` and use it as a classpath dependency from your own Java code.

## Caveats

- Full ADL support is not complete.
- For PDDL+ style planning, plan validity depends on the chosen discretisation settings.
- General formulas in preconditions, constraints, and goals are best supported by AIBR and subgoaling-based heuristics.
- Some CLI configurations are experimental and primarily intended for research use.

## Related resources

- [ENHSP homepage](https://sites.google.com/view/enhsp/)
- [Historical SMT-based planner built on top of this library](https://bitbucket.org/enricode/springroll-smt-hybrid-planner)
- License: see `LICENCE`
