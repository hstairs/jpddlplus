;;The domain features cyclic effects. Action 'a' \prec 'b' \prec 'c' \prec 'a'

(define 
    (domain simple_increase_domain)
    (:requirements :typing :durative-actions :fluents :time :negative-preconditions :timed-initial-literals)
    (:functions 
        (a) 
        (b)
    )
    (:predicates
        (true)
    )

    (:action increase_a
       :parameters ()
       :precondition (and(not (true)))
       :effect (and (increase (a) 1))
    )

    (:process simple_process
       :parameters ()
       :precondition (and(not (true)))
       :effect (and (increase (b) (* #t 1)) )
    )
) 
