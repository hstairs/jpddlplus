

(define 
    (domain testing)
    (:predicates (a) (c) (b) (d) (e) (f) (h) )
    (:functions 
        (counter) 
        (counter_2)
        (counter_3)
        (counter_4)
        (counter_5)
        (total-cost)
    )


 
    ;;this part tests the propositional part of the landmarks
     (:action produce_a_via_b_d
       :parameters ()    
       :precondition (and (b)(d))            
       :effect (and (a))
    )   
    (:action produce_a_via_b_c
       :parameters ()    
       :precondition (and (b)(e))            
       :effect (and (a))
    )  
    (:action produce_b
       :parameters ()    
       ;:precondition ()           
       :effect (and (b))
    )  
    (:action produce_c
       :parameters ()    
       :precondition (a)           
       :effect (and (c))
    ) 
    (:action produce_d
       :parameters ()    
       ;:precondition ()           
       :effect (and (d))
    ) 
    (:action produce_e
       :parameters ()    
       ;:precondition ()           
       :effect (and (e))
    ) 
    

) 
