

(define 
    (domain testing)
    (:predicates (a) (b) (c) (goal) )
    (:functions 
        (counter)
	    (total-cost)    
    )
    (:action produce-b
       :parameters ()
       :precondition (and
			            (a)
                     )
       :effect 
                    (and 
                        (increase (counter) 1)
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
