import java.util.Scanner;
import java.math.BigInteger;
import java.util.Arrays;

public class dancerecital {

	static int n;
	static int[] routines;
	static int answer;
	static int[][] quickMap;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		n = scanner.nextInt();
		routines = new int[n];
		answer = Integer.MAX_VALUE;
		quickMap = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			routines[i] = convertToInt(scanner.next());
		}
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (i == j) continue;
				
				int numQuick = new BigInteger("" + (routines[i] & routines[j])).bitCount();
				quickMap[i][j] = numQuick;
				quickMap[j][i] = numQuick;
			}
		}
		
		boolean[] used = new boolean[n];
		Arrays.fill(used, false);
		solve(0, new int[n], used);
		System.out.println(answer);
	}
	
	public static void solve(int index, int[] comb, boolean[] used) {
		if (index == n) {
			int numQuick = numQuick(comb);
			answer = Math.min(answer, numQuick(comb));
		}
		
		for (int i = 0; i < n; i++) {
			if (used[i] == false) {
				comb[index] = i;
				used[i] = true;
				solve(index + 1, comb, used);
				used[i] = false;
			}
		}
	}
	
	public static int numQuick(int[] comb) {
		int num = 0;
		for (int i = 1; i < n; i++) {
			num += quickMap[comb[i]][comb[i - 1]];
		}
		return num;
	}
	
	public static int convertToInt(String s) {
		int num = 0;
		char[] c = s.toCharArray();
		for (int i = 0; i < c.length; i++) {
			num += 1 << (c[i] - 'A');
		}
		return num;
	}
/*
5
ABC
ABEF
DEF
ABCDE
FGH

6
BDE
FGH
DEF
ABC
BDE
ABEF

4
XYZ
XYZ
ABYZ
Z
 */
	
}
