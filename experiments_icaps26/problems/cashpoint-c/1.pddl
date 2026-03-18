(define (problem cashpoint)
(:domain cashpoint)
(:objects 
	location0 - location
	location1 - location
	location2 - location
	location3 - location
	location4 - location
	currency0 - currency
	currency1 - currency
	item0 - item
	item1 - item
	item2 - item
	item3 - item
	item4 - item
)
(:init
	(at location1)
	(canwithdraw location1)
	(canbuy location2 item0)
	(canbuy location1 item1)
	(canbuy location3 item2)
	(canbuy location1 item3)
	(canbuy location1 item4)
	(currencyOf item0 currency1)
	(currencyOf item1 currency0)
	(currencyOf item2 currency0)
	(currencyOf item3 currency1)
	(currencyOf item4 currency0)
	(= (inpocket currency0) 0)
	(= (inpocket currency1) 0)
	(= (currency_goal currency0) 71)
	(= (currency_goal currency1) 55)
	(= (price item0) 17)
	(= (price item1) 36)
	(= (price item2) 59)
	(= (price item3) 13)
	(= (price item4) 64)
	(= (balance currency0) 345)
	(= (balance currency1) 128)
)
(:goal (and
	(bought item0)
	(bought item1)
	(bought item2)
	(bought item3)
	(bought item4)
	(have_enough currency0)
	(have_enough currency1)
))
)

