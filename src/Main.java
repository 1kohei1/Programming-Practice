import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// uva 12455

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int nt = to(in.readLine());
		for (int counter = 0; counter < nt; counter++) {
			int n = to(in.readLine());
			int p = to(in.readLine());
			int[] bar = new int[p];
			
			String[] s = in.readLine().split(" ");
			for (int i = 0; i < p; i++) {
				bar[i] = to(s[i]);
			}
			
			boolean[] answer = new boolean[n + 1];
			answer[0] = true;
			for (int i = 0; i < p; i++) {
				for (int j = n; j >= bar[i]; j--) {
					if (answer[j - bar[i]]) {
						answer[j] = true;
					}
				}
			}
			
			System.out.println(answer[n] ? "YES" : "NO");
		}
	}
	
	public static int to(String s) {
		return Integer.parseInt(s);
	}
}
