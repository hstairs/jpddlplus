(define (problem equality)
  (:domain count)

  (:init
    (= (d) 0)
    (= (v) 0)
    (= (a) 0)
    (= (z) 1)
  )

  (:goal (and 
        (< (v) 1) 
        (> (d) 10)
    )
  )
 
)
