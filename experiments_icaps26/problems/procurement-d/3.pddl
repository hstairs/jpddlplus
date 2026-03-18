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
	customer0 - customer
	customer1 - customer
	workshop0 - workshop
	workshop1 - workshop
)


(:init
	(can_produce_A workshop0)
	(can_produce_B workshop0)
	(can_produce_C workshop0)
	(can_produce_F workshop1)
	(can_produce_H workshop1)
	(can_produce_K workshop1)
	(can_produce_L workshop1)
	(can_produce_M workshop0)
	(can_supply_D supplier1)
	(can_supply_E supplier1)
	(can_supply_G supplier0)
	(can_supply_I supplier1)
	(can_supply_J supplier0)
	(can_supply_N supplier0)
	(can_supply_O supplier0)
	(can_supply_P supplier1)
	(can_supply_Q supplier0)
	(can_supply_R supplier0)
	(can_supply_S supplier0)
	(at customer1)
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
	(= (item-goal B) 3)
	(= (item-goal C) 1)
	(= (item-goal D) 6)
	(= (item-goal E) 10)
	(= (item-goal F) 8)
	(= (item-goal G) 4)
	(= (item-goal H) 11)
	(= (item-goal I) 13)
	(= (item-goal J) 2)
	(= (item-goal K) 5)
	(= (item-goal L) 1)
	(= (item-goal M) 11)
	(= (item-goal N) 11)
	(= (item-goal O) 13)
	(= (item-goal P) 13)
	(= (item-goal Q) 9)
	(= (item-goal R) 8)
	(= (item-goal S) 11)
)
(:goal (and
	(delivered Q customer0)
	(delivered C customer1)
))
)

