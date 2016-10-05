import java.util.ArrayList;
import java.awt.Point;

public class Sudoku {

	// Solution for: https://codefights.com/challenge/XxZQ5vhhiBR5bHGrP/main
	static int length = 9;
	static boolean solved = false;
	static int[][] answer;
	
	static int[][] sudoku1 = {
					  {5,3,0,0,7,0,0,0,0}, 
	                  {6,0,0,1,9,5,0,0,0}, 
	                  {0,9,8,0,0,0,0,6,0}, 
	                  {8,0,0,0,6,0,0,0,3}, 
	                  {4,0,0,8,0,3,0,0,1}, 
	                  {7,0,0,0,2,0,0,0,6}, 
	                  {0,6,0,0,0,0,2,8,0}, 
	                  {0,0,0,4,1,9,0,0,5}, 
	                  {0,0,0,0,8,0,0,7,9}};
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		sudokuSolver(sudoku1);
	}

	public static void sudokuSolver(int[][] sudoku) {
		ArrayList<Point> points = new ArrayList<Point>();
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				if (sudoku[i][j] == 0) {
					points.add(new Point(i, j));
				}
			}
		}
		answer = new int[length][length];
		solve(0, points, sudoku);
		print(answer);
	}
	
	public static void solve(int index, ArrayList<Point> points, int[][] sudoku) {
		if (solved) {
	        return;
	    }
	    if (index == points.size()) {
	        solved = true;
	        for (int i = 0; i < length; i++) {
	        	for (int j = 0; j < length; j++) {
	        		answer[i][j] = sudoku[i][j];
	        	}
	        }
	        return;
	    }
	    
	    Point p = points.get(index);
	    
	    for (int i = 1; i <= length; i++) {
	        if (canPlace(sudoku, p.x, p.y, i)) {
	        	sudoku[p.x][p.y] = i;
	            solve(index + 1, points, sudoku);
	            sudoku[p.x][p.y] = 0;
	        }
	    }
	}
	
	static boolean canPlace(int[][] sudoku, int r, int c, int v) {
		// Horizontally
		for (int i = 0; i < length; i++) {
			if (sudoku[r][i] == v) return false;
		}
		// Vertically
		for (int i = 0; i < length; i++) {
			if (sudoku[i][c] == v) return false;
		}
		// Block
	    int blockR = r / 3;
	    int blockC = c % 3;
	    
	    for (int i = blockR * 3; i < blockR * 3 + 3; i++) {
	        for (int j = c - blockC; j < c - blockC + 3; j++) {
	        	if (sudoku[i][j] == v) return false;
	        }
	    }
	    return true;
	}

	public static void print(int[][] sudoku) {
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				System.out.printf("%d ", sudoku[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
}