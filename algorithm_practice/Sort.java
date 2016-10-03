import java.util.Scanner;

public class Sort {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		while (true) {
			int n = in.nextInt();
			int[] arr = new int[n];
			
			for (int i = 0; i < n; i++) {
				arr[i] = in.nextInt();
			}
//			bubble_sort(arr);
//			insertion_sort(arr);
//			selection_sort(arr);
//			arr = merge_sort(arr);
			quick_sort(arr, 0, n);
			printArray(arr);
		}
	}

	// start: index that the array range starts
	// end: index that's excluded to sort
	public static void quick_sort(int[] arr, int start, int end) {
		if (end - start <= 1) {
			return;
		}
		
		int index = start + 1;
		int biggerIndex = start + 1;
		
		while (index < end) {
			if (arr[index] < arr[start]) {
				swap(arr, index, biggerIndex);
				biggerIndex++;
			}
			index++;
		}
		swap(arr, start, biggerIndex - 1);
		
		quick_sort(arr, start, biggerIndex);
		quick_sort(arr, biggerIndex, end);
	}
	
	public static int[] merge_sort(int[] arr) {
		if (arr.length == 1) {
			return arr;
		}
		int[] left = leftArray(arr);
		int[] right = rightArray(arr);
				
		// Sort two arrays
		left = merge_sort(left);
		right = merge_sort(right);
		
		// Merge two arrays
		return merge(left, right);
	}
	
	public static int[] merge(int[] left, int[] right) {
		int[] sorted = new int[left.length + right.length];
		
		int index = 0;
		int leftIndex = 0;
		int rightIndex = 0;
		
		while (index < left.length + right.length) {
			if (leftIndex == left.length) {
				sorted[index] = right[rightIndex];
				rightIndex++;
			} else if (rightIndex == right.length) {
				sorted[index] = left[leftIndex];
				leftIndex++;
			} else if (left[leftIndex] < right[rightIndex]) {
				sorted[index] = left[leftIndex];
				leftIndex++;
			} else {
				sorted[index] = right[rightIndex];
				rightIndex++;
			}
			
			index++;
		}
		
		return sorted;
	}
	
	public static int[] leftArray(int[] arr) {
		int len = arr.length / 2;
		int[] newArray = new int[len];
		
		for (int i = 0; i < len; i++) {
			newArray[i] = arr[i];
		}
		
		return newArray;
	}
	
	public static int[] rightArray(int[] arr) {
		int len = arr.length / 2;
		len += arr.length % 2 == 0 ? 0 : 1;
		
		int[] newArray = new int[len];
		for (int i = 0; i < len; i++) {
			newArray[i] = arr[i + arr.length / 2];
		}
		
		return newArray;
	}
	
	
	
	public static void selection_sort(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			int swapIndex = i;
			for (int j = i; j < arr.length; j++) {
				if (arr[swapIndex] > arr[j]) {
					swapIndex = j;
				}
			}
			swap(arr, i, swapIndex);
		}
	}
	
	public static void insertion_sort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			for (int j = i - 1; j >= 0; j--) {
				if (arr[j] > arr[j + 1]) {
					swap(arr, j, j + 1);
				} else {
					break;
				}
			}
		}
	}

	public static void bubble_sort(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					swap(arr, j, j + 1);
				}
			}
		}
	}
	
	public static void printArray(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.printf("%d ", arr[i]);
		}
		System.out.println();
	}
	
	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
