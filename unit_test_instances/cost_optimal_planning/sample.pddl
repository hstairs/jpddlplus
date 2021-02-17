
(define 
    (problem instance1)
    (:domain testing)
    (:init
	(a)
	(= (x) 2)
    (= (total-cost) 0)
    (= (total-cost2) 0)
    )
    (:goal 
	    (and
           	(goal)
	    )
    )
    (:metric
        minimize (total-cost)
    )
)
