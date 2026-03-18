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
	(canbuy location1 item0)
	(canbuy location0 item1)
	(canbuy location0 item2)
	(canbuy location2 item3)
	(canbuy location2 item4)
	(currencyOf item0 currency1)
	(currencyOf item1 currency0)
	(currencyOf item2 currency1)
	(currencyOf item3 currency0)
	(currencyOf item4 currency1)
	(= (inpocket currency0) 0)
	(= (inpocket currency1) 0)
	(= (currency_goal currency0) 10)
	(= (currency_goal currency1) 96)
	(= (price item0) 89)
	(= (price item1) 30)
	(= (price item2) 70)
	(= (price item3) 92)
	(= (price item4) 17)
	(= (balance currency0) 198)
	(= (balance currency1) 408)
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

