package org.acme.graph.model;

import java.util.List;

public class Path {

	private List<Edge> edges;
	
	public Path(List<Edge> edges) {
		this.edges = edges;
	}
	
	public List<Edge> getEdges() {
		return this.edges;
	}
	
	public double getLength() {
		double lenght = 0.0;
		for (Edge edge : this.edges) {
			lenght+=edge.getCost();
		}
		return lenght;
	}
	
	
}
