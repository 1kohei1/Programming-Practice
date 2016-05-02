import java.util.Scanner;

public class prob4 {

	
	static int r, c;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int numTests = scanner.nextInt();
		for (int counter = 1; counter <= numTests; counter++) {
			r = scanner.nextInt();
			c = scanner.nextInt();
			
			int[][] map = new int[r][c];
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					map[i][j] = scanner.nextInt();
				}
			}
			
			System.out.printf("Canal %d: %.3f\n", counter, solve(map, 0, 0, map[0][0]));
		}
	}
	
	// i: row, j: col
	public static double solve(int[][] map, int i, int j, double ijHeight) {
		if (i == r - 1 && j == c - 1) {
			return 0;
		}
		double right = Integer.MAX_VALUE;
		double down =  Integer.MAX_VALUE;
		
		// go down
		if (i != r - 1) {
			int ii = i + 1;
			int jj = j;
						
			double diff = Math.abs((map[ii][jj] + ijHeight) / 2 - ijHeight);
			double ijHeight2 = ijHeight > map[ii][jj] ? map[ii][jj] : ijHeight;

			down = diff + solve(map, ii, jj, ijHeight2 + diff);
		}
		// go right
		if (j != c - 1) {
			int ii = i;
			int jj = j + 1;
			
			double diff = Math.abs((map[ii][jj] + ijHeight) / 2 - ijHeight);
			double ijHeight2 = ijHeight > map[ii][jj] ? map[ii][jj] : ijHeight;
			
			right = diff + solve(map, ii, jj, ijHeight2 + diff);
		}
		
		return Math.min(right, down);
	}
/**
1
2 3 
3 9 5
1 4 5

 */
}
