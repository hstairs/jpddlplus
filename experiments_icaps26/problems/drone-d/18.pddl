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
	loc22 - location
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
	(= (zl loc0) 3)
	(= (xl loc1) 2)
	(= (yl loc1) 1)
	(= (zl loc1) 3)
	(= (xl loc2) 0)
	(= (yl loc2) 3)
	(= (zl loc2) 3)
	(= (xl loc3) 1)
	(= (yl loc3) 3)
	(= (zl loc3) 3)
	(= (xl loc4) 0)
	(= (yl loc4) 2)
	(= (zl loc4) 1)
	(= (xl loc5) 3)
	(= (yl loc5) 0)
	(= (zl loc5) 0)
	(= (xl loc6) 1)
	(= (yl loc6) 1)
	(= (zl loc6) 3)
	(= (xl loc7) 0)
	(= (yl loc7) 1)
	(= (zl loc7) 2)
	(= (xl loc8) 0)
	(= (yl loc8) 3)
	(= (zl loc8) 2)
	(= (xl loc9) 3)
	(= (yl loc9) 3)
	(= (zl loc9) 1)
	(= (xl loc10) 0)
	(= (yl loc10) 2)
	(= (zl loc10) 0)
	(= (xl loc11) 0)
	(= (yl loc11) 0)
	(= (zl loc11) 0)
	(= (xl loc12) 2)
	(= (yl loc12) 3)
	(= (zl loc12) 2)
	(= (xl loc13) 0)
	(= (yl loc13) 2)
	(= (zl loc13) 3)
	(= (xl loc14) 1)
	(= (yl loc14) 1)
	(= (zl loc14) 2)
	(= (xl loc15) 3)
	(= (yl loc15) 0)
	(= (zl loc15) 2)
	(= (xl loc16) 0)
	(= (yl loc16) 1)
	(= (zl loc16) 1)
	(= (xl loc17) 3)
	(= (yl loc17) 1)
	(= (zl loc17) 0)
	(= (xl loc18) 1)
	(= (yl loc18) 2)
	(= (zl loc18) 3)
	(= (xl loc19) 3)
	(= (yl loc19) 3)
	(= (zl loc19) 3)
	(= (xl loc20) 3)
	(= (yl loc20) 3)
	(= (zl loc20) 0)
	(= (xl loc21) 0)
	(= (yl loc21) 2)
	(= (zl loc21) 2)
	(= (xl loc22) 1)
	(= (yl loc22) 0)
	(= (zl loc22) 2)
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
	(visited loc22)
	(<= (x) 0.2)
	(<= (y) 0.2)
	(<= (z) 0.2)
))
)

