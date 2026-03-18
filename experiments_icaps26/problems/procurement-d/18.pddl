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
	(can_produce_A workshop4)
	(can_produce_B workshop3)
	(can_produce_C workshop3)
	(can_produce_F workshop6)
	(can_produce_H workshop7)
	(can_produce_K workshop7)
	(can_produce_L workshop0)
	(can_produce_M workshop5)
	(can_supply_D supplier8)
	(can_supply_E supplier4)
	(can_supply_G supplier4)
	(can_supply_I supplier7)
	(can_supply_J supplier7)
	(can_supply_N supplier2)
	(can_supply_O supplier0)
	(can_supply_P supplier8)
	(can_supply_Q supplier2)
	(can_supply_R supplier8)
	(can_supply_S supplier7)
	(at customer8)
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
	(= (item-goal A) 12)
	(= (item-goal B) 11)
	(= (item-goal C) 13)
	(= (item-goal D) 8)
	(= (item-goal E) 11)
	(= (item-goal F) 11)
	(= (item-goal G) 13)
	(= (item-goal H) 8)
	(= (item-goal I) 14)
	(= (item-goal J) 9)
	(= (item-goal K) 11)
	(= (item-goal L) 11)
	(= (item-goal M) 13)
	(= (item-goal N) 14)
	(= (item-goal O) 7)
	(= (item-goal P) 14)
	(= (item-goal Q) 10)
	(= (item-goal R) 3)
	(= (item-goal S) 11)
)
(:goal (and
	(delivered I customer0)
	(delivered O customer1)
	(delivered J customer2)
	(delivered N customer3)
	(delivered R customer4)
	(delivered P customer5)
	(delivered G customer6)
	(delivered C customer7)
	(delivered H customer8)
))
)

