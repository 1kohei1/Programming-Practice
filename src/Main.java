import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// uva 750

public class Main {

	static PriorityQueue<Solution> pq;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int nt = to(in.readLine());
		boolean isFirst = true;
		pq = new PriorityQueue<Solution>();
		
		while (nt-- > 0) {
			in.readLine();

			String s = in.readLine();
			
			// Get input
			String[] ss = s.split(" ");
			int row = to(ss[0]) - 1;
			int col = to(ss[1]) - 1;

			int[] sol = new int[8];
			Arrays.fill(sol, -1);
			sol[row] = col;
			
			pq.clear();
			
			// Solve
			solve(0, sol);

			if (isFirst) {
				isFirst = false;
			} else {
				System.out.println();
			}
			
			printHeader();
			int count = 1;
			while (pq.size() > 0) {
				printSolution(count, pq.poll());
				count++;
			}
			
		}
	}
	
	public static void solve(int row, int[] sol) {
//		System.out.printf("row: %d, sol: %s\n", row, arrayToString(sol));
		
		if (row == 8) {
			pq.add(new Solution(flipArray(sol)));
			return;
		}
		// Initial value
		if (sol[row] != -1) {
			solve(row + 1, sol);
			return;
		}
		
		for (int i = 0; i < 8; i++) {
			if (canPlace(sol, row, i)) {
				sol[row] = i;
				solve(row + 1, sol);
				sol[row] = -1;
			}
		}
	}
	
	public static String arrayToString(int[] a) {
		String s = "";
		for (int i = 0; i < 8; i++) {
			s += "" + (a[i] + 1);
			if (i != 7) {
				s += " ";
			}
		}
		return s;
	}
	
	public static boolean canPlace(int[] sol, int row, int col) {
		for (int i = 0; i < 8; i++) {
			if (sol[i] != -1) {
				if (i == row || sol[i] == col || Math.abs(i - row) == Math.abs(sol[i] - col)) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static int[] flipArray(int[] a) {
		int[] nums = new int[a.length];
		for (int i = 0; i < a.length; i++) {
			nums[a[i]] = i;
		}
		return nums;
	}
	
	public static void printHeader() {
		System.out.println("SOLN       COLUMN");
		System.out.println(" #      1 2 3 4 5 6 7 8");
		System.out.println();
	}
	
	public static void printSolution(int count, Solution s) {
		System.out.printf("%2d      %s\n", count, s.arrayToString());
	}
	
	public static int to(String s) {
		return Integer.parseInt(s);
	}
	
}

class Solution implements Comparable<Solution> {
	int[] solution;
	public Solution(int[] s) {
		solution = s;
	}
	
	public int compareTo(Solution o) {
		for (int i = 0; i < 8; i++) {
			if (solution[i] != o.solution[i]) {
				return solution[i] - o.solution[i];
			}
		}
		return 0;
	}
	
	public String arrayToString() {
		String s = "";
		for (int i = 0; i < 8; i++) {
			s += "" + (solution[i] + 1);
			if (i != 7) {
				s += " ";
			}
		}
		return s;
	}

}