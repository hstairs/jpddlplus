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
	customer0 - customer
	customer1 - customer
	customer2 - customer
	customer3 - customer
	customer4 - customer
	customer5 - customer
	customer6 - customer
	customer7 - customer
	customer8 - customer
	workshop0 - workshop
	workshop1 - workshop
	workshop2 - workshop
	workshop3 - workshop
	workshop4 - workshop
	workshop5 - workshop
	workshop6 - workshop
	workshop7 - workshop
	workshop8 - workshop
)


(:init
	(can_produce_A workshop6)
	(can_produce_B workshop5)
	(can_produce_C workshop5)
	(can_produce_F workshop7)
	(can_produce_H workshop4)
	(can_produce_K workshop6)
	(can_produce_L workshop1)
	(can_produce_M workshop4)
	(can_supply_D supplier8)
	(can_supply_E supplier7)
	(can_supply_G supplier7)
	(can_supply_I supplier8)
	(can_supply_J supplier6)
	(can_supply_N supplier4)
	(can_supply_O supplier1)
	(can_supply_P supplier3)
	(can_supply_Q supplier8)
	(can_supply_R supplier1)
	(can_supply_S supplier0)
	(at customer0)
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
	(= (item-goal B) 13)
	(= (item-goal C) 1)
	(= (item-goal D) 6)
	(= (item-goal E) 9)
	(= (item-goal F) 2)
	(= (item-goal G) 1)
	(= (item-goal H) 13)
	(= (item-goal I) 4)
	(= (item-goal J) 7)
	(= (item-goal K) 5)
	(= (item-goal L) 11)
	(= (item-goal M) 5)
	(= (item-goal N) 10)
	(= (item-goal O) 13)
	(= (item-goal P) 6)
	(= (item-goal Q) 3)
	(= (item-goal R) 10)
	(= (item-goal S) 4)
)
(:goal (and
	(delivered E customer0)
	(delivered L customer1)
	(delivered K customer2)
	(delivered N customer3)
	(delivered O customer4)
	(delivered R customer5)
	(delivered B customer6)
	(delivered P customer7)
	(delivered Q customer8)
))
)

