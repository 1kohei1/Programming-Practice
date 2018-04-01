import java.util.*;

// AGC 22-A
// https://beta.atcoder.jp/contests/agc022

public class Main {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		char[] S = in.next().toCharArray();
		char[] T = in.next().toCharArray();
		
		ArrayList<String> answers = new ArrayList<String>();
		
		for (int i = 0; i < S.length; i++) {
			if (i + T.length > S.length) {
				continue;
			}
			
			char[] answer = S.clone();
			
			for (int j = 0; j < i; j++) {
				if (answer[j] == '?') {
					answer[j] = 'a';
				}
			}
			
			boolean isAnswer = true;
			
			for (int j = 0; j < T.length; j++) {
				int index = i + j;
				
				if (answer[index] == '?' || answer[index] == T[j]) {
					answer[index] = T[j];
				} else {
					isAnswer = false;
				}
			}
			
			for (int j = i + T.length; j < S.length; j++) {
				if (answer[j] == '?') {
					answer[j] = 'a';
				}
			}
			
			if (isAnswer) {
				answers.add(new String(answer));
			}
		}
		
		answers.sort(null);
		
		if (answers.size() > 0) {
			System.out.println(answers.get(0));
		} else {
			System.out.println("UNRESTORABLE");
		}
	}
}