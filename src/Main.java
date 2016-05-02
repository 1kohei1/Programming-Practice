import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String s = in.readLine();
		
		while (s.length() > 0) {
			
			int[] nums = breakS(s);
			int numSoldiers = nums[0];
			int numReports = nums[1];
			
			if (numSoldiers == 0 && numReports == 0) {
				break;
			}
			
			TreeSet<Integer> trees = new TreeSet<Integer>();
			for (int i = 1; i <= numSoldiers; i++) {
				trees.add(i);
			}
			
			for (int i = 0; i < numReports; i++) {
				nums = breakS(in.readLine());
				int leftIndex = nums[0];
				int rightIndex = nums[1];
				
				for (int j = leftIndex; j <= rightIndex; j++) {
					trees.remove(j);
				}
				
				int nextLeft = 0;
				int nextRight = 0;
				
				if (trees.lower(leftIndex) == null) {
					nextLeft = -1;
				} else {
					nextLeft = trees.lower(leftIndex);
				}
				
				if (trees.higher(rightIndex) == null) {
					nextRight = -1;
				} else {
					nextRight = trees.higher(rightIndex);
				}
				
				System.out.println((nextLeft == -1 ? "*" : nextLeft) + " " + (nextRight == -1? "*" : nextRight));

			}
			System.out.println("-");
			s = in.readLine();			
		}
	}
	
	public static int[] breakS(String s) {
		String[] sArray = s.split(" ");
		int[] nums = new int[2];
		
		nums[0] = Integer.parseInt(sArray[0]);
		nums[1] = Integer.parseInt(sArray[1]);
		
		return nums;
	}
}
