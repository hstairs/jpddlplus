(define (problem p-shared-supporter)
  (:domain ssnp-aware-twist)

  (:init
    (use-shared)
    (= (shared) 0)
    (= (left-token) 0)
    (= (right-token) 0)
    (= (goal-x) 0)
    (= (goal-y) 0)
  )

  (:goal
    (and
      (>= (goal-x) 1)
      (>= (goal-y) 1)
    )
  )
)
