(define (domain terraria)

(:types     
    cobweb iron_bar lead_bar chain platinum_bar - items
    silk bed wood mahogany_wood ivy_chest chest platinum_sword - items
    location items - object
)   

(:predicates
    (placed_beds)
    (placed_chests)
    (placed_swords)
    (placed_ivy_chests)
    (ready_loom)
    (ready_sawmill)
    (ready_workbench)
    (ready_anvil)
    (can_assemble ?loc - location)
    (can_lumber ?loc - location)
    (can_mine ?loc - location)
    (can_lumber_mahogany ?loc - location)
    (at ?loc - location)
)

(:functions   
    (stock ?e - items)
    (numeric_goal_bed)
    (numeric_goal_ivy)
    (numeric_goal_chest)
    (numeric_goal_sword)        
                
    (w) ;@input[5,100]_3
    (chain_found) ;@input[1,10]_3
    (iron) ;@input[5,60]_3
    (lead) ;@input[5,60]_3
    (platinum) ;@input[5,60]_3
    (cob) ;@input[10,60]_3
    (beds) ;@input[3,30]_3
)


(:action cut_a_tree
:parameters(?a - wood ?loc1 - location)
:precondition (and 
                (can_lumber ?loc1)
                (at ?loc1)
                )
:effect (and    
                (increase (stock ?a) (w)))
)

(:action cut_mahogany_tree
:parameters(?a - mahogany_wood ?loc1 - location)
:precondition (and 
                (can_lumber_mahogany ?loc1)
                (at ?loc1)
                )
:effect (and    
                (increase (stock ?a) (w) )
))

(:action find_resources
:parameters(?c - cobweb ?i - iron_bar ?lb - lead_bar ?pb - platinum_bar ?ch - chain ?loc1 - location)
:precondition (and 
                (can_mine ?loc1)
                (at ?loc1)
                (>= 120 (+ (cob) (+ (iron) (+ (chain_found) (+ (lead) (platinum) ) ) ) ) ) 
                )
:effect (and    
                (increase (stock ?c) (cob))
                (increase (stock ?i) (iron))
                (increase (stock ?lb) (lead))
				(increase (stock ?pb) (platinum))
                (increase (stock ?ch) (chain_found))
))

(:action make_silk
:parameters(?s1 - silk ?cob - cobweb ?loc1 - location)
:precondition (and 
                (ready_loom)
                (can_assemble ?loc1)
                (at ?loc1)
                (>= (stock ?cob) (* (w) 7)) 
                )
:effect (and    
                (increase (stock ?s1) (w))
                (decrease (stock ?cob) (* (w) 7) )
))

(:action assemble_a_loom
:parameters(?w1 - wood ?loc1 - location)
:precondition (and 
                (not (ready_loom))
                (ready_sawmill)
                (>= (stock ?w1) 12) 
                (at ?loc1)
                (can_assemble ?loc1) 
                )
:effect (and    
                (decrease (stock ?w1) 12)
                (ready_loom)
))

(:action assemble_a_sawmill
:parameters(?w1 - wood ?iron - iron_bar ?ch - chain ?loc1 - location)
:precondition (and 
                (not (ready_sawmill))
                (ready_workbench)
                (>= (stock ?w1) 10)  
                (>= (stock ?iron) 2) 
                (>= (stock ?ch) 1) 
                (at ?loc1)
                (can_assemble ?loc1)
                (>= (stock ?w1) 0) 
                (>= (stock ?iron) 0) 
                (>= (stock ?ch) 0) 
                )
:effect (and    
                (decrease (stock ?w1) 10)
                (decrease (stock ?iron) 2) 
                (decrease (stock ?ch) 1) 
                (ready_sawmill)
))

(:action assemble_an_anvil
:parameters(?iron - iron_bar ?loc1 - location)
:precondition (and 
                (ready_workbench)
                (>= (stock ?iron) 5) 
				(>= (stock ?iron) 0) 
                (at ?loc1)
                (can_assemble ?loc1)
                (not (ready_anvil))
                )
:effect (and    
                (decrease (stock ?iron) 5) 
                (ready_anvil)
))

(:action assemble_beds
:parameters(?w1 - wood ?s1 - silk ?b - bed ?loc1 - location)
:precondition (and 
                (ready_sawmill) ; made on sawmill
                (at ?loc1)
                (can_assemble ?loc1)
                (>= (stock ?w1) (* (beds) 15) )  
                (>= (stock ?s1) (* (beds) 5) ) 
                (>= (stock ?w1) 0 )  
                (>= (stock ?s1) 0 ) 
                (>= (stock ?w1) 0 )  
                (>= (stock ?s1) 0 ) 
				(>= (stock ?b) 0 ) 
                )
:effect (and    
                (increase (stock ?b) (beds)) 
                (decrease (stock ?w1) (* (beds) 15) ) 
                (decrease (stock ?s1) (* (beds) 5) ) 
                
))

(:action produce_chests
:parameters(?c - chest ?w1 - wood ?lb - lead_bar ?i - iron_bar ?loc1 - location)
:precondition (and 
                (ready_workbench) ; made on sawmill
                (at ?loc1)
                (can_assemble ?loc1)
                (>= (stock ?w1) (* (beds) 8) )  
                (>= (stock ?lb) (* (beds) 2) ) 
                (>= (stock ?i) (* (beds) 2) ) 
                (>= (stock ?w1) 0 )  
                (>= (stock ?lb) 0 ) 
                (>= (stock ?i) 0 ) 
                )
:effect (and    
                (increase (stock ?c) (beds)) 
                (decrease (stock ?w1) (* (beds) 8) ) 
                (decrease (stock ?lb) (* (beds) 2) ) 
                (decrease (stock ?i) (* (beds) 2) ) 
                
))

(:action produce_ivy_chests
:parameters(?c - ivy_chest ?mw - mahogany_wood ?lb - lead_bar ?i - iron_bar ?loc1 - location)
:precondition (and 
                (ready_workbench) ; made on sawmill
                (at ?loc1)
                (can_assemble ?loc1)
                (>= (stock ?mw) (* (beds) 8) )  
                (>= (stock ?lb) (* (beds) 2) ) 
                (>= (stock ?i) (* (beds) 2) ) 
                (>= (stock ?mw) 0 )  
                (>= (stock ?lb) 0 ) 
                (>= (stock ?i) 0 ) 
                )
:effect (and    
                (increase (stock ?c) (beds)) 
                (decrease (stock ?mw) (* (beds) 8) ) 
                (decrease (stock ?lb) (* (beds) 2) ) 
                (decrease (stock ?i) (* (beds) 2) ) 
                
))


(:action produce_platinum_swords
:parameters(?s - platinum_sword ?pb - platinum_bar ?loc1 - location)
:precondition (and 
                (ready_anvil) ; made on anvil
                (at ?loc1)
                (can_assemble ?loc1)
                (>= (stock ?pb) (* (beds) 5) )  
				(>= (stock ?pb) 0 )  
				(>= (stock ?s) 0 )  
                )
:effect (and    
                (increase (stock ?s) (beds)) 
                (decrease (stock ?pb) (* (beds) 5) ) 
                
))

(:action Go_to
:parameters (?loc1 ?loc2 - location)
:precondition (and
                (at ?loc1) 
                )
:effect (and       
            (not (at ?loc1))
            (at ?loc2)
           
        ))

(:action place_beds
:parameters (?b - bed)
:precondition (and     

                (>= (stock ?b) (numeric_goal_bed)) 
                    
                    (>= (stock ?b) 0)   
					 )
:effect (and        
                    (placed_beds) 
                    (decrease (stock ?b) (numeric_goal_bed) )
                     ) )

(:action place_swords
:parameters (?s - platinum_sword)
:precondition (and     

(>= (stock ?s) (numeric_goal_sword)) 
                    
                    (>= (stock ?s) 0)   
					 )
:effect (and        
                    (placed_swords) 
                    (decrease (stock ?s) (numeric_goal_sword))
                     ) )

(:action place_chests
:parameters (?c - chest)
:precondition (and     

                    (>= (stock ?c) (numeric_goal_chest)) 
                    
                    (>= (stock ?c) 0) ) 
:effect (and        
                    (placed_chests) 
                    (decrease (stock ?c) (numeric_goal_chest) )
                     ) )

(:action place_ivy_chests
:parameters (?c - ivy_chest)
:precondition (and     

                    (>= (stock ?c) (numeric_goal_ivy) ) 
                    
                    (>= (stock ?c) 0) ) 
:effect (and        
                    (placed_ivy_chests) 
                    (decrease (stock ?c) (numeric_goal_ivy) )
                     ) )
)
