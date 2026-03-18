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
	item20 - item
	item21 - item
	item22 - item
	item23 - item
	item24 - item
	item25 - item
	item26 - item
	item27 - item
	item28 - item
	item29 - item
)
(:init
	(at location10)
	(canwithdraw location10)
	(canwithdraw location10)
	(canwithdraw location10)
	(canwithdraw location10)
	(canwithdraw location10)
	(canwithdraw location10)
	(canbuy location19 item0)
	(canbuy location15 item1)
	(canbuy location16 item2)
	(canbuy location12 item3)
	(canbuy location3 item4)
	(canbuy location16 item5)
	(canbuy location6 item6)
	(canbuy location19 item7)
	(canbuy location2 item8)
	(canbuy location0 item9)
	(canbuy location18 item10)
	(canbuy location12 item11)
	(canbuy location4 item12)
	(canbuy location18 item13)
	(canbuy location8 item14)
	(canbuy location1 item15)
	(canbuy location15 item16)
	(canbuy location3 item17)
	(canbuy location1 item18)
	(canbuy location19 item19)
	(canbuy location2 item20)
	(canbuy location2 item21)
	(canbuy location12 item22)
	(canbuy location18 item23)
	(canbuy location15 item24)
	(canbuy location6 item25)
	(canbuy location10 item26)
	(canbuy location5 item27)
	(canbuy location12 item28)
	(canbuy location0 item29)
	(currencyOf item0 currency1)
	(currencyOf item1 currency1)
	(currencyOf item2 currency0)
	(currencyOf item3 currency1)
	(currencyOf item4 currency0)
	(currencyOf item5 currency0)
	(currencyOf item6 currency0)
	(currencyOf item7 currency0)
	(currencyOf item8 currency0)
	(currencyOf item9 currency0)
	(currencyOf item10 currency0)
	(currencyOf item11 currency1)
	(currencyOf item12 currency0)
	(currencyOf item13 currency0)
	(currencyOf item14 currency1)
	(currencyOf item15 currency0)
	(currencyOf item16 currency1)
	(currencyOf item17 currency0)
	(currencyOf item18 currency1)
	(currencyOf item19 currency0)
	(currencyOf item20 currency0)
	(currencyOf item21 currency1)
	(currencyOf item22 currency1)
	(currencyOf item23 currency1)
	(currencyOf item24 currency0)
	(currencyOf item25 currency0)
	(currencyOf item26 currency0)
	(currencyOf item27 currency0)
	(currencyOf item28 currency0)
	(currencyOf item29 currency0)
	(= (inpocket currency0) 0)
	(= (inpocket currency1) 0)
	(= (currency_goal currency0) 36)
	(= (currency_goal currency1) 97)
	(= (price item0) 87)
	(= (price item1) 74)
	(= (price item2) 59)
	(= (price item3) 95)
	(= (price item4) 66)
	(= (price item5) 87)
	(= (price item6) 53)
	(= (price item7) 26)
	(= (price item8) 96)
	(= (price item9) 96)
	(= (price item10) 50)
	(= (price item11) 13)
	(= (price item12) 92)
	(= (price item13) 83)
	(= (price item14) 74)
	(= (price item15) 69)
	(= (price item16) 30)
	(= (price item17) 49)
	(= (price item18) 83)
	(= (price item19) 26)
	(= (price item20) 58)
	(= (price item21) 11)
	(= (price item22) 28)
	(= (price item23) 13)
	(= (price item24) 100)
	(= (price item25) 38)
	(= (price item26) 91)
	(= (price item27) 42)
	(= (price item28) 99)
	(= (price item29) 59)
	(= (balance currency0) 2062)
	(= (balance currency1) 908)
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
	(bought item20)
	(bought item21)
	(bought item22)
	(bought item23)
	(bought item24)
	(bought item25)
	(bought item26)
	(bought item27)
	(bought item28)
	(bought item29)
	(have_enough currency0)
	(have_enough currency1)
))
)

