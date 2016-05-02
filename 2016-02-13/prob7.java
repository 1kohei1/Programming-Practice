import java.util.Scanner;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class prob7 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int numTests = scanner.nextInt();
		
		for (int counter = 1; counter <= numTests; counter++) {
			int n = scanner.nextInt();
			
			ArrayList<Point3D> people = new ArrayList<Point3D>();
			for (int i = 0; i < n; i++) {
				int x = scanner.next().length();
				int y = scanner.next().length();
				int height = scanner.nextInt();
				
				people.add(new Point3D(x, y, height));
			}
			
			Collections.sort(people);
			
			ArrayList<Point3D> people2 = new ArrayList<Point3D>();
			for (int i = 0; i < people.size();) {
				int index = i + 1;
				Point3D a = people.get(i);
				
				if (index != people.size()) {
					Point3D next = people.get(index);
					while (a.compareTo(next) == 0) {
						a.z += next.z;
						index++;
						if (index == people.size()) {
							break;
						}
						next = people.get(index);
					}					
				}
				people2.add(a);
				i = index;
			}
			
			System.out.printf("%d %s", counter, people.size() == people2.size() ? "yes" : "no");
			for (int i = 0; i < people2.size(); i++) {
				Point3D a = people2.get(i);
				System.out.printf(" <%d,%d,%d>", a.x, a.y, a.z);
			}
			System.out.println();
		}
	}
/*
4
2
John McCain 175
Barack Obama 185
3
John McCain 175
Barack Obama 185
Mitt Romney 188
5
A A 200
B B 200
C C 200
D D 200
E E 200
2
A C 200
AA B 100
6
AA DDDD 320
A C 100
A VVV 200
AA VVV 150
A BB 100
A A 120

 */
}

class Point3D implements Comparable<Point3D> {
	int x;
	int y;
	int z;
	
	public Point3D(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public int compareTo(Point3D a) {
		if (this.x != a.x) {
			return Integer.compare(this.x, a.x);
		}
		if (this.y != a.y) {
			return Integer.compare(this.y, a.y);
		} else {
			return 0;
		}
	}
}