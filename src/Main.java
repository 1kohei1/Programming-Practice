import java.util.*;

// ABC 29-C
// http://abc029.contest.atcoder.jp/tasks/abc029_c

public class Main {

	static int n;
	
	public static void main (String[] args) throws java.lang.Exception {
	    Scanner in = new Scanner(System.in);

	    n = in.nextInt();
	    char[] c = new char[n];
	    solve(0, c);
	}
	
	public static void solve(int index, char[] c) {
		if (index == n) {
			System.out.println(new String(c));
			return;
		}
		c[index] = 'a';
		solve(index + 1, c);
		c[index] = 'b';
		solve(index + 1, c);
		c[index] = 'c';
		solve(index + 1, c);
	}
}
