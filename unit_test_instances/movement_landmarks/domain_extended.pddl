

(define 
    (domain testing)
    (:predicates (a) (c) (b) (d) (e) )
    (:functions 
        (counter1) 
	    (counter2) 
    )

    (:action increase_counter2
       :parameters ()     
       :effect (and
		            (increase (counter2) 1)
               )
    )

    (:action increase_counter1
       :parameters ()     
       :effect (and
		            (increase (counter1) 1)
               )
    )

    (:action produce-d
       :parameters ()
       :precondition (and			          
						(>= (counter2) 10) 
			        )           
       :effect (and
		            (d)
               )
    )
    (:action produce-e
       :parameters ()
       :precondition (and			          
						(>= (counter1) 10) 
			        )           
       :effect (and
		            (e)
               )
    )
	(:action produce-a
       :parameters ()         
       :effect (and
		            (a)
               )
    )
	(:action produce-d-analogously
       :parameters ()  
       :precondition (and			          
						(>= (counter2) 10) 
			        )             
       :effect (and
		            (d)
               )
    )

) 
