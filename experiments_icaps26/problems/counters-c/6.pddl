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
	counter14 - counter
	counter15 - counter
	counter16 - counter
	counter17 - counter
)

(:init
	(= (max_int) 36)
	(= (value counter0) 1)
	(= (value counter1) 35)
	(= (value counter2) 29)
	(= (value counter3) 11)
	(= (value counter4) 33)
	(= (value counter5) 32)
	(= (value counter6) 30)
	(= (value counter7) 11)
	(= (value counter8) 18)
	(= (value counter9) 29)
	(= (value counter10) 30)
	(= (value counter11) 22)
	(= (value counter12) 0)
	(= (value counter13) 28)
	(= (value counter14) 5)
	(= (value counter15) 24)
	(= (value counter16) 16)
	(= (value counter17) 2)
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
	(<= (+ (value counter13) 1) (value counter14))
	(<= (+ (value counter14) 1) (value counter15))
	(<= (+ (value counter15) 1) (value counter16))
	(<= (+ (value counter16) 1) (value counter17))
))
)

