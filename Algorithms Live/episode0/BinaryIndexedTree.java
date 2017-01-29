package episode0;

import java.util.Arrays;
import java.util.Scanner;

public class BinaryIndexedTree {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		System.out.printf("Enter the number of elements (0 to end the program): ");
		int n = in.nextInt();
		
		while (n != 0) {
			BIT bit = new BIT(n + 1);
			System.out.printf("Enter the number in the array: ");
			
			for (int i = 1; i <= n; i++) {
				bit.update(i, in.nextInt());
			}
			
			int option = 1;
			while (option != 0) {
				System.out.println("Options:");
				System.out.println("1: Calculate sum between elements");
				System.out.println("2: Update the value in the array");
				System.out.println("0: End");
				
				System.out.printf("Your option: ");
				option = in.nextInt();
				
				switch (option) {
				case 1:
					System.out.printf("index 1: ");
					int index1 = in.nextInt();
					System.out.printf("index 2: ");
					int index2 = in.nextInt();
					System.out.printf("Sum: %d\n", bit.rangeSum(index1 + 1, index2 + 1));
					break;
				case 2:
					System.out.printf("index: ");
					int index = in.nextInt();
					System.out.printf("value: ");
					int val = in.nextInt();
					bit.update(index, val - bit.table[index + 1]);
					break;
				case 0:
					break;
				default:
					
				}
				System.out.println();
			}			
			
			System.out.printf("Enter the number of elements (0 to end the program): ");
			n = in.nextInt();
		}
	}

}

class BIT {
	int size;
	int[] table;
	
	public BIT(int size) {
		this.size = size;
		this.table = new int[size];
		Arrays.fill(this.table, 0);
	}
	
	// update position i by delta
	void update(int i, int delta) {
		while (i < size) {
			table[i] += + delta;
			i += Integer.lowestOneBit(i);
		}
	}
	
	// compute the prefix sum value [1, i]
	int sum(int i) {
		int sum = 0;
		
		while (i > 0) {
			sum += table[i];
			i -= Integer.lowestOneBit(i);
		}
		return sum;
	}
	
	// compute the sum value [i, j]
	int rangeSum(int i, int j) {
		return sum(j) - sum(i - 1);
	}
	
}