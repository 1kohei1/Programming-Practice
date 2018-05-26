import java.util.*;

// ARC 98-C
// https://beta.atcoder.jp/contests/arc098/tasks/arc098_a

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int N = in.nextInt();
		char[] c = in.next().toCharArray();
		
		int[][] arr1 = new int[N][2];
		int[][] arr2 = new int[N][2];
		
		for (int i = 0; i < N; i++) {
			if (c[i] == 'W') {
				if (i == 0) {
					arr1[i][0]++;	
				} else {
					arr1[i][0] = arr1[i - 1][0] + 1;
					arr1[i][1] = arr1[i - 1][1];
				}
			} else {
				if (i == 0) {
					arr1[i][1]++;	
				} else {
					arr1[i][0] = arr1[i - 1][0];
					arr1[i][1] = arr1[i - 1][1] + 1;
				}
			}
		}
		
		for (int i = N - 1; i >= 0; i--) {
			if (c[i] == 'W') {
				if (i == N - 1) {
					arr2[i][0]++;
				} else {
					arr2[i][0] = arr2[i + 1][0] + 1;
					arr2[i][1] = arr2[i + 1][1]; 
				}
			} else {
				if (i == N - 1) {
					arr2[i][1]++;
				} else {
					arr2[i][0] = arr2[i + 1][0];
					arr2[i][1] = arr2[i + 1][1] + 1; 
				}
			}
		}
		
		int answer = N;
		
		for (int i = 0; i < N; i++) {
			int left = i == 0 ? 0 : arr1[i - 1][0];
			int right = i == N - 1 ? 0 : arr2[i + 1][1];
			
			answer = Math.min(answer, left + right);
		}
		
		System.out.println(answer);
	}
}