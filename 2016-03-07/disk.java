import java.util.Scanner;
import java.util.Arrays;

public class disk {

	static long max;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int numTests = scanner.nextInt();
		for (int counter = 1; counter <= numTests; counter++) {
			int n = scanner.nextInt();
			int[] nums = new int[n];
			max = 0;
			
			for (int i = 0; i < n; i++) {
				nums[i] = scanner.nextInt();
			}
			boolean[] used = new boolean[n];
			Arrays.fill(used, false);
			
			solve(n, nums, used, 0, new int[n]);
			System.out.printf("Wave #%d: %d\n", counter, max);
		}
	}
/*
3
3 1 2 3
5 9 2 5 3 1
10 7 3 4 5 5 7 6 8 5 4
*/
	public static void solve(int n, int[] nums, boolean[] used, int k, int[] perm) {
		if (k == n) {
			long max2 = 0;
			for (int i = 1; i < n; i++) {
				max2 += Math.abs(perm[i] - perm[i - 1]);
			}
			max = Math.max(max, max2);
			return;
		}
		for (int i = 0; i < n; i++) {
			if (used[i] == false) {
				perm[k] = nums[i];
				used[i] = true;
				solve(n, nums, used, k + 1, perm);
				used[i] = false;
			}
		}
	}
}
