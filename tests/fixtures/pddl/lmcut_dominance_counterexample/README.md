# LM-cut dominance counterexample

This two-action numeric task shows that the implemented numeric LM-cut does
not dominate either `hrmax` or `cpzerocut`, independently of the numeric
activation floor.

Using `CrdMode.NONE`, the initial-state estimates are:

| heuristic | `hybrid=false` | `hybrid=true` |
|---|---:|---:|
| `lmcut` | `31.6` | `31.6` |
| `hrmax` | `40` | `40` |
| `cpzerocut` | `40` | `40` |

Therefore, with or without the activation floor, the same state witnesses

```text
h_lmcut < h_rmax
h_lmcut < h_cpzerocut
```

The separation comes from LM-cut's proportional residual-cost reduction over
the shared numeric support structure. Its initial justification-graph value is
`41.2`. The first cut charges `25.2`, but reduces the next graph value to `6.4`:
the graph value has fallen by `34.8`, more than the amount charged. LM-cut then
returns `25.2 + 6.4 = 31.6`. CPZeroCut instead retains the complete `hrmax`
lower bound of `40`.

`LmCutDominanceCounterexampleTest` checks both hybrid settings explicitly.
