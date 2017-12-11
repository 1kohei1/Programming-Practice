import java.util.*;

// ARC 86-D
// https://beta.atcoder.jp/contests/arc086/tasks/arc086_b

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int N = in.nextInt();
		long[] nums = new long[N];
		
		int minIndex = 0;
		int maxIndex = 0;
		
		for (int i = 0; i < N; i++) {
			nums[i] = in.nextLong();
			if (nums[minIndex] > nums[i]) {
				minIndex = i;
			}
			if (nums[maxIndex] < nums[i]) {
				maxIndex = i;
			}
		}
		
		int useIndex = -1;
		boolean isNegative = false;
		
		if (Math.abs(nums[minIndex]) < Math.abs(nums[maxIndex])) {
			useIndex = maxIndex;
		} else {
			useIndex = minIndex;
		}

		if (nums[useIndex] < 0) {
			isNegative = true;
		}
		
		ArrayList<Integer> arr = new ArrayList<Integer>();
		
		if (isNegative) {
			for (int i = N - 1; i >= 1; i--) {
				while (nums[i - 1] > nums[i]) {
					nums[i - 1] += nums[useIndex];
					arr.add(useIndex + 1);
					arr.add(i - 1 + 1);
				}
				if (nums[i - 1] < nums[useIndex]) {
					useIndex = i - 1;
				}
			}
		} else {
			for (int i = 0; i < N - 1; i++) {
				while (nums[i] > nums[i + 1]) {
					nums[i + 1] += nums[useIndex];
					arr.add(useIndex + 1);
					arr.add(i + 1 + 1);
				}
				if (nums[useIndex] < nums[i + 1]) {
					useIndex = i + 1;
				}
			}
		}
		
		System.out.println(arr.size() / 2);
		for (int i = 0; i < arr.size(); i += 2) {
			System.out.printf("%d %d\n", arr.get(i), arr.get(i + 1));
		}
	}
}
