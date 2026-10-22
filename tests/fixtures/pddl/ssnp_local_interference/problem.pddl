(define (problem ssnp-local-interference-problem)
  (:domain ssnp-local-interference)

  (:init
    (= (x) 0)
  )

  (:goal
    (>= (x) 100)
  )

  (:metric minimize (cost))
)
