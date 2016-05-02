import java.util.Scanner;
import java.util.ArrayList;

public class D {

	static int[][] map;
	static int count;
	static boolean shouldStop;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			int n = scanner.nextInt();
			int m = scanner.nextInt();
			
			if (n == 0 && m == 0) return;
			
			// [row][col] = 1 => row cannot come before col
			map = new int[n][n];
			count = 0;
			shouldStop = false;
			
			for (int i = 0; i < m; i++) {
				int pre = scanner.nextInt();
				int post = scanner.nextInt();
				map[post - 1][pre - 1] = 1;
			}
			solve(new int[n], new int[n], n, 0);
			System.out.println(count);
		}
	}
		
	public static void solve(int[] sol, int[] used, int n, int fillingIndex) {
		if (fillingIndex == n) {
			if (count == 2) {
				shouldStop = true;
				return;
			} else {
				count++;
			}
		}

		System.out.println("sol");
		print(sol, fillingIndex);
		System.out.println("used");
		print(used, n);
		used = fillUsed(sol, used, fillingIndex);
		System.out.println("used");
		print(used, n);
		
		for (int i = 0; i < n; i++) {
			if (!shouldStop && used[i] == 0) {
				used[i] = 1;
				sol[fillingIndex] = i;
				solve(sol, used, n, fillingIndex + 1);
				used[i] = 0;
			}
		}
	}
	
	public static int[] fillUsed(int[] sol, int[] used, int fillingIndex) {
		for (int i = 0; i < sol.length; i++) {
			for (int j = 0; j < fillingIndex; j++) {
				if (used[i] == 0 && map[i][sol[j]] == 1) {
					used[i] = 1;
				}
			}
		}
		return used;
	}
	
	public static boolean check(int[] sol, int inserting, int fillingIndex) {
		for (int i = 0; i < fillingIndex; i++) {
			if (map[sol[i]][inserting] == 1) return false;
		}
		return true;
	}
	
	public static void print(int[] sol, int fillingIndex) {
		for (int i = 0; i < fillingIndex; i++) {
			System.out.printf("%d", sol[i]);
		}
		System.out.println();		
	}
/*
5 4
1 5
5 2
3 2
4 3
===
5 4
3 1
4 2
1 5
5 4
===
2 2
1 2
2 1
0 0
 */
}
