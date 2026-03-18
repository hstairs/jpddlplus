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
	location20 - location
	location21 - location
	location22 - location
	location23 - location
	location24 - location
	location25 - location
	location26 - location
	location27 - location
	location28 - location
	location29 - location
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
	(at location25)
	(canwithdraw location25)
	(canwithdraw location25)
	(canwithdraw location25)
	(canwithdraw location25)
	(canwithdraw location25)
	(canwithdraw location25)
	(canwithdraw location25)
	(canwithdraw location25)
	(canwithdraw location25)
	(canwithdraw location25)
	(canbuy location17 item0)
	(canbuy location14 item1)
	(canbuy location0 item2)
	(canbuy location7 item3)
	(canbuy location17 item4)
	(canbuy location8 item5)
	(canbuy location27 item6)
	(canbuy location12 item7)
	(canbuy location29 item8)
	(canbuy location12 item9)
	(canbuy location19 item10)
	(canbuy location12 item11)
	(canbuy location1 item12)
	(canbuy location26 item13)
	(canbuy location24 item14)
	(canbuy location1 item15)
	(canbuy location8 item16)
	(canbuy location26 item17)
	(canbuy location12 item18)
	(canbuy location19 item19)
	(canbuy location27 item20)
	(canbuy location1 item21)
	(canbuy location14 item22)
	(canbuy location24 item23)
	(canbuy location12 item24)
	(canbuy location19 item25)
	(canbuy location0 item26)
	(canbuy location3 item27)
	(canbuy location29 item28)
	(canbuy location27 item29)
	(currencyOf item0 currency0)
	(currencyOf item1 currency0)
	(currencyOf item2 currency0)
	(currencyOf item3 currency1)
	(currencyOf item4 currency0)
	(currencyOf item5 currency0)
	(currencyOf item6 currency1)
	(currencyOf item7 currency0)
	(currencyOf item8 currency0)
	(currencyOf item9 currency1)
	(currencyOf item10 currency1)
	(currencyOf item11 currency0)
	(currencyOf item12 currency1)
	(currencyOf item13 currency0)
	(currencyOf item14 currency1)
	(currencyOf item15 currency0)
	(currencyOf item16 currency0)
	(currencyOf item17 currency0)
	(currencyOf item18 currency0)
	(currencyOf item19 currency0)
	(currencyOf item20 currency1)
	(currencyOf item21 currency1)
	(currencyOf item22 currency0)
	(currencyOf item23 currency1)
	(currencyOf item24 currency0)
	(currencyOf item25 currency0)
	(currencyOf item26 currency0)
	(currencyOf item27 currency0)
	(currencyOf item28 currency1)
	(currencyOf item29 currency1)
	(= (inpocket currency0) 0)
	(= (inpocket currency1) 0)
	(= (currency_goal currency0) 38)
	(= (currency_goal currency1) 11)
	(= (price item0) 22)
	(= (price item1) 94)
	(= (price item2) 50)
	(= (price item3) 74)
	(= (price item4) 75)
	(= (price item5) 30)
	(= (price item6) 15)
	(= (price item7) 30)
	(= (price item8) 61)
	(= (price item9) 20)
	(= (price item10) 87)
	(= (price item11) 54)
	(= (price item12) 24)
	(= (price item13) 82)
	(= (price item14) 63)
	(= (price item15) 15)
	(= (price item16) 79)
	(= (price item17) 33)
	(= (price item18) 13)
	(= (price item19) 17)
	(= (price item20) 73)
	(= (price item21) 45)
	(= (price item22) 74)
	(= (price item23) 56)
	(= (price item24) 68)
	(= (price item25) 63)
	(= (price item26) 68)
	(= (price item27) 90)
	(= (price item28) 99)
	(= (price item29) 26)
	(= (balance currency0) 1584)
	(= (balance currency1) 890)
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

