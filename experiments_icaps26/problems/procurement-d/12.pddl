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
	(can_produce_B workshop1)
	(can_produce_C workshop2)
	(can_produce_F workshop1)
	(can_produce_H workshop1)
	(can_produce_K workshop2)
	(can_produce_L workshop0)
	(can_produce_M workshop3)
	(can_supply_D supplier4)
	(can_supply_E supplier0)
	(can_supply_G supplier4)
	(can_supply_I supplier4)
	(can_supply_J supplier4)
	(can_supply_N supplier1)
	(can_supply_O supplier1)
	(can_supply_P supplier5)
	(can_supply_Q supplier2)
	(can_supply_R supplier1)
	(can_supply_S supplier1)
	(at supplier5)
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
	(= (item-goal B) 8)
	(= (item-goal C) 11)
	(= (item-goal D) 7)
	(= (item-goal E) 3)
	(= (item-goal F) 12)
	(= (item-goal G) 9)
	(= (item-goal H) 2)
	(= (item-goal I) 6)
	(= (item-goal J) 4)
	(= (item-goal K) 6)
	(= (item-goal L) 13)
	(= (item-goal M) 9)
	(= (item-goal N) 10)
	(= (item-goal O) 8)
	(= (item-goal P) 9)
	(= (item-goal Q) 4)
	(= (item-goal R) 2)
	(= (item-goal S) 1)
)
(:goal (and
	(delivered R customer0)
	(delivered S customer1)
	(delivered E customer2)
	(delivered G customer3)
	(delivered I customer4)
	(delivered N customer5)
))
)

