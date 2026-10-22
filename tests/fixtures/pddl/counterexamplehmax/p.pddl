(define (problem problem_name) (:domain domain_name)
(:objects
)

(:init
    (= (x) 0)
    ;todo: put the initial state's facts and numeric values here
)

(:goal (or
    (>= (x) 10)
    (p2)
    ;todo: put the goal condition here
))

;un-comment the following line if metric is needed
(:metric minimize (cost))
)
