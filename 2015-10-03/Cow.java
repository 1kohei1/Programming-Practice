import java.util.*;

public class Cow {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int numCase = scanner.nextInt();
		while(numCase-- > 0) {
			int numLength = scanner.nextInt();
			char[] s = scanner.next().toCharArray();
			
			long cow = 0;
			long ow = 0;
			int w = 0;
			
			for (int i = numLength - 1; i >= 0; i--) {
				if (s[i] == 'W') {
					w++;
				} else if (s[i] == 'O') {
					ow += w;
				} else if (s[i] == 'C') {
					cow += ow;
				}
			}
			System.out.printf("%d\n", cow);
		}
	}
}
/*
2
6
COOWWW
7
CWOWCOW
*/
