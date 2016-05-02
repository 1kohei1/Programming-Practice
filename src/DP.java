import java.util.*;

public class DP {

	static boolean runCoinChange = true;
	
	public static void main(String[] args) {
//		runCoinChange();
//		runCoinChange2();
//		runKnapsach();
		runBruteForce();
	}
	
	public static void runCoinChange() {
		Scanner scanner = new Scanner(System.in);
		while(true) {
			int numCoins = scanner.nextInt();
			if (numCoins == 0) break;
			
			int coins[] = new int[numCoins];
			for (int i = 0; i < numCoins; i++) {
				coins[i] = scanner.nextInt();
			}
			
			int money = scanner.nextInt();
			int[][] memo = new int[money + 1][numCoins];
			for (int[] m : memo) {
				Arrays.fill(m, -1);
			}
			System.out.printf("%d\n", solveCoinChange(coins, money, memo, 0));
		}
	}
	
	public static int solveCoinChange(int[] coins, int money, int[][] memo, int index) {
		if (money == 0) {
			return 0;
		}
		if (money < 0) {
			return Integer.MAX_VALUE - 1;
		}
		if (index == coins.length) {
			return Integer.MAX_VALUE - 1;
		}
		if (memo[money][index] > -1) {
			return memo[money][index];
		}
		
		int result = Math.min(
				1 + solveCoinChange(coins, money - coins[index], memo, index), 
				solveCoinChange(coins, money, memo, index + 1)
			);
		System.out.printf("money: %d, index: %d, result: %d\n", money, index, result);
		memo[money][index] = result;
		return result;
	}
	
	public static void runCoinChange2() {
		Scanner scanner = new Scanner(System.in);
		
		int numCoin = scanner.nextInt();
		int[] coin = new int[numCoin];
		for (int i = 0; i < numCoin; i++) {
			coin[i] = scanner.nextInt();
		}
		int[] memo = new int[1000];
		
		while(true) {
			int change = scanner.nextInt();
			if (change == 0) break;
			
			System.out.printf("%d\n", solveCoinChange2(change, coin, memo));
		}
	}
	
	public static int solveCoinChange2(int change, int[] coin, int[] memo) {
		int minCoin = change;
		if (Arrays.asList(coin).indexOf(change) >= 0) {
			memo[change] = 1;
			return 1;
		} else if (memo[change] > 0) {
			return memo[change];
		} else {
			for (int i = 0; i < coin.length; i++) {
				if (coin[i] <= change) {
					int numCoin = 1 + solveCoinChange2(change - coin[i], coin, memo);
					if (numCoin < minCoin) {						
						minCoin = numCoin;
						memo[change] = minCoin;
					}
				}
			}
		}
		memo[change] = minCoin;
		return minCoin;
	}
	
	public static void runKnapsach() {
		// The number of total objects we are using.
		int numObjects = 4;
		
		// Variables for storing weights and values
		int[] weights = new int[]{3, 8, 9, 8};
		int[] values = new int[]{10, 4, 9, 11};
		
		// Maximum weights that the knapsack can carry
		int maxWeights = 20;
		
		// Store the maximum value that the knapsack can carry if knapsack can carry index-kg at maximum
		int[] answers = new int[maxWeights + 1];
		
		// Set initial value 0, meaning its value is 0 for index-kg
		for (int i = 0; i <= maxWeights; i++) {
			answers[i] = 0;
		}
		
		for (int i = 0; i < numObjects; i++) {
			for (int j = maxWeights; j >= weights[i]; j--) {
				if (answers[j - weights[i]] + values[i] > answers[j]) {
					answers[j] = answers[j - weights[i]] + values[i];
				}
			}
		}
		
		System.out.println(answers[maxWeights]);
	}
	
	static int maxValue = 0;
	
	public static void runBruteForce() {
		int numObjects = 4;
		int maxWeights = 20;

		int[] weights = new int[]{3, 8, 9, 8};
		int[] values = new int[]{10, 4, 9, 11};
		
		bruteForce(0, weights, values, 0, 0, numObjects, maxWeights);
		System.out.println(maxValue);
	}
	
	public static void bruteForce(int index, int[] weights, int[] values, int weight, int value, int numObjects, int maxWeights) {
		if (index == numObjects) {
			if (weight <= maxWeights && value > maxValue) {
				maxValue = value;
			}
			return;
		}
//		To make it efficient a little bit
//		if (weight + weights[index] <= maxWeights) {
//			bruteForce(index + 1, weights, values, weight + weights[index], value + values[index], numObjects, maxWeights);
//		}
		bruteForce(index + 1, weights, values, weight + weights[index], value + values[index], numObjects, maxWeights);
		bruteForce(index + 1, weights, values, weight, value, numObjects, maxWeights);
	}
}
