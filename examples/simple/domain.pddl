(define (domain test)

(:requirements :strips)

(:predicates
    (a)
    (b)
)

(:functions 

    (x)
    (y)
    (z)
    (k)

)


; (:action increase_x
;     :parameters ()
;     :precondition (and )
;     :effect (and 

;         (when (>= (y) 0) (increase (x) 1))
;     )
; )

; (:action increase_x
;     :parameters ()
;     :precondition (and )
;     :effect (and 
;         (increase (x) 1)
;         (when (b) (a))
;     )
; )

(:action non_linear
    :parameters ()
    :precondition (and )
    :effect (and 
        (increase (z) (k))
    )
)

(:action increase_k
    :parameters ()
    :precondition (and )
    :effect (and 
        (increase (k) 1)
    )
)


(:action decrease_k
    :parameters ()
    :precondition (and )
    :effect (and 
        (decrease (k) 1)
    )
)



)