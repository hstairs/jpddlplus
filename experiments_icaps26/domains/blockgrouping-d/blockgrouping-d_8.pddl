(define (domain blockgrouping)

    
    (:types block)
    
    (:functions
        (delta) ; @input[0,19]_0
        (x ?b - block)  
        (y ?b - block)  
        (max_x)
        (min_x)
        (max_y)
        (min_y)
    )

    ;; Move a block from its location to an adjacent location
    (:action move_block_up
     :parameters (?b - block)
     :precondition (and (>= (- (max_y) (y ?b) ) (delta) ))
     :effect (and
        (increase (y ?b) (delta))
    ))

    (:action move_block_down
     :parameters (?b - block)
     :precondition (and (>= (- (y ?b) (min_y)) (delta) ))
     :effect (and
        (decrease (y ?b) (delta))
    ))

    (:action move_block_right
     :parameters (?b - block)
     :precondition (and (>= (- (max_x) (x ?b)) (delta) ))
     :effect (and
        (increase (x ?b) (delta))
    ))

    (:action move_block_left
     :parameters (?b - block)
     :precondition (and (>= (- (x ?b) (min_x)) (delta) ))
     :effect (and
        (decrease (x ?b) (delta))
    ))

)
