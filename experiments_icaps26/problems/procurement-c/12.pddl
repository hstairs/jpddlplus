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
	supplier5 - supplier
	customer0 - customer
	customer1 - customer
	customer2 - customer
	customer3 - customer
	customer4 - customer
	customer5 - customer
	workshop0 - workshop
	workshop1 - workshop
	workshop2 - workshop
	workshop3 - workshop
	workshop4 - workshop
	workshop5 - workshop
)


(:init
	(can_produce_A workshop0)
	(can_produce_B workshop2)
	(can_produce_C workshop4)
	(can_produce_F workshop3)
	(can_produce_H workshop5)
	(can_produce_K workshop3)
	(can_produce_L workshop3)
	(can_produce_M workshop2)
	(can_supply_D supplier5)
	(can_supply_E supplier4)
	(can_supply_G supplier4)
	(can_supply_I supplier0)
	(can_supply_J supplier1)
	(can_supply_N supplier3)
	(can_supply_O supplier2)
	(can_supply_P supplier4)
	(can_supply_Q supplier1)
	(can_supply_R supplier1)
	(can_supply_S supplier0)
	(at supplier2)
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
	(= (item-goal B) 2)
	(= (item-goal C) 10)
	(= (item-goal D) 14)
	(= (item-goal E) 14)
	(= (item-goal F) 7)
	(= (item-goal G) 14)
	(= (item-goal H) 14)
	(= (item-goal I) 1)
	(= (item-goal J) 5)
	(= (item-goal K) 11)
	(= (item-goal L) 12)
	(= (item-goal M) 5)
	(= (item-goal N) 8)
	(= (item-goal O) 4)
	(= (item-goal P) 6)
	(= (item-goal Q) 14)
	(= (item-goal R) 8)
	(= (item-goal S) 8)
)
(:goal (and
	(delivered N customer0)
	(delivered O customer1)
	(delivered P customer2)
	(delivered E customer3)
	(delivered B customer4)
	(delivered L customer5)
))
)

