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
	location30 - location
	location31 - location
	location32 - location
	location33 - location
	location34 - location
	location35 - location
	location36 - location
	location37 - location
	location38 - location
	location39 - location
	location40 - location
	location41 - location
	location42 - location
	location43 - location
	location44 - location
	location45 - location
	location46 - location
	location47 - location
	location48 - location
	location49 - location
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
	(at location48)
	(canwithdraw location48)
	(canwithdraw location48)
	(canwithdraw location48)
	(canwithdraw location48)
	(canwithdraw location48)
	(canwithdraw location48)
	(canwithdraw location48)
	(canwithdraw location48)
	(canwithdraw location48)
	(canwithdraw location48)
	(canwithdraw location48)
	(canwithdraw location48)
	(canwithdraw location48)
	(canwithdraw location48)
	(canwithdraw location48)
	(canwithdraw location48)
	(canbuy location27 item0)
	(canbuy location2 item1)
	(canbuy location12 item2)
	(canbuy location41 item3)
	(canbuy location40 item4)
	(canbuy location27 item5)
	(canbuy location9 item6)
	(canbuy location5 item7)
	(canbuy location18 item8)
	(canbuy location31 item9)
	(canbuy location39 item10)
	(canbuy location46 item11)
	(canbuy location43 item12)
	(canbuy location33 item13)
	(canbuy location16 item14)
	(canbuy location39 item15)
	(canbuy location33 item16)
	(canbuy location41 item17)
	(canbuy location5 item18)
	(canbuy location32 item19)
	(canbuy location30 item20)
	(canbuy location2 item21)
	(canbuy location0 item22)
	(canbuy location9 item23)
	(canbuy location18 item24)
	(canbuy location11 item25)
	(canbuy location43 item26)
	(canbuy location48 item27)
	(canbuy location43 item28)
	(canbuy location16 item29)
	(canbuy location9 item30)
	(canbuy location7 item31)
	(canbuy location15 item32)
	(canbuy location17 item33)
	(canbuy location48 item34)
	(canbuy location7 item35)
	(canbuy location44 item36)
	(canbuy location16 item37)
	(canbuy location25 item38)
	(canbuy location39 item39)
	(canbuy location45 item40)
	(canbuy location7 item41)
	(canbuy location23 item42)
	(canbuy location15 item43)
	(canbuy location25 item44)
	(canbuy location17 item45)
	(canbuy location1 item46)
	(canbuy location15 item47)
	(canbuy location1 item48)
	(canbuy location41 item49)
	(currencyOf item0 currency1)
	(currencyOf item1 currency1)
	(currencyOf item2 currency1)
	(currencyOf item3 currency1)
	(currencyOf item4 currency1)
	(currencyOf item5 currency1)
	(currencyOf item6 currency1)
	(currencyOf item7 currency1)
	(currencyOf item8 currency0)
	(currencyOf item9 currency0)
	(currencyOf item10 currency0)
	(currencyOf item11 currency0)
	(currencyOf item12 currency1)
	(currencyOf item13 currency1)
	(currencyOf item14 currency1)
	(currencyOf item15 currency0)
	(currencyOf item16 currency0)
	(currencyOf item17 currency0)
	(currencyOf item18 currency0)
	(currencyOf item19 currency0)
	(currencyOf item20 currency1)
	(currencyOf item21 currency1)
	(currencyOf item22 currency0)
	(currencyOf item23 currency0)
	(currencyOf item24 currency0)
	(currencyOf item25 currency0)
	(currencyOf item26 currency0)
	(currencyOf item27 currency0)
	(currencyOf item28 currency0)
	(currencyOf item29 currency1)
	(currencyOf item30 currency1)
	(currencyOf item31 currency1)
	(currencyOf item32 currency1)
	(currencyOf item33 currency0)
	(currencyOf item34 currency0)
	(currencyOf item35 currency0)
	(currencyOf item36 currency0)
	(currencyOf item37 currency0)
	(currencyOf item38 currency0)
	(currencyOf item39 currency0)
	(currencyOf item40 currency1)
	(currencyOf item41 currency1)
	(currencyOf item42 currency1)
	(currencyOf item43 currency1)
	(currencyOf item44 currency0)
	(currencyOf item45 currency0)
	(currencyOf item46 currency0)
	(currencyOf item47 currency0)
	(currencyOf item48 currency1)
	(currencyOf item49 currency1)
	(= (inpocket currency0) 0)
	(= (inpocket currency1) 0)
	(= (currency_goal currency0) 44)
	(= (currency_goal currency1) 95)
	(= (price item0) 10)
	(= (price item1) 78)
	(= (price item2) 78)
	(= (price item3) 37)
	(= (price item4) 42)
	(= (price item5) 70)
	(= (price item6) 65)
	(= (price item7) 10)
	(= (price item8) 69)
	(= (price item9) 99)
	(= (price item10) 47)
	(= (price item11) 45)
	(= (price item12) 46)
	(= (price item13) 40)
	(= (price item14) 91)
	(= (price item15) 52)
	(= (price item16) 45)
	(= (price item17) 51)
	(= (price item18) 83)
	(= (price item19) 50)
	(= (price item20) 32)
	(= (price item21) 22)
	(= (price item22) 51)
	(= (price item23) 58)
	(= (price item24) 34)
	(= (price item25) 77)
	(= (price item26) 92)
	(= (price item27) 15)
	(= (price item28) 92)
	(= (price item29) 19)
	(= (price item30) 82)
	(= (price item31) 98)
	(= (price item32) 57)
	(= (price item33) 42)
	(= (price item34) 70)
	(= (price item35) 60)
	(= (price item36) 11)
	(= (price item37) 70)
	(= (price item38) 96)
	(= (price item39) 62)
	(= (price item40) 58)
	(= (price item41) 44)
	(= (price item42) 89)
	(= (price item43) 30)
	(= (price item44) 98)
	(= (price item45) 63)
	(= (price item46) 48)
	(= (price item47) 15)
	(= (price item48) 24)
	(= (price item49) 49)
	(= (balance currency0) 2458)
	(= (balance currency1) 1899)
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

