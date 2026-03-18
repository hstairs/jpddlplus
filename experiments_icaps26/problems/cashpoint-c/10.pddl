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
	item30 - item
	item31 - item
	item32 - item
	item33 - item
	item34 - item
	item35 - item
	item36 - item
	item37 - item
	item38 - item
	item39 - item
	item40 - item
	item41 - item
	item42 - item
	item43 - item
	item44 - item
	item45 - item
	item46 - item
	item47 - item
	item48 - item
	item49 - item
)

(:init
	(at location17)
	(canwithdraw location17)
	(canwithdraw location17)
	(canwithdraw location17)
	(canwithdraw location17)
	(canwithdraw location17)
	(canwithdraw location17)
	(canwithdraw location17)
	(canwithdraw location17)
	(canwithdraw location17)
	(canwithdraw location17)
	(canbuy location21 item0)
	(canbuy location8 item1)
	(canbuy location19 item2)
	(canbuy location8 item3)
	(canbuy location17 item4)
	(canbuy location4 item5)
	(canbuy location5 item6)
	(canbuy location1 item7)
	(canbuy location21 item8)
	(canbuy location20 item9)
	(canbuy location4 item10)
	(canbuy location19 item11)
	(canbuy location23 item12)
	(canbuy location29 item13)
	(canbuy location17 item14)
	(canbuy location5 item15)
	(canbuy location8 item16)
	(canbuy location17 item17)
	(canbuy location7 item18)
	(canbuy location1 item19)
	(canbuy location10 item20)
	(canbuy location4 item21)
	(canbuy location2 item22)
	(canbuy location10 item23)
	(canbuy location15 item24)
	(canbuy location26 item25)
	(canbuy location8 item26)
	(canbuy location20 item27)
	(canbuy location21 item28)
	(canbuy location20 item29)
	(canbuy location19 item30)
	(canbuy location25 item31)
	(canbuy location22 item32)
	(canbuy location7 item33)
	(canbuy location17 item34)
	(canbuy location22 item35)
	(canbuy location17 item36)
	(canbuy location7 item37)
	(canbuy location25 item38)
	(canbuy location10 item39)
	(canbuy location13 item40)
	(canbuy location15 item41)
	(canbuy location20 item42)
	(canbuy location1 item43)
	(canbuy location10 item44)
	(canbuy location29 item45)
	(canbuy location3 item46)
	(canbuy location2 item47)
	(canbuy location10 item48)
	(canbuy location20 item49)
	(currencyOf item0 currency1)
	(currencyOf item1 currency0)
	(currencyOf item2 currency0)
	(currencyOf item3 currency1)
	(currencyOf item4 currency1)
	(currencyOf item5 currency0)
	(currencyOf item6 currency0)
	(currencyOf item7 currency1)
	(currencyOf item8 currency0)
	(currencyOf item9 currency0)
	(currencyOf item10 currency0)
	(currencyOf item11 currency0)
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
	(currencyOf item22 currency0)
	(currencyOf item23 currency0)
	(currencyOf item24 currency1)
	(currencyOf item25 currency0)
	(currencyOf item26 currency0)
	(currencyOf item27 currency1)
	(currencyOf item28 currency0)
	(currencyOf item29 currency0)
	(currencyOf item30 currency0)
	(currencyOf item31 currency1)
	(currencyOf item32 currency1)
	(currencyOf item33 currency0)
	(currencyOf item34 currency0)
	(currencyOf item35 currency0)
	(currencyOf item36 currency0)
	(currencyOf item37 currency0)
	(currencyOf item38 currency0)
	(currencyOf item39 currency0)
	(currencyOf item40 currency0)
	(currencyOf item41 currency0)
	(currencyOf item42 currency1)
	(currencyOf item43 currency0)
	(currencyOf item44 currency1)
	(currencyOf item45 currency0)
	(currencyOf item46 currency0)
	(currencyOf item47 currency1)
	(currencyOf item48 currency0)
	(currencyOf item49 currency1)
	(= (inpocket currency0) 0)
	(= (inpocket currency1) 0)
	(= (currency_goal currency0) 15)
	(= (currency_goal currency1) 41)
	(= (price item0) 77)
	(= (price item1) 99)
	(= (price item2) 57)
	(= (price item3) 62)
	(= (price item4) 91)
	(= (price item5) 74)
	(= (price item6) 43)
	(= (price item7) 50)
	(= (price item8) 63)
	(= (price item9) 81)
	(= (price item10) 72)
	(= (price item11) 74)
	(= (price item12) 99)
	(= (price item13) 35)
	(= (price item14) 30)
	(= (price item15) 28)
	(= (price item16) 68)
	(= (price item17) 90)
	(= (price item18) 25)
	(= (price item19) 47)
	(= (price item20) 23)
	(= (price item21) 56)
	(= (price item22) 24)
	(= (price item23) 90)
	(= (price item24) 30)
	(= (price item25) 39)
	(= (price item26) 33)
	(= (price item27) 10)
	(= (price item28) 33)
	(= (price item29) 52)
	(= (price item30) 23)
	(= (price item31) 21)
	(= (price item32) 89)
	(= (price item33) 75)
	(= (price item34) 82)
	(= (price item35) 36)
	(= (price item36) 80)
	(= (price item37) 16)
	(= (price item38) 44)
	(= (price item39) 81)
	(= (price item40) 55)
	(= (price item41) 63)
	(= (price item42) 65)
	(= (price item43) 37)
	(= (price item44) 13)
	(= (price item45) 23)
	(= (price item46) 53)
	(= (price item47) 18)
	(= (price item48) 52)
	(= (price item49) 27)
	(= (balance currency0) 2836)
	(= (balance currency1) 1160)
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
	(bought item30)
	(bought item31)
	(bought item32)
	(bought item33)
	(bought item34)
	(bought item35)
	(bought item36)
	(bought item37)
	(bought item38)
	(bought item39)
	(bought item40)
	(bought item41)
	(bought item42)
	(bought item43)
	(bought item44)
	(bought item45)
	(bought item46)
	(bought item47)
	(bought item48)
	(bought item49)
	(have_enough currency0)
	(have_enough currency1)
))
)

