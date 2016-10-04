import java.util.HashMap;
import java.util.Scanner;

public class dnaSequence {

	// This is my solution for codefights challenge: https://codefights.com/challenge/fYqpnHtuii9QwwTSt/main?utm_source=featuredChallenge&utm_medium=email&utm_campaign=email_notification
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int[][] nums = new int[10*10*10*10*10][4];
		
		while (true) {
			String s = in.nextLine();
			
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			String key = "0_0_0_0";
			map.put(key,  1);
			
			int[] counts = new int[4];
			int answer = 0;

			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				
				if (c == 'A') {
					counts[0]++;
				} else if (c == 'C') {
					counts[1]++;
				} else if (c == 'G') {
					counts[2]++;
				} else if (c == 'T') {
					counts[3]++;
				}
				int max = Math.max(
						Math.max(counts[0], counts[1]),
						Math.max(counts[2], counts[3])
					);
				key = (max - counts[0]) + "_" + (max - counts[1]) + "_" + (max - counts[2]) + "_" + (max - counts[3]);
				
				if (map.containsKey(key)) {
					answer += map.get(key);
					map.put(key, map.get(key) + 1);
				} else {
					map.put(key, 1);
				}
			}
			
			System.out.println(answer);
		}
	}

}
