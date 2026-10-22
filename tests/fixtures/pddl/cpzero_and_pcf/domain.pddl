(define (domain cpzero-and-pcf)
  (:predicates
    (p)
    (q)
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
      (increase (cost) 1)
    )
  )

  (:action make-q
    :parameters ()
    :precondition (and)
    :effect (and
      (q)
      (increase (cost) 1)
    )
  )

  (:action finish
    :parameters ()
    :precondition (and (p) (q))
    :effect (goal-reached)
  )
)
