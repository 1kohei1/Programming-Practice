import java.math.BigInteger;
import java.util.HashMap;
import java.util.Scanner;

public class j_02_28 {
	
	static int k;
	static BigInteger kBig;
	static BigInteger zeroBig;
	static BigInteger oneBig;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		zeroBig = new BigInteger("0");
		oneBig = new BigInteger("1");
		
//		test(scanner);
		
		String n = scanner.next();
		k = scanner.nextInt();
		kBig = new BigInteger("" + k);
		int q = scanner.nextInt();
		
		for (int i = 0; i < q; i++) {
			String s1 = scanner.next();
			String s2 = scanner.next();
			
			String s1Path = getPath(new BigInteger(s1), "");
			String s2Path = getPath(new BigInteger(s2), "");
			
			System.out.println(answer(s1Path, s2Path));
		}
		
//		String n = scanner.next();
//		k = scanner.nextInt();
//		kBig = new BigInteger("" + k);
////		int q = scanner.nextInt();
//		
//		HashMap<BigInteger, Integer> howDeep = new HashMap<BigInteger, Integer>();
//		howDeep.put(oneBig, 0);
//		
//		BigInteger max = new BigInteger(n).add(oneBig);
//		
//		long start = System.currentTimeMillis();
//		
//		for (BigInteger a = new BigInteger("2"); !a.equals(max); a = a.add(oneBig)) {
//			howDeep.put(a, howDeep.get(getParent(a)) + 1);
//		}
//		System.out.println(System.currentTimeMillis() - start + "ms");
//		System.out.println(howDeep.get(new BigInteger(n)));
		
//		BigInteger n = scanner.nextBigInteger();
//		int k = scanner.nextInt();
//		int q = scanner.nextInt();
		
	}
	
	public static void test(Scanner scanner) {
		while (true) {
			k = scanner.nextInt();
			kBig = new BigInteger("" + k);
			
			while (true) {
				String a = scanner.next();
				if (a.equals("0")) break;
				System.out.println(getPath(new BigInteger(a), ""));
			}
		}
	}

	public static BigInteger getParent(BigInteger a) {
		if (a.mod(kBig).equals(zeroBig)) {
			return a.divide(kBig);
		} else if (a.mod(kBig).equals(oneBig)) {
			return a.subtract(oneBig).divide(kBig);
		} else {
			return a.add(kBig.subtract(a.mod(kBig))).divide(kBig);
		}
	}
	
	public static int getParent(int a, int k) {
		if (a % k == 0) {
			return a / k;
		} else if (a % k == 1) {
			return (a - 1) / k;
		} else {
			return (a + (k - a % k)) / k;
		}
	}
	
	public static BigInteger answer(String s1, String s2) {
		int max = Math.min(s1.length(), s2.length());
		int i;
		for (i = 0; i < max; i++) {
			if (s1.charAt(i) != s2.charAt(i)) {
				break;
			}
		}
		return new BigInteger(new Integer(((int) Math.abs(s1.length() - i + s2.length() - i))).toString());
	}
	
	public static String getPath(BigInteger n, String s) {
		if (n.equals(oneBig)) {
			return "1" + s;
		}
		
		if (n.mod(kBig).equals(zeroBig)) {
			return getPath(n.divide(kBig), "0" + s);
		} else if (n.mod(kBig).equals(oneBig)) {
			return getPath(n.subtract(oneBig).divide(kBig), "1" + s);
		} else {
			return getPath(n.add(kBig.subtract(n.mod(kBig))).divide(kBig), n.mod(kBig).toString() + s);
		}
	}
}
