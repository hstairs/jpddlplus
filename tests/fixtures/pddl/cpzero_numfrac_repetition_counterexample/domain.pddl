(define (domain cpzero-numfrac-repetition-counterexample)
  (:predicates (ready))
  (:functions (x) (cost))

  ;; This action is mandatory to make cheap-step applicable, but it also
  ;; contributes once to the numeric goal.
  (:action bootstrap
    :parameters ()
    :precondition (and)
    :effect (and (ready) (increase (x) 1) (increase (cost) 2)))

  (:action cheap-step
    :parameters ()
    :precondition (ready)
    :effect (and (increase (x) 1) (increase (cost) 1))))
