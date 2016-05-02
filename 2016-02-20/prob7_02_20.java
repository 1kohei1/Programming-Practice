import java.util.ArrayList;
import java.util.Scanner;

public class prob7_02_20 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int n = scanner.nextInt();
		scanner.nextLine();
		ArrayList<HashTag> tags = new ArrayList<HashTag>();
		
		for (int i = 0; i < n; i++) {
			String s = scanner.nextLine().toLowerCase();
			
			Scanner scanner2 = new Scanner(s);
			while (scanner2.hasNext()) {
				String ss = scanner2.next();
				if (ss.charAt(0) == '#' && ss.length() >= 2 && Character.isAlphabetic(ss.charAt(1))) {
					ss = makeItAlphabetical(ss.substring(1));
					
					HashTag h = new HashTag(ss);
					int index = tags.indexOf(h);
					if (index >= 0) {
						tags.get(index).occurrence++;
					} else {
						tags.add(h);
					}
				}
			}
			if (i != n - 1) {
				scanner.nextLine();				
			}
		}
		
		tags.sort(null);
		for (HashTag h : tags) {
			System.out.printf("%d %s\n", h.occurrence, h.hash);
		}
	}
	
	public static String makeItAlphabetical(String s) {
		int index = s.length() - 1;
		while (!('a' <= s.charAt(index) && s.charAt(index) <= 'z')) {
			index--;
		}
		return s.substring(0, index + 1);
	}
}

/*
9
It was great # of people! #! #.

Friendly reminder... batteries are not imported from the North Pole. #Christmas #Shopping

Santa is the man and he's got a plan I'm his biggest fan and he's coming at #Christmas #WalnutCreekStuff

It's Saturday night...who's watching the #SNL #Christmas episode?

So excited for #Christmas even though it doesn't feel like #Christmas

 Baileys #Christmas party was great. @GableBailey pic.twitter.com/egegergege
 
 Guys #Christmas is 3 days away!! Christmas is #soClose
 
 All i want for #christmas is, #food.
 
 Russ Rose would like to wish you all a Merry #Christmas without smiling. #SNL

  */

class HashTag implements Comparable<HashTag> {
	int occurrence;
	String hash;
	
	public HashTag(String a) {
		hash = a;
		occurrence = 1;
	}

	public int compareTo(HashTag arg0) {
		if (arg0.occurrence - occurrence == 0) {
			return this.hash.compareTo(arg0.hash);
		}
		return arg0.occurrence - occurrence;
	}

	public boolean equals(Object obj) {
		HashTag a = (HashTag) obj;
		return this.hash.equals(a.hash);
	}

	public String toString() {
		return this.hash;
	}
}
