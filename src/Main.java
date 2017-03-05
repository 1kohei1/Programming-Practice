import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;

// UVa 315

public class Main {

	static ArrayList<Integer>[] map;
	static int dfs_counter;
	static int[] visited;
	static int[] dfs_num;
	static int[] dfs_low;
	static int[] isArticulation;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
	
		int n = in.nextInt();
		in.nextLine();
		
		while (n > 0) {
			
			// Initialize variable
			visited = new int[n];
			dfs_counter = 0;
			isArticulation = new int[n];
			dfs_num = new int[n];
			dfs_low = new int[n];
			map = new ArrayList[n];
			for (int i = 0; i < n; i++) {
				map[i] = new ArrayList<Integer>();
			}
			
			String s = in.nextLine();
			while (s.length() != 1) {
				String[] arr = s.split(" ");
				
				int start = new Integer(arr[0]) - 1;
				for (int i = 1; i < arr.length; i++) {
					int dst = new Integer(arr[i]) - 1;
					map[start].add(dst);
					map[dst].add(start);
				}
				
				s = in.nextLine();
			}
			
			solve(-1, 0);
			
			int numArticulation = 0;
			for (int i = 0; i < n; i++) {
				numArticulation += isArticulation[i];
			}
			
			System.out.println(numArticulation);
			
			n = in.nextInt();
			in.nextLine();
		}
	}
	
	public static void solve(int parent, int curr) {
		dfs_num[curr] = dfs_counter;
		dfs_low[curr] = dfs_counter;
		dfs_counter++;
		
		visited[curr] = 1;
		ArrayList<Integer> edges = map[curr];
		
		int numChildren = 0;
		
		for (int i = 0; i < edges.size(); i++) {
			int next = edges.get(i);
			
			if (next == parent) continue;
			
			if (visited[next] == 1) {
				dfs_low[curr] = Math.min(dfs_low[curr], dfs_num[next]);
			} else {
				numChildren++;
				solve(curr, next);
				
				dfs_low[curr] = Math.min(dfs_low[curr], dfs_low[next]);
				
				if (parent == -1 && numChildren > 1) {
					isArticulation[curr] = 1;
				}
				if (parent != -1 && dfs_num[curr] <= dfs_low[next]) {
					isArticulation[curr] = 1;
				}
			}
		}
	}
}