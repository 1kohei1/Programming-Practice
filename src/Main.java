import java.util.*;

public class Main {
	
	public static void main (String[] args) throws java.lang.Exception {
		Scanner in = new Scanner(System.in);
		
		int n = in.nextInt();
		int MAX = 1000001;
		int[] colors = new int[MAX + 1];
		
		for (int i = 0; i < n; i++) {
			colors[in.nextInt()]++;
			colors[in.nextInt() + 1]--;
		}
		
		int answer = colors[0];
		
		for (int i = 1; i < MAX; i++) {
			colors[i] += colors[i - 1];
			answer = Math.max(answer, colors[i]);
		}
		System.out.println(answer);
	}
}