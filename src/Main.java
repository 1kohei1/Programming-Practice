import java.util.*;

// UVa 11450
// https://uva.onlinejudge.org/index.php?option=onlinejudge&page=show_problem&problem=2445

public class Main {
	
	static int c;
	static int m;
	static long[][] memo;

	public static void main (String[] args) throws java.lang.Exception {
	    Scanner in = new Scanner(System.in);
	    
	    int numTest = in.nextInt();
	    ArrayList[] garment = new ArrayList[2];
	    
	    while (numTest-- > 0) {
	    	m = in.nextInt();
	    	c = in.nextInt();
	    	
		    garment = new ArrayList[c];
		    memo = new long[c][m + 1];
		    
		    for (int i = 0; i < c; i++) {
		    	garment[i] = new ArrayList<Integer>();
		    	Arrays.fill(memo[i], -1);
		    	int k = in.nextInt();
		    	for (int j = 0; j < k; j++) {
		    		garment[i].add(in.nextInt());
		    	}
		    }
		    
		    long answer = solve(garment, 0, 0);
		    System.out.println(answer == -1 ? "no solution" : answer);
	    }
	    
	}
	
	public static long solve(ArrayList[] garment, int index, int usedMoney) {
		if (usedMoney > m) {
			return -1;
		}
		if (index == c) {
			return usedMoney;
		}
		if (memo[index][usedMoney] >= 0) {
			return memo[index][usedMoney];
		}
		long answer = -1;
		// Think about buying a garment at index
		for (int i = 0; i < garment[index].size(); i++) {
			answer = Math.max(answer, solve(garment, index + 1, usedMoney + (int) garment[index].get(i)));
		}
		memo[index][usedMoney] = answer;
		return answer;
	}
}
