import java.util.*;

// ARC 90-D
// https://beta.atcoder.jp/contests/arc090/tasks/arc090_b

public class Main {

	static int N;
	static HashMap<Integer, HashMap<Integer, Integer>> edges;
	static int[] values;
	static boolean[] visited;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		N = in.nextInt();
		int M = in.nextInt();
		
		edges = new HashMap<Integer, HashMap<Integer, Integer>>();
		Input[] inputs = new Input[M];
		
		for (int i = 0; i < M; i++) {
			int l = in.nextInt();
			int r = in.nextInt();
			int c = in.nextInt();
			
			if (edges.containsKey(l)) {
				HashMap<Integer, Integer> e = edges.get(l);
				e.put(r, c);
			} else {
				HashMap<Integer, Integer> e = new HashMap<Integer, Integer>();
				e.put(r, c);
				edges.put(l, e);
			}
			
			if (edges.containsKey(r)) {
				HashMap<Integer, Integer> e = edges.get(r);
				e.put(l, -1 * c);
			} else {
				HashMap<Integer, Integer> e = new HashMap<Integer, Integer>();
				e.put(l, -1 * c);
				edges.put(r, e);
			}
			
			inputs[i] = new Input(l, r, c);
		}
		
		values = new int[N + 1];
		visited = new boolean[N + 1];
		
		for (int i = 1; i < N + 1; i++) {
			if (visited[i] == false) {
				dfs(i, 0);
			}
		}
		
		boolean answer = true;
		
		for (int i = 0; i < M && answer; i++) {
			if (values[inputs[i].l] + inputs[i].c != values[inputs[i].r]) {
				answer = false;
			}
		}
		
		System.out.println(answer ? "Yes" : "No");
	}
	
	public static void dfs(int n, int val) {
		visited[n] = true;
		values[n] = val;
		
		HashMap<Integer, Integer> e = edges.get(n);
		
		if (e == null) {
			return;
		}
		
		for (int next : e.keySet()) {
			if (visited[next] == false) {
				dfs(next, val + e.get(next));
			}
		}
	}
}

class Input {
	int l;
	int r;
	int c;
	
	public Input(int l, int r, int c) {
		this.l = l;
		this.r = r;
		this.c = c;
	}
}