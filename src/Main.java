import java.util.*;

// ABC 18-C
// http://abc018.contest.atcoder.jp/tasks/abc018_3
 
public class Main {

	static int R;
	static int C;
	static int K;
	static char map[][];
	
	public static void main (String[] args) throws java.lang.Exception {
		Scanner in = new Scanner(System.in);

		R = in.nextInt();
		C = in.nextInt();
		K = in.nextInt();
		
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = in.next().toCharArray();
		}
		
		int[][] whiteInRowAbove = new int[R][C];
		int[][] whiteInRowBottom = new int[R][C];
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 'x') {
					whiteInRowAbove[i][j] = 0;
					whiteInRowBottom[i][j] = 0;
				} else {
					int count = 1;
					int row = i - 1;
					while (0 <= row && map[row][j] == 'o' && count <= K) {
						count++;
						row--;
					}
					whiteInRowAbove[i][j] = count;
					
					count = 1;
					row = i + 1;
					while (row < R && map[row][j] == 'o' && count <= K) {
						count++;
						row++;
					}
					
					whiteInRowBottom[i][j] = count;
				}
			}
		}

		long answer = 0;
		int d = K - 1;

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (i - d < 0 || i + d >= R || j - d < 0 || j + d >= C) {
					continue;
				}
				
				boolean shouldContinue = true;
				
				for (int k = j - d; k <= j + d && shouldContinue; k++) {
					int diff = K - Math.abs(j - k);
					if (whiteInRowAbove[i][k] < diff || whiteInRowBottom[i][k] < diff) {
						shouldContinue = false;
					}
				}

				if (shouldContinue) {
					answer++;
				}
			}
		}

		System.out.println(answer);
	}
}