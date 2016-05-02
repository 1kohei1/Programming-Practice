import java.util.Scanner;

public class QuickSort {

	static int[] array;
	
	public static void main(String[] args) {
		for (int i = 0; i < 20; i++) {
			System.out.println(Math.random());
		}
//		Scanner scanner = new Scanner(System.in);
//		
//		int numElements = scanner.nextInt();
//		array = new int[numElements];
//		for (int i = 0; i < numElements; i++) {
//			array[i] = scanner.nextInt();
//		}
//		
//		quickSort();
//		for (int i = 0; i < numElements; i++) {
//			System.out.printf("%d ", array[i]);
//		}
	}
/*
11
8 2 6 15 4 9 13 3 1 17 7
*/

	public static void quickSort() {
		quickSortRec(0, array.length - 1);
	}
	
	public static void quickSortRec(int low, int high) {
		int pivotIndex = partition(low, high);
		swap(low, pivotIndex);

		System.out.printf("0. low: %d, high: %d\n", low, high);
		
		int lowBounds = low;
		int highBounds = high;
		low += 1;
		while(low < high) {
			System.out.printf("1. low: %d, high: %d\n", low, high);
			while(low <= highBounds && array[low] < array[lowBounds]) {
				low++;
			}
			if (low < high) break;
			
			while(high > 0 && array[lowBounds] < array[high]) {
				high--;
			}
			System.out.printf("2. low: %d, high: %d\n", low, high);
			swap(low, high);
		}
		swap(lowBounds, high);
		quickSortRec(lowBounds, high);
		quickSortRec(high + 1, highBounds);
	}
	
	// Generate random index value between low and high inclusive
	public static int partition(int low, int high) {
		return (int) (low + Math.random() * (high + 1));
	}
	
	public static void swap(int index1, int index2) {
		int temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
	}
}
