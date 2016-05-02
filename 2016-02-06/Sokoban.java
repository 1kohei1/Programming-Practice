import java.util.ArrayList;
import java.util.Scanner;

public class Sokoban {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		ArrayList<Character> directions = new ArrayList<Character>();
		directions.add('L');
		directions.add('U');
		directions.add('R');
		directions.add('D');
		int[] dx = new int[]{0, -1, 0, 1};
		int[] dy = new int[]{-1, 0, 1, 0};
		
		int counter = 1;
		while (true) {
			int row = scanner.nextInt();
			int col = scanner.nextInt();
			scanner.nextLine();
			
			if (row == 0 && col == 0) {
				break;
			}
			
			// Read data in
			
			char[][] map = new char[row][col];
			int workerX = 0;
			int workerY = 0;
						
			for (int i = 0; i < row; i++) {
				String s = scanner.nextLine();
				for (int j = 0; j < col; j++) {
					if (s.charAt(j) == 'w' || s.charAt(j) == 'W') {
						workerX = i;
						workerY = j;
					}
					map[i][j] = s.charAt(j);
				}
			}
			
			// Move the character
			String s = scanner.nextLine();
			boolean isOver = false;
			
			for (int i = 0; i < s.length(); i++) {
				if (!isOver(map, row, col)) {
					int dir = directions.indexOf(s.charAt(i));
					int nextX = workerX + dx[dir];
					int nextY = workerY + dy[dir];
					int nextnextX = nextX + dx[dir];
					int nextnextY = nextY + dy[dir];
					
					if (map[nextX][nextY] == '#') {
						continue;
					}
					if ((map[nextX][nextY] == 'b' || map[nextX][nextY] == 'B') && 
						(map[nextnextX][nextnextY] == 'b' || map[nextnextX][nextnextY] == 'B' || map[nextnextX][nextnextY] == '#')) {
						continue;
					}
					
					// Character moves the stone if
					// next is stone, AND (nextnext is not stone nor nextnext is not wall)
					if ((map[nextX][nextY] == 'b' || map[nextX][nextY] == 'B') &&
						!(map[nextnextX][nextnextY] == 'b' || map[nextnextX][nextnextY] == 'B' || map[nextnextX][nextnextY] == '#')) {
						// Move the stone to nextnext
						if (map[nextnextX][nextnextY] == '+') {
							map[nextnextX][nextnextY] = 'B';
						} else {
							map[nextnextX][nextnextY] = 'b';
						}
						// Fill the place the stone used to be
						if (map[nextX][nextY] == 'B') {
							map[nextX][nextY] = '+';
						} else {
							map[nextX][nextY] = '.';
						}
						// Move the character to next
						if (map[nextX][nextY] == '+') {
							map[nextX][nextY] = 'W';
						} else {
							map[nextX][nextY] = 'w';
						}
						// Fill the place the character used to be
						if (map[workerX][workerY] == 'W') {
							map[workerX][workerY] = '+';
						} else {
							map[workerX][workerY] = '.';
						}
						// Update workerX and workerY
						workerX = nextX;
						workerY = nextY;
					}
					
					// Character moves if
					// next is empty space OR next is empty target location
					else if (map[nextX][nextY] == '+' || map[nextX][nextY] == '.') {
						// Move character to the next
						if (map[nextX][nextY] == '+') {
							map[nextX][nextY] = 'W';
						} else {
							map[nextX][nextY] = 'w';
						}
						// Fill the place character used to be
						if (map[workerX][workerY] == 'W') {
							map[workerX][workerY] = '+';
						} else {
							map[workerX][workerY] = '.';
						}
						// Update workerX and workerY
						workerX = nextX;
						workerY = nextY;
					}
				}
				else {
					isOver = true;
					break;
				}
			}
			if (!isOver) {
				isOver = isOver(map, row, col);
			}
			
			System.out.printf("Game %d: %scomplete\n", counter, isOver ? "" : "in");
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					System.out.printf("%c", map[i][j]);
				}
				System.out.println();
			}
			counter++;
		}
	}
	
	public static boolean isInMap(int x, int y, int row, int col) {
		return 0 <= x && x < row && 0 <= y && y < col;
	}
	
	public static boolean isOver(char[][] map, int row, int col) {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (map[i][j] == 'b') {
					return false;
				}
			}
		}
		return true;
	}
}
/*
8 9
#########	
#...#...#
#..bb.b.#
#...#w#.#
#...#b#.#
#...++++#
#...#..##
#########
ULRURDDDUULLDDD
6 7
#######
#..####
#.+.+.#
#.bb#w#
##....#
#######
DLLUDLULUURDRDDLUDRR
*/
