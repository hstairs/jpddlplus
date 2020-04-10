

(define 
    (domain testing)
    (:predicates (a) (b) (c) (goal) )
    (:action produce-b
       :parameters ()
       :precondition (and
			            (a)
                     )
       :effect 
                    (and 

                        (b)
                    )
	
    )
    (:action produces-c-deletes-a
       :parameters ()
       :precondition 
			        (a)            
       :effect (and
		            (not (b))
		            (c)
               )
    )
) 
