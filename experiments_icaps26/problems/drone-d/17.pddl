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
	loc20 - location
	loc21 - location
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
	(= (xl loc0) 2)
	(= (yl loc0) 1)
	(= (zl loc0) 0)
	(= (xl loc1) 0)
	(= (yl loc1) 1)
	(= (zl loc1) 3)
	(= (xl loc2) 2)
	(= (yl loc2) 1)
	(= (zl loc2) 3)
	(= (xl loc3) 1)
	(= (yl loc3) 3)
	(= (zl loc3) 3)
	(= (xl loc4) 3)
	(= (yl loc4) 1)
	(= (zl loc4) 2)
	(= (xl loc5) 1)
	(= (yl loc5) 3)
	(= (zl loc5) 0)
	(= (xl loc6) 3)
	(= (yl loc6) 3)
	(= (zl loc6) 2)
	(= (xl loc7) 1)
	(= (yl loc7) 0)
	(= (zl loc7) 1)
	(= (xl loc8) 1)
	(= (yl loc8) 1)
	(= (zl loc8) 0)
	(= (xl loc9) 3)
	(= (yl loc9) 0)
	(= (zl loc9) 3)
	(= (xl loc10) 1)
	(= (yl loc10) 1)
	(= (zl loc10) 3)
	(= (xl loc11) 2)
	(= (yl loc11) 0)
	(= (zl loc11) 1)
	(= (xl loc12) 2)
	(= (yl loc12) 2)
	(= (zl loc12) 1)
	(= (xl loc13) 0)
	(= (yl loc13) 3)
	(= (zl loc13) 2)
	(= (xl loc14) 1)
	(= (yl loc14) 2)
	(= (zl loc14) 1)
	(= (xl loc15) 0)
	(= (yl loc15) 2)
	(= (zl loc15) 0)
	(= (xl loc16) 0)
	(= (yl loc16) 0)
	(= (zl loc16) 0)
	(= (xl loc17) 2)
	(= (yl loc17) 2)
	(= (zl loc17) 3)
	(= (xl loc18) 1)
	(= (yl loc18) 2)
	(= (zl loc18) 0)
	(= (xl loc19) 3)
	(= (yl loc19) 2)
	(= (zl loc19) 2)
	(= (xl loc20) 1)
	(= (yl loc20) 2)
	(= (zl loc20) 3)
	(= (xl loc21) 3)
	(= (yl loc21) 0)
	(= (zl loc21) 1)
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
	(visited loc20)
	(visited loc21)
	(<= (x) 0.2)
	(<= (y) 0.2)
	(<= (z) 0.2)
))
)

