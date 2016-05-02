import java.util.Scanner;

public class h_02_28 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int[] freq = new int[26];
		
		int numLettersSaid = 0;
		int direction = 1;
		int index = 0; // Go this next
		
		int numOrders = scanner.nextInt();
		
		for (int counter = 0; counter < numOrders; counter++) {
			String order = scanner.next();
			int n = scanner.nextInt();
			
			for (; numLettersSaid < n; numLettersSaid++) {
				freq[index]++;
				index += direction;
				if (index == -1) {
					index = 25;
				} else if (index == 26) {
					index = 0;
				}
			}
			
			if  (order.equals("QUERY")) {
				int alphaInt = scanner.next().charAt(0) - 97;
				System.out.println(freq[alphaInt]);
			} else if (order.equals("DIR")) {
				direction *= -1;
				index += 2 * direction;
				if (index == -1) {
					index = 25;
				} else if (index == -2) {
					index = 24;
				} else if (index == 26) {
					index = 0;
				} else if (index == 27) {
					index = 1;
				}
			}
		}
	}
/*
5
QUERY 1 b
QUERY 3 b
DIR 4
QUERY 7 a
QUERY 10 z

5
DIR 1
DIR 2
DIR 3
QUERY 5 a
QUERY 7 w

4
QUERY 100 a
QUERY 200 c
QUERY 300 a
QUERY 400 b
 */
}
