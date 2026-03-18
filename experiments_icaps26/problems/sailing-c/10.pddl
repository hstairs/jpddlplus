(define (problem sailing)
(:domain sailing)
(:objects 
	boat0 - boat
	boat0 - boat
	boat1 - boat
	boat1 - boat
	person0 - person
	person1 - person
	person2 - person
	person3 - person
)


        (:init
	(= (x boat0) -1)
	(= (y boat0) -1)
	(= (x boat1) -1)
	(= (y boat1) 0)
	(= (d person0) -110)
	(= (d person1) -73)
	(= (d person2) 112)
	(= (d person3) 89)
	(= (min_x) -150)
	(= (min_y) -150)
	(= (max_x) 150)
	(= (max_y) 150)
	
	
	
)
(:goal (and
	(saved person0)
	(saved person1)
	(saved person2)
	(saved person3)
))
)

