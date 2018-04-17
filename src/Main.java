import java.util.*;

// ABC 64-D
// https://beta.atcoder.jp/contests/abc064/tasks/abc064_d

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int N = in.nextInt();
		char[] c = in.next().toCharArray();
		
		int pre = 0;
		int post = 0;
		int cur = 0;
		
		for (int i = 0; i < c.length; i++) {
			if (c[i] == '(') {
				cur++;
			} else if (cur > 0) {
				cur--;
			} else {
				pre++;
			}
		}
		post = cur;
		
		for (int i = 0; i < pre; i++) {
			System.out.printf("(");
		}
		for (int i = 0; i < c.length; i++) {
			System.out.print(c[i]);
		}
		for (int i = 0; i < post; i++) {
			System.out.print(')');
		}
		System.out.println();
	}
}
