import java.util.*;

// UVa 11709

public class Main {

	static int numScc;
	static int[] dfs_num;
	static int[] dfs_low;
	static int dfs_counter;
	static ArrayList[] map;
	static Stack<Integer> stack;
	static int[] inStack;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int p = in.nextInt();
		int t = in.nextInt();
		HashMap<String, Integer> names = new HashMap<String, Integer>();
		stack = new Stack<Integer>();
		
		while (p > 0 && t >= 0) {
			in.nextLine();
			
			// Initialize variables
			numScc = 0;
			dfs_num = new int[p];
			dfs_low = new int[p];
			dfs_counter = 1;
			map = new ArrayList[p];
			inStack = new int[p];
			names.clear();
			for (int i = 0; i < p; i++) {
				String s = in.nextLine();
				names.put(s, i);
				map[i] = new ArrayList<Integer>();
			}
			
			while (t-- > 0) {
				String s1 = in.nextLine();
				String s2 = in.nextLine();
				
				int i1 = names.get(s1);
				int i2 = names.get(s2);
				
				if (!map[i1].contains(i2)) {
					map[i1].add(i2);
				}
			}
			
			for (int i = 0; i < p; i++) {
				if (dfs_num[i] == 0) {
					scc(i);					
				}
			}
			
			System.out.println(numScc);
			
			p = in.nextInt();
			t = in.nextInt();
		}
	}
	
	public static void scc(int curr) {
		dfs_num[curr] = dfs_counter;
		dfs_low[curr] = dfs_counter;
		dfs_counter++;
		
		stack.push(curr);
		inStack[curr] = 1;
		ArrayList<Integer> edge = map[curr];
		
		for (int i = 0; i < edge.size(); i++) {
			int next = edge.get(i);
			
			if (dfs_num[next] == 0) {
				scc(next);
			}
			if (inStack[next] == 1) {
				dfs_low[curr] = Math.min(dfs_low[curr], dfs_low[next]);
			}
		}
		
		if (dfs_low[curr] == dfs_num[curr]) {
			int pop = stack.pop();
			while (pop != curr) {
				inStack[pop] = 0;
				pop = stack.pop();
			}
			inStack[pop] = 0;
			numScc++;
		}
	}
}
