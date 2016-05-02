import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class circuits {

	public static Double[][] map;
	public static int[] freq;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			int n = scanner.nextInt();
			if (n == 0) {
				break;
			}
			
			freq = new int[26];
			map = new Double[26][26];
			for (int i = 0; i < 26; i++) {
				Arrays.fill(map[i], null);
			}
			
			for (int i = 0; i < n; i++) {
				int start = scanner.next().toCharArray()[0] - 'A';
				int end = scanner.next().toCharArray()[0] - 'A';
				double dist = scanner.nextDouble();
				
				if (map[start][end] == null) {
					freq[start]++;
					freq[end]++;
				}
				
				place(start, end, dist);
			}
			
			// Connect in series
			for (int i = 1; i < 25; i++) {
				if (freq[i] == 2) {
					// Think p - i -q
					int p = -1;
					int q = -1;
					for (int j = 0; j < 26; j++) {
						if (j != i) {
							if (map[i][j] != null && p == -1) {
								p = j;
							}
							if (map[i][j] != null && q == -1) {
								q = j;
							}
						}
					}
					if (p == -1 || q == -1) {
						System.out.println("Something unexpected happen");
					} else {
						place(p, q, map[p][i] + map[i][q]);
						map[p][i] = null;
						map[i][p] = null;
						map[i][q] = null;
						map[q][i] = null;
						freq[i] = 0;
					}
				}
			}

			printFreq();
			boolean isWellFormed = freq[0] == 1 && freq[25] == 1;
			for (int i = 1; i < 25; i++) {
				if (freq[i] != 0) {
					isWellFormed = false;
				}
			}
			if (isWellFormed) {
				System.out.printf("%.3f\n", map[0][25]);
			} else {
				System.out.println("-1.000");
			}
		}
	}
	
	public static void place(int i, int j, double dist) {
		if (map[i][j] == null) {
			map[i][j] = dist;
			map[j][i] = dist;
		} else {
			double newDist = 1 / (1 / map[i][j] + 1 / dist);
			map[i][j] = newDist;
			map[j][i] = newDist;
		}
	}
	
	public static void printMap() {
		for (int i = 0; i < 26; i++) {
			for (int j = 0; j < 26; j++) {
				System.out.printf("%.3f ", map[i][j]);
			}
			System.out.println();
		}
	}
	
	public static void printFreq() {
		for (int i = 0; i < 26; i++) {
			System.out.printf("%d ", freq[i]);
		}
		System.out.println();
	}
/*
9
A B 1
B E 1
E Z 1
A C 1
C F 1
F Z 1
A D 1
D G 1
G Z 1

2
A B 5
B Z 8

4
A B 1
B Z 1
A C 1
C Z 1

7
A B 1
B C 1
B D 1
B E 1
C Z 1
D Z 1
E Z 1

8
N R 2
D R 3
R N 2
R D 10
Z R 7
C D 9
N C 6
A N 4
2
A Z 3
Z A 10
2
P A 6
P Z 9
5
A B 1
B Z 4
A C 8
C Z 19
B C 12
0
 */
}
