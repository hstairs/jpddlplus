(define (problem blocks-16-2)
(:domain blocks)
(:objects k i g n p a d m c b h f o j l e - block)
(:init (clear e) (clear l) (ontable j) (ontable o) (on e f) (on f h) (on h b)
 (on b c) (on c m) (on m d) (on d a) (on a p) (on p n) (on n g) (on g i)
 (on i k) (on k j) (on l o) (handempty))
(:goal (and (on i d) (on d h) (on h f) (on f b) (on b k) (on k j) (on j g)
            (on g e) (on e c) (on c l) (on l m) (on m n) (on n a) (on a p)
            (on p o)))
)