import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// uva 11742

public class Main {

	static int n, m;
	static long answer;
	static Constrain[] constrains;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String[] s;
		
		s = in.readLine().split(" ");
		n = to(s[0]);
		m = to(s[1]);
		
		while (n != 0 || m != 0) {
			
			answer = 0;
			constrains = new Constrain[m];
			
			for (int i = 0; i < m; i++) {
				s = in.readLine().split(" ");
				int a = to(s[0]);
				int b = to(s[1]);
				int dist = to(s[2]);
				
				int atMost = dist > 0 ? dist : n;
				int atLeast = dist > 0 ? 0: -1 * dist;
				
				constrains[i] = new Constrain(a, b, atLeast, atMost);
			}
			
			solve(0, new boolean[n], new int[n]);
			System.out.println(answer);
			
			s = in.readLine().split(" ");
			n = to(s[0]);
			m = to(s[1]);
		}
	}
	
	public static void solve(int index, boolean[] used, int[] perm) {
		if (index == n) {
//			printPerm(perm);
			if (isValid(perm)) {
				answer++;
			}
			return;
		}
		
		for (int i = 0; i < n; i++) {
			if (used[i] == false) {
				perm[index] = i;
				used[i] = true;
				solve(index + 1, used, perm);
				used[i] = false;
			}
		}
	}
	
	public static boolean isValid(int[] perm) {
		for (int i = 0; i < m; i++) {
			Constrain c = constrains[i];
			int a = c.a;
			int b = c.b;
			int atLeast = c.atLeast;
			int atMost = c.atMost;
			
			int dist = Math.abs(perm[a] - perm[b]);
			if (atLeast <= dist && dist <= atMost) {
				continue;
			} else {
				return false;
			}
		}
		return true;
	}
	
	public static void printPerm(int[] perm) {
		for (int i = 0; i < n; i++) {
			System.out.printf("%d ", perm[i]);
		}
		System.out.println();
	}
	
	public static int to(String s) {
		return Integer.parseInt(s);
	}
}

class Constrain {
	int a;
	int b;
	int atLeast;
	int atMost;
	
	public Constrain(int a, int b, int atLeast, int atMost) {
		super();
		this.a = a;
		this.b = b;
		this.atLeast = atLeast;
		this.atMost = atMost;
	}
}
