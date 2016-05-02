import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class prob4_02_20 {

	static int max = (int) Math.sqrt(Math.pow(2, 31)) + 1;
	static boolean[] isPrime = new boolean[max];
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		Arrays.fill(isPrime, true);
		for (int i = 2; i < max; i++) {
			if (isPrime[i]) {
				if (isPrime(i)) {
					for (int j = i * 2; j < max; j += i) {
						isPrime[j] = false;
					}
				} else {
					isPrime[i] = false;
				}
			}
		}
		int numTests = scanner.nextInt();
		for (int counter = 1; counter <= numTests; counter++) {
			int n = scanner.nextInt();
			int nn = scanner.nextInt();
			
			ArrayList<Integer> primeFactors = new ArrayList<Integer>();
			
			for (int i = 0; i < nn; i++) {
				int m = scanner.nextInt();
				for (int j = 2; j <= Math.sqrt(m); j++) {
					if (isPrime[j] && m % j == 0) {
						if (m / j >= max ? isPrime(m / j) : isPrime[m / j]) {
							if (primeFactors.indexOf(j) == -1) {
								primeFactors.add(j);							
							}
							if (primeFactors.indexOf(m / j) == -1) {
								primeFactors.add(m / j);							
							}
//							break;
						}
					}
				}
			}
			
			primeFactors.sort(null);
			System.out.println(n);
			for (int i = 0; i < primeFactors.size(); i++) {
				System.out.printf("%d ", primeFactors.get(i));
				if ((i + 1) % 5 == 0 && i + 1 < primeFactors.size()) {
					System.out.println();
				}
			}
			System.out.println();
		}
	}
/*
4
1 6
221 391 713 1457 901
299
2 4
13193 18721 31897 18527
3 2
2143650557 2140117121
4 1
35
 */
	
	public static boolean isPrime(int n) {
		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0) return false;
		}
		return true;
	}

}
