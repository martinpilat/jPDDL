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

            ; (dup-accessible ?l - location)
)

(:action move
:parameters (?l1 ?l2 - location)
:precondition (and (at-agent ?l1)
                   (alive)
                   (connected ?l1 ?l2)
                   (accessible ?l2)
                  ; (dup-accessible ?l2)
              )
:effect (and (not (at-agent ?l1))
             (at-agent ?l2)
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
