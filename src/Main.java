import java.util.*;

// AGC 22-A
// https://beta.atcoder.jp/contests/agc022

public class Main {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		String s = in.next();
		
		if (s.equals("zyxwvutsrqponmlkjihgfedcba")) {
			System.out.println(-1);
		} else {
			int[] count = new int[26];
			for (int i = 0; i < s.length(); i++) {
				count[s.charAt(i) - 'a'] += 1;
			}
			
			int index = -1;
			for (int i = 0; i < count.length; i++) {
				if (count[i] == 0) {
					index = i;
					break;
				}
			}
			
			if (index >= 0) {
				System.out.printf("%s%c\n", s, index + 'a');
			} else {
				count = new int[26];
				int stringTill = 0;
				char tailingCharacter = ' ';
				boolean answerFound = false;

				for (int i = s.length() - 1; i >= 0 && !answerFound; i--) {
					int cindex = s.charAt(i) - 'a';
					for (int j = cindex + 1; j < 26; j++) {
						if (count[j] > 0) {
							tailingCharacter = (char) (j + 'a');
							stringTill = i;
							answerFound = true;
							break;
						}
					}
					count[cindex] += 1;
				}
				
				for (int i = 0; i < stringTill; i++) {
					System.out.printf("%c", s.charAt(i));
				}
				System.out.printf("%c\n", tailingCharacter);
			}
		}
	}
}