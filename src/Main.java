import java.util.*;

// ABC 37-C
// http://abc038.contest.atcoder.jp/tasks/abc038_c

public class Main {
	
	public static void main (String[] args) throws java.lang.Exception {
	    Scanner in = new Scanner(System.in);
	    
	    int n = in.nextInt();
	    int k = in.nextInt();
	    
	    int[] nums = new int[n];
	    long answer = 0;
	    long partialSum = 0;
	    
	    for (int i = 0; i < n; i++) {
	    	nums[i] = in.nextInt();
	    	if (i < k) {
	    		partialSum += nums[i];
	    	}
	    }
	    
	    answer = partialSum;
	    
	    for (int i = 1; i < n - k + 1; i++) {
	    	partialSum -= nums[i - 1];
	    	partialSum += nums[i + k - 1];
	    	answer += partialSum;
	    }
	    System.out.println(answer);
	}
}
