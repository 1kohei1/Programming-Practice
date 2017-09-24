import java.util.*;

// CODEFES 2017.qualA-D
// http://code-festival-2017-quala.contest.atcoder.jp/tasks/code_festival_2017_quala_d

public class Main {
	
	public static void main (String[] args) throws InterruptedException {
		Scanner in = new Scanner(System.in);
		
		int H = in.nextInt();
		int W = in.nextInt();
		int d = in.nextInt();
		
		StringBuilder answer = new StringBuilder();
		
		if (d == 1) {
			char[][] color = new char[][]{{'R', 'G'}, {'G', 'R'}};
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					answer.append(color[i % 2][j % 2]);
				}
				answer.append("\n");
			}
		} else if (d == 2) {
			char[][] color = new char[][]{
				{'R', 'R', 'B', 'B'}, 
				{'G', 'G', 'Y', 'Y'}, 
				{'B', 'B', 'R', 'R'},
				{'Y', 'Y', 'G', 'G'}
			};
			
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					answer.append(color[i % 4][j % 4]);
				}
				answer.append("\n");
			}
		} else if (d % 2 == 1) {
			char[][] color = new char[2 * d][2 * d];
			
			char[] gr = new char[]{'G', 'R'};
			char[] by = new char[]{'B', 'Y'};
			
			for (int x = 0; x < 2; x++) {
				for (int y = 0; y < 2; y++) {
					int offset = (x + y) % 2;
					
					for (int j = 0; j < d; j++) {
						int numBY = Math.min(j,  d - j);
						int index = 0;
						while (index < numBY) {
							color[index + d * x][j + d * y] = by[offset];
							index++;
						}
						int numGR = d - 2 * numBY;
						while (index < numBY + numGR) {
							if (j <= d / 2) {
								color[index + d * x][j + d * y] = gr[offset];
							} else {
								color[index + d * x][j + d * y] = gr[(offset + 1) % 2];
							}
							
							index++;
						}
						
						while (index < d) {
							color[index + d * x][j + d * y] = by[(offset + 1) % 2];
							index++;
						}
					}
				}
			}
			
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					answer.append(color[i % (2 * d)][j % (2 * d)]);
				}
				answer.append("\n");
			}
		} else {
			char[][] color = new char[2 * d][2 * d];
			
			char[] rg = new char[]{'R', 'G'};
			char[] by = new char[]{'B', 'Y'};
			
			for (int x = 0; x < 2; x++) {
				for (int y = 0; y < 2; y++) {
					int offset = (x + y) % 2;
					
					for (int j = 0; j < d; j++) {
						int numRG = Math.abs(j - d / 2);
						int rgOffset = offset;
						if (j >= d / 2) {
							numRG++;
							rgOffset = (rgOffset + 1) % 2;
						}
						int otherNumRG = numRG - 1;
						
						int index = 0;
						while (index < numRG) {
							color[index + d * x][j + d * y] = rg[rgOffset];
							index++;
						}
						while (index + otherNumRG < d) {
							color[index + d * x][j + d * y] = by[offset];
							index++;
						}
						while (index < d) {
							color[index + d * x][j + d * y] = rg[(rgOffset + 1) % 2];
							index++;
						}
					}
				}
			}
			
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					answer.append(color[i % (2 * d)][j % (2 * d)]);
				}
				answer.append("\n");
			}
		}
		System.out.println(answer);
	}
}
