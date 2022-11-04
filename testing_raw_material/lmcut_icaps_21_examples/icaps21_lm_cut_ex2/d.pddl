;Header and description

(define (domain domain_name)


; un-comment following line if constants are needed
;(:constants )
(:predicates
    (p)
    (g)
)
(:functions ;todo: define numeric functions here
    (v)
)

(:action a1
    :parameters ()
    :effect (and  (increase (v) 1))
)

(:action a2
    :parameters ()
    :precondition (and (>= (v) 1 ))
    :effect (and  (p))
)

(:action a3
    :parameters ()
    :precondition (and (p))
    :effect (and  (g))
)

(:action a4
    :parameters ()
    :precondition (and (>= (v) 2 ))
    :effect (and  (g))
)

;define actions here

)