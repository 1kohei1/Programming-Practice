import java.math.BigInteger;
import java.util.Scanner;


public class work {

	static int n;
	static Worker[] w;
	static double low;
	static double high;
	
	static int permCount;
	static int[] fact;
	static int[][] perm;

	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
//		double t = 96.40460677311465;
//		double a = calculate(19, 130, t);
//		double b = calculate(20, 5, t - 1);
//		System.out.println(a);
//		System.out.println(b);
//		System.out.println(a + b);
//		t = 96.08684636030586;
//		a = calculate(19, 130, t);
//		b = calculate(20, 5, t - 1);
//		System.out.println(a);
//		System.out.println(b);
//		System.out.println(a + b);
		
		fact = new int[9];
		fact[0] = 0;
		fact[1] = 1;
		for (int i = 2; i < 9; i++) {
			fact[i] = fact[i - 1] * i;
		}

		int nt = scanner.nextInt();
		while(nt-- > 0) {
			n = scanner.nextInt();
			double target = scanner.nextDouble();
			
			w = new Worker[n];
			for (int i = 0; i < n; i++) {
				w[i] = new Worker(scanner.nextInt(), scanner.nextInt());
			}
			
			perm = new int[fact[n]][n];
			boolean[] used = new boolean[n];
			permCount = 0;
			fillPerm(0, used, new int[n]);

//			printPerm();
			
			
			double answer = Math.pow(10, 12);
			for (int i = 0; i < perm.length; i++) {
				low = 0;
				high = Math.pow(10, 12);
				solve(target, perm[i]);
				answer = Math.min(answer, (low + high) / 2);
			}
			
			System.out.println(answer);
		}
	}
	
	public static void solve(double target, int[] order) {
		for (int i = 0; i < 100; i++) {
//			System.out.printf("low: %f, high: %f\n", low, high);
			double mid = (low + high) / 2;
			
			double sum = 0;
			for (int j = 0; j < n; j++) {
				if (mid - j > 0) {
					sum += calculate(w[order[j]].a, w[order[j]].b, mid - j);
				}
			}
			if (Double.compare(target, sum) < 0) {
				high = mid;
			} else if (Double.compare(target, sum) > 0) {
				low = mid;
			} else {
//				System.out.println("Come, i: " + i);
				break;
			}
		}
	}
/*
2
1 100
17 13
2 1000
19 130
20 5

1
2 1000
19 130
20 5


1
1 1000000
1 1

 */
//	public static void solve(double target) {
//		for (int i = 0; i < 100; i++) {
//			System.out.printf("low: %f, high: %f\n", low, high);
//			double mid = (low + high) / 2;
//			
//			double allTargetSum = 0;
//			int numHigher = 0;
//			int numLower = 0;
//			
//			for (int j = 0; j < perm.length; j++) {
//				double sum = 0.0;
//				for (int k = 0; k < n; k++) {
//					if (mid - j > 0) {
//						sum += calculate(w[perm[j][k]].a, w[perm[j][k]].b, mid - j);					
//					}
//				}
//				allTargetSum += sum;
//				if (Double.compare(target, sum) < 0) {
//					numHigher++;
//				} else if (Double.compare(target, sum) > 0) {
//					numLower++;
//				}
//			}
//			
////			System.out.printf("numHigher: %d, numLower: %d\n", numHigher, numLower);
//			
//			if (numHigher > numLower) {
//				high = mid;
//			} else if (numHigher < numLower) {
//				low = mid;
//			} else {
//				System.out.println("Come i: " + i);
//				double avgTarget = allTargetSum / perm.length;
//				System.out.printf("avgTarget: %f\n", avgTarget);
//				if (Double.compare(target, avgTarget) < 0) {
//					high = avgTarget;
//				} else if (Double.compare(target, avgTarget) > 0) {
//					low = avgTarget;
//				} else {
//					System.out.println("Come i: " + i);
//					break;
//				}
//			}
//		}
//	}
	
	public static void fillPerm(int index, boolean[] used, int[] nums) {
		if (index == n) {
			perm[permCount] = nums.clone();
			permCount++;
			return;
		}
		for (int i = 0; i < n; i++) {
			if (used[i] == false) {
				nums[index] = i;
				used[i] = true;
				fillPerm(index + 1, used, nums);
				used[i] = false;
			}
		}
	}
	
	public static double calculate(int a, int b, double t) {
		return ((double) a) * Math.sqrt(t) + ((double) b) * Math.log(t + 1);
	}

	public static void printPerm() {
		for (int i = 0; i < perm.length; i++) {
			for (int j = 0; j < perm[i].length; j++) {
				System.out.printf("%d ", perm[i][j]);
			}
			System.out.println();
		}
	}
}

class Worker {
	int a;
	int b;
	
	public Worker(int a, int b) {
		this.a = a;
		this.b = b;
	}
}