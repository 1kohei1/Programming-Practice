import java.awt.Point;
import java.util.Scanner;

public class Nquenns {

	static int count = 0;
	
	// An algorithm to count how many ways there exist to place n queens in n by n grid 
	public static void main(String[] args) {
		// Place 4 queens in 4 by 4 grid
		int n = 10;
		
		Point[] queenPos = new Point[n];
		placeQueen(queenPos, 0, n);
		System.out.println(count);
	}

	public static void placeQueen(Point[] queenPos, int index, int n) {
		if (index == n) {
			count++;
//			printPoints(queenPos, n);
			return;
		}
		for (int i = 0; i < n; i++) {
			// Place queen at (index, i)
			if (canQueenBePlaced(queenPos, new Point(index, i), index, n)) {
				queenPos[index] = new Point(index, i);
				placeQueen(queenPos, index + 1, n);
			}
		}
	}
	
	public static boolean canQueenBePlaced(Point[] queenPos, Point point, int index, int n) {
		// Diagonal directions
		int DIR_SIZE = 4;
		int[] dx = new int[]{-1, -1, 1, 1};
		int[] dy = new int[]{-1, 1, -1, 1};
		
		for (int i = 0; i < index; i++) {
			// Check horizontally and vertically
			if (queenPos[i].getX() == point.getX() || queenPos[i].getY() == point.getY()) {
				return false;
			}
			int row = (int) queenPos[i].getX();
			int col = (int) queenPos[i].getY();
			// Check diagonally
			for (int j = 0; j < DIR_SIZE; j++) {
				int nextRow = row + dx[j];
				int nextCol = col + dy[j];
				
				while (0 <= nextRow && nextRow < n && 0 <= nextCol && nextCol < n) {
					if (nextRow == (int) point.getX() && nextCol == (int) point.getY()) {
						return false;
					}
					nextRow += dx[j];
					nextCol += dy[j];
				}
			}
		}
		return true;
	}
	
	public static void printPoints(Point[] points, int n) {
		for (int i = 0; i < n; i++) {
			System.out.printf("(%d, %d), ", (int) points[i].getX(), (int) points[i].getY());
		}
		System.out.println();
	}
}
