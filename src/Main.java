import java.util.*;

// UVa 11838

public class Main {

	static int[] dfs_num;
	static int[] dfs_low;
	static int dfs_counter;
	static ArrayList[] map;
	static Stack<Integer> stack;
	static int[] inStack;
	static int numSCC;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int n = in.nextInt();
		int m = in.nextInt();
		stack = new Stack<Integer>();
		
		while (0 < n && 0 < m) {
			// Initialize
			dfs_num = new int[n];
			dfs_low = new int[n];
			dfs_counter = 1;
			inStack = new int[n];
			numSCC = 0;
			map = new ArrayList[n];
			for (int i = 0; i < n; i++) {
				map[i] = new ArrayList<Integer>();
			}
			
			while (m-- > 0) {
				int a = in.nextInt() - 1;
				int b = in.nextInt() - 1;
				int c = in.nextInt();
				
				map[a].add(b);
				if (c == 2) {
					map[b].add(a);
				}
			}
			
			for (int i = 0; i < n; i++) {
				if (dfs_num[i] == 0) {
					countCSS(i);
				}
			}
			
			System.out.println(numSCC == 1 ? 1 : 0);
			
			n = in.nextInt();
			m = in.nextInt();
		}
	}
	
	public static void countCSS(int curr) {
		dfs_num[curr] = dfs_counter;
		dfs_low[curr] = dfs_counter;
		dfs_counter++;
		
		stack.push(curr);
		inStack[curr] = 1;
		ArrayList<Integer> edge = map[curr];
		
		for (int i = 0; i < edge.size(); i++) {
			int next = edge.get(i);
			
			if (dfs_num[next] == 0) {
				countCSS(next);
			}
			if (inStack[next] == 1) {
				dfs_low[curr] = Math.min(dfs_low[curr], dfs_low[next]);
			}
		}
		
		if (dfs_num[curr] == dfs_low[curr]) {
			numSCC++;
			int pop = stack.pop();
			while (pop != curr) {
				inStack[pop] = 0;
				pop = stack.pop();
			}
			inStack[pop] = 0;
		}
	}
}