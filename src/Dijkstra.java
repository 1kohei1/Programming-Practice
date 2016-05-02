import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class Dijkstra {

	static int[][] map;
	static int[] dist;
	static int[] visited;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int numNodes = scanner.nextInt();
		int numEdges = scanner.nextInt();
		
		visited = new int[numNodes];
		map = new int[numNodes][numNodes];
		dist = new int[numNodes];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		for (int i = 0; i < numEdges; i++) {
			int s = scanner.nextInt() - 1;
			int e = scanner.nextInt() - 1;
			int d = scanner.nextInt();
			
			map[s][e] = d;
			map[e][s] = d; // Comment this out for Test #3,
		}
		int start = scanner.nextInt() - 1;
		int end = scanner.nextInt() - 1;
		
		dijkstra(start, end);
		System.out.println(dist[end]);
	}

	public static void dijkstra(int start, int end) {
		ArrayList<Integer> queue = new ArrayList<Integer>();
		queue.add(start);
		dist[start] = 0;
		
		while(queue.size() != 0) {
			int curr = queue.remove(0);
			visited[curr] = 1;
			
			for (int i = 0; i < map.length; i++) {
				if (map[curr][i] != 0 && visited[i] == 0) {
					if (dist[curr] + map[curr][i] < dist[i]) {
						dist[i] = dist[curr] + map[curr][i];
					}
				}
			}
			int nextNode = nextNode();
			if (nextNode == -1 || nextNode == end) {
				return;
			}
			queue.add(nextNode);
		}
	}
	
	public static int nextNode() {
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
Test Case #1: https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
6 9
1 2 7
1 3 9
1 6 14
2 3 10
2 4 15
3 4 11
3 6 2
4 5 6
5 6 9
1 5
answer: 20

Test Case #2: https://www.youtube.com/watch?v=gdmfOwyQlcI(1:24)
7 12
1 2 4
1 3 3
1 5 7
2 3 6
2 4 5
3 4 11
3 5 8
4 5 2
4 6 10
4 7 2
5 6 5
6 7 3
1 7
answer: 11

Test Case #3: cited from http://algs4.cs.princeton.edu/44sp/tinyEWD.txt
input is edited to adjust with my program
8 15
5 6 35
6 5 35
5 8 37
6 8 28
8 6 28
6 2 32
1 5 38
1 3 26
8 4 39
2 4 29
3 8 34
7 3 40
4 7 52
7 1 58
7 5 93
1 7
answer: 151

Other start and end answer from http://algs4.cs.princeton.edu/44sp/DijkstraSP.java.html
1 2
answer: 105
1 3
answer: 26
1 4
answer: 99
1 5
answer: 38
1 6
answer: 73
1 8
answer: 60
 */
