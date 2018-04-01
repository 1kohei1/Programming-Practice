import java.util.*;

// ABC 57-D
// https://beta.atcoder.jp/contests/abc057/tasks/abc057_d

public class Main {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int N = in.nextInt();
		int A = in.nextInt();
		int B = in.nextInt();
		
		long[] nums = new long[N];
		for (int i = 0; i < N; i++) {
			nums[i] = in.nextLong();
		}
		Arrays.sort(nums);
		
		long sum = 0;
		boolean isAllSame = true;
		
		for (int i = N - 1; i >= N - A; i--) {
			sum += nums[i];
			if (i != N - 1 && nums[i] != nums[i + 1]) {
				isAllSame = false;
			}
		}
		
		double avg = ((double) sum) / A;
		
		long lowest = nums[N - A];
		int numLowestInNums = 0;
		int numLowestInAvg = 0;
		
		for (int i = 0; i < N; i++) {
			if (nums[i] == lowest) {
				numLowestInNums++;
				if (i >= N - A) {
					numLowestInAvg++;
				}
			}
		}
		
		long count = comb(numLowestInNums, numLowestInAvg);
		
		if (isAllSame) {
			for (int i = N - A - 1; i >= N - B; i--) {
				if (nums[i] == lowest) {
					numLowestInAvg += 1;
					count += comb(numLowestInNums, numLowestInAvg);
				} else {
					break;
				}
			}
		}
		
		System.out.printf("%.6f\n", avg);
		System.out.println(count);
	}
	
	public static long comb(int n, int r) {
		long v = 1;
		
		for (int i = 0; i < r; i++) {
			v *= n - i;
			v /= (i + 1);
		}
		
		return v;
	}
}