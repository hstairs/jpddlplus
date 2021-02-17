

(define 
    (domain testing)
    (:predicates (a) (b) (c) )
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
    (:action produces-c-deletes-b
       :parameters ()
       :precondition 
			        (a)            
       :effect (and
		            (not (b))
		            (c)
               )
    )
) 
