import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;

public class Family {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int counter = 1;
		while(true) {
			int numLine = scanner.nextInt();
			if (numLine == 0) break;
			scanner.nextLine();
			
			ArrayList<String> people = new ArrayList<String>();
			String[][] inputs = new String[numLine][3];
			
			// Get inputs
			for (int i = 0; i < numLine; i++) {
				String[] s = scanner.nextLine().split(" ");
				inputs[i] = s;
				for (int j = 0; j < 3; j++) {
					if (!people.contains(s[j])) {
						people.add(s[j]);
					}
				}
			}
			
			// Create 2d array to store relationship
			int[][] relations = new int[people.size()][people.size()];
			for (int i = 0; i < numLine; i++) {
				int i1 = people.indexOf(inputs[i][0]);
				int i2 = people.indexOf(inputs[i][1]);
				int i3 = people.indexOf(inputs[i][2]);
				
				relations[i1][i2] = 1;
				relations[i2][i1] = 1;
				relations[i1][i3] = 1;
				relations[i3][i1] = 1;
				relations[i2][i3] = 1;
				relations[i3][i1] = 1;
			}
			
			// Determine if they are relational
			int start = people.indexOf(scanner.next());
			int end = people.indexOf(scanner.next());
			System.out.printf("Family #%d: %s.\n", counter, isRelation(relations, start, end) ? "Related" : "Not related");
			counter++;
		}
	}

	public static boolean isRelation(int[][] relations, int start, int end) {
		
		if (start == -1 || end == -1) {
			return false;
		}
		
		LinkedList<Integer> q = new LinkedList<Integer> ();
		int[] visited = new int[relations.length];
		
		// Set the first breadth
		for (int i = 0; i < relations.length; i++) {
			if (relations[start][i] == 1 && i == end) {
				return true;
			} else if (relations[start][i] == 1) {
				visited[i] = 1;
				q.offer(i);
			}
		}
		
		while(!q.isEmpty()) {
			int next = q.poll();
			for (int i = 0; i < relations.length; i++) {
				if (relations[next][i] == 1 && i == end) {
					return true;
				} else if (visited[i] == 0 && relations[next][i] == 1) {
					q.offer(i);
					visited[i] = 1;
				}
			}
		}
		
		return false;
	}
}

/*
2
Barbara Bill Ted
Nancy Ted John
John Barbara
3
Lois Frank Jack
Florence Bill Fred
Annie Fred James
James Jack
1
John Susan Billy
John Susan
2
Karen Roger Christopher
Karen Roger Michael
Christopher Michael
0 
*/