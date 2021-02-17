
(define (domain count)
    (:predicates 
        (goal)
        (dummy_fact)
    )
    (:functions
        (x0)
        (x1)
    )

    (:action achieve_goal
         :parameters ()
         :precondition (not(dummy_fact))
         :effect (goal)
    )
    (:action increase-x0
         :parameters ()
         ;:precondition()
         :effect (increase (x0) 1)
    )
    (:action increase-x1
         :parameters ()
         ;:precondition()
         :effect (increase (x1) 1)
    )

    (:action delete_dummy_fact
         :parameters ()
         ;:precondition()
         :effect (not (dummy_fact))
    )
)
