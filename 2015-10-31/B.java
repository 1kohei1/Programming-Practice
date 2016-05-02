import java.util.*;

public class B {

	static long[] memo = new long[1000000];
	static long[] sum = new long[1000000];
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Arrays.fill(memo, -1);
		
		int index = 0;
		
		while(true) {
			int num = scanner.nextInt();
			if (num == 0) break;
			
			if (index == 0) {
				sum[0] = num;
				memo[0] = num;
			} else {
				sum[index] = sum[index - 1] + num;
			}
			
			System.out.printf("%d\n", answer(index));
			index++;
		}
	}
	
	public static long answer(int index) {
		if (index == 0) {
			return sum[0];
		}
		if (memo[index] != -1) {
			return memo[index];
		}
		long answer = answer(index - 1) + sum[index];
		memo[index] = answer;
		return answer;
	}
/*
5
10
1
0
0
0
 */

}


