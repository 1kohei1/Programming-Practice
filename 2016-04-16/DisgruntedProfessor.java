import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;

public class DisgruntedProfessor {

	static int[][] memo;
	
	static int n;
	static int[] nums;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		n = scanner.nextInt();
		nums = new int[n];
		for (int i = 0; i < n; i++) {
			nums[i] = scanner.nextInt();
		}
		
		double low = 0;
		double high = 10000;
		
		for (int i = 0; i < 100; i++) {
			double mid = (low + high) / 2;
			
			if (isPossible(mid)) {
				high = mid;
			} else {
				low = mid;
			}
		}
		System.out.printf("%.3f\n", (low + high) / 2);
	}
/*
5
5
1
7
8
2
 */
	public static boolean isPossible(double mid) {
		double[] temp = new double[n];
		for (int i = 0; i < n; i++) {
			temp[i] = nums[i] - mid;
		}
		
		double[] minSumFromLeft = new double[n];
		minSumFromLeft[0] = temp[0];
		double sum = temp[0];
		
		for (int i = 1; i < n; i++) {
			sum += temp[i];
			minSumFromLeft[i] = Math.min(sum, minSumFromLeft[i - 1]);
		}
		
		double[] minSumFromRight = new double[n];
		minSumFromRight[n - 1] = temp[n - 1];
		sum = temp[n - 1];
		
		for (int i = n - 2; i >= 0; i--) {
			sum += temp[i];
			minSumFromRight[i] = Math.min(sum, minSumFromRight[i + 1]);
		}
		
		for (int i = 0; i < n - 2; i++) {
			if (minSumFromLeft[i] + minSumFromRight[i + 2] < 1e-7) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isPossible(double mid, int leftIndex, int rightIndex, double avg, int numScore) {
//		System.out.printf("leftIndex: %d, rightIndex: %d, avg: %.3f, numScore: %d\n", leftIndex, rightIndex, avg, numScore);
		if (rightIndex - leftIndex <= 1) {
			return false;
		}
		if (Double.compare(avg, mid) < 0) {
			return true;
		}
		if (memo[leftIndex][rightIndex] != -1) {
			return memo[leftIndex][rightIndex] == 1;
		}
		
		boolean answer = isPossible(mid, leftIndex + 1, rightIndex, newAvg(avg, leftIndex, numScore), numScore + 1) ||
						 isPossible(mid, leftIndex, rightIndex - 1, newAvg(avg, rightIndex, numScore), numScore + 1);
		if (answer) {
			memo[leftIndex][rightIndex] = 1;
		} else {
			memo[leftIndex][rightIndex] = 0;
		}
		return answer;
	}
	
	public static double newAvg(double avg, int index, int numScore) {
		return (avg * numScore + nums[index]) / ((double) numScore + 1);
	}
}
