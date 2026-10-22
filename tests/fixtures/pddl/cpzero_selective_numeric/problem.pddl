(define (problem cpzero-selective-numeric-problem)
  (:domain cpzero-selective-numeric)

  (:init
    (= (x) 0)
  )

  (:goal
    (and
      (p)
      (q)
    )
  )

  (:metric minimize (cost))
)
