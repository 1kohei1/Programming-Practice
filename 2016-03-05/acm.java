import java.util.Scanner;
import java.util.Arrays;

public class acm {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int max = 26;
		int timeScore = 0;
		
		boolean[] isSolved = new boolean[max];
		Arrays.fill(isSolved, false);
		
		int[] numAttempts = new int[max];
		
		while (true) {
			int m = scanner.nextInt();
			if (m == -1) {
				break;
			}
			int prob = ((int) scanner.next().charAt(0)) - 65;
			
			String s = scanner.next();
			if (s.equals("right")) {
				isSolved[prob] = true;
				timeScore += m;
			} else if (s.equals("wrong")) {
				numAttempts[prob]++;
			}
		}
		int numSolved = 0;
		for (int i = 0; i < max; i++) {
			if (isSolved[i]) {
				numSolved++;
				timeScore += numAttempts[i] * 20;
			}
		}
		System.out.printf("%d %d\n", numSolved, timeScore);
	}
/*
3 E right
10 A wrong
30 C wrong
50 B wrong
100 A wrong
200 A right
250 C wrong
300 D right
-1

7 H right
15 B wrong
30 E wrong
35 E right
80 B wrong
80 B right
100 D wrong
100 C wrong
300 C right
300 D wrong
-1
 */
}
