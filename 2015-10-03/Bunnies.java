import java.util.*;

public class Bunnies {

	public static boolean found;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int numCase = scanner.nextInt();
		while(numCase-- > 0) {
			
			int r = scanner.nextInt();
			int c = scanner.nextInt();
			
			int px = -1;
			int py = -1;
			int cx = -1;
			int cy = -1;
			
			found = false;
			
			// Creating String array is not recommended? Because it takes more memory?
			String[][] map = new String[r][c];
			boolean[][] visited = new boolean[r][c];
			
			for (int i = 0; i < r; i++) {
				String s = scanner.next();
				for (int j = 0; j < c; j++) {
					String ss = s.substring(j, j + 1);
					if (ss.equals("P")) {
						px = i;
						py = j;
						visited[i][j] = true;
					} else if (ss.equals("C")) {
						cx = i;
						cy = j;
					}
					map[i][j] = ss;
				}
			}
			
			floodFill(map, visited, px, py, cx, cy);
			System.out.printf("%s\n", found ? "yes" : "no");
		}
	}
	
	// Recursive. Floodfill
	public static void floodFill(String[][] map, boolean[][] visited, int cx, int cy, int dx, int dy) {
		if (cx == dx && cy == dy) {
			found = true;
		}
		
		visited[cx][cy] = true;
		
		// Left
		if (cy - 1 >= 0 && !map[cx][cy - 1].equals("#") && !visited[cx][cy - 1]) {
			floodFill(map, visited, cx, cy - 1, dx, dy);
		}
		// Top
		if (cx - 1 >= 0 && !map[cx - 1][cy].equals("#") && !visited[cx - 1][cy]) {
			floodFill(map, visited, cx - 1, cy, dx, dy);
		}
		// Right
		if (cx + 1 < map.length && !map[cx + 1][cy].equals("#") && !visited[cx + 1][cy]) {
			floodFill(map, visited, cx + 1, cy, dx, dy);
		}
		// Bottom
		if (cy + 1 < map[0].length && !map[cx][cy + 1].equals("#") && !visited[cx][cy + 1]) {
			floodFill(map, visited, cx, cy + 1, dx, dy);
		}
	}
/*
4
2 2
P#
#C
2 2
P_
C_
8 7
__P____
####_##
_____#_
_____#C
##_###_
_____#_
___#_#_
___#___
5 7
__P____
####_##
_____#_
_____#C
##_###_ 
*/
}