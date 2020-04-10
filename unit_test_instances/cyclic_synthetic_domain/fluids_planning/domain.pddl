;;The domain features cyclic effects. Action 'a' \prec 'b' \prec 'c' \prec 'a'. The agent has two actions. The first opens valve b which cause liquid in b going from b to a, but leaving a unit quantity in b. The second action operates on c and a valves causing liquid transfer from c to b, and from a to c.  

(define 
    (domain fluids)
    (:predicates (true)) ;;dummy predicates for colin compatibility
    (:functions 
        (a) 
        (b)
        (c)
    )

    (:action open_b_valve
       :parameters ()
       :precondition (and(true))
       :effect (and(assign (a) (- (b) 1)) (assign (b) 1) )
    )

    (:action open_c_a_valves
       :parameters ()
       :precondition (and(true))
       :effect (and (assign (b) (c)) (assign (c) (a)) (assign (a) 0))
    )
) 
