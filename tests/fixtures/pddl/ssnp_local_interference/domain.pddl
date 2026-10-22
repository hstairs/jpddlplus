(define (domain ssnp-local-interference)
  (:predicates
    (q)
    (p)
  )

  (:functions
    (x)
    (cost)
  )

  (:action unrelated-achiever
    :parameters ()
    :precondition (and)
    :effect (and
      (increase (x) 1)
      (increase (cost) 100)
    )
  )

  (:action establish-q
    :parameters ()
    :precondition (and)
    :effect (and
      (q)
      (increase (cost) 5)
    )
  )

  (:action interfering-achiever
    :parameters ()
    :precondition (q)
    :effect (and
      (p)
      (increase (x) 1)
      (increase (cost) 1)
    )
  )

  (:action effect-witness
    :parameters ()
    :precondition (p)
    :effect (and
      (increase (x) 10)
      (increase (cost) 1)
    )
  )
)
