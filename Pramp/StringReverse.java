import java.util.Scanner;

public class StringReverse {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String s = in.nextLine();
		char[] c = s.toCharArray();
		reverseString(c);
		printCharArray(c);
	}

	public static void reverseString(char[] c) {
		reverse(c, 0, c.length - 1);
		
		int index = 0;
		for (int i = 1; i < c.length; i++) {
			if (c[i] == ' ') {
				if (i - index > 1) {
					reverse(c, index, i - 1);
				}
				index = i + 1;
			}
		}
		if (c[c.length - 1] != ' ') {
			reverse(c, index, c.length - 1);
		}
	}
	
	// Both start and end is inclusive
	public static void reverse(char[] c, int start, int end) {
		while(start < end) {
			char temp = c[start];
			c[start] = c[end];
			c[end] = temp;
			
			start++;
			end--;
		}
	}
	
	public static void printCharArray(char[] c) {
		for (int i = 0; i < c.length; i++) {
			System.out.printf("%c", c[i]);
		}
		System.out.println();
	}
}
