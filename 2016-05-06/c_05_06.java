import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class c_05_06 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int nt = Integer.parseInt(in.readLine());
		for (int counter = 1; counter <= nt; counter++) {
			// First convert each decimal to horizontal and print them vertically.
			String[] s = in.readLine().split(":");
			
			int hour = Integer.parseInt(s[0]);
			int min = Integer.parseInt(s[1]);
			int sec = Integer.parseInt(s[2]);
			
			int[] hourH = convertToBinary(hour, 0, new int[6]);
			int[] minH = convertToBinary(min, 0, new int[6]);
			int[] secH = convertToBinary(sec, 0, new int[6]);
			
			String v = "";
			for (int i = 0; i < 6; i++) {
				v += "" + hourH[i] + "" + minH[i] + "" + secH[i];
			}
			String h = convertToString(hourH) + convertToString(minH) + convertToString(secH);
			
			System.out.println(counter + " " + v + " " + h);
		}
	}

	public static int[] convertToBinary(int num, int index, int[] n) {
		if (index == 6) {
			return n;
		}
		if ((num & 1) == 1) {
			n[5 - index] = 1;
		} else {
			n[5 - index] = 0;
		}
		return convertToBinary(num >> 1, index + 1, n);
	}

	public static String convertToString(int[] num) {
		String s = "";
		for (int i = 0; i < num.length; i++) {
			s += "" + num[i];
		}
		return s;
	}
}
