import java.util.Scanner;

public class Bitmask {

	public static void main(String[] args) {
		// Write a program that creates power set of n by using bit mask
		Scanner scanner = new Scanner(System.in);
		
		while (true) {
			int n = scanner.nextInt();
			if (n == 0) break;
			printAllSubsets(n);
			sumOfAllSubsets(n);
		}
	}
	
	public static void sumOfAllSubsets(int n) {
		int max = 1 << n;
		int sum = 0;
		for (int i = 0; i < max; i++) {
			for (int j = 0; j < n; j++) {
				if ((i & (1 << j)) != 0) {
					sum += j;
				}
			}
		}
		System.out.println(sum);
	}
	
	public static void printAllSubsets(int n) {
		int max = calculateMax(n);
		
		for (int i = 0; i < max; i++) {
			System.out.printf("{");
			int count = 0;
			for (int j = 0; j < n; j++) {
				if ((i & 1 << j) != 0) {
					System.out.printf("%s%d", count == 0 ? "" : ", ", j);
					count++;
				}
			}
			System.out.printf("}");
		}
		System.out.println();
	}

	public static int calculateMax(int n) {
		return 1 << n;
	}
}
