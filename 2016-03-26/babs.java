import java.util.Scanner;

public class babs {

	static Box2[] boxes;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int counter = 1;
		while (true) {
			int n = scanner.nextInt();
			if (n == 0) {
				break;
			}
			
			boxes = new Box2[n];
			for (int i = 0; i < n; i++) {
				boxes[i] = new Box2(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
			}
			
			int max = 1;
			for (int i = 0; i < n; i++) {
				for (int j = 1; j <= 3; j++) {
					boxes[i].side = j;
					max = Math.max(max, solve(i, boxes[i]) + 1);
				}
			}
			System.out.printf("Case %d: %d\n", counter, max);
			counter++;
		}
	}
/*
4
2 2 9
6 5 5
1 4 9
3 1 1
3
2 4 2
1 5 2
3 4 1
2
1 1 1
2 2 2
3
1 1 1
1 1 1
1 1 1
0
 */
	// Try to place indexed box on the top of bottom
	public static int solve(int index, Box2 bottom) {
		if (index == boxes.length) {
			return 0;
		}
		
		int max = 0;
		
		// Place indexed box
		// Check if we can place by side 1
		boxes[index].side = 1;
		if (bottom.canPlaceOnTheTop(boxes[index])) {
			max = Math.max(max, solve(index + 1, boxes[index]) + 1);
		}
		
		// Check if we can place by side2
		boxes[index].side = 2;
		if (bottom.canPlaceOnTheTop(boxes[index])) {
			max = Math.max(max, solve(index + 1, boxes[index]) + 1);
		}
		
		// Check if we can place by side3
		boxes[index].side = 3;
		if (bottom.canPlaceOnTheTop(boxes[index])) {
			max = Math.max(max, solve(index + 1, boxes[index]) + 1);
		}
		
//		System.out.printf("solve(%d, %s) => %d\n", index, boxes[index].toString(), max);
		return max;
	}
}

class Box2 {
	int a;
	int b;
	int c;
	
	int side;
	
	public Box2(int a, int b, int c) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.side = 1;
	}
	
	public int[] getDimension() {
		if (this.side == 1) {
			return side1();
		} else if (this.side == 2) {
			return side2();
		} else {
			return side3();
		}
	}
	
	// Side always returns smaller, bigger
	
	public int[] side1() {
		if (a < b) {
			return new int[]{a, b};			
		} else {
			return new int[]{b, a};
		}
	}
	
	public int[] side2() {
		if (a < c) {
			return new int[]{a, c};
		} else {
			return new int[]{c, a};
		}
	}
	
	public int[] side3() {
		if (b < c) {
			return new int[]{b, c};
		} else {
			return new int[]{c, b};
		}
	}
	
	public boolean canPlaceOnTheTop(Box2 b) {
		int[] thisDimension = getDimension();
		int[] bDimension = b.getDimension();
		
		return thisDimension[0] >= bDimension[0] && thisDimension[1] >= bDimension[1];
	}

	@Override
	public String toString() {
		return "Box2 [a=" + a + ", b=" + b + ", c=" + c + ", side=" + side + "]";
	}
	
	
}
