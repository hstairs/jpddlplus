;Header and description

(define (domain domain_name)

(:predicates
    (a)
)

(:functions ;todo: define numeric functions here
    (x) (y)
)

(:action inc_x
    :parameters ()
    :precondition (and (a) )
    :effect (and (increase (x) 3) )
)

(:action set_a
    :parameters ()
    :precondition (and )
    :effect (and (increase (x) 1) (a) )
)


;define actions here

)