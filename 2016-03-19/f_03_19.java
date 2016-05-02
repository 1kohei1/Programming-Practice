import java.util.Scanner;

public class f_03_19 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int tests = sc.nextInt();
		for (int c = 0; c < tests; c++) {
			int k = sc.nextInt();
			
			String s = sc.next();
			int p = Integer.parseInt(s.split("/")[0]);
			int q = Integer.parseInt(s.split("/")[1]);
			
			if (q == 1) {
				System.out.printf("%d %d/%d\n", k, 1, p + 1);
				continue;
			}
			
			int count = 0;
			while (p > q) {
				p = p - q;
				count++;
			}
			// parent
			q = q - p;
			
			// right
			p += q;
			
			for (int i = 0; i < count; i++) {
				q += p;
			}
			System.out.printf("%d %d/%d\n", k, p, q);
		}
	}
}
/*
5
1 1/1
2 1/3
3 5/2
4 2178309/1346269
5 1/10000000
*/