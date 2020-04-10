;this instance is solvable by opening twice valves a and c. The first one put liquid from c to b (putting its value to 10) but at the same time
;it put c = 10. The second application puts the value of b to 10, then satisfying the goal condition.
(define 
    (problem instance1)
    (:domain fluids)
    (:init
        (= (a) 10)
        (= (b) 1)
        (= (c) 3)
        (true)
    )
    (:goal 
        (= (b) 10)
    )
)
