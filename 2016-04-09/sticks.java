import java.util.Arrays;
import java.util.Scanner;

public class sticks {

	// Spencer Solution
	public static int[] s_vals;
	public static Long[][] s_memo;
	public static  int s_cuts;
	public static int s_len;
	
	// Kohei Solution
	static boolean[] k_nums;
	static int[][] k_memo;
	
	// Kohei Solution 2
	static int[] k_nums_2;
	static int[][] k_memo_2;
	
	public static void main(String[] args){
		long start = System.currentTimeMillis();
//		kohei_solution();
//		spencer_solution();
		kohei_solution2();
		System.out.println("Took " + (System.currentTimeMillis() - start + "ms"));
	}
	
	public static void kohei_solution2() {
		Scanner scanner = new Scanner(System.in);
		
		int nt = scanner.nextInt();
		
		while(nt-- > 0) {
			long start = System.currentTimeMillis();
			int l = scanner.nextInt();
			int n = scanner.nextInt();
			
			k_nums_2 = new int[n + 2];
			k_nums_2[0] = 0;
			for (int i = 1; i <= n; i++) {
				k_nums_2[i] = scanner.nextInt();
			}
			k_nums_2[n + 1] = l;
			
			k_memo_2 = new int[l + 1][l + 1];
			for (int i = 0; i < l + 1; i++) {
				Arrays.fill(k_memo_2[i], -1);
			}
			
			System.out.println(solve2(0, n + 1));
			System.out.println("Took " + (System.currentTimeMillis() - start) + "ms");
		}
	}
	
	public static int solve2(int start, int end) {
		if (start == end) {
			return k_nums_2[start];
		}
		if (start > end || end - start <= 1) {
			return 0;
		}
		if (k_memo_2[start][end] != -1) {
			return k_memo_2[start][end];
		}
		
		int answer = Integer.MAX_VALUE;
		for (int i = start + 1; i < end; i++) {
			answer = Math.min(answer, k_nums_2[end] - k_nums_2[start] + solve2(start, i) + solve2(i, end));
		}
		if (answer == Integer.MAX_VALUE) {
			answer = 0;
		}
		k_memo_2[start][end] = answer;
		return answer;
	}
	
	public static void kohei_solution() {
		Scanner scanner = new Scanner(System.in);
		
		int nt = scanner.nextInt();
		while(nt-- > 0) {
			long start = System.currentTimeMillis();
			int l = scanner.nextInt();
			int n = scanner.nextInt();
			
			k_nums = new boolean[l];
			for (int i = 0; i < n; i++) {
				k_nums[scanner.nextInt()] = true;
			}
			k_memo = new int[l + 1][l + 1];
			for (int i = 0; i < l + 1; i++) {
				Arrays.fill(k_memo[i], -1);
			}
			
			System.out.println(solve(0, l));
			System.out.println("Took " + (System.currentTimeMillis() - start) + "ms");
		}
	}
	
	public static void spencer_solution() {
		Scanner in = new Scanner(System.in);

		int cases = in.nextInt();
		
		for(int q=1; q<=cases; q++){
			long start = System.currentTimeMillis();
			
			s_len  =in.nextInt();
			s_cuts = in.nextInt();
			s_vals =  new int[s_cuts];
			
			for(int i =0; i<s_cuts; i++){
				s_vals[i] = in.nextInt();
			}
			s_memo = new Long[s_cuts+2][s_cuts+2];
			System.out.println(go(-1,s_cuts));
			System.out.println("Took " + (System.currentTimeMillis() - start) + "ms");
		}
	}
/*
2
100
3 25 50 75
10
3 2 5 6

10
3 4 6 7 => 19
10
4 3 4 6 9 => 23

1
10
1 5 => 10

1
10000
100 2 4 6 8 10 12 14 16 18 20 22 24 26 28 30 32 34 36 38 40 42 44 46 48 50 52 54 56 58 60 62 64 66 68 70 72 74 76 78 80 82 84 86 88 90 92 94 96 98 100 102 104 106 108 110 112 114 116 118 120 122 124 126 128 130 132 134 136 138 140 142 144 146 148 150 152 154 156 158 160 162 164 166 168 170 172 174 176 178 180 182 184 186 188 190 192 194 196 198 200  
=> 11344
 */
	public static int solve(int start, int end) {
		if (start >= end) {
			return 0;
		}
		if (k_memo[start][end] != -1) {
			return k_memo[start][end];
		}
		boolean didCutFound = false;
		int answer = Integer.MAX_VALUE;
		for (int i = start + 1; i < end; i++) {
			if (k_nums[i]) {
				didCutFound = true;
				answer = Math.min(answer, end - start + solve(start, i) + solve(i, end));
			}
		}
		if (!didCutFound) {
			answer = 0;
		}
		k_memo[start][end] = answer;
		return answer;
	}
		
	public static long go(int left, int right){
		int min = 0;
		int max = s_len;
		if(right-left<2){
			return 0;
		}
		if(s_memo[left+1][right+1]!=null){
			return s_memo[left+1][right+1];
		}
		if(left>=0){
			min = s_vals[left];
		}
		if(right<s_cuts){
			max = s_vals[right];
		}
		long best = Long.MAX_VALUE/2;
		for(int i = left+1; i<right; i++){
			best = Math.min(best, max-min+go(left,i)+go(i,right));
		}
		s_memo[left+1][right+1] = best;
		return best;
	}
}
