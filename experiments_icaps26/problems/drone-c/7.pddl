(define (problem drone)
(:domain drone)
(:objects 
	loc0 - location
	loc1 - location
	loc2 - location
	loc3 - location
	loc4 - location
	loc5 - location
)

(:init
	(= (x) 0)
	(= (y) 0)
	(= (z) 0)
	(= (min_x) 0)
	(= (min_y) 0)
	(= (min_z) 0)
	(= (max_x) 2)
	(= (max_y) 2)
	(= (max_z) 2)
	(= (xl loc0) 2)
	(= (yl loc0) 0)
	(= (zl loc0) 2)
	(= (xl loc1) 0)
	(= (yl loc1) 2)
	(= (zl loc1) 0)
	(= (xl loc2) 2)
	(= (yl loc2) 0)
	(= (zl loc2) 0)
	(= (xl loc3) 0)
	(= (yl loc3) 1)
	(= (zl loc3) 2)
	(= (xl loc4) 0)
	(= (yl loc4) 2)
	(= (zl loc4) 2)
	(= (xl loc5) 1)
	(= (yl loc5) 1)
	(= (zl loc5) 1)
	(= (battery-level) 47)
	(= (battery-level-full) 47)
)
(:goal (and
	(visited loc0)
	(visited loc1)
	(visited loc2)
	(visited loc3)
	(visited loc4)
	(visited loc5)
	(<= (x) 0.2)
	(<= (y) 0.2)
	(<= (z) 0.2)
))
)

