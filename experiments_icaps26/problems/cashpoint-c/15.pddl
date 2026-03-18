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
	currency5 - currency
	currency6 - currency
	currency7 - currency
	currency8 - currency
	currency9 - currency
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
	(at location2)
	(canwithdraw location2)
	(canwithdraw location2)
	(canwithdraw location2)
	(canwithdraw location2)
	(canwithdraw location2)
	(canwithdraw location2)
	(canwithdraw location2)
	(canwithdraw location2)
	(canwithdraw location2)
	(canwithdraw location2)
	(canwithdraw location2)
	(canwithdraw location2)
	(canwithdraw location2)
	(canwithdraw location2)
	(canwithdraw location2)
	(canwithdraw location2)
	(canbuy location13 item0)
	(canbuy location31 item1)
	(canbuy location39 item2)
	(canbuy location49 item3)
	(canbuy location0 item4)
	(canbuy location48 item5)
	(canbuy location2 item6)
	(canbuy location16 item7)
	(canbuy location34 item8)
	(canbuy location6 item9)
	(canbuy location30 item10)
	(canbuy location4 item11)
	(canbuy location8 item12)
	(canbuy location22 item13)
	(canbuy location40 item14)
	(canbuy location40 item15)
	(canbuy location20 item16)
	(canbuy location19 item17)
	(canbuy location48 item18)
	(canbuy location8 item19)
	(canbuy location34 item20)
	(canbuy location0 item21)
	(canbuy location22 item22)
	(canbuy location1 item23)
	(canbuy location6 item24)
	(canbuy location20 item25)
	(canbuy location8 item26)
	(canbuy location37 item27)
	(canbuy location13 item28)
	(canbuy location34 item29)
	(canbuy location0 item30)
	(canbuy location1 item31)
	(canbuy location22 item32)
	(canbuy location11 item33)
	(canbuy location34 item34)
	(canbuy location25 item35)
	(canbuy location6 item36)
	(canbuy location16 item37)
	(canbuy location26 item38)
	(canbuy location34 item39)
	(canbuy location16 item40)
	(canbuy location39 item41)
	(canbuy location11 item42)
	(canbuy location25 item43)
	(canbuy location30 item44)
	(canbuy location49 item45)
	(canbuy location47 item46)
	(canbuy location13 item47)
	(canbuy location32 item48)
	(canbuy location34 item49)
	(canbuy location2 item50)
	(canbuy location40 item51)
	(canbuy location26 item52)
	(canbuy location47 item53)
	(canbuy location20 item54)
	(canbuy location46 item55)
	(canbuy location16 item56)
	(canbuy location11 item57)
	(canbuy location32 item58)
	(canbuy location30 item59)
	(canbuy location31 item60)
	(canbuy location33 item61)
	(canbuy location47 item62)
	(canbuy location26 item63)
	(canbuy location45 item64)
	(canbuy location26 item65)
	(canbuy location39 item66)
	(canbuy location14 item67)
	(canbuy location0 item68)
	(canbuy location19 item69)
	(canbuy location22 item70)
	(canbuy location40 item71)
	(canbuy location13 item72)
	(canbuy location2 item73)
	(canbuy location20 item74)
	(canbuy location2 item75)
	(canbuy location33 item76)
	(canbuy location31 item77)
	(canbuy location43 item78)
	(canbuy location3 item79)
	(canbuy location26 item80)
	(canbuy location44 item81)
	(canbuy location37 item82)
	(canbuy location22 item83)
	(canbuy location19 item84)
	(canbuy location4 item85)
	(canbuy location26 item86)
	(canbuy location8 item87)
	(canbuy location45 item88)
	(canbuy location48 item89)
	(canbuy location39 item90)
	(canbuy location49 item91)
	(canbuy location46 item92)
	(canbuy location34 item93)
	(canbuy location49 item94)
	(canbuy location22 item95)
	(canbuy location8 item96)
	(canbuy location14 item97)
	(canbuy location26 item98)
	(canbuy location37 item99)
	(currencyOf item0 currency3)
	(currencyOf item1 currency8)
	(currencyOf item2 currency1)
	(currencyOf item3 currency9)
	(currencyOf item4 currency6)
	(currencyOf item5 currency7)
	(currencyOf item6 currency1)
	(currencyOf item7 currency9)
	(currencyOf item8 currency3)
	(currencyOf item9 currency8)
	(currencyOf item10 currency0)
	(currencyOf item11 currency9)
	(currencyOf item12 currency2)
	(currencyOf item13 currency1)
	(currencyOf item14 currency2)
	(currencyOf item15 currency0)
	(currencyOf item16 currency7)
	(currencyOf item17 currency7)
	(currencyOf item18 currency9)
	(currencyOf item19 currency0)
	(currencyOf item20 currency9)
	(currencyOf item21 currency2)
	(currencyOf item22 currency0)
	(currencyOf item23 currency7)
	(currencyOf item24 currency0)
	(currencyOf item25 currency0)
	(currencyOf item26 currency5)
	(currencyOf item27 currency9)
	(currencyOf item28 currency3)
	(currencyOf item29 currency9)
	(currencyOf item30 currency9)
	(currencyOf item31 currency0)
	(currencyOf item32 currency4)
	(currencyOf item33 currency6)
	(currencyOf item34 currency2)
	(currencyOf item35 currency1)
	(currencyOf item36 currency9)
	(currencyOf item37 currency1)
	(currencyOf item38 currency5)
	(currencyOf item39 currency2)
	(currencyOf item40 currency8)
	(currencyOf item41 currency0)
	(currencyOf item42 currency1)
	(currencyOf item43 currency8)
	(currencyOf item44 currency2)
	(currencyOf item45 currency7)
	(currencyOf item46 currency9)
	(currencyOf item47 currency5)
	(currencyOf item48 currency4)
	(currencyOf item49 currency5)
	(currencyOf item50 currency3)
	(currencyOf item51 currency9)
	(currencyOf item52 currency3)
	(currencyOf item53 currency9)
	(currencyOf item54 currency2)
	(currencyOf item55 currency6)
	(currencyOf item56 currency7)
	(currencyOf item57 currency3)
	(currencyOf item58 currency2)
	(currencyOf item59 currency4)
	(currencyOf item60 currency9)
	(currencyOf item61 currency1)
	(currencyOf item62 currency4)
	(currencyOf item63 currency3)
	(currencyOf item64 currency0)
	(currencyOf item65 currency5)
	(currencyOf item66 currency5)
	(currencyOf item67 currency2)
	(currencyOf item68 currency9)
	(currencyOf item69 currency1)
	(currencyOf item70 currency4)
	(currencyOf item71 currency4)
	(currencyOf item72 currency9)
	(currencyOf item73 currency1)
	(currencyOf item74 currency0)
	(currencyOf item75 currency8)
	(currencyOf item76 currency6)
	(currencyOf item77 currency1)
	(currencyOf item78 currency6)
	(currencyOf item79 currency4)
	(currencyOf item80 currency4)
	(currencyOf item81 currency6)
	(currencyOf item82 currency2)
	(currencyOf item83 currency1)
	(currencyOf item84 currency9)
	(currencyOf item85 currency8)
	(currencyOf item86 currency7)
	(currencyOf item87 currency6)
	(currencyOf item88 currency0)
	(currencyOf item89 currency9)
	(currencyOf item90 currency8)
	(currencyOf item91 currency7)
	(currencyOf item92 currency8)
	(currencyOf item93 currency4)
	(currencyOf item94 currency8)
	(currencyOf item95 currency1)
	(currencyOf item96 currency6)
	(currencyOf item97 currency7)
	(currencyOf item98 currency0)
	(currencyOf item99 currency9)
	(= (inpocket currency0) 0)
	(= (inpocket currency1) 0)
	(= (inpocket currency2) 0)
	(= (inpocket currency3) 0)
	(= (inpocket currency4) 0)
	(= (inpocket currency5) 0)
	(= (inpocket currency6) 0)
	(= (inpocket currency7) 0)
	(= (inpocket currency8) 0)
	(= (inpocket currency9) 0)
	(= (currency_goal currency0) 21)
	(= (currency_goal currency1) 42)
	(= (currency_goal currency2) 64)
	(= (currency_goal currency3) 79)
	(= (currency_goal currency4) 34)
	(= (currency_goal currency5) 71)
	(= (currency_goal currency6) 42)
	(= (currency_goal currency7) 48)
	(= (currency_goal currency8) 29)
	(= (currency_goal currency9) 44)
	(= (price item0) 84)
	(= (price item1) 63)
	(= (price item2) 73)
	(= (price item3) 94)
	(= (price item4) 43)
	(= (price item5) 43)
	(= (price item6) 40)
	(= (price item7) 87)
	(= (price item8) 88)
	(= (price item9) 50)
	(= (price item10) 38)
	(= (price item11) 82)
	(= (price item12) 40)
	(= (price item13) 92)
	(= (price item14) 63)
	(= (price item15) 32)
	(= (price item16) 87)
	(= (price item17) 41)
	(= (price item18) 44)
	(= (price item19) 92)
	(= (price item20) 43)
	(= (price item21) 99)
	(= (price item22) 31)
	(= (price item23) 84)
	(= (price item24) 22)
	(= (price item25) 13)
	(= (price item26) 47)
	(= (price item27) 24)
	(= (price item28) 37)
	(= (price item29) 33)
	(= (price item30) 82)
	(= (price item31) 11)
	(= (price item32) 82)
	(= (price item33) 38)
	(= (price item34) 99)
	(= (price item35) 97)
	(= (price item36) 10)
	(= (price item37) 27)
	(= (price item38) 10)
	(= (price item39) 46)
	(= (price item40) 93)
	(= (price item41) 10)
	(= (price item42) 60)
	(= (price item43) 39)
	(= (price item44) 96)
	(= (price item45) 29)
	(= (price item46) 95)
	(= (price item47) 75)
	(= (price item48) 41)
	(= (price item49) 32)
	(= (price item50) 19)
	(= (price item51) 78)
	(= (price item52) 91)
	(= (price item53) 55)
	(= (price item54) 80)
	(= (price item55) 40)
	(= (price item56) 44)
	(= (price item57) 95)
	(= (price item58) 10)
	(= (price item59) 17)
	(= (price item60) 25)
	(= (price item61) 29)
	(= (price item62) 61)
	(= (price item63) 45)
	(= (price item64) 18)
	(= (price item65) 73)
	(= (price item66) 82)
	(= (price item67) 80)
	(= (price item68) 70)
	(= (price item69) 38)
	(= (price item70) 28)
	(= (price item71) 56)
	(= (price item72) 30)
	(= (price item73) 25)
	(= (price item74) 67)
	(= (price item75) 26)
	(= (price item76) 12)
	(= (price item77) 58)
	(= (price item78) 36)
	(= (price item79) 46)
	(= (price item80) 64)
	(= (price item81) 57)
	(= (price item82) 35)
	(= (price item83) 13)
	(= (price item84) 52)
	(= (price item85) 50)
	(= (price item86) 10)
	(= (price item87) 25)
	(= (price item88) 31)
	(= (price item89) 44)
	(= (price item90) 51)
	(= (price item91) 59)
	(= (price item92) 97)
	(= (price item93) 53)
	(= (price item94) 49)
	(= (price item95) 70)
	(= (price item96) 87)
	(= (price item97) 45)
	(= (price item98) 15)
	(= (price item99) 64)
	(= (balance currency0) 602)
	(= (balance currency1) 996)
	(= (balance currency2) 1068)
	(= (balance currency3) 807)
	(= (balance currency4) 723)
	(= (balance currency5) 585)
	(= (balance currency6) 570)
	(= (balance currency7) 735)
	(= (balance currency8) 820)
	(= (balance currency9) 1584)
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
	(have_enough currency5)
	(have_enough currency6)
	(have_enough currency7)
	(have_enough currency8)
	(have_enough currency9)
))
)

