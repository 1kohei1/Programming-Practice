import java.util.Scanner;
import java.util.TreeSet;
import java.util.Arrays;

public class two {

	static int[] fact2Lookup;
	static boolean[] is2Power;
	static TreeSet<Integer> numbers;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		
		fact2Lookup = new int[17];
		is2Power = new boolean[(int) Math.pow(2, 16) + 1];
		numbers = new TreeSet<Integer>();
		Arrays.fill(is2Power, false);
		
		for (int i = 0; i <= 16; i++) {
			fact2Lookup[i] = 1 << i;
			is2Power[fact2Lookup[i]] = true;
		}
		fillNumbers();
		
		int n = scanner.nextInt();
		for (int i = 0; i < n; i++) {
			int num = scanner.nextInt();
			System.out.printf("The next hyper-even after %d is %d\n", num, numbers.higher(num));
		}
	}
/*
3
32768
50
2016
 */
	public static void fillNumbers() {
		for (int i = 1; i <= 16; i++) {
			for (int j = 0; j < i; j++) {
				int subtract = fact2Lookup[i] - fact2Lookup[j];
				if (subtract < 11) {
					continue;
				}
				if (canBreakDownInto2Power(subtract)) {
					numbers.add(subtract);
				}
			}
		}
	}
	
	public static boolean canBreakDownInto2Power(int n) {
		String s = "" + n;
		for (int i = 1; i < s.length(); i++) {
			String s1 = s.substring(0, i);
			String s2 = s.substring(i);
			
			if (is2Power[Integer.parseInt(s1)] && is2Power[Integer.parseInt(s2)]) {
				return true;
			}
		}
		return false;
	}
}
