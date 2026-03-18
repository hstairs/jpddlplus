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
	supplier6 - supplier
	supplier7 - supplier
	customer0 - customer
	customer1 - customer
	customer2 - customer
	customer3 - customer
	customer4 - customer
	customer5 - customer
	customer6 - customer
	customer7 - customer
	workshop0 - workshop
	workshop1 - workshop
	workshop2 - workshop
	workshop3 - workshop
	workshop4 - workshop
	workshop5 - workshop
	workshop6 - workshop
	workshop7 - workshop
)


(:init
	(can_produce_A workshop2)
	(can_produce_B workshop1)
	(can_produce_C workshop0)
	(can_produce_F workshop7)
	(can_produce_H workshop6)
	(can_produce_K workshop2)
	(can_produce_L workshop1)
	(can_produce_M workshop4)
	(can_supply_D supplier0)
	(can_supply_E supplier3)
	(can_supply_G supplier6)
	(can_supply_I supplier3)
	(can_supply_J supplier7)
	(can_supply_N supplier3)
	(can_supply_O supplier6)
	(can_supply_P supplier5)
	(can_supply_Q supplier2)
	(can_supply_R supplier2)
	(can_supply_S supplier6)
	(at workshop7)
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
	(= (item-goal A) 3)
	(= (item-goal B) 10)
	(= (item-goal C) 10)
	(= (item-goal D) 8)
	(= (item-goal E) 6)
	(= (item-goal F) 2)
	(= (item-goal G) 1)
	(= (item-goal H) 3)
	(= (item-goal I) 1)
	(= (item-goal J) 1)
	(= (item-goal K) 5)
	(= (item-goal L) 6)
	(= (item-goal M) 4)
	(= (item-goal N) 11)
	(= (item-goal O) 5)
	(= (item-goal P) 12)
	(= (item-goal Q) 10)
	(= (item-goal R) 14)
	(= (item-goal S) 4)
)
(:goal (and
	(delivered R customer0)
	(delivered N customer1)
	(delivered G customer2)
	(delivered B customer3)
	(delivered P customer4)
	(delivered A customer5)
	(delivered O customer6)
	(delivered I customer7)
))
)

