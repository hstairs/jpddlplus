(define (problem counters)
(:domain counters)
(:objects 
	counter0 - counter
	counter1 - counter
	counter2 - counter
	counter3 - counter
	counter4 - counter
	counter5 - counter
	counter6 - counter
	counter7 - counter
	counter8 - counter
	counter9 - counter
)

(:init
	(= (max_int) 20)
	(= (value counter0) 8)
	(= (value counter1) 17)
	(= (value counter2) 19)
	(= (value counter3) 6)
	(= (value counter4) 2)
	(= (value counter5) 20)
	(= (value counter6) 17)
	(= (value counter7) 19)
	(= (value counter8) 19)
	(= (value counter9) 9)
)
(:goal (and
	(<= (+ (value counter0) 1) (value counter1))
	(<= (+ (value counter1) 1) (value counter2))
	(<= (+ (value counter2) 1) (value counter3))
	(<= (+ (value counter3) 1) (value counter4))
	(<= (+ (value counter4) 1) (value counter5))
	(<= (+ (value counter5) 1) (value counter6))
	(<= (+ (value counter6) 1) (value counter7))
	(<= (+ (value counter7) 1) (value counter8))
	(<= (+ (value counter8) 1) (value counter9))
))
)

