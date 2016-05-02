import java.util.Scanner;

// uva 10920

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		while(true) {
			int n = in.nextInt();
			if (n == 0) {
				break;
			}
			int p = in.nextInt();
			
			if (p == 1) {
				printAnswer((n + 1) / 2, (n + 1) / 2);
				continue;
			}
			
			int num = 0;
			boolean answerFound = false;
			
			for (int i = 1; i <= n; i += 2) {
				if (i * i == p) {
					int a = (n + 1) / 2 + i / 2;
					printAnswer(a, a);
					answerFound = true;
				} else if (p < i * i) {
					num = i - 2;
					break;
				}
			}
//			System.out.println("num: " + num);
			
			if (answerFound) {
				continue;
			}
			
			// If it's in row above
			if (num * num < p && p <= num * num + (1 + num)) {
				int diff = num * num + 1 - p;
				int row = (n + 1) / 2 + num / 2 + 1;
				int col = (n + 1) / 2 + num / 2 + diff;
				printAnswer(row, col);
			}
			// If it's in left column
			else if (num * num + (1 + num) < p && p <= num * num + 2 * (1 + num)) {
				int diff = num * num + (1 + num) - p;
				int row = (n + 1) / 2 + num / 2 + 1 + diff;
				int col = (n + 1) / 2 + num / 2 - num;
				printAnswer(row, col);
			}
			// If it's in bottom row
			else if (num * num + 2 * (1 + num) < p && p <= num * num + 3 * (1 + num)) {
				int diff = num * num + 2 * (1 + num) - p;
				int row = (n + 1) / 2 + num / 2 - num;
				int col = (n + 1) / 2 + num / 2 - num - diff;
				printAnswer(row, col);
			} else {
				int diff = (num + 2) * (num + 2) - p;
				int row = (n + 1) / 2 + num / 2 + 1 - diff;
				int col = (n + 1) / 2 + num / 2 + 1;
				printAnswer(row, col);
			}
		}
	}
	
	public static void printAnswer(int a, int b) {
		System.out.printf("Line = %d, column = %d.\n", a, b);
	}
}
