(define (problem numfract-counterexample)
  (:domain sailing)
  (:objects b0 - boat p0 p3 - person)
  (:init
    (= (x b0) 0)
    (= (y b0) 0)
    (= (d p0) -61)
    (= (d p3) 28))
  (:goal (and (saved p0) (saved p3))))
