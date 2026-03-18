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
	location15 - location
	location16 - location
	location17 - location
	location18 - location
	location19 - location
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
	(at location17)
	(canwithdraw location17)
	(canwithdraw location17)
	(canwithdraw location17)
	(canwithdraw location17)
	(canwithdraw location17)
	(canwithdraw location17)
	(canbuy location6 item0)
	(canbuy location9 item1)
	(canbuy location16 item2)
	(canbuy location16 item3)
	(canbuy location15 item4)
	(canbuy location4 item5)
	(canbuy location19 item6)
	(canbuy location2 item7)
	(canbuy location5 item8)
	(canbuy location11 item9)
	(currencyOf item0 currency0)
	(currencyOf item1 currency0)
	(currencyOf item2 currency0)
	(currencyOf item3 currency1)
	(currencyOf item4 currency0)
	(currencyOf item5 currency1)
	(currencyOf item6 currency0)
	(currencyOf item7 currency1)
	(currencyOf item8 currency0)
	(currencyOf item9 currency1)
	(= (inpocket currency0) 0)
	(= (inpocket currency1) 0)
	(= (currency_goal currency0) 88)
	(= (currency_goal currency1) 78)
	(= (price item0) 98)
	(= (price item1) 56)
	(= (price item2) 66)
	(= (price item3) 58)
	(= (price item4) 13)
	(= (price item5) 72)
	(= (price item6) 85)
	(= (price item7) 19)
	(= (price item8) 30)
	(= (price item9) 45)
	(= (balance currency0) 654)
	(= (balance currency1) 408)
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

