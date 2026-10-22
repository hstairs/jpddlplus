(define (problem problem_name) (:domain domain_name)

(:init
    (= (x) 0)
    ;todo: put the initial state's facts and numeric values here
)

(:goal (and
    ;todo: put the goal condition here
    (>= (x) 10)
))

;; with d.pddl you need 1 inc_x_a and 3 inc_x. So h* is 4 but h_rmax should be 3.3333..

;; with d2.pddl you need  1 set_a 1 set_b 1 set_c and 4 inc_x. So h* is 7. hrmax gives 3.3333, hrmaxssnp gives 6.3 :)

)
