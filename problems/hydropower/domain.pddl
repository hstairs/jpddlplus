;; The Hydro Power domain is is concerned with generating financial gains,
;; obtained by pumping up water when energy is cheap, storing it using hydroelectric
;; reservoirs and selling it when the price is higher. Introduced in

;; Coles, Amanda, M. Fox, and D. Long.
;; "A hybrid LP-RPG heuristic for modelling numeric resource flows in planning."
;; Journal of Artificial Intelligence Research 46 (2013): 343-412.
(define (domain hydropower)

;	(:requirements :adl :typing :fluents)

	(:types time turnvalue station - object)

	(:predicates

		(timenow ?t - time)
		(before ?t1 - time ?t2 - time)
		(demand ?t - time ?n - turnvalue)
	)

	(:functions
		(funds ?s - station)
		(stored_units ?s - station)
		(stored_capacity ?s - station)
		(objective ?s - station)
		(value ?n - turnvalue)
		
	)

;;;;;;;;;;;;advancing time

	(:action advance_time
		:parameters(?t1 - time ?t2 - time)
		:precondition (and
			(timenow ?t1)
			(before ?t1 ?t2)
		)
		:effect( and
			(timenow ?t2)
			(not (timenow ?t1))
		)
	)

(:action pump_water_up
		:parameters(?t1 - time ?n1 - turnvalue ?s - station)
		:precondition (and
		    (timenow ?t1)
		    (>= (funds ?s) (* 1.05 (value ?n1)))
		    (>= (stored_capacity ?s) 1)
		    (demand ?t1 ?n1)
		)
		:effect( and
		  (increase (stored_units ?s) 1)
		  (decrease (stored_capacity ?s) 1)
		  (decrease (funds ?s) (* 1.05 (value ?n1)))
		)
)

(:action generate
		:parameters(?t1 - time ?n1 - turnvalue ?s - station)
		:precondition (and
		    (timenow ?t1)
		    (>= (stored_units ?s) 1)
		    (demand ?t1 ?n1)
		)
		:effect( and
		  (decrease (stored_units ?s) 1)
		  (increase (stored_capacity ?s) 1)
		  (increase (funds ?s) (value ?n1))
		)
)



)
