# CPZeroCut NUM_FRACTIONAL counterexample

The only valid optimal plan is:

```
(bootstrap)
(cheap-step)
```

Its metric cost is 3. `cheap-step` is not interference-free: `bootstrap`
both enables it through `ready` and contributes the first unit of `x`.

For `cheap-step`, hmax's winning branch is the activation floor:
`h(ready) + cost(cheap-step) = 2 + 1`. If the cost-partitioning closure loses
that branch and instead charges `2 * cost(cheap-step)`, it fails to follow
`ready` back to `bootstrap`; the next partition then incorrectly retains 0.5
and the total estimate becomes the inadmissible 3.5.
