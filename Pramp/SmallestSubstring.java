import java.util.Scanner;

public class SmallestSubstring {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		while (true) {
			String str1 = in.nextLine();
			String str2 = in.nextLine();
			System.out.println(solve(str1, str2));
		}
	}

	// Find minimum length of substring in str2 such that it contains all characters in str1
	// Assume all characters are lower case
	public static int solve(String str1, String str2) {
		if (str1 == null || str2 == null || str2.length() < str1.length()) {
			return 0;
		}
		// Count how many characters makes str1
		int[] str1CharCount = new int[26];
		for (int i = 0; i < str1.length(); i++) {
			str1CharCount[str1.charAt(i) - 'a']++;
		}
		int[] foundChars = new int[26];
		
		// start: the beginning index of such substring
		int start = 0;
		// end: the end index of such substring
		int end = 0;
		
		int count = 0;
		
		int answer = Integer.MAX_VALUE;
		
		for (; end < str2.length(); end++) {
			int index = str2.charAt(end) - 'a';
			foundChars[index]++;
			if (foundChars[index] <= str1CharCount[index]) {
				count++;
			}
			// We found an answer!
			if (count == str1.length()) {
				// Increment start
				index = str2.charAt(start) - 'a';
				while (foundChars[index] > str1CharCount[index]) {
					start++;
					foundChars[index]--;
					index = str2.charAt(start) - 'a';
				}
				answer = Math.min(answer, end - start + 1);
			}
		}
		System.out.println();
		
		return answer;
	}
}
