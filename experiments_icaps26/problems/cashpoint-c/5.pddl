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
	(at location1)
	(canwithdraw location1)
	(canwithdraw location1)
	(canwithdraw location1)
	(canwithdraw location1)
	(canwithdraw location1)
	(canbuy location14 item0)
	(canbuy location14 item1)
	(canbuy location12 item2)
	(canbuy location12 item3)
	(canbuy location10 item4)
	(canbuy location3 item5)
	(canbuy location11 item6)
	(canbuy location12 item7)
	(canbuy location3 item8)
	(canbuy location1 item9)
	(canbuy location3 item10)
	(canbuy location5 item11)
	(canbuy location11 item12)
	(canbuy location12 item13)
	(canbuy location10 item14)
	(currencyOf item0 currency1)
	(currencyOf item1 currency0)
	(currencyOf item2 currency1)
	(currencyOf item3 currency0)
	(currencyOf item4 currency1)
	(currencyOf item5 currency0)
	(currencyOf item6 currency0)
	(currencyOf item7 currency0)
	(currencyOf item8 currency1)
	(currencyOf item9 currency1)
	(currencyOf item10 currency1)
	(currencyOf item11 currency0)
	(currencyOf item12 currency1)
	(currencyOf item13 currency0)
	(currencyOf item14 currency0)
	(= (inpocket currency0) 0)
	(= (inpocket currency1) 0)
	(= (currency_goal currency0) 97)
	(= (currency_goal currency1) 17)
	(= (price item0) 51)
	(= (price item1) 76)
	(= (price item2) 93)
	(= (price item3) 43)
	(= (price item4) 44)
	(= (price item5) 87)
	(= (price item6) 12)
	(= (price item7) 97)
	(= (price item8) 10)
	(= (price item9) 27)
	(= (price item10) 99)
	(= (price item11) 47)
	(= (price item12) 71)
	(= (price item13) 28)
	(= (price item14) 37)
	(= (balance currency0) 786)
	(= (balance currency1) 618)
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

