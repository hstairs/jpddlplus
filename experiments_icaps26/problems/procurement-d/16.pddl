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
	(can_produce_A workshop1)
	(can_produce_B workshop7)
	(can_produce_C workshop1)
	(can_produce_F workshop7)
	(can_produce_H workshop1)
	(can_produce_K workshop2)
	(can_produce_L workshop5)
	(can_produce_M workshop6)
	(can_supply_D supplier7)
	(can_supply_E supplier1)
	(can_supply_G supplier0)
	(can_supply_I supplier5)
	(can_supply_J supplier4)
	(can_supply_N supplier0)
	(can_supply_O supplier1)
	(can_supply_P supplier4)
	(can_supply_Q supplier6)
	(can_supply_R supplier3)
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
	(= (item-goal A) 7)
	(= (item-goal B) 14)
	(= (item-goal C) 2)
	(= (item-goal D) 4)
	(= (item-goal E) 14)
	(= (item-goal F) 5)
	(= (item-goal G) 9)
	(= (item-goal H) 12)
	(= (item-goal I) 8)
	(= (item-goal J) 3)
	(= (item-goal K) 8)
	(= (item-goal L) 1)
	(= (item-goal M) 14)
	(= (item-goal N) 3)
	(= (item-goal O) 13)
	(= (item-goal P) 11)
	(= (item-goal Q) 14)
	(= (item-goal R) 4)
	(= (item-goal S) 5)
)
(:goal (and
	(delivered B customer0)
	(delivered J customer1)
	(delivered O customer2)
	(delivered E customer3)
	(delivered P customer4)
	(delivered S customer5)
	(delivered A customer6)
	(delivered M customer7)
))
)

