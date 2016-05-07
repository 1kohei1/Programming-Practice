import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.TreeSet;

// uva 11286

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(in.readLine());
		
		while (n != 0) {
			HashMap<TreeSet<Integer>, Integer> map = new HashMap<TreeSet<Integer>, Integer>();
			
			int[] answer = new int[n + 1];
			
			for (int i = 0; i < n; i++) {
				String s = in.readLine();
				String[] sArray = s.split(" ");
				
				TreeSet<Integer> nums = new TreeSet<Integer>();
				for (int j = 0; j < 5; j++) {
					nums.add(Integer.parseInt(sArray[j]));
				}
				
				if (map.containsKey(nums)) {
					int prePopularity = map.get(nums);
					answer[prePopularity] -= prePopularity;
					
					int popularity = prePopularity + 1;
					
					map.put(nums, popularity);
					answer[popularity] += popularity;
				} else {
					map.put(nums, 1);
					answer[1]++;
				}
			}
			
			for (int i = n; i >= 0; i--) {
				if (answer[i] != 0) {
					System.out.println(answer[i]);
					break;
				}
			}
			
			n = Integer.parseInt(in.readLine());
		}
	}
	
}
