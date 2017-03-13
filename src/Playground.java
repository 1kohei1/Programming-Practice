import java.util.*;

/**
 * Strongly Connected components
 */

public class Playground {

	static int[] dfs_num;
	static int[] dfs_low;
	static int dfs_counter;
	static ArrayList[] map;
	static Stack<Integer> stack;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		stack = new Stack<Integer>();
		
		int n = in.nextInt();
		while (n > 0) {
			// Initialize variables
			dfs_num = new int[n];
			dfs_low = new int[n];
			dfs_counter = 1;
			map = new ArrayList[n];
			
			for (int i = 0; i < n; i++) {
				map[i] = new ArrayList<Integer>();
			}
			
			// Get map
			int m = in.nextInt();
			for (int i = 0; i < m; i++) {
				int a = in.nextInt();
				int b = in.nextInt();
				map[a].add(b);
			}
			
			for (int i = 0; i < n; i++) {
				if (dfs_num[i] == 0) {
					solve(i);
				}
			}
			
			n = in.nextInt();
		}
	}
	
	public static void solve(int curr) {
		dfs_num[curr] = dfs_counter;
		dfs_low[curr] = dfs_counter;
		dfs_counter++;
		
		stack.add(curr);
		ArrayList<Integer> edge = map[curr];
		
		for (int i = 0; i < edge.size(); i++) {
			int next = edge.get(i);
			
			// Unvisited node
			if (dfs_num[next] == 0) {
				solve(next);
			}
			
			dfs_low[curr] = Math.min(dfs_low[curr], dfs_low[next]);
			
			if (dfs_num[curr] == dfs_low[curr]) {
				System.out.printf("SCC (");
				int pop = stack.pop();
				while (pop != curr) {
					System.out.printf("%d ", pop);
					pop = stack.pop();
				}
				System.out.printf("%d)\n", pop);
			}
		}
	}
}

// Test case
/*
8 9
0 1
1 3
3 2
2 1
3 4
4 5
5 7
7 6
6 4
0
 */