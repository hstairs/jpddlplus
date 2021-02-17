

(define 
    (domain testing)
    (:predicates (a) (b) (c) (goal) )
    (:functions 
        (alpha)
        (v)   

    )

    (:action decrease_v
       :parameters ()
       :effect 
                    (and 
                        (increase (v) 1)
                    )
	
    )

    
    (:action decrease_inclination
       :parameters ()
       :effect 
                    (and 
                        (decrease (alpha) (abs v))
                    )
	
    )



) 
