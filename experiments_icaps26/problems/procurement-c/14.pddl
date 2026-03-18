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
	customer0 - customer
	customer1 - customer
	customer2 - customer
	customer3 - customer
	customer4 - customer
	customer5 - customer
	customer6 - customer
	workshop0 - workshop
	workshop1 - workshop
	workshop2 - workshop
	workshop3 - workshop
	workshop4 - workshop
	workshop5 - workshop
	workshop6 - workshop
)


(:init
	(can_produce_A workshop1)
	(can_produce_B workshop2)
	(can_produce_C workshop6)
	(can_produce_F workshop6)
	(can_produce_H workshop3)
	(can_produce_K workshop0)
	(can_produce_L workshop5)
	(can_produce_M workshop0)
	(can_supply_D supplier2)
	(can_supply_E supplier6)
	(can_supply_G supplier1)
	(can_supply_I supplier4)
	(can_supply_J supplier0)
	(can_supply_N supplier6)
	(can_supply_O supplier0)
	(can_supply_P supplier4)
	(can_supply_Q supplier0)
	(can_supply_R supplier5)
	(can_supply_S supplier5)
	(at customer2)
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
	(= (item-goal A) 4)
	(= (item-goal B) 8)
	(= (item-goal C) 4)
	(= (item-goal D) 8)
	(= (item-goal E) 8)
	(= (item-goal F) 11)
	(= (item-goal G) 14)
	(= (item-goal H) 11)
	(= (item-goal I) 1)
	(= (item-goal J) 11)
	(= (item-goal K) 7)
	(= (item-goal L) 8)
	(= (item-goal M) 9)
	(= (item-goal N) 7)
	(= (item-goal O) 13)
	(= (item-goal P) 8)
	(= (item-goal Q) 8)
	(= (item-goal R) 8)
	(= (item-goal S) 13)
)
(:goal (and
	(delivered F customer0)
	(delivered I customer1)
	(delivered K customer2)
	(delivered E customer3)
	(delivered C customer4)
	(delivered J customer5)
	(delivered R customer6)
))
)

