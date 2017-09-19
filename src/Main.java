import java.util.*;

// CF 435-A
// http://codeforces.com/contest/862/problem/A

public class Main {
	
	public static void main (String[] args) throws InterruptedException {
		Scanner in = new Scanner(System.in);
		
		int n = in.nextInt();
		int x = in.nextInt();
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		for (int i = 0; i < n; i++) {
			int a = in.nextInt();
			map.put(a,  1);
		}
		
		int numOp = 0;
		for (int i = 0; i < x; i++) {
			if (!map.containsKey(i)) {
				numOp++;
			}
		}
		if (map.containsKey(x)) {
			numOp++;
		}
		
		System.out.println(numOp);
	}
}
