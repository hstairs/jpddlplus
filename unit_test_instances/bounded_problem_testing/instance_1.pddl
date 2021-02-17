;this instance is solved repeating action a 10 times
(define 
    (problem instance1)
    (:domain simple_increase_domain)
    (:init
        (= (a) 0)
        (= (b) 1)
    )
    (:goal 
        (>= (a) 10)
    )
)
