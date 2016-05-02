import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

public class hillcipher {

	static int CHAR_ARRAY_SIZE = 10000;
	static int MAX_CHAR_IN_ONE_LINE = 80;
	
	static int n;
	static int count;
	static int[][] keyMatrix;
	
	public static void main(String[] args) {
		
		if (args.length < 2) {
			System.out.println("File names must be given. End the program");
			return;
		}
		
		File keyFile = null;
		File plainFile = null;
		
		try {
			// Initialize count
			count = 0;
			
			// Read key file
			keyFile = new File(args[0]);
			Scanner scanner = new Scanner(keyFile);
			
			n = scanner.nextInt();
			keyMatrix = new int[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					keyMatrix[i][j] = scanner.nextInt();
				}
			}
			printKeyFile();
			
			// Read plain file
			plainFile = new File(args[1]);
			scanner = new Scanner(plainFile);
			
			
			ArrayList<String> plainTexts = new ArrayList<String>();
			char[] ciphers = new char[CHAR_ARRAY_SIZE];
			int ciphersIndex = 0;
			
			while (scanner.hasNext()) {
				String s = scanner.nextLine();
				plainTexts.add(s);
				s = s.toLowerCase();
				
				for (int i = 0; i < s.length(); i++) {
					char c = s.charAt(i);
					
					if (Character.isLetter(c)) {
						ciphers[ciphersIndex] = c;
						ciphersIndex++;
					}
				}
			}
			
			// Pad the left by x
			for (int i = ciphersIndex; i % n != 0; i++) {
				ciphers[i] = 'x';
				ciphersIndex++;
			}
			
			printPlainFile(plainTexts);
			
			char[] ciphering = new char[n];
			
			for (int i = 0; i < ciphersIndex; i += n) {
				for (int j = 0; j < n; j++) {
					ciphering[j] = ciphers[i + j];
				}
				encode(ciphering);
			}
			System.out.println();
		} catch (Exception e) {
			System.out.println("Some error happened while reading file");
		}
	}
	
	public static void encode(char[] ciphering) {
		// Convert char into numbers
		int[] nums = new int[n];
		for (int i = 0; i < n; i++) {
			nums[i] = convertToNumber(ciphering[i]);
		}
		
		int[] ciphered = new int[n];
		// Calculate product of the matrix
		for (int i = 0; i < n; i++) {
			int rowSum = 0;
			for (int j = 0; j < n; j++) {
				rowSum += keyMatrix[i][j] * nums[j];
			}
			ciphered[i] = rowSum % 26;
		}
		printCiphered(ciphered);
	}
	
	public static int convertToNumber(char c) {
		return ((int) c) - 97;
	}
	
	public static char convertToChar(int n) {
		return (char) (n + 97);
	}
	
	public static void printCiphered(int[] ciphered) {
		for (int i = 0; i < ciphered.length; i++) {
			System.out.printf("%c", convertToChar(ciphered[i]));
			count++;
			if (count % 80 == 0 && count != 0) {
				System.out.println();
			}
		}
	}

	public static void printCiphers(char[] ciphers, int ciphersLength) {
		for (int i = 0; i < ciphersLength; i++) {
			System.out.printf("%c%s", ciphers[i], i % 5 == 0 && i != 0 ? " " : "");
		}
		System.out.println();
	}
	
	public static void printKeyFile() {
		System.out.println(n);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.printf("%d%s", keyMatrix[i][j], j == n - 1 ? "\n" : " ");
			}
		}
	}
	
	public static void printPlainFile(ArrayList<String> plainTexts) {
		for (int i = 0; i < plainTexts.size(); i++) {
			System.out.println(plainTexts.get(i));
		}
	}
}
