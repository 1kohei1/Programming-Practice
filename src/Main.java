import java.util.*;

// UVa 247

public class Main {

	static HashMap<String, Integer> nameMap;
	static ArrayList[] map;
	static String[] names;
	static int[] dfs_num;
	static int[] dfs_low;
	static Stack<Integer> stack;
	static int[] inStack;
	static int dfs_counter;
	
	static int numTests;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		numTests = 1;
		
		int n = in.nextInt();
		int m = in.nextInt();
		nameMap = new HashMap<String, Integer>();
		stack = new Stack<Integer>();
		
		while (0 < n || 0 < m) {
			// Initialize variables
			dfs_counter = 1;
			dfs_num = new int[n];
			dfs_low = new int[n];
			names = new String[n];
			nameMap.clear();
			inStack = new int[n];
			map = new ArrayList[n];
			for (int i = 0; i < n; i++) {
				map[i] = new ArrayList<Integer>();
			}
			
			// Get map
			int index = 0;
			for (int i = 0; i < m; i++) {
				String a = in.next();
				String b = in.next();

				int ai = -1;
				int bi = -1;
				
				if (!nameMap.containsKey(a)) {
					nameMap.put(a, index);
					names[index] = a;
					ai = index;
					index++;
				} else {
					ai = nameMap.get(a);
				}
				if (!nameMap.containsKey(b)) {
					nameMap.put(b, index);
					names[index] = b;
					bi = index;
					index++;
				} else {
					bi = nameMap.get(b);
				}
				
				map[ai].add(bi);
			}
			
			System.out.printf("Calling circles for data set %d:\n", numTests);
			for (int i = 0; i < n; i++) {
				if (dfs_num[i] == 0) {
					solve(i);
				}
			}

			n = in.nextInt();
			m = in.nextInt();
			if (n != 0 && m != 0) {
				System.out.println();
			}
			numTests++;
		}
	}
	
	public static void solve(int curr) {
		dfs_num[curr] = dfs_counter;
		dfs_low[curr] = dfs_counter;
		dfs_counter++;
		
		inStack[curr] = 1;
		stack.push(curr);
		ArrayList<Integer> edge = map[curr];
		
		for (int i = 0; i < edge.size(); i++) {
			int next = edge.get(i);
			
			// If not visited yet, visit.
			if (dfs_num[next] == 0) {
				solve(next);
			}
			// Isn't this always true?
			if (inStack[next] == 1) {
				dfs_low[curr] = Math.min(dfs_low[curr], dfs_low[next]);
			}
		}
		
		if (dfs_low[curr] == dfs_num[curr]) {
			int pop = stack.pop();
			while (pop != curr) {
				inStack[pop] = 0;
				System.out.printf("%s, ", names[pop]);
				pop = stack.pop();
			}
			inStack[pop] = 0;
			System.out.println(names[pop]);
		}
	}
}