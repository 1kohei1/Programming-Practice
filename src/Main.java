import java.util.*;

// AGC 13-B
// http://agc013.contest.atcoder.jp/tasks/agc013_b
 
public class Main {

	static int n;
	static ArrayList[] map;
	static boolean answerFound = false;
	static ArrayList<Integer> answer;

	public static void main (String[] args) throws java.lang.Exception {
		Scanner in = new Scanner(System.in);
		
		n = in.nextInt();
		int m = in.nextInt();
		
		map = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			map[i] = new ArrayList<Integer>();
		}
		
		for (int i = 0; i < m; i++) {
			int a = in.nextInt() - 1;
			int b = in.nextInt() - 1;
			
			map[a].add(b);
			map[b].add(a);
		}

		int index = 0;
		while (map[index].size() == 0) {
			index++;
		}
		int node1 = index;
		int node2 = (int) map[index].get(0);
		
		int[] visited = new int[n];
		ArrayList<Integer> path = new ArrayList<Integer>();
		visited[node1] = 1;
		visited[node2] = 1;
		path.add(node1);
		dfs(node1, path, visited, true);
		path.add(node2);
		dfs(node2, path, visited, false);
		
		System.out.println(path.size());
		for (int i = 0; i < path.size(); i++) {
			System.out.printf("%d ", path.get(i) + 1);
		}
		System.out.println();
	}
	
	public static void dfs(int node, ArrayList<Integer> path, int[] visited, boolean insertFront) {
		for (int i = 0; i < map[node].size(); i++) {
			int next = (int) map[node].get(i);
			
			if (visited[next] == 0) {
				visited[next] = 1;
				if (insertFront) {
					path.add(0, next);
				} else {
					path.add(next);
				}
				dfs(next, path, visited, insertFront);
				break;
			}
		}
	}
}