;Header and description

(define (domain domain_name)

(:predicates
    (a) (b)
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
    :precondition (and (b))
    :effect (and (a) )
)

(:action set_b
    :parameters ()
    :precondition (and )
    :effect (and (b) (increase (x) 0.001) )
)
;define actions here

)