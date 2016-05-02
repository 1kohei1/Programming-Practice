import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.ArrayList;

public class kitchen {

	static int n;
	static int[] container;
	static int target;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		n = scanner.nextInt();
		
		container = new int[n];
		for (int i = 0; i < n; i++) {
			container[i] = scanner.nextInt();
		}
		target = scanner.nextInt();
		
		int answer = solve();
		System.out.println(answer == -1 ? "impossible" : answer);
	}

	public static int solve() {
		HashMap<Long, Integer> dp = new HashMap<Long, Integer>();
		PriorityQueue<Item_kitchen> q = new PriorityQueue<Item_kitchen>();
		
		int[] initialCups = new int[n];
		initialCups[0] = container[0];
		q.add(new Item_kitchen(convertToMask(initialCups), 0));
		
		while (q.size() > 0) {
			Item_kitchen ik = (Item_kitchen) q.poll();
//			System.out.println(ik);
			
			if (convertToCups(ik.mask)[0] == target) {
				return ik.cost;
			}
			ArrayList<Item_kitchen> cases = possibleCases(ik);
			
			for (int i = 0; i < cases.size(); i++) {
				Item_kitchen ik2 = cases.get(i);
				if (!dp.containsKey(ik2.mask) || ik2.cost < dp.get(ik2.mask)) {
					dp.put(ik2.mask, ik2.cost);
					q.add(ik2);
				}
			}
		}
		
		return -1;
	}
	
	public static ArrayList<Item_kitchen> possibleCases(Item_kitchen ik) {
		ArrayList<Item_kitchen> items = new ArrayList<Item_kitchen>();
		int[] cups = convertToCups(ik.mask);
//		printCups(cups);
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == j) continue;
				
				int[] temp = cups.clone();
				int amount = Math.min(temp[i], container[j] - cups[j]);
//				System.out.printf("i: %d, j: %d, amount: %d\n", i, j, amount);
				if (amount > 0) {
					temp[i] -= amount;
					temp[j] += amount;
					items.add(new Item_kitchen(convertToMask(temp), ik.cost + amount));
				}
			}
		}
		
		return items;
	}
	
	public static void printCups(int[] cups) {
		System.out.printf("cups: ");
		for (int i = 0; i < n; i++) {
			System.out.printf("%d ", cups[i]);
		}
		System.out.println();
	}
	
	public static int[] convertToCups(long mask) {
		int[] cups = new int[n];
		for (int i = 0; i < n; i++) {
			cups[n - i - 1] = (int) mask & 127;
			mask = mask >> 7;
		}
		return cups;
	}
	
	public static long convertToMask(int[] cups) {
		long num = 0;
		for (int i = 0; i < n; i++) {
			num = (num << 7) + cups[i];
		}
		return num;
	}
}
/*
2 5 2 3
4 9 6 3 2 8
5 11 10 7 4 2 10
2 5 2 4
5 64 45 41 28 2 63
 */

class Item_kitchen implements Comparable<Item_kitchen> {
	long mask;
	int cost;
	
	public Item_kitchen(long m, int c) {
		mask = m;
		cost = c;
	}

	@Override
	public int compareTo(Item_kitchen o) {
		return this.cost - o.cost;
	}

	@Override
	public String toString() {
		String s = "cost: " + cost + "\n"
				 + "cups: ";
		
		int[] cups = kitchen.convertToCups(mask);
		for (int i = 0; i < cups.length; i++) {
			s += cups[i] + " ";
		}
		return s;
	}
	
	
}