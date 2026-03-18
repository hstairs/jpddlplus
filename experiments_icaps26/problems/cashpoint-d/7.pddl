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
	(at location4)
	(canwithdraw location4)
	(canwithdraw location4)
	(canwithdraw location4)
	(canwithdraw location4)
	(canwithdraw location4)
	(canwithdraw location4)
	(canbuy location15 item0)
	(canbuy location3 item1)
	(canbuy location18 item2)
	(canbuy location16 item3)
	(canbuy location13 item4)
	(canbuy location19 item5)
	(canbuy location16 item6)
	(canbuy location14 item7)
	(canbuy location11 item8)
	(canbuy location19 item9)
	(canbuy location18 item10)
	(canbuy location2 item11)
	(canbuy location13 item12)
	(canbuy location19 item13)
	(canbuy location5 item14)
	(canbuy location14 item15)
	(canbuy location11 item16)
	(canbuy location18 item17)
	(canbuy location15 item18)
	(canbuy location0 item19)
	(currencyOf item0 currency1)
	(currencyOf item1 currency1)
	(currencyOf item2 currency0)
	(currencyOf item3 currency0)
	(currencyOf item4 currency0)
	(currencyOf item5 currency1)
	(currencyOf item6 currency0)
	(currencyOf item7 currency1)
	(currencyOf item8 currency1)
	(currencyOf item9 currency1)
	(currencyOf item10 currency1)
	(currencyOf item11 currency0)
	(currencyOf item12 currency1)
	(currencyOf item13 currency1)
	(currencyOf item14 currency1)
	(currencyOf item15 currency1)
	(currencyOf item16 currency0)
	(currencyOf item17 currency1)
	(currencyOf item18 currency0)
	(currencyOf item19 currency1)
	(= (inpocket currency0) 0)
	(= (inpocket currency1) 0)
	(= (currency_goal currency0) 18)
	(= (currency_goal currency1) 38)
	(= (price item0) 17)
	(= (price item1) 29)
	(= (price item2) 50)
	(= (price item3) 30)
	(= (price item4) 38)
	(= (price item5) 74)
	(= (price item6) 19)
	(= (price item7) 44)
	(= (price item8) 53)
	(= (price item9) 36)
	(= (price item10) 34)
	(= (price item11) 11)
	(= (price item12) 52)
	(= (price item13) 43)
	(= (price item14) 10)
	(= (price item15) 90)
	(= (price item16) 55)
	(= (price item17) 18)
	(= (price item18) 19)
	(= (price item19) 49)
	(= (balance currency0) 360)
	(= (balance currency1) 880)
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

