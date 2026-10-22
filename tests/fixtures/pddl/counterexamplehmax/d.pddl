;Header and description

(define (domain domain_name)

; un-comment following line if constants are needed
;(:constants )

(:predicates ;todo: define predicates here
    (p0) (p1) (p2)
)


(:functions ;todo: define numeric functions here
    (x) (cost)

)

(:action inc_x_fast
    :parameters ()
    :precondition (and (p0) (p1) )
    :effect (and (increase (cost) 1) (increase (x) 2))
)

(:action get_p1
    :parameters ()
    :precondition (and )
    :effect (and (p1) (increase (cost) 10) )
)

(:action get_p0_inc_x_slow
    :parameters ()
    :precondition (and )
    :effect (and (increase (x) 1) (p0) (increase (cost) 1) )
)

(:action get_p2
    :parameters ()
    :precondition (and )
    :effect (and (p2) (increase (cost) 8) )
)





;define actions here

)
