;;The domain features cyclic effects. Action 'a' \prec 'b' \prec 'c' \prec 'a'

(define 
    (domain cyclicdomain)
    (:functions 
        (a) 
        (b)
        (c)
    )

    (:durative-action change_a
       :parameters ()
       :duration (= ?duration 1)
       ;:precondition ()
       :effect (at end(and(assign (a) (- (b) 1)) (assign (b) 1) ))
    )

    (:durative-action change_b_c
       :parameters ()
       :duration (= ?duration 1)
       ;:precondition ()
       :effect (at end (and (assign (b) (c)) (assign (c) (a)) (assign (a) 0)))
    )
) 
