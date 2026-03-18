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
	(at location4)
	(canwithdraw location4)
	(canbuy location4 item0)
	(canbuy location4 item1)
	(canbuy location2 item2)
	(canbuy location3 item3)
	(canbuy location1 item4)
	(canbuy location4 item5)
	(canbuy location1 item6)
	(canbuy location2 item7)
	(canbuy location4 item8)
	(canbuy location3 item9)
	(currencyOf item0 currency0)
	(currencyOf item1 currency0)
	(currencyOf item2 currency0)
	(currencyOf item3 currency0)
	(currencyOf item4 currency0)
	(currencyOf item5 currency0)
	(currencyOf item6 currency1)
	(currencyOf item7 currency0)
	(currencyOf item8 currency0)
	(currencyOf item9 currency0)
	(= (inpocket currency0) 0)
	(= (inpocket currency1) 0)
	(= (currency_goal currency0) 28)
	(= (currency_goal currency1) 46)
	(= (price item0) 75)
	(= (price item1) 97)
	(= (price item2) 35)
	(= (price item3) 34)
	(= (price item4) 98)
	(= (price item5) 33)
	(= (price item6) 48)
	(= (price item7) 10)
	(= (price item8) 61)
	(= (price item9) 79)
	(= (balance currency0) 825)
	(= (balance currency1) 141)
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

