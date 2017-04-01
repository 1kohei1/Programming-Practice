import java.util.*;
 
// ABC 7-C
// https://abc007.contest.atcoder.jp/tasks/abc007_3
 
public class Main {
 
	static int[] dx = new int[]{-1, 0, 1, 0};
	static int[] dy = new int[]{0, -1, 0, 1};
	
	public static void main (String[] args) throws java.lang.Exception {
	    Scanner in = new Scanner(System.in);
	   
	    int r = in.nextInt();
	    int c = in.nextInt();
	    
	    int sx = in.nextInt() - 1;
	    int sy = in.nextInt() - 1;
	    
	    int gx = in.nextInt() - 1;
	    int gy = in.nextInt() - 1;
	    
	    char[][] map = new char[r][c];
	    for (int i = 0; i < r; i++) {
	    	map[i] = in.next().toCharArray();
	    }
	    
	    int answer = 0;
	    ArrayList<Integer> q = new ArrayList<Integer>();
	    q.add(sx);
	    q.add(sy);
	    q.add(0);
	    
	    while (q.size() > 0) {
	    	int cx = q.remove(0);
	    	int cy = q.remove(0);
	    	int step = q.remove(0);
	    
	    	if (cx == gx && cy == gy) {
	    		answer = step;
	    		break;
	    	}
	    	
	    	// This node is already visited.
	    	if (map[cx][cy] == '#') {
	    		continue;
	    	}
	    	
	    	// Make it visited
	    	map[cx][cy] = '#';
	    	
	    	for (int i = 0; i < 4; i++) {
	    		int nx = cx + dx[i];
	    		int ny = cy + dy[i];
	    		
	    		if (0 <= nx && nx < r && 0 <= ny && ny < c && map[nx][ny] == '.') {
	    			q.add(nx);
	    			q.add(ny);
	    			q.add(step + 1);
	    		}
	    	}
	    }
	    
	    System.out.println(answer);
	}
}
