import java.util.Scanner;

public class LongestCommonSubstring {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			char[] c1 = scanner.next().toCharArray();
			char[] c2 = scanner.next().toCharArray();
			
			int[][] map = new int[c1.length + 1][c2.length + 1];
			
			int answer = 0;
			int dx = 0;
			int dy = 0;
			
			for (int i = 1; i < c1.length + 1; i++) {
				for (int j = 1; j < c2.length + 1; j++) {
					if (c1[i - 1] == c2[j - 1]) {
						map[i][j] = map[i - 1][j - 1] + 1;
						if (answer < map[i][j]) {
							answer = map[i][j];
							dx = i;
							dy = j;
						}
					} else {
						map[i][j] = 0;
					}
				}
			}
			
			String commonS = "";
			while (true) {
				if (map[dx][dy] == 0) {
					break;
				}
				commonS = "" + c1[dx - 1] + commonS;
				dx--;
				dy--;
			}
			
			
			System.out.printf("%d: %s\n", answer, commonS);
		}
	}

}
