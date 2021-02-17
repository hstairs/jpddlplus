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
;this instance is solved repeating action a 10 times
(define 
    (problem instance1)
    (:domain simple_increase_domain)
    (:init
        (= (a) 0)
        (= (b) 1)
    )
    (:goal 
        (>= (a) 10)
    )
)
