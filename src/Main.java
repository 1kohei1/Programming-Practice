import java.util.*;

// ABC 84-D
// https://beta.atcoder.jp/contests/abc084/tasks/abc084_d

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int max = 100001;
		boolean[] isPrime = new boolean[max];
		Arrays.fill(isPrime, true);
		isPrime[0] = false;
		isPrime[1] = false;
		isPrime[2] = true;
		for (int i = 2; i <= Math.sqrt(max); i++) {
			if (i != 2 && i % 2 == 0) {
				isPrime[i] = false;
			} else if (isPrime[i] && isPrime(i)) {
				for (int j = i * 2; j < max; j += i) {
					isPrime[j] = false;
				}
			}
		}
		
		int[] nums = new int[max];
		for (int i = 1; i < max; i++) {
			if (isPrime[i] && isPrime[(i + 1) / 2]) {
				nums[i] = nums[i - 1] + 1;
			} else {
				nums[i] = nums[i - 1];
			}
		}
		
		int Q = in.nextInt();
		
		for (int i = 0; i < Q; i++) {
			int l = in.nextInt();
			int r = in.nextInt();
			System.out.println(nums[r] - nums[l - 1]);
		}
	}
	
	public static boolean isPrime(int n) {
		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
}
