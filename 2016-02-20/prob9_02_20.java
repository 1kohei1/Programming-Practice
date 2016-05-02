import java.util.Scanner;

public class prob9_02_20 {

	static int size = 9;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int numTests = scanner.nextInt();
		scanner.nextLine();
		
		for (int counter = 0; counter < numTests; counter++) {
			int[][] grid = new int[size][size];
			
			String[] moves = scanner.nextLine().split(" ");
			boolean isAllValid = true;
			
			int boxRow = -1;
			int boxCol = -1;
			int[][] boxStatus = new int[3][3];
			
			for (int i = 0; i < moves.length; i++) {
				int num = Integer.parseInt(moves[i]);
				int row = num / 10;
				int col = num % 10;
				
				// Out of the box
				if (row >= size || col >= size) {
					isAllValid = false;
				}
				// Is it placed in the right box
				else if (boxRow >= 0 && boxCol >= 0 && !(boxRow * 3 <= row && row < (boxRow + 1) * 3 && boxCol * 3 <= col && col < (boxCol + 1) * 3)) {
					isAllValid = false;
				}
				// If it is already token
				else if (grid[row][col] != 0) {
					isAllValid = false;
				}
				// Going cell is already completed, you cannot go
				// I'm not convinced this should be invalid, though
				else if (boxStatus[row / 3][col / 3] != 0) {
					isAllValid = false;
				}
				if (!isAllValid) {
					System.out.printf("Player %s made an invalid move on turn %d.\n", i % 2 == 0 ? "one" : "two", i);
					break;
				} else {
					grid[row][col] = i % 2 + 1;
					// Update boxRow and boxCol
					boolean isEmptyCellLeft = false;
					for (int j = (row / 3) * 3; j < (row / 3) * 3 + 3; j++) {
						for (int k = (col / 3) * 3; k < (col / 3) * 3 + 3; k++) {
							if (j == row && k == col) {
								boxRow = j - (row / 3) * 3;
								boxCol = k - (col / 3) * 3;
							}
							if (grid[j][k] == 0) {
								isEmptyCellLeft = true;
							}
						}
					}
					if (!isEmptyCellLeft) {
						boxStatus[row / 3][col / 3] = 3;
					}
					if (isThisBoxComplete(grid, row / 3, col / 3, i % 2 + 1)) { 
						boxStatus[row / 3][col / 3] = i % 2 + 1;
					}
					if (boxStatus[boxRow][boxCol] != 0) {
						boxRow = -1;
						boxCol = -1;
					}
				}
//				if (isGameOver(boxStatus)) {
//					break;
//				}
			}
			if (isAllValid) {
				System.out.println("All moves were valid.");
			}
		}
	}
/*
6
00 00 00
44 53 60 22 77 63
44 53 60 22 77 43 50 06
44 53 60 22 77 43 50 71 55 66 11 33 87
44 53 71 54 74 55 77 53
44 33 22 78 38 07 23 70 51 83 72 47 43 41 45 57 84 73 50 60 02 17 52 88 67 04 24 63 12 37 05 27 68 80 66
 */

	public static boolean isGameOver(int[][] boxStatus) {
		// Horizontal
		for (int i = 0; i < 3; i++) {
			if (boxStatus[i][0] != 0 && boxStatus[i][0] == boxStatus[i][1] && boxStatus[i][0] == boxStatus[i][2]) {
				return true;
			}
		}
		
		// Vertical
		for (int i = 0; i < 3; i++) {
			if (boxStatus[0][i] != 0 && boxStatus[0][i] == boxStatus[1][i] && boxStatus[0][i] == boxStatus[2][i]) {
				return true;
			}
		}
		
		// Diagonal, from left top to right bottom
		if (boxStatus[0][0] != 0 && boxStatus[0][0] == boxStatus[1][1] && boxStatus[0][0] == boxStatus[2][2] ||
			boxStatus[1][1] != 0 && boxStatus[2][0] == boxStatus[1][1] && boxStatus[1][1] == boxStatus[0][2]) {
			return true;
		}
		return false;
	}
	
	public static boolean isThisBoxComplete(int[][] grid, int boxRow, int boxCol, int num) {
		int row = boxRow * 3;
		int col = boxCol * 3;
		
		// Horizontal
		for (int i = row; i < row + 3; i++) {
			if (grid[i][col] == num && grid[i][col + 1] == num && grid[i][col + 2] == num) {
				return true;
			}
		}
		
		// Vertical
		for (int i = col; i < col + 3; i++) {
			if (grid[row][i] == num && grid[row + 1][i] == num && grid[row + 2][i] == num) {
				return true;
			}
		}
		
		// diagonal, left top to right bottom
		if (grid[row][col] == num && grid[row + 1][col + 1] == num && grid[row + 2][col + 2] == num) {
			return true;
		}
		// diagonal, left bottom to right top
		if (grid[row + 2][col] == num && grid[row + 1][col + 1] == num && grid[row][col + 2] == num) {
			return true;
		}
		return false;
	}
}
