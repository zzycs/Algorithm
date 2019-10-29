import java.util.*;

/**
 * 树算法
 * 
 * @author 张梓扬
 * @email zhangziyangcn@outlook.com
 * 
 */
public class Algorithm_Tree {

	/******************** 二叉树 ********************/

	/**
	 * 二叉树中两个节点的最近公共祖先节点
	 */
	static TreeNode getLowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null || root == p || root == q) return root;
		TreeNode left = getLowestCommonAncestor(root.left, p, q);
		TreeNode right = getLowestCommonAncestor(root.right, p, q);
		if (left != null && right != null) return root;
		return left != null ? left : right;
	}

	/******************** 遍历 ********************/

	/**
	 * 前序遍历（递归）
	 */
	static void preorderTraversalRecursion(TreeNode node) {
		if (node == null) return;
		System.out.print(node.value);
		preorderTraversalRecursion(node.left);
		preorderTraversalRecursion(node.right);
	}

	/**
	 * 中序遍历（递归）
	 */
	static void inorderTraversalRecursion(TreeNode node) {
		if (node == null) return;
		inorderTraversalRecursion(node.left);
		System.out.print(node.value);
		inorderTraversalRecursion(node.right);
	}

	/**
	 * 后序递归（递归）
	 */
	static void postorderTraversalRecursion(TreeNode node) {
		if (node == null) return;
		postorderTraversalRecursion(node.left);
		postorderTraversalRecursion(node.right);
		System.out.print(node.value);
	}

	/**
	 * 前序遍历
	 */
	static void preorderTraversal(TreeNode node) {
		if (node == null) return;
		Stack<TreeNode> stack = new Stack<>();
		while (node != null || !stack.isEmpty()) {
			while (node != null) {
				System.out.print(node.value);
				stack.push(node);
				node = node.left;
			}
			node = stack.pop();
			node = node.right;
		}
	}

	/**
	 * 中序遍历
	 */
	static void inorderTraversal(TreeNode node) {
		if (node == null) return;
		Stack<TreeNode> stack = new Stack<>();
		while (node != null || !stack.isEmpty()) {
			while (node != null) {
				stack.push(node);
				node = node.left;
			}
			node = stack.pop();
			System.out.print(node.value);
			node = node.right;
		}
	}

	/**
	 * 后序遍历
	 */
	static void postorderTraversal(TreeNode node) {
		if (node == null) return;
		Stack<TreeNode> stack = new Stack<>();
		Map<TreeNode, Boolean> map = new HashMap<>();
		stack.push(node);
		while (!stack.isEmpty()) {
			node = stack.peek();
			if (node.left != null && !map.containsKey(node.left)) {
				while ((node = node.left) != null) {
					if (map.containsKey(node)) {
						break;
					} else {
						stack.push(node);
					}
				}
				continue;
			}
			if (node.right != null && !map.containsKey(node.right)) {
				stack.push(node.right);
				continue;
			}
			node = stack.pop();
			map.put(node, true);
			System.out.print(node.value);
		}
	}

	/**
	 * 层序遍历
	 */
	static void levelTraversal(TreeNode node) {
		if (node == null) return;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(node);
		while (!queue.isEmpty()) {
			node = queue.poll();
			System.out.print(node.value);
			if (node.left != null) {
				queue.add(node.left);
			}
			if (node.right != null) {
				queue.add(node.right);
			}
		}
	}

	/**
	 * 之字形顺序遍历
	 */
	static void reverseTraversal(TreeNode node) {
		Stack<TreeNode> s1 = new Stack<>();
		Stack<TreeNode> s2 = new Stack<>();
		int layer = 1;
		s1.push(node);
		while (!s1.isEmpty() || !s2.isEmpty()) {
			if (layer % 2 != 0) {
				ArrayList<String> temp = new ArrayList<>();
				while (!s1.isEmpty()) {
					node = s1.pop();
					if (node != null) {
						temp.add(node.value);
						s2.push(node.left);
						s2.push(node.right);
					}
				}
				if (!temp.isEmpty()) {
					for (String value : temp)
						System.out.print(value);
					System.out.println();
					layer++;
				}
			} else {
				ArrayList<String> temp = new ArrayList<>();
				while (!s2.isEmpty()) {
					node = s2.pop();
					if (node != null) {
						temp.add(node.value);
						s1.push(node.right);
						s1.push(node.left);
					}
				}
				if (!temp.isEmpty()) {
					for (String value : temp)
						System.out.print(value);
					System.out.println();
					layer++;
				}
			}
		}
	}

	/******************** 转换 ********************/

	/**
	 * 数组构建完全二叉树
	 */
	static TreeNode array2tree(String[] input, int start) {
		if (start >= input.length) return null;
		TreeNode node = new TreeNode(start, input[start]);
		node.left = array2tree(input, 2 * start + 1);
		node.right = array2tree(input, 2 * start + 2);
		return node;
	}

	/**
	 * 字符串构建二叉树1，e.g. 4(2(3)(1))(6(5))
	 */
	static TreeNode string2tree1(String input) {
		if (input == null || input.length() == 0) return null;
		int firstIndex = input.indexOf("(");
		TreeNode root;
		if (firstIndex == -1) {
			root = new TreeNode(Integer.parseInt(input));
			return root;
		} else {
			root = new TreeNode(Integer.parseInt(input.substring(0, firstIndex)));
		}
		int start = firstIndex, count = 0;
		for (int i = start; i < input.length(); i++) {
			if (input.charAt(i) == '(') {
				count++;
			} else if (input.charAt(i) == ')') {
				count--;
			}
			if (count == 0) {
				if (start == firstIndex) {
					root.left = string2tree1(input.substring(start + 1, i));
					start = i + 1;
				} else {
					root.right = string2tree1(input.substring(start + 1, i));
				}
			}
		}
		return root;
	}

	/**
	 * 字符串构建二叉树2，e.g. 1(2(3,4(,5)),6(7,))
	 */
	static TreeNode string2tree2(String input) {
		if (input == null || input.length() == 0) return null;
		int firstIndex = input.indexOf("(");
		TreeNode root;
		if (firstIndex == -1) {
			root = new TreeNode(Integer.parseInt(input));
			return root;
		} else {
			root = new TreeNode(Integer.parseInt(input.substring(0, firstIndex)));
		}
		int start = firstIndex;
		int count = 0;
		for (int i = start; i < input.length(); i++) {
			if (input.charAt(i) == '(') {
				count++;
			} else if (input.charAt(i) == ')') {
				count--;
			}
			if (count == 1 && input.charAt(i) == ',' && start == firstIndex) {
				root.left = string2tree2(input.substring(start + 1, i));
				start = i;
			} else if (i == input.length() - 1) {
				root.right = string2tree2(input.substring(start + 1, i));
			}
		}
		return root;
	}

}

/******************** 辅助类 ********************/

/**
 * 树节点
 */
class TreeNode {

	int key;
	String value;
	TreeNode left, right;

	TreeNode(int key) {
		this.key = key;
	}

	TreeNode(int key, String value) {
		this.key = key;
		this.value = value;
	}
}
