package cz.cuni.mff.jpddl.tools.search.bench;

public class Bench extends Timed {

	public int nodesVisited;
	
	public void start() {
		super.start();
		nodesVisited = 0;
	}
	
	public void end() {
		super.end();
	}
	
	public void report(String headline) {
		super.report(headline);
	}
	
	public void report(String headline, String indent) {
		super.report(headline, indent);
		System.out.println(indent + "  +-- nodes visited: " + nodesVisited);
		System.out.println(indent + "  +-- performance:   " + (((double)nodesVisited) / (((double)durationMillis))) + " nodes/ms");
	}
	
}
