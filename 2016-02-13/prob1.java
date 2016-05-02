import java.util.Scanner;

public class prob1 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int numTests = scanner.nextInt();
		for (int counter = 1; counter <= numTests; counter++) {
			int n = scanner.nextInt();
			
			boolean answerFound = false;
			for (int x = (int) Math.sqrt(n); x >= 2; x--) {
				if (n % x == 0) {
					int y = n / x;
					int a = y - x + 1;
					int b = x + y - 1;
					if (b % 2 == 1 && a % 2 == 1) {
						answerFound = true;
						System.out.printf("%d: [%d, %d]\n", n, a, b);
					}
				}
			}
			if (!answerFound) {
				System.out.printf("%d: impossible\n", n);
			}
		}
	}
	
/*
3
7
35
400
 */
}
