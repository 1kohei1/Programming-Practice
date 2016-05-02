import java.util.ArrayList;
import java.util.Scanner;

public class names {

	static ArrayList<Character> vowels;
	static ArrayList<String> names;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		vowels = new ArrayList<Character>();
		vowels.add('A');
		vowels.add('I');
		vowels.add('U');
		vowels.add('E');
		vowels.add('O');
		
		int n = scanner.nextInt();
		for (int i = 1; i <= n; i++) {
			names = new ArrayList<String>();
			String s1 = scanner.next();
			String s2 = scanner.next();
			
			solve(s1, s2);
			solve(s2, s1);
			names.sort(null);
			
			System.out.printf("Couple #%d: %d possible names\n", i, names.size());
			for (int j = 0; j < names.size(); j++) {
				System.out.println(names.get(j));
			}
		}
	}
/*
2
JAMES MARY
ABE JO
 */
	public static void solve(String s1, String s2) {
		ArrayList<String> s2Parsed = new ArrayList<String>();
		
		while (s2.length() > 1) {
			s2 = s2.substring(1);
			s2Parsed.add(s2);
		}
		
		while (s1.length() > 1) {
			s1 = s1.substring(0, s1.length() - 1);
			boolean isEndVowel = vowels.indexOf(s1.charAt(s1.length() - 1)) >= 0;
			
			for (int i = 0; i < s2Parsed.size(); i++) {
				String ss2 = s2Parsed.get(i);
				boolean shouldAdd = (isEndVowel && vowels.indexOf(ss2.charAt(0)) == -1) || (!isEndVowel && vowels.indexOf(ss2.charAt(0)) >= 0);
				
				if (shouldAdd && names.indexOf(s1 + ss2) == -1) {
					names.add(s1 + ss2);
				}
			}
		}
	}
}
