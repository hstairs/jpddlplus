
(define (domain count)
    (:predicates (activation))
    (:functions
        (d)
        (v)
        (a)
        (z)
    )
    ;; Increment the value in the given counter by one
    (:action movement
         :parameters ()
         ;:precondition()
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
