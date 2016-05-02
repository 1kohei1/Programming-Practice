import java.util.*;

public class Box {

	static int numMove = 4;
	static int[] dx = new int[]{0, -1, 0, 1};
	static int[] dy = new int[]{-1, 0, 1, 0};
	
	static char[][] map;
	static int[][] dist;
	static int[][][] warp;
	static boolean[] warpUsed;
	static boolean[][] visited;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			int row = scanner.nextInt();
			int col = scanner.nextInt();
			scanner.nextLine();
			if (row == 0 && col == 0) break;
			
			map = new char[row][col];
			dist = new int[row][col];
			warp = new int[10][2][2];
			warpUsed = new boolean[10];
			visited = new boolean[row][col];
						
			int startX = -1, startY = -1, endX = -1, endY = -1;
			
			for (int i = 0; i < row; i++) {
				char[] line = scanner.nextLine().toCharArray();
				map[i] = line;
				for (int j = 0; j < col; j++) {
					// Get startX and startY
					if (line[j] == 'B') {
						startX = i;
						startY = j;
					// Get warp
					} else if (Character.isDigit(line[j])) {
						int digit = Character.getNumericValue(line[j]);
						int index = 0;
						if (warpUsed[digit]) {
							index = 1;
						} else {
							warpUsed[digit] = true;
						}
						warp[digit][index][0] = i;
						warp[digit][index][1] = j;
					// Get endX, endY
					} else if (line[j] == 'X') {
						endX = i;
						endY = j;
					}
				}
			}
			Arrays.fill(warpUsed, false);
			solve(startX, startY, row, col);
//			for (int i = 0; i < row; i++) {
//				for (int j = 0; j < col; j++) {
//					System.out.printf("%d ", dist[i][j]);
//				}
//				System.out.printf("\n");
//			}
			System.out.printf("He got the Box in %d steps!\n", dist[endX][endY]);
		}
	}

	public static void solve(int x, int y, int row, int col) {
		visited[x][y] = true;
		LinkedList<Integer> q = new LinkedList<Integer>();		
		q.add(x);
		q.add(y);
		int nextX, nextY;
		
		while(!q.isEmpty()) {
			x = q.poll();
			y = q.poll();
			// When I for and if, I got correct. But why!!!!!!????????
			// Does he have option to use teleportation???????
			// I thought once he steps over teleportation machine, the machine immediately transfers him to the other spot...
			for (int i = 0; i < numMove; i++) {
				nextX = x + dx[i];
				nextY = y + dy[i];
				if (0 <= nextX && nextX < row && 0 <= nextY && nextY < col && !visited[nextX][nextY] && map[nextX][nextY] != 'W') {
					visited[nextX][nextY] = true;
					dist[nextX][nextY] = dist[x][y] + 1;
					// If He reaches the box, quit the program
					if (map[nextX][nextY] == 'X') {
						return;
					}
					q.add(nextX);
					q.add(nextY);
				}
			}
			if (Character.isDigit(map[x][y])) {
				int digit = Character.getNumericValue(map[x][y]);
				if (!warpUsed[digit]) {
					warpUsed[digit] = true;
					int index = warp[digit][0][0] == x && warp[digit][0][1] == y ? 1 : 0;
					nextX = warp[digit][index][0];
					nextY = warp[digit][index][1];
					visited[nextX][nextY] = true;
					dist[nextX][nextY] = dist[x][y];
					q.add(nextX);
					q.add(nextY);
					continue;
				}
			}
		}
	}
	
}

/*
5 5
B....
....1
WWWWW
1....
....X
5 4
...B
WWW.
5XW.
WWW.
.5..
0 0
*/