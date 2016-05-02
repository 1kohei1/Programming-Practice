import java.util.Scanner;

public class CommonSubstring {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		while (true) {
			String s1 = scanner.next();
			String s2 = scanner.next();
			
			char[] c1 = s1.toCharArray();
			char[] c2 = s2.toCharArray();
			
			int[][] map = new int[s1.length() + 1][s2.length() + 1];
			
			for (int i = 1; i < s1.length() + 1; i++) {
				for (int j = 1; j < s2.length() + 1; j++) {
					if (c1[i - 1] == c2[j - 1]) {
						map[i][j] = map[i - 1][j - 1] + 1;
					} else {
						map[i][j] = Math.max(map[i - 1][j], map[i][j - 1]);
					}
				}
			}
			System.out.println(map[c1.length][c2.length]);
		}		
	}
	// Some reference: http://www.geeksforgeeks.org/longest-common-substring/
}
