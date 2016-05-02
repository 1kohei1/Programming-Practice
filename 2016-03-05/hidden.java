import java.util.Scanner;

public class hidden {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
//		while (true) {
		String s1 = scanner.next();
		String s2 = scanner.next();
		
		boolean didPass = true;
		
		for (int i = 0; i < s1.length() && didPass; i++) {
			String subS1 = s1.substring(i, i + 1);
//			System.out.println("Testing " + subS1 + ":");
			int index1 = s2.indexOf(subS1);
			
			if (index1 == -1) {
				didPass = false;
				break;
			}
			
			for (int j = i + 1; j < s1.length(); j++) {
				String subS2 = s1.substring(j, j + 1);
				int index2 = s2.indexOf(subS2);
				
				if (index2 == -1 || index2 < index1) {
					didPass = false;
					break;
				}
			}
			s2 = s2.substring(index1 + 1);
//			System.out.println("s2: " + s2);
		}
		
//		int pos = 0;
//		for (int i = 0; i < s1.length(); i++) {
//			String subS = s1.substring(i, i + 1);
//			System.out.println("Testing " + subS + ":");
//			int index = s2.indexOf(subS);
//			if (index < pos) {
//				System.out.println("FAIL");
//				return;
//			}
//			pos = index;
//			s2 = s2.substring(index + 1);
//			System.out.println("s2: " + s2);
//		}
//		System.out.println("PASS");
		
		if (didPass) {
			System.out.println("PASS");
		} else {
			System.out.println("FAIL");
		}
//		}
	}
/*
ABC HAPPYBIRTHDAYCACEY
ABC TRAGICBIRTHDAYCACEY
ABC HAPPYBIRTHDAY
SECRET SOMECHORESARETOUGH
SECRET SOMECHEERSARETOUGH

ABC BCA
ABC XYZ
AAAB AB
 */
}
