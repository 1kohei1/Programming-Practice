import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

// uva 11565

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int nt = Integer.parseInt(in.readLine());
		for (int i = 0; i < nt; i++) {
			String[] s = in.readLine().split(" ");
			int a = to(s[0]);
			int b = to(s[1]);
			int c = to(s[2]);
			
			boolean answerFound = false;
			for (int x = -100; x <= 100 && !answerFound; x++) {
				for (int y = x + 1; y <= 100 && !answerFound; y++) {
					for (int z = y + 1; z <= 100 && !answerFound; z++) {
						if (x + y + z == a && x * y * z == b && x * x + y * y + z * z == c) {
							System.out.printf("%d %d %d\n", x, y, z);
							answerFound = true;
						}
					}
				}
			}
			
			if (!answerFound) {
				System.out.println("No solution.");
			}
		}
	}
	
	public static int to(String s) {
		return Integer.parseInt(s);
	}
}