(define (domain AUV)
(:requirements :typing :equality :non-deterministic)
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
             (event-round)
             (event-selected)
)

(:action move
:parameters (?a - auv ?l1 ?l2 - location)
:precondition (and (act-round)
                   (at ?a ?l1)
                   (operational ?a)
                   (connected ?l1 ?l2)
                   (free ?l2)
              )
:effect (and (not (at ?a ?l1))
             (at ?a ?l2)
             (free ?l1)
             (not (free ?l2))
             (not (act-round))
             (event-round)
        )
)

(:action sample
:parameters (?a - auv ?r - resource ?l - location)
:precondition (and (act-round)
                   (at ?a ?l)
                   (at-res ?r ?l)
                   (operational ?a)
              )
:effect (and (sampled ?r)
             (not (act-round))
             (event-round)
        )
)

;;;;;;;;;;;
;;; noop
;;;;;;;;;;;

(:action noop
:parameters ()
:precondition (and (act-round))
:effect (and (not (act-round))
             (event-round)
        )
)

;;;;;;;;;;;
;;; switcher
;;;;;;;;;;;

(:action switch
:parameters ()
:precondition (and (event-round))
:effect (oneof (and (not (event-round))(act-round))
               (and (not (event-round))(event-selected))
        )
)

;;;;;;;;;;;
;;; events
;;;;;;;;;;;

(:action enter-ship-free
:parameters (?s - ship ?l - location)
:precondition (and (event-selected)
                   (entry ?s ?l)
                   (outside ?s)
                   (free ?l)
              )
:effect (and (at ?s ?l)
             (not (free ?l))
             (not (outside ?s))
             (not (event-selected))
             (act-round)
        )
)

(:action enter-ship-auv
:parameters (?s - ship ?l - location ?a - auv)
:precondition (and (event-selected)
                   (entry ?s ?l)
                   (outside ?s)
                   (at ?a ?l)
              )
:effect (and (at ?s ?l)
             (not (free ?l))
             (not (outside ?s))
             (not (operational ?a))
             (not (event-selected))
             (act-round)
        )
)

(:action leave-ship
:parameters (?s - ship ?l - location)
:precondition (and (event-selected)
                   (exit ?s ?l)
                   (at ?s ?l)
              )
:effect (and (not (at ?s ?l))
             (free ?l)
             (outside ?s)
             (not (event-selected))
             (act-round)
        )
)

(:action move-ship-free
:parameters (?s - ship ?l1 ?l2 - location)
:precondition (and (event-selected)
                   (at ?s ?l1)
                   (connected-ship ?s ?l1 ?l2)
                   (free ?l2)
              )
:effect (and (at ?s ?l2)
             (not (at ?s ?l1))
             (free ?l1)
             (not (free ?l2))
             (not (event-selected))
             (act-round)
        )
)

(:action move-ship-auv
:parameters (?s - ship ?l1 ?l2 - location ?a - auv)
:precondition (and (event-selected)
                   (at ?s ?l1)
                   (connected-ship ?s ?l1 ?l2)
                   (at ?a ?l2)
              )
:effect (and (at ?s ?l2)
             (not (at ?s ?l1))
             (free ?l1)
             (not (free ?l2))
             (not (operational ?a))
             (not (event-selected))
             (act-round)
        )
)

)
