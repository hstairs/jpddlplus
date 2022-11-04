;Header and description

(define (domain domain_name)


; un-comment following line if constants are needed
;(:constants )

(:functions ;todo: define numeric functions here
    (v)
)

(:action a1
    :parameters ()
    :effect (and  (increase (v) 1))
)

(:action a2
    :parameters ()
    :precondition (and (>= (v) 2 ))
    :effect (and  (increase (v) 2))
)

;define actions here

)