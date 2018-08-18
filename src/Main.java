import java.util.*;

// ARC 106-D
// https://beta.atcoder.jp/contests/abc106/tasks/abc106_d

public class Main {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

      int N = in.nextInt();
      int M = in.nextInt();
      int Q = in.nextInt();

      int[][] map = new int[N + 1][N + 1];
      for (int i = 0; i < M; i++) {
        int L = in.nextInt();
        int R = in.nextInt();

        map[L][R] += 1;
      }

      for (int i = 0; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
          map[i][j] += map[i][j - 1];
        }
      }
      
      for (int i = 0; i < Q; i++) {
        int P = in.nextInt();
        int q = in.nextInt();

        int answer = 0;
        for (int j = P; j <= q; j++) {
          answer += map[j][q];
        }

        System.out.println(answer);
      }
    }
}
