import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.awt.Point;

public class lightson {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		HashMap<Point, ArrayList<Point>> lights = new HashMap<Point, ArrayList<Point>>();
		
		for (int i = 0; i < m; i++) {
			int x = scanner.nextInt() - 1;
			int y = scanner.nextInt() - 1;
			int xx = scanner.nextInt() - 1;
			int yy = scanner.nextInt() - 1;
			
			Point p = new Point(x, y);
			Point pp = new Point(xx, yy);
			
			if (lights.containsKey(p)) {
				lights.get(p).add(pp);
			} else {
				ArrayList<Point> ppp = new ArrayList<Point>();
				ppp.add(pp);
				lights.put(p, ppp);
			}
		}
		
		int[][] visited = new int[n][n];
		int[][] isOn = new int[n][n];
		
		visited[0][0] = 1;
		isOn[0][0] = 1;
		
		int[] dx = new int[]{-1, 1, 0, 0};
		int[] dy = new int[]{0, 0, -1, 1};
		
		ArrayList<Point> queue = new ArrayList<Point>();
		queue.add(new Point(0, 0));
		
		while (queue.size() > 0) {
			Point p = queue.remove(0);
			visited[p.x][p.y] = 1;
			
			for (int i = 0; i < 4; i++) {
				int newX = p.x + dx[i];
				int newY = p.y + dy[i];
				
				if (0 <= newX && newX < n && 0 <= newY && newY < n && isOn[newX][newY] == 1 && visited[newX][newY] == 0 && queue.indexOf(new Point(newX, newY)) == -1) {
					queue.add(new Point(newX, newY));
				}
			}
			
			if (lights.containsKey(p)) {
				ArrayList<Point> switches = lights.get(p);
				for (int i = 0; i < switches.size(); i++) {
					Point pp = switches.get(i);
					if (isOn[pp.x][pp.y] == 0) {
						isOn[pp.x][pp.y] = 1;
						for (int j = 0; j < 4; j++) {
							int newX = pp.x + dx[j];
							int newY = pp.y + dy[j];
							
							if (0 <= newX && newX < n && 0 <= newY && newY < n && visited[newX][newY] == 1 && visited[pp.x][pp.y] == 0 && queue.indexOf(pp) == -1) {
								queue.add(pp);
								break;
							}
						}
					}
				}
			}
		}
		int numRoom = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				numRoom += isOn[i][j];
			}
		}

//		System.out.println("visited:");
//		print(visited);
//		System.out.println("isOn:");
//		print(isOn);
		
		System.out.println(numRoom);
	}
/*
3 6
1 1 1 2
2 1 2 2
1 1 1 3
2 3 3 1
1 3 1 2
1 3 2 1

3 5
3 3 3 1
2 2 2 3
2 1 2 2
1 1 2 1
1 1 3 3

3 4
1 1 1 3
2 1 1 2
1 1 2 1
1 2 3 2

3 7
1 1 3 3
1 1 3 2
1 1 3 1
1 1 2 3
1 1 2 1
1 1 1 3
1 1 1 2
 */
	public static void print(int[][] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				System.out.printf("%d ", array[i][j]);
			}
			System.out.println();
		}
	}
}
