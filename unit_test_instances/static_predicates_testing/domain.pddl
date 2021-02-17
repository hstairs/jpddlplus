

(define 
    (domain testing)
	(:types caccamo -object)
    (:predicates (a ?x ?y -caccamo ) (b ?x ?y -caccamo )  (goal) )
    (:functions 
	    (f1 ?x -caccamo)
    )
    (:action first
       :parameters (?x ?y -caccamo)
       :precondition (not (not(a ?x ?y)))
       :effect 
                (and 
                    (b ?x ?y)
                )
	
    )
    
    (:action second
       :parameters (?x ?y -caccamo)
       :precondition (and (b ?x ?y) (a ?x ?y))
       :effect 
                (and 
                    (not(goal))
                )
	
    )
) 
