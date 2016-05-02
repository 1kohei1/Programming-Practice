import java.util.Scanner;

// uva 10855

public class Main {

	static int n1;
	static int n2;
	
	static char[][] bigger;
	static char[][] smaller;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		while(true) {
			n1 = in.nextInt();
			if (n1 == 0) {
				break;
			}
			n2 = in.nextInt();
			
			bigger = new char[n1][n1];
			smaller = new char[n2][n2];
			
			for (int i = 0; i < n1; i++) {
				bigger[i] = in.next().toCharArray();
			}
			for (int i = 0; i < n2; i++) {
				smaller[i] = in.next().toCharArray();
			}
			
			for (int i = 0; i < 4; i++) {
				System.out.printf("%d%s", count(), i == 3 ? "" : " ");
				rotate();
			}
			System.out.println();
		}
	}
	
	// Return how many it has
	public static int count() {
		int count = 0;
		
		for (int i = 0; i <= n1 - n2; i++) {
			for (int j = 0; j <= n1 - n2; j++) {
				boolean isFound = true;
				for (int k = 0; k < n2 && isFound; k++) {
					for (int l = 0; l < n2 && isFound; l++) {
						if (smaller[k][l] != bigger[i + k][j + l]) {
							isFound = false;
						}
					}
				}
				if (isFound) {
					count++;
				}
			}
		}
		
		return count;
	}
	
	// Rotate 90 degree
	public static void rotate() {
		// move first col to first row,
		// move second col to second row,
		// move thrid col to third row
		
		char[][] newSmaller = new char[n2][n2];
		for (int i = 0; i < n2; i++) {
			for (int j = n2 - 1; j >= 0; j--) {
				newSmaller[i][n2 - 1 - j] = smaller[j][i];
			}
		}
		smaller = newSmaller;
	}
	
	public static void printArray(char[][] c) {
		for (int i = 0; i < c.length; i++) {
			for (int j = 0; j < c[i].length; j++) {
				System.out.print(c[i][j]);
			}
			System.out.println();
		}
	}
	
}
