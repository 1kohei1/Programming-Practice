import java.util.*;

// ABC 70-C
// https://beta.atcoder.jp/contests/abc070/tasks/abc070_c

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int N = in.nextInt();
		long answer = 1;
		
		for (int i = 0; i < N; i++) {
			long n = in.nextLong();
			answer = lcm(answer, n);
		}
		System.out.println(answer);
	}
	
	public static long lcm(long a, long b) {
		long gcd = gcd(a, b);
		return gcd * (a / gcd) * (b / gcd);
	}
	
	public static long gcd(long a, long b) {
		if (a == 0) {
			return b;
		}
		return gcd(b % a, a);
	}
}
