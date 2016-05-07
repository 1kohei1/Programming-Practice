import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

// uva 11572

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int nt = Integer.parseInt(in.readLine());
		
		for (int counter = 0; counter < nt; counter++) {
			int n = Integer.parseInt(in.readLine());
			
			int[] nums = new int[n];
			for (int i = 0; i < n; i++) {
				nums[i] = Integer.parseInt(in.readLine());
			}
			
			ArrayList<Integer> nums1 = new ArrayList<Integer>();
			nums1.add(0);
			
			int answer = 0;
			
			while (nums1.size() > 0) {
				int index = nums1.remove(0);
				
				HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
				int count = 0;
				
				for (int i = index; i < n; i++) {
					if (map.containsKey(nums[i])) {
						nums1.add(map.get(nums[i]) + 1);
						break;
					} else {
						map.put(nums[i], i);
					}
					count++;
				}
				
				if (count > answer) {
					answer = count;
				}
			}
			
			System.out.println(answer);
		}
	}
	
}
