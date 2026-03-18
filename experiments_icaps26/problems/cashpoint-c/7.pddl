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
	item10 - item
	item11 - item
	item12 - item
	item13 - item
	item14 - item
	item15 - item
	item16 - item
	item17 - item
	item18 - item
	item19 - item
)

(:init
	(at location5)
	(canwithdraw location5)
	(canwithdraw location5)
	(canwithdraw location5)
	(canwithdraw location5)
	(canwithdraw location5)
	(canwithdraw location5)
	(canbuy location4 item0)
	(canbuy location18 item1)
	(canbuy location10 item2)
	(canbuy location7 item3)
	(canbuy location18 item4)
	(canbuy location1 item5)
	(canbuy location2 item6)
	(canbuy location18 item7)
	(canbuy location2 item8)
	(canbuy location6 item9)
	(canbuy location10 item10)
	(canbuy location18 item11)
	(canbuy location0 item12)
	(canbuy location12 item13)
	(canbuy location4 item14)
	(canbuy location2 item15)
	(canbuy location7 item16)
	(canbuy location18 item17)
	(canbuy location4 item18)
	(canbuy location5 item19)
	(currencyOf item0 currency1)
	(currencyOf item1 currency1)
	(currencyOf item2 currency1)
	(currencyOf item3 currency1)
	(currencyOf item4 currency0)
	(currencyOf item5 currency0)
	(currencyOf item6 currency1)
	(currencyOf item7 currency1)
	(currencyOf item8 currency1)
	(currencyOf item9 currency1)
	(currencyOf item10 currency0)
	(currencyOf item11 currency0)
	(currencyOf item12 currency0)
	(currencyOf item13 currency1)
	(currencyOf item14 currency0)
	(currencyOf item15 currency0)
	(currencyOf item16 currency1)
	(currencyOf item17 currency1)
	(currencyOf item18 currency0)
	(currencyOf item19 currency1)
	(= (inpocket currency0) 0)
	(= (inpocket currency1) 0)
	(= (currency_goal currency0) 48)
	(= (currency_goal currency1) 76)
	(= (price item0) 72)
	(= (price item1) 25)
	(= (price item2) 82)
	(= (price item3) 40)
	(= (price item4) 31)
	(= (price item5) 71)
	(= (price item6) 28)
	(= (price item7) 40)
	(= (price item8) 43)
	(= (price item9) 100)
	(= (price item10) 74)
	(= (price item11) 13)
	(= (price item12) 82)
	(= (price item13) 50)
	(= (price item14) 27)
	(= (price item15) 91)
	(= (price item16) 84)
	(= (price item17) 19)
	(= (price item18) 76)
	(= (price item19) 26)
	(= (balance currency0) 770)
	(= (balance currency1) 1028)
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
	(bought item15)
	(bought item16)
	(bought item17)
	(bought item18)
	(bought item19)
	(have_enough currency0)
	(have_enough currency1)
))
)

