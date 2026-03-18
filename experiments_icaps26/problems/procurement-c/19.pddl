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
	supplier8 - supplier
	supplier9 - supplier
	customer0 - customer
	customer1 - customer
	customer2 - customer
	customer3 - customer
	customer4 - customer
	customer5 - customer
	customer6 - customer
	customer7 - customer
	customer8 - customer
	customer9 - customer
	workshop0 - workshop
	workshop1 - workshop
	workshop2 - workshop
	workshop3 - workshop
	workshop4 - workshop
	workshop5 - workshop
	workshop6 - workshop
	workshop7 - workshop
	workshop8 - workshop
	workshop9 - workshop
)


(:init
	(can_produce_A workshop3)
	(can_produce_B workshop2)
	(can_produce_C workshop4)
	(can_produce_F workshop0)
	(can_produce_H workshop6)
	(can_produce_K workshop8)
	(can_produce_L workshop2)
	(can_produce_M workshop4)
	(can_supply_D supplier1)
	(can_supply_E supplier8)
	(can_supply_G supplier5)
	(can_supply_I supplier1)
	(can_supply_J supplier9)
	(can_supply_N supplier1)
	(can_supply_O supplier3)
	(can_supply_P supplier7)
	(can_supply_Q supplier7)
	(can_supply_R supplier3)
	(can_supply_S supplier3)
	(at customer3)
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
	(= (item-goal B) 7)
	(= (item-goal C) 11)
	(= (item-goal D) 5)
	(= (item-goal E) 2)
	(= (item-goal F) 6)
	(= (item-goal G) 6)
	(= (item-goal H) 11)
	(= (item-goal I) 3)
	(= (item-goal J) 3)
	(= (item-goal K) 6)
	(= (item-goal L) 1)
	(= (item-goal M) 3)
	(= (item-goal N) 10)
	(= (item-goal O) 14)
	(= (item-goal P) 1)
	(= (item-goal Q) 7)
	(= (item-goal R) 9)
	(= (item-goal S) 8)
)
(:goal (and
	(delivered P customer0)
	(delivered Q customer1)
	(delivered R customer2)
	(delivered M customer3)
	(delivered E customer4)
	(delivered F customer5)
	(delivered J customer6)
	(delivered O customer7)
	(delivered C customer8)
	(delivered L customer9)
))
)

