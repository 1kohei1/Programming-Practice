import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class countseq {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int nt = scanner.nextInt();
		for (int counter = 0; counter < nt; counter++) {
			char[] c1 = scanner.next().toCharArray();
			char[] c2 = scanner.next().toCharArray();
			
			long[] answer = new long[c2.length + 1];
			answer[0] = 1;
			
			for (int i = 0; i < c1.length; i++) {
				for (int j = c2.length - 1; j >= 0; j--) {
					if (c1[i] == c2[j]) {
						answer[j + 1] += answer[j];
					}
				}
			}
//			for (int i = 0; i < c2.length + 1; i++) {
////				System.out.println(answer[i]);
//			}
			System.out.println(answer[c2.length]);
		}
	}
}
/*
2
engineering
nine
sallysellsseashells
sell
*/