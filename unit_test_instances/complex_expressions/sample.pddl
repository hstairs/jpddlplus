;; in this problem we expect to find just one landmark (the goal) but with a counter set to 10
(define 
    (problem instance1)
    (:domain testing)
    (:init
    (= (counter) 0)
    (= (counter2) -1)
    (= (counter1) 0)
    (= (counter3) 0)

    )
    (:goal 
	    (and
           	(<= (+ (counter) (counter1)) -1)

	    )
    )
)
