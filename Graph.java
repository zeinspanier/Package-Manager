import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Filename: Graph.java 
 * Project: p4 
 * Authors: Zach Einspanier
 * 
 * Directed and unweighted graph implementation
 */

/**
 * @author bobza
 *
 */
public class Graph implements GraphADT {

	private class GraphNode {
		String vertex;
		ArrayList<GraphNode> edges;

		private GraphNode(String vertex) {
			this.vertex = vertex;
			edges = new ArrayList<GraphNode>();
		}

		private void addEdge(GraphNode edge) {
			edges.add(edge);
		}

	}

	private List<GraphNode> nodeList = new ArrayList<>(); // List that has all nodes
	private int order; // number of nodes
	private int size; // number of edges

	/*
	 * Default no-argument constructor
	 */
	public Graph() {

	}

	/**
	 * This helper method find a graph node for a given string vertex
	 * 
	 * @param vertex1 - vertex to find
	 * @return - graph node that is found 
	 */
	private GraphNode findGraphNode(String vertex1) {
		
		// Goes through each node and checks if it equals vertex1
		for (int i = 0; i < nodeList.size(); i++) {
			GraphNode currentVertex = nodeList.get(i);
			if (currentVertex.vertex.equals(vertex1)) {
				return currentVertex;
			} else {
				// Next check the vertexes edges
				if (currentVertex.edges.size() == 0) {
					continue;
				}
				// Goes through each edge to find vertex
				for (int j = 0; j < currentVertex.edges.size(); j++) {
					GraphNode currentEdge = currentVertex.edges.get(j);
					if (currentEdge.vertex.equals(vertex1)) {
						return currentEdge;
					}
				}
			}
		}
		// Returns null if vertex is not found
		return null;
	}

	/**
	 * Add new vertex to the graph.
	 *
	 * If vertex is null or already exists, method ends without adding a vertex
	 * or throwing an exception.
	 * 
	 * Valid argument conditions: 1. vertex is non-null 2. vertex is not already
	 * in the graph
	 */
	public void addVertex(String vertex) {
		// Executes if vertex is non null
		if (vertex != null) {
			// If graph is empty
			if (nodeList.size() == 0) {
				GraphNode addV = new GraphNode(vertex);
				nodeList.add(addV);
				++order;
			} else {
				// Tries to find vertex in graph
				GraphNode foundNode = findGraphNode(vertex);

				// Vertex was not found if value returned was null
				if (foundNode == null) {
					GraphNode addV = new GraphNode(vertex);
					nodeList.add(addV);
					++order;
				}
			}
		}
	}

	/**
	 * Remove a vertex and all associated edges from the graph.
	 * 
	 * If vertex is null or does not exist, method ends without removing a
	 * vertex, edges, or throwing an exception.
	 * 
	 * Valid argument conditions: 1. vertex is non-null 2. vertex is not already
	 * in the graph
	 */
	public void removeVertex(String vertex) {
		if (vertex != null) {
			// If vertex is not in graph, do nothing
			if (findGraphNode(vertex) == null) {
				return;
			}
			// Looks through graph to find the vertex to remove
			for (int i = 0; i < nodeList.size(); i++) {
				GraphNode currentVertex = nodeList.get(i);
				if (currentVertex.vertex.equals(vertex)) {
					nodeList.remove(i);
					// removing an index moves all values back one
					--i;
					if (findGraphNode(vertex) == null) {
						// Only exit when the vertex can't be found in graph
						--order;
						return;
					}
				} else {
					if (currentVertex.edges.size() == 0) {
						continue;
					}
					for (int j = 0; j < currentVertex.edges.size(); j++) {
						GraphNode currentEdge = currentVertex.edges.get(j);
						if (currentEdge.vertex.equals(vertex)) {
							currentVertex.edges.remove(j);
							if (findGraphNode(vertex) == null) {
								// Only exit when the vertex can't be found in graph
								--order;
								return;
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Add the edge from vertex1 to vertex2 to this graph. (edge is directed and
	 * unweighted) If either vertex does not exist, add vertex, and add edge, no
	 * exception is thrown. If the edge exists in the graph, no edge is added
	 * and no exception is thrown.
	 * 
	 * Valid argument conditions: 1. neither vertex is null 2. both vertices are
	 * in the graph 3. the edge is not in the graph
	 */
	public void addEdge(String vertex1, String vertex2) {
		// Exit method if vertex1 or vertex2 are null
		if (vertex1 != null && vertex2 != null) {
			// if vertex1 is not in graph, add it
			GraphNode vertexOne = findGraphNode(vertex1);
			if (vertexOne == null) {
				this.addVertex(vertex1);
				vertexOne = findGraphNode(vertex1);
			}
			// if vertex2 is not in graph, add it
			GraphNode vertexTwo = findGraphNode(vertex2);
			if (vertexTwo == null) {
				this.addVertex(vertex2);
				vertexTwo = findGraphNode(vertex2);
			}
			// Looks if edges already exists
			for (int i = 0; i < vertexOne.edges.size(); ++i) {
				if (vertexOne.edges.get(i).equals(vertexTwo)) {
					return;
				}
			}
			// Add edge
			vertexOne.edges.add(vertexTwo);
			++size;
		}
	}

	/**
	 * Remove the edge from vertex1 to vertex2 from this graph. (edge is
	 * directed and unweighted) If either vertex does not exist, or if an edge
	 * from vertex1 to vertex2 does not exist, no edge is removed and no
	 * exception is thrown.
	 * 
	 * Valid argument conditions: 1. neither vertex is null 2. both vertices are
	 * in the graph 3. the edge from vertex1 to vertex2 is in the graph
	 */
	public void removeEdge(String vertex1, String vertex2) {
		// Exit method if vertex1 or vertex2 are null
		if (vertex1 != null && vertex2 != null) {
			GraphNode vertexOne = findGraphNode(vertex1);
			GraphNode vertexTwo = findGraphNode(vertex2);
			// If nodes do not exist in graph, exit
			if (vertexOne == null || vertexTwo == null) {
				return;
			}
			// Find edge and delete it
			for (int i = 0; i < vertexOne.edges.size(); i++) {
				if (vertexOne.edges.get(i).equals(vertexTwo)) {
					vertexOne.edges.remove(i);
					--size;
				}
			}
		}
	}

	/**
	 * Returns a Set that contains all the vertices
	 * 
	 */
	public Set<String> getAllVertices() {
		Set<String> allVertices = new HashSet<String>();
		// Adds all vertices to set
		for (int i = 0; i < nodeList.size(); ++i) {
			allVertices.add(nodeList.get(i).vertex);
		}

		return allVertices;
	}

	/**
	 * Get all the neighbor (adjacent) vertices of a vertex
	 *
	 */
	public List<String> getAdjacentVerticesOf(String vertex) {

		List<String> adjacentVertices = null;
		// Return empty list if vertex does not exist
		if (vertex != null) {
			adjacentVertices = new ArrayList<String>();

			GraphNode vertex1 = findGraphNode(vertex);
			// adds all edges to list
			for (int i = 0; i < vertex1.edges.size(); ++i) {
				adjacentVertices.add(vertex1.edges.get(i).vertex);
			}
		}

		return adjacentVertices;
	}

	/**
	 * Returns the number of edges in this graph.
	 */
	public int size() {
		return size;
	}

	/**
	 * Returns the number of vertices in this graph.
	 */
	public int order() {
		return order;
	}
}
