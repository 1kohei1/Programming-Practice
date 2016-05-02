import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.awt.Point;

public class Cables {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		while (true) {
			int n = scanner.nextInt();
			if (n == 0) {
				break;
			}
			
			Point[] points = new Point[n];
			for (int i = 0; i < n; i++) {
				points[i] = new Point(scanner.nextInt(), scanner.nextInt());
			}
			
			PriorityQueue<Edge> queue = new PriorityQueue<Edge>();
			
			// Sort distance
			for (int i = 0; i < n; i++) {
				for (int j = i + 1; j < n; j++) {
					double d = points[i].distance(points[j]);
					queue.offer(new Edge(i, j, d));
				}
			}

			int edgeConnected = 0;
			double dist = 0;
			int[] parent = new int[n];
			Arrays.fill(parent, -1);
			
			while (edgeConnected != n - 1) {
				Edge e = queue.poll();
				
				int aParent = findParent(parent, e.a);
				int bParent = findParent(parent, e.b);
				
				if (aParent != bParent) {
					parent = merge(parent, aParent, bParent);
					dist += e.d;
					edgeConnected++;
				}
			}
			System.out.printf("%.2f\n", dist);
		}
	}
	
	public static int findParent(int[] parent, int n) {
		if (parent[n] == -1) {
			return n;
		}
		return findParent(parent, parent[n]);
	}
	
	public static int[] merge(int[] parent, int a, int b) {
		int aParent = findParent(parent, a);
		int bParent = findParent(parent, b);
		parent[aParent] = bParent;
		return parent;
	}
}
/*
4
0 0
0 10
10 0
10 10
2
0 0
10 10
0
*/
class Edge implements Comparable<Edge> {
	int a;
	int b;
	double d;
	
	public Edge(int a, int b, double d) {
		this.a = a;
		this.b = b;
		this.d = d;
	}

	@Override
	public int compareTo(Edge o) {
		if (this.d > o.d) {
			return 1; 
		} else if (this.d < o.d) {
			return -1;
		} else {
			return 0;
		}
	}
}