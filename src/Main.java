import java.util.*;

// ABC 84-C
// https://beta.atcoder.jp/contests/abc084/tasks/abc084_c

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int N = in.nextInt();
		int[] cost = new int[N];
		int[] start = new int[N];
		int[] freq = new int[N];
		
		for (int i = 0; i < N - 1; i++) {
			cost[i] = in.nextInt();
			start[i] = in.nextInt();
			freq[i] = in.nextInt();
		}
		
		long[] answer = new long[N];
		for (int i = 0; i < N - 1; i++) {
			long sec = start[i] + cost[i];
			for (int j = i + 1; j < N - 1; j++) {
				if (sec <= start[j]) {
					sec = start[j] + cost[j];
				} else {
					int n = 1;
					while (start[j] + n * freq[j] < sec) {
						n++;
					}
					sec = start[j] + n * freq[j];
					sec += cost[j];
				}
			}
			answer[i] = sec;
		}
		
		for (int i = 0; i < N; i++) {
			System.out.println(answer[i]);
		}
	}
}
