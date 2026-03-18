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
	(at location1)
	(canwithdraw location1)
	(canwithdraw location1)
	(canwithdraw location1)
	(canbuy location6 item0)
	(canbuy location0 item1)
	(canbuy location4 item2)
	(canbuy location7 item3)
	(canbuy location7 item4)
	(canbuy location7 item5)
	(canbuy location9 item6)
	(canbuy location3 item7)
	(canbuy location2 item8)
	(canbuy location3 item9)
	(currencyOf item0 currency0)
	(currencyOf item1 currency0)
	(currencyOf item2 currency1)
	(currencyOf item3 currency0)
	(currencyOf item4 currency0)
	(currencyOf item5 currency0)
	(currencyOf item6 currency1)
	(currencyOf item7 currency1)
	(currencyOf item8 currency1)
	(currencyOf item9 currency1)
	(= (inpocket currency0) 0)
	(= (inpocket currency1) 0)
	(= (currency_goal currency0) 60)
	(= (currency_goal currency1) 75)
	(= (price item0) 46)
	(= (price item1) 55)
	(= (price item2) 87)
	(= (price item3) 42)
	(= (price item4) 31)
	(= (price item5) 45)
	(= (price item6) 59)
	(= (price item7) 72)
	(= (price item8) 64)
	(= (price item9) 46)
	(= (balance currency0) 418)
	(= (balance currency1) 604)
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

