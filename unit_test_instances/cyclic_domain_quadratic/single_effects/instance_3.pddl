(define 
    (problem instance1)
    (:domain cyclicdomain)
    (:init
        (= (a) 0)
        (= (b) 1)
    )
    (:goal 
        (<= (b) 0)
    )
)
