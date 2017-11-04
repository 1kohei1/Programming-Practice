import java.util.*;

//
//

public class Main {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int N = in.nextInt();
		int[] A = new int[N];
		ArrayList<Integer> B = new ArrayList<Integer>();
		ArrayList<Integer> C = new ArrayList<Integer>();
		TreeSet<Integer> bSet = new TreeSet<Integer>();
		TreeSet<Integer> cSet = new TreeSet<Integer>();
		
		for (int i = 0; i < N; i++) {
			A[i] = in.nextInt();
		}
		for (int i = 0; i < N; i++) {
			int n = in.nextInt();
			B.add(n);
			bSet.add(n);
		}
		for (int i = 0; i < N; i++) {
			int n = in.nextInt();
			C.add(n);
			cSet.add(n);
		}

		B.sort(null);
		C.sort(null);
		
		long[] temp = new long[N];
		for (int i = 0; i < N; i++) {
			Integer c = cSet.higher(B.get(i));
			if (c != null) {
				int index = C.indexOf(c);
				temp[i] = N - index;
			} else {
				temp[i] = 0;
			}
		}
		
		for (int i = N - 2; i >= 0; i--) {
			temp[i] += temp[i + 1];
		}
		
		long answer = 0;
		for (int i = 0; i < N; i++) {
			Integer b = bSet.higher(A[i]);
			
			if (b != null) {
				int index = B.indexOf(b);
				answer += temp[index];
			}
		}
		System.out.println(answer);
	}
}