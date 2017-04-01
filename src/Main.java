import java.util.*;

// UVa 11450
// https://uva.onlinejudge.org/index.php?option=onlinejudge&page=show_problem&problem=2445

public class Main {
	
	public static void main (String[] args) throws java.lang.Exception {
	    Scanner in = new Scanner(System.in);
	    
	    int numTest = in.nextInt();
	    
	    while (numTest-- > 0) {
	    	int m = in.nextInt();
	    	int c = in.nextInt();
	    	
	    	ArrayList[] garment = new ArrayList[c];
	    	int[][] dp = new int[c][m + 1]; 
	    	// dp[c][m] means in the range of garment0 to garmentc, it's possible to spend the m amount of money
	    	
	    	for (int i = 0; i < c; i++) {
	    		int k = in.nextInt();
	    		garment[i] = new ArrayList<Integer>();
	    		
	    		for (int j = 0; j < k; j++) {
	    			garment[i].add(in.nextInt());
	    		}
	    	}
	    	
	    	// First item
	    	for (int i = 0; i < garment[0].size(); i++) {
	    		int p = (int) garment[0].get(i);
	    		if (0 < p && p < m) {
	    			dp[0][p] = 1;
	    		}
	    	}
	    	
	    	// from second item
	    	for (int i = 1; i < c; i++) {
	    		for (int j = 0; j < garment[i].size(); j++) {
	    			int p = (int) garment[i].get(j);
	    			for (int k = 0; k < m + 1; k++) {
	    				if (dp[i - 1][k] == 1 && k + p <= m) {
	    					dp[i][k + p] = 1;
	    				}
	    			}
	    		}
	    	}
	    	
	    	int answer = -1;
	    	for (int i = m; i > 0; i--) {
	    		if (dp[c - 1][i] == 1) {
	    			answer = i;
	    			break;
	    		}
	    	}
	    	System.out.println(answer == -1 ? "no solution" : answer);
	    }
	}
}
