
(define (domain count)
    (:predicates (activation))
    (:functions
        (a)
        (v)
        (x)
    )
    ;; Increment the value in the given counter by one
    (:action movement
         :parameters ()
         ;:precondition()
         :effect (and  
                      (increase (v) (a))
                      (increase (x) 1)))
                      
    (:constraint bound
        :parameters()
        :condition (and (< (x) 10)))

)
