import java.util.Scanner;
import java.util.Arrays;

public class lineup {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int n = scanner.nextInt();
		String[] names = new String[n];
		String[] orderedNames = new String[n];
		
		for (int i = 0; i < n; i++) {
			names[i] = scanner.next();
			orderedNames[i] = names[i];
		}
		
		Arrays.sort(orderedNames);
		
		boolean isIncreasing = true;
		for (int i = 0; i < n; i++) {
			if (!names[i].equals(orderedNames[i])) {
				isIncreasing = false;
				break;
			}
		}
		if (isIncreasing) {
			System.out.println("INCREASING");
			return;
		}
		
		boolean isDecreasing = true;
		for (int i = n - 1; i >= 0; i--) {
			if (!names[i].equals(orderedNames[n - i - 1])) {
				isDecreasing = false;
				break;
			}
		}
		if (isDecreasing) {
			System.out.println("DECREASING");
			return;
		}
		
		System.out.println("NEITHER");
	}
/*
3
A
B
C

5
E
D
C
B
A

3
A
C
B

5
JOE
BOB
ANDY
AL
ADAM

11
HOPE
ALI
BECKY
JULIE
MEGHAN
LAUREN
MORGAN
CARLI
MEGAN
ALEX
TOBIN

4
GEORGE
JOHN
PAUL
RINGO
 */
}
