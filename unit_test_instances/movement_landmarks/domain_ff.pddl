

(define 
    (domain testing)
    (:predicates (a) (c) (b) (d) (e) )
    (:functions 
        (x) (y) (z)
    )

    
    (:action move-right
       :parameters ()
       :precondition (not (and (> (x) 10) (< (x) 30) (> (y) 10) (< (y) 30) (> (z) 10) (< (z) 30)))
       :effect (and
		            (increase (x) 1)
               )
    )
    
    (:action move-left
       :parameters ()
       :precondition (not (and (> (x) 10) (< (x) 30) (> (y) 10) (< (y) 30) (> (z) 10) (< (z) 30)))
       :effect (and
		            (decrease (x) 1)
               )
    )
    
    (:action move-down
       :parameters ()
       :precondition (not (and (> (x) 10) (< (x) 30) (> (y) 10) (< (y) 30) (> (z) 10) (< (z) 30)))
       :effect (and
		            (decrease (y) 1)
               )
    )

    (:action move-up
       :parameters ()
       :precondition (not (and (> (x) 10) (< (x) 30) (> (y) 10) (< (y) 30) (> (z) 10) (< (z) 30)))
       :effect (and
		            (increase (y) 1)
               )
    )
    (:action increase_z
        :parameters ()
        :precondition (not (and (> (x) 10) (< (x) 30) (> (y) 10) (< (y) 30) (> (z) 10) (< (z) 30)))
        :effect (increase (z) 1)
     )
     
     (:action decrease_z
        :parameters ()
        :precondition (not (and (> (x) 10) (< (x) 30) (> (y) 10) (< (y) 30) (> (z) 10) (< (z) 30)))
        :effect (decrease (z) 1)
     )


) 
