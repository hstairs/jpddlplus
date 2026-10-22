(define (domain h1dec-ssnp-state-reset)
  (:functions
    (value)
    (limit)
  )

  (:action increment
    :parameters ()
    :precondition (<= (+ (value) 1) (limit))
    :effect (increase (value) 1)
  )
)
