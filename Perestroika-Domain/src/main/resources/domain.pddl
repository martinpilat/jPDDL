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


(:event shrink-big
:parameters (?l - location)
:precondition (and (big ?l)
              )
:effect (and (medium ?l)
             (not (big ?l))
        )
)

(:event shrink-medium
:parameters (?l - location)
:precondition (and (medium ?l)
              )
:effect (and (small ?l)
             (not (medium ?l))
        )
)

(:event shrink-small-empty
:parameters (?l - location)
:precondition (and (small ?l)
                   (not (at-agent ?l))
              )
:effect (and (none ?l)
             (not (small ?l))
             (not (accessible ?l))
        )
)

(:event shrink-small-agent
:parameters (?l - location)
:precondition (and (small ?l)
                   (at-agent ?l)
              )
:effect (and (none ?l)
             (not (small ?l))
             (not (accessible ?l))
             (not (alive))
             (dead)
        )
)

(:event create
:parameters (?l - location)
:precondition (and (none ?l)
              )
:effect (and (big ?l)
             (not (none ?l))
             (accessible ?l)
        )
)

)
