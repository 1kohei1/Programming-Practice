import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// uva 10895

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String s1;
		while((s1 = in.readLine()) != null) {
			String[] s1Array = s1.split(" ");
			
			int row = Integer.parseInt(s1Array[0]);
			int col = Integer.parseInt(s1Array[1]);
			
			ArrayList[] answer = new ArrayList[col];
			for (int i = 0; i < col; i++) {
				answer[i] = new ArrayList<Element>();
			}
			
			for (int i = 0; i < row; i++) {
				// First line
				String s2 = in.readLine();
				String[] s2Array = s2.split(" ");
				
				int r = convert(s2Array[0]);
				int[] cols = new int[r];
				
				for (int j = 0; j < r; j++) {
					cols[j] = convert(s2Array[j + 1]);
				}
				
				// Second line
				String s3 = in.readLine();
				String[] s3Array = s3.split(" ");
				
				for (int j = 0; j < r; j++) {
					Element e = new Element(i + 1, convert(s3Array[j]));
					answer[cols[j] - 1].add(e);
				}
			}
			
			System.out.printf("%d %d\n", col, row);
			
			for (int i = 0; i < col; i++) {
				ArrayList<Element> e = answer[i];
				int size = e.size();
				String line1 = "";
				String line2 = "";
				
				for (int j = 0; j < size; j++) {
					line1 += e.get(j).col;
					line2 += e.get(j).val;
					
					if (j != size - 1) {
						line1 += " ";
						line2 += " ";
					}
				}
				
				if (size == 0) {
					System.out.println(size);
					System.out.println();
				} else {
					System.out.println(size + " " + line1);
					System.out.println(line2);
				}
			}
		}
		in.close();
	}
/*
4 3
3 1 2 3
1 3 2
2 2 3
4 -1
0

3 1 2 3
5 -2 11
 */
	
	public static void printStringArray(String[] s) {
		for (int i = 0; i < s.length; i++) {
			System.out.printf("%s ", s[i]);
		}
		System.out.println();
	}
	
	public static void printAnswer(ArrayList[] answer) {
		for (int i = 0; i < answer.length; i++) {
			System.out.printf("row %d: ", i);
			for (int j = 0; j < answer[i].size(); j++) {
				Element e = ((Element) answer[i].get(j));
				System.out.printf("(col: %d, val: %d) ", e.col, e.val);
			}
			System.out.println();
		}
	}
	
	public static int convert(String s) {
		return Integer.parseInt(s);
	}
}

class Element implements Comparable<Element> {
	public int col;
	public int val;
	
	public Element(int c, int v) {
		col = c;
		val = v;
	}

	@Override
	public int compareTo(Element o) {
		return this.col - o.col;
	}
}