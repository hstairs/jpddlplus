# JPDDLPLUS and ENHSP

**JPDDLPLUS** is a Java library for parsing, grounding and solving planning
problems expressed in PDDL, numeric PDDL and PDDL+.
**ENHSP** (Expressive Numeric Heuristic Search Planner) is the planner
application built on this library, with command-line and graphical interfaces.

## Build

Requirements: JDK 16 or newer and Bash. Runtime dependencies are bundled in
`lib/runtime/`; test-only dependencies are in `lib/test-dependencies/`.

From the repository root:

```bash
./compile.sh
```

This produces `enhsp25.jar` (CLI) and `enhsp25-gui.jar` (GUI). Compiled classes
go under `build/compile/`; the JARs reference runtime libraries in
`lib/runtime/` through their manifests.

Run the Java regression suite with `./tests/run.sh`. Test classes go under
`build/tests/` and cover the sources grouped under `tests/java/`.

## Run

```bash
java -jar enhsp25.jar \
  -o examples/pddl2_1/counters/domain.pddl \
  -f examples/pddl2_1/counters/instance_4.pddl \
  -planner sat-hmrp
```

```bash
java -jar enhsp25.jar -help
java -jar enhsp25-gui.jar
```

The planner JARs use the adjacent libraries in `lib/runtime/`. Docker builds
the current checkout with `docker build -t enhsp25 .`; see
[the Docker notes](docker-builder/README.md) for container commands.

See [the ENHSP guide](README_ENHSP.md) for heuristic/search options, PDDL+
discretisation, plan output and validation. Ready-to-load GUI examples are
listed in [examples/gui/README.md](examples/gui/README.md).

## Library and supported models

The library provides domain/problem/state representations, grounding,
numeric expression evaluation, heuristic search and planner integration APIs
under `com.hstairs.ppmajal.*` and `com.hstairs.enhsp.*`. After building, use
the planner JAR and the adjacent `lib/runtime/` dependencies on a Java
application's classpath.

Supported models include classical and numeric planning, linear/nonlinear
numeric expressions, discretised PDDL+ processes/events and global constraints.
Full ADL support remains partial. Heuristic support varies with the language
constructs used; PDDL+ execution and validity depend on discretisation settings.

## Repository layout

| Path | Purpose |
| --- | --- |
| `src/com/hstairs/ppmajal/` | JPDDLPLUS library: model, parsing, grounding, heuristics and search |
| `src/enhsp2/` | ENHSP CLI and GUI |
| `src/com/hstairs/enhsp/` | Integration utilities |
| `grammar/` | PDDL grammar and parser-generation material |
| `lib/runtime/` | Runtime Java dependencies |
| `lib/test-dependencies/` | JUnit and Hamcrest for the test runner |
| `tests/java/` | Java unit, heuristic, integration and microbenchmark sources |
| `tests/fixtures/pddl/` | PDDL inputs and historical counterexamples used by tests |
| `examples/` | Classical, numeric and PDDL+ examples |
| `Dockerfile`, `compose.yaml` | Local multi-stage Docker build and Compose setup |
| `docs/` | Implementation notes and development documentation |

Contributor instructions are in [AGENTS.md](AGENTS.md). The experimental Rust
port is being prepared for a separate repository; its supported scope and
pending synchronization work are tracked in [docs/rust-sync.md](docs/rust-sync.md).

## Research and project information

JPDDLPLUS evolved from PPMaJaL. Background on its numeric-planning techniques
includes *Heuristics for Numeric Planning via Subgoaling* (IJCAI 2016),
*Interval-Based Relaxation for General Numeric Planning* (ECAI 2016),
*Landmarks for Numeric Planning Problems* (IJCAI 2017), and
*Subgoaling Techniques for Satisficing and Optimal Numeric Planning* (JAIR 2020).

- [ENHSP homepage](https://sites.google.com/view/enhsp/)
- [Enrico Scala's publication list](https://scholar.google.com/citations?user=lgfpklAAAAAJ&hl=en)
- License terms: [LICENCE](LICENCE)
- Contact: enricos83@gmail.com
