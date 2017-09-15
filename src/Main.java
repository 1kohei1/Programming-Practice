import java.util.*;

// ARC 80-C
// http://arc080.contest.atcoder.jp/tasks/arc080_a

public class Main {
	
	public static void main (String[] args) throws InterruptedException {
		Scanner in = new Scanner(System.in);
		
		int N = in.nextInt();
		int num4 = 0;
		int num2 = 0;
		
		for (int i = 0; i < N; i++) {
			int n = in.nextInt();
			if (n % 4 == 0) {
				num4++;
			} else if (n % 2 == 0) {
				num2++;
			}
		}
		
		String answer = "No";
		
		if (num4 >= N / 2) {
			answer = "Yes";
		} else {
			int remain = N - num4 * 2;
			if (remain <= num2) {
				answer = "Yes";
			}
		}
		
		System.out.println(answer);
	}
}
