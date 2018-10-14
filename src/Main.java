import java.util.*;

// AGC 28-A
// https://beta.atcoder.jp/contests/agc028/tasks/agc028_a

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int N = in.nextInt();
		int M = in.nextInt();
		String S = in.next();
		String T = in.next();
		
		long gcd = gcd(N, M);
		long lcm = lcm(N, M);

		boolean isPossible = true;
		for (int i = 0; i < gcd; i++) {
			int index1 = (int) (i * (lcm / M));
			int index2 = (int) (i * (lcm / N));
			
			if (S.charAt(index1) != T.charAt(index2)) {
				isPossible = false;
			}
		}
		
		if (isPossible) {
			System.out.println(lcm);
		} else {
			System.out.println(-1);
		}
	}
	
	// Taken from https://www.geeksforgeeks.org/c-program-find-gcd-hcf-two-numbers/
	public static long gcd(long a, long b) {
		if (b == 0) {
			return a;
		}
		return gcd(b, a % b);
	}
	
	public static long lcm(long a, long b) {
		return a * b / gcd(a, b);
	}
	
    public static List<List<Integer>> threeSum(int[] nums) {
    	Arrays.sort(nums);
    	
    	List<List<Integer>> answers = new LinkedList<>();
    	HashMap<Integer, ArrayList<Integer>> memo = new HashMap<Integer, ArrayList<Integer>>();
    	HashMap<String, Integer> uniqueAnswers = new HashMap<String, Integer>();
    	
    	for (int i = 0; i < nums.length; i++) {
    		int val = nums[i];
    		if (!memo.containsKey(val)) {
    			memo.put(val, new ArrayList<Integer>());
    		}
    		memo.get(val).add(i);
    	}
    	
    	int low = 0;
    	int high = nums.length - 1;
    	while (high - low > 1) {
    		int rem = 0 - nums[low] - nums[high];
    		// Check if rem exists in the nums
    		if (memo.containsKey(rem)) {
    			String str = "" + nums[low] + "," + rem + "," + nums[high];
    			// Check if it is unique answer
    			if (!uniqueAnswers.containsKey(str)) {
    				// Check if there is no duplicate index used
    				ArrayList<Integer> arr = memo.get(rem);
    				int len = arr.size();
    				for (int i = 0; i < arr.size(); i++) {
    					int val = arr.get(i);
    					if (val == low || val == high) {
    						len--;
    					}
    				}
    				
    				// It's an answer. Now, add it to answers and uniqueAnswers
    				if (len > 0) {
    					ArrayList<Integer> arr2 = new ArrayList<Integer>();
    					arr2.add(nums[low]);
    					arr2.add(rem);
    					arr2.add(nums[high]);
    					answers.add(arr2);
        				uniqueAnswers.put(str, 1);
    				}
    			}
    			low++;
    		} else if (0 - nums[low] - nums[high] > 0) {
    			low++;
    		} else {
    			high--;
    		}
    	}
    	
    	
    	return answers;
    }
}

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

class TreeDrawer {
	public static String treeNodeToString(TreeNode root) {
		if (root == null) {
			return "[]";
		}

		String output = "";
		Queue<TreeNode> nodeQueue = new LinkedList<>();
		nodeQueue.add(root);
		while (!nodeQueue.isEmpty()) {
			TreeNode node = nodeQueue.remove();

			if (node == null) {
				output += "null, ";
				continue;
			}

			output += String.valueOf(node.val) + ", ";
			nodeQueue.add(node.left);
			nodeQueue.add(node.right);
		}
		return "[" + output.substring(0, output.length() - 2) + "]";
	}

	public static TreeNode stringToTreeNode(String input) {
		input = input.trim();
		input = input.substring(1, input.length() - 1);
		if (input.length() == 0) {
			return null;
		}

		String[] parts = input.split(",");
		String item = parts[0];
		TreeNode root = new TreeNode(Integer.parseInt(item));
		Queue<TreeNode> nodeQueue = new LinkedList<>();
		nodeQueue.add(root);

		int index = 1;
		while (!nodeQueue.isEmpty()) {
			TreeNode node = nodeQueue.remove();

			if (index == parts.length) {
				break;
			}

			item = parts[index++];
			item = item.trim();
			if (!item.equals("null")) {
				int leftNumber = Integer.parseInt(item);
				node.left = new TreeNode(leftNumber);
				nodeQueue.add(node.left);
			}

			if (index == parts.length) {
				break;
			}

			item = parts[index++];
			item = item.trim();
			if (!item.equals("null")) {
				int rightNumber = Integer.parseInt(item);
				node.right = new TreeNode(rightNumber);
				nodeQueue.add(node.right);
			}
		}
		return root;
	}

	public static void prettyPrintTree(TreeNode node, String prefix, boolean isLeft) {
		if (node == null) {
			System.out.println("Empty tree");
			return;
		}

		if (node.right != null) {
			prettyPrintTree(node.right, prefix + (isLeft ? "│   " : "    "), false);
		}

		System.out.println(prefix + (isLeft ? "└── " : "┌── ") + node.val);

		if (node.left != null) {
			prettyPrintTree(node.left, prefix + (isLeft ? "    " : "│   "), true);
		}
	}

	public static void draw(TreeNode node) {
		prettyPrintTree(node, "", true);
	}
}
