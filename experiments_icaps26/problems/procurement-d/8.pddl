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
	(can_produce_A workshop0)
	(can_produce_B workshop0)
	(can_produce_C workshop1)
	(can_produce_F workshop2)
	(can_produce_H workshop0)
	(can_produce_K workshop3)
	(can_produce_L workshop1)
	(can_produce_M workshop1)
	(can_supply_D supplier1)
	(can_supply_E supplier1)
	(can_supply_G supplier1)
	(can_supply_I supplier3)
	(can_supply_J supplier1)
	(can_supply_N supplier2)
	(can_supply_O supplier0)
	(can_supply_P supplier3)
	(can_supply_Q supplier2)
	(can_supply_R supplier3)
	(can_supply_S supplier0)
	(at supplier3)
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
	(= (item-goal A) 9)
	(= (item-goal B) 5)
	(= (item-goal C) 10)
	(= (item-goal D) 1)
	(= (item-goal E) 2)
	(= (item-goal F) 2)
	(= (item-goal G) 4)
	(= (item-goal H) 6)
	(= (item-goal I) 9)
	(= (item-goal J) 1)
	(= (item-goal K) 7)
	(= (item-goal L) 10)
	(= (item-goal M) 11)
	(= (item-goal N) 11)
	(= (item-goal O) 11)
	(= (item-goal P) 12)
	(= (item-goal Q) 14)
	(= (item-goal R) 5)
	(= (item-goal S) 14)
)
(:goal (and
	(delivered J customer0)
	(delivered C customer1)
	(delivered S customer2)
	(delivered H customer3)
))
)

