import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

// uva 441

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String[] s = in.readLine().split(" ");
		int x = Integer.parseInt(s[0]);
		
		boolean isFirst = true;
		
		while (x != 0) {
			
			if (!isFirst) {
				System.out.println();
			}
			
			int[] nums = new int[x];
			for (int i = 0; i < x; i++) {
				nums[i] = Integer.parseInt(s[i + 1]);
	 		}

			for (int i = 0; i <= x - 6; i++) {
				for (int j = i + 1; j <= x - 5; j++) {
					for (int k = j + 1; k <= x - 4; k++) {
						for (int l = k + 1; l <= x - 3; l++) {
							for (int m = l + 1; m <= x - 2; m++) {
								for (int n = m + 1; n <= x - 1; n++) {
									System.out.printf("%d %d %d %d %d %d\n", nums[i], nums[j], nums[k], nums[l], nums[m], nums[n]);
								}
							}
						}
					}
				}
			}
			
			s = in.readLine().split(" ");
			x = Integer.parseInt(s[0]);
			isFirst = false;
		}
	}
}