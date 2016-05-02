import java.util.*;

public class Upwards {

	static String[] chars = "abcdefghijklmnopqrstuvwxyz".split("");
	static int count = 0;
	static boolean done = false;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int numCase = scanner.nextInt();
		while(numCase-- > 0) {
			
			done = false;
			count = 0;
			
			int skip = scanner.nextInt();
			int numChars = scanner.nextInt();
			int rank = scanner.nextInt();
		
			result("", skip, numChars, 0, 0, rank);
		}
	}
/*
2
1 3 16
0 25 24
*/
	
	public static void result(String word, int k, int n, int curr, int fillingIndex, int r) {
		if (word.length() == n) {
			count++;
			if (count == r) {
				System.out.printf("%s\n", word);
				done = true;
			}
		} else {
			for (int i = curr; i < chars.length; i++) {
				if (!done) {
					result(word + chars[i] , k, n, i + k + 1, fillingIndex + 1, r);					
				}
			}
		}
	}
}
