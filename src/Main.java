import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;

// uva 12356

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		while (true) {
			int numSoldiers = in.nextInt();
			if (numSoldiers == 0) {
				break;
			}
			int numReports = in.nextInt();
			
			TreeSet<Integer> tree = new TreeSet<Integer>();
			for (int i = 1; i <= numSoldiers; i++) {
				tree.add(i);
			}
			
			for (int i = 0; i < numReports; i++) {
				int left = in.nextInt();
				int right = in.nextInt();
				
				for (int j = left; j <= right; j++) {
					tree.remove(j);
				}
				
				int answerLeft = 0;
				int answerRight = 0;
				
				if (tree.lower(left) == null) {
					answerLeft = -1;
				} else {
					answerLeft = tree.lower(left);
				}
				
				if (tree.higher(right) == null) {
					answerRight = -1;
				} else {
					answerRight = tree.higher(right);
				}
				System.out.println((answerLeft == -1 ? "*" : answerLeft) + " " + (answerRight == -1 ? "*" : answerRight));
			}
			System.out.println("-");
		}
	}
}
