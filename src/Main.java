import java.util.*;
import java.awt.Point;

// ABC 2-C
// http://abc002.contest.atcoder.jp/tasks/abc002_3
 
public class Main {
	
	public static void main (String[] args) throws java.lang.Exception {
		Scanner in = new Scanner(System.in);

		double x1 = in.nextInt();
		double y1 = in.nextInt();
		double x2 = in.nextInt();
		double y2 = in.nextInt();
		double x3 = in.nextInt();
		double y3 = in.nextInt();
		
		double a = length(x1, y1, x2, y2);
		double b = length(x2, y2, x3, y3);
		double c = length(x3, y3, x1, y1);
		
		// Hero's formula
		double p = (a + b + c) / 2;
		System.out.println(Math.sqrt(p * (p - a) * (p - b) * (p - c)));
	}
	
	public static double length(double x1, double y1, double x2, double y2) {
		double len1 = Math.abs(x1 - x2);
		double len2 = Math.abs(y1 - y2);
		
		return Math.sqrt(Math.pow(len2, 2) + Math.pow(len1, 2));
	}
}
