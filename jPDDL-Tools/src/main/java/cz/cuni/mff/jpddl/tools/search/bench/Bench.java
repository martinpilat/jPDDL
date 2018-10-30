package cz.cuni.mff.jpddl.tools.search.bench;

public class Bench {

	public long startMillis;
	
	public int nodesVisited;
	
	public long endMillis;
	
	public long durationMillis;
	
	public void start() {
		startMillis = System.currentTimeMillis();
		endMillis = durationMillis = 0;
		nodesVisited = 0;
	}
	
	public void end() {
		endMillis = System.currentTimeMillis();
		durationMillis = endMillis - startMillis;
	}
	
	public void report(String headline) {
		report(headline, "");
	}
	
	public void report(String headline, String indent) {
		System.out.println(indent + headline);
		System.out.println(indent + "  +-- search time:   " + durationMillis + "ms = " + (((double)durationMillis) / 1000.0d) + "s");
		System.out.println(indent + "  +-- nodes visited: " + nodesVisited);
		System.out.println(indent + "  +-- performance:   " + (((double)nodesVisited) / (((double)durationMillis))) + " nodes/ms");
	}
	
}
