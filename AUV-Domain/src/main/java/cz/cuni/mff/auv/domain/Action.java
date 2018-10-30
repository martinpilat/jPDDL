package cz.cuni.mff.auv.domain;

import cz.cuni.mff.auv.domain.actions.Ac_Move;
import cz.cuni.mff.auv.domain.actions.Ac_Sample;

public abstract class Action extends Effector {
	
	public static final Ac_Move Move = new Ac_Move();
	public static final Ac_Sample Sample = new Ac_Sample();
	
	public static final Action[] ALL = new Action[] { Move, Sample };
	
}
