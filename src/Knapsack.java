import java.util.Scanner;

// http://www.cs.ucf.edu/~dmarino/progcontests/modules/dp2/ProgTeamLecDP-2.pdf
public class Knapsack {

	static boolean DEBUG = true;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int W = scanner.nextInt();
		int n = scanner.nextInt();
		
		int[] weights = new int[n];
		int[] values = new int[n];
		int[] answer = new int[W + 1];
		
		for (int i = 0; i < n; i++) {
			weights[i] = scanner.nextInt();
			values[i] = scanner.nextInt();
		}
		
		for (int i = 0; i <= W; i++) {
			answer[i] = 0;
		}
		
		// We can only pick one block
		// By going backward, we only add one block to the combination at index. 
		// Since it doesn't come back for the next round
//		for (int k = 0; k < n; k++) {
//			for (int w = W; w >= weights[k]; w--) {
//				if (answer[w] < values[k] + answer[w - weights[k]]) {
//					answer[w] = values[k] + answer[w - weights[k]];
//				}
//			}
//			if (DEBUG) printArray(answer);
//		}
		
		// Reset answer
		for (int i = 0; i <= W; i++) {
			answer[i] = 0;
		}
		
		// We can pick multiple block
		for (int k = 0; k < n; k++) {
			for (int w = weights[k]; w <= W; w++) {
				if (answer[w] < answer[w - weights[k]] + values[k]) {
					answer[w] = answer[w - weights[k]] + values[k];
				}
			}
			if (DEBUG) printArray(answer);
		}
	}
/*
10 6
4 6
2 4
3 5
1 3
6 9
4 7
*/
	public static void printArray(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			System.out.printf("%d ", nums[i]);
		}
		System.out.println();
	}
}
