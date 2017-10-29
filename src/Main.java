import java.util.*;

// Google foo.bar level 3: Doomsday Fuel

public class Main {
	
	public static void main(String[] args) {
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

//		Fraction[][] map1 = new Fraction[][]{
//			{new Fraction(1, 1), new Fraction(2,1)},
//			{new Fraction(0, 1), new Fraction(1, 1)}
//		};
//		Fraction[][] map2 = new Fraction[][]{
//			{new Fraction(3, 1), new Fraction(4, 1), new Fraction(0, 1)},
//			{new Fraction(1, 1), new Fraction(2, 1), new Fraction(3, 1)}
//		};
//		Utility.print2DFraction(Utility.multiply(map1, map2));
		
//		Fraction[][] fmap = new Fraction[n][n];
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < n; j++) {
//				fmap[i][j] = new Fraction(in.nextLong(), 1);
//			}
//		}
//		
//		System.out.println("Inverse");
//		Utility.print2DFraction(Utility.inverse(fmap));
	}
	
	public static int[] answer(int[][] m) {
		int len = m.length;
		
		boolean[] isAbsorbing = new boolean[len];
		int numAbsorbingNode = 0;
		Fraction[][] fmap = new Fraction[len][len];
		
		// Convert to fraction map
		for (int i = 0; i < len; i++) {
			int sum = 0;
			for (int j = 0; j < len; j++) {
				sum += m[i][j];
			}
			
			for (int j = 0; j < len; j++) {
				if (sum == 0 && i == j) {
					fmap[i][j] = new Fraction(1, 1);
				} else if (sum == 0) {
					fmap[i][j] = new Fraction(m[i][j], 1);
				} else {
					fmap[i][j] = new Fraction(m[i][j], sum);
				}
			}
			
			if (sum == 0) {
				isAbsorbing[i] = true;
				numAbsorbingNode++;
			}
		}
		
		if (isAbsorbing[0]) {
			return new int[]{1, 1};
		}
		
//		System.out.println("fmap");
//		Utility.print2DFraction(fmap);
		
		// Get Q, R, I in canonical form
		Fraction[][] Q = new Fraction[len - numAbsorbingNode][len - numAbsorbingNode];
		Fraction[][] R = new Fraction[len - numAbsorbingNode][numAbsorbingNode];
		Fraction[][] I = new Fraction[numAbsorbingNode][numAbsorbingNode];
		
		int qrRowIndex = 0;
		int iRowIndex = 0;
		
		for (int i = 0; i < len; i++) {
			if (!isAbsorbing[i]) {
				int qColIndex = 0;
				int rColIndex = 0;
				for (int j = 0; j < len; j++) {
					if (!isAbsorbing[j]) {
						Q[qrRowIndex][qColIndex] = fmap[i][j];
						qColIndex++;
					} else {
						R[qrRowIndex][rColIndex] = fmap[i][j];
						rColIndex++;
					}
				}
				qrRowIndex++;
			} else {
				int iColIndex = 0;
				for (int j = 0; j < len; j++) {
					if (isAbsorbing[j]) {
						I[iRowIndex][iColIndex] = fmap[i][j];
						iColIndex++;
					}
				}
				iRowIndex++;
			}
		}
		
//		System.out.println("Q");
//		Utility.print2DFraction(Q);
//		System.out.println("R");
//		Utility.print2DFraction(R);
//		System.out.println("I");
//		Utility.print2DFraction(I);

		// Calculate I - Q to get inverse of I - Q
		int iMinusQLen = len - numAbsorbingNode;
		Fraction[][] iMinusQ = new Fraction[iMinusQLen][iMinusQLen];
		
		for (int i = 0; i < iMinusQLen ; i++) {
			for (int j = 0; j < iMinusQLen ; j++) {
				Fraction f1 = new Fraction(0, 1);
				if (i == j) {
					f1 = new Fraction(1, 1);
				}
				Fraction f2 = Q[i][j];
				iMinusQ[i][j] = Utility.subtract(f1, f2);
			}
		}
		
		Fraction[][] N = Utility.inverse(iMinusQ);
		
//		System.out.println("iMinusQ");
//		Utility.print2DFraction(iMinusQ);
//		System.out.println("N");
//		Utility.print2DFraction(N);
		
		// Calculate probabilities at each terminate state
		Fraction[][] probabilities = Utility.multiply(N, R);
		int[] answer = new int[probabilities[0].length + 1];
		
		long lcm = 1;
		
		for (int i = 0; i < probabilities[0].length; i++) {
			lcm = Utility.lcm(lcm, probabilities[0][i].denominator);
		}
		for (int i = 0; i < probabilities[0].length; i++) {
			Fraction f = probabilities[0][i];
			answer[i] = (int) (lcm / f.denominator * f.numerator);
		}
		answer[probabilities[0].length] = (int) lcm;
		
		return answer;
	}
}

class Fraction {
	long numerator;
	long denominator;
	
	public Fraction(long n, long d) {
		this.numerator = n;
		this.denominator = d;
		
		if (this.denominator < 0) {
			this.numerator *= -1;
			this.denominator *= -1;
		}
		if (this.numerator == 0) {
			this.denominator = 1;
		}
		this.simplify();
	}
	
	public void simplify() {
		long gcd = Utility.gcd(Math.abs(this.numerator), Math.abs(this.denominator));
		if (gcd > 1) {
			this.numerator = this.numerator / gcd;
			this.denominator = this.denominator / gcd;
		}
	}
	
	public Fraction abs() {
		Fraction f = new Fraction(this.numerator, this.denominator);
		if (f.numerator < 0) {
			f.numerator *= -1;
		}
		return f;
	}
	
	@Override
	public String toString() {
		return this.numerator + "/" + this.denominator;
	}
}

class Utility {
	public static long gcd(long a, long b) {
		if (a == 0) {
			return b;
		}
		return gcd(b % a, a);
	}
	
	public static long lcm(long a, long b) {
		return a * b / gcd(a, b);
	}
	
	public static Fraction add(Fraction f1, Fraction f2) {
		long lcm = lcm(f1.denominator, f2.denominator);
		long newNumerator = lcm / f1.denominator * f1.numerator + lcm / f2.denominator * f2.numerator;
		return new Fraction(newNumerator, lcm);
	}
	
	public static Fraction subtract(Fraction f1, Fraction f2) {
		long lcm = lcm(f1.denominator, f2.denominator);
		long newNumerator = lcm / f1.denominator * f1.numerator - lcm / f2.denominator * f2.numerator;
		return new Fraction(newNumerator, lcm);
	}
	
	public static Fraction multiply(Fraction f1, Fraction f2) {
		return new Fraction(f1.numerator * f2.numerator, f1.denominator * f2.denominator);
	}
	
	public static Fraction divide(Fraction f1, Fraction f2) {
		return new Fraction(f1.numerator * f2.denominator, f1.denominator * f2.numerator);
	}
	
	public static Fraction[][] multiply(Fraction[][] map1, Fraction[][] map2) {
		int rowLen = map1.length;
		int colLen = map2[0].length;
		int size = map2.length; // A stopping variable for multiplication 
		
		Fraction[][] newM = new Fraction[rowLen][colLen];
		
		for (int i = 0; i < rowLen; i++) {
			for (int j = 0; j < colLen; j++) {
				Fraction f = new Fraction(0, 1);
				
				for (int k = 0; k < size; k++) {
					Fraction ff = multiply(map1[i][k], map2[k][j]);
					f = add(f, ff);
				}
				
				newM[i][j] = f;
			}
		}
		
		return newM;
	}
	
	// -1 => f1 < f2
	// 0  => f1 == f2
	// 1  => f1 > f2
	public static int compare(Fraction f1, Fraction f2) {
		long lcm = lcm(f1.denominator, f2.denominator);
		long f1Numerator = lcm / f1.denominator * f1.numerator;
		long f2Numerator = lcm / f2.denominator * f2.numerator;
		
		if (f1Numerator < f2Numerator) {
			return -1;
		} else if (f1Numerator > f2Numerator) {
			return 1;
		} else {
			return 0;
		}
	}
	
	public static Fraction[][] inverse(Fraction[][] map) {
		int len = map.length;
		
		Fraction[][] inverse = new Fraction[len][len];
		Fraction[][] map2 = new Fraction[len][len + 1];
		
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				map2[i][j] = map[i][j];
				inverse[i][j] = new Fraction(1, 1);
			}
		}
		
		for (int i = 0; i < len; i++) {
			// Append identity column to the end of the matrix
			for (int j = 0; j < len; j++) {
				if (i == j) {
					map2[j][len] = new Fraction(1, 1);
				} else {
					map2[j][len] = new Fraction(0, 1);
				}
			}
			Fraction[] inverseColumn = gauss(copy(map2));
			for (int j = 0; j < len; j++) {
				inverse[j][i] = inverseColumn[j];
			}
		}
		
		return inverse;
	}
	
	private static Fraction[] gauss(Fraction[][] map) {
		int len = map.length;
		
		// Iterate through each column
		for (int j = 0; j < len; j++) {
			// currentRow is set to j since previous rows are already reduced as j increases.
			int currentRow = j;
			
			// Find the row that contains the largest value in j-th column
			Fraction maxVal = map[currentRow][j].abs();
			int maxRow = currentRow;
			for (int i = currentRow + 1; i < len; i++) {
				if (compare(maxVal, map[i][j].abs()) < 0) {
					maxVal = map[i][j];
					maxRow = i;
				}
			}
			
			// Swap current row and the row that contains the largest value in the column j
			for (int k = 0; k < len + 1; k++) {
				Fraction temp = map[currentRow][k];
				map[currentRow][k] = map[maxRow][k];
				map[maxRow][k] = temp;
			}
			
			// Reduce each row by using currentRow
			for (int i = currentRow + 1; i < len; i++) {
				Fraction ratio = divide(map[i][j], map[currentRow][j]);
				for (int k = 0; k < len + 1; k++) {
					if (k == j) {
						map[i][k] = new Fraction(0, 1);
					} else {
						Fraction f = multiply(map[currentRow][k], ratio);
						f = subtract(f, map[i][k]);
						map[i][k] = f;
					}
				}
			}
		}
		
		Fraction[] answer = new Fraction[len];
		// Iterate rows from the bottom, and compute ax = b
		for (int i = len - 1; i >= 0; i--) {
			int currentColumn = i;
			
			answer[i] = divide(map[i][len], map[i][currentColumn]);
			int row = i - 1;
			while (row >= 0) {
				// Take acount the result of the answer for currentColumn
				Fraction f = multiply(map[row][currentColumn], answer[i]);
				f = subtract(map[row][len], f);
				map[row][len] = f;
				row--;
			}
		}
		
		return answer;
	}
	
	private static Fraction[][] copy(Fraction[][] map) {
		int rowLen = map.length;
		int colLen = map[0].length;
		Fraction[][] map2 = new Fraction[rowLen][colLen];
		
		for (int i = 0; i < rowLen; i++) {
			for (int j = 0; j < colLen; j++) {
				map2[i][j] = new Fraction(map[i][j].numerator, map[i][j].denominator);
			}
		}
		
		return map2;
	}
	
	public static void print2DFraction(Fraction[][] f) {
		for (int i = 0; i < f.length; i++) {
			for (int j = 0; j < f[i].length; j++) {
				System.out.printf("%s ", f[i][j]);
			}
			System.out.println();
		}
	}
}