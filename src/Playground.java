import java.util.ArrayList;
import java.util.Scanner;

public class Playground {
	
	static int[] pos;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int n = in.nextInt();
		pos = new int[n];
		
		for (int i = 0; i < n; i++) {
			int barNum = in.nextInt() - 1;
			pos[i] = barNum;
		}

		int answer = 0;
		for (int i = n - 1; i >= 0; i--) {
			answer += solve(i);
		}
		
		System.out.println(answer);
	}
	
	// Returns how many steps takes to make pos[num] = 0;
	public static int solve(int num) {
		if (pos[num] == 0) {
			return 0;
		}
		if (num == 0) {
			return 1;
		}
		// Make pos[num] = 0
		return 3;
	}
}


