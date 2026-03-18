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
	loc12 - location
	loc13 - location
	loc14 - location
	loc15 - location
	loc16 - location
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
	(= (xl loc0) 1)
	(= (yl loc0) 2)
	(= (zl loc0) 1)
	(= (xl loc1) 2)
	(= (yl loc1) 1)
	(= (zl loc1) 1)
	(= (xl loc2) 0)
	(= (yl loc2) 2)
	(= (zl loc2) 1)
	(= (xl loc3) 1)
	(= (yl loc3) 0)
	(= (zl loc3) 1)
	(= (xl loc4) 2)
	(= (yl loc4) 0)
	(= (zl loc4) 2)
	(= (xl loc5) 1)
	(= (yl loc5) 1)
	(= (zl loc5) 0)
	(= (xl loc6) 0)
	(= (yl loc6) 1)
	(= (zl loc6) 0)
	(= (xl loc7) 2)
	(= (yl loc7) 2)
	(= (zl loc7) 2)
	(= (xl loc8) 2)
	(= (yl loc8) 1)
	(= (zl loc8) 0)
	(= (xl loc9) 0)
	(= (yl loc9) 0)
	(= (zl loc9) 0)
	(= (xl loc10) 1)
	(= (yl loc10) 0)
	(= (zl loc10) 0)
	(= (xl loc11) 1)
	(= (yl loc11) 2)
	(= (zl loc11) 2)
	(= (xl loc12) 2)
	(= (yl loc12) 2)
	(= (zl loc12) 1)
	(= (xl loc13) 2)
	(= (yl loc13) 0)
	(= (zl loc13) 0)
	(= (xl loc14) 0)
	(= (yl loc14) 0)
	(= (zl loc14) 1)
	(= (xl loc15) 1)
	(= (yl loc15) 1)
	(= (zl loc15) 1)
	(= (xl loc16) 0)
	(= (yl loc16) 1)
	(= (zl loc16) 1)
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
	(visited loc12)
	(visited loc13)
	(visited loc14)
	(visited loc15)
	(visited loc16)
	(<= (x) 0.2)
	(<= (y) 0.2)
	(<= (z) 0.2)
))
)

