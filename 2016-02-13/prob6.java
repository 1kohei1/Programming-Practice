import java.util.Scanner;
import java.util.HashMap;

public class prob6 {

	static HashMap<String, Integer> memo;
	static int MAX = 4;
	
	public static void main(String[] args) {
		
		// i = 3
//		String s = "abcdefg";
//		int i = s.length() - 1;
//		// Get abc, and get defg
//		System.out.println(s.substring(0, i + 1));
//		System.out.println(s.substring(i + 1));
		
		Scanner scanner = new Scanner(System.in);
		memo = new HashMap<String, Integer>();
		
		initializeMemo();
		
		String s = scanner.nextLine();
		while (!s.equals("#")) {
			System.out.println(solve(s));
			s = scanner.nextLine();
		}
	}
/*
.-
....
.-...
#
 */
	
	public static int solve(String s) {
		if (s.length() == 0) {
			return 0;
		}
		if (memo.containsKey(s)) {
			return memo.get(s);
		}
		int count = 0;
//		for (int i = s.length() - 1; i > 0; i--) {
//			String ss = s.substring(0, i + 1);
//			if (memo.containsKey(ss)) {
//				count += memo.get(ss) + solve(s.substring(i + 1));
//			}
//		}
		
		for (int i = 0; i < MAX && i < s.length(); i++) {
			String ss = s.substring(i + 1);
			if (memo.containsKey(ss)) {
				count += memo.get(ss);
				if (s.substring(i + 1).length() > 0) {
					count += solve(s.substring(i + 1));
				}
			}
		}
		
		if (count != 0) {
			memo.put(s, count);
		}
		return count;
	}
	
	public static void initializeMemo() {
		memo.put(".-", 1);
		memo.put("-...", 1);
		memo.put("-.-.", 1);
		memo.put("-..", 1);
		memo.put(".", 1);
		memo.put("..-.", 1);
		memo.put("--.", 1);
		memo.put("....", 1);
		memo.put("..", 1);
		memo.put(".---", 1);
		memo.put("-.-", 1);
		memo.put(".-..", 1);
		memo.put("--", 1);
		memo.put("-.", 1);
		memo.put("---", 1);
		memo.put(".--.", 1);
		memo.put("--.-", 1);
		memo.put(".-.", 1);
		memo.put("...", 1);
		memo.put("-", 1);
		memo.put("..-", 1);
		memo.put("...-", 1);
		memo.put(".--", 1);
		memo.put("-..-", 1);
		memo.put("-.--", 1);
		memo.put("--..", 1);
	}
}
