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
	(can_produce_A workshop3)
	(can_produce_B workshop0)
	(can_produce_C workshop3)
	(can_produce_F workshop1)
	(can_produce_H workshop6)
	(can_produce_K workshop3)
	(can_produce_L workshop4)
	(can_produce_M workshop4)
	(can_supply_D supplier5)
	(can_supply_E supplier4)
	(can_supply_G supplier3)
	(can_supply_I supplier3)
	(can_supply_J supplier5)
	(can_supply_N supplier6)
	(can_supply_O supplier2)
	(can_supply_P supplier6)
	(can_supply_Q supplier4)
	(can_supply_R supplier3)
	(can_supply_S supplier2)
	(at supplier0)
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
	(= (item-goal A) 9)
	(= (item-goal B) 6)
	(= (item-goal C) 1)
	(= (item-goal D) 2)
	(= (item-goal E) 3)
	(= (item-goal F) 10)
	(= (item-goal G) 12)
	(= (item-goal H) 13)
	(= (item-goal I) 11)
	(= (item-goal J) 9)
	(= (item-goal K) 5)
	(= (item-goal L) 9)
	(= (item-goal M) 7)
	(= (item-goal N) 5)
	(= (item-goal O) 5)
	(= (item-goal P) 1)
	(= (item-goal Q) 13)
	(= (item-goal R) 5)
	(= (item-goal S) 3)
)
(:goal (and
	(delivered O customer0)
	(delivered S customer1)
	(delivered A customer2)
	(delivered E customer3)
	(delivered C customer4)
	(delivered F customer5)
	(delivered D customer6)
))
)

