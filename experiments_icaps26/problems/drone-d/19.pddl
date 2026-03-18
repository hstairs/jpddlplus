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
	loc23 - location
	loc24 - location
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
	(= (xl loc1) 0)
	(= (yl loc1) 1)
	(= (zl loc1) 3)
	(= (xl loc2) 3)
	(= (yl loc2) 2)
	(= (zl loc2) 1)
	(= (xl loc3) 3)
	(= (yl loc3) 1)
	(= (zl loc3) 2)
	(= (xl loc4) 3)
	(= (yl loc4) 3)
	(= (zl loc4) 2)
	(= (xl loc5) 0)
	(= (yl loc5) 0)
	(= (zl loc5) 1)
	(= (xl loc6) 1)
	(= (yl loc6) 0)
	(= (zl loc6) 1)
	(= (xl loc7) 3)
	(= (yl loc7) 0)
	(= (zl loc7) 3)
	(= (xl loc8) 3)
	(= (yl loc8) 0)
	(= (zl loc8) 0)
	(= (xl loc9) 1)
	(= (yl loc9) 1)
	(= (zl loc9) 3)
	(= (xl loc10) 2)
	(= (yl loc10) 1)
	(= (zl loc10) 2)
	(= (xl loc11) 3)
	(= (yl loc11) 1)
	(= (zl loc11) 1)
	(= (xl loc12) 1)
	(= (yl loc12) 3)
	(= (zl loc12) 2)
	(= (xl loc13) 1)
	(= (yl loc13) 1)
	(= (zl loc13) 2)
	(= (xl loc14) 2)
	(= (yl loc14) 0)
	(= (zl loc14) 3)
	(= (xl loc15) 2)
	(= (yl loc15) 1)
	(= (zl loc15) 1)
	(= (xl loc16) 1)
	(= (yl loc16) 2)
	(= (zl loc16) 0)
	(= (xl loc17) 3)
	(= (yl loc17) 1)
	(= (zl loc17) 3)
	(= (xl loc18) 1)
	(= (yl loc18) 3)
	(= (zl loc18) 1)
	(= (xl loc19) 3)
	(= (yl loc19) 1)
	(= (zl loc19) 0)
	(= (xl loc20) 1)
	(= (yl loc20) 2)
	(= (zl loc20) 3)
	(= (xl loc21) 0)
	(= (yl loc21) 0)
	(= (zl loc21) 2)
	(= (xl loc22) 2)
	(= (yl loc22) 3)
	(= (zl loc22) 1)
	(= (xl loc23) 0)
	(= (yl loc23) 2)
	(= (zl loc23) 2)
	(= (xl loc24) 1)
	(= (yl loc24) 0)
	(= (zl loc24) 2)
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
	(visited loc23)
	(visited loc24)
	(<= (x) 0.2)
	(<= (y) 0.2)
	(<= (z) 0.2)
))
)

