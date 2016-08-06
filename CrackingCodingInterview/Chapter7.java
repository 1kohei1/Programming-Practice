import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Chapter7 {

	public static void main(String[] args) {
		p68();
	}

	// Find all combinations of a^3 + b^3 = c^3 + d^3
	// All values are between 0 < a, b, c, d < 1000
	public static void p68() {
		int n = 100;
		
		HashMap<Integer, ArrayList<Chapter7Pair>> map = new HashMap<Integer, ArrayList<Chapter7Pair>>();
		
		for (int a = 1; a < n; a++) {
			for (int b = a; b < n; b++) {
				int qubeSum = a * a * a + b * b * b;
				Chapter7Pair pair = new Chapter7Pair(a, b);
				
				if (map.containsKey(qubeSum)) {
					ArrayList<Chapter7Pair> arrays = map.get(qubeSum);
					arrays.add(pair);
					map.put(qubeSum, arrays);
				} else {
					ArrayList<Chapter7Pair> arrays = new ArrayList<Chapter7Pair>();
					arrays.add(pair);
					map.put(qubeSum, arrays);
				}
			}
		}
		
		Set<Integer> keys = map.keySet();
		for (Integer sum : keys) {
			ArrayList<Chapter7Pair> pairs = map.get(sum);
			
			for (int i = 0; i < pairs.size() - 1; i++) {
				for (int j = i + 1; j < pairs.size(); j++) {
					Chapter7Pair p1 = pairs.get(i);
					Chapter7Pair p2 = pairs.get(j);
					
					System.out.printf("(%d, %d, %d, %d)\n", p1.a, p1.b, p2.a, p2.b);
				}
			}
		}

	}
}

class Chapter7Pair {
	int a;
	int b;
	
	public Chapter7Pair(int a, int b) {
		this.a = a;
		this.b = b;
	}
}