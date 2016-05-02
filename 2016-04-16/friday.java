import java.util.Scanner;
import java.util.GregorianCalendar;

public class friday {

	static int[] nums;
	static int totalFriday;
	static int[] daysInMonth;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
				
		nums = new int[400];
		totalFriday = 0;
		fillNums2();
		
		while(true) {
			long start = scanner.nextLong();
			long end = scanner.nextLong();
			
			if (start == 0 && end == 0) {
				break;
			}
			
			start -= 1583;
			end -= 1583;
			
			long set1 = start / 400;
			long set2 = end / 400;
			
			if (start % 400 == 0) {
				set1--;
			}
			
			long numFri1 = set1 * totalFriday + nums[(int) (start % 400 == 0 ? 399 : start % 400 - 1)];
			long numFri2 = set2 * totalFriday + nums[(int) (end % 400)];
			
			System.out.println(numFri2 - numFri1);
		}
	}
/*
2015 2015
2016 2016
2000 2015
0 0
 */
	public static void fillNums2() {
		// This is about x100 times slower
		for (int i = 0; i < 400; i++) {
			int count = 0;
			for (int j = 0; j < 12; j++) {
				GregorianCalendar g = new GregorianCalendar(1583 + i, j, 13);
				if (g.get(GregorianCalendar.DAY_OF_WEEK) == 6) {
					count++;
				}
			}
			totalFriday += count;
			if (i == 0) {
				nums[i] = count;
			} else {
				nums[i] = nums[i - 1] + count;				
			}
		}
		
	}
}
