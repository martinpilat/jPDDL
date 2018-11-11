package cz.cuni.mff.jpddl.tools.search.bench;

public class Timed {

	public long startMillis;
	
	public long endMillis;
	
	public long durationMillis;
	
	public void start() {
		startMillis = System.currentTimeMillis();
		endMillis = durationMillis = 0;
	}
	
	public void end() {
		endMillis = System.currentTimeMillis();
		durationMillis = endMillis - startMillis;
	}
	
	public void reportInline(String headline) {
		System.out.println(headline + ": " + durationMillis + "ms = " + (((double)durationMillis) / 1000.0d) + "s");
	}
	
	public void report(String headline) {
		report(headline, "");
	}
	
	public void report(String headline, String indent) {
		System.out.println(indent + headline);
		System.out.println(indent + "  +-- time:   " + durationMillis + "ms = " + (((double)durationMillis) / 1000.0d) + "s");
	}
	
}
