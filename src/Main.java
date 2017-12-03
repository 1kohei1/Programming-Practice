import java.util.*;

// ABC 80-D
// https://beta.atcoder.jp/contests/abc080/tasks/abc080_d

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int N = in.nextInt();
		int C = in.nextInt();
		
		HashMap<Integer, ArrayList<Rokuga>> startTimes = new HashMap<Integer, ArrayList<Rokuga>>();
		
		for (int i = 0; i < N; i++) {
			int startTime = in.nextInt() - 1;
			int endTime = in.nextInt();
			int channel = in.nextInt();
			
			ArrayList<Rokuga> a = new ArrayList<Rokuga>();
			if (startTimes.containsKey(startTime)) {
				a = startTimes.get(startTime);
			}
			
			boolean isSameIn = false;
			
			for (int j = 0; j < a.size(); j++) {
				Rokuga r = a.get(j);
				if (r.endTime == endTime && r.channel == channel) {
					isSameIn = true;
				}
			}
			
			if (!isSameIn) {
				a.add(new Rokuga(endTime, channel));
			}
			startTimes.put(startTime, a);
		}
		
		HashMap<Integer, Integer> endTimes = new HashMap<Integer, Integer>(); // Stores endtime => number of rokuga ends in that end time
		int[] channels = new int[C + 1]; // Stores the end time of that channel
		Arrays.fill(channels, -1);
		int answer = 0;
		int temp = 0;
		
		for (int i = 0; i <= 100000; i++) {			
			// Start recording 
			if (startTimes.containsKey(i)) {
				ArrayList<Rokuga> arrlist = startTimes.get(i);
				for (int j = 0; j < arrlist.size(); j++) {
					int startTime = i;
					int endTime = arrlist.get(j).endTime;
					int channel = arrlist.get(j).channel;
					
					int tempAdd = 1;
					
					// Check if we can overlap the recording
					if (channels[channel] >= i) {
						// If recording stop time is less than current recording stop one, extend the recording stop time to current one. 
						// So that one recording machine can handle two recordings
						if (channels[channel] < endTime) {
							int n = endTimes.get(channels[channel]) - 1;
							if (n == 0) {
								endTimes.remove(channels[channel]);
							} else {
								endTimes.put(channels[channel], n);
							}
							channels[channel] = endTime;
						}
						tempAdd = 0;
					} else {
						channels[channel] = endTime;
					}
					
					if (endTimes.containsKey(endTime)) {
						endTimes.put(endTime, endTimes.get(endTime) + 1);
					} else {
						endTimes.put(endTime, 1);
					}
					temp += tempAdd;
				}
			}
			
			// Stop all rokuga that ends in this second
			if (endTimes.containsKey(i)) {
				temp -= endTimes.get(i);
				endTimes.remove(i);
			}

			answer = Math.max(answer, temp);
		}
		System.out.println(answer);
	}
}

class Rokuga {
	int endTime;
	int channel;
	
	public Rokuga(int endTime, int channel) {
		this.endTime = endTime;
		this.channel = channel;
	}
}