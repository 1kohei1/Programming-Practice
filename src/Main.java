import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

// uva 1047

public class Main {
	
	static int n;
	static int m;
	static int[] nums;
	
	static HashMap<Integer, Integer> intersections;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int counter = 1;
		n = in.nextInt();
		m = in.nextInt();
		
		while (n != 0 && m != 0) {
			nums = new int[n];
			intersections = new HashMap<Integer, Integer>();
			
			for (int i = 0; i < n; i++) {
				nums[i] = in.nextInt();
			}
			
			int a = in.nextInt();
			for (int i = 0; i < a; i++) {
				int t = in.nextInt();
				
				int key = 0;
				for (int j = 0; j < t; j++) {
					key |= 1 << (in.nextInt() - 1);
				}
				
				int numPeople = in.nextInt();
				intersections.put(key, numPeople);
			}
			
			// Try all possible combinations
			for (int i = 1; i < 1 << n; i++) {
				int count = 0;
				for (int j = 0; j < n; j++) {
					if ((i & (1 << j)) != 0) {
						count++;
					}
					if (count > m) {
						break;
					}
				}
				
				if (count != m) {
					continue;
				}
				
				int totalPeople = 0;
				for (int j = 0; j < n; j++) {
					if ((i & (1 << j)) != 0) {
						totalPeople += nums[j];
					}
				}
				
				// Then, subtract number that overlaps.
				
			}
			
			counter++;
			n = in.nextInt();
			m = in.nextInt();
		}
	}
}