import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;

// uva 10004

public class Main {

	static int[][] map;
	static int[] color;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
	
		int n = in.nextInt();
		while (n != 0) {
			int l = in.nextInt();
			
			map = new int[n][n];
			color = new int[n]; // Let use 1 and 2 to indicate bicolor
			
			for (int i = 0; i < l; i++) {
				int a = in.nextInt();
				int b = in.nextInt();
				map[a][b] = 1;
				map[b][a] = 1;
			}
			
			if (isBicolord()) {
				System.out.println("BICOLORABLE.");
			} else {
				System.out.println("NOT BICOLORABLE.");
			}
			
			
			n = in.nextInt();
		}
	}

	public static boolean isBicolord() {
		// Use color 1 at index 0
		color[0] = 1;
		
		ArrayList<Integer> q = new ArrayList<Integer>();
		q.add(0);
		
		while (q.size() > 0) {
			int curr = q.remove(0);
			int nextColor = color[curr] == 1 ? 2 : 1;
			
			for (int i = 0; i < map.length; i++) {
				// If there is an edge and it is not colored yet.
				if (map[curr][i] == 1 && color[i] == 0) {
					// Check if neighbor node is already assigned a color. If they are assigned, make sure that color is different from nextColor
					for (int j = 0; j < map.length; j++) {
						if (map[i][j] == 1 && color[j] == nextColor) {
							return false;
						}
					}
					q.add(i);
					color[i] = nextColor;
				}
			}
		}
		return true;
		
	}
	
}