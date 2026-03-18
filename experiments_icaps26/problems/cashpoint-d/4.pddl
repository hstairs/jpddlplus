(define (problem cashpoint)
(:domain cashpoint)
(:objects 
	location0 - location
	location1 - location
	location2 - location
	location3 - location
	location4 - location
	location5 - location
	location6 - location
	location7 - location
	location8 - location
	location9 - location
	location10 - location
	location11 - location
	location12 - location
	location13 - location
	location14 - location
	currency0 - currency
	currency1 - currency
	item0 - item
	item1 - item
	item2 - item
	item3 - item
	item4 - item
	item5 - item
	item6 - item
	item7 - item
	item8 - item
	item9 - item
)
(:init
	(at location9)
	(canwithdraw location9)
	(canwithdraw location9)
	(canwithdraw location9)
	(canwithdraw location9)
	(canwithdraw location9)
	(canbuy location8 item0)
	(canbuy location12 item1)
	(canbuy location1 item2)
	(canbuy location4 item3)
	(canbuy location8 item4)
	(canbuy location12 item5)
	(canbuy location9 item6)
	(canbuy location7 item7)
	(canbuy location7 item8)
	(canbuy location8 item9)
	(currencyOf item0 currency1)
	(currencyOf item1 currency1)
	(currencyOf item2 currency0)
	(currencyOf item3 currency1)
	(currencyOf item4 currency0)
	(currencyOf item5 currency1)
	(currencyOf item6 currency1)
	(currencyOf item7 currency0)
	(currencyOf item8 currency0)
	(currencyOf item9 currency1)
	(= (inpocket currency0) 0)
	(= (inpocket currency1) 0)
	(= (currency_goal currency0) 58)
	(= (currency_goal currency1) 31)
	(= (price item0) 19)
	(= (price item1) 26)
	(= (price item2) 99)
	(= (price item3) 91)
	(= (price item4) 19)
	(= (price item5) 69)
	(= (price item6) 22)
	(= (price item7) 39)
	(= (price item8) 100)
	(= (price item9) 88)
	(= (balance currency0) 472)
	(= (balance currency1) 519)
)
(:goal (and
	(bought item0)
	(bought item1)
	(bought item2)
	(bought item3)
	(bought item4)
	(bought item5)
	(bought item6)
	(bought item7)
	(bought item8)
	(bought item9)
	(have_enough currency0)
	(have_enough currency1)
))
)

