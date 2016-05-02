import java.util.Scanner;
import java.util.ArrayList;

public class prob5 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int numTests = scanner.nextInt();
		for (int counter = 1; counter <= numTests; counter++) {
			ArrayList<Team> teams = new ArrayList<Team>();

			// Get teams
			int t = scanner.nextInt();
			scanner.nextLine();
			for (int i = 0; i < t; i++) {
				String s = scanner.nextLine();
				Team tt = new Team(trailEndingSpace(s));
				int index = teams.indexOf(tt);
				
				if (index == -1) {
					tt.nameProvided = true;
					teams.add(tt);
				} else {
					tt = teams.get(index);
					tt.numTeams++;
					tt.nameProvided = true;
					teams.set(index, tt);
				}
			}
			
			// Get registered teams
			int m = scanner.nextInt();
			scanner.nextLine();
			for (int i = 0; i < m; i++) {
				String s = scanner.nextLine();
				Team tt = new Team(trailEndingSpace(s));
				int index = teams.indexOf(tt);
			
				if (index == -1) {
					tt.payRegistration = true;
					teams.add(tt);
				} else {
					tt = teams.get(index);
					tt.payRegistration = true;
					teams.set(index, tt);
				}
			}
			
			// Get submitted teams
			int p = scanner.nextInt();
			scanner.nextLine();
			for (int i = 0; i < p; i++) {
				String s = scanner.nextLine();
				Team tt = new Team(trailEndingSpace(s));
				int index = teams.indexOf(tt);
				
				if (index == -1) {
					tt.submitAProblem = true;
					teams.add(tt);
				} else {
					tt = teams.get(index);
					tt.submitAProblem = true;
					teams.set(index, tt);
				}
			}
			
			// Sort
			int numTeams = 0;
			ArrayList<Team> three = new ArrayList<Team>();
			ArrayList<Team> two = new ArrayList<Team>();
			ArrayList<Team> one = new ArrayList<Team>();
			
			for (int i = 0; i < teams.size(); i++) {
				Team tt = teams.get(i);
				int count = count(tt);
				if (count == 3) {
					numTeams += tt.numTeams;
					three.add(tt);
				} else if (count == 2) {
					two.add(tt);
				} else if (count == 1) {
					one.add(tt);
				} else {
					System.out.println("SOMETHING WRONG");
				}
			}
			
			three.sort(null);
			two.sort(null);
			one.sort(null);
			
			System.out.printf("Contest number %d\n", counter);
			if (three.size() > 0) {
				System.out.printf("---%d Registered Teams:\n", numTeams);
				for (int i = 0; i < three.size(); i++) {
					Team tt = three.get(i);
					System.out.printf("%s (%d)\n", tt.name, tt.numTeams);
				}
			}
			if (two.size() > 0) {
				System.out.println("---Send reminders to:");
				for (int i = 0; i < two.size(); i++) {
					System.out.println(two.get(i));
				}
			}
			if (one.size() > 0) {
				System.out.println("---Cancel registration for:");
				for (int i = 0; i < one.size(); i++) {
					System.out.println(one.get(i));
				}
			}
			System.out.println();
		}
	}
	
	public static int count(Team t) {
		int count = 0;
		if (t.nameProvided) {
			count++;
		}
		if (t.payRegistration) {
			count++;
		}
		if (t.submitAProblem) {
			count++;
		}
		return count;
	}
	
	public static String trailEndingSpace(String s) {
		int index = s.length() - 1;
		while (index >= 0 && s.charAt(index) == ' ') {
			index--;
		}
		return s.substring(0, index + 1);
	}
}

/*
2
7
A
B
C
D
A
B
E
3
E
B
D
4
B
C
E
F

3
A
B
A
1
A
1
A

 */

class Team implements Comparable<Team> {

	String name;
	int numTeams;
	
	boolean nameProvided;
	boolean payRegistration;
	boolean submitAProblem;
	
	public Team(String s) {
		this.name = s;
		this.numTeams = 1;
		
		this.nameProvided = false;
		this.payRegistration = false;
		this.submitAProblem = false;
	}
	
	public int compareTo(Team o) {
		return this.name.compareTo(o.name);
	}

	public String toString() {
		return this.name;
	}

	public boolean equals(Object obj) {
		return this.name.equals(((Team) obj).name);
	}
}