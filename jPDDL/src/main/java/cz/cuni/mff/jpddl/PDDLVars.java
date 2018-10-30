package cz.cuni.mff.jpddl;

import gnu.trove.map.hash.TIntObjectHashMap;

public class PDDLVars {
	
	private TIntObjectHashMap<PDDLType> values = new TIntObjectHashMap<PDDLType>();
	
	public PDDLType getValue(int variable) {
		return values.get(variable);
	}
	
	public void bindVariable(int variable, PDDLType value) {
		values.put(variable, value);
	}

}
