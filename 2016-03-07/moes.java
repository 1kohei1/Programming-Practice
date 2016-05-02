import java.util.Scanner;
import java.util.ArrayList;

public class moes {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int n = scanner.nextInt();
		ArrayList<String> names = new ArrayList<String>();
		
		for (int i = 1; i <= n; i++) {
			String s = scanner.next();
			if (names.indexOf(s) == -1) {
				System.out.printf("Customer #%d: Welcome to Moe's!!!\n", i);
				names.add(s);
			} else {
				System.out.printf("Customer #%d: **continue working**\n", i);
			}
		}
	}
/*
7
Ethan
Jack
Ethan
Billy
Susan
Jack
Jack
 */
}
