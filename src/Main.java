import java.util.*;

// ABC 85-D
// https://beta.atcoder.jp/contests/abc085/tasks/abc085_d

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int N = in.nextInt();
		int H = in.nextInt();
		
		long[] swingD = new long[N];
		long[] throwD = new long[N];
		long maxSwingD = 0;
		int maxSwingDIndex = 0;
		
		for (int i = 0; i < N; i++) {
			long sd = in.nextLong();
			long td = in.nextLong();
			
			if (maxSwingD < sd) {
				maxSwingD = sd;
				maxSwingDIndex = i;
			}
			
			swingD[i] = sd;
			throwD[i] = td;
		}

		ArrayList<Long> nums = new ArrayList<Long>();
		for (int i = 0; i < N; i++) {
			if (maxSwingD <= throwD[i]) {
				nums.add(throwD[i]);
			}
		}
		nums.sort(null);
		
		long answer = 0;
		for (int i = nums.size() - 1; i >= 0; i--) {
			H -= nums.get(i);
			answer++;
			if (H <= 0) {
				break;
			}
		}
		
		if (H <= 0) {
			System.out.println(answer);
			return;
		}
		
		answer += H / maxSwingD;
		if (H % maxSwingD != 0) {
			answer++;
		}
		System.out.println(answer);
	}
}
