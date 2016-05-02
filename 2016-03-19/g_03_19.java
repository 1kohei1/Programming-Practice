import java.util.Scanner;

public class g_03_19 {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int numTests = scanner.nextInt();
		for (int cc = 0; cc < numTests; cc++) {
			int k = scanner.nextInt();
			
			int x = scanner.nextInt();
			int y = scanner.nextInt();
			
			if (y > x) {
				System.out.printf("%d 2 %d %d\n", k, x, y);
			} else if (y <= 3) {
				System.out.printf("%d NO PATH\n", k);
			} else {
				int[] nums = new int[6];
				nums[0] = 1;
				nums[1] = 2;
				nums[2] = 3;
				
				int a, b, c;
				a = 4;
				b = x + 2;
				c = y + a - 2;
				
				while (a < b) {
					c = y + a - 2;
					if (c > b) { 
						break;
					}
					a++;
				}
				
				if (a == b) {
					System.out.printf("%d NO PATH\n", k);
					continue;
				}
				
				nums[3] = a;
				nums[4] = b;
				nums[5] = c;
				
				
				System.out.printf("%d 6 ", k);
				for (int i = 0; i < nums.length; i++) {
					System.out.printf("%d%s", nums[i], i == 5 ? "" : " ");
				}
				System.out.println();
			}
		}
	}
/*
5
1 1 1
2 3 5
3 8 4
4 6 4
5 8932 2673

1 1 8932 2673
 */
}
