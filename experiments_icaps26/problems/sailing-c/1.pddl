(define (problem sailing)
(:domain sailing)
(:objects 
	boat0 - boat
	boat0 - boat
	person0 - person
	person1 - person
)

        (:init
	(= (x boat0) -1)
	(= (y boat0) -1)
	(= (d person0) -115)
	(= (d person1) 93)
	(= (min_x) -150)
	(= (min_y) -150)
	(= (max_x) 150)
	(= (max_y) 150)
	
	
	
)
(:goal (and
	(saved person0)
	(saved person1)
))
)

