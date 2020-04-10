(define 
    (domain testing)
    ;(:requirements :typing :durative-actions :fluents :time)
    (:predicates (activea) (activeb) (activep) (goal)(beforegoal))
    (:functions 
    	(x)
    )
    (:event set-x-0
       :parameters ()
       :precondition (and
			            (> (x) 0)
                     )
       :effect 
                     (and 
                        (assign (x) 0)
                     )
    )

    (:action increase_x
       :parameters ()
       :precondition (and
                        (>= (x) 0)
                     )
       :effect 
                    (and  
                        (increase (x) 1)
                    )
    )

) 
