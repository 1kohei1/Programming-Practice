import java.util.Scanner;

// uva 459

public class Main {
	
	static int[][] map;
	static int[] visited;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int numTests = in.nextInt();
		String s;
		while (numTests-- > 0) {
			int numNodes = in.next().charAt(0) - 'A' + 1;
			in.nextLine();
			visited = new int[numNodes];
			map = new int[numNodes][numNodes];
			
			s = in.nextLine();
			while (s.length() != 0) {
				int a = s.charAt(0) - 'A';
				int b = s.charAt(1) - 'A';
				map[a][b] = 1;
				map[b][a] = 1;
				if (in.hasNextLine()) {
					s = in.nextLine();
				} else {
					break;
				}
			}
						
			int answer = 0;
			for (int i = 0; i < numNodes; i++) {
				if (visited[i] == 0) {
					solve(i);
					answer++;
				}
			}
			System.out.println(answer);
			if (numTests > 0) {
				System.out.println();
			}
		}
	}

	public static void solve(int start) {
		if (visited[start] == 1) {
			return;
		}
		visited[start] = 1;
		
		for (int i = 0; i < map.length; i++) {
			if (map[start][i] == 1 && visited[i] == 0) {
				solve(i);
			}
		}
	}
}