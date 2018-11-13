(define (domain AUV)
(:requirements :typing :equality)
(:types vehicle location resource - object
        auv ship - vehicle
)

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
             
             (act-round)
             (dup-free ?l - location)
)

(:action move
:parameters (?a - auv ?l1 ?l2 - location)
:precondition (and (at ?a ?l1)
                   (operational ?a)
                   (connected ?l1 ?l2)
                   (free ?l2)
              )
:effect (and (not (at ?a ?l1))
             (at ?a ?l2)
             (free ?l1)
             (not (free ?l2))
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
