import java.util.Scanner;

public class bcount {

	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		
		int n = scanner.nextInt();
		int q = scanner.nextInt();
		int[][] cow = new int[n + 1][3];
		
		for (int i = 1; i <= n; i++) {
			cow[i][0] = cow[i - 1][0];
			cow[i][1] = cow[i - 1][1];
			cow[i][2] = cow[i - 1][2];
			
			int breed = scanner.nextInt() - 1;
			
			cow[i][breed]++;
		}
		
		for (int i = 0; i < q; i++) {
			int start = scanner.nextInt() - 1;
			int end = scanner.nextInt();
			
			System.out.printf("%d %d %d\n", cow[end][0] - cow[start][0], cow[end][1] - cow[start][1], cow[end][2] - cow[start][2]);
		}
	}
/*
6 3
2
1
1
3
2
1
1 6
3 3
2 4
 */
}
