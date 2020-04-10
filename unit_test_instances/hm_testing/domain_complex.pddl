
(define (domain count)
    (:predicates (activation) (a))
    (:functions
        (x0)
        (x1)
        (x2)
        (x3)
        (total-cost)
    )
    ;; Increment x0 by one and decrease x3 by 0.5
    
    (:constraint speed_limit
        :parameters ()
        :condition (and (>= (x0) 0))
    )

    
    
    (:action increase-x0-decrease-x3
         :parameters ()
         :precondition ()
         :effect (and (increase (x0) 1)(decrease(x3) 0.5) (increase (total-cost) 1.5))
    )
    (:action increase-x2-decrease-x1
         :parameters ()
         ;:precondition()
         :effect (and (increase (x2) 1)(decrease (x1) 0.5) (increase (total-cost) 1.5))
    )
    (:action activate
         :parameters ()
         :precondition (a)
         :effect (and (activation) (increase (total-cost) 100))
    )
)
