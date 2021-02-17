

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


    ;;this tests the numeric part of the problem plus dominated constraints
    (:action increase_counter_2_by_1
       :parameters ()
       ;:precondition ()            
       :effect (and(increase (counter_2) 1)(increase (total-cost) 1))
    )

    (:action increase_counter_by_1_a
       :parameters ()
       :precondition (>= (counter_2) 5)            
       :effect (and(increase (counter) 1)(increase (total-cost) 1))
    )
    
    (:action increase_counter_by_1_b
       :parameters ()    
       :precondition (>= (counter_2) 2)            
       :effect (and(increase (counter) 1)(increase (total-cost) 1))
    )

    (:action decrease_counter
       :parameters ()    
       ;:precondition ()            
       :effect (and(decrease (counter) 1)(increase (total-cost) 1))
    )

    ;;this tests the redundant constraint part
    (:action increase_counter_3_decrease_4
       :parameters ()    
       ;:precondition ()            
       :effect (and (decrease (counter_4) 5)(increase (counter_3) 5)(increase (total-cost) 1))
    )

    (:action increase_counter_4
       :parameters ()    
       ;:precondition ()            
       :effect (and (increase (counter_4) 1)(increase (total-cost) 1))
    )
    
    (:action increase_counter_3
       :parameters ()    
       ;:precondition ()            
       :effect (and (increase (counter_3) 1)(increase (total-cost) 1))
    )

    ;;this tests the ability to deal with cost
    (:action increase_counter_5
       :parameters ()    
       ;:precondition ()            
       :effect (and (increase (counter_5) 2) (increase (total-cost) 1))
    )

    (:action increase_counter_5_expensive
       :parameters ()    
       ;:precondition ()            
       :effect (and (increase (counter_5) 5) (increase (total-cost) 2))
    )
 
    ;;this part tests the propositional part of the landmarks
     (:action produce_a_via_b_d
       :parameters ()    
       :precondition (and (b)(d))            
       :effect (and (a))
    )   
    (:action produce_a_via_b_c
       :parameters ()    
       :precondition (and (b)(c))            
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
    

) 
