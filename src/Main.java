import java.util.*;

// CODEFES_B 2017-C
// http://code-festival-2017-qualb.contest.atcoder.jp/tasks/code_festival_2017_qualb_c	

public class Main {

	public static void main (String[] args) throws InterruptedException {
		Scanner in = new Scanner(System.in);
		
		int N = in.nextInt();
		int M = in.nextInt();
		
		ArrayList<ArrayList<Integer>> map = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < N; i++) {
			map.add(new ArrayList<Integer>());
		}
		
		ArrayList<Path> q = new ArrayList<Path>();
		
		for (int i = 0; i < M; i++) {
			int a = in.nextInt() - 1;
			int b = in.nextInt() - 1;
			map.get(a).add(b);
			map.get(b).add(a);
			q.add(new Path(a, b));
		}
		
		long answer = 0;
		
		while (q.size() > 0) {
			Path p = q.remove(0);
			int start = p.start;
			int end = p.end;
			
			for (int i = 0; i < map.get(start).size(); i++) {
				for (int j = 0; j < map.get(end).size(); j++) {
					int point1 = map.get(start).get(i);
					int point2 = map.get(end).get(j);
					
					if (point1 != point2 && point1 != start && point1 != end && point2 != start && point2 != end && map.get(point1).indexOf(point2) == -1) {
						map.get(point1).add(point2);
						map.get(point2).add(point1);
						Path newP = new Path(point1, point2);
						if (!q.contains(newP)) {
							q.add(newP);
						}
						answer++;
					}
				}
			}
		}
		System.out.println(answer);
	}
}

class Path {
	int start;
	int end;
	
	public Path(int start, int end) {
		this.start = Math.min(start, end);
		this.end = Math.max(start, end);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Path) {
			Path p = (Path) obj;
			return p.start == this.start && p.end == this.end;
		} else {
			return false;
		}
	}
	
	
}