
(define 
    (problem instance1)
    (:domain testing)
	(:objects c1 -caccamo c2 -caccamo)
    (:init
        (a c1 c2)
        (goal)
    )
    (:goal 
	    (and
           	(not (goal))
           	(b c1 c2)
	    )
    )
)
