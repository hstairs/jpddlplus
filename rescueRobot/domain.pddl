;; Transport sequential
;;

(define (domain transport)
  (:requirements :typing :fluents )
  (:types
        site - object
        robot - object
  )
  (:predicates (joined ?l1 ?l2 -site) (in ?r -robot ?l1 -site) (observed ?l1 -site))

  (:functions (power ?r -robot) 
	      (time) 
              (distance ?l1 ?l2 -site) 
              (powerConsumption ?r -robot)
              (memory ?r -robot)
              (memoryConsumption ?r -robot)
	      (pathDifficulty ?l1 ?l2 -location)
	      (info ?l1 -location)	
  ) 

  (:action drive-normal
    :parameters (?r - robot ?l1 ?l2 -site)
    :precondition (and (joined ?l1 ?l2)
		       (in ?r ?l1) 
		   (> (power ?r) (* (distance ?l1 ?l2) 10))
                  )
    :effect (and
        (in ?r ?l2)
        (not(in ?r ?l1))
	(increase (powerConsumption ?r) (* (distance ?l1 ?l2) 10))
	(increase (time) (* (distance ?l1 ?l2) 10))
	(decrease (power ?r)(* (distance ?l1 ?l2) 10))
 	)
  )
  
  (:action drive-safe
    :parameters (?r - robot ?l1 ?l2 -site)
    :precondition (and (joined ?l1 ?l2)
		       (in ?r ?l1) 
		   (> (power ?r) (* (distance ?l1 ?l2) 8))
                  )
    :effect (and
        (in ?r ?l2)
        (not(in ?r ?l1))
	(increase (powerConsumption ?r) (* (distance ?l1 ?l2) 8))
	(increase (time) (* (distance ?l1 ?l2) 12))
	(decrease (power ?r)(* (distance ?l1 ?l2) 8))
 	)
  )

  (:action drive-fast
    :parameters (?r - robot ?l1 ?l2 -site)
    :precondition (and (joined ?l1 ?l2)
		       (in ?r ?l1) 
		   (> (power ?r) (* (distance ?l1 ?l2) 12))
                  )
    :effect (and
        (in ?r ?l2)
        (not(in ?r ?l1))
	(increase (powerConsumption ?r) (* (distance ?l1 ?l2) 12))
	(increase (time) (* (distance ?l1 ?l2) 8))
	(decrease (power ?r)(* (distance ?l1 ?l2) 12))
 	)
  )


  (:action takePicture-hr
    :parameters (?r - robot ?l1 -site)
    :precondition (and (in ?r ?l1) 
		       (> (memory ?r) 10)
                  )
		
    :effect (and
        (observed ?l1)
	(increase (memoryConsumption ?r) 10)
	(decrease (memory ?r) 10)
	(increase (time) 5)	
	(increase (info ?l1) 3)
 	)
  )
  (:action takePicture-mr
    :parameters (?r - robot ?l1 -site)
    :precondition (and (in ?r ?l1) 
		       (> (memory ?r) 8)
                  )
		
    :effect (and
        (observed ?l1)
	(increase (memoryConsumption ?r) 8)
	(decrease (memory ?r) 8)
	(increase (time) 3)	
	(increase (info ?l1) 2)
 	)
  )
  (:action takePicture-lr
    :parameters (?r - robot ?l1 -site)
    :precondition (and (in ?r ?l1) 
		       (> (memory ?r) 5)
                  )
		
    :effect (and
        (observed ?l1)
	(increase (memoryConsumption ?r) 5)
	(decrease (memory ?r) 5)
	(increase (time) 2)
	(increase (info ?l1) 1)	
 	)
  )
)
