(define 
    (domain cyclicdomain)
    (:functions 
        (a) 
        (b)
        (c)
    )

    (:action change_a
       :parameters ()
;       :precondition ()
       :effect (and(assign (a) (b)))
    )

    (:action change_b_c
       :parameters ()
;       :precondition ()
       :effect (and (assign (b) (c)) (assign (c) (a)) )
    )
)
