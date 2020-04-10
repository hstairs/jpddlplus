(define (problem equality)
  (:domain count)

  (:init
    (= (x0) 0)
    (= (x1) 0)
    (= (x2) 0)
    (= (x3) 0)
  )

  (:goal (and 
        (>= (+ (x0) (x1)) 0) 
        (>= (+ (x2) (x3)) 0) 
    )
  )
 
)
