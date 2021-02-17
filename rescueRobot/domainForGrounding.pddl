;; Transport sequential
;;

(define (domain transport)
  (:requirements :typing :fluents )
  (:types
        location - object
        robot - object
  )
  (:predicates
	(at ?r - robot ?l -location)
	(road ?l1 -location ?l2 -location)
        (visited ?l -location)
        (accessPoint ?l -location)
        (infoSent ?l -location)
  )
  (:functions
	(power ?r)
	(time)
	(knowlOf ?l)
  )

  (:action drive-normal
    :parameters (?r - robot ?l1 ?l2 - location)
    :precondition (and
        (at ?r ?l1)
        (road ?l1 ?l2)
      )
    :effect (and
        (not (at ?r ?l1))
        (at ?r ?l2)
	(decrease (power ?r) 1)
        (increase (time) 2)
      )
  )

  (:action drive-fast
    :parameters (?r - robot ?l1 ?l2 - location)
    :precondition (and
        (at ?r ?l1)
        (road ?l1 ?l2)
      )
    :effect (and
        (not (at ?r ?l1))
        (at ?r ?l2)
	(decrease (power ?r) 3)
        (increase (time) 1)
      )
  )

  (:action visitInDeep
    :parameters (?r - robot ?l1 - location)
    :precondition (and
        (at ?r ?l1)
	(not (visited ?l1))
      )
    :effect (and
        (visited ?l1)
        (not (infoSent ?l1))
        (increase (time) 2)
	(decrease (power ?r) 2)
        (increase (knowlOf ?l1) 5)
      )
  )

  (:action visit
    :parameters (?r - robot ?l1 - location)
    :precondition (and
        (at ?r ?l1)
	(not (visited ?l1))
      )
    :effect (and
        (visited ?l1)
        (not (infoSent ?l1))
        (increase (time) 1)
	(decrease (power ?r) 1)
        (increase (knowlOf ?l1) 1)
      )
  )

  (:action comm
    :parameters (?r - robot ?l1 - location ?l2 -location)
    :precondition (and
        (at ?r ?l2)
	(accessPoint ?l2)
        (visited ?l1)
      )
    :effect (and
        (infoSent ?l1)
      )
  )
)
