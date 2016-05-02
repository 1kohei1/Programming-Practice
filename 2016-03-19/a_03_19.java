import java.util.Scanner;

public class a_03_19 {

	static int size = 20;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int tests = sc.nextInt();
		for (int c = 0; c < tests; c++) {
			int k = sc.nextInt();
			int[] nums = new int[size];
			
			for (int i = 0; i < size; i++) {
				nums[i] = sc.nextInt();
			}
			
			int answer = 0;

			for (int i = 1; i < size; i++) {
				int j = i - 1;
				while (j >= 0 && nums[j] > nums[j + 1]) {
					int temp = nums[j + 1];
					nums[j + 1] = nums[j];
					nums[j] = temp;
					answer++;
					j--;
				}
			}
						
			System.out.printf("%d %d\n", k, answer);
		}
	}
/*
4
1 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19
2 19 18 17 16 15 14 13 12 11 10 9 8 7 6 5 4 3 2 1 0
3 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 0
4 18 17 16 15 14 13 12 11 10 9 8 7 6 5 4 3 2 1 0 19
 */
}
