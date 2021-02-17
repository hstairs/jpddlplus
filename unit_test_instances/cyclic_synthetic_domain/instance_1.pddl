;this instance is solvable with two repetitions of change_b_c. The first one changes b with c (putting its value to 10) but at the same time
;it put c = 0. The second application of change_b_c puts the value of b to 0, then satisfying the condition.
(define 
    (problem instance1)
    (:domain cyclicdomain)
    (:init
        (= (a) 10.0)
        (= (b) 1.0)
        (= (c) 3.0)
    )
    (:goal 
        (>= (b) 10.0)
    )
)
