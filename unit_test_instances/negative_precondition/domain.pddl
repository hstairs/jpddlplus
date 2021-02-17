

(define 
    (domain testing)
    (:predicates (a) (b) (c)(goal))
    (:functions 
	(total-cost)
    )
    (:action fast-but-costly
       :parameters ()
       :precondition (and
			            (not(a))
                     )
       :effect 
                    (and 
                        (not(goal))
                        (increase (total-cost) 1)
                    )
	
    )
) 
