---
name: initial-cut-diagnostic
description: Run ENHSP CPZeroCut initial-state base versus numfract diagnostics and compare their improvement over hmax.
---

Use `tests/tools/initial_cut_local.py` on a dataset containing domain directories with `domain.pddl` and `instances/*.pddl`. Select a Java JAR that supports `-diagnostic_cut`, and record its checksum and provenance. For comparisons with the ICAPS 2027 paper use the frozen diagnostic JAR `slurm_experiments/runs/artifacts/enhsp25-enhsp-java-cut-diagnostic-20261116.jar`; verify its availability. Run `python3 tests/tools/initial_cut_local.py --root DATASET --jar JAR --out OUTPUT`; tune workers and timeout explicitly when needed. The script stops each process immediately after its first diagnostic line; inspect missing records and preserve logs and exact commands. Parse `hmax`, `estimate`, and `improvement` from each line. Improvement is an absolute difference, not a percentage. Compare paired instances only and flag differing hmax values or non-finite estimates. This is initial-state heuristic evaluation, not search coverage or full runtime. See `README_ENHSP.md` for timing semantics.
