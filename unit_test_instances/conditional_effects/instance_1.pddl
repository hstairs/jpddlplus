
(define 
    (problem instance1)
    (:domain testing)
    (:init
	(a)
	(= (counter) 0)
    )
    (:goal 
	    (and
           	(c)
		    (pre1)
            (> (counter) 10)
	    )
    )
)
