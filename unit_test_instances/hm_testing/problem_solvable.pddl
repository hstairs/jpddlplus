;;This is a unit test, synthetic problem for the h^m like heuristic The cost of this problem should be 33+100=133
(define (problem complex_numeric_interactions)
  (:domain count)

  (:init
    (= (x0) 0)
    (= (x1) 0)
    (= (x2) 0)
    (= (x3) 0)
    (= (total-cost) 0)
    (a)
  )

  (:goal (and 
        (>= (+ (x0) (x3)) 6) ;;this would require applying action incrx0-decx3 12 times
        (>= (+ (x2) (x1)) 5) ;;this would require applying action incrx2-decx1 10 times
        ;;(activation) ;;With this, the heuristic should be perfect
    )
  )
 
)
