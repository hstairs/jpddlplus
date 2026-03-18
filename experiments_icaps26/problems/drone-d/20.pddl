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
	loc25 - location
	loc26 - location
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
	(= (yl loc0) 0)
	(= (zl loc0) 2)
	(= (xl loc1) 2)
	(= (yl loc1) 2)
	(= (zl loc1) 2)
	(= (xl loc2) 0)
	(= (yl loc2) 1)
	(= (zl loc2) 3)
	(= (xl loc3) 0)
	(= (yl loc3) 3)
	(= (zl loc3) 0)
	(= (xl loc4) 0)
	(= (yl loc4) 3)
	(= (zl loc4) 3)
	(= (xl loc5) 1)
	(= (yl loc5) 2)
	(= (zl loc5) 2)
	(= (xl loc6) 1)
	(= (yl loc6) 3)
	(= (zl loc6) 3)
	(= (xl loc7) 1)
	(= (yl loc7) 3)
	(= (zl loc7) 0)
	(= (xl loc8) 2)
	(= (yl loc8) 3)
	(= (zl loc8) 0)
	(= (xl loc9) 2)
	(= (yl loc9) 3)
	(= (zl loc9) 3)
	(= (xl loc10) 3)
	(= (yl loc10) 0)
	(= (zl loc10) 3)
	(= (xl loc11) 1)
	(= (yl loc11) 1)
	(= (zl loc11) 3)
	(= (xl loc12) 1)
	(= (yl loc12) 2)
	(= (zl loc12) 1)
	(= (xl loc13) 3)
	(= (yl loc13) 0)
	(= (zl loc13) 1)
	(= (xl loc14) 0)
	(= (yl loc14) 0)
	(= (zl loc14) 3)
	(= (xl loc15) 0)
	(= (yl loc15) 0)
	(= (zl loc15) 0)
	(= (xl loc16) 2)
	(= (yl loc16) 3)
	(= (zl loc16) 2)
	(= (xl loc17) 1)
	(= (yl loc17) 0)
	(= (zl loc17) 3)
	(= (xl loc18) 2)
	(= (yl loc18) 0)
	(= (zl loc18) 0)
	(= (xl loc19) 2)
	(= (yl loc19) 0)
	(= (zl loc19) 3)
	(= (xl loc20) 2)
	(= (yl loc20) 2)
	(= (zl loc20) 0)
	(= (xl loc21) 2)
	(= (yl loc21) 1)
	(= (zl loc21) 1)
	(= (xl loc22) 3)
	(= (yl loc22) 1)
	(= (zl loc22) 3)
	(= (xl loc23) 3)
	(= (yl loc23) 1)
	(= (zl loc23) 0)
	(= (xl loc24) 3)
	(= (yl loc24) 3)
	(= (zl loc24) 3)
	(= (xl loc25) 0)
	(= (yl loc25) 2)
	(= (zl loc25) 2)
	(= (xl loc26) 1)
	(= (yl loc26) 1)
	(= (zl loc26) 1)
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
	(visited loc25)
	(visited loc26)
	(<= (x) 0.2)
	(<= (y) 0.2)
	(<= (z) 0.2)
))
)

