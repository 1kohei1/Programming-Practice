import java.util.*;

// Tenakaichi 2017-D
// http://tenka1-2017.contest.atcoder.jp/tasks/tenka1_2017_d	

public class Main {

	public static void main (String[] args) throws InterruptedException {
		Scanner in = new Scanner(System.in);

		int N = in.nextInt();
		int K = in.nextInt();

		int[] nums = new int[N];
		int[] values = new int[N];
		
		for (int i = 0; i < N; i++) {
			nums[i] = in.nextInt();
			values[i] = in.nextInt();
		}

		long answer = 0;
		
		for (int i = -1; i < 32; i++) {
			if (i == -1 || ((1 << i) & K) > 0) {
				long tempAnswer = 0;
				for (int j = 0; j < N; j++) {
					if (i == -1 || (nums[j] & (1 << i)) == 0) {
						boolean shouldAdd = true;
						for (int k = i + 1; k < 32; k++) {
							if ((K & (1 << k)) == 0 && (nums[j] & (1 << k)) > 0) {
								shouldAdd = false;
								break;
							}
						}
						
						if (shouldAdd) {
							tempAnswer += values[j];
						}
					}
				}
				answer = Math.max(answer, tempAnswer);
			}
		}
		System.out.println(answer);
	}
}
