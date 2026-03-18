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
	customer0 - customer
	customer1 - customer
	customer2 - customer
	customer3 - customer
	customer4 - customer
	customer5 - customer
	workshop0 - workshop
	workshop1 - workshop
	workshop2 - workshop
	workshop3 - workshop
	workshop4 - workshop
	workshop5 - workshop
)


(:init
	(can_produce_A workshop3)
	(can_produce_B workshop3)
	(can_produce_C workshop2)
	(can_produce_F workshop1)
	(can_produce_H workshop0)
	(can_produce_K workshop5)
	(can_produce_L workshop4)
	(can_produce_M workshop5)
	(can_supply_D supplier2)
	(can_supply_E supplier2)
	(can_supply_G supplier3)
	(can_supply_I supplier1)
	(can_supply_J supplier3)
	(can_supply_N supplier4)
	(can_supply_O supplier5)
	(can_supply_P supplier2)
	(can_supply_Q supplier3)
	(can_supply_R supplier4)
	(can_supply_S supplier5)
	(at supplier4)
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
	(= (item-goal C) 11)
	(= (item-goal D) 7)
	(= (item-goal E) 9)
	(= (item-goal F) 9)
	(= (item-goal G) 8)
	(= (item-goal H) 12)
	(= (item-goal I) 13)
	(= (item-goal J) 14)
	(= (item-goal K) 6)
	(= (item-goal L) 2)
	(= (item-goal M) 12)
	(= (item-goal N) 4)
	(= (item-goal O) 11)
	(= (item-goal P) 12)
	(= (item-goal Q) 3)
	(= (item-goal R) 10)
	(= (item-goal S) 1)
)
(:goal (and
	(delivered D customer0)
	(delivered C customer1)
	(delivered B customer2)
	(delivered O customer3)
	(delivered K customer4)
	(delivered L customer5)
))
)

