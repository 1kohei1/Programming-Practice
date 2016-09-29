import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Point;

public class Playground {
	
	public static void main(String[] args) {
		ArrayList<Point> p1 = new ArrayList<Point>();
		ArrayList<Point> p2 = new ArrayList<Point>();
		p1.add(new Point(1,2));
		p1.add(new Point(2,3));
		p2.add(new Point(2,3));
		p2.add(new Point(1,2));
		
		System.out.println(p1.equals(p2));
		
		ArrayList<Point> p3 = (ArrayList<Point>) p1.clone();
		System.out.println(p1.equals(p3));
	}
	
	public static void print(ArrayList<Point> p) {
		for (int i = 0; i < p.size(); i++) {
			Point pp = p.get(i);
			System.out.printf("(%d, %d) ", pp.x, pp.y); 
		}
		System.out.println();
	}
}
