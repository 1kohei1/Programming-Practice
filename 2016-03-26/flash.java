import java.util.Arrays;
import java.util.Scanner;
import java.awt.Point;

public class flash {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		// I believe this is correct, I don't know what test case I missed.	
		int counter = 1;
		while (true) {
			int n = scanner.nextInt();
			if (n == 0) {
				break;
			}
			
			Point[] points = new Point[n];
			int[] x = new int[n];
			int[] y = new int[n];
			for (int i = 0; i < n; i++) {
				int xx = scanner.nextInt();
				int yy = scanner.nextInt();
				points[i] = new Point(xx, yy);
				x[i] = xx;
				y[i] = yy;
			}
			
			Arrays.sort(x);
			Arrays.sort(y);
			
			if (n % 2 == 1) {
				int answerX = x[n / 2];
				int answerY = y[n / 2];
				long dist = dist(points, answerX, answerY);
				System.out.printf("Case %d: (%d,%d) %d\n", counter, answerX, answerY, dist);
			} else {
				int answerX = x[n / 2 - 1];
				int answerY = y[n / 2 - 1];
				long dist = dist(points, answerX, answerY);
				System.out.printf("Case %d: (%d,%d) %d\n", counter, answerX, answerY, dist);
			}
			
			counter++;
		}
	}
/*
5 3 4 0 5 1 1 5 5 5 5
4 100 2 100 2 100 2 1 20000
0
 */
	public static long dist(Point[] points, int answerX, int answerY) {
		long dist = 0;
		for (int i = 0; i < points.length; i++) {
			dist += Math.abs(points[i].x - answerX) + Math.abs(points[i].y - answerY);
		}
		return dist;
	}
}
