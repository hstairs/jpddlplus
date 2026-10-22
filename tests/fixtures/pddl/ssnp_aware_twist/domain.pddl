(define (domain ssnp-aware-twist)
  (:requirements :strips :typing :numeric-fluents)
  (:predicates
    (use-shared)
    (use-independent)
  )

  (:functions
    (shared)
    (left-token)
    (right-token)
    (goal-x)
    (goal-y)
  )

  ;; Shared numeric supporter for both goal achievers.
  (:action load-shared
    :precondition (use-shared)
    :effect (increase (shared) 1)
  )

  (:action make-x-from-shared
    :precondition (and (use-shared) (>= (shared) 1))
    :effect (increase (goal-x) 1)
  )

  (:action make-y-from-shared
    :precondition (and (use-shared) (>= (shared) 1))
    :effect (increase (goal-y) 1)
  )

  ;; Independent supporters, one per goal achiever.
  (:action load-left
    :precondition (use-independent)
    :effect (increase (left-token) 1)
  )

  (:action load-right
    :precondition (use-independent)
    :effect (increase (right-token) 1)
  )

  (:action make-x-from-left
    :precondition (and (use-independent) (>= (left-token) 1))
    :effect (increase (goal-x) 1)
  )

  (:action make-y-from-right
    :precondition (and (use-independent) (>= (right-token) 1))
    :effect (increase (goal-y) 1)
  )
)
