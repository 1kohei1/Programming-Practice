import java.util.*;

// Foobar Level 3: prepare_the_bunnies_escape

public class Main {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int n = in.nextInt();
		int m = in.nextInt();
		int[][] map = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] = in.nextInt();
			}
		}
		System.out.println(answer(map));
	}
	
	public static int answer(int[][] maze) {
		int h = maze.length;
		int w = maze[0].length;
		
		int answer = path(maze);
		
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (maze[i][j] == 1) {
					maze[i][j] = 0;
					answer = Math.min(answer, path(maze));
					maze[i][j] = 1;
				}
			}
		}
		
		return answer;
	}
	
	public static int path(int[][] maze) {
		int h = maze.length;
		int w = maze[0].length;
		
		int[] di = new int[]{-1, 0, 1, 0};
		int[] dj = new int[]{0, 1, 0, -1};
		int[][] visited = new int[h][w];
		
		ArrayList<State> q = new ArrayList<State>();
		q.add(new State(0, 0, 1, false));
		
		while(q.size() > 0) {
			State s = q.remove(0);
			int i = s.i;
			int j = s.j;
			if (i == h - 1 && j == w - 1) {
				return s.answer;
			}
			if (visited[i][j] == 1) {
				continue;
			}
			visited[i][j] = 1;
			
			for (int z = 0; z < 4; z++) {
				int newI = i + di[z];
				int newJ = j + dj[z];
				
				if (0 <= newI && newI < h && 0 <= newJ && newJ < w && visited[newI][newJ] == 0 && maze[newI][newJ] == 0) {
					q.add(new State(newI, newJ, s.answer + 1, s.isBlockBroken));
				}
			}
		}
		return Integer.MAX_VALUE;
	}
	
	public static void printMaze(int[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.printf("%d ", map[i][j]);
			}
			System.out.println();
		}
	}
}

class State {
	int i;
	int j;
	int answer;
	boolean isBlockBroken;
	
	public State(int i, int j, int answer, boolean isBlockBroken) {
		this.i = i;
		this.j = j;
		this.answer = answer;
		this.isBlockBroken = isBlockBroken;
	}
}