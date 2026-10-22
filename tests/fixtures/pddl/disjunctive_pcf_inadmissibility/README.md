# Scalar-PCF disjunction counterexample

The zero-cost `finish` action requires:

```text
(p or r) and (q or r)
```

Producing `p` or `q` costs 2, while the single action producing `r` costs 3.
Therefore `(make-r, finish)` is a valid plan of cost 3.

Hmax assigns cost 2 to both disjunctions. A scalar PCF can first select the
`p` branch and charge 2, then select the `q` branch after the first cost
reduction and charge another 2. LM-cut consequently returns 4, exceeding the
cost-3 plan.

CPZeroCut instead represents a PCF as a set. Its OR rule retains every
disjunct, so the first zeroing step also protects the shared `r` branch and
the heuristic returns 2.
