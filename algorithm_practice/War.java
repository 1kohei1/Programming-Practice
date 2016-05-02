import java.util.LinkedList;
import java.util.Scanner;

public class War {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int numCase = scanner.nextInt();
		while(numCase-- > 0) {
			int numStudents = scanner.nextInt();
			int numPairs = scanner.nextInt();
			
			int[][] students = new int[numStudents][numStudents];
			for (int i = 0; i < numStudents; i++) {
				for (int j = 0; j < numStudents; j++) {
					students[i][j] = 1;
				}
			}
			
			for (int i = 0; i < numPairs; i++) {
				students[scanner.nextInt()][scanner.nextInt()] = 0;
			}
			
			int numTeams = 0;
			int[] used = new int[numStudents];
			
			for (int i = 0; i < numStudents; i++) {
				if (used[i] == 0) {
					int numPeople = 1;
					used[i] = 1;
					LinkedList<Integer> q = new LinkedList<Integer>();
					for (int j = 0; j < numStudents; j++) {
						if (students[i][j] == 1) {
							used[j] = 1;
							q.offer(j);
							numPeople++;
						}
					}
					
					while(!q.isEmpty()) {
						int next = q.poll();
						for (int j = 0; j < numStudents; j++) {
							if (used[j] == 0 && students[next][j] == 1) {
								q.offer(j);
								used[j] =  1;
								numPeople++;
							}
						}
					}
					
					if (numPeople >= 2) {
						numTeams++;
					}
				}
			}
			System.out.printf("%s\n", numTeams >= 2 ? "YES" : "NO");
		}
	}
}
/*
2
4 5
0 1
0 2
1 2
1 3
0 3
4 4
0 2
0 3
1 2
1 3
*/