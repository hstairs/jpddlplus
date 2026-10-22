(define (problem lmcut-dominance-counterexample-problem)
  (:domain lmcut-dominance-counterexample)

  (:init
    (= (x) 0)
    (= (y) 0)
  )

  (:goal
    (and
      (>= (x) 10)
      (>= (y) 11)
    )
  )

  (:metric minimize (cost))
)
