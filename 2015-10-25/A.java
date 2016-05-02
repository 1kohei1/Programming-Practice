import java.util.*;

public class A {

	static int memo[];
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			int numCandy = scanner.nextInt();
			int money = (int) (scanner.nextDouble() * 100);
			
			if (numCandy == 0 && money == 0) break;
			
			int[] calories = new int[numCandy];
			int[] prices = new int[numCandy];
			memo = new int[money + 1];
			
			for (int i = 0; i < numCandy; i++) {
				calories[i] = scanner.nextInt();
				prices[i] = (int) (scanner.nextDouble() * 100);
			}
			
			for(int i = 0; i < numCandy; i++) {
				for(int j = prices[i]; j <= money; j++) {
//					System.out.printf("j: %d, compare1: %d, compare2: %d\n", j, memo[j], memo[j - prices[i]] + calories[i]);
//					memo[j] = Math.max(memo[j], memo[j - prices[i]] + calories[i]);
				}
			}
//			System.out.println(memo[money]);
			
			int result = solve(calories, prices, money, 0);
			System.out.println(result > 0 ? result : 0);
		}
	}
	
	public static int solve(int[] calories, int[] prices, int leftMoney, int index) {
		if (leftMoney == 0) {
			return 0;
		}
		if (leftMoney < 0) {
			return -Integer.MAX_VALUE;
		}
		if (index == calories.length) {
			return -Integer.MAX_VALUE;
		}
		// When the memo's value is set, it is considered that is the optimized answer. So this does not work. 
		if (memo[leftMoney] != 0) {
			return memo[leftMoney];
		}

		int a = calories[index] + solve(calories, prices, leftMoney - prices[index], index);
		int b = solve(calories, prices, leftMoney, index + 1);
//		System.out.printf("leftMoney: %d, index: %d, compare1: %d, compare2: %d\n", leftMoney, index, a, b);
//		int result = Math.max(
//				calories[index] + solve(calories, prices, leftMoney - prices[index], index),
//				solve(calories, prices, leftMoney, index + 1)
//			);
		int result = Math.max(a, b);
		memo[leftMoney] = result;
		return result;
	}
/*
2 8.00
700 7.00
199 2.00
3 8.00
700 7.00
299 3.00
499 5.00
0 0.00
 */
}
