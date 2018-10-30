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
             (act-turn)

             (dup-free ?l - location)
)

(:action move
:parameters (?a - auv ?l1 ?l2 - location)
:precondition (and (at ?a ?l1)
                   (operational ?a)
                   (connected ?l1 ?l2)
                   (free ?l2)
                   (dup-free ?l2)
              )
:effect (and (not (at ?a ?l1))
             (at ?a ?l2)
             (free ?l1)
             (dup-free ?l1)
             (not (free ?l2))
             (not (dup-free ?l2))
        )
)

(:action sample
:parameters (?a - auv ?r - resource ?l - location)
:precondition (and (at ?a ?l)
                   (at-res ?r ?l)
                   (operational ?a)
              )
:effect (and (sampled ?r)
             (not (at-res ?r ?l))
        )
)


(:event enter-ship-free
:parameters (?s - ship ?l - location)
:precondition (and (entry ?s ?l)
                   (outside ?s)
                   (free ?l)
                   (dup-free ?l)
              )
:effect (and (at ?s ?l)
             (not (free ?l))
             (not (dup-free ?l))
             (not (outside ?s))
        )
)

(:event enter-ship-auv
:parameters (?s - ship ?l - location ?a - auv)
:precondition (and (entry ?s ?l)
                   (outside ?s)
                   (at ?a ?l)
              )
:effect (and (at ?s ?l)
             (not (free ?l))
             (not (dup-free ?l))
             (not (outside ?s))
             (not (operational ?a))
        )
)

(:event leave-ship
:parameters (?s - ship ?l - location)
:precondition (and (exit ?s ?l)
                   (at ?s ?l)
              )
:effect (and (not (at ?s ?l))
             (free ?l)
             (dup-free ?l)
             (outside ?s)
        )
)

(:event move-ship-free
:parameters (?s - ship ?l1 ?l2 - location)
:precondition (and (at ?s ?l1)
                   (connected-ship ?s ?l1 ?l2)
                   (free ?l2)
                   (dup-free ?l2)
              )
:effect (and (at ?s ?l2)
             (not (at ?s ?l1))
             (free ?l1)
             (dup-free ?l1)
             (not (free ?l2))
             (not (dup-free ?l2))
        )
)

(:event move-ship-auv
:parameters (?s - ship ?l1 ?l2 - location ?a - auv)
:precondition (and (at ?s ?l1)
                   (connected-ship ?s ?l1 ?l2)
                   (at ?a ?l2)
              )
:effect (and (at ?s ?l2)
             (not (at ?s ?l1))
             (free ?l1)
             (dup-free ?l1)
             (not (free ?l2))
             (not (dup-free ?l2))
             (not (operational ?a))
        )
)

)
