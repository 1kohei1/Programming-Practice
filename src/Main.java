import java.util.*;

// UVa 796

public class Main {

	static ArrayList[] map;
	static int dfs_counter;
	static int[] dfs_num;
	static int[] dfs_low;
	static ArrayList<Bridge> bridges;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		bridges = new ArrayList<Bridge>();
		
		while (in.hasNext()) {
			int n = in.nextInt();
			// Initialize variables
			dfs_counter = 1;
			bridges.clear();
			dfs_num = new int[n];
			dfs_low = new int[n];
			map = new ArrayList[n];
			for (int i = 0; i < n; i++) {
				map[i] = new ArrayList<Integer>();
			}
			
			for (int i = 0; i < n; i++) {
				int start = in.nextInt();
				String s = in.next();
				int numEdges = new Integer(s.substring(1, s.length() - 1));
				for (int j = 0; j < numEdges; j++) {
					int end = in.nextInt();
					map[start].add(end);
				}
			}
			
			// Find articulation bridges
			for (int i = 0; i < n; i++) {
				if (dfs_num[i] == 0) {
					articulation(-1, i);
				}
			}
			
			bridges.sort(null);
			
			// Print
			System.out.printf("%d critical links\n", bridges.size());
			for (int i = 0; i < bridges.size(); i++) {
				Bridge b = bridges.get(i);
				System.out.printf("%d - %d\n", b.start, b.end);
			}
			
			System.out.println();
		}
		
	}
	
	public static void articulation(int parent, int curr) {
		dfs_num[curr] = dfs_counter;
		dfs_low[curr] = dfs_counter;
		dfs_counter++;
		
		ArrayList<Integer> edge = map[curr];
		
		for (int i = 0; i < edge.size(); i++) {
			int next = edge.get(i);
			
			// Not visited yet
			if (dfs_num[next] == 0) {
				articulation(curr, next);
				
				dfs_low[curr] = Math.min(dfs_low[curr], dfs_low[next]);
			
				if (dfs_num[curr] < dfs_low[next]) {
					bridges.add(new Bridge(curr, next));
				}
			} 
			// Visited node and not parent
			else if (next != parent) {
				dfs_low[curr] = Math.min(dfs_low[curr], dfs_num[next]);
			}
		}
		
	}
}

class Bridge implements Comparable<Bridge> {
	int start;
	int end;
	
	public Bridge(int start, int end) {
		if (start < end) {
			this.start = start;
			this.end = end;
		} else {
			this.start = end;
			this.end = start;
		}
	}
	
	@Override
	public int compareTo(Bridge o) {
		if (this.start == o.start) {
			return this.end - o.end;
		} else {
			return this.start - o.start;
		}
	}
	
	
}