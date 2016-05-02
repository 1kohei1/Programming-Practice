import java.util.Scanner;

// Arup's code: http://www.cs.ucf.edu/~dmarino/progcontests/weecs-12/lectures/10-16/approach.java

public class approach {

	static int n;
	static int permCount;
	static int[] fact;
	static int[][] perm;
	static Plane[] planes;
	
	static int low;
	static int high;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		fact = new int[9];
		fact[0] = 0;
		fact[1] = 1;
		for (int i = 2; i < 9; i++) {
			fact[i] = fact[i - 1] * i;
		}
		
		int counter = 1;
		while(true) {
			n = scanner.nextInt();
			if (n == 0) {
				break;
			}
			
			planes = new Plane[n];
			for (int i = 0; i < n; i++) {
				planes[i] = new Plane(scanner.nextInt() * 60, scanner.nextInt() * 60);
			}
			
			perm = new int[fact[n]][n];
			boolean[] used = new boolean[n];
			permCount = 0;
			fillPerm(0, used, new int[n]);
			
//			printPerm();
			low = 0;
			high = 1440 * 60;
			
			int mid = solve();
//			mid = (low + high) / 2;

//			System.out.printf("(After solving) low: %d, high: %d, mid: %d\n", low, high, mid);
			System.out.printf("Case %d: %d:%02d\n", counter, mid / 60, mid % 60);
			
			counter++;
		}
	}
/*
3
0 10
5 15
10 15
2
0 10
10 20
0

2
0 10
0 10

2
0 1440
0 0

3
0 5
5 15
15 30

2
0 10
20 30

3
0 6
3 9
9 12

3
0 2
1 3
2 5

3
0 6
2 3
6 12

3
0 10
5 7
7 10

3
0 3
1 4
2 5

=========== INCORRECT RESULT ===========
0
 */
	public static int solve() {
		int mid = (low + high) / 2;
		for (int i = 0; i < 100; i++) {
			if (low > high) {
				return mid;
			}
			mid = (low + high) / 2;
			System.out.printf("low: %d, high: %d, mid: %d\n", low, high, mid);
			if (isThisValid(mid)) {
//				System.out.println("Increase low");
				low = mid + 1;
			} else {
//				System.out.println("Decrease high");
				high = mid - 1;
			}
		}
		return mid;
	}
	
	public static boolean isThisValid(int interval) {
		for (int i = 0; i < perm.length; i++) {
			if (doesPass(interval, planes[perm[i][0]].start, i, 0)) {
				System.out.printf("interval: %d is valid for: ", interval);
				for (int j = 0; j < n; j++) {
					System.out.printf("%d ", perm[i][j]);
				}
				System.out.println();
				return true;
			}
		}
		return false;
	}
	
	public static boolean doesPass(int interval, int sec, int permIndex, int index) {
		System.out.printf("interval: %d, sec: %d, index: %d\n", interval, sec, index);
		if (index == n - 1) {
			return sec <= planes[perm[permIndex][index]].end;
		}
		if (sec + interval < planes[perm[permIndex][index + 1]].start) {
			System.out.printf("Land plane index %d at %ss\n", index + 1, planes[perm[permIndex][index + 1]].start);
			return doesPass(interval, planes[perm[permIndex][index + 1]].start, permIndex, index + 1);
		} else if (sec + interval <= planes[perm[permIndex][index + 1]].end) {
			System.out.printf("Land plane index %d at %ss\n", index + 1, sec + interval);
			return doesPass(interval, sec + interval, permIndex, index + 1);
		} else {
			return false;
		}
	}
	
	public static void fillPerm(int index, boolean[] used, int[] nums) {
		if (index == n) {
			perm[permCount] = nums.clone();
			permCount++;
			return;
		}
		for (int i = 0; i < n; i++) {
			if (used[i] == false) {
				nums[index] = i;
				used[i] = true;
				fillPerm(index + 1, used, nums);
				used[i] = false;
			}
		}
	}
	
	public static void printPerm() {
		for (int i = 0; i < perm.length; i++) {
			for (int j = 0; j < perm[i].length; j++) {
				System.out.printf("%d ", perm[i][j]);
			}
			System.out.println();
		}
	}
}

class Plane {
	int start;
	int end;
	
	public Plane(int s, int e) {
		start = s;
		end = e;
	}
}