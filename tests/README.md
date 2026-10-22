# Java tests

Run the JUnit suite from any directory with:

```bash
./tests/run.sh
```

Each test class runs in its own JVM. This keeps legacy tests that share static
planner state from affecting the result of later classes. Java sources are grouped by purpose under
`java/`:

- `java/unit/`: focused library, CLI option and model tests.
- `java/heuristics/`: H1, CPZeroCut, LM-Cut and heuristic regression cases.
- `java/integration/`: end-to-end planner tests.
- `java/benchmarks/`: standalone benchmark programs; these are compiled by the
  runner but are not included in the JUnit run.

PDDL fixtures are in `fixtures/pddl/` and are addressed relative to the
repository root. Run the script from another directory safely; it changes to
the repository root before compiling and invoking JUnit. Experiment data,
reports and scripts are organized under `benchmarks/`, `research/` and
`tools/`; they are not part of the planner core.


`lib/test-dependencies/` contains only JUnit and Hamcrest: they are runner
dependencies, not tests or fixtures. The old `test/` was an empty IDE output
path and has been removed; `tests/` is the sole canonical root for verification
material.
