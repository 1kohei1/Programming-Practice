import java.util.*;

public class Contractor {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int n = scanner.nextInt();
		for (int counter = 0; counter < n; counter++) {
			int numJobs = scanner.nextInt();
			int numDays = scanner.nextInt();
			
			int[][] jobs = new int[numJobs][2];
			for (int i = 0; i < numJobs; i++) {
				jobs[i][0] = scanner.nextInt(); // numDay
				jobs[i][1] = scanner.nextInt(); // money
			}
			int max = getMax();
			System.out.printf("%d\n", max);
		}
	}
	
	public static int getMax() {
		
		return 0;
	}
	
}
