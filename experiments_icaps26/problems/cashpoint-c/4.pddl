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
	(at location2)
	(canwithdraw location2)
	(canwithdraw location2)
	(canwithdraw location2)
	(canwithdraw location2)
	(canwithdraw location2)
	(canbuy location0 item0)
	(canbuy location8 item1)
	(canbuy location14 item2)
	(canbuy location5 item3)
	(canbuy location2 item4)
	(canbuy location13 item5)
	(canbuy location7 item6)
	(canbuy location12 item7)
	(canbuy location13 item8)
	(canbuy location7 item9)
	(currencyOf item0 currency0)
	(currencyOf item1 currency1)
	(currencyOf item2 currency0)
	(currencyOf item3 currency1)
	(currencyOf item4 currency0)
	(currencyOf item5 currency1)
	(currencyOf item6 currency1)
	(currencyOf item7 currency1)
	(currencyOf item8 currency0)
	(currencyOf item9 currency0)
	(= (inpocket currency0) 0)
	(= (inpocket currency1) 0)
	(= (currency_goal currency0) 49)
	(= (currency_goal currency1) 17)
	(= (price item0) 67)
	(= (price item1) 63)
	(= (price item2) 30)
	(= (price item3) 76)
	(= (price item4) 18)
	(= (price item5) 53)
	(= (price item6) 64)
	(= (price item7) 69)
	(= (price item8) 72)
	(= (price item9) 81)
	(= (balance currency0) 476)
	(= (balance currency1) 513)
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

