import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int H = in.nextInt();
		int W = in.nextInt();
		
		int[][] map = new int[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				map[i][j] = in.nextInt();
			}
		}
	
		for (int k = 0; k < 10; k++) {
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
				}
			}
		}
		
		long answer = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				int n = in.nextInt();
				if (n != -1) {
					answer += map[n][1];
				}
			}
		}
		System.out.println(answer);
	}
}
