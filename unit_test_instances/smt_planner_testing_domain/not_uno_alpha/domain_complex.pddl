
(define (domain doubling)
    (:predicates (activation))
    (:functions
        (v)
    )
    ;; Increment the value in the given counter by one
    (:action doubling
         :parameters ()
         ;:precondition()
         :effect (and  (assign (v) (* (v) 2)))
    )
    
)
