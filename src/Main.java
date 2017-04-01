import java.util.*;

// ABC 42-C
// http://abc042.contest.atcoder.jp/tasks/arc058_a

public class Main {
	
	public static void main (String[] args) throws java.lang.Exception {
	    Scanner in = new Scanner(System.in);
	    
	    int n = in.nextInt();
	    int k = in.nextInt();
	    HashMap<Integer, Integer> avoid = new HashMap<Integer, Integer>();
	    
	    for (int i = 0; i < k; i++) {
	    	avoid.put(in.nextInt(), 1);
	    }
	    
	    while (!isConditionMet(avoid, n)) {
	    	n++;
	    }
	    System.out.println(n);
	}
	
	public static boolean isConditionMet(HashMap<Integer, Integer> avoid, int n) {
		while (n > 0) {
			if (avoid.containsKey(n % 10)) {
				return false;
			}
			if (n == 0) {
				break;
			}
			n /= 10;
		}
		return true;
	}
}
