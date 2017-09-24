import java.util.*;

// Yuki 141
// https://yukicoder.me/problems/no/131

public class Main {
	
	public static void main (String[] args) throws InterruptedException {
		Scanner in = new Scanner(System.in);
		
		int x = in.nextInt();
		int y = in.nextInt();
		int d = in.nextInt();
		
		int answer = 0;
		if (d <= y) {
			answer = Math.min(d,  x) + 1;
		} else if (d <= x) {
			answer = Math.min(d, y) + 1;
		} else {
			int crossX = d - y;
			if (0 <= crossX && crossX <= x) {
				int crossY = 0 - x + d;
				answer = y - crossY + 1;
			}
		}
		System.out.println(answer);
	}
}
