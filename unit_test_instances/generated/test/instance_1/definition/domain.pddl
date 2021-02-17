;;The domain features cyclic effects. Action 'a' \prec 'b' \prec 'c' \prec 'a'

(define 
    (domain cyclicdomain)
    (:functions 
        (a) -number
        (b) -number
        (c) -number
    )

    (:action change_a
       :parameters ()
       :precondition ()
       :effect (and(assign (a) (- (b) 1.0)) (assign (b) 1.0) )
    )

    (:action change_b_c
       :parameters ()
       :precondition ()
       :effect (and (assign (b) (c)) (assign (c) (a)) (assign (a) 0.0))
    )
) 
