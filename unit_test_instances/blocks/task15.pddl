(define (problem blocks-8-2)
(:domain blocks)
(:objects f b g c h e a d - block)
(:init (clear d) (clear a) (clear e) (clear h) (clear c) (ontable g)
 (ontable a) (ontable e) (ontable h) (ontable c) (on d b) (on b f) (on f g)
 (handempty))
(:goal (and (on c b) (on b e) (on e g) (on g f) (on f a) (on a d) (on d h)))
)