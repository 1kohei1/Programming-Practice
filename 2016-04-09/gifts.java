import java.util.Scanner;

public class gifts {

	static int n;
	static int[] nums;
	static int[] sums;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
				
		int nt = scanner.nextInt();
		for (int counter = 1; counter <= nt; counter++) {
			n = scanner.nextInt();
			
			nums = new int[n];
			sums = new int[n];
			for (int i = 0; i < n; i++) {
				nums[i] = scanner.nextInt();
				if (i == 0) {
					sums[i] = nums[i];
				} else {
					sums[i] = sums[i - 1] + nums[i];
				}
			}
			
			if (n == 1) {
				System.out.printf("Case %d: %d\n", counter, 0);
				continue;
			}
			
			int answer = 0;
			for (int i = 0; i < n; i++) {
				answer = Math.max(answer, partition(i));
			}
			System.out.printf("Case %d: %d\n", counter, answer);
		}
	}
	
	public static int partition(int index) {
		int[] left = new int[index + 1];
		int[] right = new int[n - index - 1];
		
		for (int i = 0; i <= index; i++) {
			left[i] = nums[i];
		}
		for (int i = index + 1; i < n; i++) {
			right[i - index - 1] = nums[i];
		}
		
		int leftSum = sums[index];
		int rightSum = sums[n - 1] - sums[index];
		
		return solve(left, right, leftSum, rightSum, index);
	}
	
	public static int solve(int[] left, int[] right, int leftSum, int rightSum, int index) {
		boolean[][] leftdp = new boolean[leftSum + 1][left.length + 1];
		boolean[][] rightdp = new boolean[rightSum + 1][right.length + 1];
		
		leftdp[0][0] = true;
		rightdp[0][0] = true;
		
		for (int i = 0; i < left.length; i++) {
			for (int j = leftSum; j >= left[i]; j--) {
				for (int k = left.length; k > 0; k--) {
					if (leftdp[j - left[i]][k - 1]) {
						leftdp[j][k] = true;
					}
				}
			}
		}
		
		for (int i = 0; i < right.length; i++) {
			for (int j = rightSum; j >= right[i]; j--) {
				for (int k = right.length; k > 0; k--) {
					if (rightdp[j - right[i]][k - 1]) {
						rightdp[j][k] = true;
					}
				}
			}
		}
		
		int max = Math.min(leftSum, rightSum);
		int countMax = Math.min(left.length, right.length) + 1;

		for (int i = max; i > 0; i--) {
			for (int j = 0; j < countMax; j++) {
				if (leftdp[i][j] && rightdp[i][j]) {
					return i;
				}
			}
		}
		
		return 0;
	}
	
	public static void printLeftRight(int[] left, int[] right, int leftSum, int rightSum) {
		System.out.printf("leftSum: %d, rightSum: %d\n", leftSum, rightSum);
		System.out.printf("left: ");
		for (int i = 0; i < left.length; i++) {
			System.out.printf("%d ", left[i]);
		}
		System.out.printf("\nright: ");
		for (int i = 0; i < right.length; i++) {
			System.out.printf("%d ", right[i]);
		}
		System.out.println();
	}
/*
5
2
5 5
4
3 8 3 2
4
8 4 5 7
5 
1 2 3 4 5

1
5
1 0 2 0 1

*/
	public static void printBoolean2D(boolean[][] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.printf("%d: ", i);
			for (int j = 0; j < a[i].length; j++) {
				System.out.printf("%s ", a[i][j] ? "T" : "F");
			}
			System.out.println();
		}
	}
}
