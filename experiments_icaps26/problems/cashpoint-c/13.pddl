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
	item50 - item
	item51 - item
	item52 - item
	item53 - item
	item54 - item
	item55 - item
	item56 - item
	item57 - item
	item58 - item
	item59 - item
	item60 - item
	item61 - item
	item62 - item
	item63 - item
	item64 - item
	item65 - item
	item66 - item
	item67 - item
	item68 - item
	item69 - item
	item70 - item
	item71 - item
	item72 - item
	item73 - item
	item74 - item
	item75 - item
	item76 - item
	item77 - item
	item78 - item
	item79 - item
	item80 - item
	item81 - item
	item82 - item
	item83 - item
	item84 - item
	item85 - item
	item86 - item
	item87 - item
	item88 - item
	item89 - item
	item90 - item
	item91 - item
	item92 - item
	item93 - item
	item94 - item
	item95 - item
	item96 - item
	item97 - item
	item98 - item
	item99 - item
)

(:init
	(at location30)
	(canwithdraw location30)
	(canwithdraw location30)
	(canwithdraw location30)
	(canwithdraw location30)
	(canwithdraw location30)
	(canwithdraw location30)
	(canwithdraw location30)
	(canwithdraw location30)
	(canwithdraw location30)
	(canwithdraw location30)
	(canwithdraw location30)
	(canwithdraw location30)
	(canwithdraw location30)
	(canwithdraw location30)
	(canwithdraw location30)
	(canwithdraw location30)
	(canbuy location28 item0)
	(canbuy location7 item1)
	(canbuy location40 item2)
	(canbuy location13 item3)
	(canbuy location9 item4)
	(canbuy location27 item5)
	(canbuy location36 item6)
	(canbuy location25 item7)
	(canbuy location19 item8)
	(canbuy location14 item9)
	(canbuy location33 item10)
	(canbuy location19 item11)
	(canbuy location9 item12)
	(canbuy location20 item13)
	(canbuy location3 item14)
	(canbuy location33 item15)
	(canbuy location39 item16)
	(canbuy location18 item17)
	(canbuy location9 item18)
	(canbuy location23 item19)
	(canbuy location40 item20)
	(canbuy location4 item21)
	(canbuy location16 item22)
	(canbuy location25 item23)
	(canbuy location44 item24)
	(canbuy location7 item25)
	(canbuy location20 item26)
	(canbuy location19 item27)
	(canbuy location41 item28)
	(canbuy location9 item29)
	(canbuy location24 item30)
	(canbuy location34 item31)
	(canbuy location1 item32)
	(canbuy location18 item33)
	(canbuy location27 item34)
	(canbuy location27 item35)
	(canbuy location12 item36)
	(canbuy location6 item37)
	(canbuy location26 item38)
	(canbuy location26 item39)
	(canbuy location9 item40)
	(canbuy location38 item41)
	(canbuy location43 item42)
	(canbuy location41 item43)
	(canbuy location43 item44)
	(canbuy location44 item45)
	(canbuy location3 item46)
	(canbuy location23 item47)
	(canbuy location36 item48)
	(canbuy location34 item49)
	(canbuy location36 item50)
	(canbuy location43 item51)
	(canbuy location12 item52)
	(canbuy location25 item53)
	(canbuy location4 item54)
	(canbuy location6 item55)
	(canbuy location40 item56)
	(canbuy location40 item57)
	(canbuy location41 item58)
	(canbuy location18 item59)
	(canbuy location2 item60)
	(canbuy location13 item61)
	(canbuy location19 item62)
	(canbuy location5 item63)
	(canbuy location49 item64)
	(canbuy location16 item65)
	(canbuy location13 item66)
	(canbuy location49 item67)
	(canbuy location20 item68)
	(canbuy location14 item69)
	(canbuy location26 item70)
	(canbuy location40 item71)
	(canbuy location5 item72)
	(canbuy location23 item73)
	(canbuy location26 item74)
	(canbuy location24 item75)
	(canbuy location28 item76)
	(canbuy location34 item77)
	(canbuy location49 item78)
	(canbuy location20 item79)
	(canbuy location1 item80)
	(canbuy location27 item81)
	(canbuy location16 item82)
	(canbuy location3 item83)
	(canbuy location18 item84)
	(canbuy location18 item85)
	(canbuy location18 item86)
	(canbuy location30 item87)
	(canbuy location44 item88)
	(canbuy location20 item89)
	(canbuy location33 item90)
	(canbuy location28 item91)
	(canbuy location13 item92)
	(canbuy location36 item93)
	(canbuy location43 item94)
	(canbuy location9 item95)
	(canbuy location30 item96)
	(canbuy location26 item97)
	(canbuy location6 item98)
	(canbuy location38 item99)
	(currencyOf item0 currency1)
	(currencyOf item1 currency2)
	(currencyOf item2 currency0)
	(currencyOf item3 currency0)
	(currencyOf item4 currency2)
	(currencyOf item5 currency0)
	(currencyOf item6 currency4)
	(currencyOf item7 currency1)
	(currencyOf item8 currency4)
	(currencyOf item9 currency4)
	(currencyOf item10 currency2)
	(currencyOf item11 currency3)
	(currencyOf item12 currency4)
	(currencyOf item13 currency4)
	(currencyOf item14 currency0)
	(currencyOf item15 currency1)
	(currencyOf item16 currency4)
	(currencyOf item17 currency3)
	(currencyOf item18 currency3)
	(currencyOf item19 currency1)
	(currencyOf item20 currency0)
	(currencyOf item21 currency4)
	(currencyOf item22 currency2)
	(currencyOf item23 currency3)
	(currencyOf item24 currency1)
	(currencyOf item25 currency2)
	(currencyOf item26 currency4)
	(currencyOf item27 currency4)
	(currencyOf item28 currency0)
	(currencyOf item29 currency4)
	(currencyOf item30 currency2)
	(currencyOf item31 currency0)
	(currencyOf item32 currency0)
	(currencyOf item33 currency3)
	(currencyOf item34 currency1)
	(currencyOf item35 currency2)
	(currencyOf item36 currency0)
	(currencyOf item37 currency1)
	(currencyOf item38 currency1)
	(currencyOf item39 currency2)
	(currencyOf item40 currency3)
	(currencyOf item41 currency4)
	(currencyOf item42 currency3)
	(currencyOf item43 currency2)
	(currencyOf item44 currency2)
	(currencyOf item45 currency2)
	(currencyOf item46 currency3)
	(currencyOf item47 currency4)
	(currencyOf item48 currency1)
	(currencyOf item49 currency2)
	(currencyOf item50 currency0)
	(currencyOf item51 currency4)
	(currencyOf item52 currency0)
	(currencyOf item53 currency4)
	(currencyOf item54 currency4)
	(currencyOf item55 currency2)
	(currencyOf item56 currency3)
	(currencyOf item57 currency2)
	(currencyOf item58 currency0)
	(currencyOf item59 currency2)
	(currencyOf item60 currency4)
	(currencyOf item61 currency4)
	(currencyOf item62 currency1)
	(currencyOf item63 currency1)
	(currencyOf item64 currency3)
	(currencyOf item65 currency2)
	(currencyOf item66 currency3)
	(currencyOf item67 currency1)
	(currencyOf item68 currency0)
	(currencyOf item69 currency3)
	(currencyOf item70 currency0)
	(currencyOf item71 currency4)
	(currencyOf item72 currency1)
	(currencyOf item73 currency0)
	(currencyOf item74 currency4)
	(currencyOf item75 currency0)
	(currencyOf item76 currency3)
	(currencyOf item77 currency3)
	(currencyOf item78 currency0)
	(currencyOf item79 currency3)
	(currencyOf item80 currency1)
	(currencyOf item81 currency0)
	(currencyOf item82 currency3)
	(currencyOf item83 currency3)
	(currencyOf item84 currency1)
	(currencyOf item85 currency1)
	(currencyOf item86 currency4)
	(currencyOf item87 currency2)
	(currencyOf item88 currency3)
	(currencyOf item89 currency1)
	(currencyOf item90 currency1)
	(currencyOf item91 currency0)
	(currencyOf item92 currency0)
	(currencyOf item93 currency3)
	(currencyOf item94 currency2)
	(currencyOf item95 currency0)
	(currencyOf item96 currency2)
	(currencyOf item97 currency2)
	(currencyOf item98 currency3)
	(currencyOf item99 currency2)
	(= (inpocket currency0) 0)
	(= (inpocket currency1) 0)
	(= (inpocket currency2) 0)
	(= (inpocket currency3) 0)
	(= (inpocket currency4) 0)
	(= (currency_goal currency0) 52)
	(= (currency_goal currency1) 10)
	(= (currency_goal currency2) 49)
	(= (currency_goal currency3) 100)
	(= (currency_goal currency4) 44)
	(= (price item0) 10)
	(= (price item1) 82)
	(= (price item2) 50)
	(= (price item3) 87)
	(= (price item4) 67)
	(= (price item5) 30)
	(= (price item6) 15)
	(= (price item7) 49)
	(= (price item8) 32)
	(= (price item9) 70)
	(= (price item10) 86)
	(= (price item11) 60)
	(= (price item12) 67)
	(= (price item13) 73)
	(= (price item14) 41)
	(= (price item15) 56)
	(= (price item16) 78)
	(= (price item17) 53)
	(= (price item18) 32)
	(= (price item19) 73)
	(= (price item20) 95)
	(= (price item21) 36)
	(= (price item22) 63)
	(= (price item23) 86)
	(= (price item24) 63)
	(= (price item25) 42)
	(= (price item26) 79)
	(= (price item27) 74)
	(= (price item28) 84)
	(= (price item29) 77)
	(= (price item30) 90)
	(= (price item31) 35)
	(= (price item32) 75)
	(= (price item33) 33)
	(= (price item34) 43)
	(= (price item35) 16)
	(= (price item36) 35)
	(= (price item37) 19)
	(= (price item38) 46)
	(= (price item39) 72)
	(= (price item40) 24)
	(= (price item41) 93)
	(= (price item42) 89)
	(= (price item43) 94)
	(= (price item44) 36)
	(= (price item45) 82)
	(= (price item46) 23)
	(= (price item47) 69)
	(= (price item48) 31)
	(= (price item49) 76)
	(= (price item50) 43)
	(= (price item51) 91)
	(= (price item52) 85)
	(= (price item53) 94)
	(= (price item54) 59)
	(= (price item55) 62)
	(= (price item56) 52)
	(= (price item57) 46)
	(= (price item58) 58)
	(= (price item59) 60)
	(= (price item60) 50)
	(= (price item61) 96)
	(= (price item62) 100)
	(= (price item63) 65)
	(= (price item64) 41)
	(= (price item65) 62)
	(= (price item66) 30)
	(= (price item67) 14)
	(= (price item68) 44)
	(= (price item69) 80)
	(= (price item70) 73)
	(= (price item71) 57)
	(= (price item72) 72)
	(= (price item73) 45)
	(= (price item74) 33)
	(= (price item75) 92)
	(= (price item76) 11)
	(= (price item77) 80)
	(= (price item78) 71)
	(= (price item79) 63)
	(= (price item80) 62)
	(= (price item81) 100)
	(= (price item82) 29)
	(= (price item83) 18)
	(= (price item84) 43)
	(= (price item85) 18)
	(= (price item86) 10)
	(= (price item87) 29)
	(= (price item88) 24)
	(= (price item89) 16)
	(= (price item90) 24)
	(= (price item91) 94)
	(= (price item92) 40)
	(= (price item93) 69)
	(= (price item94) 50)
	(= (price item95) 52)
	(= (price item96) 70)
	(= (price item97) 59)
	(= (price item98) 84)
	(= (price item99) 43)
	(= (balance currency0) 2072)
	(= (balance currency1) 1221)
	(= (balance currency2) 2004)
	(= (balance currency3) 1622)
	(= (balance currency4) 1946)
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
	(bought item50)
	(bought item51)
	(bought item52)
	(bought item53)
	(bought item54)
	(bought item55)
	(bought item56)
	(bought item57)
	(bought item58)
	(bought item59)
	(bought item60)
	(bought item61)
	(bought item62)
	(bought item63)
	(bought item64)
	(bought item65)
	(bought item66)
	(bought item67)
	(bought item68)
	(bought item69)
	(bought item70)
	(bought item71)
	(bought item72)
	(bought item73)
	(bought item74)
	(bought item75)
	(bought item76)
	(bought item77)
	(bought item78)
	(bought item79)
	(bought item80)
	(bought item81)
	(bought item82)
	(bought item83)
	(bought item84)
	(bought item85)
	(bought item86)
	(bought item87)
	(bought item88)
	(bought item89)
	(bought item90)
	(bought item91)
	(bought item92)
	(bought item93)
	(bought item94)
	(bought item95)
	(bought item96)
	(bought item97)
	(bought item98)
	(bought item99)
	(have_enough currency0)
	(have_enough currency1)
	(have_enough currency2)
	(have_enough currency3)
	(have_enough currency4)
))
)

