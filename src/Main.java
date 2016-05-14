import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// uva 11565

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int nt = Integer.parseInt(in.readLine());
		for (int counter = 0; counter < nt; counter++) {
			String[] s = in.readLine().split(" ");
			int a = to(s[0]);
			int b = to(s[1]);
			int c = to(s[2]);
			
			boolean solutionFound = false;
			
			for (int x = -100; x <= 100 && !solutionFound; x++) {
				for (int y = -100;y <= 100 && !solutionFound; y++) {
					for (int z = -100;z <= 100 && !solutionFound; z++) {
						if (x != y && y != z && x != z && x + y + z == a && x * y * z == b && x * x + y * y + z * z == c) {
							solutionFound = true;
							System.out.printf("%d %d %d\n", x, y, z);
						}
					}
				}
			}
			
			if (!solutionFound) {
				System.out.println("No solution.");
			}
		}
	}
	
	public static int to(String s) {
		return Integer.parseInt(s);
	}
}