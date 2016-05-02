import java.util.Scanner;

public class diamond {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int nt = in.nextInt();
		for (int c = 1; c <= nt; c++) {
			int n = in.nextInt();
			
			boolean[][] map = new boolean[n][n];
			for (int i = 0; i < n; i++) {
				int m = in.nextInt();
				for (int j = 0; j < m; j++) {
					int a = in.nextInt() - 1;
					// a points to i
					map[a][i] = true;
				}
			}
			
			boolean answer = false;
			for (int i = 0; i < n && !answer; i++) {
				boolean[] visited = new boolean[n];
				visited[i] = true;
				for (int j = 0; j < n; j++) {
					if (map[i][j] && dfs(j, map, visited)) {
						answer = true;
						break;
					}
				}
			}
			System.out.printf("Case #%d: %s\n", c, answer ? "Yes" : "No");
		}
	}
	
	public static boolean dfs(int index, boolean[][] map, boolean[] visited) {
		if (visited[index]) {
			return true;
		}
		visited[index] = true;
		for (int i = 0; i < map.length; i++) {
			if (map[index][i] && dfs(i, map, visited)) {
				return true;
			}
		}
		return false;
	}
/*
3
3
1 2
1 3
0
5
2 2 3
1 4
1 5
1 5
0
3
2 2 3
1 3
0

1
5
2 4 2
0
1 5
1 3
1 5
 */
}
