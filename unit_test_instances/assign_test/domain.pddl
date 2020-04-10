

(define 
    (domain testing)
    (:predicates (a) (c) (b) (d) (e) (f) (h) )
    (:functions 
        (counter)  (counter1)
    )


    ;;this tests the numeric part of the problem plus dominated constraints
    (:action set_counter
       :parameters ()
       ;:precondition ()            
       :effect (and (assign (counter) 1) (decrease (counter1) 1))
    )
) 
