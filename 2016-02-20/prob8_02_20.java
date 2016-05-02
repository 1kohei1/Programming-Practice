import java.util.Scanner;
import java.util.Arrays;

public class prob8_02_20 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		while (true) {
			int n = scanner.nextInt();
			if (n == 0) break;
			
			int[] lengths = new int[n];
			int sum = 0;
			for (int i = 0; i < n; i++) {
				lengths[i] = scanner.nextInt();
				sum += lengths[i];
			}
			double m = sum / 2.0;
			
			Arrays.sort(lengths);
			
			double area[] = new double[sum];
			
			for (int i = 0; i < n; i++) {
				for (int j = sum - 1; j >= lengths[i]; j--) {
					if (j == lengths[i] && area[j] == 0) {
						area[j] = calculate(m, j);
					} else if (area[j - lengths[i]] != 0) {
						area[j] = calculate(m, j);
					}
				}
			}
			Arrays.sort(area);
			System.out.println((int) area[sum - 1]);
		}
	}
/*
3 4 2 5
4 1 3 7 10
4 3 3 8 11
3 1 1 1
 */
	public static void printArea(double[] area, int sum) {
		for (int i = 0; i < sum; i++) {
			if (area[i] != 0) {
				System.out.printf("[%d]: %f ", i, area[i]);
			}
		}
		System.out.println();
	}
	
	public static double calculate(double m, int a) {
		return (m * m - (a - m) * (a - m)) / 2;
	}
}
