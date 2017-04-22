import java.util.*;

// ABC 59-C
// http://abc059.contest.atcoder.jp/tasks/arc072_a
 
public class Main {
	
	public static void main (String[] args) throws java.lang.Exception {
		Scanner in = new Scanner(System.in);

		int n = in.nextInt();
		
		int[] nums = new int[n];
		for (int i = 0; i < n; i++) {
			nums[i] = in.nextInt();
		}
		
		long answer = solve(nums, 0, 0);
		if (nums[0] > 0) {
			answer = Math.min(solve(nums, nums[0], 1), nums[0] + 1 + solve(nums, -1, 1));
		} else if (nums[0] < 0) {
			answer = Math.min(solve(nums, nums[0], 1), Math.abs(nums[0]) + 1 + solve(nums, 1, 1));
		} else {
			answer = Math.min(1 + solve(nums, 1, 1), 1 + solve(nums, -1, 1));
		}
		
		System.out.println(answer);
	}
	
	public static long solve(int[] nums, long sum, int index) {
		if (index == nums.length) {
			return 0;
		}
		if (sum < 0 && sum + nums[index] < 0) {
			return 1 + Math.abs(sum + nums[index]) + solve(nums, 1, index + 1);
		} else if (sum > 0 && sum + nums[index] > 0) {
			return 1 + sum + nums[index] + solve(nums, -1, index + 1);
		} else if (sum + nums[index] == 0) {
			if (sum > 0) {
				return 1 + solve(nums, -1, index + 1);
			} else {
				return 1 + solve(nums, 1, index + 1);
			}
		} else {
			return solve(nums, sum + nums[index], index + 1);
		}
	}
}