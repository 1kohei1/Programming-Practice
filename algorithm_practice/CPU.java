import java.util.Scanner;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class CPU {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int numCase = scanner.nextInt();
		for (int counter = 1; counter <= numCase; counter++) {
			int numVertex = scanner.nextInt();
			
			ArrayList[] graph = new ArrayList[numVertex];
			for (int i = 0; i < numVertex; i++) {
				graph[i] = new ArrayList<Edge3>();
			}
			
			for (int i = 0; i < numVertex; i++) {
				for (int j = 0; j < numVertex; j++) {
					int length = scanner.nextInt();
					if (i != j) {
						Edge3 e = new Edge3(i, j, length);
						graph[i].add(e);
					}
				}
			}
			
			System.out.printf("Design %d: %d micrometers\n", counter, mst(graph, 0));
		}
	}
	
	public static int mst(ArrayList[] graph, int v) {
		int n = graph.length;
		boolean[] visited = new boolean[n];
		visited[v] = true;
		
		PriorityQueue<Edge3> pq = new PriorityQueue<Edge3>();
		
		for (int i = 0; i < graph[v].size(); i++) {
			pq.offer( (Edge3) graph[v].get(i));
		}
		
		int numEdges = 0;
		int answer = 0;
		
		while(pq.size() > 0) {
			Edge3 next = pq.poll();
			if (visited[next.v1] && visited[next.v2]) continue;
			
			if (!visited[next.v1]) {
				for (int i = 0; i < graph[next.v1].size(); i++) {
					pq.offer( (Edge3) graph[next.v1].get(i));
					visited[next.v1] = true;
				}
			} else {
				for (int i = 0; i < graph[next.v2].size(); i++) {
					pq.offer( (Edge3) graph[next.v2].get(i) );
					visited[next.v2] = true;
				}
			}
			
			numEdges++;
			answer += next.length;
			if (numEdges == n - 1) break;
		}
		
		return numEdges == n - 1 ? answer : -1;
	}

}

class Edge3 implements Comparable<Edge3> {
	int v1;
	int v2;
	int length;
	
	public Edge3(int v1, int v2, int length) {
		this.v1 = v1;
		this.v2 = v2;
		this.length = length;
	}
	
	public int compareTo(Edge3 other) {
		return this.length - other.length;
	}
}

/*
2
3
0 1 2
1 0 4
2 4 0
5
0 3 2 4 1
3 0 1 2 5
2 1 0 7 1
4 2 7 0 3
1 5 1 3 0 
*/