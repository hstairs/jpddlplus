;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;; Simple domain for iterative metric optimization tests
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(define (domain iterative-optimization-demo)
    (:requirements :strips :numeric-fluents)

    (:functions
        (x)
        (reward)
    )

    ;; Progresses quickly but consumes more reward.
    (:action costly_step
         :parameters ()
         :precondition ()
         :effect (and
             (increase (x) 1)
             (decrease (reward) 1)
         )
    )

    ;; Progresses more slowly but preserves more reward.
    (:action cheap_step
         :parameters ()
         :precondition ()
         :effect (and
             (increase (x) 0.9)
             (decrease (reward) 0.2)
         )
    )
)
