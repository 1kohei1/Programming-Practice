import java.awt.Point;
import java.util.Scanner;

public class robot {

	public static Point[] points;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			int n = scanner.nextInt();
			if (n == 0) {
				break;
			}
			
			// Increment by two to include the start and end
			points = new Point[n + 2];
			int[] penalties = new int[n + 2];
			
			// Set start
			points[0] = new Point(0, 0);
			penalties[0] = 0;
			
			for (int i = 0; i < n; i++) {
				points[i + 1] = new Point(scanner.nextInt(), scanner.nextInt());
				penalties[i + 1] = scanner.nextInt();
			}
			
			// Set goal
			points[n + 1] = new Point(100, 100);
			penalties[n + 1] = 0;
			
			// Store the minimum score to reach indexed point
			Double[]  memo = new Double[n + 2];
			memo[0] = 0.0;
			
			// Start from index 0
			for (int i = 1; i < n + 2; i++) {
				// Directly come to this point
				memo[i] = memo[i - 1] + dist(i - 1, i) + 1;
				// Come to this point with skipping previous points
				double penalty = penalties[i - 1];
				for (int j = i - 2; j >= 0; j--) {
					memo[i] = Math.min(memo[i], memo[j] + dist(j, i) + 1 + penalty);
					penalty += penalties[j];
				}
			}
			
			System.out.printf("%.03f\n", memo[n + 1]);
		}
	}
/*
1
50 50 20
3
30 30 90
60 60 80
10 90 100
3
30 30 90
60 60 80
10 90 10
0
 */
	
	public static double dist(int index1, int index2) {
		int x = points[index1].x;
		int y = points[index1].y;
		int xx = points[index2].x;
		int yy = points[index2].y;
		
		double dist = Math.pow(Math.abs(x - xx), 2) + Math.pow(Math.abs(y - yy), 2);
		return Math.sqrt(dist);
	}
}
