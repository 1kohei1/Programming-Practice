import java.util.*;

// ABC 17-C
// http://abc017.contest.atcoder.jp/tasks/abc017_3

public class Main {
	
	static int N;
	static int M;
	static int[] jewelStart;
	static int[] jewelEnd;
	static int[] scores;
	
	public static void main (String[] args) {
		Scanner in = new Scanner(System.in);
		
		N = in.nextInt();
		M = in.nextInt();
		
		jewelStart = new int[N];
		jewelEnd = new int[N];
		scores = new int[N];
		
		for (int i = 0; i < N; i++) {
			jewelStart[i] = in.nextInt();
			jewelEnd[i] = in.nextInt();
			scores[i] = in.nextInt();
		}
		
		long answer = 0;
		
		for (int i = 1; i <= M; i++) {
			long num = 0;
			for (int j = 0; j < N; j++) {
				if (!(jewelStart[j] <= i && i <= jewelEnd[j])) {
					num += scores[j];
				}
			}
			answer = Math.max(answer, num);
		}
		
		System.out.println(answer);
	}
	
}