import java.util.*;
import java.awt.Point;
import java.math.BigInteger;

// Google foo.bar

public class Main {
	
	public static void main (String[] args) throws InterruptedException {
		Scanner in = new Scanner(System.in);

		int n = in.nextInt();
		int[][] map = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j] = in.nextInt();
			}
		}
		int[] answer = answer(map);
		for (int i = 0; i < answer.length; i++) {
			System.out.printf("%d ", answer[i]);
		}
	}
	
	public static int[] answer(int[][] m) {
		boolean[] isTerminate = new boolean[m.length];
		Fraction[][] dist = new Fraction[m.length][m.length];
		Fraction[] loop = new Fraction[m.length];
		Fraction[] answer = new Fraction[m.length];
		
		for (int i = 0; i < m.length; i++) {
			loop[i] = null;
			answer[i] = null;
			int sum = 0;

			for (int j = 0; j < m[i].length; j++) {
				sum += m[i][j];
			}
			if (sum == 0) {
				isTerminate[i] = true;
			} else {
				for (int j = 0; j < m[i].length; j++) {
					if (m[i][j] > 0) {
						Fraction f = new Fraction(m[i][j], sum);
						dist[i][j] = f;
					}
				}
			}
		}
//		print2DFraction(dist);
		
		ArrayList<Integer> path = new ArrayList<Integer>();
		dfs(dist, loop, 0, isTerminate, answer, path);
		
//		print1DFraction(loop);
//		print1DFraction(answer);
		
		long lcm = 1;
		int terminateCount = 0;
		for (int i = 0; i < m.length; i++) {
			if (isTerminate[i]) {
				terminateCount++;
				if (answer[i] != null) {
					lcm = lcm(lcm, answer[i].denominator);
				}
			}
		}
		
		int[] returnArr = new int[terminateCount + 1];
		int index = 0;
		for (int i = 0; i < m.length; i++) {
			if (isTerminate[i]) {
				if (answer[i] == null) {
					returnArr[index] = 0;
				} else {
					returnArr[index] = (int) (answer[i].numerator * (lcm / answer[i].denominator));
				}
				index++;
			}
		}
		returnArr[index] = (int) lcm;
		
		return returnArr;
	}
	
	public static void dfs(Fraction[][] dist, Fraction[] loop, int index, boolean[] isTerminate, Fraction[] answer, ArrayList<Integer> path) {
		int pathIndex = path.indexOf(index);
		
		if (pathIndex >= 0) {
			path.add(index);
			Fraction f = new Fraction(1, 1);
			for (int i = pathIndex + 1; i < path.size(); i++) {
				f.multiply(dist[path.get(i - 1)][path.get(i)]);
			}
			
			f.calculateForLoop();
			
			if (loop[index] == null) {
				loop[index] = f;
			} else {
				loop[index].multiply(f);
			}
			path.remove(path.size() - 1);
			return;
		}
		
		path.add(index);
		
		for (int i = 0; i < dist[index].length; i++) {
			if (dist[index][i] != null) {
				dfs(dist, loop, i, isTerminate, answer, path);
			}
		}
		if (isTerminate[index]) {
			Fraction f = new Fraction(1, 1);
			for (int i = 0; i < path.size() - 1; i++) {
				int currentNode = path.get(i);
				if (loop[currentNode] != null) {
					f.multiply(loop[currentNode]);
				}
				f.multiply(dist[currentNode][path.get(i + 1)]);
			}
			if (answer[index] == null) {
				answer[index] = f;
			} else {
				answer[index].add(f);
			}
		}
		path.remove(path.size() - 1);
	}
	
	public static void print2DFraction(Fraction[][] f) {
		for (int i = 0; i < f.length; i++) {
			for (int j = 0; j < f[i].length; j++) {
				if (f[i][j] != null) {
					System.out.printf("[%d][%d]: %s\n", i, j, f[i][j]);
				}
			}
		}
	}
	
	public static void print1DFraction(Fraction[] f) {
		for (int i = 0; i < f.length; i++) {
			if (f[i] != null) {
				System.out.printf("%d: %s\n", i, f[i]);
			}
		}
	}
	
	public static void printArrayList(ArrayList<Integer> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.printf("%d ", list.get(i));
		}
		System.out.println();
	}
	
	public static long gcd(long a, long b) {
		if (a == 0) {
			return b;
		}
		return gcd(b % a, a);
	}
	
	public static long lcm(long a, long b) {
		return a * b / gcd(a, b);
	}
	
	/*
	public static String answer(int[] xs) {
		
		int numZero = 0;
		int numNegative = 0;
		int index = -1;
		
		for (int i = 0; i < xs.length; i++) {
			if (xs[i] == 0) {
				numZero++;
			}
			if (xs[i] < 0) {
				numNegative++;
				if (index == -1) {
					index = i;
				} else if (Math.abs(xs[i]) < Math.abs(xs[index])) {
					index = i;
				}
			}
		}
		
		if (numZero == xs.length) {
			return "0";
		} else if (numZero > 0 && numNegative == 1 && numNegative + numZero == xs.length) {
			return "0";
		} else if (numNegative == 1 && numNegative == xs.length) {
			return new Integer(xs[0]).toString();
		} else {
			BigInteger answer = new BigInteger("1");
			for (int i = 0; i < xs.length; i++) {
				if (xs[i] != 0 && !(numNegative % 2 == 1 && index == i)) {
					answer = answer.multiply(new BigInteger(new Integer(Math.abs(xs[i])).toString()));
				}
			}
			return answer.toString();
		}
	}
	*/
	
	
	/*
    public static int[] answer(int[] l, int t) {
    	int left = 0;
    	int right = 0;
    	int sum = 0;
    	int[] answer = new int[]{-1, -1};
    	
    	while (left < l.length) {
    		if (sum == t) {
    			answer[0] = left;
    			answer[1] = right - 1;
    			return answer;
    		}
    		if (sum < t && right < l.length) {
    			sum += l[right];
    			right++;
    		} else {
    			sum -= l[left];
    			left++;
    		}
    	}
    	
    	return answer;
    }
    */
}

class Fraction {
	long numerator;
	long denominator;
	
	public Fraction(long n, long d) {
		this.numerator = n;
		this.denominator = d;
		this.simplify();
	}
	
	public void simplify() {
		long gcd = this.gcd(this.numerator, this.denominator);
		if (gcd > 1) {
			this.numerator = this.numerator / gcd;
			this.denominator = this.denominator / gcd;
		}
	}
	
	public void multiply(Fraction f) {
		this.numerator *= f.numerator;
		this.denominator *= f.denominator;
		this.simplify();
	}
	
	// Calculate sum of unlimited ratio series
	public void calculateForLoop() {
		long n = this.numerator;
		long d = this.denominator;
		this.numerator = d;
		this.denominator = d - n;
		this.simplify();
	}
	
	public void add(Fraction f) {
		long commonDenominator = lcm(this.denominator, f.denominator);
		long a = this.numerator * (commonDenominator / this.denominator);
		long b = f.numerator * (commonDenominator / f.denominator);
		this.numerator = a + b;
		this.denominator = commonDenominator;
		this.simplify();
	}
	
	private long gcd(long a, long b) {
		if (a == 0) {
			return b;
		}
		return gcd(b % a, a);
	}
	
	public long lcm(long a, long b) {
		return a * b / gcd(a, b);
	}

	@Override
	public String toString() {
		return this.numerator + "/" + this.denominator;
	}
}
