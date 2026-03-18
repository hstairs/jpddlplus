(define (problem procurement)
(:domain procurement)
(:objects 
	A - itemA
	B - itemB
	C - itemC
	D - itemD
	E - itemE
	F - itemF
	G - itemG
	H - itemH
	I - itemI
	J - itemJ
	K - itemK
	L - itemL
	M - itemM
	N - itemN
	O - itemO
	P - itemP
	Q - itemQ
	R - itemR
	S - itemS
	supplier0 - supplier
	supplier1 - supplier
	supplier2 - supplier
	supplier3 - supplier
	customer0 - customer
	customer1 - customer
	customer2 - customer
	customer3 - customer
	workshop0 - workshop
	workshop1 - workshop
	workshop2 - workshop
	workshop3 - workshop
)


(:init
	(can_produce_A workshop1)
	(can_produce_B workshop3)
	(can_produce_C workshop0)
	(can_produce_F workshop0)
	(can_produce_H workshop0)
	(can_produce_K workshop1)
	(can_produce_L workshop1)
	(can_produce_M workshop2)
	(can_supply_D supplier0)
	(can_supply_E supplier1)
	(can_supply_G supplier0)
	(can_supply_I supplier3)
	(can_supply_J supplier1)
	(can_supply_N supplier3)
	(can_supply_O supplier3)
	(can_supply_P supplier3)
	(can_supply_Q supplier2)
	(can_supply_R supplier3)
	(can_supply_S supplier3)
	(at supplier1)
	(= (stock A) 0)
	(= (stock B) 0)
	(= (stock C) 0)
	(= (stock D) 0)
	(= (stock E) 0)
	(= (stock F) 0)
	(= (stock G) 0)
	(= (stock H) 0)
	(= (stock I) 0)
	(= (stock J) 0)
	(= (stock K) 0)
	(= (stock L) 0)
	(= (stock M) 0)
	(= (stock N) 0)
	(= (stock O) 0)
	(= (stock P) 0)
	(= (stock Q) 0)
	(= (stock R) 0)
	(= (stock S) 0)
	(= (item-goal A) 12)
	(= (item-goal B) 8)
	(= (item-goal C) 7)
	(= (item-goal D) 14)
	(= (item-goal E) 10)
	(= (item-goal F) 3)
	(= (item-goal G) 13)
	(= (item-goal H) 8)
	(= (item-goal I) 8)
	(= (item-goal J) 5)
	(= (item-goal K) 13)
	(= (item-goal L) 13)
	(= (item-goal M) 11)
	(= (item-goal N) 2)
	(= (item-goal O) 2)
	(= (item-goal P) 8)
	(= (item-goal Q) 13)
	(= (item-goal R) 12)
	(= (item-goal S) 6)
)
(:goal (and
	(delivered M customer0)
	(delivered J customer1)
	(delivered R customer2)
	(delivered I customer3)
))
)

