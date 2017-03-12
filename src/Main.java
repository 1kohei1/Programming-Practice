import java.util.*;

// UVa 10765

public class Main {

	static ArrayList[] map;
	static int dfs_counter;
	static int[] dfs_num;
	static int[] dfs_low;
	static ArrayList<Integer> articulations;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int n = in.nextInt();
		int m = in.nextInt();
		articulations = new ArrayList<Integer>();
		
		while (n > 0 && m > 0) {
						
			// Initialize variables
			dfs_counter = 1;
			dfs_num = new int[n];
			dfs_low = new int[n];
			articulations.clear();
			map = new ArrayList[n];
			for (int i = 0; i < n; i++) {
				map[i] = new ArrayList<Integer>();
			}
			
			// Get map
			int a = in.nextInt();
			int b = in.nextInt();
			while (a >= 0 && b >= 0) {
				map[a].add(b);
				map[b].add(a);
				
				a = in.nextInt();
				b = in.nextInt();
			}
			
			// Find articulation points
			findArticulation(-1, 0);
			
			ArrayList<Solution> solutions = new ArrayList<Solution>();
			for (int i = 0; i < articulations.size(); i++) {
				solutions.add(new Solution(articulations.get(i), calculatePidgeonValue(articulations.get(i))));
			}
			solutions.sort(null);
			
			if (m > solutions.size()) {
				for (int i = 0; i < solutions.size(); i++) {
					Solution s = solutions.get(i);
					System.out.printf("%d %d\n", s.removing, s.pigeonValue);
				}
				// Pigeon value will be 1
				int counter = 0;
				int index = 0;
				while (counter < m - solutions.size()) {
					while (articulations.contains(index)) {
						index++;
					}
					System.out.printf("%d %d\n", index, 1);
					counter++;
					index++;
				}
			} else {
				for (int i = 0; i < m; i++) {
					Solution s = solutions.get(i);
					System.out.printf("%d %d\n", s.removing, s.pigeonValue);
				}
			}
			
			// Get next test case
			n = in.nextInt();
			m = in.nextInt();
			System.out.println();
		}
	}
	
	public static void findArticulation(int parent, int curr) {
		dfs_num[curr] = dfs_counter;
		dfs_low[curr] = dfs_counter;
		dfs_counter++;
		
		int numChildren = 0;
		ArrayList<Integer> edge = map[curr];
		
		for (int i = 0; i < edge.size(); i++) {
			int next = edge.get(i);
			
			// Next node is not visited yet.
			if (dfs_num[next] == 0) {
				numChildren++;
				findArticulation(curr, next);
				
				dfs_low[curr] = Math.min(dfs_low[curr], dfs_low[next]);
				
				if (parent != -1 && dfs_num[curr] <= dfs_low[next] && !articulations.contains(curr)) {
					articulations.add(curr);
				}
			}
			// Next node is visited. But not parent. Back edge
			else if (parent != next) {
				dfs_low[curr] = Math.min(dfs_low[curr], dfs_num[next]);
			}
		}
		
		if (parent == -1 && numChildren > 1 && !articulations.contains(curr)) {
			articulations.add(curr);
		}
	}

	public static int calculatePidgeonValue(int removingIndex) {
		int pv = 0;
		
		int n = map.length;
		boolean[] visited = new boolean[n];
		visited[removingIndex] = true;
		
		ArrayList<Integer> edge = map[removingIndex];
		for (int i = 0; i < edge.size(); i++) {
			int next = edge.get(i);
			
			if (visited[next] == false) {
				pv++;
				recurr(next, removingIndex, visited);
			}
		}
		
		return pv;
	}
	
	public static void recurr(int index, int removingIndex, boolean[] visited) {
		visited[index] = true;
		
		ArrayList<Integer> edge = map[index];
		for (int i = 0; i < edge.size(); i++) {
			int next = edge.get(i);
			
			if (visited[next] == false) {
				recurr(next, removingIndex, visited);
			}
		}
	}
}

class Solution implements Comparable<Solution> {

	int removing;
	int pigeonValue;
	
	public Solution(int removing, int pigeonValue) {
		this.removing = removing;
		this.pigeonValue = pigeonValue;
	}
	
	@Override
	public int compareTo(Solution o) {
		if (this.pigeonValue == o.pigeonValue) {
			return this.removing - o.removing;
		} else {
			return o.pigeonValue - this.pigeonValue;
		}
	}
	
}