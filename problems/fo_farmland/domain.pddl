;; Enrico Scala (enricos83@gmail.com) and Dongxu Li (dongxu.li@anu.edu.au,)
;; Reference Paper: Li, D., Scala, E., Haslum, P., & Bogomolov, S. (2018, July). 
;;                  Effect-abstraction based relaxation for linear numeric planning. 
;;                  In Proceedings of the 27th International 
;;                  Joint Conference on Artificial Intelligence (pp. 4787-4793).
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; This is an extended version of the Farmland domain; it does so introducing a new action (movebycar)
;; with which it is possible to move more workers per time from place to place, yet paying a higher cost

(define (domain farmland_ln)
    (:requirements :typing :negative-preconditions :numeric-fluents)
    (:types farm  station - object
            
    )
    (:predicates (adj ?f1 ?f2 - farm))
    (:functions
        (x ?b - farm)
        (num_of_cars ?s - station)
        (cost)
        
    )

    ;; Move a person from a unit f1 to a unit f2
    (:action move-by-car
        :parameters (?f1 ?f2 - farm ?s - station)
        :precondition (and (not (= ?f1 ?f2)) (>= (x ?f1) (* 4 (num_of_cars ?s))) (adj ?f1 ?f2) )
        :effect (and  (decrease (x ?f1) (* 4 (num_of_cars ?s))) 
                      (increase (x ?f2) (* 4 (num_of_cars ?s)))
                      (increase (cost) (* 0.1  (* 4 (num_of_cars ?s))))
                )
    )
    
    (:action move-slow
         :parameters (?f1 ?f2 - farm)
         :precondition (and (not (= ?f1 ?f2)) (>= (x ?f1) 1) (adj ?f1 ?f2))
         :effect (and(decrease (x ?f1) 1) (increase (x ?f2) 1))
    )

    (:action hire-car
        :parameters (?s - station)
        :effect (and  (increase (num_of_cars ?s) 1))
    )
)
