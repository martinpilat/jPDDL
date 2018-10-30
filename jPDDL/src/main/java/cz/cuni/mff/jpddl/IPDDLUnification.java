package cz.cuni.mff.jpddl;

public interface IPDDLUnification<EFFECTOR extends PDDLEffector> {

	void start();
	
	/**
	 * @param effector (next) result of the unification ... {@link PDDLEffector#clone()} or {@link PDDLEffector#rewrite(PDDLEffector)} this if you need to store it / change it!
	 * @return
	 */
	void unified(EFFECTOR effector);
	
	void end();
	
}
