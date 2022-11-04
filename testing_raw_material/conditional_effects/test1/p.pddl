(define (problem instance1) (:domain cond_test1)


(:init
    ;todo: put the initial state's facts and numeric values here
    (= (total-cost) 0)
)

(:goal (and
    (g1)
    (g2)
))

;un-comment the following line if metric is needed
(:metric minimize total-cost)
)
