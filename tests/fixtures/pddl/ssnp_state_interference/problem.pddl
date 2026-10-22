(define (problem ssnp-state-interference-problem)
  (:domain ssnp-state-interference)

  (:init
    (already-supported)
    (= (x) 0)
  )

  (:goal
    (>= (x) 100)
  )

  (:metric minimize (cost))
)
