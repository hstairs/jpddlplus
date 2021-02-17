;this instance is solvable with two repetition of change b. The first one changes b with c (putting its value to 10) but at the same time
;it put c = 0. The second application of change b, will put the value of b to 0, then satisfying the condition.
(define 
    (problem instance1)
    (:domain cyclicdomain)
    (:init
        (= (a) 0)
        (= (b) 1)
        (= (c) 10)
    )
    (:goal 
        (<= (b) 0)
    )
)
