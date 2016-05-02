import java.util.Scanner;

public class guessing {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int n = scanner.nextInt();
		for (int i = 1; i <= n; i++) {
			int low = scanner.nextInt();
			int high = scanner.nextInt();
			int selected = scanner.nextInt();
		
			int answer = answer(selected, low, high, 1);
			System.out.printf("Game #%d: %d guess%s\n", i, answer, answer == 1 ? "" : "es");
		}
	}
/*
3
1 100 33
1 3 2
11 19 14
 */
	public static int answer(int selected, int low, int high, int numGuess) {
		int guess = middle(low, high);
		if (guess < selected) {
			return answer(selected, guess + 1, high, numGuess + 1);
		} else if (guess > selected) {
			return answer(selected, low, guess - 1, numGuess + 1);
		} else {
			return numGuess;
		}
	}
	
	public static int middle(int low, int high) {
		int guess = (low + high) / 2;
		guess += (low + high) % 2 == 1 ? 1: 0;
		return guess;
	}
}
