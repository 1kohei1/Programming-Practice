import java.util.*;

// ABC 51-D
// https://abc051.contest.atcoder.jp/tasks/abc051_d

public class Main {

	static int N;
	static int[][] map;
	static int[][] usedEdges;
	static int numUsedEdges;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		N = in.nextInt();
		int M = in.nextInt();
		map = new int[N][N];
		usedEdges = new int[N][N];
		numUsedEdges = 0;
		
		for (int i = 0; i < M; i++) {
			int a = in.nextInt() - 1;
			int b = in.nextInt() - 1;
			int c = in.nextInt();
			
			map[a][b] = c;
			map[b][a] = c;
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i != j) {
					dijkstra2(i);
				}
			}
		}
		System.out.println(M - numUsedEdges);
	}
	
	public static void dijkstra2(int start) {
		int[] dist = new int[N];
		Arrays.fill(dist, Integer.MAX_VALUE / 2 - 1);
		dist[start] = 0;
		
		ArrayList<ArrayList<Integer>> paths = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < N; i++) {
			paths.add(new ArrayList<Integer>());
		}
		
		int[] visited = new int[N];
		
		PriorityQueue<Custom_Pair> queue = new PriorityQueue<Custom_Pair>();
		Custom_Pair p = new Custom_Pair(start, 0);
		p.path.add(start);
		queue.add(p);
		
		while (queue.size() > 0) {
			p = queue.poll();
			
			if (visited[p.p] == 1) {
				continue;
			}
			
			for (int i = 0; i < N; i++) {
				if (map[p.p][i] > 0 && dist[i] > dist[p.p] + map[p.p][i]) {
					try {
						dist[i] = dist[p.p] + map[p.p][i];
						Custom_Pair newPair = (Custom_Pair) p.clone();
						newPair.p = i;
						newPair.cost = dist[i];
						newPair.path.add(i);
						paths.set(i, newPair.path);
						queue.add(newPair);
					} catch (Exception e) {
						
					}
				}
			}
			
			visited[p.p] = 1;
		}
		
		for (int i = 0; i < N; i++) {
			if (i != start) {
				ArrayList<Integer> path = paths.get(i);
				for (int j = 0; j < path.size() - 1; j++) {
					int a = path.get(j);
					int b = path.get(j + 1);
					if (usedEdges[a][b] == 0 && usedEdges[b][a] == 0) {
						usedEdges[a][b] = 1;
						usedEdges[b][a] = 1;
						numUsedEdges++;
					}
				}
			}
		}
	}
}

class Custom_Pair implements Comparable<Custom_Pair> {
	int p;
	int cost;
	ArrayList<Integer> path;
	
	public Custom_Pair(int p, int cost) {
		super();
		this.p = p;
		this.cost = cost;
		this.path = new ArrayList<Integer>();
	}

	@Override
	public int compareTo(Custom_Pair o) {
		return this.cost - o.cost;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		Custom_Pair p = new Custom_Pair(this.p, this.cost);
		for (int i = 0; i < this.path.size(); i++) {
			p.path.add(this.path.get(i));
		}
		return p;
	}
}
