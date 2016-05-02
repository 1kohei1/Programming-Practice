import java.util.Scanner;

public class prob6_02_20 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int n = scanner.nextInt();
		int[] nums = new int[n];
		int sum = 0;
		for (int i = 0; i < n; i++) {
			nums[i] = scanner.nextInt();
			sum += nums[i];
		}
		int avg = sum / n;
		
		// Subtract avg from all elements of the array
		int[] nums2 = nums.clone();
		for (int i = 0; i < n; i++) {
			nums2[i] -= avg;
		}
		
		int maxStartIndex = 0;
		int maxEndIndex = -1;
		int maxSoFar = 0;
		int max = 0;
		
		for (int i = 1; i < n - 1; i++) {
			maxSoFar += nums2[i];
			if (maxSoFar < 0) {
				maxStartIndex = i;
				maxSoFar = 0;
			}
			if (max < maxSoFar) {
				max = maxSoFar;
				maxEndIndex = i;
			}
		}
		System.out.printf("maxEndIndex: %d, maxStartIndex: %d\n", maxEndIndex, maxStartIndex);
		max += avg * (maxEndIndex - maxStartIndex);
		System.out.printf("%.3f\n", (sum - max) / ((double) (n - maxEndIndex + maxStartIndex)));
	}
/*
5
5
1
7
8
2

7
0
5
4
3
2
1
0

3
5
5
5
 */
}
