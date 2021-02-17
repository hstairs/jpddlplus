

(define 
    (domain testing)
    (:types obj -object)
    (:predicates 
                (a ?o -obj)
                (a2 ?o -obj)
                (b)
                (b2))
    (:action action_a
       :parameters ()
       :precondition (exists 
			            (?o -obj) (a ?o) )
       :effect 
                (and 
                    (b))) 
    (:action action_c
       :parameters (?o -obj)
       :effect 
                (and 
                    (a ?o)))
                    
    (:action action_b
       :parameters ()
       :precondition (forall 
			            (?o -obj) (a2 ?o) )
       :effect 
                (and 
                    (b2))) 
    (:action action_d
       :parameters (?o -obj)
       :effect 
                (and 
                    (a2 ?o)))) 
