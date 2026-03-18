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
	(canbuy location0 item0)
	(canbuy location16 item1)
	(canbuy location0 item2)
	(canbuy location9 item3)
	(canbuy location13 item4)
	(canbuy location12 item5)
	(canbuy location6 item6)
	(canbuy location4 item7)
	(canbuy location14 item8)
	(canbuy location13 item9)
	(canbuy location5 item10)
	(canbuy location11 item11)
	(canbuy location18 item12)
	(canbuy location18 item13)
	(canbuy location18 item14)
	(canbuy location7 item15)
	(canbuy location16 item16)
	(canbuy location3 item17)
	(canbuy location4 item18)
	(canbuy location7 item19)
	(canbuy location5 item20)
	(canbuy location3 item21)
	(canbuy location3 item22)
	(canbuy location0 item23)
	(canbuy location13 item24)
	(canbuy location16 item25)
	(canbuy location5 item26)
	(canbuy location2 item27)
	(canbuy location9 item28)
	(canbuy location16 item29)
	(currencyOf item0 currency1)
	(currencyOf item1 currency0)
	(currencyOf item2 currency0)
	(currencyOf item3 currency0)
	(currencyOf item4 currency1)
	(currencyOf item5 currency0)
	(currencyOf item6 currency1)
	(currencyOf item7 currency0)
	(currencyOf item8 currency0)
	(currencyOf item9 currency0)
	(currencyOf item10 currency1)
	(currencyOf item11 currency1)
	(currencyOf item12 currency0)
	(currencyOf item13 currency0)
	(currencyOf item14 currency1)
	(currencyOf item15 currency1)
	(currencyOf item16 currency1)
	(currencyOf item17 currency0)
	(currencyOf item18 currency1)
	(currencyOf item19 currency1)
	(currencyOf item20 currency1)
	(currencyOf item21 currency1)
	(currencyOf item22 currency0)
	(currencyOf item23 currency0)
	(currencyOf item24 currency0)
	(currencyOf item25 currency0)
	(currencyOf item26 currency1)
	(currencyOf item27 currency0)
	(currencyOf item28 currency0)
	(currencyOf item29 currency1)
	(= (inpocket currency0) 0)
	(= (inpocket currency1) 0)
	(= (currency_goal currency0) 48)
	(= (currency_goal currency1) 45)
	(= (price item0) 92)
	(= (price item1) 96)
	(= (price item2) 68)
	(= (price item3) 87)
	(= (price item4) 57)
	(= (price item5) 63)
	(= (price item6) 54)
	(= (price item7) 18)
	(= (price item8) 10)
	(= (price item9) 89)
	(= (price item10) 16)
	(= (price item11) 50)
	(= (price item12) 60)
	(= (price item13) 95)
	(= (price item14) 25)
	(= (price item15) 37)
	(= (price item16) 91)
	(= (price item17) 52)
	(= (price item18) 38)
	(= (price item19) 73)
	(= (price item20) 86)
	(= (price item21) 79)
	(= (price item22) 26)
	(= (price item23) 30)
	(= (price item24) 81)
	(= (price item25) 46)
	(= (price item26) 88)
	(= (price item27) 66)
	(= (price item28) 33)
	(= (price item29) 67)
	(= (balance currency0) 1452)
	(= (balance currency1) 1347)
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

