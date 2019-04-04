(define (problem AUV-problem)
(:domain AUV)
(:objects a - auv
          s - ship
          r1 r2 r3 - resource
          l-1-1 l-1-2 l-1-3 l-1-4 l-2-1 l-2-2 l-2-3 l-2-4 l-3-1 l-3-2 l-3-3 l-3-4 l-4-1 l-4-2 l-4-3 l-4-4 - location
)

(:init (act-round)
       (operational a)
       (outside s)
       (at a l-1-1)
       (at-res r1 l-2-4)
       (at-res r2 l-4-4)
       (at-res r3 l-4-2)

(free l-1-1)
(free l-1-2)
(free l-1-3)
(free l-1-4)
(free l-2-1)
(free l-2-2)
(free l-2-3)
(free l-2-4)
(free l-3-1)
(free l-3-2)
(free l-3-3)
(free l-3-4)
(free l-4-1)
(free l-4-2)
(free l-4-3)
(free l-4-4)

(dup-free l-1-1)
(dup-free l-1-2)
(dup-free l-1-3)
(dup-free l-1-4)
(dup-free l-2-1)
(dup-free l-2-2)
(dup-free l-2-3)
(dup-free l-2-4)
(dup-free l-3-1)
(dup-free l-3-2)
(dup-free l-3-3)
(dup-free l-3-4)
(dup-free l-4-1)
(dup-free l-4-2)
(dup-free l-4-3)
(dup-free l-4-4)

(connected l-1-1 l-2-1)
(connected l-2-1 l-1-1)
(connected l-1-1 l-1-2)
(connected l-1-2 l-1-1)
(connected l-1-2 l-2-2)
(connected l-2-2 l-1-2)
(connected l-1-2 l-1-3)
(connected l-1-3 l-1-2)
(connected l-1-3 l-2-3)
(connected l-2-3 l-1-3)
(connected l-1-3 l-1-4)
(connected l-1-4 l-1-3)
(connected l-1-4 l-2-4)
(connected l-2-4 l-1-4)
(connected l-2-1 l-3-1)
(connected l-3-1 l-2-1)
(connected l-2-1 l-2-2)
(connected l-2-2 l-2-1)
(connected l-2-2 l-3-2)
(connected l-3-2 l-2-2)
(connected l-2-2 l-2-3)
(connected l-2-3 l-2-2)
(connected l-2-3 l-3-3)
(connected l-3-3 l-2-3)
(connected l-2-3 l-2-4)
(connected l-2-4 l-2-3)
(connected l-2-4 l-3-4)
(connected l-3-4 l-2-4)
(connected l-3-1 l-4-1)
(connected l-4-1 l-3-1)
(connected l-3-1 l-3-2)
(connected l-3-2 l-3-1)
(connected l-3-2 l-4-2)
(connected l-4-2 l-3-2)
(connected l-3-2 l-3-3)
(connected l-3-3 l-3-2)
(connected l-3-3 l-4-3)
(connected l-4-3 l-3-3)
(connected l-3-3 l-3-4)
(connected l-3-4 l-3-3)
(connected l-3-4 l-4-4)
(connected l-4-4 l-3-4)
(connected l-4-1 l-4-2)
(connected l-4-2 l-4-1)
(connected l-4-2 l-4-3)
(connected l-4-3 l-4-2)
(connected l-4-3 l-4-4)
(connected l-4-4 l-4-3)

(entry s l-3-4)
(exit s l-3-1)
(exit s l-4-3)
(connected-ship s l-3-4 l-3-3)
(connected-ship s l-3-3 l-3-2)
(connected-ship s l-3-2 l-3-1)

(connected-ship s l-3-3 l-4-3)

)

(:goal (and (sampled r1)
            (sampled r2)
            (sampled r3)
            (at a l-1-1)
            (operational a)
       )
)

)