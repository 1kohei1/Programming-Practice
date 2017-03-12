import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

// UVa 10199

public class Main {

	static HashMap<String, Integer> cities;
	static String[] cityNames;
	static ArrayList[] map;
	static int dfs_counter;
	static int[] dfs_num;
	static int[] dfs_low;
	static int[] isArticulation;
	static ArrayList<String> answerNames;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int n = in.nextInt();
		int numTests = 1;
		
		cities = new HashMap<String, Integer>();
		answerNames = new ArrayList<String>();
		
		while (n > 0) {
			if (numTests != 1) {
				System.out.println();
			}
			// Initialize variables
			cities.clear();
			map = new ArrayList[n];
			dfs_counter = 1;
			dfs_num = new int[n];
			dfs_low = new int[n];
			cityNames = new String[n];
			answerNames.clear();
			isArticulation = new int[n];
			
			// Get city name
			for (int i = 0; i < n; i++) {
				String name = in.next();
				cities.put(name, i);
				cityNames[i] = name;
				map[i] = new ArrayList<Integer>();
 			}
			
			int r = in.nextInt();
			for (int i = 0; i < r; i++) {
				String a = in.next();
				String b = in.next();
				
				int ai = cities.get(a);
				int bi = cities.get(b);
				
				map[ai].add(bi);
				map[bi].add(ai);
			}
			
			for (int i = 0; i < n; i++) {
				if (dfs_num[i] == 0) {
					solve(-1, i);
				}
			}
			
			answerNames.sort(null);
			System.out.printf("City map #%d: %d camera(s) found\n", numTests, answerNames.size());
			for (int i = 0; i < answerNames.size(); i++) {
				System.out.println(answerNames.get(i));
			}
			
			// Get next test set
			n = in.nextInt();
			numTests++;
		}
	}
	
	public static void solve(int parent, int curr) {
		dfs_num[curr] = dfs_counter;
		dfs_low[curr] = dfs_counter;
		dfs_counter++;
		
		int numChildren = 0;
		ArrayList<Integer> edge = map[curr];
		
		for (int i = 0; i < edge.size(); i++) {
			int next = edge.get(i);
			
			// Unvisited node
			if (dfs_num[next] == 0) {
				numChildren++;
				solve(curr, next);
				
				dfs_low[curr] = Math.min(dfs_low[curr], dfs_low[next]);
				
				if (parent != -1 && dfs_num[curr] <= dfs_low[next] && !answerNames.contains(cityNames[curr])) {
					answerNames.add(cityNames[curr]);
				}
			}
			// It's visited node, but not equal to the parent
			else if (next != parent) {
				dfs_low[curr] = Math.min(dfs_low[curr], dfs_num[next]);
			}
		}
		
		if (parent == -1 && numChildren > 1 && !answerNames.contains(cityNames[curr])) {
			answerNames.add(cityNames[curr]);
		}
	}
}