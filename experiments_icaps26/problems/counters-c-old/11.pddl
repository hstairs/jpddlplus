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
)

(:init
	(= (max_int) 108)
	(= (value counter0) 89)
	(= (value counter1) 56)
	(= (value counter2) 46)
	(= (value counter3) 45)
	(= (value counter4) 10)
	(= (value counter5) 74)
	(= (value counter6) 49)
	(= (value counter7) 20)
	(= (value counter8) 50)
	(= (value counter9) 72)
	(= (value counter10) 40)
	(= (value counter11) 70)
	(= (value counter12) 91)
	(= (value counter13) 88)
	(= (value counter14) 108)
	(= (value counter15) 104)
	(= (value counter16) 77)
	(= (value counter17) 103)
	(= (value counter18) 26)
	(= (value counter19) 60)
	(= (value counter20) 30)
	(= (value counter21) 13)
	(= (value counter22) 69)
	(= (value counter23) 70)
	(= (value counter24) 22)
	(= (value counter25) 101)
	(= (value counter26) 34)
	(= (value counter27) 52)
	(= (value counter28) 4)
	(= (value counter29) 23)
	(= (value counter30) 3)
	(= (value counter31) 20)
	(= (value counter32) 80)
	(= (value counter33) 40)
	(= (value counter34) 22)
	(= (value counter35) 32)
	(= (value counter36) 83)
	(= (value counter37) 67)
	(= (value counter38) 19)
	(= (value counter39) 15)
	(= (value counter40) 41)
	(= (value counter41) 109)
	(= (value counter42) 21)
	(= (value counter43) 33)
	(= (value counter44) 109)
	(= (value counter45) 79)
	(= (value counter46) 5)
	(= (value counter47) 44)
	(= (value counter48) 86)
	(= (value counter49) 11)
	(= (value counter50) 93)
	(= (value counter51) 49)
	(= (value counter52) 62)
	(= (value counter53) 14)
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
))
)

