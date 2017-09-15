import java.util.*;

// ARC 81-D
// https://beta.atcoder.jp/contests/arc081/tasks/arc081_b

public class Main {
	
	public static void main (String[] args) {
		Scanner in = new Scanner(System.in);
		
		int N = in.nextInt();
		char[][] map = new char[2][52];
		
		map[0] = in.next().toCharArray();
		map[1] = in.next().toCharArray();
		
		long answer = 1;
		long MOD = 1000000007;
		int i = 0;
		
		// Placed vertically
		if (map[0][0] == map[1][0]) {
			answer = 3;
			i = 1;
		}
		// Placed horizontally
		else {
			answer = 6;
			i = 2;
		}
		
		for (; i < N; i++) {
			// Placed vertically
			if (map[0][i] == map[1][i]) {
				// Previous domino placed vertically
				if (map[0][i - 1] == map[1][i - 1]) {
					answer *= 2;
				}
				// Previous domino placed horizontally
				else {
					answer *= 1;
				}
			}
			// Placed horizontally
			else {
				// Previous domino placed vertically
				if (map[0][i - 1] == map[1][i - 1]) {
					answer *= 2;
				}
				// Previous domino placed horizontally
				else {
					answer *= 3;
				}
				i++;
			}
			answer %= MOD;
		}
		
		System.out.println(answer);
	}
}
