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
	item10 - item
	item11 - item
	item12 - item
	item13 - item
	item14 - item
)
(:init
	(at location13)
	(canwithdraw location13)
	(canwithdraw location13)
	(canwithdraw location13)
	(canwithdraw location13)
	(canwithdraw location13)
	(canbuy location14 item0)
	(canbuy location3 item1)
	(canbuy location2 item2)
	(canbuy location3 item3)
	(canbuy location1 item4)
	(canbuy location3 item5)
	(canbuy location5 item6)
	(canbuy location1 item7)
	(canbuy location12 item8)
	(canbuy location14 item9)
	(canbuy location14 item10)
	(canbuy location5 item11)
	(canbuy location1 item12)
	(canbuy location2 item13)
	(canbuy location0 item14)
	(currencyOf item0 currency1)
	(currencyOf item1 currency0)
	(currencyOf item2 currency1)
	(currencyOf item3 currency0)
	(currencyOf item4 currency1)
	(currencyOf item5 currency1)
	(currencyOf item6 currency0)
	(currencyOf item7 currency1)
	(currencyOf item8 currency1)
	(currencyOf item9 currency1)
	(currencyOf item10 currency1)
	(currencyOf item11 currency0)
	(currencyOf item12 currency1)
	(currencyOf item13 currency0)
	(currencyOf item14 currency0)
	(= (inpocket currency0) 0)
	(= (inpocket currency1) 0)
	(= (currency_goal currency0) 26)
	(= (currency_goal currency1) 61)
	(= (price item0) 44)
	(= (price item1) 60)
	(= (price item2) 13)
	(= (price item3) 35)
	(= (price item4) 61)
	(= (price item5) 30)
	(= (price item6) 62)
	(= (price item7) 71)
	(= (price item8) 56)
	(= (price item9) 59)
	(= (price item10) 76)
	(= (price item11) 98)
	(= (price item12) 70)
	(= (price item13) 19)
	(= (price item14) 18)
	(= (balance currency0) 477)
	(= (balance currency1) 812)
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
	(bought item10)
	(bought item11)
	(bought item12)
	(bought item13)
	(bought item14)
	(have_enough currency0)
	(have_enough currency1)
))
)

