import java.util.Scanner;
import java.util.ArrayList;

public class maze {

	static int[] dx = new int[]{0, -1, 0, 1};
	static int[] dy = new int[]{-1, 0, 1, 0};
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int n = scanner.nextInt();
		for (int counter = 1; counter <= n; counter++) {
			int row = scanner.nextInt();
			int col = scanner.nextInt();
			char[][] map = new char[row][col];
			char[][] visited = new char[row][col];
			
			int startRow = -1;
			int startCol = -1;
			int front = -1;
			
			for (int i = 0; i < row; i++) {
				char[] line = scanner.next().toCharArray();
				for (int j = 0; j < col; j++) {
					if (line[j] == 'S') {
						startRow = i;
						startCol = j;
						
						if (startRow == 0 && startCol != 0 && startCol != col - 1) {
							front = 3;
						} else if (startCol == 0 && startRow != 0 && startRow != row - 1) {
							front = 2;
						} else if (startRow == row - 1 && startCol != 0 && startCol != col - 1) {
							front = 1;
						} else {
							front = 0;
						}
					}
				}
				map[i] = line;
				visited[i] = line.clone();
			}
			
			int result = solve(map, startRow, startCol, front);
			if (result == -1) {
				System.out.printf("Maze #%d: Impossible\n", counter);
			} else {
				System.out.printf("Maze #%d: %d seconds\n", counter, result * 5);
			}
			
		}
	}
/*
2
10 10
########E#
#.....#..#
##.##...##
#..#..#..#
#.##.#####
#.#......#
#.######.#
#....#...#
#.##.#.#.#
#S########

5 5
#E###
#.#.#
###.#
#...#
#S###

3 3
#S#
###
#E#
*/
	public static int solve(char[][] map, int startRow, int startCol, int front) {
		int newX = startRow;
		int newY = startCol;
		
		int count = 0;
		while (true) {
			if (map[newX][newY] == 'E') {
				return count;
			}
			
			count++;
			// check right
			int right = convert(front + 1);
			if (map[newX + dx[right]][newY + dy[right]] == 'S') {
				return -1;
			}
			if (map[newX + dx[right]][newY + dy[right]] == '.' || map[newX + dx[right]][newY + dy[right]] == 'E') {
				newX += dx[right];
				newY += dy[right];
				front = right;
				continue;
			}
			// check front
			if (map[newX + dx[front]][newY + dy[front]] == 'S') {
				return -1;
			}
			if (map[newX + dx[front]][newY + dy[front]] == '.' || map[newX + dx[front]][newY + dy[front]] == 'E') {
				newX += dx[front];
				newY += dy[front];
				continue;
			}
			// check left
			int left = convert(front - 1);
			if (map[newX + dx[left]][newY + dy[left]] == 'S') {
				return -1;
			}
			if (map[newX + dx[left]][newY + dy[left]] == '.' || map[newX + dx[left]][newY + dy[left]] == 'E') {
				newX += dx[left];
				newY += dy[left];
				front = left;
				continue;
			}
			// check back
			int back = convert(front + 2);
			if (!inRange(newX + dx[back], newY + dy[back], map.length, map[0].length) || map[newX + dx[back]][newY + dy[back]] == 'S') {
				return -1;
			}
			if (map[newX + dx[back]][newY + dy[back]] == '.' || map[newX + dx[back]][newY + dy[back]] == 'E') {
				newX += dx[back];
				newY += dy[back];
				front = back;
				continue;
			}
			return -1;
		}
	}
	
	public static boolean isPossible(char[][] map2, int startRow, int startCol) {
		ArrayList<Integer> x = new ArrayList<Integer>();
		ArrayList<Integer> y = new ArrayList<Integer>();
		
		x.add(startRow);
		y.add(startCol);
		
		while (x.size() > 0) {
			int newX = x.remove(0);
			int newY = y.remove(0);
			
			map2[newX][newY] = '#';
			for (int i = 0; i < 4; i++) {
				int xx = newX + dx[i];
				int yy = newY + dy[i];
								
				if (0 <= xx && xx < map2.length && 0 <= yy && yy < map2[0].length) {
					if (map2[xx][yy] == 'E') {
						return true;
					} else if (map2[xx][yy] == '.') {
						x.add(xx);
						y.add(yy);
					}
				}
			}
		}
		
		return false;
	}
	
	public static void print(char[][] map2) {
		for (int i = 0; i < map2.length; i++) {
			for (int j = 0; j < map2[0].length; j++) {
				System.out.print(map2[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static boolean inRange(int i, int j, int row, int col) {
		return 0 <= i && i < row && 0 <= j && j < col;
	}
	
	public static int convert(int n) {
		if (n == -1) {
			return 3;
		} else if (n == 4) {
			return 0;
		} else if (n == 5) {
			return 1;
		}
		return n;
	}
}
