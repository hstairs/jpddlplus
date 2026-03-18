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
	(canbuy location18 item0)
	(canbuy location20 item1)
	(canbuy location29 item2)
	(canbuy location10 item3)
	(canbuy location25 item4)
	(canbuy location12 item5)
	(canbuy location23 item6)
	(canbuy location24 item7)
	(canbuy location14 item8)
	(canbuy location25 item9)
	(canbuy location14 item10)
	(canbuy location23 item11)
	(canbuy location23 item12)
	(canbuy location0 item13)
	(canbuy location27 item14)
	(canbuy location18 item15)
	(canbuy location14 item16)
	(canbuy location7 item17)
	(canbuy location28 item18)
	(canbuy location0 item19)
	(canbuy location12 item20)
	(canbuy location14 item21)
	(canbuy location29 item22)
	(canbuy location25 item23)
	(canbuy location17 item24)
	(canbuy location21 item25)
	(canbuy location12 item26)
	(canbuy location15 item27)
	(canbuy location17 item28)
	(canbuy location18 item29)
	(canbuy location15 item30)
	(canbuy location21 item31)
	(canbuy location14 item32)
	(canbuy location0 item33)
	(canbuy location27 item34)
	(canbuy location12 item35)
	(canbuy location27 item36)
	(canbuy location20 item37)
	(canbuy location17 item38)
	(canbuy location20 item39)
	(canbuy location24 item40)
	(canbuy location10 item41)
	(canbuy location12 item42)
	(canbuy location12 item43)
	(canbuy location14 item44)
	(canbuy location23 item45)
	(canbuy location7 item46)
	(canbuy location21 item47)
	(canbuy location9 item48)
	(canbuy location28 item49)
	(currencyOf item0 currency0)
	(currencyOf item1 currency0)
	(currencyOf item2 currency1)
	(currencyOf item3 currency0)
	(currencyOf item4 currency0)
	(currencyOf item5 currency1)
	(currencyOf item6 currency0)
	(currencyOf item7 currency1)
	(currencyOf item8 currency1)
	(currencyOf item9 currency0)
	(currencyOf item10 currency1)
	(currencyOf item11 currency1)
	(currencyOf item12 currency1)
	(currencyOf item13 currency0)
	(currencyOf item14 currency0)
	(currencyOf item15 currency1)
	(currencyOf item16 currency0)
	(currencyOf item17 currency1)
	(currencyOf item18 currency1)
	(currencyOf item19 currency1)
	(currencyOf item20 currency0)
	(currencyOf item21 currency1)
	(currencyOf item22 currency0)
	(currencyOf item23 currency0)
	(currencyOf item24 currency0)
	(currencyOf item25 currency0)
	(currencyOf item26 currency0)
	(currencyOf item27 currency0)
	(currencyOf item28 currency1)
	(currencyOf item29 currency1)
	(currencyOf item30 currency0)
	(currencyOf item31 currency1)
	(currencyOf item32 currency1)
	(currencyOf item33 currency1)
	(currencyOf item34 currency1)
	(currencyOf item35 currency0)
	(currencyOf item36 currency0)
	(currencyOf item37 currency1)
	(currencyOf item38 currency1)
	(currencyOf item39 currency1)
	(currencyOf item40 currency0)
	(currencyOf item41 currency1)
	(currencyOf item42 currency0)
	(currencyOf item43 currency0)
	(currencyOf item44 currency1)
	(currencyOf item45 currency1)
	(currencyOf item46 currency0)
	(currencyOf item47 currency0)
	(currencyOf item48 currency0)
	(currencyOf item49 currency0)
	(= (inpocket currency0) 0)
	(= (inpocket currency1) 0)
	(= (currency_goal currency0) 49)
	(= (currency_goal currency1) 74)
	(= (price item0) 91)
	(= (price item1) 99)
	(= (price item2) 73)
	(= (price item3) 36)
	(= (price item4) 12)
	(= (price item5) 32)
	(= (price item6) 62)
	(= (price item7) 93)
	(= (price item8) 25)
	(= (price item9) 22)
	(= (price item10) 59)
	(= (price item11) 24)
	(= (price item12) 70)
	(= (price item13) 79)
	(= (price item14) 74)
	(= (price item15) 67)
	(= (price item16) 82)
	(= (price item17) 81)
	(= (price item18) 73)
	(= (price item19) 46)
	(= (price item20) 44)
	(= (price item21) 22)
	(= (price item22) 99)
	(= (price item23) 59)
	(= (price item24) 76)
	(= (price item25) 12)
	(= (price item26) 78)
	(= (price item27) 29)
	(= (price item28) 38)
	(= (price item29) 36)
	(= (price item30) 53)
	(= (price item31) 75)
	(= (price item32) 42)
	(= (price item33) 52)
	(= (price item34) 53)
	(= (price item35) 86)
	(= (price item36) 70)
	(= (price item37) 83)
	(= (price item38) 93)
	(= (price item39) 36)
	(= (price item40) 86)
	(= (price item41) 59)
	(= (price item42) 83)
	(= (price item43) 42)
	(= (price item44) 59)
	(= (price item45) 77)
	(= (price item46) 37)
	(= (price item47) 56)
	(= (price item48) 84)
	(= (price item49) 22)
	(= (balance currency0) 2433)
	(= (balance currency1) 2163)
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

