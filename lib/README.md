# Java dependencies

- `runtime/` contains the libraries used by ENHSP and JPDDLPLUS at runtime.
- `test-dependencies/` contains JUnit 4 and Hamcrest, used only by `./tests/run.sh`.

The old flat dependency folder included duplicate versions and source,
documentation and test bundles. These were moved to
`archive/2026-10-22-cleanup/dependencies/`; the archive manifest records the
original names and SHA-256 checksums. Keep the JARs together with the planner
JARs: their paths are listed in each planner JAR's manifest.

`cplex.jar` enables compilation of the optional CPLEX-backed heuristic. CPLEX
itself remains an external IBM installation, including its native libraries
and license.
