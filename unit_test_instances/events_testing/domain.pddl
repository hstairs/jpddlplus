

(define 
    (domain testing)
    (:requirements :typing :time :fluents)
    (:predicates (activea) (activeb) (activep) (goal)(beforegoal))
    (:functions 
    	(x)
    )
    (:process p
       :parameters ()
       :precondition (and
			            (activep)
                     )
       :effect 
                    (and 
                        (increase (x) (* #t 1))
                    )
    )
    
    (:event a
       :parameters ()
       :precondition (and
			            (activea)
                     )
       :effect 
                    (and 
                        (not (activea))
                        (activep)
                    )
    )
    (:event b
       :parameters ()
       :precondition (and
			            (> (x) 0)
                        (activep)
                     )
       :effect 
                     (and 
                        (beforegoal)
                        (not (activep))
                     )
    )

    (:action start-action
       :parameters ()
       :precondition (and
                        (= (x) 0)
                     )
       :effect 
                    (and  
                        (activea)
                    )
    )

    (:action close-plan
       :parameters ()
       :precondition (and
                        (beforegoal)
                        (< (x) 1)
                     )
       :effect 
                    (and  
                        (goal)
                    )
    )
) 
