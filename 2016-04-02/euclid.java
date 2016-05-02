import java.util.Scanner;

public class euclid {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		while(true) {			
			double ax = scanner.nextDouble();
			double ay = scanner.nextDouble();
			double bx = scanner.nextDouble();
			double by = scanner.nextDouble();
			double cx = scanner.nextDouble();
			double cy = scanner.nextDouble();
			double dx = scanner.nextDouble();
			double dy = scanner.nextDouble();
			double ex = scanner.nextDouble();
			double ey = scanner.nextDouble();
			double fx = scanner.nextDouble();
			double fy = scanner.nextDouble();
			
			if (ax == 0.0 && ay == 0.0 && bx == 0.0 && by == 0.0 && cx == 0.0 && cy == 0.0 && dx == 0.0 && dy == 0.0 && ex == 0.0 && ey == 0.0 && fx == 0.0 && fy == 0.0) {
				break;
			}
			
			Double[] a = new Double[]{ax, ay};
			Double[] b = new Double[]{bx, by};
			Double[] c = new Double[]{cx, cy};
			Double[] d = new Double[]{dx, dy};
			Double[] e = new Double[]{ex, ey};
			Double[] f = new Double[]{fx, fy};
			
			Double[] de = new Double[2];
			de[0] = e[0] - d[0];
			de[1] = e[1] - d[1];
			
			Double[] df = new Double[2];
			df[0] = f[0] - d[0];
			df[1] = f[1] - d[1];
			
//			print(a);
//			print(b);
//			print(c);
//			print(d);
//			print(e);
//			print(f);
			
			double dotdedf = dotProduct(de, df);
//			System.out.printf("dotdedf: %.3f\n", dotdedf);
			double cosdedf = calculateCos(de, df, dotdedf);
//			System.out.printf("cosdedf: %.3f\n", cosdedf);
			double sindedf = calculateSin(cosdedf);
//			System.out.printf("sindedf: %.3f\n", sindedf);
			double area = calculateArea(de, df, sindedf);
//			System.out.printf("area: %.3f\n", area);
			
			Double[] ac = new Double[2];
			ac[0] = c[0] - a[0];
			ac[1] = c[1] - a[1];
			
			Double[] ab = new Double[2];
			ab[0] = b[0] - a[0];
			ab[1] = b[1] - a[1];
			
			double dotacab = dotProduct(ac, ab);
//			System.out.printf("dotacab: %.3f\n", dotacab);
			double cosacab = calculateCos(ab, ac, dotacab);
//			System.out.printf("cosacab: %.3f\n", cosacab);
			double sinacab = calculateSin(cosacab);
//			System.out.printf("sinacab: %.3f\n", sinacab);
			
			double magnitudeah = area / (sinacab * magnitude(ab));
//			System.out.printf("magnitudeah: %.3f\n", magnitudeah);
			
			Double[] h = new Double[2];
			Double[] g = new Double[2];
			if (Double.compare(a[0], c[0]) != 0) {
//				System.out.println("come 1");
				double slope = (c[1] - a[1]) / (c[0] - a[0]);
//				System.out.printf("slope: %.3f\n", slope);
				double hx = Math.sqrt(Math.pow(magnitudeah , 2) / (1 + Math.pow(slope, 2)));
//				System.out.printf("hx: %.3f\n", hx);
				
				// a[0] < c[0]
				if (Double.compare(a[0], c[0]) < 0) {
					h[0] = a[0] + hx;
					h[1] = a[1] + hx * slope;
					
					g[0] = b[0] + hx;
					g[1] = b[1] + hx * slope;					
				} 
				// c[0] < a[0]
				else if (Double.compare(a[0], c[0]) > 0) {
					h[0] = a[0] - hx;
					h[1] = a[1] - hx * slope;
					
					g[0] = b[0] - hx;
					g[1] = b[1] - hx * slope;
				}
			} else {
				h[0] = a[0];
				g[0] = b[0];
				
				// a[1] < c[1]
				if (Double.compare(a[1], c[1]) < 0) {
					h[1] = a[1] + magnitudeah;
					g[1] = b[1] + magnitudeah;
				}
				// c[1] < a[1]
				else if (Double.compare(a[1], c[1]) > 0) {
					h[1] = a[1] - magnitudeah;
					g[1] = b[1] - magnitudeah;
				}
			}
			
			System.out.printf("%.3f %.3f %.3f %.3f\n", g[0], g[1], h[0], h[1]);
			
		}
	}
/*
0 0 5 0 0 5 3 2 7 2 0 4
1.3 2.6 12.1 4.5 8.1 13.7 2.2 0.1 9.8 6.6 1.9 6.7
0 0 1.414 1.414 5 5 10 10 11 10 11 12
0 0 1 0 5 5 10 10 11 10 11 12
0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0 0.0
 */
	public static double dotProduct(Double[] vector1, Double[] vector2) {
		return vector1[0] * vector2[0] + vector1[1] * vector2[1];
	}
	
	public static double calculateCos(Double[] v1, Double[] v2, double dotProduct) {
		double mg1 = magnitude(v1);
		double mg2 = magnitude(v2);
		
		return dotProduct / (mg1 * mg2);
	}
	
	public static double calculateSin(double cos) {
		return Math.sqrt(1 - cos * cos);
	}
	
	public static double calculateArea(Double[] v1, Double[] v2, double sin) {
		double mg1 = magnitude(v1);
		double mg2 = magnitude(v2);
		
		return mg1 * mg2 * sin / 2;
	}
	
	public static double magnitude(Double[] v) {
		return Math.sqrt(Math.pow(v[0], 2) + Math.pow(v[1], 2));
	}
	
	public static double dist(Double[] a, Double[] b) {
		return 0;
	}
	
	public static void print(Double[] a) {
		System.out.printf("%.3f %.3f\n", a[0], a[1]);
	}
}
