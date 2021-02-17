

(define 
    (domain testing)
    (:predicates (a) (b) (c) (goal) )
    (:functions 
        (alpha)
        (v)   
	(max_speed) 
        (max_alpha) 
        (plane_length)
        (alpha_incr)
    )

    (:constraint possible_angles
	:parameters ()
	:condition (and (> (alpha) (* -1 (max_alpha)  )) (< (alpha) (max_alpha)) )

    )

    (:constraint possible_speed
	:parameters ()
	:condition (and (> (v) (* -1 (max_speed) )) (< (v) (max_speed) ) )

    )

    (:constraint possible_position
	:parameters ()
	:condition (and (> (x) (* -1 (plane_length))) (< (x) (plane_length)) )

    )
    

    (:process ball-dynamic-going-right
       :parameters ()
       :precondition(and
		    	(>= v 0)
		    )
       :effect 
                    (and 
                        (decrease (v) (* #t (- (* 9.8 (sin alpha) )  (* 0.01 (^ v 2)) )))
                        (increase (x) (* #t (v)) )	
                    )
	
    )
    
    (:process ball-dynamic-going-left
       :parameters ()
       :precondition(and
		    	(< v 0)
		    )
       :effect 
                    (and 
                        (decrease (v) (* #t (+ (* 9.8 (sin alpha) )  (* 0.01 (^ v 2)) )))
                        (increase (x) (* #t (v)) )	
                    )
	
    )
    
    
    (:action decrease_inclination
       :parameters ()
       :effect 
                    (and 
                        (decrease (alpha) (alpha_incr))
                    )
	
    )

    (:action increase_inclination
       :parameters ()
       :effect 
                    (and 
                        (increase (alpha) (alpha_incr))
                    )
	
    )

) 
