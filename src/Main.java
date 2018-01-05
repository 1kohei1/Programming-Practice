import java.util.*;

// ABC 64-C
// https://beta.atcoder.jp/contests/abc064/tasks/abc064_c

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int N = in.nextInt();
		int[] nums = new int[9];
		
		for (int i = 0; i < N; i++) {
			int a = in.nextInt();
			if (a >= 3200) {
				nums[8]++;
			} else {
				nums[a / 400]++;
			}
		}
		
		int count = 0;
		for (int i = 0; i < 8; i++) {
			if (nums[i] > 0) {
				count++;
			}
		}
		int min = count;
		int max = count;
		if (min == 0) {
			min = 1;
		}
		if (nums[8] > 0) {
			max = count + nums[8];
		}
		System.out.printf("%d %d\n", min, max);
	}
}
