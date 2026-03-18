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
	(can_produce_B workshop5)
	(can_produce_C workshop5)
	(can_produce_F workshop1)
	(can_produce_H workshop8)
	(can_produce_K workshop0)
	(can_produce_L workshop4)
	(can_produce_M workshop2)
	(can_supply_D supplier0)
	(can_supply_E supplier3)
	(can_supply_G supplier6)
	(can_supply_I supplier0)
	(can_supply_J supplier6)
	(can_supply_N supplier7)
	(can_supply_O supplier5)
	(can_supply_P supplier8)
	(can_supply_Q supplier1)
	(can_supply_R supplier7)
	(can_supply_S supplier8)
	(at supplier3)
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
	(= (item-goal B) 9)
	(= (item-goal C) 3)
	(= (item-goal D) 13)
	(= (item-goal E) 9)
	(= (item-goal F) 4)
	(= (item-goal G) 3)
	(= (item-goal H) 8)
	(= (item-goal I) 1)
	(= (item-goal J) 1)
	(= (item-goal K) 9)
	(= (item-goal L) 4)
	(= (item-goal M) 2)
	(= (item-goal N) 10)
	(= (item-goal O) 10)
	(= (item-goal P) 3)
	(= (item-goal Q) 10)
	(= (item-goal R) 3)
	(= (item-goal S) 1)
)
(:goal (and
	(delivered D customer0)
	(delivered O customer1)
	(delivered H customer2)
	(delivered E customer3)
	(delivered M customer4)
	(delivered B customer5)
	(delivered I customer6)
	(delivered K customer7)
	(delivered S customer8)
))
)

