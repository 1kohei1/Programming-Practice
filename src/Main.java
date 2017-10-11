import java.util.*;

// CODEFES_B 2017-C
// http://code-festival-2017-qualb.contest.atcoder.jp/tasks/code_festival_2017_qualb_c	

public class Main {
	
	static int N;

	static boolean isBipartite = true;
	static int[] color;
	static ArrayList<ArrayList<Integer>> map;

	public static void main (String[] args) throws InterruptedException {
		Scanner in = new Scanner(System.in);
		
		N = in.nextInt();
		int M = in.nextInt();
		
		map = new ArrayList<ArrayList<Integer>>();
		color = new int[N];
		
		for (int i = 0; i < N; i++) {
			map.add(new ArrayList<Integer>());
		}
		
		for (int i = 0; i < M; i++) {
			int a = in.nextInt() - 1;
			int b = in.nextInt() - 1;
			map.get(a).add(b);
			map.get(b).add(a);
		}
		
		isBipartite(0, 1);
		if (isBipartite) {
			long numBlack = 0;
			long numWhite = 0;
			for (int i = 0; i < N; i++) {
				if (color[i] == 1) {
					numBlack++;
				} else if (color[i] == 2){
					numWhite++;
				}
			}
			System.out.println(numBlack * numWhite - M);
		} else {
			System.out.println(((long) N) * ((long) (N - 1)) / 2 - M);
		}
	}
	
	public static void isBipartite(int index, int fillColor) {
		if (!isBipartite) {
			return;
		}
		if (color[index] == 0) {
			color[index] = fillColor;
		}
		
		ArrayList<Integer> queue = new ArrayList<Integer>();
		
		for (int i = 0; i < map.get(index).size(); i++) {
			int newIndex = map.get(index).get(i);
			if (color[index] == color[newIndex]) {
				isBipartite = false;
			} else if (color[newIndex] == 0) {
				queue.add(newIndex);
			}
		}
		
		while (queue.size() > 0) {
			int newIndex = queue.remove(0);
			isBipartite(newIndex, fillColor % 2 + 1);
		}
	}
}
