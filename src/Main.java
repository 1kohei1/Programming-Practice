import java.util.*;

// ABC 33-C
// http://abc033.contest.atcoder.jp/tasks/abc033_c

public class Main {

	static long MOD = 1000000007;
	
	public static void main (String[] args) throws java.lang.Exception {
	    Scanner in = new Scanner(System.in);
	    
	    String[] s = in.next().split("\\+");
	    
	    long answer = 0;
	    for (int i = 0; i < s.length; i++) {
	    	if (s[i].length() == 1 && s[i].charAt(0) != '0') {
	    		answer++;
	    	} else if (s[i].contains("*") && s[i].indexOf('0') == -1) {
	    		answer++;
	    	}
	    	answer %= MOD;
	    }
	    System.out.println(answer);
	}
}
