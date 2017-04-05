import java.util.*;
 
// ABC 31-C
// http://abc031.contest.atcoder.jp/tasks/abc031_c
 
public class Main {
	
	public static void main (String[] args) throws java.lang.Exception {
		Scanner in = new Scanner(System.in);
		
		int n = in.nextInt();
		int[] nums = new int[n];
		
		for (int i = 0; i < n; i++) {
			nums[i] = in.nextInt();
		}
		
		int answer = -100000;
		
		for (int i = 0; i < n; i++) {
			answer = Math.max(answer, takahashiScore(nums, i));
		}
		
		System.out.println(answer);
		
		// Iterate through 50 positions that Takahashi  can mark
		// Figure out which element Aoki will mark
		// Save the answer
	}

	public static int takahashiScore(int[] nums, int n) {
		int maxSoFar = -1000000;
		int takahashiscore = -100;
		
		for (int i = 0; i < nums.length; i++) {
			if (i == n) {
				continue;
			}
			int low = Math.min(n,  i);
			int max = Math.max(n, i);

			int takashi = 0;
			int aoki = 0;
			
			// Calculate takashi score and aoki score when they choose the range [i..n] or [n..i]
			for (int j = low; j <= max; j++) {
				// Takashi score
				if ((j - low + 1) % 2 == 1) {
					takashi += nums[j];
				} 
				// Aoki score
				else {
					aoki += nums[j];
				}
			}
			
			if (maxSoFar < aoki) {
				maxSoFar = aoki;
				takahashiscore = takashi;
			}
		}
		return takahashiscore;
	}
}
