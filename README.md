# JPDDLPLUS and ENHSP

**JPDDLPLUS** is a Java library for parsing, grounding and solving planning
problems expressed in PDDL, numeric PDDL and PDDL+. **ENHSP** (Expressive
Numeric Heuristic Search Planner) is the planner application built on the
library. This branch contains the ENHSP 25 backend.

## Build

Use JDK 17 and Bash. The Java dependencies are bundled in
`jar_dependencies/`. From the repository root, run:

```bash
./compile.sh
```

This builds `jpddlplus.jar`, which contains the ENHSP command-line entry
point. The build also uses the JARs in `jar_dependencies/`; keep that directory
beside the generated JAR. The build script replaces the checked-in
`jpddlplus.jar`, so review that binary change before committing build results.

## Run ENHSP

For example, solve a small numeric planning problem:

```bash
java -jar jpddlplus.jar \
  -o examples/pddl2_1/counters/domain.pddl \
  -f examples/pddl2_1/counters/instance_4.pddl \
  -planner sat-hmrp
```

The CLI accepts `-planner` presets including `sat-hadd`, `sat-hmrp`,
`sat-aibr` and `opt-hrmax`. Use `-h <heuristic>` and `-s <search>` to select
the components directly. For options and examples, see the
[ENHSP project website](https://sites.google.com/view/enhsp/).

## Library and repository layout

The reusable APIs cover PDDL models, state representation, grounding, numeric
expressions, heuristics and search. Core classes are under
`src/com/hstairs/ppmajal/`; the planner entry point is under `src/enhsp2/`.
Support for full ADL is partial, and some PDDL+ behavior depends on the
chosen discretisation settings.

| Path | Purpose |
| --- | --- |
| `src/com/hstairs/ppmajal/` | JPDDLPLUS models, heuristics and search |
| `src/enhsp2/` | ENHSP command-line application |
| `grammar/` | PDDL grammar and parser material |
| `jar_dependencies/` | Bundled Java dependencies |
| `test/`, `tests/` | Java test sources and planning examples |
| `examples/` | PDDL, numeric PDDL and PDDL+ inputs |

## Selected research

JPDDLPLUS evolved from PPMaJaL. The following papers describe numeric
planning techniques used by, or closely related to, this codebase:

- E. Scala, P. Haslum, and S. Thiébaux (2016). [*Heuristics for Numeric Planning via Subgoaling*](https://www.ijcai.org/abstract/16/457). IJCAI.
- E. Scala, P. Haslum, S. Thiébaux, and M. Ramírez (2016). [*Interval-Based Relaxation for General Numeric Planning*](https://doi.org/10.3233/978-1-61499-672-9-655). ECAI.
- E. Scala, P. Haslum, D. Magazzeni, and S. Thiébaux (2017). [*Landmarks for Numeric Planning Problems*](https://doi.org/10.24963/ijcai.2017/612). IJCAI.
- E. Scala, P. Haslum, S. Thiébaux, and M. Ramírez (2020). [*Subgoaling Techniques for Satisficing and Optimal Numeric Planning*](https://doi.org/10.1613/JAIR.1.11875). JAIR 68, 691–752.
- F. Percassi, E. Scala, and M. Vallati (2023). [*A Practical Approach to Discretised PDDL+ Problems by Translation to Numeric Planning*](https://doi.org/10.1613/JAIR.1.13904). JAIR 76, 115–162.
- L. Bonassi, A. E. Gerevini, and E. Scala (2024). [*Dealing with Numeric and Metric Time Constraints in PDDL3 via Compilation to Numeric Planning*](https://doi.org/10.1609/aaai.v38i18.29981). AAAI, 20036–20043.
- E. Scala and L. Bonassi (2025). [*On Using Lazy Greedy Best-First Search with Subgoaling Relaxation in Numeric Planning Problems*](https://doi.org/10.1609/icaps.v35i1.36125). ICAPS, 245–249.

See also [Enrico Scala's publication list](https://scholar.google.com/citations?user=lgfpklAAAAAJ&hl=en).

## Acknowledgements

JPDDLPLUS has benefited from contributions by Alban Grastien, Dongxu Li,
Da An, Alessandro Bernocchi, Luigi Bonassi, Andrea Micheli, Ryan Wang,
Miquel Ramírez, Marco Roveri, ZioCecio and Enrico Scala. These names are
recorded in this branch's Git history; the original contact list is in
[contributors.txt](contributors.txt).

License terms: [LICENCE](LICENCE). Contact: enricos83@gmail.com.
