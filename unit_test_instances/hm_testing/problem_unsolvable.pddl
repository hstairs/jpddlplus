(define (problem equality)
  (:domain count)

  (:init
    (= (x0) 0)
    (= (x1) 0)
    (= (x2) 0)
    (= (x3) 0)
  )

  (:goal (and 
        (>= (+ (x0) (* (x3) 2)) 1) ;;this would require applying action incrx0-decx3 an infinite amount of time. Unsolvable problem
        (>= (+ (x2) (x1)) 1) ;;this would require applying action incrx2-decx1 10 times
    )
  )
 
)
