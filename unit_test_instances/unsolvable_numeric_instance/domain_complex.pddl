
(define (domain count)
    (:predicates (activation))
    (:functions
        (d)
        (b)
        (a)
        (z)
    )
    ;; Increment the value in the given counter by one
    
    
    (:action increase-a-decrease-b
         :parameters ()
         ;:precondition()
         :effect (and (increase (a) 1)(decrease(b) 1))
    )
    (:action increase-b-decrease-a
         :parameters ()
         ;:precondition()
         :effect (and (increase (b) 1)(decrease (a) 0.1))
    )

)
