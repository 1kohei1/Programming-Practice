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
	static int[] inStack;
	
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
			inStack = new int[n];
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
		inStack[curr] = 1;
		ArrayList<Integer> edge = map[curr];
		
		for (int i = 0; i < edge.size(); i++) {
			int next = edge.get(i);
			
			// Unvisited node
			if (dfs_num[next] == 0) {
				solve(next);
			}
			if (inStack[next] == 1) {
				dfs_low[curr] = Math.min(dfs_low[curr], dfs_low[next]);
			}
		}
		
		if (dfs_num[curr] == dfs_low[curr]) {
			System.out.printf("SCC (");
			int pop = stack.pop();
			while (pop != curr) {
				inStack[pop] = 0;
				System.out.printf("%d ", pop);
				pop = stack.pop();
			}
			inStack[pop] = 0;
			System.out.printf("%d)\n", pop);
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
=>
SCC (6 7 5 4)
SCC (2 3 1)
SCC (0)

5 6
0 1
1 4
1 2
2 0
2 3
3 2
=> 
SCC (4)
SCC (0 1 3 2)
4 5
0 1
1 2
2 0
0 3
3 0
=>
SCC (3 2 1 0)
3 2
1 0
2 0
=>
SCC (0)
SCC (1)
SCC (2)
5 5
0 1
1 2
2 0
0 4
3 0
0 0
=>
SCC (4)
SCC (2 1 0)
SCC (3)

4 3
0 1
1 2
2 3
=>
SCC (3)
SCC (2)
SCC (1)
SCC (0)

6 7
0 1
1 2
2 3
3 4
4 2
1 5
5 2
=>
SCC (4 3 2)
SCC (5)
SCC (1)
SCC (0)

6 7
0 1
1 2
2 3
3 4
5 0
4 2
1 5
5 2
=>
SCC (4 3 2)
SCC (5 1 0)
 */