import java.util.*;

// ABC 70-D
// https://beta.atcoder.jp/contests/abc070/tasks/abc070_d

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int N = in.nextInt();
		
		ArrayList<ArrayList<KPoint>> map = new ArrayList<ArrayList<KPoint>>();
		for (int i = 0; i < N; i++) {
			map.add(new ArrayList<KPoint>());
		}
		
		for (int i = 0; i < N - 1; i++) {
			int a = in.nextInt() - 1;
			int b = in.nextInt() - 1;
			int c = in.nextInt();

			map.get(a).add(new KPoint(b, c));
			map.get(b).add(new KPoint(a, c));
		}
		int Q = in.nextInt();
		int K = in.nextInt() - 1;

		long[] dist = new long[N];
		int[] visited = new int[N];
		dfs(K, dist, visited, 0, map);
		
		for (int i = 0; i < Q; i++) {
			int start = in.nextInt() - 1;
			int end = in.nextInt() - 1;
			System.out.println(dist[start] + dist[end]);
		}
	}
	
	public static void dfs(int curr, long[] dist, int[] visited, long d, ArrayList<ArrayList<KPoint>> map) {
		visited[curr] = 1;
		dist[curr] = d;
		for (int i = 0; i < map.get(curr).size(); i++) {
			KPoint kp = map.get(curr).get(i);
			if (visited[kp.p] == 0) {
				dfs(kp.p, dist, visited, d + kp.dist, map);
			}
		}
	}
}

class KPoint {
	int p;
	int dist;
	
	public KPoint(int p, int dist) {
		this.p = p;
		this.dist = dist;
	}
}