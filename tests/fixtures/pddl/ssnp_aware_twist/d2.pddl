;Header and description

(define (domain domain_name)

(:predicates
    (a) (b) (c)
)

(:functions ;todo: define numeric functions here
    (x) (y)
)

(:action inc_x
    :parameters ()
    :precondition (and (a) )
    :effect (and (increase (x) 3) )
)

(:action inc_x_set_a
    :parameters ()
    :precondition (and )
    :effect (and (increase (x) 1) )
)
(:action set_a
    :parameters ()
    :precondition (and (b) )
    :effect (and (a))
)

(:action set_b
    :parameters ()
    :precondition (and (c))
    :effect (and (b))
)

(:action set_c
    :parameters ()
    :precondition (and )
    :effect (and (c))
)


;define actions here

)