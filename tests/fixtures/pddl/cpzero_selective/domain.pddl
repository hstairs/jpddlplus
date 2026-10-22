(define (domain cpzero-selective)
  (:predicates
    (p)
    (q)
    (r)
  )

  (:functions
    (cost)
  )

  (:action make-q
    :parameters ()
    :precondition (and)
    :effect (and
      (q)
      (increase (cost) 1)
    )
  )

  (:action make-r
    :parameters ()
    :precondition (and)
    :effect (and
      (r)
      (increase (cost) 5)
    )
  )

  (:action make-p-cheaply
    :parameters ()
    :precondition (q)
    :effect (and
      (p)
      (increase (cost) 10)
    )
  )

  (:action make-p-expensively
    :parameters ()
    :precondition (r)
    :effect (and
      (p)
      (increase (cost) 20)
    )
  )
)
