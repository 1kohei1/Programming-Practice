import java.util.Scanner;

public class MatrixSpiralPrint {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (true) {
			int row = in.nextInt();
			int col = in.nextInt();
			
			if (row == 0 && col == 0) {
				break;
			}
			int[][] arr = new int[row][col];
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					arr[i][j] = in.nextInt();
				}
			}
			
			printMatrixInSpin(arr);
			System.out.println();
		}

	}

	public static void printMatrixInSpin(int[][] arr) {
		if (arr == null || arr.length == 0 || arr[0].length == 0) {
			return;
		}
		
		int rowStart = 0;
		int rowEnd = arr.length - 1;
		int colStart = 0;
		int colEnd = arr[0].length - 1;
		
		while (rowStart <= rowEnd && colStart <= colEnd) {
			// Left to right
			for (int i = colStart; i <= colEnd; i++) {
				System.out.printf("%d ", arr[rowStart][i]);
			}
			rowStart++;
			// Top to bottom
			for (int i = rowStart; i <= rowEnd; i++) {
				System.out.printf("%d ", arr[i][colEnd]);
			}
			colEnd--;
//			if (rowStart <= rowEnd) {
				// Right to left
				for (int i = colEnd; i >= colStart; i--) {
					System.out.printf("%d ", arr[rowEnd][i]);
				}
//			}
			rowEnd--;
			
//			if (colStart <= colEnd) {
				// Bottom to top
				for (int i = rowEnd; i >= rowStart; i--) {
					System.out.printf("%d ", arr[i][colStart]);
				}
//			}
			colStart++;
		}
	}
}
