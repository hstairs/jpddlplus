(define (problem blockgrouping)
(:domain blockgrouping)
(:objects 
	b0 - block
	b1 - block
	b2 - block
	b3 - block
	b4 - block
)

(:init
	(= (x b0) 18)
	(= (y b0) 3)
	(= (x b1) 16)
	(= (y b1) 6)
	(= (x b2) 17)
	(= (y b2) 1)
	(= (x b3) 12)
	(= (y b3) 13)
	(= (x b4) 19)
	(= (y b4) 8)
	(= (max_x) 20)
	(= (min_x) 0)
	(= (max_y) 20)
	(= (min_y) 0)
)
(:goal (and
	(<= (- (x b0) (x b3)) 0.2)
	(>= (- (x b0) (x b3)) -0.2)
	(<= (- (y b0) (y b3)) 0.2)
	(>= (- (y b0) (y b3)) -0.2)

	(<= (- (x b0) (x b4)) 0.2)
	(>= (- (x b0) (x b4)) -0.2)
	(<= (- (y b0) (y b4)) 0.2)
	(>= (- (y b0) (y b4)) -0.2)

	(<= (- (x b1) (x b2)) 0.2)
	(>= (- (x b1) (x b2)) -0.2)
	(<= (- (y b1) (y b2)) 0.2)
	(>= (- (y b1) (y b2)) -0.2)

	(<= (- (x b3) (x b4)) 0.2)
	(>= (- (x b3) (x b4)) -0.2)
	(<= (- (y b3) (y b4)) 0.2)
	(>= (- (y b3) (y b4)) -0.2)

))
)

