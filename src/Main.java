import java.util.*;

// UVa 11504

public class Main {

	static int n;
	static int[] visited;
	static int answer;
	static int index;
	static int[] ts;
	static ArrayList[] map;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int numTests = in.nextInt();
		
		while (numTests-- > 0) {
			n = in.nextInt();
			int m = in.nextInt();
			
			map = new ArrayList[n];
			ts = new int[n];
			visited = new int[n];
			for (int i = 0; i < n; i++) {
				map[i] = new ArrayList<Integer>();
			}
			
			while (m-- > 0) {
				int a = in.nextInt() - 1;
				int b = in.nextInt() - 1;
				
				if (!map[a].contains(b)) {
					map[a].add(b);
				}
			}
			
			// Topological sort
			index = n - 1;
			for (int i = 0; i < n; i++) {
				if (visited[i] == 0) {
					toposort(i);
				}
			}
			
			// Start from node in ts
			answer = 0;
			Arrays.fill(visited, 0);
			for (int i = 0; i < n; i++) {
				if (visited[ts[i]] == 0) {
					answer++;
					dfs(ts[i]);
				}
			}
			System.out.println(answer);
		}
	}
	
	public static void dfs(int curr) {
		visited[curr] = 1;
		for (int i = 0; i < map[curr].size(); i++) {
			if (visited[(int) map[curr].get(i)] == 0) {
				dfs((int) map[curr].get(i));
			}
		}
	}
	
	public static void toposort(int curr) {
		visited[curr] = 1;
		for (int i = 0; i < map[curr].size(); i++) {
			if (visited[(int) map[curr].get(i)] == 0) {
				toposort((int) map[curr].get(i));
			}
		}
		ts[index] = curr;
		index--;
	}
}
