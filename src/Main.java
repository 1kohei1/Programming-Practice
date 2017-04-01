import java.util.*;
 
// ABC 6-C
// http://abc006.contest.atcoder.jp/tasks/abc006_3
 
public class Main {

	static int n;
	static int m;
	static boolean answerFound = false;
	static int a = -1;
	static int b = -1;
	static int c = -1;
	static int[][] dp;
	
	public static void main (String[] args) throws java.lang.Exception {
	    Scanner in = new Scanner(System.in);

	    n = in.nextInt();
	    m = in.nextInt();
	    
	    dp = new int[n + 1][m + 1];
	    solve(0, m, 0, 0, 0);
	    System.out.printf("%d %d %d\n", a, b, c);
	}
	
	public static void solve(int nn, int mm, int aa, int bb, int cc) {
		if (nn == n && mm == 0) {
			answerFound = true;
			a = aa;
			b = bb;
			c = cc;
		} else if (answerFound || nn > n || mm < 0 || dp[nn][mm] == 1) {
			return;
		} else {
			// child
			solve(nn + 1, mm - 4, aa, bb, cc + 1);
			// elder
			solve(nn + 1, mm - 3, aa, bb + 1, cc);
			// adult
			solve(nn + 1, mm - 2, aa + 1, bb, cc);
			
			// Don't explore this combination any more
			dp[nn][mm] = 1;
		}
	}
}
