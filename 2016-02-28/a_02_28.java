import java.math.BigInteger;
import java.util.Scanner;

public class a_02_28 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println((int)Math.pow(10, 10));
		System.out.println((int)Math.pow(2, 31));
		
		int numTests = scanner.nextInt();
		for (int counter = 0; counter < numTests; counter++) {
			int n = scanner.nextInt();
			
			BigInteger bi = new BigInteger(scanner.next());
		}
	}

	public static int gcd(int a, int b) {
		return a % b == 0 ? b : gcd(b, a % b);
	}
}
