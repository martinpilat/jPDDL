(define (domain AUV)
(:requirements :typing :equality :conditional-effects)
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

             (dup-free ?l - location)
             (act-turn)
             (event-turn)
)

(:action move
:parameters (?a - auv ?l1 ?l2 - location)
:precondition (and (at ?a ?l1)
                   (operational ?a)
                   (connected ?l1 ?l2)
                   (free ?l2) 
                   (act-turn)
              )
:effect (and (not (at ?a ?l1))
             (at ?a ?l2)
             (free ?l1)
             (not (free ?l2))
             (dup-free ?l1)
             (not (dup-free ?l2))
             (not (act-turn))
             (event-turn)
        )
)

(:action sample
:parameters (?a - auv ?r - resource ?l - location)
:precondition (and (at ?a ?l)
                   (at-res ?r ?l)
                   (operational ?a)
                   (act-turn)
              )
:effect (and (sampled ?r)
             (not (act-turn))
             (event-turn)
        )
)

;;events conditional effects
(:action events
:parameters ()
:precondition (and (event-turn))
:effect (and (not (event-turn))
             (act-turn)
             ;;enter ship free
             (forall (?s - ship ?l - location)
                  (when (and (entry ?s ?l)(outside ?s)(dup-free ?l))
                        (and (at ?s ?l)(not (free ?l)))
                  )
             )
             ;;enter ship auv
             (forall (?s - ship ?l - location ?a - auv)
                  (when (and (entry ?s ?l)(outside ?s)(at ?a ?l))
                        (and (at ?s ?l)(not (free ?l))(not (operational ?a)))
                  )
             )
             ;;move ship free
             (forall (?s - ship ?l1 ?l2 - location)
                  (when (and (at ?s ?l1)(connected-ship ?s ?l1 ?l2)(dup-free ?l2))
                        (and (at ?s ?l2)(dup-free ?l1)(not (free ?l1)))
                  )
             )
             ;;move ship auv
             (forall (?s - ship ?l1 ?l2 - location ?a - auv)
                  (when (and (at ?s ?l1)(connected-ship ?s ?l1 ?l2)(at ?a ?l2))
                        (and (at ?s ?l2)(dup-free ?l1)(not (free ?l1))(not (operational ?a)))
                  )
             )
             ;;leave ship
             (forall (?s - ship ?l - location)
                  (when (and (exit ?s ?l)(at ?s ?l))
                        (and (dup-free ?l)(outside ?s))
                  )
             )
        )
)

;;;;;;;;; events

)
