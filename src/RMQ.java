import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RMQ {

	static int[] nums;
	static int[] rmq;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		while (true) {
			String ns = in.readLine();
			int n = Integer.parseInt(ns);
			if (n == 0) {
				break;
			}
			
			nums = new int[n];
			String numsS = in.readLine();
			String[] sArray = numsS.split(" ");
			for (int i = 0; i < sArray.length; i++) {
				nums[i] = Integer.parseInt(sArray[i]);
			}
			
			rmq = new int[2 * n];
			buildRMQ(0, n - 1, 1);
//			printRMQ();
			
			String rangeS = in.readLine();
			String[] rangeSArray = rangeS.split(" ");
			
			int start = Integer.parseInt(rangeSArray[0]);
			int end = Integer.parseInt(rangeSArray[1]);
			
			System.out.println(getMinimumIndexInRange(0, n - 1, 1, start, end));
		}
	}

	public static int buildRMQ(int left, int right, int index) {
		if (left == right) {
			rmq[index] = left;
			return left;
		}
		int leftRangeEnd = (left + right) / 2;
		int rightRangeStart = (left + right) / 2 + 1;
		
		int index1 = buildRMQ(left, leftRangeEnd, 2 * index);
		int index2 = buildRMQ(rightRangeStart, right, 2 * index + 1);
		
		int answer = 0;
		
		if (nums[index1] > nums[index2]) {
			rmq[index] = index2;
			answer = index2;
		} else {
			rmq[index] = index1;
			answer = index1;
		}
		
		return answer;
	}
	
	public static int getMinimumIndexInRange(int indexRangeStart, int indexRangeEnd, int index, int rangeStart, int rangeEnd) {
		// Out of requested range
		if (rangeEnd < indexRangeStart || indexRangeEnd < rangeStart) {
			return -1;
		}
		// index range is in the requested range.
		if (rangeStart <= indexRangeStart && indexRangeEnd <= rangeEnd) {
			return rmq[index];
		}

		int leftRangeEnd = (indexRangeStart + indexRangeEnd) / 2;
		int rightRangeStart = (indexRangeStart + indexRangeEnd) / 2 + 1;

		int left = getMinimumIndexInRange(indexRangeStart, leftRangeEnd, 2 * index, rangeStart, rangeEnd);
		int right = getMinimumIndexInRange(rightRangeStart, indexRangeEnd, 2 * index + 1, rangeStart, rangeEnd);
		
		if (left == -1) {
			return right;
		} else if (right == -1) {
			return left;
		} else {
			if (nums[left] > nums[right]) {
				return right;
			} else {
				return left;
			}
		}
	}
	
	public static void printRMQ() {
		for (int i = 0; i < rmq.length; i++) {
			System.out.printf("%d ", rmq[i]);
		}
		System.out.println();
	}
/*
7
18 17 13 19 15 11 20
	
 */
}
