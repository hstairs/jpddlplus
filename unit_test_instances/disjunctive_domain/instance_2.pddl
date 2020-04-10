;; in this problem we expect to find just one landmark (the goal) but with a counter set to 10
(define 
    (problem instance1)
    (:domain testing)
    (:init
	(= (x0) 0)
	(= (x1) 0)
    )
    (:goal 
	    (or
           	(>= (x0) 5)
           	(>= (x1) 5)
	    )
    )
)
