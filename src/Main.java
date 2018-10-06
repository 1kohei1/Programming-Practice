import java.util.*;

// ABC 112-C
// https://beta.atcoder.jp/contests/abc112/tasks/abc112_c

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int N = in.nextInt();
		int[] x = new int[N];
		int[] y = new int[N];
		int[] h = new int[N];
		
		long minHeight = Long.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			x[i] = in.nextInt();
			y[i] = in.nextInt();
			h[i] = in.nextInt();
			minHeight = Math.min(minHeight, h[i]);
		}
		
		boolean shouldContinue = true;
		for (int i = 0; i <= 100 && shouldContinue; i++) {
			for (int j = 0; j <= 100 && shouldContinue; j++) {
				long min = minHeight;
				long max = Long.MAX_VALUE - Integer.MAX_VALUE;
				while (min <= max && shouldContinue) {
					long tmpH = (min + max) / 2;
//					System.out.printf("(%d, %d, %d), min: %d, max: %d\n", i, j, tmpH, min, max);
					boolean isAnswerFound = true;
					for (int k = 0; k < N && isAnswerFound; k++) {
						long tmpH2 = Math.max(tmpH - Math.abs(x[k] - i) - Math.abs(y[k] - j), 0);
						if (tmpH2 < h[k]) {
							min = tmpH + 1;
							isAnswerFound = false;
						} else if (h[k] < tmpH2) {
							max = tmpH - 1;
							isAnswerFound = false;
						}
					}
					if (isAnswerFound) {
						System.out.printf("%d %d %d\n", i, j, tmpH);
						shouldContinue = false;
					}
				}
			}
		}
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