import java.util.*;
 
// ABC 11-C
// http://abc011.contest.atcoder.jp/tasks/abc011_3
 
public class Main {
 
	static int n;
	
	static int a;
	static int b;
	static int c;
	
	static int[] dp;
	
	public static void main (String[] args) throws java.lang.Exception {
	    Scanner in = new Scanner(System.in);
	    
	    n = in.nextInt();
	    
	    a = in.nextInt();
	    b = in.nextInt();
	    c = in.nextInt();
	    
	    dp = new int[n + 1];
	    
	    filldp(0, 0);
	    System.out.println(dp[n] == 0 || dp[n] > 100 ? "NO" : "YES");
	}
	
	public static void filldp(int op, int pre) {
		if (pre + 3 < n + 1 && dp[pre + 3] == 0 && pre + 3 != a && pre + 3 != b && pre + 3 != c) {
			dp[pre + 3] = dp[pre] + 1;
			filldp(op + 1, pre + 3);
		}
		if (pre + 2 < n + 1 && dp[pre + 2] == 0 && pre + 2 != a && pre + 2 != b && pre + 2 != c) {
			dp[pre + 2] = dp[pre] + 1;
			filldp(op + 1, pre + 2);
		}
		if (pre + 1 < n + 1 && dp[pre + 1] == 0 && pre + 1 != a && pre + 1 != b && pre + 1 != c) {
			dp[pre + 1] = dp[pre] + 1;
			filldp(op + 1, pre + 1);
		}
	}
}