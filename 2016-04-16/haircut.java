import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class haircut {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int counter = 1;
		int nt = scanner.nextInt();
		while(nt-- > 0) {
			int n = scanner.nextInt();
			long numInLine = scanner.nextInt();
			
			int[] nums = new int[n];
			for (int i = 0; i < n; i++) {
				nums[i] = scanner.nextInt();
			}
			
			if (n == 1) {
				System.out.printf("Case #%d: 1\n", counter);
				counter++;
				continue;
			}
			
			if (numInLine <= n) {
				System.out.printf("Case #%d: %d\n", counter, numInLine);
				counter++;
				continue;
			}
			
			long low = 0;
			long high = (long) Math.pow(10, 15);
			int answer = 0;
			
			for (int i = 0; i < 100; i++) {
				long mid = (low + high) / 2;
				
				long howManyDone = 0;
				int lastBarberIndex = 0;
				long rem = Long.MAX_VALUE;
				
				for (int j = 0; j < n; j++) {
					howManyDone += mid / nums[j] + 1;
					if (mid % nums[j] <= rem) {
						rem = mid % nums[j];
						lastBarberIndex = j;
					}
				}
				
				if (howManyDone == numInLine) {
					answer = lastBarberIndex + 1;
					break;
				} else if (howManyDone > numInLine) {
					high = mid;
					continue;
				}
				
				for (int j = 0; j < n; j++) {
					if ((mid + 1) % nums[j] == 0) {
						howManyDone++;
						if (howManyDone == numInLine) {
							answer = j + 1;
							break;
						}
					}
				}
				
				if (howManyDone < numInLine) {
					low = mid + 1;
				} else if (howManyDone > numInLine) { // Theoretically not achievable
					high = mid - 1;
				} else {
					break;
				}
			}
			
			System.out.printf("Case #%d: %d\n", counter, answer);
			counter++;
		}
	}
/*
3
2 4
10 5
3 12
7 7 7
3 8
4 2 1

1
3 12
7 7 7

1
2 4
10 5

1
2 4
2 2
 */
}
