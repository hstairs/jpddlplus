(define (domain boa-tradeoff)
  (:requirements :strips :numeric-fluents)
  (:predicates (done))
  (:functions (cost1) (cost2))

  (:action favor-first
    :parameters ()
    :precondition (not (done))
    :effect (and (done) (increase (cost1) 1) (increase (cost2) 5)))

  (:action favor-second
    :parameters ()
    :precondition (not (done))
    :effect (and (done) (increase (cost1) 5) (increase (cost2) 1)))

  (:action dominated
    :parameters ()
    :precondition (not (done))
    :effect (and (done) (increase (cost1) 6) (increase (cost2) 6)))
)
