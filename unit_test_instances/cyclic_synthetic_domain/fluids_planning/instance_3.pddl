;this instance is solvable by opening twice the valve a and b and executing open valve b
(define 
    (problem instance1)
    (:domain fluids)
    (:init
        (= (a) 0)
        (= (b) 5)
        (= (c) 10)
        (true)
    )
    (:goal 
        (= (a) -1)
    )
)
