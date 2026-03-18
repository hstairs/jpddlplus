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
	(can_produce_A workshop1)
	(can_produce_B workshop1)
	(can_produce_C workshop1)
	(can_produce_F workshop0)
	(can_produce_H workshop0)
	(can_produce_K workshop0)
	(can_produce_L workshop1)
	(can_produce_M workshop0)
	(can_supply_D supplier0)
	(can_supply_E supplier1)
	(can_supply_G supplier0)
	(can_supply_I supplier0)
	(can_supply_J supplier1)
	(can_supply_N supplier1)
	(can_supply_O supplier1)
	(can_supply_P supplier0)
	(can_supply_Q supplier1)
	(can_supply_R supplier1)
	(can_supply_S supplier0)
	(at workshop0)
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
	(= (item-goal B) 7)
	(= (item-goal C) 12)
	(= (item-goal D) 7)
	(= (item-goal E) 8)
	(= (item-goal F) 10)
	(= (item-goal G) 13)
	(= (item-goal H) 3)
	(= (item-goal I) 8)
	(= (item-goal J) 10)
	(= (item-goal K) 1)
	(= (item-goal L) 4)
	(= (item-goal M) 4)
	(= (item-goal N) 12)
	(= (item-goal O) 7)
	(= (item-goal P) 3)
	(= (item-goal Q) 11)
	(= (item-goal R) 1)
	(= (item-goal S) 14)
)
(:goal (and
	(delivered Q customer0)
	(delivered P customer1)
))
)

