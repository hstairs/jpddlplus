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
	(can_produce_A workshop8)
	(can_produce_B workshop5)
	(can_produce_C workshop6)
	(can_produce_F workshop4)
	(can_produce_H workshop4)
	(can_produce_K workshop3)
	(can_produce_L workshop6)
	(can_produce_M workshop4)
	(can_supply_D supplier3)
	(can_supply_E supplier5)
	(can_supply_G supplier5)
	(can_supply_I supplier6)
	(can_supply_J supplier5)
	(can_supply_N supplier0)
	(can_supply_O supplier8)
	(can_supply_P supplier1)
	(can_supply_Q supplier8)
	(can_supply_R supplier6)
	(can_supply_S supplier6)
	(at customer1)
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
	(= (item-goal B) 11)
	(= (item-goal C) 10)
	(= (item-goal D) 7)
	(= (item-goal E) 2)
	(= (item-goal F) 10)
	(= (item-goal G) 9)
	(= (item-goal H) 6)
	(= (item-goal I) 14)
	(= (item-goal J) 1)
	(= (item-goal K) 12)
	(= (item-goal L) 4)
	(= (item-goal M) 12)
	(= (item-goal N) 12)
	(= (item-goal O) 1)
	(= (item-goal P) 5)
	(= (item-goal Q) 6)
	(= (item-goal R) 3)
	(= (item-goal S) 6)
)
(:goal (and
	(delivered A customer0)
	(delivered B customer1)
	(delivered F customer2)
	(delivered K customer3)
	(delivered G customer4)
	(delivered S customer5)
	(delivered M customer6)
	(delivered O customer7)
	(delivered E customer8)
))
)

