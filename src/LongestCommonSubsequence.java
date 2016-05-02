import java.util.Scanner;

public class LongestCommonSubsequence {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			char[] c1 = scanner.next().toCharArray();
			char[] c2 = scanner.next().toCharArray();
			
			int[][] map = new int[c1.length + 1][c2.length + 1];
			
			for (int i = 1; i < c1.length + 1; i++) {
				for (int j = 1; j < c2.length + 1; j++) {
					if (c1[i - 1] == c2[j - 1]) {
						map[i][j] = map[i - 1][j - 1] + 1;
					} else {
						map[i][j] = Math.max(map[i - 1][j], map[i][j - 1]);
					}
				}
			}
			
			// Print common sequence
			String commonS = "";
			int dx = c1.length;
			int dy = c2.length;
			
			while (dx > 0 && dy > 0) {
				if (c1[dx - 1] == c2[dy - 1]) {
					commonS = "" + c1[dx - 1] + commonS;
					dx--;
					dy--;
				} else {
					if (map[dx - 1][dy] > map[dx][dy - 1]) {
						dx--;
					} else if (map[dx - 1][dy] < map[dx][dy - 1]) {
						dy--;
					}
				}
			}
			System.out.printf("%d, %s\n", map[c1.length][c2.length], commonS);
		}
	}

}
