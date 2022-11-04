(define (problem problem_name) (:domain domainname)
(:init
    (a)
    (= (x) 0)
    (= (y) 0)
    ;todo: put the initial state's facts and numeric values here
)

(:goal (and
    (> (+ x y) 0)
    ;todo: put the goal condition here
))

;un-comment the following line if metric is needed
;(:metric minimize (???))
)
