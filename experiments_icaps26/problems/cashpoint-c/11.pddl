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
	(at location44)
	(canwithdraw location44)
	(canwithdraw location44)
	(canwithdraw location44)
	(canwithdraw location44)
	(canwithdraw location44)
	(canwithdraw location44)
	(canwithdraw location44)
	(canwithdraw location44)
	(canwithdraw location44)
	(canwithdraw location44)
	(canwithdraw location44)
	(canwithdraw location44)
	(canwithdraw location44)
	(canwithdraw location44)
	(canwithdraw location44)
	(canwithdraw location44)
	(canbuy location14 item0)
	(canbuy location42 item1)
	(canbuy location14 item2)
	(canbuy location32 item3)
	(canbuy location28 item4)
	(canbuy location33 item5)
	(canbuy location43 item6)
	(canbuy location35 item7)
	(canbuy location35 item8)
	(canbuy location36 item9)
	(canbuy location45 item10)
	(canbuy location5 item11)
	(canbuy location42 item12)
	(canbuy location25 item13)
	(canbuy location2 item14)
	(canbuy location14 item15)
	(canbuy location21 item16)
	(canbuy location33 item17)
	(canbuy location22 item18)
	(canbuy location24 item19)
	(canbuy location35 item20)
	(canbuy location40 item21)
	(canbuy location31 item22)
	(canbuy location9 item23)
	(canbuy location48 item24)
	(canbuy location14 item25)
	(canbuy location49 item26)
	(canbuy location28 item27)
	(canbuy location25 item28)
	(canbuy location16 item29)
	(canbuy location5 item30)
	(canbuy location35 item31)
	(canbuy location43 item32)
	(canbuy location16 item33)
	(canbuy location21 item34)
	(canbuy location0 item35)
	(canbuy location2 item36)
	(canbuy location38 item37)
	(canbuy location8 item38)
	(canbuy location27 item39)
	(canbuy location41 item40)
	(canbuy location31 item41)
	(canbuy location45 item42)
	(canbuy location20 item43)
	(canbuy location2 item44)
	(canbuy location35 item45)
	(canbuy location7 item46)
	(canbuy location24 item47)
	(canbuy location19 item48)
	(canbuy location9 item49)
	(currencyOf item0 currency0)
	(currencyOf item1 currency1)
	(currencyOf item2 currency1)
	(currencyOf item3 currency0)
	(currencyOf item4 currency1)
	(currencyOf item5 currency1)
	(currencyOf item6 currency0)
	(currencyOf item7 currency1)
	(currencyOf item8 currency0)
	(currencyOf item9 currency1)
	(currencyOf item10 currency1)
	(currencyOf item11 currency0)
	(currencyOf item12 currency1)
	(currencyOf item13 currency1)
	(currencyOf item14 currency0)
	(currencyOf item15 currency1)
	(currencyOf item16 currency0)
	(currencyOf item17 currency1)
	(currencyOf item18 currency0)
	(currencyOf item19 currency1)
	(currencyOf item20 currency1)
	(currencyOf item21 currency0)
	(currencyOf item22 currency0)
	(currencyOf item23 currency0)
	(currencyOf item24 currency0)
	(currencyOf item25 currency0)
	(currencyOf item26 currency0)
	(currencyOf item27 currency1)
	(currencyOf item28 currency1)
	(currencyOf item29 currency0)
	(currencyOf item30 currency0)
	(currencyOf item31 currency1)
	(currencyOf item32 currency0)
	(currencyOf item33 currency0)
	(currencyOf item34 currency1)
	(currencyOf item35 currency1)
	(currencyOf item36 currency1)
	(currencyOf item37 currency0)
	(currencyOf item38 currency0)
	(currencyOf item39 currency1)
	(currencyOf item40 currency1)
	(currencyOf item41 currency1)
	(currencyOf item42 currency0)
	(currencyOf item43 currency0)
	(currencyOf item44 currency0)
	(currencyOf item45 currency1)
	(currencyOf item46 currency0)
	(currencyOf item47 currency1)
	(currencyOf item48 currency1)
	(currencyOf item49 currency1)
	(= (inpocket currency0) 0)
	(= (inpocket currency1) 0)
	(= (currency_goal currency0) 95)
	(= (currency_goal currency1) 87)
	(= (price item0) 92)
	(= (price item1) 22)
	(= (price item2) 94)
	(= (price item3) 88)
	(= (price item4) 14)
	(= (price item5) 83)
	(= (price item6) 55)
	(= (price item7) 92)
	(= (price item8) 32)
	(= (price item9) 43)
	(= (price item10) 56)
	(= (price item11) 38)
	(= (price item12) 95)
	(= (price item13) 21)
	(= (price item14) 84)
	(= (price item15) 48)
	(= (price item16) 17)
	(= (price item17) 72)
	(= (price item18) 93)
	(= (price item19) 26)
	(= (price item20) 21)
	(= (price item21) 64)
	(= (price item22) 15)
	(= (price item23) 41)
	(= (price item24) 48)
	(= (price item25) 88)
	(= (price item26) 23)
	(= (price item27) 53)
	(= (price item28) 83)
	(= (price item29) 52)
	(= (price item30) 43)
	(= (price item31) 42)
	(= (price item32) 48)
	(= (price item33) 66)
	(= (price item34) 52)
	(= (price item35) 44)
	(= (price item36) 38)
	(= (price item37) 89)
	(= (price item38) 46)
	(= (price item39) 25)
	(= (price item40) 85)
	(= (price item41) 47)
	(= (price item42) 31)
	(= (price item43) 51)
	(= (price item44) 81)
	(= (price item45) 63)
	(= (price item46) 47)
	(= (price item47) 84)
	(= (price item48) 77)
	(= (price item49) 42)
	(= (balance currency0) 2140)
	(= (balance currency1) 2264)
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

