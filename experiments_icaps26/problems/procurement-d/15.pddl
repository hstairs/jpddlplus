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
	(can_produce_A workshop6)
	(can_produce_B workshop2)
	(can_produce_C workshop0)
	(can_produce_F workshop3)
	(can_produce_H workshop4)
	(can_produce_K workshop2)
	(can_produce_L workshop0)
	(can_produce_M workshop2)
	(can_supply_D supplier0)
	(can_supply_E supplier4)
	(can_supply_G supplier1)
	(can_supply_I supplier2)
	(can_supply_J supplier5)
	(can_supply_N supplier2)
	(can_supply_O supplier3)
	(can_supply_P supplier5)
	(can_supply_Q supplier6)
	(can_supply_R supplier4)
	(can_supply_S supplier7)
	(at workshop5)
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
	(= (item-goal A) 6)
	(= (item-goal B) 13)
	(= (item-goal C) 7)
	(= (item-goal D) 1)
	(= (item-goal E) 9)
	(= (item-goal F) 4)
	(= (item-goal G) 11)
	(= (item-goal H) 10)
	(= (item-goal I) 8)
	(= (item-goal J) 14)
	(= (item-goal K) 7)
	(= (item-goal L) 11)
	(= (item-goal M) 7)
	(= (item-goal N) 4)
	(= (item-goal O) 1)
	(= (item-goal P) 10)
	(= (item-goal Q) 12)
	(= (item-goal R) 14)
	(= (item-goal S) 10)
)
(:goal (and
	(delivered O customer0)
	(delivered L customer1)
	(delivered P customer2)
	(delivered H customer3)
	(delivered Q customer4)
	(delivered E customer5)
	(delivered R customer6)
	(delivered J customer7)
))
)

