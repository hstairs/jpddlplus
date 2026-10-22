;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;; Run with:
;;; java -jar enhsp25.jar -o examples/iterative_optimization/domain.pddl \
;;;   -f examples/iterative_optimization/instance_1.pddl -planner sat-aibr -iopt
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(define (problem iterative-optimization-instance-1)
  (:domain iterative-optimization-demo)

  (:init
    (= (x) 0)
    (= (reward) 100)
  )

  (:goal
    (and
      (> (x) 10)
      (>= (reward) 0)
    )
  )

  (:metric maximize (reward))
)
