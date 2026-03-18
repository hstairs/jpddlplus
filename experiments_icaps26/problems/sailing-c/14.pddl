(define (problem sailing)
(:domain sailing)
(:objects 
	boat0 - boat
	boat0 - boat
	boat1 - boat
	boat1 - boat
	boat2 - boat
	boat2 - boat
	person0 - person
)

        (:init
	(= (x boat0) -1)
	(= (y boat0) -1)
	(= (x boat1) -1)
	(= (y boat1) 0)
	(= (x boat2) -1)
	(= (y boat2) 1)
	(= (d person0) 112)
	(= (min_x) -150)
	(= (min_y) -150)
	(= (max_x) 150)
	(= (max_y) 150)
	
	
	
)
(:goal (and
	(saved person0)
))
)

