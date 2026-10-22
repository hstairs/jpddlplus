(define (domain lmcut-dominance-counterexample)
  (:functions
    (x)
    (y)
    (cost)
  )

  (:action slow-both
    :parameters ()
    :precondition (and)
    :effect (and
      (increase (x) 1)
      (increase (y) 1)
      (increase (cost) 4)
    )
  )

  (:action fast-y-after-four
    :parameters ()
    :precondition (>= (y) 4)
    :effect (and
      (increase (x) 1)
      (increase (y) 4)
      (increase (cost) 6)
    )
  )
)
