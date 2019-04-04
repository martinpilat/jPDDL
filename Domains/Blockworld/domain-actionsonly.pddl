(define (domain blocksworld)
  (:requirements :strips :typing)
  (:types block hand - object
          stickyh slippery - hand)
  (:predicates (on ?x - block ?y - block)
	       (ontable ?x - block)
	       (clear ?x - block)
	       (handempty ?h - hand)
	       (holding ?h - hand ?x - block)
               (movable ?x - block)
               (sticky ?x - block)
	       )

  (:action pick-up-sl
	     :parameters (?x - block ?h - slippery)
	     :precondition (and (clear ?x) (ontable ?x) (handempty ?h)(movable ?x))
	     :effect
	     (and  
                   (not (ontable ?x))
		   (not (clear ?x))
		   (not (handempty ?h))
		   (holding ?h ?x)))

  (:action pick-up-st
	     :parameters (?x - block ?h - stickyh)
	     :precondition (and (clear ?x) (ontable ?x) (handempty ?h)(movable ?x))
	     :effect
	     (and  
                   (not (ontable ?x))
		   (not (clear ?x))
		   (not (handempty ?h))
		   (holding ?h ?x)
                   (sticky ?x)
                      ))

  (:action put-down
	     :parameters (?x - block ?h - hand)
	     :precondition (and (holding ?h ?x))
	     :effect
	     (and (not (holding ?h ?x))
		   (clear ?x)
		   (handempty ?h)
		   (ontable ?x)))

  (:action stack
	     :parameters (?x - block ?y - block ?h - hand)
	     :precondition (and (holding ?h ?x) (clear ?y))
	     :effect
	     (and (not (holding ?h ?x))
		   (not (clear ?y))
		   (clear ?x)
		   (handempty ?h)
		   (on ?x ?y)))

  (:action unstack-sl
	     :parameters (?x - block ?y - block ?h - slippery)
	     :precondition (and (on ?x ?y) (clear ?x) (handempty ?h)(movable ?x))
	     :effect
	     (and  
                   (holding ?h ?x)
		   (clear ?y)
		   (not (clear ?x))
		   (not (handempty ?h))
		   (not (on ?x ?y))))

  (:action unstack-st
	     :parameters (?x - block ?y - block ?h - stickyh)
	     :precondition (and (on ?x ?y) (clear ?x) (handempty ?h)(movable ?x))
	     :effect
	     (and  
                   (holding ?h ?x)
		   (clear ?y)
		   (not (clear ?x))
		   (not (handempty ?h))
		   (not (on ?x ?y))
                   (sticky ?x)))


)
