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
	(can_produce_A workshop5)
	(can_produce_B workshop6)
	(can_produce_C workshop1)
	(can_produce_F workshop5)
	(can_produce_H workshop2)
	(can_produce_K workshop7)
	(can_produce_L workshop6)
	(can_produce_M workshop7)
	(can_supply_D supplier1)
	(can_supply_E supplier4)
	(can_supply_G supplier0)
	(can_supply_I supplier1)
	(can_supply_J supplier1)
	(can_supply_N supplier3)
	(can_supply_O supplier3)
	(can_supply_P supplier6)
	(can_supply_Q supplier5)
	(can_supply_R supplier4)
	(can_supply_S supplier6)
	(at supplier7)
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
	(= (item-goal A) 10)
	(= (item-goal B) 14)
	(= (item-goal C) 7)
	(= (item-goal D) 3)
	(= (item-goal E) 14)
	(= (item-goal F) 6)
	(= (item-goal G) 8)
	(= (item-goal H) 7)
	(= (item-goal I) 7)
	(= (item-goal J) 10)
	(= (item-goal K) 1)
	(= (item-goal L) 12)
	(= (item-goal M) 5)
	(= (item-goal N) 9)
	(= (item-goal O) 9)
	(= (item-goal P) 3)
	(= (item-goal Q) 2)
	(= (item-goal R) 5)
	(= (item-goal S) 5)
)
(:goal (and
	(delivered I customer0)
	(delivered G customer1)
	(delivered D customer2)
	(delivered A customer3)
	(delivered O customer4)
	(delivered K customer5)
	(delivered C customer6)
	(delivered J customer7)
))
)

