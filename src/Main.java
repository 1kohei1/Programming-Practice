import java.util.*;

// ABC 62-D
// http://abc062.contest.atcoder.jp/tasks/arc074_b

public class Main {
	
	static int N;
	static int A;
	static int B;
	static int[] health;
	
	public static void main (String[] args) {
		Scanner in = new Scanner(System.in);
		
		N = in.nextInt();
		A = in.nextInt();
		B = in.nextInt();

		health = new int[N];
		long sum = 0;
		for (int i = 0; i < N; i++) {
			health[i] = in.nextInt();
			sum += health[i];
		}
		
		System.out.println(solve(0, sum));
	}
	
	public static long solve(long min, long max) {
		long mid = (min + max) / 2;
		
		long answer = Long.MAX_VALUE;
		
		while (min <= max) {
			mid = (min + max) / 2;
		
			long count = 0;
			
			for (int i = 0; i < N; i++) {
				if (health[i] > mid * B) {
					if ((health[i] - mid * B) % (A - B) == 0) {
						count += (health[i] - mid * B) / (A - B);
					} else {
						count += (health[i] - mid * B) / (A - B) + 1;
					}
				}
			}
			
			if (count <= mid) {
				answer = Math.min(answer, mid);
				max = mid - 1;
			} else {
				min = mid + 1;
			}
		}
		
		return answer;
	}
}