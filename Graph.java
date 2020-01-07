
import java.util.*;

class Graph {
	private int V; // No. of vertices
	private LinkedList<Integer> adj[]; // Adjacency List

	// Constructor of the graph of size v
	public Graph(int v) {
		V = v;
		adj = new LinkedList[v];
		for (int i = 0; i < v; ++i)
			adj[i] = new LinkedList();
	}

	// Function to add E(v, w) into the graph
	void addEdge(int v, int w) {
		adj[v].add(w);
		adj[w].add(v);
	}

	// getter for a graph order
	public int getV() {
		return V;
	}

	// gets the eccentricity of given source s
	int Ecc(int s) {

		/*
		 * Mark all the vertices as not visited and initialize a distance array set as
		 * false)
		 */

		boolean visited[] = new boolean[V];
		int[] distance = new int[V];
		int ecc = 0;

		// Create a queue for the code

		LinkedList<Integer> queue = new LinkedList<Integer>();

		// Mark the current node as visited and put in queue
		visited[s] = true;
		queue.add(s);

		while (queue.size() != 0) {
			// poll a vertex from queue
			s = queue.poll();

			// Get all adjacent vertices of the dequeued vertex s
			// If a adjacent has not been visited, then mark it
			// visited and enqueue it, and update the distance to this vertix
			// iterate through the adjacncy list of s
			Iterator<Integer> i = adj[s].listIterator();
			while (i.hasNext()) {
				int n = i.next();
				if (!visited[n]) {
					distance[n] = distance[s] + 1;
					visited[n] = true;
					queue.add(n);
				}
			}
		}
		// to get the eccentricity, get the max distance in the list
		for (int i = 0; i < distance.length; i++)
			ecc = Math.max(ecc, distance[i]);

		return ecc;

	}

	// method that calculates the eccentricity of all vertices and returns max
	// as diameter
	public int diameter() {
		int diameter = Integer.MIN_VALUE;
		int[] Eccentricities = new int[V];
		for (int v = 0; v < V; v++)
			Eccentricities[v] = Ecc(v);

		for (int i = 0; i < Eccentricities.length; i++)
			diameter = Math.max(diameter, Eccentricities[i]);

		return diameter;
	}

	// method that calculates the eccentricity of all vertices and returns min
	// as diameter
	public int radius() {
		int radius = Integer.MAX_VALUE;
		int[] Eccentricities = new int[V];
		for (int v = 0; v < V; v++)
			Eccentricities[v] = Ecc(v);

		for (int i = 0; i < Eccentricities.length; i++)
			radius = Math.min(radius, Eccentricities[i]);

		return radius;
	}

	// method that calculates the petijean index for a graph
	public double petijean() {
		double radius = radius();
		double diameter = diameter();
		return (diameter - radius) / radius;
	}

}
