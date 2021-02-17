(define 
    (domain cyclicdomain)
    (:functions 
        (a) 
        (b)
    )

    (:action change_a
       :parameters ()
       :precondition ()
       :effect (and(assign (a) (b)))
    )

    (:action change_b
       :parameters ()
       :precondition ()
       :effect (and (assign (b) (a)))
    )
)
