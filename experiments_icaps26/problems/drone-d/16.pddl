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
	loc17 - location
	loc18 - location
	loc19 - location
)

(:init
	(= (x) 0)
	(= (y) 0)
	(= (z) 0)
	(= (min_x) 0)
	(= (min_y) 0)
	(= (min_z) 0)
	(= (max_x) 3)
	(= (max_y) 3)
	(= (max_z) 3)
	(= (xl loc0) 0)
	(= (yl loc0) 1)
	(= (zl loc0) 0)
	(= (xl loc1) 2)
	(= (yl loc1) 1)
	(= (zl loc1) 0)
	(= (xl loc2) 0)
	(= (yl loc2) 3)
	(= (zl loc2) 0)
	(= (xl loc3) 2)
	(= (yl loc3) 1)
	(= (zl loc3) 3)
	(= (xl loc4) 1)
	(= (yl loc4) 3)
	(= (zl loc4) 0)
	(= (xl loc5) 3)
	(= (yl loc5) 3)
	(= (zl loc5) 2)
	(= (xl loc6) 1)
	(= (yl loc6) 0)
	(= (zl loc6) 1)
	(= (xl loc7) 2)
	(= (yl loc7) 3)
	(= (zl loc7) 3)
	(= (xl loc8) 3)
	(= (yl loc8) 1)
	(= (zl loc8) 1)
	(= (xl loc9) 3)
	(= (yl loc9) 2)
	(= (zl loc9) 0)
	(= (xl loc10) 0)
	(= (yl loc10) 0)
	(= (zl loc10) 3)
	(= (xl loc11) 0)
	(= (yl loc11) 2)
	(= (zl loc11) 3)
	(= (xl loc12) 3)
	(= (yl loc12) 0)
	(= (zl loc12) 2)
	(= (xl loc13) 2)
	(= (yl loc13) 0)
	(= (zl loc13) 0)
	(= (xl loc14) 2)
	(= (yl loc14) 0)
	(= (zl loc14) 3)
	(= (xl loc15) 2)
	(= (yl loc15) 2)
	(= (zl loc15) 0)
	(= (xl loc16) 3)
	(= (yl loc16) 3)
	(= (zl loc16) 3)
	(= (xl loc17) 0)
	(= (yl loc17) 0)
	(= (zl loc17) 2)
	(= (xl loc18) 3)
	(= (yl loc18) 0)
	(= (zl loc18) 1)
	(= (xl loc19) 1)
	(= (yl loc19) 1)
	(= (zl loc19) 1)
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
	(visited loc17)
	(visited loc18)
	(visited loc19)
	(<= (x) 0.2)
	(<= (y) 0.2)
	(<= (z) 0.2)
))
)

