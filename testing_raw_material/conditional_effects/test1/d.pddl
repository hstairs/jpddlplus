

(define 
    (domain cond_test1)
    (:predicates (a) (b) (c) (g1) (g2) (pre2) (pre3))
    (:functions 
	    (total-cost)    
    )
    (:action  get_goals
       :parameters ()
       :effect (and 
                   (when (a) (g1))
                   (when (b) (g2))
                   (increase (total-cost) 1)
               )
    )
    (:action get_a
       :parameters ()
       :effect (and 
                   (a)
                    (increase (total-cost) 1)
               )
    )

    (:action get_b
       :parameters ()
       :effect (and 
                   (b)
                   (increase (total-cost) 1)
               )
    )

) 
