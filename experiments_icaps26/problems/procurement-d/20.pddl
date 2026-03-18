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
	(can_produce_A workshop0)
	(can_produce_B workshop0)
	(can_produce_C workshop2)
	(can_produce_F workshop4)
	(can_produce_H workshop1)
	(can_produce_K workshop8)
	(can_produce_L workshop4)
	(can_produce_M workshop9)
	(can_supply_D supplier1)
	(can_supply_E supplier1)
	(can_supply_G supplier7)
	(can_supply_I supplier4)
	(can_supply_J supplier9)
	(can_supply_N supplier8)
	(can_supply_O supplier9)
	(can_supply_P supplier0)
	(can_supply_Q supplier6)
	(can_supply_R supplier7)
	(can_supply_S supplier7)
	(at supplier8)
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
	(= (item-goal A) 14)
	(= (item-goal B) 14)
	(= (item-goal C) 2)
	(= (item-goal D) 10)
	(= (item-goal E) 1)
	(= (item-goal F) 11)
	(= (item-goal G) 11)
	(= (item-goal H) 7)
	(= (item-goal I) 8)
	(= (item-goal J) 8)
	(= (item-goal K) 9)
	(= (item-goal L) 4)
	(= (item-goal M) 3)
	(= (item-goal N) 12)
	(= (item-goal O) 1)
	(= (item-goal P) 14)
	(= (item-goal Q) 8)
	(= (item-goal R) 11)
	(= (item-goal S) 7)
)
(:goal (and
	(delivered Q customer0)
	(delivered K customer1)
	(delivered D customer2)
	(delivered M customer3)
	(delivered I customer4)
	(delivered J customer5)
	(delivered P customer6)
	(delivered L customer7)
	(delivered B customer8)
	(delivered A customer9)
))
)

