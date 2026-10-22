# Working on JPDDLPLUS / ENHSP

## Responsibilities and repositories

- This repository is the active Java implementation: JPDDLPLUS is the library,
  ENHSP is its planner application. Java defines the intended public behavior.
- Rust is a separate implementation destined for its own repository. Preserve
  behavior while using representations and algorithms appropriate for Rust;
  do not require a line-by-line translation.
- Read `README_LOCAL.md`, when present, for local checkout locations and tools.
  `JPDDLPLUS_RUST_DIR` can identify the external Rust checkout. During the
  migration only, an existing ignored `rust-port/` is the working Rust copy.
  Do not invent a remote URL or move/init/publish a repository without an
  explicit destination. Never discard this staging copy before verifying its
  replacement.
- `slurm_experiments/` and `subgoaling_revisit_paper/`, when present, are
  independent repositories. Do not reorganize or edit their contents as part
  of work on this repository.

## Java / Rust change workflow

For every Java change affecting parsing, grounding, state transitions,
numeric semantics, heuristics, search or CLI behavior:

1. Identify the affected Java and Rust components using `docs/rust-sync.md`.
2. Add or update focused regression cases describing the intended behavior.
3. If the functionality exists in the available Rust checkout, implement and
   verify the corresponding change there as part of the same task.
4. If Rust is unavailable or the functionality is not ported, continue the
   Java work and record a concrete pending item in `docs/rust-sync.md`, with
   affected behavior, paths and a reproducer. Never imply synchronization
   without evidence.
5. Report Java/Rust validation separately. When commits are created, reference
   the companion repository commit or its recorded pending item. Separate
   repositories do not share an atomic commit.

Documentation-only and Java-only integrations need an impact assessment, not
an artificial Rust implementation. Existing user changes must be preserved.
Do not modify unrelated Java sources while performing repository maintenance.

## Correctness and performance

- Differential checks should compare applicability, successor states, numeric
  values, heuristic estimates, plan cost and plan validity as appropriate.
  Use compatible numeric tolerances and specify tie-breaking explicitly.
- A discovered Java defect should be investigated and fixed deliberately;
  do not silently reproduce it or change Rust semantics without a regression.
- Keep Rust feature coverage explicit. An unsupported heuristic or option
  must fail clearly rather than fall back to blind estimates or another mode.
- Benchmark release Rust against a documented Java build with identical
  inputs and options. Record node/duplicate counts as well as elapsed time;
  distinguish startup, preprocessing, heuristic and search costs when possible.
- Use `fn-counters/instance_8` as a reference case for blind WAStar, alongside
  other representative tasks. A cost-bounded or timed-out run measures partial
  exploration and must not be reported as solving the original problem.
- Retain input snapshots, exact commands and performance results. Include
  memory measurements when investigating state/frontier representation.
  Do not generalize a speedup from one small synthetic benchmark.

## Build, tests and workspace hygiene

- `compile.sh` is the Java build entry point; it produces `enhsp25.jar` and
  `enhsp25-gui.jar`. It cleans only `build/compile/classes`; benchmarks use
  their separate `build/classes/` directory.
- Runtime dependencies are in `lib/runtime/`; JUnit and Hamcrest are in
  `lib/test-dependencies/`. Archived Ant/Maven files are historical, not build entry points.
- Java regression tests live under `tests/java/{unit,heuristics,integration,benchmarks}/`;
  run the suite with `./tests/run.sh`. PDDL fixtures, experiment campaigns,
  scientific reports and test-only tools live under `tests/` as well. Keep these
  scripts and experiment drivers out of `src/` and the planner build.
- The root `Dockerfile` builds the current checkout. `docker-builder/build.sh`
  is a small wrapper around it; do not restore the former remote-clone behavior.
- Run checks relevant to a change; avoid unrelated experiment campaigns.
  Rust checks are `cargo test`, `cargo clippy --all-targets -- -D warnings`
  and `cargo build --release` in its checkout.
- Before archiving an item, check build/script/documentation references.
  Keep sources, current JARs and IDE output paths in place unless the task
  explicitly changes them. Store verification material under `tests/`.
- Archive obsolete material under a dated `archive/` directory, preserving
  original paths, reasons and checksums in a manifest. Prefer reversible moves
  to deletion; do not put archived binaries or private IDE settings into Git.
- Public documentation belongs in `README.md`, `README_ENHSP.md` and `docs/`.
  Machine-specific paths and checkout instructions belong in the ignored
  `README_LOCAL.md`. Update that map when directories change.
