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
	customer0 - customer
	customer1 - customer
	customer2 - customer
	workshop0 - workshop
	workshop1 - workshop
	workshop2 - workshop
)


(:init
	(can_produce_A workshop0)
	(can_produce_B workshop1)
	(can_produce_C workshop1)
	(can_produce_F workshop0)
	(can_produce_H workshop2)
	(can_produce_K workshop1)
	(can_produce_L workshop2)
	(can_produce_M workshop1)
	(can_supply_D supplier0)
	(can_supply_E supplier1)
	(can_supply_G supplier0)
	(can_supply_I supplier0)
	(can_supply_J supplier0)
	(can_supply_N supplier0)
	(can_supply_O supplier1)
	(can_supply_P supplier2)
	(can_supply_Q supplier0)
	(can_supply_R supplier2)
	(can_supply_S supplier2)
	(at customer0)
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
	(= (item-goal A) 5)
	(= (item-goal B) 11)
	(= (item-goal C) 10)
	(= (item-goal D) 13)
	(= (item-goal E) 8)
	(= (item-goal F) 9)
	(= (item-goal G) 9)
	(= (item-goal H) 12)
	(= (item-goal I) 1)
	(= (item-goal J) 9)
	(= (item-goal K) 1)
	(= (item-goal L) 8)
	(= (item-goal M) 12)
	(= (item-goal N) 13)
	(= (item-goal O) 7)
	(= (item-goal P) 11)
	(= (item-goal Q) 9)
	(= (item-goal R) 9)
	(= (item-goal S) 1)
)
(:goal (and
	(delivered C customer0)
	(delivered A customer1)
	(delivered P customer2)
))
)

