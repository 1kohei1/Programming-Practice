import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class prob8 {

	static ArrayList<Pair> pairs;
	
	// knapsack
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			int weight = scanner.nextInt();
			if (weight == 0) {
				break;
			}
			int n = scanner.nextInt();
			
			pairs = new ArrayList<Pair>();
			String s = scanner.nextLine();
			
			for (int i = 0; i < n; i++) {
				int index1 = s.indexOf('(');
				int index2 = s.indexOf(',');
				int index3 = s.indexOf(')');
				Integer a = new Integer(s.substring(index1 + 1, index2));
				Integer b = new Integer(s.substring(index2 + 2, index3));

				if (i != n - 1) {
					s = s.substring(index3 + 1);
				}
				pairs.add(new Pair(a, b));
			}
			// Want to sort nums and minutes. Allowing duplicate nums.
			pairs.sort(null);
			
			int[] B = new int[weight + 1];
			int[][] numStatus = new int[weight + 1][n];
			Arrays.fill(B, 0);
			Arrays.fill(numStatus, new int[n]);
			
			for (int k = 0; k < n; k++) {
				Pair p = pairs.get(k);
				int m = p.minutes;
				for (int w = m; w <= weight; w++) {
					int[] nums2 = numStatus[w - m].clone();
					
					if (nums2[k] == p.nums) {
						B[w] = B[w - 1];
						numStatus[w] = numStatus[w - 1];
						continue;
					}
					
					if (B[w] < 1 + B[w - m]) {
						B[w] = 1 + B[w - m];
						nums2[k]++;
						numStatus[w] = nums2;
					} else if (B[w] == 1 + B[w - m]) {
						int[] nums3 = numStatus[w].clone();
						nums2[k]++;
						int num2Minutes = calculate(nums2);
						int num3Minutes = calculate(nums3);
						
						if (num2Minutes < num3Minutes) {
							numStatus[w] = nums2;
						} else {
							numStatus[w] = nums3;
						}
					}
				}
//				for (int i = 0; i <= weight; i++) {
//					System.out.printf("[%2d]: %2d | ", i, B[i]);
//					printArray(numStatus[i]);
//				}
//				System.out.println();
			}
			
			int index = weight;
			while (index > 0 && B[index] == 0) {
				index--;
			}
			int max = B[index];
			int minMinutes = calculate(numStatus[index]);
			while(index > 0 && B[index] == max) {
				int m = calculate(numStatus[index]);
				if (m < minMinutes) {
					minMinutes = m;
				}
				index--;
			}
			
			System.out.println(max + " " + minMinutes);
		}
	}
/*
20 3 (10, 1) (5, 5) (8, 2)
50 2 (12, 4) (42, 3)
12 4 (1, 4) (3, 5) (1, 3) (1, 1)
47 4 (1, 10) (3, 5) (2, 7) (3, 3)
33 2 (100, 5) (200, 2) 
100 1 (1, 5)
 */

	public static int calculate(int[] nums2) {
		int m = 0;
		for (int i = 0; i < nums2.length; i++) {
			m += pairs.get(i).minutes * nums2[i];
		}
		return m;
	}
	
	public static void printArray(int[] nums2) {
		for (int i = 0; i < nums2.length; i++) {
			System.out.printf("%d, ", nums2[i]);
		}
		System.out.println();
	}
}

class Pair implements Comparable<Pair> {
	int nums;
	int minutes;
	
	public Pair(int x, int y) {
		nums = x;
		minutes = y;
	}

	public int compareTo(Pair o) {
		if (this.minutes < o.minutes) {
			return -1;
		}
		if (this.minutes > o.minutes) {
			return 1;
		}
		return 0;
	}
	
	public String toString() {
		return "(" + minutes + ", " + nums + ")";
	}
}