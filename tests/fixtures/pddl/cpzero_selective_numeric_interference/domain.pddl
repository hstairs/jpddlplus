(define (domain cpzero-selective-numeric-interference)
  (:predicates
    (support-ready)
    (action-ready)
  )

  (:functions
    (x)
    (cost)
  )

  (:action prepare-support
    :parameters ()
    :precondition (and)
    :effect (and
      (support-ready)
      (increase (cost) 1)
    )
  )

  (:action interfering-achiever
    :parameters ()
    :precondition (support-ready)
    :effect (and
      (action-ready)
      (increase (x) 1)
      (increase (cost) 6)
    )
  )

  (:action main-achiever
    :parameters ()
    :precondition (action-ready)
    :effect (and
      (increase (x) 10)
      (increase (cost) 1)
    )
  )
)
