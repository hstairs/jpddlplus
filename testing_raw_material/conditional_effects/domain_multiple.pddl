

(define 
    (domain testing)
    (:predicates (a) (b) (c) (g1) (g2) (pre2) (pre3))
    (:functions 
	    (total-cost)    
    )
    (:action condition
       :parameters ()
       :effect (and 
                   (when (a) (g1))
                   (when (b) (g2))
                   (increase (total-cost) 1)
               )
    )
    (:action achieve_pre_1
       :parameters ()
       :effect (and 
                   (a)
                    (increase (total-cost) 1)
               )
    )

    (:action achieve_pre_2
       :parameters ()
       :effect (and 
                   (b)
                   (increase (total-cost) 1)
               )
    )

) 
