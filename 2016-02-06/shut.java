import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.List;
import java.util.Arrays;

/**
 * Will come back to this problem to solve by myself.
 * Solution is taken from http://www.icpc-midcentral.us/archives/2011/mcpc2011/shut/shutAndy.java.html
 */

public class shut {
	
	public static int MAX = 6;
	public static ArrayList<Integer>[] masks;
	
	static int maxShut = 0;
	
    static final int NMAX = 22, VMAX = 22, SET_LIM = 1 << NMAX;
    static int N, T;  //highest number, max turns
    static int[] turns;

     
    //precalc size of bitfield sets
    //choices.get(v) is list of all bitfield sets for roll of v, allowing NMAX
    static int[] bitCount = new int[SET_LIM];
    static List<List<Integer>> choices = new ArrayList<List<Integer>>();
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		// Create masks
		masks = new ArrayList[NMAX + 1];
		/**
		 * masks[i] stores all combinations added up to i. Let i = 5
		 * masks[5] = {16, 9, 6}. Each number is translated into
		 * 16 => 10000. Use {5} to create 5
		 * 9  => 01001. Use {4, 1} to create 5
		 * 6  => 00110. Use {3, 2} to create 5
		 */
//		for (int i = 0; i < 1<<MAX; i++) {
//			int sum = 0;
//			for (int j = 0; j < MAX; j++) {
//				if ((i & (1<<j)) != 0) {
//					sum += j & (j - 1);
//				}
//			}
//		}
//		printMasks();
		
//		int[] nums = new int[MAX];
//		for (int i = 0; i < MAX; i++) {
//			nums[i] = i + 1;
//		}
//		System.out.println("Integer MAX: " + Integer.MAX_VALUE);
//
//		for (int i = 0; i < 1 << MAX; i++) {
//			int sum = 0;
//			int comb = 0;
//			for (int j = 0; j < MAX; j++) {
//				if ((i & 1 << j) != 0) {
//					sum += nums[j];
//					// So represent which number I took by binary.
//					comb += j << 1 & j;
//					System.out.printf("+%d", nums[j]);
//				}
//			}
//			System.out.printf("=%d => (%d)\n", sum, comb);
//		}

//		int[] preSum = new int[1 << MAX];
//		for(int i = 1; i < 1 << MAX; i++){
//			int a = (int) (preSum[i-Integer.lowestOneBit(i)]+(Math.log(Integer.lowestOneBit(i))/Math.log(2.0))+1);
//			preSum[i] = a;
//			System.out.printf("preSum[%d]: %d\n", i, a);
//		}
		
		preCalc();
		// Inspect choices
//		for (int i = 0; i <= VMAX; i++) {
////			System.out.printf("choices[%d]: ", i);
////			for (int j = 0; j < choices.get(i).size(); j++) {
////				System.out.printf("%d, ", choices.get(i).get(j));
////			}
////			System.out.println();
//			System.out.printf("masks[%d]: ", i);
//			for (int j = 0; j < masks[i].size(); j++) {
//				System.out.printf("%d, ", masks[i].get(j));
//			}
//			System.out.println();
//		}
		// bitCount is for how many 1 exists to represent that number
		// Inspect bitcount
//		for (int i = 0; i < SET_LIM; i++) {
//			System.out.printf("[%d] => %d\n", i, bitCount[i]);
//		}

		int counter = 1;
//		while (true) {
//			int n = scanner.nextInt();
//			int t = scanner.nextInt();
//			if (n == 0 && t == 0) break;
//
//			boolean[] used = new boolean[n + 1];
//			Arrays.fill(used, false);
//			int[] targets = new int[t];
//			for (int i = 0; i < t; i++) {
//				targets[i] = scanner.nextInt();
//			}
//			
//			maxShut = 0;
//			solve(used, targets, 0, 0);
//			System.out.printf("Game %d: %d\n", counter, maxShut);
//		}
       N = scanner.nextInt();
        int dataSetN = 1;
        while (N > 0) {
            T = scanner.nextInt();
            turns = new int[T];
            for (int i = 0; i < T; i++)  
                turns[i] = scanner.nextInt();
            System.out.format("Game %d: %d%n", dataSetN, solve());
            dataSetN++;
            N = scanner.nextInt();
        }
	}

    /** 
    return best count
    */
    static int solve() {
        int unusedBits = ~((1 << N) - 1); //bits for (illegal) numbers above N
        HashSet<Integer> r = new HashSet<Integer>(), rLast;
        r.add(0);        
        int mostThroughLast = 0; // most shut through last completed roll 
//        int hSize=0;  // judge statistic
        for (int roll: turns) {
            rLast = r;
            r = new HashSet<Integer>();
            int mostThisRoll = 0; // most shut so far *using* this roll
            for (int s: choices.get(roll)) {
                if (0 != (s & unusedBits)) continue; //not use bit > N
                for (int t : rLast) 
                    if ((s&t) == 0 && !r.contains(s+t)) {
                        r.add(s+t);
                        mostThisRoll = Math.max(mostThisRoll, bitCount[s+t]);
                    }
            }
            if (mostThisRoll == N) return N;// won
            if (mostThisRoll == 0) return mostThroughLast; // lost
            mostThroughLast = Math.max(mostThisRoll, mostThroughLast);
//            hSize = max(hSize, r.size());  // judge statistic
        }
//        pr("Hashset size: " + hSize);
        return mostThroughLast;
    }	
	
//	public static void solve(boolean[] used, int[] targets, int index, int numShut) {
//		if (index == targets.length) {
//			if (numShut > maxShut) {
//				System.out.printf("maxShut: %d, numShut: %d, index: %d\n", maxShut, numShut, index);
//				printUsed(used);
//				maxShut = numShut;
//			}
//			return;
//		}
//		int targetNum = targets[index];
//		boolean didShut = false;
//		for (int i = 0; i < masks[targetNum].size(); i++) {
//			ArrayList<Integer> indexes = canUse(used, masks[targetNum].get(i));
//			if (indexes.size() > 0) {
//				didShut = true;
//				System.out.printf("[%d] => ", targetNum);
//				System.out.println(indexes);
//				for (int j = 0; j < indexes.size(); j++) {
//					System.out.printf("%d ", indexes.get(j));
//					used[indexes.get(j)] = true;
//				}
//				System.out.println();
//				solve(used, targets, index + 1, numShut + indexes.size());
//				for (int j = 0; j < indexes.get(j); j++) {
//					used[j] = false;
//				}
//			}
//		}
//		if (!didShut && numShut > maxShut) {
//			System.out.printf("maxShut: %d, numShut: %d, index: %d\n", maxShut, numShut, index);
//			printUsed(used);
//			maxShut = numShut;
//		}
//	}
	
	public static void printUsed(boolean[] used) {
		System.out.printf("Use: ");
		for (int i = 0; i < used.length; i++) {
			if (used[i]) {
				System.out.printf("%d, ", i + 1);				
			}
		}
		System.out.println();
	}
	
	public static ArrayList<Integer> canUse(boolean[] used, int num) {
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		int index = used.length - 1;
		while (index >= 0 && num != 0) {
			if (num >= 1 << index) {
				if (used[index]) {
					// Cannot use that num since that index is already used
					return new ArrayList<Integer>();
				} else {
					indexes.add(index + 1);
				}
				num -= 1 << index;
			}
			index--;
		}
		return indexes;
	}
	
	
   /**  calc bitCount, choices */
	static void preCalc() {
        for (int i = 0; i < SET_LIM; i++) { // calc bitCount
            int tot = 0, n = i;
            while (n != 0) {
               if ((n & 1) != 0)
                   tot++;
               n >>= 1;
            }
            bitCount[i] = tot;
        }
        ArrayList<Integer> noRoll = new ArrayList<Integer>();
        noRoll.add(0);
        choices.add(noRoll); //initial entry for 0 roll, just 0
        
        masks[0] = new ArrayList<Integer>();
        masks[0].add(0);
        
        // find sums for each roll
        for (int roll = 1; roll <= VMAX; roll++) {
        	// init for current roll
            Set<Integer> set = new HashSet<Integer>();
            // consider a mark
            for (int mark = 1; mark <= roll; mark++) {
            	// stored in bit field,and
                int bit = 1 << (mark-1);
        		// a choice for roll-mark
                for (int s : choices.get(roll-mark)) {
                	// if mark not used in the choice
                     if ((s & bit) == 0)
                    	// include it choice set for roll
                        set.add(s | bit);
                }
            }
            choices.add(new ArrayList<Integer>(set));//list iteration efficient
            masks[roll] = new ArrayList<Integer>(set);
        }
    }
	
	public static void printMasks() {
		for (int i = 0; i < MAX; i++) {
			System.out.printf("[%d]: ", i);
			for (int j = 0; j < masks[i].size(); j++) {
				System.out.printf("%d, ", masks[i].get(j));
			}
			System.out.println();
		}
	}
}
