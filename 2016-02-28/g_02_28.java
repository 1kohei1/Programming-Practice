import java.util.Scanner;

public class g_02_28 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();
		int m = scanner.nextInt();
		
		int nLength = numDigits(n);
		int mLength = numDigits(m);
		
		int size = nLength > mLength ? nLength : mLength;
		
		int[] nParsed = parseInt(n, 0, size, new int[size]);
		int[] mParsed = parseInt(m, 0, size, new int[size]);
		
//		printArray(nParsed);
//		printArray(mParsed);
		
		for (int i = 0; i < size; i++) {
			if (nParsed[i] < mParsed[i]) {
				nParsed[i] = -1;
			} else if (nParsed[i] > mParsed[i]) {
				mParsed[i] = -1;
			}
		}
		
		String nn = "";
		String mm = "";
		
		for (int i = 0; i < size; i++) {
			if (nParsed[i] != -1) {
				nn += "" + nParsed[i];
			}
			if (mParsed[i] != -1) {
				mm += "" + mParsed[i];
			}
		}

		if (nn.length() == 0) {
			System.out.println("YODA");
		} else {
			System.out.println(Integer.parseInt(nn));
		}
		if (mm.length() == 0) {
			System.out.println("YODA");
		} else {
			System.out.println(Integer.parseInt(mm));
		}
//		String nn = "";
//		String mm = "";
//		
//		int size = nLength > mLength ? nLength : mLength;
//		
//		for (int i = 0; i < size; i++) {
//			int na = n % 10;
//			int mb = m % 10;
//			
//			if (na < mb) {
//				mm = "" + mb + mm;
//			} else if (na > mb) {
//				nn = "" + na + nn;
//			} else {
//				nn = "" + na + nn;
//				mm = "" + mb + mm;
//			}
//			n /= 10;
//			m /= 10;
//		}
//		nn = "" + n + nn;
//		mm = "" + m + mm;
//		
//		if (Integer.parseInt(nn) == 0) {
//			System.out.println("YODA");
//		} else {
//			System.out.println(Integer.parseInt(nn));
//		}
//		if (Integer.parseInt(mm) == 0) {
//			System.out.println("YODA");
//		} else {
//			System.out.println(Integer.parseInt(mm));
//		}
	}
	
	public static int[] parseInt(int n, int index, int length, int[] nParsed) {
		if (index == length) {
			return nParsed;
		}
		if (n == 0) {
			nParsed[length - index - 1] = 0;
			return parseInt(n, index + 1, length, nParsed);
		} else {
			nParsed[length - index - 1] = n % 10;
			return parseInt(n / 10, index + 1, length, nParsed);			
		}
	}
	
	public static void printArray(int[] n) {
		for (int i = 0; i < n.length; i++) {
			System.out.printf("%d ", n[i]);
		}
		System.out.println();
	}
/*
300
500

65743
9651

2341
6785

1
1

100
1

1234556
1

1000000000
1
 */
	public static int numDigits(int n) {
		if (n <= 9) {
			return 1;
		}
		return 1 + numDigits(n / 10);
	}
}
