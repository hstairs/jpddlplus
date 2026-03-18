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
)
(:init
	(= (max_int) 30)
	(= (value counter0) 12)
	(= (value counter1) 8)
	(= (value counter2) 22)
	(= (value counter3) 7)
	(= (value counter4) 18)
	(= (value counter5) 26)
	(= (value counter6) 7)
	(= (value counter7) 24)
	(= (value counter8) 8)
	(= (value counter9) 25)
	(= (value counter10) 14)
	(= (value counter11) 0)
	(= (value counter12) 16)
	(= (value counter13) 7)
	(= (value counter14) 8)
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
))
)

