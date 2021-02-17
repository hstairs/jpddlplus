
(define 
    (problem instance1)
    (:domain testing)
    (:init
	(a)
	(= (counter) 0)
    )
    (:goal 
	    (and
           	(b)
           	(c)
           	(>= (counter) 1)
	    )
    )
)
