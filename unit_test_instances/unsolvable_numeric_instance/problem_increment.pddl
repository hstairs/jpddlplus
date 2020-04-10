(define (problem equality)
  (:domain count)

  (:init
    (= (a) 0)
    (= (b) 0)
  )

  (:goal (and 
        (>= (a) 2) 
        (>= (b) 2)
    )
  )
 
)
