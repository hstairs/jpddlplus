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
	(can_produce_B workshop0)
	(can_produce_C workshop0)
	(can_produce_F workshop4)
	(can_produce_H workshop3)
	(can_produce_K workshop5)
	(can_produce_L workshop0)
	(can_produce_M workshop3)
	(can_supply_D supplier4)
	(can_supply_E supplier3)
	(can_supply_G supplier1)
	(can_supply_I supplier2)
	(can_supply_J supplier5)
	(can_supply_N supplier0)
	(can_supply_O supplier0)
	(can_supply_P supplier2)
	(can_supply_Q supplier5)
	(can_supply_R supplier5)
	(can_supply_S supplier2)
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
	(= (item-goal A) 14)
	(= (item-goal B) 2)
	(= (item-goal C) 12)
	(= (item-goal D) 2)
	(= (item-goal E) 12)
	(= (item-goal F) 14)
	(= (item-goal G) 4)
	(= (item-goal H) 4)
	(= (item-goal I) 7)
	(= (item-goal J) 14)
	(= (item-goal K) 5)
	(= (item-goal L) 8)
	(= (item-goal M) 12)
	(= (item-goal N) 1)
	(= (item-goal O) 11)
	(= (item-goal P) 5)
	(= (item-goal Q) 11)
	(= (item-goal R) 12)
	(= (item-goal S) 4)
)
(:goal (and
	(delivered G customer0)
	(delivered I customer1)
	(delivered S customer2)
	(delivered N customer3)
	(delivered O customer4)
	(delivered J customer5)
))
)

