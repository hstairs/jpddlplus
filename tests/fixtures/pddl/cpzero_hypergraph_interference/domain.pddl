(define (domain cpzero-hypergraph-interference)
  (:predicates
    (p)
    (q)
    (c)
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
      (increase (cost) 3)
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

  (:action main-achiever
    :parameters ()
    :precondition (p)
    :effect (and
      (c)
      (increase (cost) 1)
    )
  )

  (:action interfering-achiever
    :parameters ()
    :precondition (q)
    :effect (and
      (p)
      (c)
      (increase (cost) 20)
    )
  )

  (:action finish
    :parameters ()
    :precondition (and (c) (q))
    :effect (goal-reached)
  )
)
