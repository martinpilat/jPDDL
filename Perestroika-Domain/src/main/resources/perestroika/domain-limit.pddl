(define (domain Perestroika)
(:requirements :typing :equality)
(:types location resource uns - object
        
)

(:constants u0 u1 u2 u3 u4 u5 - uns)

(:predicates (at-agent ?l - location)
             (connected ?l1 ?l2 - location)
             (at-res ?r - resource ?l - location)
             (taken ?r - resource)
             (accessible ?l - location)
             (alive)
             (dead)
             (solid ?l - location)
             (big ?l - location)
             (medium ?l - location)
             (small ?l - location)
             (none ?l - location)   
             (act-round)

            ; (dup-accessible ?l - location)

             (unsafe-times ?u - uns)
             (next ?u1 ?u2 - uns)
)

(:action move-safe
:parameters (?l1 ?l2 - location)
:precondition (and (at-agent ?l1)
                   (alive)
                   (connected ?l1 ?l2)
                   (accessible ?l2)
                  ; (dup-accessible ?l2)
                   (solid ?l2)
              )
:effect (and (not (at-agent ?l1))
             (at-agent ?l2)
             (not (unsafe-times u1))(not (unsafe-times u2))(not (unsafe-times u3))(not (unsafe-times u4))(not (unsafe-times u5))
             (unsafe-times u0)
        )
)

(:action move-unsafe
:parameters (?l1 ?l2 - location ?u1 ?u2 - uns)
:precondition (and (at-agent ?l1)
                   (alive)
                   (connected ?l1 ?l2)
                   (accessible ?l2)
                  ; (dup-accessible ?l2)
                   (not (solid ?l2))
                    (unsafe-times ?u1)
                   (next ?u1 ?u2)
              )
:effect (and (not (at-agent ?l1))
             (at-agent ?l2)
             (not (unsafe-times ?u1))
             (unsafe-times ?u2)
        )
)

(:action collect
:parameters (?r - resource ?l - location)
:precondition (and (at-agent ?l)
                   (at-res ?r ?l)
                   (alive)
              )
:effect (and (taken ?r)
        )
)


)
