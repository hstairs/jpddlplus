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
	counter18 - counter
	counter19 - counter
)

(:init
	(= (max_int) 40)
	(= (value counter0) 18)
	(= (value counter1) 0)
	(= (value counter2) 13)
	(= (value counter3) 30)
	(= (value counter4) 29)
	(= (value counter5) 2)
	(= (value counter6) 33)
	(= (value counter7) 23)
	(= (value counter8) 6)
	(= (value counter9) 4)
	(= (value counter10) 24)
	(= (value counter11) 28)
	(= (value counter12) 23)
	(= (value counter13) 36)
	(= (value counter14) 26)
	(= (value counter15) 29)
	(= (value counter16) 30)
	(= (value counter17) 4)
	(= (value counter18) 13)
	(= (value counter19) 1)
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
	(<= (+ (value counter17) 1) (value counter18))
	(<= (+ (value counter18) 1) (value counter19))
))
)

