(define (domain ssnp-state-interference)
  (:predicates
    (already-supported)
    (needed-support)
    (gate)
  )

  (:functions
    (x)
    (cost)
  )

  (:action satisfied-branch-achiever
    :parameters ()
    :precondition (and)
    :effect (and
      (already-supported)
      (increase (x) 1)
      (increase (cost) 1)
    )
  )

  (:action establish-gate
    :parameters ()
    :precondition (and)
    :effect (and
      (gate)
      (increase (cost) 5)
    )
  )

  (:action unsatisfied-branch-achiever
    :parameters ()
    :precondition (gate)
    :effect (and
      (needed-support)
      (increase (x) 1)
      (increase (cost) 1)
    )
  )

  (:action quantitative-witness
    :parameters ()
    :precondition (and (already-supported) (needed-support))
    :effect (and
      (increase (x) 10)
      (increase (cost) 1)
    )
  )
)
