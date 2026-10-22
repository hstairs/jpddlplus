(define (domain sailing)
  (:types boat person)
  (:predicates (saved ?p - person))
  (:functions (x ?b - boat) (y ?b - boat) (d ?p - person))

  (:action go_est
    :parameters (?b - boat)
    :effect (increase (x ?b) 3))
  (:action go_west
    :parameters (?b - boat)
    :effect (decrease (x ?b) 3))
  (:action go_south_east
    :parameters (?b - boat)
    :effect (and (decrease (x ?b) 2) (decrease (y ?b) 2)))
  (:action go_south_west
    :parameters (?b - boat)
    :effect (and (increase (x ?b) 2) (decrease (y ?b) 2)))

  (:action save_person
    :parameters (?b - boat ?p - person)
    :precondition (and
      (>= (+ (x ?b) (y ?b)) (d ?p))
      (>= (- (y ?b) (x ?b)) (d ?p))
      (<= (+ (x ?b) (y ?b)) (+ (d ?p) 25))
      (<= (- (y ?b) (x ?b)) (+ (d ?p) 25)))
    :effect (saved ?p)))
