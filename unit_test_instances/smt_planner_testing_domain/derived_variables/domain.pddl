
(define (domain count)
    (:predicates (activation))
    (:functions
        (d) 
    )
    (:free_functions (v) (a))
    (:constraint max_speed
        :parameters()
        :condition (or (< (v) 0) (< (v) 1000))
    )
    
    (:constraint constr_acc
        :parameters()
        :condition (or (< (a) 10))
    )
    ;; Increment the value in the given counter by one
    (:action movement
         :parameters ()
         :precondition  (> (a) 0)
         :effect (and  (increase (d) (v)))
    )

)
