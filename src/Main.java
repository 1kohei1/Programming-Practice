import java.util.*;

// Yuki 208
// https://yukicoder.me/problems/no/208

public class Main {
	
	public static void main (String[] args) throws InterruptedException {
		Scanner in = new Scanner(System.in);
		
		int x = in.nextInt();
		int y = in.nextInt();
		int x1 = in.nextInt();
		int y1 = in.nextInt();
		
		long answer = Math.max(x, y);
		if (x1 < x && y1 < y && x == y && x1 == y1) {
			answer++;
		}
		System.out.println(answer);
	}
}
