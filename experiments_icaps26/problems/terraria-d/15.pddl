(define (problem terraria)
(:domain terraria)
(:objects 
	cobweb1 - cobweb iron1 - iron_bar lead1 - lead_bar chain1 - chain platinum1 - platinum_bar
	silk1 - silk bed1 - bed sword1 - platinum_sword
	wood1 - wood chest1 - chest MahoganyWood - mahogany_wood IvyChest - ivy_chest
	Home Workshop Forest MahoganyForest Mine - location
)

(:init
	(= (numeric_goal_bed) 150)
	(= (numeric_goal_ivy) 150)
	(= (numeric_goal_chest) 150)
	(= (numeric_goal_sword) 150)
	(= (stock cobweb1) 0)
	(= (stock iron1) 0)
	(= (stock MahoganyWood) 0)
	(= (stock lead1) 0)
	(= (stock chain1) 0)
	(= (stock silk1) 0)
	(= (stock platinum1) 0)
	(= (stock bed1) 0)
	(= (stock sword1) 0)
	(= (stock chest1) 0)
	(= (stock wood1) 0)
	(= (stock IvyChest) 0)
	(can_assemble Workshop)
	(can_lumber Forest)
	(can_lumber_mahogany MahoganyForest)
	(can_mine Mine)
	(at Home)
	(ready_workbench)
)
(:goal (and
	(placed_swords) (placed_beds) (placed_chests) (placed_ivy_chests)
))
)

