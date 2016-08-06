import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class d_05_06 {

	static long[] answer;
	static int MAX = 1000;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		answer = new long[MAX];
		answer[0] = 1; // That number it self.
		answer[1] = 1;
		answer[2] = 2;
		
		long[] cum = new long[MAX];
		cum[0] = 1;
		cum[1] = 2;
		cum[2] = 4;
		
		for (int i = 3; i < MAX; i++) {
			answer[i] = cum[(i - 1) / 2];
			if (i % 2 == 0) {
				answer[i] += answer[i / 2];
			}
			cum[i] = cum[i - 1] + answer[i];
		}

		int n = Integer.parseInt(in.readLine());
		for (int i = 1; i <= n; i++) {
			int a = Integer.parseInt(in.readLine());
			long b = solve(a);
			System.out.println(i + " " + b);
		}
	}

	public static long solve(int n) {
		if (n < MAX) {
			return answer[n];
		}
		long a = solve((n - 1) / 2);
		if (n % 2 == 0) {
			a += solve(n / 2);
		}
		return a;
	}
}
/*
3
1
2
3
4
7
20
*/