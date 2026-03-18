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
	(at location1)
	(canwithdraw location1)
	(canwithdraw location1)
	(canwithdraw location1)
	(canwithdraw location1)
	(canwithdraw location1)
	(canwithdraw location1)
	(canwithdraw location1)
	(canwithdraw location1)
	(canwithdraw location1)
	(canwithdraw location1)
	(canwithdraw location1)
	(canwithdraw location1)
	(canwithdraw location1)
	(canwithdraw location1)
	(canwithdraw location1)
	(canwithdraw location1)
	(canbuy location45 item0)
	(canbuy location21 item1)
	(canbuy location11 item2)
	(canbuy location46 item3)
	(canbuy location18 item4)
	(canbuy location8 item5)
	(canbuy location35 item6)
	(canbuy location39 item7)
	(canbuy location25 item8)
	(canbuy location31 item9)
	(canbuy location35 item10)
	(canbuy location29 item11)
	(canbuy location28 item12)
	(canbuy location27 item13)
	(canbuy location20 item14)
	(canbuy location22 item15)
	(canbuy location46 item16)
	(canbuy location41 item17)
	(canbuy location30 item18)
	(canbuy location22 item19)
	(canbuy location22 item20)
	(canbuy location13 item21)
	(canbuy location39 item22)
	(canbuy location25 item23)
	(canbuy location22 item24)
	(canbuy location27 item25)
	(canbuy location2 item26)
	(canbuy location19 item27)
	(canbuy location33 item28)
	(canbuy location40 item29)
	(canbuy location16 item30)
	(canbuy location2 item31)
	(canbuy location37 item32)
	(canbuy location28 item33)
	(canbuy location28 item34)
	(canbuy location40 item35)
	(canbuy location33 item36)
	(canbuy location20 item37)
	(canbuy location31 item38)
	(canbuy location17 item39)
	(canbuy location46 item40)
	(canbuy location13 item41)
	(canbuy location45 item42)
	(canbuy location22 item43)
	(canbuy location19 item44)
	(canbuy location33 item45)
	(canbuy location13 item46)
	(canbuy location41 item47)
	(canbuy location12 item48)
	(canbuy location41 item49)
	(currencyOf item0 currency3)
	(currencyOf item1 currency2)
	(currencyOf item2 currency1)
	(currencyOf item3 currency4)
	(currencyOf item4 currency3)
	(currencyOf item5 currency3)
	(currencyOf item6 currency3)
	(currencyOf item7 currency3)
	(currencyOf item8 currency2)
	(currencyOf item9 currency4)
	(currencyOf item10 currency2)
	(currencyOf item11 currency4)
	(currencyOf item12 currency0)
	(currencyOf item13 currency1)
	(currencyOf item14 currency2)
	(currencyOf item15 currency0)
	(currencyOf item16 currency2)
	(currencyOf item17 currency2)
	(currencyOf item18 currency0)
	(currencyOf item19 currency3)
	(currencyOf item20 currency0)
	(currencyOf item21 currency4)
	(currencyOf item22 currency1)
	(currencyOf item23 currency3)
	(currencyOf item24 currency2)
	(currencyOf item25 currency0)
	(currencyOf item26 currency3)
	(currencyOf item27 currency0)
	(currencyOf item28 currency0)
	(currencyOf item29 currency4)
	(currencyOf item30 currency3)
	(currencyOf item31 currency4)
	(currencyOf item32 currency2)
	(currencyOf item33 currency3)
	(currencyOf item34 currency2)
	(currencyOf item35 currency1)
	(currencyOf item36 currency2)
	(currencyOf item37 currency2)
	(currencyOf item38 currency0)
	(currencyOf item39 currency4)
	(currencyOf item40 currency1)
	(currencyOf item41 currency3)
	(currencyOf item42 currency1)
	(currencyOf item43 currency1)
	(currencyOf item44 currency1)
	(currencyOf item45 currency4)
	(currencyOf item46 currency2)
	(currencyOf item47 currency3)
	(currencyOf item48 currency3)
	(currencyOf item49 currency1)
	(= (inpocket currency0) 0)
	(= (inpocket currency1) 0)
	(= (inpocket currency2) 0)
	(= (inpocket currency3) 0)
	(= (inpocket currency4) 0)
	(= (currency_goal currency0) 84)
	(= (currency_goal currency1) 60)
	(= (currency_goal currency2) 14)
	(= (currency_goal currency3) 87)
	(= (currency_goal currency4) 38)
	(= (price item0) 17)
	(= (price item1) 43)
	(= (price item2) 83)
	(= (price item3) 41)
	(= (price item4) 94)
	(= (price item5) 32)
	(= (price item6) 99)
	(= (price item7) 69)
	(= (price item8) 89)
	(= (price item9) 90)
	(= (price item10) 37)
	(= (price item11) 88)
	(= (price item12) 86)
	(= (price item13) 18)
	(= (price item14) 79)
	(= (price item15) 69)
	(= (price item16) 15)
	(= (price item17) 81)
	(= (price item18) 47)
	(= (price item19) 79)
	(= (price item20) 47)
	(= (price item21) 32)
	(= (price item22) 96)
	(= (price item23) 83)
	(= (price item24) 92)
	(= (price item25) 89)
	(= (price item26) 98)
	(= (price item27) 52)
	(= (price item28) 31)
	(= (price item29) 73)
	(= (price item30) 78)
	(= (price item31) 80)
	(= (price item32) 42)
	(= (price item33) 38)
	(= (price item34) 92)
	(= (price item35) 83)
	(= (price item36) 13)
	(= (price item37) 48)
	(= (price item38) 39)
	(= (price item39) 98)
	(= (price item40) 27)
	(= (price item41) 55)
	(= (price item42) 28)
	(= (price item43) 74)
	(= (price item44) 11)
	(= (price item45) 59)
	(= (price item46) 92)
	(= (price item47) 36)
	(= (price item48) 41)
	(= (price item49) 52)
	(= (balance currency0) 816)
	(= (balance currency1) 798)
	(= (balance currency2) 1106)
	(= (balance currency3) 1359)
	(= (balance currency4) 898)
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

