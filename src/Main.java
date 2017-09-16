import java.util.*;

// ARC 83-C
// http://arc083.contest.atcoder.jp/tasks/arc083_a

public class Main {
	
	static int A;
	static int B;
	static int C;
	static int D;
	static int E;
	static int F;
	
	static double concentration = 0;
	static int answerW = 0;
	static int answerS = 0;
	
	static int[][] dp;
	
	public static void main (String[] args) throws InterruptedException {
		Scanner in = new Scanner(System.in);
		
		A = in.nextInt();
		B = in.nextInt();
		C = in.nextInt();
		D = in.nextInt();
		E = in.nextInt();
		F = in.nextInt();
		
		dp = new int[F + 1][F * E / 100 + 1];
		
		answerW = 100 * A;
		
		solve(0, 0);
		
		System.out.printf("%d %d\n", answerW + answerS, answerS);
	}
	
	public static void solve(int water, int sugar) {
		if (water + sugar > F || water > F || sugar > F * E / 100) {
			return;
		}
		if (dp[water][sugar] == 1) {
			return;
		}
		
		boolean isSugarDissolved = isSugarDissolved(water, sugar);
		if (isSugarDissolved) {
			double conc = calculateConcentration(water, sugar);
			if (conc > concentration) {
				concentration = conc;
				answerW = water;
				answerS = sugar;
			}
		}

		dp[water][sugar] = 1;
				
		// Operation 1
		solve(water + 100 * A, sugar);
		// Operation 2
		solve(water + 100 * B, sugar);
		
		// Only sugar is dissolved, check adding sugar
		if (isSugarDissolved) {
			// Operation 3
			solve(water, sugar + C);
			// Operation 4
			solve(water, sugar + D);
		}
	}
	
	public static double calculateConcentration(int water, int sugar) {
		return (100.0 * sugar) / (water + sugar);
	}
	
	public static boolean isSugarDissolved(int water, int sugar) {
		return sugar <= water * E / 100;
	}
}
