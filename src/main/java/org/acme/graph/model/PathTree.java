package org.acme.graph.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.acme.graph.errors.NotFoundException;

public class PathTree {

	private Map<Vertex,PathNode> nodes = new HashMap<Vertex,PathNode>();

	/**
	 * Prépare le graphe pour le calcul du plus court chemin
	 * 
	 * @param source
	 */
	public PathTree(Vertex source) {
		PathNode pathNode = new PathNode();
		pathNode.setCost(0.0);
		pathNode.setReachingEdge(null);
		pathNode.setVisited(false);
		nodes.put(source, pathNode);	
	}
	
	/**
	 * Construit le chemin en remontant les relations incoming edge
	 * 
	 * @param target
	 * @return
	 */
	public List<Edge> getPath(Vertex target) {
		List<Edge> result = new ArrayList<>();
		if (this.isReached(target)) {
			Edge current = getNode(target).getReachingEdge();
			do {
				result.add(current);
				current = getNode(current.getSource()).getReachingEdge();
			} while (current != null);
	
			Collections.reverse(result);
			return result;
		}
		else {throw new NotFoundException(String.format("The node '%s' has not been reached yet",target));}
	}
	
	public PathNode getNode(Vertex vertex) {
		return nodes.get(vertex);
	}
	
	public boolean isReached(Vertex destination) {
		return this.getNode(destination).getReachingEdge() != null;
	}
	
	public PathNode getOrCreateNode(Vertex vertex) {
		if (nodes.containsKey(vertex)){
			return nodes.get(vertex);
		}
		else {
			PathNode pathNode = new PathNode();
			pathNode.setCost(Double.POSITIVE_INFINITY);
			pathNode.setReachingEdge(null);
			pathNode.setVisited(false);
			nodes.put(vertex, pathNode);
			return pathNode;
		}
	}
	
	public Collection<Vertex> getReachedVertices(){
		Collection<Vertex> reachedVertices = new ArrayList<Vertex>();
		for (Map.Entry<Vertex, PathNode> pair : nodes.entrySet()) {
				reachedVertices.add(pair.getKey());
		}
		return reachedVertices;
	}
}
