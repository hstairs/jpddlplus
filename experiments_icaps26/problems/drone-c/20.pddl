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
	loc10 - location
	loc11 - location
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
	(= (xl loc0) 0)
	(= (yl loc0) 2)
	(= (zl loc0) 1)
	(= (xl loc1) 2)
	(= (yl loc1) 0)
	(= (zl loc1) 2)
	(= (xl loc2) 1)
	(= (yl loc2) 0)
	(= (zl loc2) 1)
	(= (xl loc3) 0)
	(= (yl loc3) 1)
	(= (zl loc3) 0)
	(= (xl loc4) 0)
	(= (yl loc4) 2)
	(= (zl loc4) 0)
	(= (xl loc5) 1)
	(= (yl loc5) 1)
	(= (zl loc5) 2)
	(= (xl loc6) 1)
	(= (yl loc6) 0)
	(= (zl loc6) 0)
	(= (xl loc7) 2)
	(= (yl loc7) 1)
	(= (zl loc7) 2)
	(= (xl loc8) 0)
	(= (yl loc8) 0)
	(= (zl loc8) 2)
	(= (xl loc9) 0)
	(= (yl loc9) 0)
	(= (zl loc9) 1)
	(= (xl loc10) 1)
	(= (yl loc10) 1)
	(= (zl loc10) 1)
	(= (xl loc11) 0)
	(= (yl loc11) 1)
	(= (zl loc11) 1)
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
	(visited loc10)
	(visited loc11)
	(<= (x) 0.2)
	(<= (y) 0.2)
	(<= (z) 0.2)
))
)

