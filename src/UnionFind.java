import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

// Disjoint-set data structure
// Keeps track of subgroups
// Used with cycle detection
public class UnionFind {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int n = scanner.nextInt();
		int numEdges = scanner.nextInt();
	
		// Edge is implemented in Cables in 2015-10-03
		PriorityQueue<Edge> queue = new PriorityQueue<Edge>();
		for (int i = 0; i < numEdges; i++) {
			queue.add(new Edge(scanner.nextInt(), scanner.nextInt(), scanner.nextDouble()));
		}
		
		int dist = 0;
		int[] parent = new int[n];
		Arrays.fill(parent, -1);
		int connectedEdges = 0;
		
		while (connectedEdges != n - 1) {
			Edge e = queue.poll();
			
			int aParent = findParent(parent, e.a);
			int bParent = findParent(parent, e.b);
			
			System.out.printf("Edge a: %d, b: %d, d: %.2f\n", e.a, e.b, e.d);
			
			// a and b belongs to different group
			if (aParent != bParent) {
				parent = union(parent, e.a, e.b);
				dist += e.d;
				connectedEdges++;
			} else {
				System.out.println("Edge detected");
			}
			print(parent);
		}
		print(parent);
		System.out.println(dist);
	}
	
	public static int findParent(int[] parent, int n) {
		if (parent[n] == -1) {
			return n;
		}
		return findParent(parent, parent[n]);
	}
	
	public static int[] union(int[] parent, int x, int y) {
		int xParent = findParent(parent, x);
		int yParent = findParent(parent, y);
		
		// The subset that y belongs is merged to the subset that x belongs
		parent[yParent] = xParent;
		return parent;
	}
	
	public static void print(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			System.out.printf("%d ", nums[i]);
		}
		System.out.println();
	}
/*
4 6
0 3 2
0 2 5
0 1 1
1 2 2
1 3 5
2 3 1

6 7
0 1 3
2 3 2
4 3 4
4 1 5
0 4 6
0 5 11
1 5 8
 */
}
