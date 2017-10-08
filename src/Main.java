import java.util.*;

// DDCC 2017-D
// http://ddcc2017-qual.contest.atcoder.jp/tasks/ddcc2017_qual_d	

public class Main {

	public static void main (String[] args) throws InterruptedException {
		Scanner in = new Scanner(System.in);

		int H = in.nextInt();
		int W = in.nextInt();
		int A = in.nextInt();
		int B = in.nextInt();
		
		int[][] map = new int[H][W];
		int numStones = 0;
		
		for (int i = 0; i < H; i++) {
			char[] c = in.next().toCharArray();
			for (int j = 0; j < W; j++) {
				if (c[j] == 'S') {
					map[i][j] = 1;
					numStones++;
				}
			}
		}
		
		int cornerPair = 0;
		int tatePair = 0;
		int yokoPair = 0;
		
		for (int i = 0; i < H / 2; i++) {
			for (int j = 0; j < W / 2; j++) {
				int curr = map[i][j];
				int yoko = map[i][W - j - 1];
				int tate = map[H - i - 1][j];
				int oku = map[H - i - 1][W - j - 1];
				
				if (curr == 1 && yoko == 1 && tate == 1 && oku == 1) {
					cornerPair++;
				} else {
					if ((curr == 1 && yoko == 1) || (tate == 1 && oku == 1)) {
						yokoPair++;
					}
					if ((curr == 1 && tate == 1) || (yoko == 1 && oku == 1)) {
							tatePair++;
					}
				}
			}
		}
		
		// 石を一つ取り、ペアが崩れる可能性を考える
		if (cornerPair * 4 + (tatePair + yokoPair) * 2 == numStones) {
			if (tatePair + yokoPair > 0) {
				if (A < B) {
					if (tatePair > 0) {
						tatePair--;
					} else {
						yokoPair--;
					}
				} else {
					if (yokoPair > 0) {
						yokoPair--;
					} else {
						tatePair--;
					}
				}
			} else {
				cornerPair--;
				if (A > B) {
					tatePair++;
				} else {
					yokoPair++;
				}
			}
		}
		
		int answer = (A + B + Math.max(A, B)) * cornerPair + Math.max(A * tatePair, B * yokoPair) + A + B;
		System.out.println(answer);
	}
}
