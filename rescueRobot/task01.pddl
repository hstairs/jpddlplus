(define (problem task01)
	(:domain transport)
	(:objects r1 - robot l1 l2 l3 l4 - site)
	(:init 
		;info su connessioni
		(joined l1 l2)
		(= (distance l1 l2) 1)

		(joined l1 l4)
		(= (distance l1 l4) 1)

		(joined l4 l2)
		(= (distance l4 l2) 1)

		(joined l2 l3)
		(= (distance l2 l3) 1)

		(joined l3 l4)
		(= (distance l3 l4) 1)


		(in r1 l1)

		;robot numerical variable
		(= (power r1) 1000)
		(= (powerConsumption r1) 0)
		(= (memory r1) 100)
		(= (memoryConsumption r1) 0)
		(= (time) 0)

		;info su locations
		(=(info l1) 0)
		(=(info l2) 0)
		(=(info l3) 0)
		(=(info l4) 0)

	)
	
	(:goal (and (observed l4) 
		    (> (power r1) 0)
               )
        )
	(:metric minimize (+ (* (powerConsumption r1) 1) (* (time) 1) ))
     
)
