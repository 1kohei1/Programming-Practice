import java.util.Scanner;

public class Multiply {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int counter = 1;
//		test();
		while (true) {
			int n1 = scanner.nextInt();
			int n2 = scanner.nextInt();
			
			if (n1 == 0 && n2 == 0) {
				break;
			}
			System.out.printf("Problem %d\n", counter);
			
			int n1Length = numLength(n1);
			int n2Length = numLength(n2);
			long product = (long) n1 * (long) n2;
			
			int[] n2Array = breakDownNum(n2, new int[n2Length], 0, n2Length);
			long[] answers = new long[n2Length];
			for (int i = n2Length - 1; i >= 0; i--) {
				answers[i] = n1 * n2Array[i];
			}
			
			int productLength = numLength(product);
			int nonZeroAnswers = nonZeroAnswers(answers);
			int trailingZero = 0;
			
			// Print answers
			printN1timesn2(n1, n2, n1Length, n2Length, productLength);
			
			if (nonZeroAnswers != 1) {
				for (int i = n2Length - 1; i >= 0; i--) {
					int endSpace = n2Length - 1 - i;
					if (answers[i] != 0) {
						for (int j = 0; j < productLength - endSpace - numLength(answers[i]); j++) {
							System.out.printf(" ");
						}
						System.out.printf("%d", answers[i]);
						for (int j = 0; j < trailingZero; j++) {
							System.out.printf("0");
						}
						trailingZero = 0;
						System.out.println();
					} else {
						trailingZero++;
					}
					endSpace++;
				}
				for (int i = 0; i < productLength; i++) {
					System.out.printf("-");
				}
				System.out.println();
			}
			
			System.out.println(product);
			counter++;
		}
	}
	
	public static void printN1timesn2(int n1, int n2, int n1Length, int n2Length, int productLength) {
		for (int i = 0; i < productLength - n1Length; i++) {
			System.out.printf(" ");
		}
		System.out.println(n1);
		for (int i = 0; i < productLength - n2Length; i++) {
			System.out.printf(" ");
		}
		System.out.println(n2);
		for (int i = 0; i < productLength; i++) {
			System.out.printf("-");
		}
		System.out.println();	
	}
	
	public static int numLength(long n) {
		if (n < 10) {
			return 1;
		}
		return 1 + numLength(n / 10);
	}
	
	public static int nonZeroAnswers(long[] answers) {
		int count = 0;
		for (int i = 0; i < answers.length; i++) {
			if (answers[i] != 0) {
				count++;
			}
		}
		return count;
	}

	// Break down n into num array, such as 123 -> [1, 2, 3]
	public static int[] breakDownNum(int n, int[] num, int index, int nLength) {
		if (index == nLength) {
			return num;
		}
		num[nLength - index - 1] = n % 10;
		return breakDownNum(n / 10, num, index + 1, nLength);
	}
	
	public static void test() {
//		int[] nums = new int[]{0, 1, 5, 12, 156, 136, 1980};
//		for (int i = 0; i < nums.length; i++) {
//			int n = nums[i];
//			int nLength = numLength(n);
//			int[] nArray = breakDownNum(n, new int[nLength], 0, nLength);
//			for (int j = 0; j < nLength; j++) {
//				System.out.printf("%d ", nArray[j]);
//			}
//			System.out.println();
//		}
		System.out.println(numLength(245));
		System.out.println(numLength(2452));
		System.out.println(numLength(24528));
		System.out.println(numLength(245286));
		System.out.println(numLength(2452896));
		System.out.println(numLength(2452896));
	}
}
