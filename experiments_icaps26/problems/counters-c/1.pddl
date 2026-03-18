(define (problem counters)
(:domain counters)
(:objects 
	counter0 - counter
	counter1 - counter
	counter2 - counter
	counter3 - counter
	counter4 - counter
)

(:init
	(= (max_int) 10)
	(= (value counter0) 7)
	(= (value counter1) 5)
	(= (value counter2) 8)
	(= (value counter3) 2)
	(= (value counter4) 2)
)
(:goal (and
	(<= (+ (value counter0) 1) (value counter1))
	(<= (+ (value counter1) 1) (value counter2))
	(<= (+ (value counter2) 1) (value counter3))
	(<= (+ (value counter3) 1) (value counter4))
))
)

