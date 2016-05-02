import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class BufferedReaderPlayground {

	public static void main(String[] args) throws IOException {
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		
//		String s = in.readLine();
//		System.out.println(s);
		
		Scanner in = new Scanner(System.in);
		System.out.println(in.nextInt());
		System.out.println(in.nextInt());
	}

}
