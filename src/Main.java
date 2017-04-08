import java.util.*;
 
// ABC 19-C
// http://abc019.contest.atcoder.jp/tasks/abc019_3
 
public class Main {
	
	static int MOD = 1000000007;

	public static void main (String[] args) throws java.lang.Exception {
		Scanner in = new Scanner(System.in);

		int n = in.nextInt();
		int m = in.nextInt();
		long answer = 0;
		
		long[] x = new long[n];
		long[] y = new long[m];
		
		for (int i = 0; i < n; i++) {
			x[i] = in.nextInt();
		}
		for (int i = 0; i < m; i++) {
			y[i] = in.nextInt();
		}
		
		long xx = 0;
		long yy = 0;
		for (int i = n - 1; i > 0; i -= 2) {
			xx += i * x[(n + i) / 2];
			xx -= i * x[(n - i) / 2];
		}
		for (int i = m - 1; i > 0; i -= 2) {
			yy += i * y[(m + i) / 2];
			yy -= i * y[(m - i) / 2];
		}
		
		System.out.printf("%d\n", ((xx % MOD) * (yy % MOD)) % MOD);
		
//		for (int i = 0; i < n; i++) {
//			for (int j = i + 1; j < n; j++) {
//				long xx = x[j] - x[i];
//				for (int k = 0; k < m; k++) {
//					for (int l = k + 1; l < m; l++) {
//						long yy = y[l] - y[k];
//						answer += xx * yy;
//						answer %= MOD;
//					}
//				}
//			}
//		}
//		
//		System.out.println(answer);
		
		
//		int t = in.nextInt();
//		int num = 1;
//		while (t-- > 0) {
//			int n = in.nextInt();
//			
//			while (!isTidy(n)) {
//				n--;
//			}
//			System.out.printf("Case #%d: %d\n", num, n);
//			num++;
//		}
	}	
	
	public static boolean isTidy(int n) {
		if (n < 10) {
			return true;
		}
		int pre = n % 10;
		n /= 10;
		int curr = n % 10;
		
		while (n > 0) {
			if (pre < curr) {
				return false;
			}
			pre = curr;
			n /= 10;
			curr = n % 10;
		}
		return true;
	}
}
