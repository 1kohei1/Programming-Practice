import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

// uva 469

public class Main {
	
	static char[][] map;
	static int[][] visited;
	static int[] dx = new int[]{-1, 0, 1, -1, 1, -1, 0, 1};
	static int[] dy = new int[]{-1, -1, -1, 0, 0, 1, 1, 1};
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
	
		int numTests = in.nextInt();
		in.nextLine();
		in.nextLine();
		ArrayList<String> temp = new ArrayList<String>();
		while (numTests-- > 0) {
			temp.clear();
			
			int numRows = 0;
			int numCols = 0;
			
			String line = in.nextLine();
			// Get map info
			while (line.charAt(0) == 'L' || line.charAt(0) == 'W') {
				temp.add(line);
				numRows++;
				numCols = line.length();
				line = in.nextLine();
			}
			
//			System.out.printf("numRows: %d, numCols: %d\n", numRows, numCols);
			
			// Create map
			map = new char[numRows][numCols];
			visited = new int[numRows][numCols];
			for (int i = 0; i < numRows; i++) {
				map[i] = temp.get(i).toCharArray();
			}
			
			// Get cell info
			while (true) {
				String[] array = line.split(" ");
				int r = new Integer(array[0]) - 1;
				int c = new Integer(array[1]) - 1;
				System.out.println(count(r, c));
				for (int i = 0; i < numRows; i++) {
					Arrays.fill(visited[i], 0);;
				}
				if (in.hasNext()) {
					line = in.nextLine();
				} else {
					break;
				}
				if (line.length() == 0) break;
			}
			
			if (numTests > 0) {
				System.out.println();
			}
		}
	}
	
	public static int count(int r, int c) {
		if (visited[r][c] == 1) {
			return 0;
		}
		int answer = 1;
		visited[r][c] = 1;
		
		for (int i = 0; i < dx.length; i++) {
			int newR = r + dx[i];
			int newC = c + dy[i];
			if (0 <= newR && newR < visited.length && 0 <= newC && newC < visited[0].length && map[newR][newC] == 'W') {
				answer += count(newR, newC);
			}
		}
		
		return answer;
	}
}