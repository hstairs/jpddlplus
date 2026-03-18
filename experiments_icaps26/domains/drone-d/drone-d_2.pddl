    (define (domain drone)

    (:types location)


    (:predicates 
        (visited ?x - location)
    )
    
    (:functions
        (deltax) ;@input[0,2]_0
        (deltay) ;@input[0,2]_0
        (deltaz) ;@input[0,2]_0
        (x)
        (y)
        (z) 
        (xl ?l - location)
        (yl ?l - location)
        (zl ?l - location)
        (battery-level)
        (battery-level-full)        
        (min_x)
        (max_x)
        (min_y)
        (max_y)
        (min_z)
        (max_z)
    )

    (:action increase_x
        :parameters ()
        :precondition (and 
                          (>= (battery-level) (deltax))
                          (>= (- (max_x) (x)) (deltax))
                      )
        :effect (and (increase (x) (deltax)) 
                    (decrease (battery-level) (deltax))
                )
    )

    (:action decrease_x
        :parameters ()
        :precondition (and 
                            (>= (battery-level) (deltax))
                            (>= (- (x) (min_x)) (deltax))
                      )
        :effect (and (decrease (x) (deltax))
                    (decrease (battery-level) (deltax))
                )
    )


    (:action increase_y
        :parameters ()
        :precondition (and 
                            (>= (battery-level) (deltay))
                            (>= (- (max_y) (y)) (deltay))
                      )
        :effect (and (increase (y) (deltay))
                    (decrease (battery-level) (deltay))
                )
    )
    (:action decrease_y
        :parameters ()
        :precondition (and 
                            (>= (battery-level) (deltay))
                            (>= (- (y) (min_y)) (deltay))
                      )
        :effect (and (decrease (y) (deltay))
                    (decrease (battery-level) (deltay))
                )
    )


    (:action increase_z
        :parameters ()
        :precondition (and 
                            (>= (battery-level) (deltaz))
                            (>= (- (max_z) (z)) (deltaz))
                      )
        :effect (and (increase (z) (deltaz))
                    (decrease (battery-level) (deltaz))
                )
    )
    (:action decrease_z
        :parameters ()
        :precondition (and 
                            (>= (battery-level) (deltaz))
                            (>= (- (z) (min_z)) (deltaz))
                      )
        :effect (and (decrease (z) (deltaz))
                    (decrease (battery-level) (deltaz))
                )
    )


;    (:action visit
;        :parameters (?l - location)
;        :precondition (and
;                        (>= (battery-level) 1)
;                        (<= (- (xl ?l) (x)) 0.2)
;                        (<= (- (yl ?l) (y)) 0.2)
;                        (<= (- (zl ?l) (z)) 0.2)                        
;                        (>= (- (x) (xl ?l)) 0.2)
;                        (>= (- (y) (yl ?l)) 0.2)
;                        (>= (- (z) (zl ?l)) 0.2)
;                        ;(not (visited ?l))                        
;                       )
;        :effect (and (visited ?l)(decrease (battery-level) 1))
;    )

    (:action visit
    :parameters (?l - location)
    :precondition (and
        (>= (battery-level) 1)
        (<= (- (xl ?l) (x)) 0.2)
        (<= (- (x) (xl ?l)) 0.2)
        (<= (- (yl ?l) (y)) 0.2)
        (<= (- (y) (yl ?l)) 0.2)
        (<= (- (zl ?l) (z)) 0.2)
        (<= (- (z) (zl ?l)) 0.2)
    )
    :effect (and (visited ?l) (decrease (battery-level) 1))
    )


    (:action recharge
        :parameters ()
        :precondition (and
                        (<= (x) 0.2)
                        (<= (y) 0.2)
                        (<= (z) 0.2)                        
                        (>= (x) 0)
                        (>= (y) 0)
                        (>= (z) 0)                        
                       )
        :effect (and 
                       (assign (battery-level) (battery-level-full)))
    )

)
