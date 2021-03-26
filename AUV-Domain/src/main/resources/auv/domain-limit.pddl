(define (domain AUV)
(:requirements :typing :equality)
(:types vehicle location resource uns - object
        auv ship - vehicle
)

(:constants u0 u1 u2 u3 u4 u5 - uns)

(:predicates (at ?v - vehicle ?l - location)
             (connected ?l1 ?l2 - location)
             (at-res ?r - resource ?l - location)
             (sampled ?r - resource)
             (free ?l - location)
             (operational ?a - auv)
             (connected-ship ?s - ship ?l1 ?l2 - location)
             (outside ?s - ship)
             (entry ?s - ship ?l - location)
             (exit ?s - ship ?l - location)
             
             (act-turn)
             (dup-free ?l - location)

             (safe ?v - vehicle ?l - location)

             (unsafe-times ?u - uns)
             (next ?u1 ?u2 - uns)
)

(:action move-safe
:parameters (?a - auv ?l1 ?l2 - location)
:precondition (and (at ?a ?l1)
                   (operational ?a)
                   (connected ?l1 ?l2)
                   (safe ?a ?l2)
                   (free ?l2)
              )
:effect (and (not (at ?a ?l1))
             (at ?a ?l2)
             (free ?l1)
             (not (free ?l2))
             (not (unsafe-times u1))(not (unsafe-times u2))(not (unsafe-times u3))(not (unsafe-times u4))(not (unsafe-times u5))
             (unsafe-times u0)
        )
)

(:action move-unsafe
:parameters (?a - auv ?l1 ?l2 - location ?u1 ?u2 - uns)
:precondition (and (at ?a ?l1)
                   (operational ?a)
                   (connected ?l1 ?l2)
                   (free ?l2)
                   (not (safe ?a ?l2))
                   (unsafe-times ?u1)
                   (next ?u1 ?u2)
              )
:effect (and (not (at ?a ?l1))
             (at ?a ?l2)
             (free ?l1)
             (not (free ?l2))
             (not (unsafe-times ?u1))
             (unsafe-times ?u2)
        )
)

(:action sample
:parameters (?a - auv ?r - resource ?l - location)
:precondition (and (at ?a ?l)
                   (at-res ?r ?l)
                   (operational ?a)
              )
:effect (and (sampled ?r)
        )
)



)
