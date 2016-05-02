import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;

public class GraphAlgorithm {

	public static int DEPTH = 0;
	public static int BREADTH = 0;
	public static int SHORTEST = 0;
	public static int DIJKSTRA = 1;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		// Traverse
		if (DEPTH == 1) {
			runDepthFirst(scanner);
		}
		if (BREADTH == 1) {
			runBreadthFirst(scanner);	
		}
		
		// Shortest path
		if (SHORTEST == 1) {
			runShortest(scanner);
		}
		if (DIJKSTRA == 1) {
			runDijkstra(scanner);
		}
		
	}
	
	// Depth first algorithm wrapper
	public static void runDepthFirst(Scanner scanner) {
		int size = scanner.nextInt();		
		int numEdges = scanner.nextInt();
		int[][] maps = new int[size][size];
		// Initialize
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				maps[i][j] = 0;
			}
		}
		for(int counter = 0; counter < numEdges; counter++) {
			int i = scanner.nextInt();
			int j = scanner.nextInt();
			maps[i][j] = 1;
			maps[j][i] = 1;
		}
		
		// Traverse
		for (int i = 0; i < size; i++) {
			ArrayList<Integer> visited = new ArrayList<Integer>();
			depthFirst(maps, visited, i, size);				
		}		
	}
	
	// Traversal
	public static void depthFirst(int[][] maps, ArrayList<Integer> visited, int start, int size) {
		boolean isEnd = true;
		for (int j = 0; j < size; j++) {
			if (maps[start][j] == 1 && !visited.contains(j)) {
				ArrayList<Integer> clone = (ArrayList<Integer>) visited.clone();
				clone.add(j);
				depthFirst(maps, clone, j, size);
				isEnd = false;
			}
		}
		if (isEnd) {
			printDepthPath(visited);
		}
	}
	
	public static void printDepthPath(ArrayList<Integer> visited) {
		for (int i = 0; i < visited.size(); i++) {
			System.out.printf("%d ", visited.get(i));
		}
		System.out.printf("\n");
	}

	public static void runBreadthFirst(Scanner scanner) {
		int size = scanner.nextInt();		
		int numEdges = scanner.nextInt();
		// Map to store the distance and connectivity
		int[][] maps = new int[size][size];
		
		// Get data
		for (int i = 0; i < numEdges; i++) {
			maps[scanner.nextInt()][scanner.nextInt()] = scanner.nextInt();
		}
		
		LinkedList<Integer> q = new LinkedList<Integer>();
		int[] visited = new int[size];
		visited[0] = 1;
		q.offer(0);
		
		// Let's think later how to get the shortest path
		bfs(maps, visited, q);
	}
	
	public static void bfs(int[][] maps, int[] visited, LinkedList<Integer> q) {
		while(q.size() > 0) {
			int now = q.remove();
			for (int i = 0; i < maps.length; i++) {
				if (maps[now][i] > 0 && visited[i] == 0) {
					visited[i] = 1;
					System.out.printf("%d -> %d: %d\n", now, i, maps[now][i]);
					q.offer(i);
				}
			}
		}
	}
	
	public static void runShortest(Scanner scanner) {
		int numVertex = scanner.nextInt();
		int numPath = scanner.nextInt();
		
		int[][] map = new int[numVertex][numVertex];
		int[][] cost = new int[numVertex][numVertex];
		int[][] next = new int[numVertex][numVertex];
		for (int i = 0; i < numVertex; i++) {
			for (int j = 0; j < numVertex; j++) {
				cost[i][j] = i == j ? 0 : 1000000;
				next[i][j] = -1;
			}
		}
		
		for (int i = 0; i < numPath; i++) {
			int start = scanner.nextInt();
			int end = scanner.nextInt();
			int dist = scanner.nextInt();
			
			map[start][end] = dist;
			map[end][start] = dist;
			cost[start][end] = dist;
			cost[end][start] = dist;
		}
		
		for (int mid = 0; mid < numVertex; mid++) {
			for (int i = 0; i < numVertex; i++) {
				for (int j = 0; j < numVertex; j++) {
					if ((map[i][mid] > 0 && map[mid][j] > 0) && cost[i][mid] + cost[mid][j] < cost[i][j]) {
						cost[i][j] = cost[i][mid] + cost[mid][j];
						next[i][j] = mid; // Loop didn't stop
					}
				}
			}
		}
		System.out.printf("%d\n", cost[0][4]);
		int point = 0;
		while (point != -1) {
			System.out.printf("%d -> ", point);
			point = next[4][point];
		}
		System.out.printf("4");
	}
	
	public static void print2DArray(int[][] array, int max) {
		for (int i = 0; i < max; i++) {
			for (int j = 0; j < max; j++) {
				System.out.printf("%d ", array[i][j]);
			}
			System.out.printf("\n");
		}
	}
	
	public static void runDijkstra(Scanner scanner) {
		int numNodes = scanner.nextInt();
		int numEdges = scanner.nextInt();
		
		ArrayList<ArrayList<Integer>> map = new ArrayList<ArrayList<Integer>>();
		int[] dist = new int[numNodes];
		int[] visited = new int[numNodes];
		
		for (int i = 0; i < numNodes; i++) {
			map.add(new ArrayList<Integer>());
			dist[i] = Integer.MAX_VALUE;
		}
		
		for (int i = 0; i < numEdges; i++) {
			int x = scanner.nextInt() - 1;
			int y = scanner.nextInt() - 1;
			int d = scanner.nextInt();
			
			map.get(x).add(y);
			map.get(x).add(d);
			
			// Comment these two lines of code out for test case #3.
			map.get(y).add(x);
			map.get(y).add(d);
		}
		int start = scanner.nextInt() - 1;
		int end = scanner.nextInt() - 1;
		
		dist = dijkstra(map, dist, visited, start, end);
		System.out.println(dist[end]);
	}
	
	public static int[] dijkstra(ArrayList<ArrayList<Integer>> map, int[] dist, int[] visited, int start, int end) {
		ArrayList<Integer> queue = new ArrayList<Integer>();
		queue.add(start);
		dist[start] = 0;
		visited[start] = 1;
		
		while(queue.size() != 0) {
			int curr = queue.remove(0);
			
			for (int i = 0; i < map.get(curr).size(); i += 2) {
				int adjacent = map.get(curr).get(i);
				int d = map.get(curr).get(i + 1);
				
				if (visited[adjacent] == 0 && dist[curr] + d < dist[adjacent]) {
					dist[adjacent] = dist[curr] + d;
				}
			}
			int nextNode = nextNode(visited, dist);
			if (nextNode == -1 || nextNode == end) {
				return dist;
			}
			visited[curr] = 1;
			queue.add(nextNode);
		}
		return dist;
	}
	
	public static int nextNode(int[] visited, int[] dist) {
		int nextNode = -1;
		int smallestDistance = Integer.MAX_VALUE;
		
		for (int i = 0; i < dist.length; i++) {
			if (visited[i] == 0 && dist[i] < smallestDistance) {
				nextNode = i;
				smallestDistance = dist[i];
			}
		}
		return nextNode;
	}
}

/*
DFS Sample Input
8
9
0 1
0 3
0 7
1 4
1 5
1 6
2 6
3 4
4 5

BFS Sample Input
7
16
0 3 5
0 2 10
0 5 2
1 5 3
1 6 1
2 0 10
2 3 3
2 4 1
3 0 5
3 2 3
4 2 1
4 6 6
5 0 2
5 1 3
6 1 1
6 4 6

Shortest Path (from 0 -> 4) Input
5 6
0 1 3
0 2 1
0 3 7
1 3 5
3 4 2 
2 4 9
*/