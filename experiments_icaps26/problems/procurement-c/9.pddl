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
	(can_produce_A workshop4)
	(can_produce_B workshop3)
	(can_produce_C workshop2)
	(can_produce_F workshop4)
	(can_produce_H workshop1)
	(can_produce_K workshop2)
	(can_produce_L workshop0)
	(can_produce_M workshop3)
	(can_supply_D supplier1)
	(can_supply_E supplier0)
	(can_supply_G supplier2)
	(can_supply_I supplier4)
	(can_supply_J supplier3)
	(can_supply_N supplier0)
	(can_supply_O supplier2)
	(can_supply_P supplier2)
	(can_supply_Q supplier4)
	(can_supply_R supplier1)
	(can_supply_S supplier3)
	(at supplier4)
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
	(= (item-goal A) 8)
	(= (item-goal B) 10)
	(= (item-goal C) 8)
	(= (item-goal D) 8)
	(= (item-goal E) 10)
	(= (item-goal F) 7)
	(= (item-goal G) 14)
	(= (item-goal H) 10)
	(= (item-goal I) 3)
	(= (item-goal J) 2)
	(= (item-goal K) 1)
	(= (item-goal L) 1)
	(= (item-goal M) 4)
	(= (item-goal N) 6)
	(= (item-goal O) 4)
	(= (item-goal P) 7)
	(= (item-goal Q) 2)
	(= (item-goal R) 1)
	(= (item-goal S) 3)
)
(:goal (and
	(delivered R customer0)
	(delivered Q customer1)
	(delivered F customer2)
	(delivered B customer3)
	(delivered C customer4)
))
)

