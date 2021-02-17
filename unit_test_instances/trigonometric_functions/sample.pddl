
(define 
    (problem instance1)
    (:domain testing)
    (:init
	    (a)
	    (goal)
	    (= (alpha) 0)
	    (= (max_alpha) 0.5)
	    (= (max_speed) 5)
	    (= (plane_length) 25)
        (= (alpha_incr) 0.01)
	    (= (v) 0)
	    (= (x) 0)
    )
    (:goal 
	    (and
           	(>= (x) 10))
           	(< (x) 10.5)
	    )
    )
)
