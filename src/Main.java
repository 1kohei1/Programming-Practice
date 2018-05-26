import java.util.*;

// ARC 98-D
// https://beta.atcoder.jp/contests/arc098/tasks/arc098_b

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int N = in.nextInt();
		long[] arr = new long[N];
		
		for (int i = 0; i < N; i++) {
			arr[i] = in.nextLong();
		}
		
		long answer = 0;
		long temp = 0;
		int left = 0;
		int right = 0;
		
		while (left < N) {
			if (left == right) {
				temp = arr[right];
				answer++;
				right++;
			} else if (right < N && (temp & arr[right]) == 0) {
				temp += arr[right];
				answer++;
				right++;
			} else {
				temp -= arr[left];
				left++;
//				int num = right - left;
//				answer += num * (num - 1) / 2;
				answer += right - left;
			}
		}
		
//		answer += N;
		
		System.out.println(answer);
	}
}