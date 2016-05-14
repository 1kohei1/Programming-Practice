import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

// uva 725

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(in.readLine());
		boolean isFirst = true;
		
		while (n != 0) {
			
			if (!isFirst) {
				System.out.println();
			}
			
			int count = 0;
			for (int fghij = 1234; fghij * n < 100000; fghij++) {
				int abcde = fghij * n;
				
				if (all0to9(abcde, fghij)) {
					count++;
					System.out.printf("%d / %s%d = %d\n", abcde, fghij < 10000 ? "0" : "", fghij, n);
				}
			}
			
			if (count == 0) {
				System.out.printf("There are no solutions for %d.\n", n);
			}
			
			n = Integer.parseInt(in.readLine());
			isFirst = false;
		}
	}

	public static boolean all0to9(int abcde, int fghij) {
		TreeSet<Integer> nums = new TreeSet<Integer>();
		
		if (abcde < 10000 && fghij < 10000) {
			return false;
		}
		if (fghij < 10000) {
			nums.add(0);
		}
		
		while (fghij > 0) {
			int onethDigit = fghij % 10;
			if (nums.contains(onethDigit)) {
				return false;
			} else {
				nums.add(onethDigit);
			}
			fghij /= 10;
		}
		
		while (abcde > 0) {
			int onethDigit = abcde % 10;
			if (nums.contains(onethDigit)) {
				return false;
			} else {
				nums.add(onethDigit);
			}
			abcde /= 10;
		}
		
		return true;
	}
}