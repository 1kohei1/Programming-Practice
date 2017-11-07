import java.util.*;

// Foobar Level 3: queue_to_do

public class Main {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int start = in.nextInt();
		int length = in.nextInt();
		System.out.println(answer(start, length));
	}
	
	public static int answer(int start, int length) {
		int answer = 0;
		
		for (int i = 0; i < length; i++) {
			answer ^= xorInRange(start + i * length, start + (i + 1) * length - i - 1);
		}
		
		return answer;
	}
	
	public static int xor(int n) {
		int[] arr = new int[]{n, 1, n + 1, 0};
		return arr[Math.abs(n % 4)];
	}
	
	public static int xorInRange(int start, int end) {
		System.out.printf("start: %d, end: %d\n", start, end);
		if (start == 0) {
			start = 1;
		}
		if (start == end) {
			return start;
		}
		return xor(start - 1) ^ xor(end);
	}
}
