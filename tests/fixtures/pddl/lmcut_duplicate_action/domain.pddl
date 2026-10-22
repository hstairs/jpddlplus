(define (domain lmcut-duplicate-action)
  (:requirements :strips :numeric-fluents)

  (:predicates
    (p)
    (q)
    (g)
  )

  (:functions
    (cost)
  )

  (:action make-p-and-q
    :precondition (and)
    :effect (and
      (p)
      (q)
      (increase (cost) 1)
    )
  )

  (:action p-to-g
    :precondition (p)
    :effect (g)
  )

  (:action q-to-g
    :precondition (q)
    :effect (g)
  )
)
