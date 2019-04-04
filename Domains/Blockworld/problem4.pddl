

(define (problem BW-rand-10)
(:domain blocksworld)
(:objects b1 b2 b3 b4 b5 b6 b7 b8 b9 b10  - block
   h1 h2 - slippery
          h3 - stickyh)
(:init
(handempty h1)
(handempty h2)
(on b1 b4)
(on b2 b7)
(on b3 b5)
(ontable b4)
(on b5 b10)
(on b6 b2)
(ontable b7)
(on b8 b9)
(on b9 b3)
(on b10 b6)
(clear b1)
(clear b8)
(movable b1)
(movable b2)
(movable b3)
(movable b4)
(movable b5)
(movable b6)
(movable b7)
(movable b8)
(movable b9)
(movable b10)
)
(:goal
(and
(on b1 b2)
(on b2 b7)
(on b3 b5)
(on b4 b10)
(on b5 b6)
(on b6 b4)
(on b8 b3)
(on b9 b1))
)
)


