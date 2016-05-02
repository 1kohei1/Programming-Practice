import java.util.Scanner;

public class chocolate {
	//                         Representing SV, SS, SC, RV, RS, RC, TV, TS, TC
	public static int[] truffle = new int[]{11, 12, 13, 21, 22, 23, 31, 32, 33};
	static int[][][] clue;
	static int[][] answer;
	static boolean isSolved;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int numPuzzle = scanner.nextInt();
		for (int counter = 1; counter <= numPuzzle; counter++) {
			int numClue = scanner.nextInt();
			
			clue = new int[numClue][][];
			
			for (int i = 0; i < numClue; i++) {
				int row = scanner.nextInt();
				int col = scanner.nextInt();
				
				clue[i] = new int[row][col];
				for (int j = 0; j < row; j++) {
					for (int k = 0; k < col; k++) {
						String s = scanner.next();
						clue[i][j][k] = convertToCode(s);
					}
				}
			}
			
			answer = new int[3][3];
			isSolved = false;
			
			solve(0, 0, new boolean[9]);
			
			System.out.println("Puzzle #" + counter + ":");
			printAnswer();
		}
	}
	
	public static void solve(int r, int c, boolean[] used) {
		if (isSolved) {
			return;
		}
		if (r == 3) {
			isSolved = true;
			return;
		}
		for (int i = 0; i < 9 && !isSolved; i++) {
			if (used[i] == false) {
				used[i] = true;
				answer[r][c] = truffle[i];
//				printAnswer();
//				for (int j = 0; j < 9; j++) {
//					System.out.printf("%s: %s, ", convertToString(truffle[j]), used[j] ? "true" : "false");
//				}
//				System.out.println("\n");
				
				
				if (didPassClue() && !isSolved) {
					if (c == 2) {
						solve(r + 1, 0, used);
					} else {
						solve(r, c + 1, used);
					}
				}
				if (!isSolved) {
					answer[r][c] = 0;						
					used[i] = false;
				}
			}
		}
	}
/*
3
4
3 3
TC __ SS
__ __ __
__ TV __
2 3
__ SC __
RV __ SV
3 3
__ __ __
__ RC __
__ __ __
2 3
__ __ __
TS __ RS
5
2 3
__ __ __
__ __ RC
2 2
__ RS
SC __
2 2
SV TC
__ __
3 2
TV __
__ __
__ RV
3 2
__ TS
__ __
__ __
3
3 2
_C R_
_C __
S_ _C
1 2
TC _V
3 2
_V __
S_ S_
T_ _V
 */
	
	public static boolean didPassClue() {
		for (int i = 0; i < clue.length; i++) {
			int row = clue[i].length;
			int col = clue[i][0].length;
			boolean didPassIthTestCase = false;
			
			// Move clue box
			for (int j = 0; j + row <= 3 && !didPassIthTestCase; j++) {
				for (int k = 0; k + col <= 3 && !didPassIthTestCase; k++) {
					
					boolean didPass = true;
					
					for (int clueRow = 0; clueRow < row; clueRow++) {
						for (int clueCol = 0; clueCol < col; clueCol++) {
							if (clue[i][clueRow][clueCol] != 0) {
								if (clue[i][clueRow][clueCol] / 10 != 0 && answer[j + clueRow][k + clueCol] / 10 != 0 && clue[i][clueRow][clueCol] / 10 != answer[j + clueRow][k + clueCol] / 10) {
									didPass = false;
								}
								if (clue[i][clueRow][clueCol] % 10 != 0 && answer[j + clueRow][k + clueCol] % 10 != 0 && clue[i][clueRow][clueCol] % 10 != answer[j + clueRow][k + clueCol] % 10) {
									didPass = false;
								}
							}
							
						}
					}
					
					if (didPass) {
//						System.out.printf("Passed test clue %d at (%d, %d)\n", i, j, k);
						didPassIthTestCase = true;
						
					} else {
//						System.out.printf("Failed test clue %d at (%d, %d)\n", i, j, k);
						
					}
				}
			}
			
			if (!didPassIthTestCase) {
//				System.out.println("Didn't pass " + i + " clue\n");
				return false;
			}
		}
		return true;
	}
	
	public static int convertToCode(String s) {
		char[] c = s.toCharArray();
		int num = 0;
		switch(c[0]) {
		case 'S':
			num = 10;
			break;
		case 'R':
			num = 20;
			break;
		case 'T':
			num = 30;
			break;
		default:
			break;
		}
		
		switch(c[1]) {
		case 'V':
			num += 1;
			break;
		case 'S':
			num += 2;
			break;
		case 'C':
			num += 3;
			break;
		}

		return num;
	}
	
	public static String convertToString(int num) {
		String s = "";
		
		switch (num / 10) {
		case 1:
			s += "S";
			break;
		case 2:
			s += "R";
			break;
		case 3:
			s += "T";
			break;
		default: 
			s += "_";
			break;
		}
		
		switch (num % 10) {
		case 1:
			s += "V";
			break;
		case 2:
			s += "S";
			break;
		case 3:
			s += "C";
			break;
		default:
			s += "_";
			break;
		}
		
		return s;
	}

	public static void printClue() {
		for (int i = 0; i < clue.length; i++) {
			for (int j = 0; j < clue[i].length; j++) {
				for (int k = 0; k < clue[i][j].length; k++) {
					System.out.printf("%d ", clue[i][j][k]);
				}
				System.out.println();
			}
			System.out.println();
		}
		System.out.println();
		
//		for (int i = 0; i < clue.length; i++) {
//			for (int j = 0; j < clue[i].length; j++) {
//				for (int k = 0; k < clue[i][j].length; k++) {
//					System.out.printf("%s ", convertToString(clue[i][j][k]));
//				}
//				System.out.println();
//			}
//			System.out.println();
//		}
//		System.out.println();
	}

	public static void printAnswer() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.printf("%s ", convertToString(answer[i][j]));
			}
			System.out.println();
		}
		System.out.println();
	}
}
