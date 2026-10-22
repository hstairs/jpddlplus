(define (problem cpzero-selective-numeric-interference-problem)
  (:domain cpzero-selective-numeric-interference)

  (:init
    (= (x) 0)
  )

  (:goal
    (>= (x) 100)
  )

  (:metric minimize (cost))
)
