import java.util.*;
 
// ABC 10-C
// http://abc010.contest.atcoder.jp/tasks/abc010_3
 
public class Main {
 
	public static void main (String[] args) throws java.lang.Exception {
	    Scanner in = new Scanner(System.in);
	    
	    int x1 = in.nextInt();
	    int y1 = in.nextInt();
	    int x2 = in.nextInt();
	    int y2 = in.nextInt();
	    
	    int t = in.nextInt();
	    int v = in.nextInt();
	    int maxd = t * v;
	    
	    int n = in.nextInt();
	    int count = 0;
	    for (int i = 0; i < n; i++) {
	    	int x = in.nextInt();
	    	int y = in.nextInt();
	    	
	    	double d = dist(x1, y1, x, y) + dist(x, y, x2, y2);
	    	if (d <= maxd) {
	    		count++;
	    	}
	    }
	    
	    System.out.println(count > 0 ? "YES" : "NO");
	}
	
	public static double dist(int x1, int y1, int x2, int y2) {
		return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}
}