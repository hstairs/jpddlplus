(define (problem drone)
(:domain drone)
(:objects 
	loc0 - location
	loc1 - location
	loc2 - location
	loc3 - location
	loc4 - location
	loc5 - location
	loc6 - location
	loc7 - location
	loc8 - location
	loc9 - location
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
	(= (yl loc0) 1)
	(= (zl loc0) 1)
	(= (xl loc1) 1)
	(= (yl loc1) 1)
	(= (zl loc1) 0)
	(= (xl loc2) 2)
	(= (yl loc2) 2)
	(= (zl loc2) 2)
	(= (xl loc3) 0)
	(= (yl loc3) 0)
	(= (zl loc3) 0)
	(= (xl loc4) 2)
	(= (yl loc4) 1)
	(= (zl loc4) 2)
	(= (xl loc5) 2)
	(= (yl loc5) 0)
	(= (zl loc5) 0)
	(= (xl loc6) 0)
	(= (yl loc6) 2)
	(= (zl loc6) 2)
	(= (xl loc7) 0)
	(= (yl loc7) 0)
	(= (zl loc7) 1)
	(= (xl loc8) 1)
	(= (yl loc8) 1)
	(= (zl loc8) 1)
	(= (xl loc9) 0)
	(= (yl loc9) 1)
	(= (zl loc9) 1)
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
	(visited loc6)
	(visited loc7)
	(visited loc8)
	(visited loc9)
	(<= (x) 0.2)
	(<= (y) 0.2)
	(<= (z) 0.2)
))
)

