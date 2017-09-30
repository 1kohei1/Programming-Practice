import java.util.*;

// Tenakaichi 2017-C
// http://tenka1-2017.contest.atcoder.jp/tasks/tenka1_2017_c	

public class Main {
	
	public static void main (String[] args) throws InterruptedException {
		Scanner in = new Scanner(System.in);
		
		double E = 1e-9;
		
		double N = in.nextDouble();
		boolean answerFound = false;
		double d = 4 / N;
		
		for (double i = 3500; i > 0 && 1 / i < d && !answerFound; i--) {
			double dd = d - 1 / i;
			
			for (double j = 3500; j > 0 && 1 / j < dd && !answerFound; j--) {
				double ddd = dd - 1 / j;
				
				double x = 1 / ddd;
				int k = 0;
				
				if (Math.abs(Math.ceil(x) - x) <= E) {
					k = (int) Math.ceil(x);
					answerFound = true;
				} else if (Math.abs(Math.floor(x) - x) <= E) {
					k = (int) Math.floor(x);
					answerFound = true;
				}
				
				if (answerFound) {
					System.out.printf("%d %d %d\n", (int) i, (int) j, k);
				}
			}
		}
	}
	
	
}
