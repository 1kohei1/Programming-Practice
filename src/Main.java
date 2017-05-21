import java.util.*;

// ABC 17-D
// http://abc017.contest.atcoder.jp/tasks/abc017_3
 
public class Main {
	
	static int N;
	static int M;
	static int[] scores;
	static int[] jewelsLeft;
	static int[] jewelsRight;
	
	static int answer = Integer.MIN_VALUE;

	public static void main (String[] args) throws java.lang.Exception {
		Scanner in = new Scanner(System.in);
		
		N = in.nextInt();
		M = in.nextInt();
		
		jewelsLeft = new int[N];
		jewelsRight = new int[N];
		scores = new int[N];
		
		for (int i = 0; i < N; i++) {
			jewelsLeft[i] = in.nextInt();
			jewelsRight[i] = in.nextInt();
			scores[i] = in.nextInt();
		}
		
		solve(0, new int[M + 1], 0);
		System.out.println(answer);
	}

	public static void solve(int index, int[] jewels, int score) {
		if (index == N) {
			// Check if all of them are more than or equal to 0
			boolean isDaemonRevival = true;
			for (int i = 1; i <= M; i++) {
				if (jewels[i] < 1) {
					isDaemonRevival = false;
					break;
				}
			}
			
			if (isDaemonRevival) {
				score = 0;
			}
			answer = Math.max(answer, score);
		} else {
			// Don't go this heritage
			solve(index + 1, jewels, score);
			// Go this heritage
			for (int i = jewelsLeft[index]; i <= jewelsRight[index]; i++) {
				jewels[i]++;
			}
			solve(index + 1, jewels, score + scores[index]);
			// Reset jewels
			for (int i = jewelsLeft[index]; i <= jewelsRight[index]; i++) {
				jewels[i]--;
			}
}
	}
}