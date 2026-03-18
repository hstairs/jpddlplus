(define (domain counters)
    
    (:types counter)

    (:functions
        (delta) ;@input[0,58]_0
        (value ?c - counter)
        (max_int)
        (total-cost) ; acumulador de costes

    )

    ;; Increment the value in the given counter 
    (:action increment
         :parameters (?c - counter)
         :precondition (and (>= (- (max_int) (value ?c)) (delta)))
         :effect (and (increase (value ?c) (delta))
         ;(increase (total-cost) (delta))
         )

    )

    ;; Decrement the value in the given counter
    (:action decrement
         :parameters (?c - counter)
         :precondition (and (>= (value ?c) (delta)))
         :effect (and (decrease (value ?c) (delta))
         ;(increase (total-cost) (delta))
         )
    )

)
