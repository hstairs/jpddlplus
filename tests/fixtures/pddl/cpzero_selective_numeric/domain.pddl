(define (domain cpzero-selective-numeric)
  (:predicates
    (p)
    (q)
  )

  (:functions
    (x)
    (cost)
  )

  (:action make-q
    :parameters ()
    :precondition (and)
    :effect (and
      (q)
      (increase (cost) 2)
    )
  )

  (:action increase-x
    :parameters ()
    :precondition (q)
    :effect (and
      (increase (x) 1)
      (increase (cost) 1)
    )
  )

  (:action make-p-directly
    :parameters ()
    :precondition (and)
    :effect (and
      (p)
      (increase (cost) 10)
    )
  )

  (:action make-p-numerically
    :parameters ()
    :precondition (>= (x) 7)
    :effect (and
      (p)
      (increase (cost) 5)
    )
  )
)
