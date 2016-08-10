import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class QuadCombination {

	public static void main(String[] args) {
		
	}

	public static int[] solve(int[] arr, int S) {
		if (arr == null) {
			return null;
		}
		
		if (arr.length < 4) {
			return null;
		}
		
		HashMap<Integer, ArrayList<int[]>> map = new HashMap<Integer, ArrayList<int[]>>();
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				int sum = arr[i] + arr[j];
				int[] pair = new int[2];
				pair[0] = i;
				pair[1] = j;
				
				if (!map.containsKey(sum)) {
					map.put(sum, new ArrayList<int[]>());
				}
				ArrayList<int[]> pairs = map.get(sum);
				pairs.add(pair);
				map.put(sum, pairs);
			}
		}
		
		Set<Integer> keys = map.keySet();
		int[] sums = new int[keys.size()];
		
		int index = 0;
		for (Integer i : keys) {
			sums[index] = i;
			index++;
		}
		
		for (int i = 0; i < sums.length; i++) {
			int sum1 = sums[i];
			int sum2 = S - sum1;
			if (map.containsKey(sum2)) {
				// compare two array list if we can make array list
				ArrayList<int[]> sum1List = map.get(sum1);
				ArrayList<int[]> sum2List = map.get(sum2);
				
				// For each element in sum1List, check if the same value exists in sum2List
				for (int j = 0; j < sum1List.size(); j++) {
					for (int k = 0; k < sum2List.size(); k++) {
						int[] sum1Array = sum1List.get(j);
						int[] sum2Array = sum2List.get(k);
						
						if (sum1Array[0] != sum2Array[0] && 
							sum1Array[0] != sum2Array[1] &&
							sum1Array[1] != sum2Array[0] && 
							sum1Array[1] != sum2Array[1]) {
							int[] answer = new int[4];
							answer[0] = sum1Array[0];
							answer[1] = sum1Array[1];
							answer[2] = sum2Array[0];
							answer[3] = sum2Array[1];
							return answer;
						}
					}
				}
			}
		}
		
		return null;
	}
}
