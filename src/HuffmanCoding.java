import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class HuffmanCoding {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			String s = scanner.nextLine();
			
			// You can end the program by entering 0
			if (s.length() == 1 && s.charAt(0) == '0') {
				break;
			}
			
			int[] freq = createFreq(s);
			
			PriorityQueue<HuffmanNode> pq = new PriorityQueue<HuffmanNode>();
			for (int i = 0; i < freq.length; i++) {
				if (freq[i] > 0) {
					pq.add(new HuffmanNode(i, freq[i]));
				}
			}
			
			// This if statement is to handle if there is only one character in the string.
			if (pq.size() == 1) {
				HuffmanNode a = pq.poll();
				HuffmanNode newNode = new HuffmanNode(-1, a.freq);
				newNode.left = a;
				pq.add(newNode);
			}
			while(pq.size() > 1) {
				HuffmanNode left = pq.poll();
				HuffmanNode right = pq.poll();
				HuffmanNode newNode = new HuffmanNode(-1, left.freq + right.freq);
				newNode.left = left;
				newNode.right = right;
				pq.add(newNode);
			}

			HuffmanNode root = pq.poll();

			HashMap<Integer, String> stats = new HashMap<Integer, String>();
			printResult(root, "", stats);
			printStats(s, stats, freq);
			printEncoded(s, stats);
		}
	}
	
	public static void printResult(HuffmanNode node, String encoded, HashMap<Integer, String> stats) {
		if (node == null) {
			return;
		}
		if (node.code >= 0) {
			System.out.printf("%c: %s\n", (char) node.code, encoded);
			stats.put(node.code, encoded);
			return;
		}
		printResult(node.left, encoded + "0", stats);
		printResult(node.right, encoded + "1", stats);
	}
	
	public static void printEncoded(String s, HashMap<Integer, String> stats) {
		System.out.print("Encoded message: ");
		char[] c = s.toCharArray();
		
		for (int i = 0; i < c.length; i++) {
			System.out.printf("%s", stats.get((int) c[i]));
		}
	}
	
	public static void printStats(String s, HashMap<Integer, String> stats, int[] freq) {
		int compressedSize = 0;
		for (Integer code : stats.keySet()) {
			compressedSize += freq[code] * stats.get(code).length();
		}
		int originalSize = 8 * s.length();
		
		System.out.println("Compressed size: " + compressedSize);
		System.out.println("Original size  : " + originalSize);
	}
		
	public static int[] createFreq(String s) {
		int[] freq = new int[256];
		char[] c = s.toCharArray();
		
		for (int i = 0; i < c.length; i++) {
			freq[(int) c[i]]++;
		}
		
		return freq;
	}
}

class HuffmanNode implements Comparable<HuffmanNode> {

	int code;
	int freq;
	HuffmanNode left;
	HuffmanNode right;
	
	public HuffmanNode(int code, int freq) {
		this.code = code;
		this.freq = freq;
		this.left = null;
		this.right = null;
	}
	
	@Override
	public int compareTo(HuffmanNode o) {
		return this.freq - o.freq;
	}
	
}