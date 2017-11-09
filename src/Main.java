import java.awt.geom.Point2D;
import java.util.*;

// Foobar Level 4: bringing_a_gun_to_a_guard_fight

public class Main {

	public static double EPSILON = 1e-9;
	public static int[] toBadguy;
	public static int[] xvec;
	public static int[] yvec;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int width = in.nextInt();
		int height = in.nextInt();
		int x1 = in.nextInt();
		int y1 = in.nextInt();
		int x2 = in.nextInt();
		int y2 = in.nextInt();
		int d = in.nextInt();

		double start = System.currentTimeMillis();
		System.out.println(answer(new int[] { width, height }, new int[] { x1, y1 }, new int[] { x2, y2 }, d));
		System.out.println(System.currentTimeMillis() - start + "ms");
	}

	public static int answer(int[] dimensions, int[] captain_position, int[] badguy_position, int distance) {
		int answer = 0;

    	int width = dimensions[0];
    	int height = dimensions[1];
    	
    	// No reflection
    	if (dist(captain_position, badguy_position) <= distance) {
    		answer++;
    	}
    	
    	HashMap<Point2D, Integer> lasers = new HashMap<Point2D, Integer>();
    	HashMap<Point2D, Integer> mirrorCaptains = new HashMap<Point2D, Integer>();
    	toBadguy = vector(captain_position, badguy_position);
    	xvec = new int[]{1, 0};
    	yvec = new int[]{0, 1};
    	
    	int numVerticalBlock = (distance + captain_position[1]) / height + 1;
    	int numHorizontalBlock = (distance + captain_position[0]) / width + 1;
    	
    	for (int a = 0; a <= numVerticalBlock; a++) {
    		for (int b = 0; b <= numHorizontalBlock; b++) {
    			// Ignore the original block
    			if (a == 0 && b == 0) {
    				continue;
    			}
    			answer += checkBlock(dimensions, captain_position, badguy_position, distance, lasers, mirrorCaptains, a, b);
    			answer += checkBlock(dimensions, captain_position, badguy_position, distance, lasers, mirrorCaptains, -a, b);
    			answer += checkBlock(dimensions, captain_position, badguy_position, distance, lasers, mirrorCaptains, a, -b);
    			answer += checkBlock(dimensions, captain_position, badguy_position, distance, lasers, mirrorCaptains, -a, -b);
    		}
    	}
    	return answer;
	}
	
	public static int checkBlock(int[] dimensions, int[] captain, int[] badguy, int r, HashMap<Point2D, Integer> lasers, HashMap<Point2D, Integer> mirrorCaptains, int a, int b) {
		int[] mirrorBadguy = getMirrorPoint(dimensions, badguy, a, b);
		int[] mirrorCaptain = getMirrorPoint(dimensions, captain, a, b);
		
		int[] toMirrorBadguy = vector(captain, mirrorBadguy);
		int[] toMirrorCaptain = vector(captain, mirrorCaptain);
		
		Point2D mirrorBadguyKey = convertToHashKey(toMirrorBadguy);
		Point2D mirrorCaptainKey = convertToHashKey(toMirrorCaptain);
		
		if (isWithinCircle(captain, r, mirrorCaptain) && !mirrorCaptains.containsKey(mirrorCaptainKey)) {
			mirrorCaptains.put(mirrorCaptainKey, 1);
		}
		
		if (
			isWithinCircle(captain, r, mirrorBadguy) &&
			!lasers.containsKey(mirrorBadguyKey) &&
			!mirrorCaptains.containsKey(mirrorBadguyKey) &&
			!isParallel(toBadguy, toMirrorBadguy, false) &&
			!isParallel(xvec, toMirrorBadguy, false) &&
			!isParallel(yvec, toMirrorBadguy, false) && 
			!doesHitCorner(dimensions, captain, toMirrorBadguy)
		) {
			lasers.put(mirrorBadguyKey, 1);
			return 1;
		} else {
			return 0;
		}
	}
	
    public static boolean isWithinCircle(int[] center, int r, int[] p) {
    	return dist(center, p) <= r;
    }
	
    public static Point2D convertToHashKey(int[] v) {
    	// If x direction is 0, it's omitted by parallel with x axis, so just return without making change
    	if (v[0] == 0) {
    		return new Point2D.Double(v[0], v[1]);
    	}
    	double x = (double) Math.abs(v[0]);
    	return new Point2D.Double(v[0] / x, v[1] / x);
    }

    public static boolean isParallel(int[] v1, int[] v2, boolean strict) {
    	int product = v1[0] * v2[1] - v1[1] * v2[0];
    	if (strict) {
    		return product == 0 && v1[0] * v2[0] >= 0 && v1[1] * v2[1] >= 0;
    	} else {
    		return product == 0;
    	}
    }
    
    public static boolean doesHitCorner(int[] dimensions, int[] captain, int[] v) {
    	int[] leftTop = vector(captain, new int[]{0, dimensions[1]});
    	int[] origin = vector(captain, new int[]{0, 0});
    	int[] rightTop = vector(captain, dimensions);
    	int[] rightBottom = vector(captain, new int[]{dimensions[0], 0});
    	
    	return isParallel(v, leftTop, true) ||
    			isParallel(v, origin, true) || 
    			isParallel(v, rightTop, true) ||
    			isParallel(v, rightBottom, true);
    }
	
    public static int[] getMirrorPoint(int[] dimensions, int[] p, int numVerticalBlock, int numHorizontalBlock) {
    	int mirrorX = (dimensions[0] * 2) * (numHorizontalBlock / 2) + p[0];
    	if (Math.abs(numHorizontalBlock) % 2 == 1) {
    		if (numHorizontalBlock < 0) {
    			mirrorX -= p[0] * 2;
    		} else {
    			mirrorX += (dimensions[0] - p[0]) * 2;
    		}
    	}
    	int mirrorY = (dimensions[1] * 2) * (numVerticalBlock / 2) + p[1];
    	if (Math.abs(numVerticalBlock) % 2 == 1) {
    		if (numVerticalBlock < 0) {
    			mirrorY -= p[1] * 2;
    		} else {
    			mirrorY += (dimensions[1] - p[1]) * 2;
    		}
    	}
    	return new int[]{mirrorX, mirrorY};
    }
	
    public static double dist(int[] p1, int[] p2) {
    	return Math.sqrt(Math.pow(p1[0] - p2[0], 2) + Math.pow(p1[1] - p2[1], 2));
    }

    public static int[] vector(int[] origin, int[] target) {
    	return new int[]{target[0] - origin[0], target[1] - origin[1]};
    }
}