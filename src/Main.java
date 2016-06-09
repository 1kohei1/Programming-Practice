import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// uva 10660

public class Main {
	
	static int distance;
	static Area[] area;
	static int[] solution;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int nt = to(in.readLine());
		while (nt-- > 0) {
			int n = to(in.readLine());
			Area[] area = new Area[n];
			
			for (int i = 0; i < n; i++) {
				String[] s = in.readLine().split(" ");
				int row = to(s[0]);
				int col = to(s[1]);
				int numPeople = to(s[2]);
				
				area[i] = new Area(row, col, numPeople);
			}
			
			int[] solution = new int[5];
			int distance = Integer.MAX_VALUE;
			
			for (int a = 0; a < 25; a++) {
				for (int b = a + 1; b < 25; b++) {
					for (int c = b + 1; c < 25; c++) {
						for (int d = c + 1; d < 25; d++) {
							for (int e = d + 1; e < 25; e++) {
								int dist = 0;
								
								for (int i = 0; i < n; i++) {
									int officeD = Integer.MAX_VALUE;
									officeD = Math.min(officeD, Math.abs(a / 5 - area[i].row) + Math.abs(a % 5 - area[i].col));
									officeD = Math.min(officeD, Math.abs(b / 5 - area[i].row) + Math.abs(b % 5 - area[i].col));
									officeD = Math.min(officeD, Math.abs(c / 5 - area[i].row) + Math.abs(c % 5 - area[i].col));
									officeD = Math.min(officeD, Math.abs(d / 5 - area[i].row) + Math.abs(d % 5 - area[i].col));
									officeD = Math.min(officeD, Math.abs(e / 5 - area[i].row) + Math.abs(e % 5 - area[i].col));
									dist += officeD * area[i].numPeople;
								}
								
								if (dist < distance) {
									distance = dist;
									solution = new int[]{a, b, c, d, e};
								}
							}
						}
					}
				}
			}
			
			for (int i = 0; i < 5; i++) {
				System.out.printf("%d%s", solution[i], i == 4 ? "" : " ");
			}
			System.out.println();
		}
	}
	
	public static int to(String s) {
		return Integer.parseInt(s);
	}
}

class Area {
	int row;
	int col;
	int numPeople;
	
	public Area(int r, int c, int n) {
		row = r;
		col = c;
		numPeople = n;
	}
}