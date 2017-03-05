import java.util.Scanner;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * Articulation point algorithm
 * Resources are: 
 * http://www.geeksforgeeks.org/articulation-points-or-cut-vertices-in-a-graph/
 * https://www.hackerearth.com/practice/notes/nj/?utm_source=header&utm_medium=search&utm_campaign=he-search
 * https://www.youtube.com/watch?v=2kREIkF9UAs
 *
 * Please use the one that makes the most sense for you.
 */

public class Playground {

	static ArrayList<Integer> map[];
	static int dfs_counter;
	static int[] visited;
	static int[] dfs_num;
	static int[] dfs_low;
	static int[] isArticulation;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int n = in.nextInt();
		ArrayList<Integer> list;
		
		while (n > 0) {
			// Initialize variable
			map = new ArrayList[n];
			for (int i = 0; i < n; i++) {
				map[i] = new ArrayList<Integer>();
			}
			dfs_counter = 0;
			visited = new int[n];
			dfs_num = new int[n];
			dfs_low = new int[n];
			isArticulation = new int[n];
			
			int m = in.nextInt();
			for (int i = 0; i < m; i++) {
				int a = in.nextInt();
				int b = in.nextInt();

				map[a].add(b);
				map[b].add(a);
			}
			
			dfs(-1, 0);
			
			System.out.printf("Articulation point is:");
			for (int i = 0; i < n; i++) {
				if (isArticulation[i] == 1) {
					System.out.printf(" %d", i);
				}
			}
			System.out.println();
			
			n = in.nextInt();
		}
	}
	
	public static void printMap() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].size(); j++) {
				System.out.printf("%d ", map[i].get(j));
			}
			System.out.println();
		}
	}
	
	public static void dfs(int parent, int curr) {
		dfs_num[curr] = dfs_counter;
		dfs_low[curr] = dfs_counter;
		dfs_counter++;
				
		visited[curr] = 1;
		
		ArrayList<Integer> list = map[curr];
		
		// If it is a root of DFS tree, and connected to more than 1 edge, it is automatically articulation point.
		// So set isArticulation[curr] = 1
		// FALSE!!
		// Checking a root condition here doesn't work. If children nodes are connected to each other, removing root doesn't make children node isolate.
		// Remove this condition and test what happens in the last test case.
		// if (parent == -1 && list.size() > 1) {
		//	isArticulation[curr] = 1;
		// }
		
		int numChildren = 0;
		
		for (int i = 0; i < list.size(); i++) {
			int next = list.get(i);
			
			// If it is a parent node, do nothing.
			if (next == parent) {
				continue;
			}
			
			// If it is a visited node, update current node's dfs_low  
			if (visited[next] == 1) {
				// Why are we comparing with next node's dfs_num? Why can't we compare with dfs_low? 
				// If that node is visited, dfs_num must be always smaller right? Since dfs_counter keeps increasing.
				// Why can't we do dfs_low[curr] = dfs_low[next]? 
				// Because this always overrides dfs_low[curr]. Probably there is case that current node has connected to two visited nodes,
				// and the first visited node's dfs_low value is less than the second visited node's dfs_low value. 
				// I still don't know why we are not using dfs_low here. Please leave a comment if you know why we use dfs_num instead of dfs_low
				
				// I don't know why, but this must use dfs_num.
				
				// dfs_low[curr] = Math.min(dfs_low[curr], dfs_num[next]);
				dfs_low[curr] = Math.min(dfs_low[curr], dfs_low[next]);
			}
			// If it is an unvisited node, call dfs on that node.
			else {
				numChildren++;
				dfs(curr, next);
				
				// If next node is achievable from already visited node, current node is also achievable from already visited node. 
				// Therefore, apply change made to next node to current node.
				dfs_low[curr] = Math.min(dfs_low[curr], dfs_low[next]);

				// If it is a root node and it has two disconnected graph as children, it is an articulation point.
				if (parent == -1 && numChildren > 1) {
					isArticulation[curr] = 1;
				}
				// If reaching next node takes more than it takes to reach current node, removing current node will isolate the next node.
				// Therefore, current node is an articulation point.
				if (parent != -1 && dfs_num[curr] <= dfs_low[next]) {
					isArticulation[curr] = 1;
				}
			}
		}
	}
}

// Test case
/*
6 5
0 1
1 2
1 4
3 4
4 5
=> 1 4

6 6
0 1
1 2
1 3
1 4
1 5
4 5
=> 1

5 5
1 0
0 2
2 1
0 3
3 4
=> 0 3

4 3
0 1
1 2
2 3
=> 1 2

7 8
0 1
1 2
2 0
1 3
1 4
1 6
3 5
4 5
=> 1

5 4
4 0
4 1
4 2
4 3
=> 4

6 5
1 4
1 0
1 2
4 3
4 5
=> 1 4
0
 */