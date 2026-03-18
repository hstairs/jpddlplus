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
	(at location5)
	(canwithdraw location5)
	(canwithdraw location5)
	(canwithdraw location5)
	(canbuy location3 item0)
	(canbuy location2 item1)
	(canbuy location9 item2)
	(canbuy location1 item3)
	(canbuy location8 item4)
	(canbuy location2 item5)
	(canbuy location1 item6)
	(canbuy location9 item7)
	(canbuy location9 item8)
	(canbuy location7 item9)
	(currencyOf item0 currency0)
	(currencyOf item1 currency0)
	(currencyOf item2 currency0)
	(currencyOf item3 currency1)
	(currencyOf item4 currency1)
	(currencyOf item5 currency1)
	(currencyOf item6 currency1)
	(currencyOf item7 currency0)
	(currencyOf item8 currency0)
	(currencyOf item9 currency0)
	(= (inpocket currency0) 0)
	(= (inpocket currency1) 0)
	(= (currency_goal currency0) 63)
	(= (currency_goal currency1) 73)
	(= (price item0) 11)
	(= (price item1) 14)
	(= (price item2) 83)
	(= (price item3) 15)
	(= (price item4) 31)
	(= (price item5) 69)
	(= (price item6) 52)
	(= (price item7) 77)
	(= (price item8) 53)
	(= (price item9) 13)
	(= (balance currency0) 471)
	(= (balance currency1) 360)
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

