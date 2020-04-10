;; in this problem we expect to find just one landmark (the goal) but with a counter set to 10
(define 
    (problem instance1)
    (:domain testing)
    (:init
	(= (y) 0)
	(= (x) 0)
	(= (z) 0)
	(a)
    )
    (:goal 
	    (and
           	(>= (x) 100) (<= (x) 105)
           	(>= (y) 100) (<= (y) 105)
           	(>= (z) 100) (<= (z) 105)
	    )
    )
)
