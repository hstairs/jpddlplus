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
	currency2 - currency
	currency3 - currency
	currency4 - currency
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
	(at location9)
	(canwithdraw location9)
	(canwithdraw location9)
	(canwithdraw location9)
	(canwithdraw location9)
	(canwithdraw location9)
	(canwithdraw location9)
	(canwithdraw location9)
	(canwithdraw location9)
	(canwithdraw location9)
	(canwithdraw location9)
	(canwithdraw location9)
	(canwithdraw location9)
	(canwithdraw location9)
	(canwithdraw location9)
	(canwithdraw location9)
	(canwithdraw location9)
	(canbuy location23 item0)
	(canbuy location4 item1)
	(canbuy location36 item2)
	(canbuy location33 item3)
	(canbuy location20 item4)
	(canbuy location36 item5)
	(canbuy location13 item6)
	(canbuy location49 item7)
	(canbuy location15 item8)
	(canbuy location35 item9)
	(canbuy location49 item10)
	(canbuy location11 item11)
	(canbuy location18 item12)
	(canbuy location23 item13)
	(canbuy location35 item14)
	(canbuy location13 item15)
	(canbuy location5 item16)
	(canbuy location8 item17)
	(canbuy location10 item18)
	(canbuy location35 item19)
	(canbuy location38 item20)
	(canbuy location38 item21)
	(canbuy location29 item22)
	(canbuy location35 item23)
	(canbuy location44 item24)
	(canbuy location44 item25)
	(canbuy location20 item26)
	(canbuy location4 item27)
	(canbuy location11 item28)
	(canbuy location40 item29)
	(canbuy location18 item30)
	(canbuy location47 item31)
	(canbuy location36 item32)
	(canbuy location13 item33)
	(canbuy location9 item34)
	(canbuy location22 item35)
	(canbuy location10 item36)
	(canbuy location49 item37)
	(canbuy location23 item38)
	(canbuy location47 item39)
	(canbuy location4 item40)
	(canbuy location3 item41)
	(canbuy location4 item42)
	(canbuy location11 item43)
	(canbuy location33 item44)
	(canbuy location11 item45)
	(canbuy location19 item46)
	(canbuy location15 item47)
	(canbuy location9 item48)
	(canbuy location23 item49)
	(currencyOf item0 currency3)
	(currencyOf item1 currency4)
	(currencyOf item2 currency0)
	(currencyOf item3 currency2)
	(currencyOf item4 currency1)
	(currencyOf item5 currency1)
	(currencyOf item6 currency0)
	(currencyOf item7 currency2)
	(currencyOf item8 currency0)
	(currencyOf item9 currency1)
	(currencyOf item10 currency3)
	(currencyOf item11 currency3)
	(currencyOf item12 currency4)
	(currencyOf item13 currency1)
	(currencyOf item14 currency4)
	(currencyOf item15 currency4)
	(currencyOf item16 currency3)
	(currencyOf item17 currency3)
	(currencyOf item18 currency2)
	(currencyOf item19 currency0)
	(currencyOf item20 currency3)
	(currencyOf item21 currency2)
	(currencyOf item22 currency0)
	(currencyOf item23 currency1)
	(currencyOf item24 currency2)
	(currencyOf item25 currency0)
	(currencyOf item26 currency4)
	(currencyOf item27 currency1)
	(currencyOf item28 currency4)
	(currencyOf item29 currency3)
	(currencyOf item30 currency4)
	(currencyOf item31 currency4)
	(currencyOf item32 currency0)
	(currencyOf item33 currency4)
	(currencyOf item34 currency0)
	(currencyOf item35 currency1)
	(currencyOf item36 currency1)
	(currencyOf item37 currency0)
	(currencyOf item38 currency3)
	(currencyOf item39 currency4)
	(currencyOf item40 currency1)
	(currencyOf item41 currency3)
	(currencyOf item42 currency4)
	(currencyOf item43 currency1)
	(currencyOf item44 currency2)
	(currencyOf item45 currency3)
	(currencyOf item46 currency4)
	(currencyOf item47 currency4)
	(currencyOf item48 currency1)
	(currencyOf item49 currency1)
	(= (inpocket currency0) 0)
	(= (inpocket currency1) 0)
	(= (inpocket currency2) 0)
	(= (inpocket currency3) 0)
	(= (inpocket currency4) 0)
	(= (currency_goal currency0) 89)
	(= (currency_goal currency1) 30)
	(= (currency_goal currency2) 13)
	(= (currency_goal currency3) 77)
	(= (currency_goal currency4) 59)
	(= (price item0) 43)
	(= (price item1) 78)
	(= (price item2) 12)
	(= (price item3) 12)
	(= (price item4) 13)
	(= (price item5) 63)
	(= (price item6) 88)
	(= (price item7) 20)
	(= (price item8) 94)
	(= (price item9) 38)
	(= (price item10) 75)
	(= (price item11) 61)
	(= (price item12) 58)
	(= (price item13) 48)
	(= (price item14) 18)
	(= (price item15) 100)
	(= (price item16) 19)
	(= (price item17) 41)
	(= (price item18) 12)
	(= (price item19) 81)
	(= (price item20) 37)
	(= (price item21) 81)
	(= (price item22) 23)
	(= (price item23) 94)
	(= (price item24) 22)
	(= (price item25) 92)
	(= (price item26) 98)
	(= (price item27) 20)
	(= (price item28) 26)
	(= (price item29) 100)
	(= (price item30) 68)
	(= (price item31) 94)
	(= (price item32) 63)
	(= (price item33) 77)
	(= (price item34) 42)
	(= (price item35) 77)
	(= (price item36) 85)
	(= (price item37) 95)
	(= (price item38) 86)
	(= (price item39) 34)
	(= (price item40) 17)
	(= (price item41) 17)
	(= (price item42) 84)
	(= (price item43) 44)
	(= (price item44) 76)
	(= (price item45) 43)
	(= (price item46) 33)
	(= (price item47) 93)
	(= (price item48) 87)
	(= (price item49) 55)
	(= (balance currency0) 1018)
	(= (balance currency1) 1006)
	(= (balance currency2) 354)
	(= (balance currency3) 898)
	(= (balance currency4) 1380)
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
	(have_enough currency2)
	(have_enough currency3)
	(have_enough currency4)
))
)

