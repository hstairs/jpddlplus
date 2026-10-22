(define (problem lmcut-duplicate-action-problem)
  (:domain lmcut-duplicate-action)

  (:init
    (= (cost) 0)
  )

  (:goal (g))

  (:metric minimize (cost))
)
