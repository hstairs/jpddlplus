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
	item5 - item
	item6 - item
	item7 - item
	item8 - item
	item9 - item
)
(:init
	(at location0)
	(canwithdraw location0)
	(canbuy location3 item0)
	(canbuy location3 item1)
	(canbuy location0 item2)
	(canbuy location2 item3)
	(canbuy location2 item4)
	(canbuy location4 item5)
	(canbuy location4 item6)
	(canbuy location0 item7)
	(canbuy location3 item8)
	(canbuy location4 item9)
	(currencyOf item0 currency1)
	(currencyOf item1 currency1)
	(currencyOf item2 currency0)
	(currencyOf item3 currency0)
	(currencyOf item4 currency0)
	(currencyOf item5 currency0)
	(currencyOf item6 currency0)
	(currencyOf item7 currency1)
	(currencyOf item8 currency0)
	(currencyOf item9 currency0)
	(= (inpocket currency0) 0)
	(= (inpocket currency1) 0)
	(= (currency_goal currency0) 18)
	(= (currency_goal currency1) 69)
	(= (price item0) 77)
	(= (price item1) 52)
	(= (price item2) 38)
	(= (price item3) 41)
	(= (price item4) 64)
	(= (price item5) 42)
	(= (price item6) 36)
	(= (price item7) 44)
	(= (price item8) 63)
	(= (price item9) 66)
	(= (balance currency0) 552)
	(= (balance currency1) 363)
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

