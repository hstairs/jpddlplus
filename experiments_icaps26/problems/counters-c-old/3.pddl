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
	counter10 - counter
	counter11 - counter
	counter12 - counter
	counter13 - counter
)

(:init
	(= (max_int) 28)
	(= (value counter0) 2)
	(= (value counter1) 29)
	(= (value counter2) 0)
	(= (value counter3) 9)
	(= (value counter4) 23)
	(= (value counter5) 13)
	(= (value counter6) 12)
	(= (value counter7) 20)
	(= (value counter8) 4)
	(= (value counter9) 4)
	(= (value counter10) 15)
	(= (value counter11) 0)
	(= (value counter12) 24)
	(= (value counter13) 15)
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
	(<= (+ (value counter9) 1) (value counter10))
	(<= (+ (value counter10) 1) (value counter11))
	(<= (+ (value counter11) 1) (value counter12))
	(<= (+ (value counter12) 1) (value counter13))
))
)

