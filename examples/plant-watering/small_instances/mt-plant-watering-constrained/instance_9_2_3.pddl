(define (problem instance_9_2_3)
  (:domain mt-plant-watering-constrained)
  (:objects
    tap1 - tap
	plant1 plant2 - plant
	agent1 - agent
  )

  (:init
    (= (max_int) 40)
	(= (maxx) 9)
	(= (minx) 1)
	(= (maxy) 9)
	(= (miny) 1)
	(= (carrying) 0)
	(= (total_poured) 0)
	(= (total_loaded) 0)
	(= (poured plant1) 0)
	(= (poured plant2) 0)
	(= (x agent1) 9)
	(= (y agent1) 3)
	(= (x tap1) 4)
	(= (y tap1) 4)
	(= (x plant1) 5)
	(= (y plant1) 5)
	(= (x plant2) 4)
	(= (y plant2) 4)
  )

  (:goal (and 
    (= (poured plant1) 7)
	(= (poured plant2) 3)
	(= (total_poured) (+ (poured plant1) (poured plant2)) )
  ))

  
  

  
)
