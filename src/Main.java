import java.util.*;

// CODEFES 2017.qualA-C
// http://code-festival-2017-quala.contest.atcoder.jp/tasks/code_festival_2017_quala_c

public class Main {
	
	public static void main (String[] args) throws InterruptedException {
		Scanner in = new Scanner(System.in);
		
		int H = in.nextInt();
		int W = in.nextInt();
		
		int[] freq = new int[26];
		for (int i = 0; i < H; i++) {
			char[] c = in.next().toCharArray();
			for (int j = 0; j < c.length; j++) {
				freq[c[j] - 'a']++;
			}
		}
		
		if (H == 1 || W == 1) {
			int numRemaining = 0;
			int index = 0;
			while (index < 26) {
				if (freq[index] >= 2) {
					freq[index] -= 2;
				} else {
					numRemaining += freq[index];
					index++;
				}
			}
			
			System.out.println(numRemaining == Math.max(H,  W) % 2 ? "Yes" : "No");
		} else {
			int num2Ok = 0;
			int num1Ok = 0;
			
			if (H % 2 == 1) {
				num2Ok += W / 2;
			}
			if (W % 2 == 1) {
				num2Ok += H / 2;
			}
			if (H % 2 == 1 && W % 2 == 1) {
				num1Ok = 1;
			}
			
//			System.out.printf("num2Ok: %d, num1Ok: %d\n", num2Ok, num1Ok);
			
			int index = 0;
			boolean answer = true;
			while (index < 26 && answer) {
//				System.out.printf("index: %d, freq[index]: %d\n", index, freq[index]);
				if (freq[index] >= 4) {
					freq[index] -= 4;
				} else if (freq[index] == 2 && num2Ok > 0) {
					freq[index] -= 2;
					num2Ok--;
					index++;
				} else if (freq[index] == 1 && num1Ok > 0) {
					freq[index] -= 1;
					num1Ok--;
					index++;
				} else if (freq[index] == 0) {
					index++;
				} else {
					answer = false;
				}
			}
			System.out.println(answer ? "Yes" : "No");
		}
	}
	
}
