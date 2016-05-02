import java.util.Scanner;

public class LSC {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			System.out.printf("%d\n", lcsrec(scanner.next(), scanner.next()));			
		}
	}
	
	public static int lcsrec(String x, String y) {
		// Base case empty string
		if (x.length() == 0 || y.length() == 0) return 0;
		int len1 = x.length();
		int len2 = y.length();
		
		// Solve the problem recursively
		
		if (x.charAt(len1 - 1) == y.charAt(len2 - 1)) {
			return 1 + lcsrec(x.substring(0, len1 - 1), y.substring(0, len2 - 1));
		} else {
			return Math.max(
					lcsrec(x, y.substring(0, len2 - 1)),
					lcsrec(x.substring(0, len1 - 1), y)
				);
		}
	}

}
