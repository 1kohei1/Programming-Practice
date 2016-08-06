import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class a_05_06 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int nt = Integer.parseInt(in.readLine());
		for (int counter = 1; counter <= nt; counter++) {
			String[] s = in.readLine().split(" ");
			
			int a = Integer.parseInt(s[0]);
			int b = Integer.parseInt(s[1]);
			
			int gcm = gcm(a, b);
			int lcm = gcm * (a / gcm) * (b / gcm);
			
			System.out.printf("%d %d %d\n", counter, lcm, gcm);
		}
	}

	public static int gcm(int a, int b) {
		return a % b == 0 ? b : gcm(b, a % b);
	}
}
