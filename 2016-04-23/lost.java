import java.util.Arrays;
import java.util.Scanner;

public class lost {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int nt = in.nextInt();
		for (int c = 0; c < nt; c++) {
			int n = in.nextInt();
			int k = in.nextInt();
			
			// Index i forwards person to the building forward[i];
			int[] forward = new int[n];
			for (int i = 0; i < n; i++) {
				forward[i] = in.nextInt() - 1;
			}
		
			// ith person is at building number loc[i]
			int[] loc = new int[n];
			// personOrder[i] must be printed out at ith
			int[] personOrder = new int[n];
			
			for (int i = 0; i < n; i++) {
				loc[i] = i;
				personOrder[i] = in.nextInt() - 1;
			}
			
			// Move person at loc[i] k times
			for (int i = 0; i < n; i++) {
				int now = loc[i];
				for (int j = 0; j < k; j++) {
					now = forward[now];
				}
				loc[i] = now;
			}
			
			int[] nums = new int[n];
			for (int i = 0; i < n; i++) {
				nums[loc[i]] = personOrder[i];
			}
			
			// So this is printing out which building has which # of person.
			for (int i = 0; i < n; i++) {
				System.out.printf("%d ", nums[i] + 1);
			}
			System.out.println();
		}
	}
/*
2
4 6
4 3 2 1
3 4 1 2
4 7
4 3 2 1
3 4 1 2

4 0
4 3 2 1
3 4 1 2
 */
}
