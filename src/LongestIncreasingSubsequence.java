import java.util.Scanner;

public class LongestIncreasingSubsequence {

	public static void main(String[] args) {
		nSquareSolution();
	}
	
	// This solution takes n^2 complexity
	public static void nSquareSolution() {
		Scanner in = new Scanner(System.in);
		
		while(true) {
			int n = in.nextInt();
			
			if (n == 0) {
				break;
			}
			
			int[] nums = new int[n];
			for (int i = 0; i < n; i++) {
				nums[i] = in.nextInt();
			}
			
			int[] temp = new int[n];
			temp[0] = 1;
			
			int answer = 0;
			for (int i = 1; i < n; i++) {
				int max = 0;
				for (int j = 0; j < i; j++) {
					if (nums[j] < nums[i]) {
						max = Math.max(max, temp[j]);
					}
				}
				temp[i] = max + 1;
				answer = Math.max(answer, temp[i]);
			}
			
			System.out.println(answer);
		}
	}
/*
5
2
7
4
3
8
 */
}
