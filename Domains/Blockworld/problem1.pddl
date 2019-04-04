
(define (problem BW-rand-5)
(:domain blocksworld)
(:objects b1 b2 b3 b4 b5  - block
          h1 - slippery
          h2 - stickyh)
(:init
(handempty h1)
(handempty h2)
(on b1 b2)
(on b2 b4)
(ontable b3)
(ontable b4)
(on b5 b3)
(clear b1)
(clear b5)
(movable b1)
(movable b2)
(movable b3)
(movable b4)
(movable b5)
)
(:goal
(and
(on b1 b4)
(on b2 b5)
(on b3 b1)
(on b5 b3))
)
)


