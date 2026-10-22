# Java / Rust synchronization

Java development continues in this repository. Rust will use a separate
repository; until its location is supplied, the ignored `rust-port/` directory
is the transitional working copy. Set `JPDDLPLUS_RUST_DIR` to the external
checkout after migration. Local machine paths are documented in
`README_LOCAL.md`, not here.

## Component map and current coverage

Paths in the Rust column are relative to the Rust checkout root.

| Behavior | Java reference | Rust component | Current scope |
| --- | --- | --- | --- |
| Syntax and semantic model | `grammar/`, `src/com/hstairs/ppmajal/domain/`, parser code | `src/model.rs`, `src/syntax.rs`, `src/parser.rs` | Common PDDL/numeric/PDDL+ subset; full language parity unverified |
| State and numeric evaluation | `PDDLProblem/`, `conditions/`, `expressions/` | `src/state.rs`, `src/indexed.rs`, `src/compiled.rs` | Dense states and indexed grounded execution; focused differential checks |
| Grounding | `src/com/hstairs/ppmajal/propositionalFactory/`, `PDDLProblem/` | `src/ground.rs` | Typed enumeration and compiled references; broader Java preprocessing remains incomplete |
| H1 and related heuristics | `pddl/heuristics/advanced/H1.java` | `src/heuristic.rs` | HAdd/HMax subset, propositional HMRP extraction; complete H1 parity is pending |
| CPZeroCut and other advanced heuristics | `pddl/heuristics/advanced/` | Not implemented | Explicit unsupported-option errors; do not substitute blind search |
| Search | `search/WAStar.java`, other search engines | `src/search.rs` | WAStar/GBFS subset; other algorithms and several options remain pending |
| PDDL+ simulation | `PDDLProblem/PDDLProblem.java` | `src/simulation.rs`, `src/decimal.rs` | Discrete simulation subset; broader parity unverified |
| CLI and integrations | `src/enhsp2/`, `src/com/hstairs/enhsp/` | `src/main.rs` | ENHSP CLI subset; Java GUI/integrations are not ported |

Java paths above are under `src/com/hstairs/ppmajal/` unless written in full.
The Rust `PORTING.md` contains more detailed limitations. This table records
coverage, not a claim that all implementations have identical behavior.

## Pending items

| ID | Item | Completion evidence |
| --- | --- | --- |
| RUST-001 | Create/identify the separate Rust repository and transfer the staging copy | Independent build/test, configured checkout path, paired agent instructions and preserved benchmark history |
| RUST-002 | Complete H1 parity: numeric HAdd/HMax/HMRP, switches, extraction and applicable Java preprocessing | Differential initial/intermediate estimates and matching plan behavior on representative domains |
| RUST-003 | Expand search/CLI coverage and document tie behavior | Differential traces and explicit supported-option registry |
| RUST-004 | Expand regression/performance coverage beyond blind counters | Multiple numeric, propositional and PDDL+ cases with reproducible time, memory and correctness measurements |
| JAVA-001 | Wire supplied-plan validation into the Java CLI or revise its advertised `-ival`/`-inputplan` interface | A regression must check the supplied file, reject an invalid plan and avoid silently performing a new search; assess the Rust counterpart when implemented |

Baseline noted on 2026-10-22: Java HEAD
`4a51bb4e6ade3060a006bbeae42e14c7c08ee698`, with ongoing uncommitted Java work.
This is not a clean paired-release identifier. The latest counters audit is a
partial blind-WAStar exploration (`g < 16`), not a solved `instance_8` run.
On the same date the staged Rust tree passed its tests, Clippy and a release
build after being copied outside the Java workspace. Its CLI fixtures are
self-contained and its comparison tools accept an external Java JAR. RUST-001
therefore still needs the actual repository destination/transfer, not a new
packaging design.

## Recording a new pending change

Add a row with a stable ID, the Java behavior/source and commit when available,
the Rust component, the reason synchronization is pending, and a command or
fixture that demonstrates the expected result. Close it only after verifying
the counterpart, referencing its commit when one exists. If both checkouts are
available, implement supported counterpart changes during the same task.
