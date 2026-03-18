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
	(can_produce_A workshop3)
	(can_produce_B workshop1)
	(can_produce_C workshop1)
	(can_produce_F workshop1)
	(can_produce_H workshop1)
	(can_produce_K workshop2)
	(can_produce_L workshop0)
	(can_produce_M workshop3)
	(can_supply_D supplier3)
	(can_supply_E supplier2)
	(can_supply_G supplier0)
	(can_supply_I supplier3)
	(can_supply_J supplier0)
	(can_supply_N supplier1)
	(can_supply_O supplier3)
	(can_supply_P supplier0)
	(can_supply_Q supplier1)
	(can_supply_R supplier3)
	(can_supply_S supplier3)
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
	(= (item-goal A) 6)
	(= (item-goal B) 5)
	(= (item-goal C) 4)
	(= (item-goal D) 13)
	(= (item-goal E) 9)
	(= (item-goal F) 2)
	(= (item-goal G) 13)
	(= (item-goal H) 4)
	(= (item-goal I) 8)
	(= (item-goal J) 8)
	(= (item-goal K) 1)
	(= (item-goal L) 5)
	(= (item-goal M) 4)
	(= (item-goal N) 10)
	(= (item-goal O) 11)
	(= (item-goal P) 1)
	(= (item-goal Q) 3)
	(= (item-goal R) 10)
	(= (item-goal S) 12)
)
(:goal (and
	(delivered K customer0)
	(delivered S customer1)
	(delivered O customer2)
	(delivered M customer3)
))
)

