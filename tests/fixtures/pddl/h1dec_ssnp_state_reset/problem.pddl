(define (problem h1dec-ssnp-state-reset-problem)
  (:domain h1dec-ssnp-state-reset)

  (:init
    (= (value) 0)
    (= (limit) 2)
  )

  (:goal
    (>= (value) 1)
  )
)
