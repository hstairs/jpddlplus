

(define 
    (domain testing)
    (:predicates (a) (c) (b) (d) (e) )
    (:functions 
        (x) (y) (z)
    )
    
    (:constraint obstacle
        :parameters ()
        :condition (not (and (> (x) 10) (< (x) 30) (> (y) 10) (< (y) 30) (> (z) 10) (< (z) 30)))
    )
    
    (:action move-right
       :parameters ()
       :precondition ()
       :effect (and
		            (increase (x) 1)
               )
    )
    
    
    
    
    (:action move-left
       :parameters ()
       :precondition (a)
       :effect (and
		            (decrease (x) 1)
               )
    )
    
    (:action move-down
       :parameters ()
       :precondition (a)
       :effect (and
		            (decrease (y) 1)
               )
    )
    

    (:action move-up
       :parameters ()
       :precondition (a)
       :effect (and
		            (increase (y) 1)
               )
    )
    (:action increase_z
        :parameters ()
        :precondition(a)
        :effect (increase (z) 1)
     )
     
     (:action decrease_z
        :parameters ()
        :precondition(a)
        :effect (decrease (z) 1)
     )


) 
