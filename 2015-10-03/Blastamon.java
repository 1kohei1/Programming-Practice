import java.util.*;

public class Blastamon {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int n = scanner.nextInt();
		while(n != 0) {
			
			ArrayList<String> names = new ArrayList<String>();
			ArrayList<Integer> points = new ArrayList<Integer>();
			
			for (int i = 0; i < n; i++) {
				names.add(scanner.next());
				points.add(scanner.nextInt());
			}
			
			ArrayList<Integer> pointsCopy = (ArrayList<Integer>) points.clone();
			points.sort(null);
			
			for (int i = 0; i < n; i++) {
				int index = pointsCopy.indexOf(points.get(i));
				System.out.printf("%s\n", names.get(index));
			}
			System.out.printf("\n");
			
			n = scanner.nextInt();
		}
	}
}

/*
3
Roar
10
Squeak
1
Bark
5
2
Megamon
1000
Minimon
1
0
*/


