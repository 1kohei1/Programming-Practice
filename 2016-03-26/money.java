import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class money {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int counter = 1;
		while (true) {
			int n = scanner.nextInt();
			scanner.nextLine();
			if (n == 0) {
				break;
			}
			
//			if (counter == 29) {
//				System.out.printf("n: %d\n", n);
//			}
			
			ArrayList<Currency> currencies = new ArrayList<Currency>();
			
			for (int i = 0; i < n; i++) {
				String s = scanner.nextLine();
//				if (counter == 29) {
//					System.out.println(s);
//				}
				String s1 = s.split("=")[0];
				String s2 = s.split("=")[1];
				
				int value1 = Integer.parseInt(s1.split(" ")[0]);
				int value2 = Integer.parseInt(s2.split(" ")[1]);
				
				String name1 = s1.split(" ")[1];
				String name2 = s2.split(" ")[2];
				
				Currency c1 = new Currency(name1, value1);
				Currency c2 = new Currency(name2, value2);
				
				c1.equalTo = new Currency(name2, value2);
				c2.equalTo = new Currency(name1, value1);
				
				currencies.add(c1);
				currencies.add(c2);
			}
			
			int amount = scanner.nextInt();
			String currency = scanner.next();
			
//			if (counter == 29) {
//				System.out.println("amount: " + amount);
//				System.out.println("currency: " + currency);
//				return;
//			}
			
			double max = 100000.0;
			
			HashMap<String, Double> save = new HashMap<String, Double>();
			save.put(currency, (double) amount);
			
			ArrayList<String> queue = new ArrayList<String>();
			queue.add(currency);

			double diff = Integer.MAX_VALUE;
			int answerValue = 0;
			String answerCurrency = "";
			
			ArrayList<String> checkedCurrencies = new ArrayList<String>();
			checkedCurrencies.add(currency);
			
			ArrayList<Currency> removedCurrencies = new ArrayList<Currency>();
			ArrayList<String> equalToString = new ArrayList<String>();
			
			while (queue.size() > 0) {
				String currencyName = queue.remove(0);
				
				int index = currencies.indexOf(new Currency(currencyName, 3));
				if (index >= 0) {
					Currency c = currencies.get(index);
					if (checkedCurrencies.indexOf(c.equalTo.name) >= 0) {
						currencies.remove(index);
						if (currencies.indexOf(new Currency(currencyName, 3)) >= 0) {
							queue.add(currencyName);
						}						
					} else {
						removedCurrencies.add(c);
						equalToString.add(c.equalTo.name);
						
						double a = save.get(currencyName) / c.value * c.equalTo.value - 1e-7;
						checkedCurrencies.add(c.equalTo.name);
						save.put(c.equalTo.name, a);
						
						double ceiled = Math.ceil(a);
						
//						System.out.printf("Convert from %s to %s: %.2f %s => %.2f %s\n", currencyName, c.equalTo.name, save.get(currencyName), currencyName, a, c.equalTo.name);
						// This condition must be changed to compare with A
						if (ceiled <= max) {
							String eqS = c.equalTo.name;
							double equiv = ceiled;
							while (!eqS.equals(currency)) {
								int index2 = equalToString.indexOf(eqS);
								Currency equivC = removedCurrencies.get(index2);
								
//								System.out.println("Conevrt back from " + eqS + " to " + equivC.name);
								
//								System.out.printf("%.2f / %d * %d = %.2f\n", equiv, equivC.equalTo.value, equivC.value, equiv / equivC.equalTo.value * equivC.value);
								equiv = equiv / equivC.equalTo.value * equivC.value;
								eqS = equivC.name;
							}
							if (Math.abs(equiv - amount) < diff) {
								diff = Math.abs(equiv - amount);
								answerValue = (int) ceiled;
								answerCurrency = c.equalTo.name;
							}
//							System.out.println("Equiv: " + equiv + " " + currency);
//							diff = ceiled - a;
//							answerValue = (int) ceiled;
//							answerCurrency = c.equalTo.name;
						}
						
						queue.add(c.equalTo.name);
						
						currencies.remove(index);
						
						if (currencies.indexOf(new Currency(currencyName, 3)) >= 0) {
							queue.add(currencyName);
						}						
					}
				}
			}
			
			System.out.printf("Case %d: %d %s\n", counter, answerValue, answerCurrency);
			counter++;
		}
	}
/*
1
4 A = 19 B
1 B

2
15 A = 29 B
25 A = 17 C
5 C
 */
//	public static ArrayList<String> names;
//	public static Double[][] rates;
//	public static boolean[] visited;
//	
//	public static double originalD;
//	public static double diff;
//	public static String answerCurrency;
//	public static int answerAmount;
//	
//	public static void main(String[] args) {
//		Scanner scanner = new Scanner(System.in);
//		
//		int counter = 1;
//		
//		while (true) {
//			int n = scanner.nextInt();
//			if (n == 0) {
//				break;
//			}
//			
//			names = new ArrayList<String>();
//			rates = new Double[2 * n][2 * n];
//			visited = new boolean[2 * n];
//			
//			for (int i = 0; i < n; i++) {
//				double d1 = scanner.nextDouble();
//				String s1 = scanner.next();
//				scanner.next();
//				double d2 = scanner.nextDouble();
//				String s2 = scanner.next();
//				
//				int index1 = names.indexOf(s1);
//				if (index1 == -1) {
//					index1 = names.size();
//					names.add(s1);
//				}
//				int index2 = names.indexOf(s2);
//				if (index2 == -1) {
//					index2 = names.size();
//					names.add(s2);
//				}
//				
//				rates[index1][index2] = d2 / d1;
//				rates[index2][index1] = d1 / d2;
//			}
//			
//			originalD = scanner.nextDouble();
//			String s = scanner.next();
//			int sIndex = names.indexOf(s);
//			visited[sIndex] = true;
//			
//			diff = Integer.MAX_VALUE;
//			answerCurrency = "";
//			answerAmount = 0;
//			
//			for (int i = 0; i < n * 2; i++) {
//				if (rates[sIndex][i] != null) {
//					solve(i, rates[sIndex][i], s);
//				}
//			}
//			
//			System.out.printf("Case %d: %d %s\n", counter, answerAmount, answerCurrency);
//			counter++;
//		}
//	}
//	
//	// conversationRate is rate from sIndex to index
//	public static void solve(int index, double conversionRate, String originalCurrency) {
//		if (visited[index]) {
//			return;
//		}
//		visited[index] = true;
//		
//		double d = originalD * conversionRate - 1e-7;
//		
//		if (Math.ceil(d) <= 100000) {
//			double originalEquiv = Math.ceil(d) / conversionRate;
////			System.out.printf("%.2f, %.2f %s = %.2f %s\n", d, Math.ceil(d), names.get(index), originalEquiv, originalCurrency);
//			if (originalEquiv - originalD < diff) {
//				diff = originalEquiv - originalD;
//				answerCurrency = names.get(index);
//				answerAmount = (int) Math.ceil(d);
//			}
//		}
//		for (int i = 0; i < rates.length; i++) {
//			if (rates[index][i] != null) {
//				solve(i, conversionRate * rates[index][i], originalCurrency);
//			}
//		}
//	}
/*
4
23 A = 17 B
16 C = 29 E
5 B = 14 E
1 D = 7 F
100 A

6
29 A = 30 B
29 B = 30 C
29 C = 30 D
30 D = 29 E
30 E = 29 F
30 F = 29 G
100 A

1
1 shekel = 2 quatloo
40 quatloo

2
20 A = 30 B
21 A = 45 C
100 A

3
10 A = 20 B
11 B = 5 C
9 A = 8 D
90000 A

3
10 A = 20 B
11 B = 4 C
7 A = 3 D
90000 A

4
23 A = 17 B
16 C = 29 E
5 B = 14 E
1 D = 7 F
64000 A

2
1 A = 2 B
2 B = 1 A
40 B
0
 */
}

class Currency implements Comparable<Currency> {
	int value;
	String name;
	boolean used;
	Currency equalTo;
	
	public Currency(String name, int value) {
		this.name = name;
		this.value = value;
		this.used = false;
		this.equalTo = null;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.name.equals(((Currency) obj).name);
	}

	@Override
	public int compareTo(Currency o) {
		return this.value - o.value;
	}
}