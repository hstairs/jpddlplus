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
	(can_produce_A workshop2)
	(can_produce_B workshop2)
	(can_produce_C workshop2)
	(can_produce_F workshop2)
	(can_produce_H workshop2)
	(can_produce_K workshop3)
	(can_produce_L workshop4)
	(can_produce_M workshop1)
	(can_supply_D supplier4)
	(can_supply_E supplier4)
	(can_supply_G supplier3)
	(can_supply_I supplier4)
	(can_supply_J supplier3)
	(can_supply_N supplier1)
	(can_supply_O supplier1)
	(can_supply_P supplier1)
	(can_supply_Q supplier3)
	(can_supply_R supplier4)
	(can_supply_S supplier2)
	(at customer2)
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
	(= (item-goal A) 14)
	(= (item-goal B) 12)
	(= (item-goal C) 1)
	(= (item-goal D) 4)
	(= (item-goal E) 13)
	(= (item-goal F) 6)
	(= (item-goal G) 5)
	(= (item-goal H) 2)
	(= (item-goal I) 9)
	(= (item-goal J) 10)
	(= (item-goal K) 10)
	(= (item-goal L) 11)
	(= (item-goal M) 8)
	(= (item-goal N) 10)
	(= (item-goal O) 2)
	(= (item-goal P) 10)
	(= (item-goal Q) 5)
	(= (item-goal R) 12)
	(= (item-goal S) 6)
)
(:goal (and
	(delivered S customer0)
	(delivered F customer1)
	(delivered C customer2)
	(delivered E customer3)
	(delivered N customer4)
))
)

