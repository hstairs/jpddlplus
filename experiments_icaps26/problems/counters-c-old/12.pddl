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
)

(:init
	(= (max_int) 118)
	(= (value counter0) 63)
	(= (value counter1) 47)
	(= (value counter2) 77)
	(= (value counter3) 15)
	(= (value counter4) 92)
	(= (value counter5) 47)
	(= (value counter6) 33)
	(= (value counter7) 60)
	(= (value counter8) 21)
	(= (value counter9) 115)
	(= (value counter10) 13)
	(= (value counter11) 52)
	(= (value counter12) 28)
	(= (value counter13) 59)
	(= (value counter14) 13)
	(= (value counter15) 1)
	(= (value counter16) 83)
	(= (value counter17) 40)
	(= (value counter18) 105)
	(= (value counter19) 0)
	(= (value counter20) 40)
	(= (value counter21) 104)
	(= (value counter22) 57)
	(= (value counter23) 113)
	(= (value counter24) 72)
	(= (value counter25) 50)
	(= (value counter26) 108)
	(= (value counter27) 102)
	(= (value counter28) 114)
	(= (value counter29) 2)
	(= (value counter30) 98)
	(= (value counter31) 97)
	(= (value counter32) 107)
	(= (value counter33) 87)
	(= (value counter34) 38)
	(= (value counter35) 109)
	(= (value counter36) 49)
	(= (value counter37) 73)
	(= (value counter38) 93)
	(= (value counter39) 116)
	(= (value counter40) 29)
	(= (value counter41) 29)
	(= (value counter42) 26)
	(= (value counter43) 22)
	(= (value counter44) 54)
	(= (value counter45) 60)
	(= (value counter46) 3)
	(= (value counter47) 79)
	(= (value counter48) 84)
	(= (value counter49) 33)
	(= (value counter50) 40)
	(= (value counter51) 60)
	(= (value counter52) 6)
	(= (value counter53) 6)
	(= (value counter54) 66)
	(= (value counter55) 22)
	(= (value counter56) 115)
	(= (value counter57) 59)
	(= (value counter58) 19)
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
))
)

