;; Enrico Scala (enricos83@gmail.com) and Miquel Ramirez (miquel.ramirez@gmail.com)
(define (domain civ) 
;(:requirements :adl :typing :equality)
 (:types resource - object vehicle - store place - store)
 (:constants ore - resource iron - resource stone - resource wood - resource
  timber - resource coal - resource)
 (:predicates (connected-by-land ?p1 - place ?p2 - place)
  (connected-by-rail ?p1 - place ?p2 - place)
  (connected-by-sea ?p1 - place ?p2 - place) (woodland ?p - place)
  (mountain ?p - place) (metalliferous ?p - place) (by-coast ?p - place)
  (has-cabin ?p - place) (has-coal-stack ?p - place) (has-quarry ?p - place)
  (has-mine ?p - place) (has-sawmill ?p - place) (has-ironworks ?p - place)
  (has-docks ?p - place) (has-wharf ?p - place) (is-cart ?v - vehicle)
  (is-train ?v - vehicle) (is-ship ?v - vehicle)
  (is-at ?v - vehicle ?p - place) (potential ?v - vehicle))
 (:functions (available ?r - resource ?s - store) (space-in ?v - vehicle)
  (labour) (resource-use) (pollution) (housing ?p - place))

 (:action load1 :parameters (?v - vehicle ?p - place ?r - resource)
  :precondition (and (is-at ?v ?p) (>= (available ?r ?p) 1) (>= (space-in ?v) 1))
  :effect
  (and (decrease (space-in ?v) 1)
       (increase (available ?r ?v) 1)
       (decrease (available ?r ?p) 1)
       (increase (labour) 1)))
 (:action unload1 :parameters (?v - vehicle ?p - place ?r - resource)
  :precondition (and (is-at ?v ?p) (>= (available ?r ?v) 1)) :effect
  (and (increase (space-in ?v) 1)
       (decrease (available ?r ?v) 1)
       (increase (available ?r ?p) 1)
       (increase (labour) 1)))
 (:action move-cart1 :parameters (?v - vehicle ?p1 - place ?p2 - place)
  :precondition (and (is-cart ?v) (connected-by-land ?p1 ?p2) (is-at ?v ?p1))
  :effect (and (not (is-at ?v ?p1)) (is-at ?v ?p2) (increase (labour) 2)))
 (:action move-train1 :parameters (?v - vehicle ?p1 - place ?p2 - place)
  :precondition
  (and (is-train ?v)
       (connected-by-rail ?p1 ?p2)
       (is-at ?v ?p1)
       (>= (available coal ?v) 1))
  :effect
  (and (not (is-at ?v ?p1))
       (is-at ?v ?p2)
       (decrease (available coal ?v) 1)
       (increase (pollution) 1)))
 (:action move-ship1 :parameters (?v - vehicle ?p1 - place ?p2 - place)
  :precondition
  (and (is-ship ?v)
       (connected-by-sea ?p1 ?p2)
       (is-at ?v ?p1)
       (>= (available coal ?v) 2))
  :effect
  (and (not (is-at ?v ?p1))
       (is-at ?v ?p2)
       (decrease (available coal ?v) 2)
       (increase (pollution) 2)))
 (:action build-cabin1 :parameters (?p - place) :precondition (woodland ?p)
  :effect (and (increase (labour) 1) (has-cabin ?p)))
 (:action build-quarry1 :parameters (?p - place) :precondition (mountain ?p)
  :effect (and (increase (labour) 2) (has-quarry ?p)))
 (:action build-coal-stack1 :parameters (?p - place) :precondition
  (>= (available timber ?p) 1) :effect
  (and (increase (labour) 2)
       (decrease (available timber ?p) 1)
       (has-coal-stack ?p)))
 (:action build-sawmill1 :parameters (?p - place) :precondition
  (>= (available timber ?p) 2) :effect
  (and (increase (labour) 2)
       (decrease (available timber ?p) 2)
       (has-sawmill ?p)))
 (:action build-mine1 :parameters (?p - place) :precondition
  (and (metalliferous ?p) (>= (available wood ?p) 2)) :effect
  (and (increase (labour) 3) (decrease (available wood ?p) 2) (has-mine ?p)))
 (:action build-ironworks1 :parameters (?p - place) :precondition
  (and (>= (available stone ?p) 2) (>= (available wood ?p) 2)) :effect
  (and (increase (labour) 3)
       (decrease (available stone ?p) 2)
       (decrease (available wood ?p) 2)
       (has-ironworks ?p)))
 (:action build-docks1 :parameters (?p - place) :precondition
  (and (by-coast ?p) (>= (available stone ?p) 2) (>= (available wood ?p) 2))
  :effect
  (and (decrease (available stone ?p) 2)
       (decrease (available wood ?p) 2)
       (increase (labour) 2)
       (has-docks ?p)))
 (:action build-wharf1 :parameters (?p - place) :precondition
  (and (has-docks ?p) (>= (available stone ?p) 2) (>= (available iron ?p) 2))
  :effect
  (and (decrease (available stone ?p) 2)
       (decrease (available iron ?p) 2)
       (increase (labour) 2)
       (has-wharf ?p)))
 (:action build-rail1 :parameters (?p1 - place ?p2 - place) :precondition
  (and (connected-by-land ?p1 ?p2)
       (>= (available wood ?p1) 1)
       (>= (available iron ?p1) 1))
  :effect
  (and (decrease (available wood ?p1) 1)
       (decrease (available iron ?p1) 1)
       (increase (labour) 2)
       (connected-by-rail ?p1 ?p2)))
 (:action build-house1 :parameters (?p - place) 
	:precondition (and (>= (available wood ?p) 1) (>= (available stone ?p) 1))
        :effect
  	(and (increase (housing ?p) 1)
       	     (decrease (available wood ?p) 1)
       	     (decrease (available stone ?p) 1))
 )
 (:action build-cart1 :parameters (?p - place ?v - vehicle) :precondition
  (and (>= (available timber ?p) 1) (potential ?v)) :effect
  (and (decrease (available timber ?p) 1)
       (is-at ?v ?p)
       (is-cart ?v)
       (not (potential ?v))
       (increase (space-in ?v) 1)
       (increase (labour) 1)))
 (:action build-train1 :parameters (?p - place ?v - vehicle) :precondition
  (and (potential ?v) (>= (available iron ?p) 2)) :effect
  (and (decrease (available iron ?p) 2)
       (is-at ?v ?p)
       (is-train ?v)
       (not (potential ?v))
       (increase (space-in ?v) 5)
       (increase (labour) 2)))
 (:action build-ship1 :parameters (?p - place ?v - vehicle) :precondition
  (and (potential ?v) (>= (available iron ?p) 4)) :effect
  (and (has-wharf ?p)
       (decrease (available iron ?p) 4)
       (is-at ?v ?p)
       (is-ship ?v)
       (not (potential ?v))
       (increase (space-in ?v) 10)
       (increase (labour) 3)))
 (:action fell-timber1 :parameters (?p - place) :precondition (has-cabin ?p)
  :effect (and (increase (available timber ?p) 1) (increase (labour) 1)))
 (:action break-stone1 :parameters (?p - place) :precondition (has-quarry ?p)
  :effect
  (and (increase (available stone ?p) 1)
       (increase (labour) 1)
       (increase (resource-use) 1)))
 (:action mine-ore1 :parameters (?p - place) :precondition (has-mine ?p)
  :effect (and (increase (available ore ?p) 1) (increase (resource-use) 2)))
 (:action burn-coal1 :parameters (?p - place) :precondition
  (and (has-coal-stack ?p) (>= (available timber ?p) 1)) :effect
  (and (decrease (available timber ?p) 1)
       (increase (available coal ?p) 1)
       (increase (pollution) 1)))
 (:action saw-wood1 :parameters (?p - place) :precondition
  (and (has-sawmill ?p) (>= (available timber ?p) 1)) :effect
  (and (decrease (available timber ?p) 1) (increase (available wood ?p) 1)))
 (:action make-iron1 :parameters (?p - place) :precondition
  (and (has-ironworks ?p) (>= (available ore ?p) 1) (>= (available coal ?p) 2))
  :effect
  (and (decrease (available ore ?p) 1)
       (decrease (available coal ?p) 2)
       (increase (available iron ?p) 1)
       (increase (pollution) 2))))
