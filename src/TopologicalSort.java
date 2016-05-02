import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class TopologicalSort {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		while (true) {
			System.out.printf("Num of points: ");
			int n = scanner.nextInt();
			
			if (n == 0) break;
			
			System.out.printf("Num of edges: ");
			int m = scanner.nextInt();
			
			// Define the graph
			Point[] graph = new Point[n];
			for (int i = 0; i < n; i++) {
				graph[i] = new Point(i);
			}
			
			int[] freq = new int[n];
			
			for (int i = 0; i < m; i++) {
				int d = scanner.nextInt();
				int u = scanner.nextInt();
				
				freq[u]++;
				graph[d].connectsTo.add(u);
			}
			
			boolean[] visited = new boolean[n];
			Arrays.fill(visited, false);
			
			ArrayList<Integer> queue = new ArrayList<Integer>();
			ArrayList<Integer> answer = new ArrayList<Integer>();
			
			for (int i = 0; i < n; i++) {
				if (freq[i] == 0) {
					queue.add(i);
				}
			}
			
			boolean isUnique = true;
			while (queue.size() != 0) {
				if (queue.size() > 1) {
					isUnique = false;
				}
				int src = queue.remove(0);
				visited[src] = true;
				for (int i = 0; i < graph[src].connectsTo.size(); i++) {
					int dst = graph[src].connectsTo.get(i);
					freq[dst]--;
					if (freq[dst] == 0 && visited[dst] == false && queue.indexOf(dst) == -1) {
						queue.add(dst);
					}
				}
				answer.add(src);
			}
			
			if (answer.size() != n) {
				System.out.print("Not possible");
			} else {
				for (int i = 0; i < answer.size(); i++) {
					System.out.printf("%d ", answer.get(i));
				}
			}
			System.out.println();
		}
	}
	
	public static void printGraph(Point[] graph, int n) {
		System.out.println("Graph is printed");
		for (int i = 0; i < n; i++) {
			System.out.printf("[%d]: ", i);
			for (int j = 0; j < graph[i].connectsTo.size(); j++) {
				System.out.printf("%d ", graph[i].connectsTo.get(j));
			}
			System.out.println();
		}
	}
}

/*
5
4
0 1
1 2
3 4
4 2

6
5
1 3
4 2
2 3
0 5
5 3

6
9
4 1
2 1
4 2
0 2
0 4
0 5
5 3
2 3
1 3

6
10
3 0
4 1
2 1
4 2
0 2
0 4
0 5
5 3
2 3
1 3

4
4
0 1
1 2
2 3
3 1

 */

class Point {
	int n;
	ArrayList<Integer> connectsTo;
	
	public Point(int n) {
		this.n = n;
		connectsTo = new ArrayList<Integer>();
	}

	public boolean equals(Object obj) {
		return this.n == ((Point) obj).n;
	}
	
	
}