
(define (domain cashpoint)
(:requirements :typing :fluents :continuous)

(:types location
        currency
        item)


(:predicates    
    (at ?a - location)
    (canwithdraw ?b - location)
    (canbuy ?a - location ?i - item)
    (bought ?i - item)
    (have_enough ?z - currency)
    (currencyOf ?i - item ?c - currency)
)
(:functions     
    (cash) ;@input[20,500]_0
    (inpocket ?z - currency)
    (currency_goal ?z1 - currency)
    (price ?i - item)
    (balance ?z - currency)
)

(:action buy_with_cash
:parameters (?i - item ?a - location ?z - currency)
:precondition (and     
                (at ?a)
                (canbuy ?a ?i) 
                (>= (inpocket ?z) (price ?i) )
                (currencyOf ?i ?z)
                (not (bought ?i))
            )
:effect (and    
                (decrease (inpocket ?z) (price ?i) )
                (bought ?i) 
        ))

(:action withdraw
:parameters (?b - location ?z1 - currency)
:precondition (and 
                (at ?b) 
                (>= (inpocket ?z1) 0)
                (canwithdraw ?b)
                (>=  (balance ?z1) (cash))
            )
:effect (and    
                (increase (inpocket ?z1) (cash))
                (decrease (balance ?z1) (cash))
        ))

(:action goto
:parameters (?a ?b - location)
:precondition (and 
                (at ?a)
            ) 
:effect (and       
                (not (at ?a))
                (at ?b)
        ))

(:action save_for_later
:parameters (?z - currency)
:precondition (and  
                (>= (inpocket ?z) (currency_goal ?z) ) 
                (not (have_enough ?z))
            )
:effect (and        
                (have_enough ?z)
                (decrease (inpocket ?z) (currency_goal ?z) )
))
)
