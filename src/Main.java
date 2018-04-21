import java.util.*;

// ARC 96-D
// https://beta.atcoder.jp/contests/arc096/tasks/arc096_a

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		long A = in.nextInt();
		long B = in.nextInt();
		long C = in.nextInt();
		long X = in.nextInt();
		long Y = in.nextInt();
		
		long a = A * X + B * Y;
		long b = Math.min(X, Y) * 2 * C + (X - Math.min(X, Y)) * A + (Y - Math.min(X, Y)) * B;
		long c = Math.max(X, Y) * 2 * C;
		
		System.out.println(Math.min(Math.min(a, b), c));
	}
}
