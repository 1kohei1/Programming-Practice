import java.util.*;
import java.awt.Point;

// ABC 75-D
// https://beta.atcoder.jp/contests/abc075/tasks/abc075_d	

public class Main {
	
	public static void main (String[] args) throws InterruptedException {
		Scanner in = new Scanner(System.in);

		int N = in.nextInt();
		int K = in.nextInt();
		
		Point[] points = new Point[N];
		for (int i = 0; i < N; i++) {
			points[i] = new Point(in.nextInt(), in.nextInt());
		}
		
		long answer = Long.MAX_VALUE;
		long smallerX, smallerY, biggerX, biggerY;
		int count;
		
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				if (K == 2) {
					smallerX = Math.min(points[i].x, points[j].x);
					smallerY = Math.min(points[i].y, points[j].y);
					biggerX = Math.max(points[i].x, points[j].x);
					biggerY = Math.max(points[i].y, points[j].y);
					
					answer = Math.min(answer, (biggerX - smallerX) * (biggerY - smallerY));
					continue;
				}
				for (int a = j + 1; a < N; a++) {
					if (K == 3) {
						smallerX = (long) Math.min(Math.min(points[i].x, points[j].x), points[a].x);
						smallerY = (long) Math.min(Math.min(points[i].y, points[j].y), points[a].y);
						biggerX = (long) Math.max(Math.max(points[i].x, points[j].x), points[a].x);
						biggerY = (long) Math.max(Math.max(points[i].y, points[j].y), points[a].y);
						
						answer = Math.min(answer, (biggerX - smallerX) * (biggerY - smallerY));
						continue;
					}
					for (int b = a + 1; b < N; b++) {
						smallerX = (long) Math.min(Math.min(points[i].x, points[j].x), Math.min(points[a].x, points[b].x));
						smallerY = (long) Math.min(Math.min(points[i].y, points[j].y), Math.min(points[a].y, points[b].y));
						biggerX = (long) Math.max(Math.max(points[i].x, points[j].x), Math.max(points[a].x, points[b].x));
						biggerY = (long) Math.max(Math.max(points[i].y, points[j].y), Math.max(points[a].y, points[b].y));

						count = 0;
						for (int x = 0; x < N; x++) {
							if (smallerX <= points[x].x && points[x].x <= biggerX && smallerY <= points[x].y && points[x].y <= biggerY) {
								count++;
							}
						}
						
						if (K <= count) {
							answer = Math.min(answer, (biggerX - smallerX) * (biggerY - smallerY));
						}
 					}
				}
			}
		}
		
		System.out.println(answer);
	}
}
