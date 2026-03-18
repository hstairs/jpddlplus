(define (domain procurement)
(:requirements :typing :continuous)

(:types     itemA itemB itemC itemD itemE itemF itemG itemH itemI itemJ itemK itemL itemM itemN itemO itemP itemQ itemR itemS - item
            supplier customer workshop - location
)


(:predicates
    (can_supply_D ?loc - supplier)
    (can_supply_E ?loc - supplier)
    (can_supply_G ?loc - supplier)
    (can_supply_I ?loc - supplier)
    (can_supply_J ?loc - supplier)
    (can_supply_N ?loc - supplier)
    (can_supply_O ?loc - supplier)
    (can_supply_Q ?loc - supplier)
    (can_supply_R ?loc - supplier)
    (can_supply_X ?loc - supplier)
    (can_supply_S ?loc - supplier)
    (can_supply_P ?loc - supplier)
    (can_produce_A ?loc - workshop)
    (can_produce_B ?loc - workshop)
    (can_produce_C ?loc - workshop)
    (can_produce_F ?loc - workshop)
    (can_produce_H ?loc - workshop)
    (can_produce_K ?loc - workshop)
    (can_produce_L ?loc - workshop)
    (can_produce_M ?loc - workshop)
    (can_produce_P ?loc - workshop)
    (can_produce_S ?loc - workshop)
    (delivered ?a - item ?loc - customer)
    (at ?loc1 - location)
)

(:functions   
    (batchsize) ;@input[0,100]_0
    (stock ?e - item) 
    (item-goal ?e - item) 
)

(:action produce_A ;; level 0
:parameters (?a - itemA ?b - itemB ?c - itemC ?d - itemD ?loc1 - workshop)
:precondition (and 
                (can_produce_A ?loc1)
                (at ?loc1) 
                (>= (stock ?a) 0)
                (>= (stock ?b) (* (batchsize) 2) ) 
                (>= (stock ?c) (batchsize))  
                (>= (stock ?d) (* (batchsize) 2) ) 
            )
:effect (and    
                (increase (stock ?a) (batchsize))
                (decrease (stock ?c) (batchsize) ) 
                (decrease (stock ?d) (* (batchsize) 2) ) 
                (decrease (stock ?b) (* (batchsize) 2) ) 
)
)

(:action produce_B ;; level 1
:parameters (?b - itemB ?e - itemE ?f - itemF ?g - itemG ?loc1 - workshop)
:precondition (and  
                (can_produce_B ?loc1)
                (at ?loc1)                 
                (>= (stock ?b) 0)
                (>= (stock ?e) (* (batchsize) 2) ) 
                (>= (stock ?f) (* (batchsize) 1) ) 
                (>= (stock ?g) (* (batchsize) 2) ) 

            )
:effect (and    
                (increase (stock ?b) (batchsize)) 
		(decrease (stock ?e) (* (batchsize) 2) ) 
		(decrease (stock ?f) (* (batchsize) 1) ) 
		(decrease (stock ?g) (* (batchsize) 2) ) 
)
)

(:action produce_C ;; level 1
:parameters (?c - itemC ?h - itemH ?i - itemI ?loc1 - workshop)
:precondition (and 
                (can_produce_C ?loc1)
                (at ?loc1)
                (>= (stock ?c) 0)
                (>= (stock ?h) (* (batchsize) 1) ) 
                (>= (stock ?i) (* (batchsize) 2) ) 
            )
:effect (and    
                (increase (stock ?c) (batchsize))  
		(decrease (stock ?h) (* (batchsize) 1) ) 
		(decrease (stock ?i) (* (batchsize) 2) ) 
)
)

(:action produce_F ;; level 2
:parameters (?f - itemF ?j - itemJ ?k - itemK ?loc1 - workshop)
:precondition (and 
                (can_produce_F ?loc1)
                (at ?loc1)
                (>= (stock ?f) 0)
                (>= (stock ?k) (* (batchsize) 1) ) 
                (>= (stock ?j) (* (batchsize) 4) ) 
            )
:effect (and    
                (increase (stock ?f) (batchsize))  
		(decrease (stock ?k) (* (batchsize) 1) ) 
		(decrease (stock ?j) (* (batchsize) 4) ) 
)
)

(:action produce_H ;; level 2
:parameters (?h - itemH ?l - itemL ?m - itemM ?loc1 - workshop)
:precondition (and
                (can_produce_H ?loc1)
                (at ?loc1)
                (>= (stock ?h) 0)
                (>= (stock ?l) (* (batchsize) 5) ) 
                (>= (stock ?m) (* (batchsize) 2) ) 
            )
:effect (and    
                (increase (stock ?h) (batchsize))  
		(decrease (stock ?l) (* (batchsize) 5) ) 
		(decrease (stock ?m) (* (batchsize) 2) ) 
)
)

(:action produce_K ;; level 3
:parameters (?k - itemK ?n - itemN ?o - itemO ?p - itemP ?loc1 - workshop)
:precondition (and 
                (can_produce_K ?loc1)
                (at ?loc1)
                (>= (stock ?k) 0)
                (>= (stock ?n) (* (batchsize) 6) ) 
                (>= (stock ?o) (* (batchsize) 2) ) 
                (>= (stock ?p) (* (batchsize) 1) ) 
            )
:effect (and    
                (increase (stock ?k) (batchsize))  
		(decrease (stock ?n) (* (batchsize) 6) ) 
		(decrease (stock ?o) (* (batchsize) 2) ) 
		(decrease (stock ?p) (* (batchsize) 1) ) 
)
)

(:action produce_L ;; level 3
:parameters (?l - itemL ?q - itemQ ?loc1 - workshop)
:precondition (and 
                (can_produce_L ?loc1)
                (at ?loc1)
                (>= (stock ?l) 0)
                (>= (stock ?q) (* (batchsize) 2) ) 
            )
:effect (and    
                (increase (stock ?l) (batchsize))  
		(decrease (stock ?q) (* (batchsize) 2) ) 
)
)

(:action produce_M ;; level 3
:parameters (?m - itemM ?r - itemR ?s - itemS ?loc1 - workshop)
:precondition (and 
                (can_produce_M ?loc1)
                (at ?loc1)
                (>= (stock ?m) 0)
                (>= (stock ?r) (* (batchsize) 3) ) 
                (>= (stock ?s) (* (batchsize) 2) ) 
            )
:effect (and    
                (increase (stock ?m) (batchsize))  
		(decrease (stock ?r) (* (batchsize) 3) ) 
		(decrease (stock ?s) (* (batchsize) 2) ) 
)
)

(:action supply_raw_material_D
:parameters (?d - itemD ?loc1 - supplier)
:precondition (and 
                (can_supply_D ?loc1) 
                (at ?loc1) 
                (>= (stock ?d) 0)

            )
:effect (and    
                (increase (stock ?d) (batchsize))  
)
)

(:action supply_raw_material_E
:parameters (?d - itemE ?loc1 - supplier)
:precondition (and 
                (can_supply_E ?loc1) 
                (at ?loc1) 
                (>= (stock ?d) 0)
            )
:effect (and    
                (increase (stock ?d) (batchsize))  
)
)

(:action supply_raw_material_G
:parameters (?d - itemG ?loc1 - supplier)
:precondition (and 
                (can_supply_G ?loc1) 
                (at ?loc1) 
                (>= (stock ?d) 0)
            )
:effect (and    
                (increase (stock ?d) (batchsize))  
)
)

(:action supply_raw_material_I
:parameters (?d - itemI ?loc1 - supplier)
:precondition (and 
                (can_supply_I ?loc1) 
                (at ?loc1) 
                (>= (stock ?d) 0)
            )
:effect (and    
                (increase (stock ?d) (batchsize))  
)
)

(:action supply_raw_material_J
:parameters (?d - itemJ ?loc1 - supplier)
:precondition (and 
                (can_supply_J ?loc1) 
                (at ?loc1) 
                (>= (stock ?d) 0)

            )
:effect (and    
                (increase (stock ?d) (batchsize))  
)
)

(:action supply_raw_material_N
:parameters (?d - itemN ?loc1 - supplier)
:precondition (and 
                (can_supply_N ?loc1) 
                (at ?loc1) 
                (>= (stock ?d) 0)
            )
:effect (and    
                (increase (stock ?d) (batchsize))  
)
)

(:action supply_raw_material_O
:parameters (?d - itemO ?loc1 - supplier)
:precondition (and 
                (can_supply_O ?loc1) 
                (at ?loc1) 
                (>= (stock ?d) 0)
            )
:effect (and    
                (increase (stock ?d) (batchsize))  
)
)

(:action supply_raw_material_Q
:parameters (?d - itemQ ?loc1 - supplier)
:precondition (and 
                (can_supply_Q ?loc1) 
                (at ?loc1) 
                (>= (stock ?d) 0)
            )
:effect (and    
                (increase (stock ?d) (batchsize))  
)
)

(:action supply_raw_material_R
:parameters (?d - itemR ?loc1 - supplier)
:precondition (and 
                (can_supply_R ?loc1) 
                (at ?loc1) 
                (>= (stock ?d) 0)
            )
:effect (and    
                (increase (stock ?d) (batchsize))  
)
)

(:action supply_raw_material_S
:parameters (?d - itemS ?loc1 - supplier)
:precondition (and 
                (can_supply_S ?loc1) 
                (at ?loc1) 
                (>= (stock ?d) 0)

            )
:effect (and    
                (increase (stock ?d) (batchsize))  
)
)

(:action supply_raw_material_P
:parameters (?d - itemP ?loc1 - supplier)
:precondition (and 
                (can_supply_P ?loc1) 
                (at ?loc1) 
                (>= (stock ?d) 0)
            )
:effect (and    
                (increase (stock ?d) (batchsize))  
)
)

(:action go_to
:parameters (?loc1 ?loc2 - location)
:precondition (and (at ?loc1) 
                )
:effect (and       
            (not (at ?loc1))
            (at ?loc2)
        ))

(:action deliver_to_customer
:parameters (?loc1 - customer ?a - item)
:precondition (and 
                (at ?loc1)
                (>= (stock ?a) (item-goal ?a) )
		(not (delivered ?a ?loc1))
            )
:effect (and    
                (delivered ?a ?loc1)
                (decrease (stock ?a) (item-goal ?a) )
                )
)
)
