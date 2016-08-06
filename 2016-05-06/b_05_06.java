import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class b_05_06 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int nt = Integer.parseInt(in.readLine());
		for (int counter = 1; counter <= nt; counter++) {
			char[] encoded = in.readLine().toCharArray();
			char[] decoded = in.readLine().toCharArray();
			
			System.out.printf("%d ", counter);
			for (int i = 0; i < encoded.length; i++) {
				if (encoded[i] == ' ') {
					System.out.printf(" ");
				} else {
					System.out.printf("%c", decoded[encoded[i] - 'A']);
				}
			}
			System.out.println();
		}
	}

}
