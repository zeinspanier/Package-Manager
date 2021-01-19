import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

/**
 * @author bobza
 *
 */
public class GraphTest {

	Graph testGraph = new Graph(); // Graph used for testing

	/**
	 * This method tests addVertex and addEdge from the Graph class
	 */
	@Test
	void test000_add() {
		
		Set<String> graphVertices = new HashSet<String>();
		// Adds vertexes to graph
		testGraph.addVertex("vertex1");
		testGraph.addVertex("vertex2");
		testGraph.addVertex("vertex3");
		testGraph.addVertex("vertex4");
		testGraph.addVertex("vertex5");
		testGraph.addVertex("vertex6");
		// Number of vertices should be 6
		if (testGraph.order() != 6) {
			fail("The size of the graph is incorrect. Size was expected to be 5 instead it was: " + testGraph.size());
		}
		// Adds vertices to set
		graphVertices.add("vertex1");
		graphVertices.add("vertex2");
		graphVertices.add("vertex3");
		graphVertices.add("vertex4");
		graphVertices.add("vertex5");
		graphVertices.add("vertex6");
		
		// Checks that all vertices are in graph
		if(!graphVertices.equals(testGraph.getAllVertices())) {
			fail("Not all vertices in graph.");
		}
		
		// Adds edges to graph
		testGraph.addEdge("vertex1", "vertex3");
		testGraph.addEdge("vertex1", "vertex5");
		
		ArrayList<String> odd = new ArrayList<String>();
		
		odd.add("vertex3");
		odd.add("vertex5");
		// checks that correct edges are in graph
		if(!odd.equals(testGraph.getAdjacentVerticesOf("vertex1"))) {
			fail("Adjacent vertexes are not correct.");
		}
		
		// Adds edges to graph
		testGraph.addEdge("vertex2", "vertex4");
		testGraph.addEdge("vertex2", "vertex6");
		
		ArrayList<String> even = new ArrayList<String>();
		
		even.add("vertex4");
		even.add("vertex6");
		
		// checks that correct edges are in graph
		if(!even.equals(testGraph.getAdjacentVerticesOf("vertex2"))) {
			fail("Adjacent vertexes are not correct.");
		}
	}

	/**
	 * This method tests removeVertex and removeEdge
	 */
	@Test
	void test001_remove() {
		
		Set<String> graphVertices = new HashSet<String>();
		// Add vertices and edges
		testGraph.addVertex("vertex1");
		testGraph.addVertex("vertex2");
		testGraph.addVertex("vertex3");
		testGraph.addVertex("vertex4");
		testGraph.addVertex("vertex5");
		
		testGraph.addEdge("vertex1", "vertex2");
		testGraph.addEdge("vertex2", "vertex3");
		testGraph.addEdge("vertex5", "vertex1");
		
		// Remove edge
		testGraph.removeEdge("vertex1", "vertex2");
		
		List<String> adjVertices = new ArrayList<String>();
		// Makes sure that edge was removed
		if(!adjVertices.equals(testGraph.getAdjacentVerticesOf("vertex1")) && testGraph.size() != 2){
			fail("There should be no adjacent vertices and the size should be two.");
		}
		
		testGraph.addEdge("vertex3", "vertex2");
		
		testGraph.removeVertex("vertex2");
		// Makes sure that when removing a vertex it is removed from all edges
		if(!adjVertices.equals(testGraph.getAdjacentVerticesOf("vertex3")) || testGraph.order() != 4) {
		fail("fail");
		}
		
		testGraph.removeVertex("vertex1");
		testGraph.removeVertex("vertex3");
		// Makes sure that vertex was removed
		if(testGraph.order() != 2) {
			fail("Vertices were not removed. The order should be 2, instead it is: " + testGraph.order());
		}
	}

	/**
	 * This method tests that the get vertices methods in graph work correctly
	 */
	@Test
	void test002_getVertices() {
		Set<String> graphVertices = new HashSet<String>();
		
		testGraph.addVertex("vertex1");
		testGraph.addVertex("vertex2");
		testGraph.addVertex("vertex3");
		testGraph.addVertex("vertex4");
		testGraph.addVertex("vertex5");
		testGraph.addVertex("vertex6");
		testGraph.addVertex("vertex7");
		testGraph.addVertex("vertex8");
		testGraph.addVertex("vertex9");
		testGraph.addVertex("vertex10");
		
		graphVertices.add("vertex1");
		graphVertices.add("vertex2");
		graphVertices.add("vertex3");
		graphVertices.add("vertex4");
		graphVertices.add("vertex5");
		graphVertices.add("vertex6");
		graphVertices.add("vertex7");
		graphVertices.add("vertex8");
		graphVertices.add("vertex9");
		graphVertices.add("vertex10");
		// Checks that the correct set was returned
		if(!graphVertices.equals(testGraph.getAllVertices())) {
			fail("Vertices list is not correct.");
		}
		
		List<String> vertex1Edges = new ArrayList<String>();
		
		testGraph.addEdge("vertex1", "vertex2");
		testGraph.addEdge("vertex1", "vertex10");
		testGraph.addEdge("vertex1", "vertex7");
		
		vertex1Edges.add("vertex2");
		vertex1Edges.add("vertex10");
		vertex1Edges.add("vertex7");
		// Checks that the correct list was returned
		if(!vertex1Edges.equals(testGraph.getAdjacentVerticesOf("vertex1"))) {
			fail("Vertices list is not correct.");
		}
		
		List<String> vertex2Edges = new ArrayList<String>();
		
		testGraph.addEdge("vertex2", "vertex3");
		testGraph.addEdge("vertex2", "vertex8");
		testGraph.addEdge("vertex2", "vertex10");
		testGraph.addEdge("vertex2", "vertex5");
		
		vertex2Edges.add("vertex3");
		vertex2Edges.add("vertex8");
		vertex2Edges.add("vertex10");
		vertex2Edges.add("vertex5");
		// Checks that the correct list was returned
		if(!vertex2Edges.equals(testGraph.getAdjacentVerticesOf("vertex2"))) {
			fail("Vertices list is not correct.");
		}	
	}

	/**
	 * This method adds and removes vertices
	 */
	@Test
	void test003_addAndRemove() {
		
		Set<String> graphVertices = new HashSet<String>();
		
		testGraph.addVertex("vertex1");
		testGraph.addVertex("vertex2");
		testGraph.addVertex("vertex3");
		testGraph.addVertex("vertex4");
		testGraph.addVertex("vertex5");
		
		testGraph.removeVertex("vertex1");
		testGraph.removeVertex("vertex5");
		
		testGraph.addVertex("vertex6");
		testGraph.addVertex("vertex7");
		testGraph.addVertex("vertex8");
		testGraph.addVertex("vertex9");
		testGraph.addVertex("vertex10");
		
		graphVertices.add("vertex2");
		graphVertices.add("vertex3");
		graphVertices.add("vertex4");
		graphVertices.add("vertex6");
		graphVertices.add("vertex7");
		graphVertices.add("vertex8");
		graphVertices.add("vertex9");
		graphVertices.add("vertex10");
		// Checks that correct vertices are in graph
		if(!graphVertices.equals(testGraph.getAllVertices())) {
			fail("Vertices list is not correct.");
		}
		
		testGraph.addVertex("vertex1");
		testGraph.addVertex("vertex5");
		
		graphVertices.add("vertex1");
		graphVertices.add("vertex5");
		// Checks that correct vertices are in graph
		if(!graphVertices.equals(testGraph.getAllVertices())) {
			fail("Vertices list is not correct.");
		}
	}
}
