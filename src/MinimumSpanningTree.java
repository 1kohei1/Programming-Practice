import java.util.Scanner;
import java.util.Arrays;
import java.util.PriorityQueue;

public class MinimumSpanningTree {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		PriorityQueue<Edge4> queue = new PriorityQueue<Edge4>();
		
		for (int i = 0; i < m; i++) {
			queue.add(new Edge4(scanner.nextInt(), scanner.nextInt(), scanner.nextInt()));
		}
		
		int numEdges = 0;
		double dist = 0;
		boolean[] visited = new boolean[n];
		Arrays.fill(visited, false);
		
		while (numEdges != n - 1) {
			Edge4 e = queue.poll();
			
			if (visited[e.a] == false || visited[e.b] == false) {
				visited[e.a] = true;
				visited[e.b] = true;
				dist += e.d;
				numEdges++;
			}
		}
		System.out.println(dist);
	}
}

class Edge4 implements Comparable<Edge4> {

	int a;
	int b;
	int d;
	
	public Edge4(int a, int b, int d) {
		this.a = a;
		this.b = b;
		this.d = d;
	}
	
	@Override
	public int compareTo(Edge4 o) {
		return this.d - o.d;
	}
}