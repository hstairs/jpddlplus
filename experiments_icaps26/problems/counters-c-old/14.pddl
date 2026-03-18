(define (problem counters)
(:domain counters)
(:objects 
	counter0 - counter
	counter1 - counter
	counter2 - counter
	counter3 - counter
	counter4 - counter
	counter5 - counter
	counter6 - counter
	counter7 - counter
	counter8 - counter
	counter9 - counter
	counter10 - counter
	counter11 - counter
	counter12 - counter
	counter13 - counter
	counter14 - counter
	counter15 - counter
	counter16 - counter
	counter17 - counter
	counter18 - counter
	counter19 - counter
	counter20 - counter
	counter21 - counter
	counter22 - counter
	counter23 - counter
	counter24 - counter
	counter25 - counter
	counter26 - counter
	counter27 - counter
	counter28 - counter
	counter29 - counter
	counter30 - counter
	counter31 - counter
	counter32 - counter
	counter33 - counter
	counter34 - counter
	counter35 - counter
	counter36 - counter
	counter37 - counter
	counter38 - counter
	counter39 - counter
	counter40 - counter
	counter41 - counter
	counter42 - counter
	counter43 - counter
	counter44 - counter
	counter45 - counter
	counter46 - counter
	counter47 - counter
	counter48 - counter
	counter49 - counter
	counter50 - counter
	counter51 - counter
	counter52 - counter
	counter53 - counter
	counter54 - counter
	counter55 - counter
	counter56 - counter
	counter57 - counter
	counter58 - counter
	counter59 - counter
	counter60 - counter
	counter61 - counter
	counter62 - counter
	counter63 - counter
	counter64 - counter
	counter65 - counter
	counter66 - counter
	counter67 - counter
	counter68 - counter
)

(:init
	(= (max_int) 138)
	(= (value counter0) 82)
	(= (value counter1) 60)
	(= (value counter2) 90)
	(= (value counter3) 139)
	(= (value counter4) 22)
	(= (value counter5) 118)
	(= (value counter6) 74)
	(= (value counter7) 53)
	(= (value counter8) 15)
	(= (value counter9) 67)
	(= (value counter10) 39)
	(= (value counter11) 6)
	(= (value counter12) 111)
	(= (value counter13) 83)
	(= (value counter14) 32)
	(= (value counter15) 56)
	(= (value counter16) 47)
	(= (value counter17) 74)
	(= (value counter18) 5)
	(= (value counter19) 11)
	(= (value counter20) 9)
	(= (value counter21) 83)
	(= (value counter22) 77)
	(= (value counter23) 139)
	(= (value counter24) 140)
	(= (value counter25) 62)
	(= (value counter26) 16)
	(= (value counter27) 0)
	(= (value counter28) 39)
	(= (value counter29) 81)
	(= (value counter30) 56)
	(= (value counter31) 127)
	(= (value counter32) 30)
	(= (value counter33) 66)
	(= (value counter34) 36)
	(= (value counter35) 84)
	(= (value counter36) 103)
	(= (value counter37) 76)
	(= (value counter38) 23)
	(= (value counter39) 44)
	(= (value counter40) 74)
	(= (value counter41) 39)
	(= (value counter42) 23)
	(= (value counter43) 116)
	(= (value counter44) 74)
	(= (value counter45) 40)
	(= (value counter46) 140)
	(= (value counter47) 103)
	(= (value counter48) 17)
	(= (value counter49) 5)
	(= (value counter50) 61)
	(= (value counter51) 84)
	(= (value counter52) 25)
	(= (value counter53) 118)
	(= (value counter54) 74)
	(= (value counter55) 44)
	(= (value counter56) 65)
	(= (value counter57) 70)
	(= (value counter58) 7)
	(= (value counter59) 123)
	(= (value counter60) 27)
	(= (value counter61) 63)
	(= (value counter62) 33)
	(= (value counter63) 128)
	(= (value counter64) 31)
	(= (value counter65) 50)
	(= (value counter66) 7)
	(= (value counter67) 17)
	(= (value counter68) 2)
)
(:goal (and
	(<= (+ (value counter0) 1) (value counter1))
	(<= (+ (value counter1) 1) (value counter2))
	(<= (+ (value counter2) 1) (value counter3))
	(<= (+ (value counter3) 1) (value counter4))
	(<= (+ (value counter4) 1) (value counter5))
	(<= (+ (value counter5) 1) (value counter6))
	(<= (+ (value counter6) 1) (value counter7))
	(<= (+ (value counter7) 1) (value counter8))
	(<= (+ (value counter8) 1) (value counter9))
	(<= (+ (value counter9) 1) (value counter10))
	(<= (+ (value counter10) 1) (value counter11))
	(<= (+ (value counter11) 1) (value counter12))
	(<= (+ (value counter12) 1) (value counter13))
	(<= (+ (value counter13) 1) (value counter14))
	(<= (+ (value counter14) 1) (value counter15))
	(<= (+ (value counter15) 1) (value counter16))
	(<= (+ (value counter16) 1) (value counter17))
	(<= (+ (value counter17) 1) (value counter18))
	(<= (+ (value counter18) 1) (value counter19))
	(<= (+ (value counter19) 1) (value counter20))
	(<= (+ (value counter20) 1) (value counter21))
	(<= (+ (value counter21) 1) (value counter22))
	(<= (+ (value counter22) 1) (value counter23))
	(<= (+ (value counter23) 1) (value counter24))
	(<= (+ (value counter24) 1) (value counter25))
	(<= (+ (value counter25) 1) (value counter26))
	(<= (+ (value counter26) 1) (value counter27))
	(<= (+ (value counter27) 1) (value counter28))
	(<= (+ (value counter28) 1) (value counter29))
	(<= (+ (value counter29) 1) (value counter30))
	(<= (+ (value counter30) 1) (value counter31))
	(<= (+ (value counter31) 1) (value counter32))
	(<= (+ (value counter32) 1) (value counter33))
	(<= (+ (value counter33) 1) (value counter34))
	(<= (+ (value counter34) 1) (value counter35))
	(<= (+ (value counter35) 1) (value counter36))
	(<= (+ (value counter36) 1) (value counter37))
	(<= (+ (value counter37) 1) (value counter38))
	(<= (+ (value counter38) 1) (value counter39))
	(<= (+ (value counter39) 1) (value counter40))
	(<= (+ (value counter40) 1) (value counter41))
	(<= (+ (value counter41) 1) (value counter42))
	(<= (+ (value counter42) 1) (value counter43))
	(<= (+ (value counter43) 1) (value counter44))
	(<= (+ (value counter44) 1) (value counter45))
	(<= (+ (value counter45) 1) (value counter46))
	(<= (+ (value counter46) 1) (value counter47))
	(<= (+ (value counter47) 1) (value counter48))
	(<= (+ (value counter48) 1) (value counter49))
	(<= (+ (value counter49) 1) (value counter50))
	(<= (+ (value counter50) 1) (value counter51))
	(<= (+ (value counter51) 1) (value counter52))
	(<= (+ (value counter52) 1) (value counter53))
	(<= (+ (value counter53) 1) (value counter54))
	(<= (+ (value counter54) 1) (value counter55))
	(<= (+ (value counter55) 1) (value counter56))
	(<= (+ (value counter56) 1) (value counter57))
	(<= (+ (value counter57) 1) (value counter58))
	(<= (+ (value counter58) 1) (value counter59))
	(<= (+ (value counter59) 1) (value counter60))
	(<= (+ (value counter60) 1) (value counter61))
	(<= (+ (value counter61) 1) (value counter62))
	(<= (+ (value counter62) 1) (value counter63))
	(<= (+ (value counter63) 1) (value counter64))
	(<= (+ (value counter64) 1) (value counter65))
	(<= (+ (value counter65) 1) (value counter66))
	(<= (+ (value counter66) 1) (value counter67))
	(<= (+ (value counter67) 1) (value counter68))
))
)

