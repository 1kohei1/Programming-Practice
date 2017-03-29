import java.util.*;

// ABC 45-C
// http://abc045.contest.atcoder.jp/tasks/arc061_a

public class Main {
	
    static int n;
    static int[] nums;

	public static void main (String[] args) throws java.lang.Exception {
	    Scanner in = new Scanner(System.in);
	    String s = in.next();
	    n = s.length();
	    nums = new int[n];
	    
	    for (int i = 0; i < n; i++) {
	        nums[i] = new Integer(s.substring(i, i + 1));
	    }
	    
	    long answer = 0;
	    for (int i = 0; i < (1 << (n - 1)); i++) {
	        answer += solve(i);
	    }
	    
	    System.out.println(answer);
	}
	
	public static long solve(int a) {
	    long sofar = 0;
	    long returnv = 0;
	    for (int i = 0; i < n; i++) {
	       // System.out.printf("%d%c", nums[i], (a & (1 << i)) > 0 ? '+' : ' ');

            sofar *= 10;
            sofar += nums[i];
	        

	        if ((a & (1 << i)) > 0) {
	            returnv += sofar;
	            sofar = 0;
	        } else {
	           // sofar *= 10;
	           // sofar += nums[i];
	        }
	    }
	    returnv += sofar;
	   // System.out.printf(": returnv: %d\n", returnv);
	    return returnv;
	}
}
