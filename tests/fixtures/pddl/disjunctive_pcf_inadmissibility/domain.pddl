(define (domain disjunctive-pcf-inadmissibility)
  (:predicates
    (p)
    (q)
    (r)
    (goal-reached)
  )

  (:functions
    (cost)
  )

  (:action make-p
    :parameters ()
    :precondition (and)
    :effect (and
      (p)
      (increase (cost) 2)
    )
  )

  (:action make-q
    :parameters ()
    :precondition (and)
    :effect (and
      (q)
      (increase (cost) 2)
    )
  )

  (:action make-r
    :parameters ()
    :precondition (and)
    :effect (and
      (r)
      (increase (cost) 3)
    )
  )

  (:action finish
    :parameters ()
    :precondition (and
      (or (p) (r))
      (or (q) (r))
    )
    :effect (goal-reached)
  )
)
