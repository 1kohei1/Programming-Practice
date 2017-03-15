import java.util.*;

// UVa 11504

public class Main {

	static int answer;
	static Node[] map;
	static int[] visited;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int numTests = in.nextInt();
		
		while (numTests-- > 0) {
			int n = in.nextInt();
			int m = in.nextInt();
			
			// Initialize variables
			map = new Node[n];
			answer = 0;
			visited = new int[n];
			for (int i = 0; i < n; i++) {
				map[i] = new Node(i);
			}
			
			while (m-- > 0) {
				int a = in.nextInt() - 1;
				int b = in.nextInt() - 1;
				map[a].edge.add(b);
				map[b].numIncoming++;
			}
			
			topoSort();
			
			System.out.println(answer);
		}
	}

	public static void topoSort() {
		ArrayList<Node> list = new ArrayList<Node>();
		for (int i = 0; i < map.length; i++) {
			list.add(map[i]);
		}
		list.sort(null);
		
		for (int i = 0; i < list.size(); i++) {
			Node n = list.get(i);
			
			if (visited[n.v] == 0) {
				answer++;
				visit(n);
			}
		}
	}
	
	public static void visit(Node n) {
		visited[n.v] = 1;

		for (int i = 0; i < n.edge.size(); i++) {
			if (visited[n.edge.get(i)] == 0) {
				visit(map[n.edge.get(i)]);
			}
		}
	}
}

class Node implements Comparable<Node> {
	int v;
	int numIncoming;
	ArrayList<Integer> edge;
	
	public Node(int v) {
		this.v = v;
		this.numIncoming = 0;
		edge = new ArrayList<Integer>();
	}
	
	@Override
	public int compareTo(Node o) {
		return this.numIncoming - o.numIncoming;
	}
	
	
}