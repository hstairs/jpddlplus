

(define 
    (domain testing)
    (:predicates (a) (b) (c) (goal) (pre1) (pre2) (pre3))
    (:functions 
        (counter)
	    (total-cost)    
    )
    (:action conditional_action1
       :parameters ()
       :precondition (or (pre1) (pre2) (pre3))
       :effect (and 
                   (when (not (and (a) (b))) (c))
                   (when (not (and (a) (b))) (increase (counter)1))
               )
    )

    (:action achieve_pre_1
       :parameters ()
       :effect (and 
                   (pre1)
               )
    )

    (:action achieve_pre_2
       :parameters ()
       :effect (and 
                   (pre2)
               )
    )

) 
