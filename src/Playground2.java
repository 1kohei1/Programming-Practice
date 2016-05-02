import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Playground2 {
	
	public static ArrayList<String> names;
	public static Double[][] rates;
	public static boolean[] visited;
	
	public static double originalD;
	public static double diff;
	public static String answerCurrency;
	public static int answerAmount;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int counter = 1;
		
		while (true) {
			int n = scanner.nextInt();
			if (n == 0) {
				break;
			}
			
			names = new ArrayList<String>();
			rates = new Double[2 * n][2 * n];
			visited = new boolean[2 * n];
			
			for (int i = 0; i < n; i++) {
				double d1 = scanner.nextDouble();
				String s1 = scanner.next();
				scanner.next();
				double d2 = scanner.nextDouble();
				String s2 = scanner.next();
				
				int index1 = names.indexOf(s1);
				if (index1 == -1) {
					index1 = names.size();
					names.add(s1);
				}
				int index2 = names.indexOf(s2);
				if (index2 == -1) {
					index2 = names.size();
					names.add(s2);
				}
				
				rates[index1][index2] = d2 / d1;
				rates[index2][index1] = d1 / d2;
			}
			
			originalD = scanner.nextDouble();
			String s = scanner.next();
			int sIndex = names.indexOf(s);
			visited[sIndex] = true;
			
			diff = 1;
			answerCurrency = "";
			answerAmount = 0;
			
			for (int i = 0; i < n * 2; i++) {
				if (rates[sIndex][i] != null) {
					solve(i, rates[sIndex][i]);
				}
			}
			
			System.out.printf("Case %d: %d %s\n", counter, answerAmount, answerCurrency);
			counter++;
		}
	}
	
	// conversationRate is rate from sIndex to index
	public static void solve(int index, double conversionRate) {
		if (visited[index]) {
			return;
		}
		visited[index] = true;
		
		double d = originalD * conversionRate;
		
		if (Math.ceil(d) <= 100000) {
			double originalEquiv = Math.ceil(d) / conversionRate;
			if (originalEquiv - originalD < diff) {
				diff = originalEquiv - originalD;
				answerCurrency = names.get(index);
				answerAmount = (int) Math.ceil(d);
			}
		}
		for (int i = 0; i < rates.length; i++) {
			if (rates[index][i] != null) {
				solve(i, conversionRate * rates[index][i]);
			}
		}
	}
}
