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
	supplier4 - supplier
	customer0 - customer
	customer1 - customer
	customer2 - customer
	customer3 - customer
	customer4 - customer
	workshop0 - workshop
	workshop1 - workshop
	workshop2 - workshop
	workshop3 - workshop
	workshop4 - workshop
)


(:init
	(can_produce_A workshop0)
	(can_produce_B workshop1)
	(can_produce_C workshop3)
	(can_produce_F workshop0)
	(can_produce_H workshop2)
	(can_produce_K workshop1)
	(can_produce_L workshop1)
	(can_produce_M workshop3)
	(can_supply_D supplier0)
	(can_supply_E supplier1)
	(can_supply_G supplier1)
	(can_supply_I supplier4)
	(can_supply_J supplier4)
	(can_supply_N supplier2)
	(can_supply_O supplier2)
	(can_supply_P supplier3)
	(can_supply_Q supplier0)
	(can_supply_R supplier2)
	(can_supply_S supplier4)
	(at workshop2)
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
	(= (item-goal A) 4)
	(= (item-goal B) 1)
	(= (item-goal C) 2)
	(= (item-goal D) 4)
	(= (item-goal E) 1)
	(= (item-goal F) 3)
	(= (item-goal G) 11)
	(= (item-goal H) 5)
	(= (item-goal I) 2)
	(= (item-goal J) 2)
	(= (item-goal K) 6)
	(= (item-goal L) 4)
	(= (item-goal M) 9)
	(= (item-goal N) 9)
	(= (item-goal O) 7)
	(= (item-goal P) 11)
	(= (item-goal Q) 13)
	(= (item-goal R) 4)
	(= (item-goal S) 2)
)
(:goal (and
	(delivered N customer0)
	(delivered L customer1)
	(delivered J customer2)
	(delivered F customer3)
	(delivered B customer4)
))
)

