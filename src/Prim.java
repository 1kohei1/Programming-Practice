import java.util.*;

public class Prim {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int numVertex = scanner.nextInt();
		int numEdges = scanner.nextInt();
		ArrayList[] graph = new ArrayList[numVertex];
		
		// Initialize graph
		for (int i = 0; i < numVertex; i++) {
			graph[i] = new ArrayList<Edge1>();
		}
		
		for (int i = 0; i < numEdges; i++) {
			int start = scanner.nextInt();
			int end = scanner.nextInt();
			int weight = scanner.nextInt();
			
			Edge1 e1 = new Edge1(start, end, weight);
			Edge1 e2 = new Edge1(end, start, weight);
			
			// So I have to store them in both vertex.
			graph[start].add(e1);
			graph[end].add(e2);
		}
		
		// The shortest path from 0th vertex is 
		System.out.printf("%d\n", mst(graph, 0));
	}
	
	/*
	 * So graph is array of ArrayList<Edge>
	 * ArrayList.get(0) means get all edges from vertex 0.
	 * So there is a constraints in Edge in ArrayList? Like ArrayList does not contain
	 * Edge
	 * -v1: 0
	 * -v2: 3
	 * -weight: 5
	 */
	
	public static int mst(ArrayList[] graph, int v) {
		// Mark vertex v as being in mst.
		int n = graph.length;
		boolean[] used = new boolean[n];
		used[v] = true; // v is start vertex or the initial vertex picked at random
		
		// Add all of v's edges into the priority queue
		PriorityQueue<Edge1> pq = new PriorityQueue<Edge1>(); // I guess it prioritize Edge by compareTo method
		for (int i = 0; i < graph[v].size(); i++) {
			pq.offer( (Edge1) graph[v].get(i) ); // How about this? (Edge) graph[next.v1].get(i)
		}
		
		int numEdges = 0;
		int res = 0;
		
		while (pq.size() > 0) {
			// Get next edge
			Edge1 next = pq.poll();
			if (used[next.v1] && used[next.v2]) continue;
			
			// Add new items to priorityQueue - need to check which vertex is new
			if (!used[next.v1]) { // This does not matter. It is just getting other edges of the other side that is not from.
				for (int i = 0; i < graph[next.v1].size(); i++) {
					// Duplicate Edge could be added to priority queue, but it does not matter.
					// (Assuming undirected graph)
					pq.offer( (Edge1) graph[next.v1].get(i) ); 
					used[next.v1] = true;
				}
			} else {
				for (int i = 0; i < graph[next.v2].size(); i++) {
					pq.offer( (Edge1) graph[next.v2].get(i) );
					used[next.v2] = true;
				}
			}
			
			// Bookkeeping
			numEdges++;
			res += next.weight;
			if (numEdges == n - 1) break;
		}
		
		// -1 indicates no MST, so not connected
		return numEdges == n - 1 ? res : -1;
	}
}

class Edge1 implements Comparable<Edge1> {
	public int v1;
	public int v2;
	public int weight;
	
	public Edge1(int v1, int v2, int weight) {
		this.v1 = v1;
		this.v2 = v2;
		this.weight = weight;
	}
	
	public int compareTo(Edge1 other) {
		return this.weight - other.weight;
	}
}

/*
Input
7 11
0 3 5
1 0 7
1 2 8
1 3 9
4 1 7
2 4 5
3 4 15
5 3 6
5 4 8
4 6 9
6 5 11
*/