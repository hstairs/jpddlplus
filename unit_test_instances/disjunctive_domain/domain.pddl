
(define (domain count)
    (:predicates (goal))
    (:functions
        (x0)
        (x1)
    )

    (:action achieve_goal
         :parameters ()
         :precondition (or (> x0 5) (> x1 5))
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
)
