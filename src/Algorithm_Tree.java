import java.util.*;

/**
 * 树算法
 * 
 * @author zzy
 * 
 */
public class Algorithm_Tree {

	public static void main(String[] args) {
		String[] array = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
				"m", "n", "o", "p", "q" };
		Node_Tree tree = array2tree(array, 0);
		reversedTraversal(tree);
	}

	/******************** 树的遍历 ********************/

	/**
	 * 前序遍历（递归）
	 */
	public static void preorderRecursion(Node_Tree node) {
		if (node == null) return;
		System.out.print(node.value);
		preorderRecursion(node.left);
		preorderRecursion(node.right);
	}

	/**
	 * 中序遍历（递归）
	 */
	public static void inorderRecursion(Node_Tree node) {
		if (node == null) return;
		inorderRecursion(node.left);
		System.out.print(node.value);
		inorderRecursion(node.right);
	}

	/**
	 * 后序递归（递归）
	 */
	public static void postorderRecursion(Node_Tree node) {
		if (node == null) return;
		postorderRecursion(node.left);
		postorderRecursion(node.right);
		System.out.print(node.value);
	}

	/**
	 * 前序遍历（非递归）
	 */
	public static void preorderTraversal(Node_Tree node) {
		if (node == null) return;
		Stack<Node_Tree> stack = new Stack<>();
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
	public static void inorderTraversal(Node_Tree node) {
		if (node == null) return;
		Stack<Node_Tree> stack = new Stack<>();
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
	public static void postorderTraversal(Node_Tree node) {
		if (node == null) return;
		Stack<Node_Tree> stack = new Stack<>();
		Map<Node_Tree, Boolean> map = new HashMap<>();
		stack.push(node);
		while (!stack.isEmpty()) {
			node = stack.peek();
			if (node.left != null && !map.containsKey(node.left)) {
				while ((node = node.left) != null) {
					if (map.containsKey(node)) break;
					else stack.push(node);
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
	public static void levelTraversal(Node_Tree node) {
		if (node == null) return;
		Queue<Node_Tree> queue = new LinkedList<>();
		queue.add(node);
		while (!queue.isEmpty()) {
			node = queue.poll();
			System.out.print(node.value);
			if (node.left != null) queue.add(node.left);
			if (node.right != null) queue.add(node.right);
		}
	}

	/**
	 * 之字形顺序遍历
	 */
	public static void reversedTraversal(Node_Tree node) {
		Stack<Node_Tree> s1 = new Stack<>();
		Stack<Node_Tree> s2 = new Stack<>();
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

	/******************** 树的转换 ********************/

	/**
	 * 数组构建完全二叉树
	 */
	public static Node_Tree array2tree(String[] input, int start) {
		if (start >= input.length) return null;
		Node_Tree node = new Node_Tree(start, input[start]);
		node.left = array2tree(input, 2 * start + 1);
		node.right = array2tree(input, 2 * start + 2);
		return node;
	}

	/**
	 * 字符串构建二叉树1，e.g. 4(2(3)(1))(6(5))
	 */
	public static Node_Tree string2tree1(String input) {
		if (input == null || input.length() == 0) return null;
		int firstIndex = input.indexOf("(");
		Node_Tree root;
		if (firstIndex == -1) {
			root = new Node_Tree(Integer.parseInt(input));
			return root;
		} else {
			root = new Node_Tree(Integer.parseInt(input.substring(0, firstIndex)));
		}
		int start = firstIndex, count = 0;
		for (int i = start; i < input.length(); i++) {
			if (input.charAt(i) == '(') count++;
			else if (input.charAt(i) == ')') count--;
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
	public static Node_Tree string2tree2(String input) {
		if (input == null || input.length() == 0) return null;
		int firstIndex = input.indexOf("(");
		Node_Tree root;
		if (firstIndex == -1) {
			root = new Node_Tree(Integer.parseInt(input));
			return root;
		} else {
			root = new Node_Tree(Integer.parseInt(input.substring(0, firstIndex)));
		}
		int start = firstIndex;
		int count = 0;
		for (int i = start; i < input.length(); i++) {
			if (input.charAt(i) == '(') count++;
			else if (input.charAt(i) == ')') count--;
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
 * 树节点类（键为int，值为String）
 */
class Node_Tree {

	int key;
	String value;
	Node_Tree left, right;

	Node_Tree(int key) {
		this.key = key;
	}

	Node_Tree(int key, String value) {
		this.key = key;
		this.value = value;
	}
}

/**
 * 红黑树节点类
 */
class Node_RedBlack_Tree {

	int key;
	String value;
	Node_RedBlack_Tree left, right;
	boolean color; // 非红即黑

	Node_RedBlack_Tree(int key, String value) {
		this.key = key;
		this.value = value;
		this.color = false; // 默认为黑
	}

	Node_RedBlack_Tree(int key, String value, boolean color) {
		this.key = key;
		this.value = value;
		this.color = color;
	}
}