import java.util.Scanner;

public class thirty {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int n = scanner.nextInt();
		for (int i = 0; i < n; i++) {
			System.out.println(scanner.nextInt() / 6);
		}
	}
/*
3
30
66
246
 */
}
