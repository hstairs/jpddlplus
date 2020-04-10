
(define (domain count)
    (:predicates (activation))
    (:functions
        (d)
        (v)
        (a)
        (z)
    )
    (:constraint max_speed
        :parameters()
        :condition (or (< (a) 2) (< (v) 4))
    )
    ;; Increment the value in the given counter by one
    (:action movement
         :parameters ()
         :precondition(< (v) 100)
         :effect (and  (increase (v) (a)))
    )
    
    
    (:action accelerate
         :parameters ()
         ;:precondition()
         :effect (and (increase (a) 1))
    )
    (:action decelerate
         :parameters ()
         ;:precondition()
         :effect (and (decrease (a) 1) )
    )

)
