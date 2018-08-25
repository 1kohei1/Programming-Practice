import java.util.*;

// ARC 101-A
// https://beta.atcoder.jp/contests/arc101/tasks/arc101_a

public class Main {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int N = in.nextInt();
		int K = in.nextInt();
		
		ArrayList<Long> left = new ArrayList<Long>();
		ArrayList<Long> right = new ArrayList<Long>();
		
		for (int i = 0; i < N; i++) {
			long temp = in.nextLong();
			// Include 0 in left
			if (temp <= 0) {
				left.add(temp);
			} else {
				right.add(temp);
			}
		}
		
		long temp = 0;
		long answer = Long.MAX_VALUE;

		// 左へ行く
		for (int i = left.size() - 1; i >= 0; i--) {
			int count = left.size() - i;
			temp = Math.abs(left.get(i));
			
			if (count < K && count + right.size() >= K) {
				int rightIndex = K - count - 1;
				temp *= 2;
//				System.out.printf("i: %d, left count: %d, right index: %d\n", i, count, rightIndex, temp);
				temp += right.get(rightIndex);
				answer = Math.min(answer, temp);
			} else if (count == K) {
				answer = Math.min(answer, temp);
			}
		}
		
		// 右へ行く
		for (int i = 0; i < right.size(); i++) {
			int count = i + 1;
			temp = right.get(i);
			
			if (count < K && count + left.size() >= K) {
				int leftIndex = left.size() - (K - count);
				temp *= 2;
//				System.out.printf("i: %d, right count: %d, left index: %d\n", i, count, leftIndex, temp);
				temp += Math.abs(left.get(leftIndex));
				answer = Math.min(answer, temp);
			} else if (count == K) {
				answer = Math.min(answer, temp);
			}
		}
		
		System.out.println(answer);
    }
}
