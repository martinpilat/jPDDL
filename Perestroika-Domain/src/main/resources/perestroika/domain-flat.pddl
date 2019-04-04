(define (domain Perestroika)
(:requirements :typing :equality)
(:types location resource - object
        
)

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
             (event-round)

            ; (dup-accessible ?l - location)
)

(:action move
:parameters (?l1 ?l2 - location)
:precondition (and (at-agent ?l1)
                   (alive)
                   (connected ?l1 ?l2)
                   (accessible ?l2)
                  ; (dup-accessible ?l2)
                   (act-round)
              )
:effect (and (not (at-agent ?l1))
             (at-agent ?l2)
              (not (act-round))
             (event-round)
        )
)

(:action collect
:parameters (?r - resource ?l - location)
:precondition (and (at-agent ?l)
                   (at-res ?r ?l)
                   (alive)
                   (act-round)
              )
:effect (and (taken ?r)
              (not (act-round))
             (event-round)
        )
)


;;events conditional effects
(:action events
:parameters ()
:precondition (and (event-round))
:effect (and (not (event-round))
             (act-round)
              ;;shrink-big
             (forall (?l - location)
                 (when (big ?l)(medium ?l))
             )
             ;;shrink-med
             (forall (?l - location)
                 (when (medium ?l)(small ?l))
             )
              ;;shrink-small-empty
             (forall (?l - location)
                 (when (and (small ?l)(not (at-agent ?l)))
                       (and (none ?l)(not (accessible ?l))))
             )
              ;;shrink-small-agent
             (forall (?l - location)
                 (when (and (small ?l)(at-agent ?l))
                       (and (none ?l)(not (accessible ?l))(not (alive))(dead)))
             )
             ;;shrink-med
             (forall (?l - location)
                 (when (none ?l)(big ?l))
             )
        )
)
             
;;;;events



)
