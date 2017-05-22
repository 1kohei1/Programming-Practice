import java.util.*;

// ABC 17-C
// http://abc017.contest.atcoder.jp/tasks/abc017_3

public class Main {
	
	public static void main (String[] args) {
		Scanner in = new Scanner(System.in);
		
		int N = in.nextInt();
		int M = in.nextInt();
		
		long sum = 0;
		long[] isumo = new long[M + 2];
		
		for (int i = 0; i < N; i++) {
			int start = in.nextInt();
			int end = in.nextInt();
			int score = in.nextInt();
			
			isumo[start] += score;
			isumo[end + 1] -= score;
			sum += score;
		}
		
		for (int i = 1; i < M + 2; i++) {
			isumo[i] += isumo[i - 1];
		}
		
		long answer = 0;
		
		for (int i = 1; i <= M; i++) {
			answer = Math.max(answer, sum - isumo[i]);
		}
		
		System.out.println(answer);
	}
	
}