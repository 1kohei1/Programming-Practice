import java.util.*;

// Leetcode 99-892
// https://leetcode.com/contest/weekly-contest-99/problems/surface-area-of-3d-shapes/

public class Main {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int N = in.nextInt();
		int[][] grid = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				grid[i][j] = in.nextInt();
			}
		}
		
		System.out.println(surfaceArea(grid));
    }
	
    public static int surfaceArea(int[][] grid) {
        int answer = 0;
        int N = grid.length;
        
        int[][][] grid3 = new int[N][N][50];
        for (int i = 0; i < N; i++) {
        	for (int j = 0; j < N; j++) {
        		int K = grid[i][j];
        		for (int k = 0; k < K; k++) {
        			grid3[i][j][k] = 1;
        		}
        	}
        }
        
        for (int i = 0; i < N; i++) {
        	for (int j = 0; j < N; j++) {
        		int k = 0;
        		while (k < 50 && grid3[i][j][k] == 1) {
        			if ((k + 1 < 50 && grid3[i][j][k + 1] == 0) || k + 1 == 50) {
        				answer++;
        			}
        			if ((k - 1 >= 0 && grid3[i][j][k - 1] == 0) || k - 1 == -1) {
        				answer++;
        			}
        			if ((i - 1 >= 0 && grid3[i - 1][j][k] == 0) || i - 1 == -1) {
        				answer++;
        			}
        			if ((i + 1 < N && grid3[i + 1][j][k] == 0) || i + 1 == N) {
        				answer++;
        			}
        			if ((j - 1 >= 0 && grid3[i][j - 1][k] == 0) || j - 1 == -1) {
        				answer++;
        			}
        			if ((j + 1 < N && grid3[i][j + 1][k] == 0) || j + 1 == N) {
        				answer++;
        			}
        			k++;
        		}
        	}
        }
        
        return answer;
    }
}
