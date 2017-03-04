import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

// uva 11060

public class Main {

	static HashMap<String, Drink> map;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
	
		HashMap<String, Drink> map = new HashMap<String, Drink>();
		PriorityQueue<Drink> pq = new PriorityQueue<Drink>();
		int numTest = 1;
		
		while (in.hasNext()) {
			int n = in.nextInt();
			for (int i = 0; i < n; i++) {
				String s = in.next();
				Drink d = new Drink(s, i);
				map.put(s, d);
				pq.add(d);
			}
			
			int m = in.nextInt();
			for (int i = 0; i < m; i++) {
				String s1 = in.next();
				String s2 = in.next();
				
				// Update connectingTo
				Drink d;
				d = map.get(s1);
				d.connectingTo.add(s2);
				
				d = map.get(s2);
				pq.remove(d);
				d.numConnected++;
				pq.add(d);
			}
			
			// Output
			System.out.printf("Case #%d: Dilbert should drink beverages in this order:", numTest);
			while (pq.size() > 0) {
				Drink d = pq.remove();
				if (d.numConnected != 0) {
					System.out.println("Something is wrong\n");
					return;
				}
				
				for (int i = 0; i < d.connectingTo.size(); i++) {
					Drink dd = map.get(d.connectingTo.get(i));
					pq.remove(dd);
					dd.numConnected--;
					pq.add(dd);
				}
				System.out.printf(" %s", d.name);
			}
			System.out.println(".\n");
			numTest++;
		}
	}
}

class Drink implements Comparable<Drink> {

	int order;
	String name;
	ArrayList<String> connectingTo;
	int numConnected;
	
	public Drink(String name, int order) {
		this.order = order;
		this.name = name;
		this.connectingTo = new ArrayList<String>();
		this.numConnected = 0;
	}
	
	@Override
	public int compareTo(Drink o) {
		if (this.numConnected - o.numConnected != 0) {
			return this.numConnected - o.numConnected;
		} else {
			return this.order - o.order;
		}
	}
	
}