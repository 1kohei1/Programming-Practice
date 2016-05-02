import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;

public class haircut_solution {

    private BufferedWriter bw;
    
    private static int numTestCases;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
	     numTestCases = scan.nextInt();
	        
	        for (int i = 0; i < numTestCases; i++) {
	            int numberOfBarbers = scan.nextInt();
	            long customerNumber = scan.nextInt();
	            
	            // int array holds the cutting speed of every barber.
	            int[] barberList = new int[numberOfBarbers];
	            
	            // determine the speed of the slowest barber, which determines
	            // the worst case scenario.
	            long slowestBarber = 0;
	            
	            for (int j = 0; j < numberOfBarbers; j++) {
	                barberList[j] = scan.nextInt();
	                slowestBarber = Math.max(slowestBarber, barberList[j]);           
	            }
	            
	            long timeServed = findTime(customerNumber, slowestBarber, barberList);
	            
	            // figure out the total number of customers served in the previous
	            // time period and get the remaining amount.
	            long customersRemaining = ((long) customerNumber) - countServedCustomers(timeServed - 1, barberList);
	            
	            for (int k = 0; k < barberList.length; k++) {
	                // true if barber available in this time period.
	                if (timeServed % barberList[k] == 0)
	                    customersRemaining--;
	                if (customersRemaining == 0) {
	                    System.out.println("Case #" + (i + 1) + ": " + (k+1));
//	                    try {
//	                    	System.out.println("Case #" + (i + 1) + ": " + (k+1));
//	                    }
//	                        bw.write("Case #" + (i + 1) + ": " + (k+1));
//	                        bw.newLine();
//	                    } catch (IOException e) {
//	                        System.err.println("Case #" + (i + 1) + " - ERROR: Could not write to file");
//	                    }
	                    break;
	                }
	            }
	          }
	}
	        public static long countServedCustomers(long time, int[] barberList) {
	            long customersServed = 0;
	            for (int i = 0; i < barberList.length; i++) {
	                customersServed += (time / barberList[i]) + 1;
	            }
	            return customersServed;
	        }
	        
	        public static long findTime(long customerNo, long slowestBarber, int[] barberList) {
	            long low = -1;
	            long high = slowestBarber * customerNo;
	            
	            while (low + 1 < high) {
	                long mid = (low + high) / 2;
	                if (countServedCustomers(mid, barberList) < customerNo)
	                    low = mid;
	                else
	                    high = mid;    
	            }
	            return high;
	        }
}
