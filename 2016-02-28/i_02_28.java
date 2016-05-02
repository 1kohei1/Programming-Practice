import java.util.Scanner;

public class i_02_28 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		while (true) {
			int n = scanner.nextInt();
			int m = scanner.nextInt();
			if (n == 0) break;
			System.out.println("n binary: " + Integer.toBinaryString(n));
			System.out.println("m binary: " + Integer.toBinaryString(m));
			System.out.println("n & m: " + (n & m) + ", n & m binary: " + Integer.toBinaryString(n & m));
		}
	}

}
