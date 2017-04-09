import java.util.*;
import java.awt.Point;

// ABC 5-C
// http://abc005.contest.atcoder.jp/tasks/abc005_3
 
public class Main {
	
	public static void main (String[] args) throws java.lang.Exception {
		Scanner in = new Scanner(System.in);

		int t = in.nextInt();
		int n = in.nextInt();
		ArrayList<Integer> takos = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			takos.add(in.nextInt());
		}
		int m = in.nextInt();
		ArrayList<Integer> customers = new ArrayList<Integer>();
		for (int i = 0; i < m; i++) {
			customers.add(in.nextInt());
		}
		
		boolean isPossible = true;
		
		while (customers.size() > 0 && isPossible) {
			int time = customers.remove(0);
			
			int start = time - t;
			boolean isTakoyakiSold = false;
			while (start <= time && !isTakoyakiSold) {
				if (takos.contains(start)) {
					int index = takos.indexOf(start);
					takos.remove(index);
					isTakoyakiSold = true;
				}
				start++;
			}
			isPossible = isTakoyakiSold;
		}
		
		System.out.println(isPossible ? "yes" : "no");
	}
}
