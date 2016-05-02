import java.util.Scanner;

public class Prob5_02_20 {

	static String[] digits = ",one,two,three,four,five,six,seven,eight,nine".split(",");
	static String[] tenth = "ten,eleven,twelve,thirteen,fourteen,fifteen,sixteen,seventeen,eighteen,nineteen".split(",");
	static String[] otherTenth = ",,twenty,thirty,fourty,fifty,sixty,seventy,eighty,ninety".split(",");
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int numTests = scanner.nextInt();
		for (int counter = 0; counter < numTests; counter++) {
			String s = scanner.next();
			
			while (s.length() > 0) {
				int sLen = s.length();
				if (sLen >= 19) {
					String ss = s.substring(0, sLen - 19 + 1);
					if (!ss.equals("000")) {
						print3Digits(makeit3(ss));
						System.out.print("quintillion ");						
					}
					s = s.substring(sLen - 19 + 1);
				} else if (sLen >= 16) {
					String ss = s.substring(0, sLen - 16 + 1);
					if (!ss.equals("000")) {
						print3Digits(makeit3(ss));
						System.out.print("quadrillion ");						
					}
					s = s.substring(sLen - 16 + 1);
				} else if (sLen >= 13) {
					String ss = s.substring(0, sLen - 13 + 1);
					if (!ss.equals("000")) {
						print3Digits(makeit3(ss));
						System.out.print("trillion ");
					}
					s = s.substring(sLen - 13 + 1);
				} else if (sLen >= 10) {
					String ss = s.substring(0, sLen - 10 + 1);
					if (!ss.equals("000")) {
						print3Digits(makeit3(ss));
						System.out.print("billion ");
					}
					s = s.substring(sLen - 10 + 1);
				} else if (sLen >= 7) {
					String ss = s.substring(0, sLen - 7 + 1);
					if (!ss.equals("000")) {
						print3Digits(makeit3(ss));
						System.out.print("million ");
					}
					s = s.substring(sLen - 7 + 1);
				} else if (sLen >= 4) {
					String ss = s.substring(0, sLen - 4 + 1);
					if (!ss.equals("000")) {
						print3Digits(makeit3(ss));
						System.out.print("thousand ");
					}
					s = s.substring(sLen - 4 + 1);
				} else {
					print3Digits(makeit3(s));
					s = "";
				}
			}
			System.out.println();
		}
	}
	
	public static String makeit3(String s) {
		int sLen = s.length();
		if (sLen >= 3) {
			return s;
		} else if (sLen == 2) {
			return "0" + s;
		} else {
			return "00" + s;
		}
	}

	public static void print3Digits(String s) {
		if (s.charAt(0) != '0') {
			int a = new Integer(s.charAt(0)) - 48;
			System.out.printf("%s hundred ", digits[a]);
		}
		int ten = new Integer(s.charAt(1)) - 48;
		int one = new Integer(s.charAt(2)) - 48;
		
		if (ten == 1) {
			System.out.printf("%s ", tenth[one]);
		} else if (ten > 1) {
			System.out.printf("%s ", otherTenth[ten]);
		}
		
		if (ten != 1 && one != 0) {
			System.out.printf("%s ", digits[one]);			
		}
	}
}
