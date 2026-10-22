# Piccolo controesempio per `cpzerocut -selzero numfract`

Una barca parte da `(x,y)=(0,0)` e deve salvare due persone. Il dominio contiene solo quattro movimenti e l'azione `save_person`; conserva le quattro condizioni numeriche di salvataggio di Sailing.

Dal root del progetto:

```sh
java -jar enhsp25.jar -o tests/benchmarks/sailing-numfract-counterexample/domain.pddl -f tests/benchmarks/sailing-numfract-counterexample/problem.pddl -h cpzerocut -selzero numfract -crd none -s wastar -timeout 1 | rg -m1 'h\(I\)'
java -jar enhsp25.jar -o tests/benchmarks/sailing-numfract-counterexample/domain.pddl -f tests/benchmarks/sailing-numfract-counterexample/problem.pddl -h lmcut -crd none -s wastar -timeout 1 | rg -m1 'h\(I\)'
```

Risultato al nodo iniziale:

| Euristica | `h(I)` |
| --- | ---: |
| CPZeroCut numfract | 20.333332 |
| LM-Cut | 20.666666 |

Nel primo taglio CPZeroCut estrae `10.333333` per `(saved p3)` e azzera `go_est` e `save_person p3`. Nel secondo estrae `10` per `(saved p0)` e azzera `go_south_east`, **`go_west`** e `save_person p0`.

Il punto critico: per `p0`, `x+y <= -36`. Dallo stato iniziale `go_west` riduce `x+y` di 3, quindi servono 12 ripetizioni. Dopo il costo unitario di `save_person p0`, il secondo taglio ha solo 9 unità disponibili per i movimenti. Nonostante `12 > 9`, `zeroSupportClosureUpToThreshold` azzera il costo unitario di `go_west`: il calcolo di `retainedCost` usa `availableDistance` ma non `edgeDistance`/`repetitions`. Lo stesso `go_west` può contribuire a `y-x >= 28` per `p3`; azzerarlo fa perdere il successivo contributo al lower bound.
