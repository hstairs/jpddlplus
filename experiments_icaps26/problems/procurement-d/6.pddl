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
	(can_produce_C workshop0)
	(can_produce_F workshop2)
	(can_produce_H workshop2)
	(can_produce_K workshop0)
	(can_produce_L workshop2)
	(can_produce_M workshop0)
	(can_supply_D supplier1)
	(can_supply_E supplier0)
	(can_supply_G supplier1)
	(can_supply_I supplier1)
	(can_supply_J supplier2)
	(can_supply_N supplier2)
	(can_supply_O supplier2)
	(can_supply_P supplier1)
	(can_supply_Q supplier1)
	(can_supply_R supplier1)
	(can_supply_S supplier2)
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
	(= (item-goal A) 8)
	(= (item-goal B) 8)
	(= (item-goal C) 7)
	(= (item-goal D) 2)
	(= (item-goal E) 9)
	(= (item-goal F) 13)
	(= (item-goal G) 5)
	(= (item-goal H) 13)
	(= (item-goal I) 13)
	(= (item-goal J) 10)
	(= (item-goal K) 11)
	(= (item-goal L) 6)
	(= (item-goal M) 4)
	(= (item-goal N) 8)
	(= (item-goal O) 13)
	(= (item-goal P) 14)
	(= (item-goal Q) 11)
	(= (item-goal R) 7)
	(= (item-goal S) 7)
)
(:goal (and
	(delivered C customer0)
	(delivered A customer1)
	(delivered H customer2)
))
)

