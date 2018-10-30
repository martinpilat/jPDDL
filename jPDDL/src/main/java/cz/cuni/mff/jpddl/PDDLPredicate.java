package cz.cuni.mff.jpddl;

import java.util.Collection;
import java.util.Comparator;

public abstract class PDDLPredicate implements Comparable<PDDLPredicate> {

	/**
	 * Returns PDDL name of the effector.
	 * @return
	 */
	public abstract String getName();
	
	/**
	 * Returns PDDL representation of the predicate instance.
	 * @return
	 */
	public abstract String toPredicate();
	
	@Override
	public int compareTo(PDDLPredicate o) {
		if (o == null) return 1;
		return toPredicate().compareTo(o.toPredicate());
	}
	
	public static void dumpPredicates(Collection<? extends PDDLPredicate> predicates, int maxLineLength, String indent) {
		int len = 0;
		System.out.print(indent);
		int i = 0;
		PDDLPredicate last = null;
		for (PDDLPredicate predicate : predicates) {
			if (i == predicates.size()-1) {
				last = predicate;
				break;
			}
			String pred = predicate.toPredicate();
			if (len + pred.length() > maxLineLength) {
				System.out.println();
				System.out.print(indent);
				len = 0;
			}			
			len += pred.length();
			System.out.print(pred);			
			System.out.print(" ");
			i += 1;
		}
		if (last != null) {
			String pred = last.toPredicate();
			if (len + pred.length() > maxLineLength) {
				System.out.println();
				System.out.print(indent);
				len = 0;
			}			
			len += pred.length();
			System.out.println(pred);			
		} else {
			System.out.println("none");
		}
	}
	
	public static class PDDLPredicateComparator implements Comparator<PDDLPredicate> {

		@Override
		public int compare(PDDLPredicate p1, PDDLPredicate p2) {
			if (p1 == null) {
				if (p2 == null) return 0;
				else return -1;
			} else {
				if (p2 == null) return 1;
				return p1.compareTo(p2);
			}				
		}		
	}
	
	public static final PDDLPredicateComparator COMPARATOR = new PDDLPredicateComparator();
	
}
