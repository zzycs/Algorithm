import java.util.*;

/**
 * 剑指Offer答案汇总
 * 
 * @author zzy
 *
 */
@SuppressWarnings("unused")
public class Solution {

	public static void main(String[] args) {
		int[] array = new int[] { 4, 5, 1, 6, 2, 7, 3, 8 };
		GetLeastNumbers_Solution(array, 8);
	}

	/******************** 排列组合 ********************/

	/**
	 * 字符串的排列
	 */
	public static ArrayList<String> Permutation(String str) {
		ArrayList<String> list = new ArrayList<>();
		if (str != null && str.length() > 0) {
			PermutationRec(str.toCharArray(), 0, list);
			Collections.sort(list);
		}
		return list;
	}

	public static ArrayList<String> PermutationRec(char[] chars, int i, ArrayList<String> list) {
		if (i == chars.length) {
			String s = String.valueOf(chars);
			if (!list.contains(s)) list.add(s);
		}
		for (int j = i; j < chars.length; j++) {
			swap(chars, i, j);
			PermutationRec(chars, i + 1, list);
			swap(chars, j, i);
		}
		return list;
	}

	public static void swap(char[] chars, int i, int j) {
		char temp = chars[i];
		chars[i] = chars[j];
		chars[j] = temp;
	}

	/******************** 数学 ********************/

	/**
	 * 剪绳子
	 */
	public static int cutRope(int target) {
		if (target < 2) return 0;
		if (target == 2) return 1;
		if (target == 3) return 2;
		int q3 = target / 3;
		if (target - q3 * 3 == 1) {
			q3--;
		}
		int q2 = (target - q3 * 3) / 2;
		return (int) Math.pow(3, q3) * (int) Math.pow(2, q2);
	}

	/**
	 * 数值的整数次方
	 */
	public static double Power(double base, int exponent) {
		boolean negative = false;
		double result = 1;
		if (exponent == 0) {
			return 1;
		} else if (exponent < 0) {
			if (base == 0) {
				throw new RuntimeException("Invaild");
			}
			exponent = -exponent;
			negative = true;
		}
		while (exponent != 0) {
			if ((exponent & 1) == 1) result *= base;
			base *= base;
			exponent >>= 1;
		}
		return negative ? 1 / result : result;
	}

	/******************** 穷举 ********************/

	/**
	 * 丑数
	 */
	public static int GetUglyNumber_Solution(int index) {
		if (index < 7) return index;
		int[] result = new int[index];
		result[0] = 1;
		int i, t2 = 0, t3 = 0, t5 = 0;
		for (i = 1; i < index; i++) {
			result[i] = Math.min(Math.min(result[t2] * 2, result[t3] * 3), result[t5] * 5);
			if (result[i] == result[t2] * 2) t2++;
			if (result[i] == result[t3] * 3) t3++;
			if (result[i] == result[t5] * 5) t5++;
		}
		return result[i - 1];
	}

	/**
	 * 整数中1出现的次数
	 */
	public static int NumberOf1Between1AndN_Solution(int n) {
		int count = 0;
		for (int i = 1; i <= n; i *= 10) {
			int a = n / i;
			int b = n % i;
			if (a % 10 == 0) {
				count = count + a / 10 * i;
			} else if (a % 10 == 1) {
				count = count + a / 10 * i + (b + 1);
			} else {
				count = count + (a / 10 + 1) * i;
			}
		}
		return count;
	}

	/******************** 递归 ********************/

	/**
	 * 斐波那契数列（递归）
	 */
	public static int Fibonacci1(int n) {
		if (n <= 1) return n;
		return Fibonacci1(n - 1) + Fibonacci1(n - 2);
	}

	/**
	 * 斐波那契数列（动态规划）
	 */
	public static int Fibonacci2(int n) {
		int f = 0, g = 1;
		while (n-- > 0) {
			g += f;
			f = g - f;
		}
		return f;
	}

	/**
	 * 斐波那契数列（数组动态规划）
	 */
	public static int Fibonacci3(int n) {
		if (n <= 1) return n;
		int[] record = new int[n + 1];
		record[0] = 0;
		record[1] = 1;
		for (int i = 2; i <= n; i++) {
			record[i] = record[i - 1] + record[i - 2];
		}
		return record[n];
	}

	/**
	 * 跳台阶
	 */
	public static int JumpFloor(int target) {
		if (target <= 0) return -1;
		else if (target == 1) return 1;
		else if (target == 2) return 2;
		return JumpFloor(target - 1) + JumpFloor(target - 2);
	}

	/**
	 * 变态跳台阶
	 */
	public static int JumpFloorII(int target) {
		if (target <= 0) return -1;
		else if (target == 1) return 1;
		return 2 * JumpFloorII(target - 1);
	}

	/**
	 * 矩形覆盖
	 */
	public static int RectCover(int target) {
		if (target <= 0) return 0;
		else if (target == 1) return 1;
		else if (target == 2) return 2;
		return RectCover(target - 1) + RectCover(target - 2);
	}

	/**
	 * 矩阵中的路径
	 */
	public static boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
		int[] temp = new int[matrix.length]; // 经过的索引
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (findPath(matrix, rows, cols, i, j, str, 0, temp)) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean findPath(char[] matrix, int rows, int cols, int i, int j, char[] str,
			int k, int[] temp) {
		int index = i * cols + j;
		if (i < 0 || i >= rows || j < 0 || j >= cols || matrix[index] != str[k]
				|| temp[index] == 1)
			return false;
		if (k == str.length - 1) return true;
		temp[index] = 1; // 标记
		if (findPath(matrix, rows, cols, i - 1, j, str, k + 1, temp) // 往上
				|| findPath(matrix, rows, cols, i + 1, j, str, k + 1, temp) // 往下
				|| findPath(matrix, rows, cols, i, j - 1, str, k + 1, temp) // 往左
				|| findPath(matrix, rows, cols, i, j + 1, str, k + 1, temp)) { // 往右
			return true;
		}
		temp[index] = 0; // 释放
		return false;
	}

	/**
	 * 机器人的运动范围
	 */
	public static int movingCount(int threshold, int rows, int cols) {
		return movingCountRec(threshold, rows, cols, 0, 0, new int[rows][cols]);
	}

	public static int movingCountRec(int threshold, int rows, int cols, int i, int j,
			int[][] temp) {
		if (i < 0 || i >= rows || j < 0 || j >= cols || numSum(i) + numSum(j) > threshold
				|| temp[i][j] == 1) {
			return 0;
		}
		temp[i][j] = 1;
		return 1 + movingCountRec(threshold, rows, cols, i + 1, j, temp)
				+ movingCountRec(threshold, rows, cols, i - 1, j, temp)
				+ movingCountRec(threshold, rows, cols, i, j + 1, temp)
				+ movingCountRec(threshold, rows, cols, i, j - 1, temp);
	}

	public static int numSum(int num) {
		int sum = 0;
		while (num > 0) {
			sum += (num % 10);
			num /= 10;
		}
		return sum;
	}

	/******************** 发散思维 ********************/

	/**
	 * 不用加减乘除做加法
	 */
	public static int Add(int a, int b) {
		if (b == 0) return a;
		return Add(a ^ b, (a & b) << 1);
	}

	/**
	 * 不用加减乘除做减法
	 */
	public static int Minus(int a, int b) {
		if (b == 0) return a;
		return Add(a ^ (~b + 1), (a & (~b + 1)) << 1);
	}

	/**
	 * 求1+2+3+...+n
	 */
	public static int Sum_Solution(int n) {
		int sum = n;
		boolean ans = n > 0 && (sum += Sum_Solution(n - 1)) > 0;
		return sum;
	}

	/**
	 * 读取数据流
	 */
	public static void Insert(Integer num) {
		if (((count++) & 1) == 0) {
			maxHeap.offer(num);
			minHeap.offer(maxHeap.poll());
		} else {
			minHeap.offer(num);
			maxHeap.offer(minHeap.poll());
		}
	}

	/**
	 * 数据流中的中位数
	 */
	public static Double GetMedian() {
		return (count & 1) == 0 ? (minHeap.peek() + maxHeap.peek()) / 2.0
				: new Double(minHeap.peek().intValue());
	}

	public static int count;
	public static PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
	public static PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(
			new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					return o2.compareTo(o1);
				}
			});

	/**
	 * 二进制中1的个数
	 */
	public static int NumberOf1(int n) {
		if (n == 0) return 0;
		if ((n & 1) != 0) {
			return NumberOf1(n >>> 1) + 1;
		}
		return NumberOf1(n >>> 1);
	}

	/******************** 数组 ********************/

	/**
	 * 数字在排序数组中出现的次数
	 */
	public static int GetNumberOfK(int[] array, int k) {
		return BinarySearch(array, k + 0.5) - BinarySearch(array, k - 0.5);
	}

	public static int BinarySearch(int[] array, double k) {
		int start = 0, end = array.length - 1;
		while (start <= end) {
			int mid = (start + end) / 2;
			if (k < array[mid]) {
				end = mid - 1;
			} else if (k > array[mid]) {
				start = mid + 1;
			}
		}
		return start;
	}

	/**
	 * 数组中只出现一次的数字
	 */
	public static void FindNumsAppearOnce(int[] array, int num1[], int num2[]) {
		if (array == null || array.length < 2) return;
		int temp = 0;
		for (int i = 0; i < array.length; i++) {
			temp ^= array[i];
		}
		int indexBit = FindIndexBit(temp);
		for (int i = 0; i < array.length; i++) {
			if (IsBit(array[i], indexBit)) {
				num1[0] ^= array[i];
			} else {
				num2[0] ^= array[i];
			}
		}
	}

	public static int FindIndexBit(int num) {
		int indexBit = 0;
		while (((num & 1) == 0) && (indexBit) < 8 * 4) {
			num = num >> 1;
			++indexBit;
		}
		return indexBit;
	}

	public static boolean IsBit(int num, int indexBit) {
		num = num >> indexBit;
		return (num & 1) == 1;
	}

	/**
	 * 顺时针打印矩阵
	 */
	public static ArrayList<Integer> printMatrix(int[][] matrix) {
		if (matrix == null || matrix.length == 0) return null;
		ArrayList<Integer> result = new ArrayList<>();
		int row = matrix.length, col = matrix[0].length; // 行数，列数
		int circle = ((row < col ? row : col) - 1) / 2 + 1;// 圈数
		for (int i = 0; i < circle; i++) {
			for (int j = i; j < col - i; j++) {
				result.add(matrix[i][j]);
			}
			for (int j = i + 1; j < row - i; j++) {
				result.add(matrix[j][col - 1 - i]);
			}
			for (int j = (col - 1) - i - 1; j >= i && row - i - 1 != i; j--) {
				result.add(matrix[row - i - 1][j]);
			}
			for (int j = (row - 1) - i - 1; j > i && col - i - 1 != i; j--) {
				result.add(matrix[j][i]);
			}
		}
		return result;
	}

	/**
	 * 二维数组中的查找
	 */
	public boolean Find(int target, int[][] array) {
		return findRec(target, array, 0, array[0].length - 1);
	}

	public boolean findRec(int target, int[][] array, int i, int j) {
		if (i > array.length - 1 || j < 0) {
			return false;
		} else if (array[i][j] == target) {
			return true;
		} else {
			if (array[i][j] > target) {
				return findRec(target, array, i, j - 1);
			} else {
				return findRec(target, array, i + 1, j);
			}
		}
	}

	/**
	 * 滑动窗口最大值
	 */
	public static ArrayList<Integer> maxInWindows(int[] num, int size) {
		ArrayList<Integer> result = new ArrayList<>();
		if (size == 0) return result;
		LinkedList<Integer> queue = new LinkedList<>(); // 存下标
		int index = 0; // 尾部索引
		for (int i = 0; i < num.length; i++) { // 头部索引
			index = i - size + 1; // 更新尾部索引
			if (queue.isEmpty()) queue.offer(i);
			else if (index > queue.peekFirst()) queue.pollFirst();// 判断当前最大值是否过期
			while (!queue.isEmpty() && num[queue.peekLast()] <= num[i]) // 从队尾开始比较，把所有比他小的值丢掉，即队头永远为最大值
				queue.pollLast();
			queue.offer(i); // 在队尾添加该元素
			if (index >= 0) result.add(num[queue.peekFirst()]); // 结果中添加队头
		}
		return result;
	}

	/**
	 * 旋转数组的最小数字
	 */
	public static int minNumberInRotateArray(int[] array) {
		int len = array.length;
		if (len == 0) return 0;
		int low = 0, high = len - 1;
		while (low < high) {
			int mid = (low + high) / 2;
			if (array[mid] > array[high]) {
				low = mid + 1;
			} else if (array[mid] < array[high]) {
				high = mid;
			} else {
				high = high - 1;
			}
		}
		return array[low];
	}

	/**
	 * 数组中重复的数字
	 */
	public static boolean duplicate(int numbers[], int length, int[] duplication) {
		for (int i = 0; i < length; i++) {
			int index = numbers[i];
			if (index >= length) {
				index -= length;
			}
			if (numbers[index] >= length) {
				duplication[0] = index;
				return true;
			}
			numbers[index] += length;
		}
		return false;
	}

	/**
	 * 构建乘积数组（N^2）
	 */
	public static int[] multiply1(int[] A) {
		int[] result = new int[A.length];
		for (int i = 0; i < A.length; i++) {
			int product = 1;
			for (int j = 0; j < A.length; j++) {
				if (i != j) {
					product *= A[j];
				}
			}
			result[i] = product;
		}
		return result;
	}

	/**
	 * 构建乘积数组（N）
	 */
	public static int[] multiply2(int[] A) {
		int len = A.length;
		int[] B = new int[len];
		if (len == 0) return B;
		// 计算下三角
		B[0] = 1;
		for (int i = 1; i < len; i++) {
			B[i] = B[i - 1] * A[i - 1];
		}
		// 计算上三角
		int temp = 1;
		for (int i = len - 2; i >= 0; i--) {
			temp *= A[i + 1];
			B[i] *= temp;
		}
		return B;
	}

	/**
	 * 扑克牌顺子
	 */
	public static boolean isContinuous(int[] numbers) {
		if (numbers.length != 5) return false;
		int[] temp = new int[14]; // 一次性分配桶
		int max = -1, min = 14;
		for (int i = 0; i < numbers.length; i++) {
			int number = numbers[i];
			temp[number]++;
			if (number == 0) continue;
			if (temp[number] > 1) return false; // 判断是否有重复
			if (number < min) min = number;
			if (number > max) max = number;
			if (max - min >= 5) return false;
		}
		return true;
	}

	/**
	 * 圆圈中最后剩下的数
	 */
	public static int LastRemaining_Solution(int n, int m) {
		int[] array = new int[n]; // 模拟环
		int index = -1;
		int count = 0; // 报数
		int size = n; // 总人数
		while (size > 0) {
			index++;
			if (index >= n) index = 0; // 模拟环
			if (array[index] == -1) continue; // 已删除的点
			count++; // 报数
			if (count == m) {
				array[index] = -1;
				count = 0;
				size--;
			}
		}
		return index;
	}

	/**
	 * 连续子数组的最大和
	 */
	public static int FindGreatestSumOfSubArray(int[] array) {
		int max = array[0];
		int carry = array[0];
		for (int i = 1; i < array.length; i++) {
			max = Math.max(max + array[i], array[i]);
			carry = Math.max(carry, max);
		}
		return carry;
	}

	/**
	 * 最小的K个数（最大堆）
	 */
	public static ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
		ArrayList<Integer> result = new ArrayList<>();
		if (k > input.length || k == 0) return result;
		PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2.compareTo(o1);
			}
		});
		for (int i = 0; i < k; i++) {
			queue.offer(input[i]);
		}
		for (int i = k; i < input.length; i++) {
			if (input[i] <= queue.peek()) {
				queue.offer(input[i]);
			}
			if (queue.size() > k) {
				queue.poll();
			}
		}
		while (!queue.isEmpty()) {
			result.add(queue.poll());
		}
		return result;
	}

	/**
	 * 最小的K个数（最小堆）
	 */
	public static ArrayList<Integer> GetLeastNumbers_Solution2(int[] input, int k) {
		ArrayList<Integer> result = new ArrayList<>();
		if (k > input.length) return result;
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		for (int i = 0; i < input.length; i++)
			queue.offer(input[i]);
		while (k-- > 0 && !queue.isEmpty())
			result.add(queue.poll());
		return result;
	}

	/**
	 * 数组中的逆序对
	 */
	public static int InversePairs(int[] array) {
		int length = array.length;
		if (length == 0) return 0;
		int[] copy = new int[length];
		for (int i = 0; i < length; i++)
			copy[i] = array[i];
		return (int) (InversePairsRec(array, copy, 0, array.length - 1) % 1000000007);
	}

	public static long InversePairsRec(int[] array, int[] copy, int low, int high) {
		if (low == high) return 0;
		int mid = (low + high) >> 1;
		long left = InversePairsRec(array, copy, low, mid);
		long right = InversePairsRec(array, copy, mid + 1, high);
		int i = mid, j = high, k = high;
		long count = 0;
		while (i >= low && j > mid) {
			if (array[i] > array[j]) {
				count += (j - mid);
				copy[k--] = array[i--];
			} else {
				copy[k--] = array[j--];
			}
		}
		for (; i >= low; i--)
			copy[k--] = array[i];
		for (; j > mid; j--)
			copy[k--] = array[j];
		for (int s = low; s <= high; s++)
			array[s] = copy[s];
		return left + right + count;
	}

	/**
	 * 数组中出现次数超过一半的数字
	 */
	public static int MoreThanHalfNum_Solution(int[] array) {
		if (array.length == 0) return 0;
		Arrays.sort(array);
		int count = 0;
		int mid = array.length / 2;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == array[mid]) {
				count++;
			}
		}
		return count > mid ? array[mid] : 0;
	}

	/**
	 * 把数组排成最小的数
	 */
	public static String PrintMinNumber(int[] numbers) {
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < numbers.length; i++) {
			list.add(numbers[i]);
		}
		Collections.sort(list, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				String s1 = o1 + "" + o2;
				String s2 = o2 + "" + o1;
				return s1.compareTo(s2);
			}
		});
		String string = "";
		for (int i : list) {
			string += i;
		}
		return string;
	}

	/**
	 * 调整数组顺序使奇数位于偶数前面
	 */
	public static void reOrderArray(int[] array) {
		if (array == null || array.length == 0) return;
		int i = 0, j = 0;
		while (i < array.length) {
			while (i < array.length && array[i] % 2 != 0) {
				i++; // 找偶数
			}
			j = i + 1;
			while (j < array.length && array[j] % 2 == 0) {
				j++; // 找奇数
			}
			if (j >= array.length) break;
			int temp = array[j];
			for (int k = j; k > i; k--) {
				array[k] = array[k - 1];
			}
			array[i] = temp;
			i++;
		}
	}

	/******************** 列表 ********************/

	/**
	 * 和为S的两个数字
	 */
	public static ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {
		ArrayList<Integer> result = new ArrayList<>();
		int i = 0, j = array.length - 1;
		while (i < j) {
			while (i < j && array[i] + array[j] > sum)
				j--;
			while (i < j && array[i] + array[j] < sum)
				i++;
			if (array[i] + array[j] == sum) {
				result.add(array[i]);
				result.add(array[j]);
				break;
			}
		}
		return result;
	}

	/**
	 * 和为S的连续正数序列
	 */
	public static ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<>();
		int low = 1, high = 2;
		while (low < high) {
			int sum0 = (low + high) * (high - low + 1) / 2;
			if (sum0 == sum) {
				ArrayList<Integer> list = new ArrayList<>();
				for (int i = low; i <= high; i++) {
					list.add(i);
				}
				result.add(list);
				low++;
			} else if (sum0 < sum) {
				high++;
			} else {
				low++;
			}
		}
		return result;
	}

	/******************** 链表 ********************/

	/**
	 * 翻转单词顺序列（递归）
	 */
	public static ArrayList<Integer> result = new ArrayList<>();

	public static ArrayList<Integer> printListFromTailToHead1(ListNode listNode) {
		if (listNode != null) {
			printListFromTailToHead1(listNode.next);
			result.add(listNode.val);
		}
		return result;
	}

	/**
	 * 翻转单词顺序列（栈）
	 */
	public static ArrayList<Integer> printListFromTailToHead2(ListNode listNode) {
		ArrayList<Integer> result = new ArrayList<>();
		Stack<Integer> stack = new Stack<>();
		while (listNode != null) {
			stack.push(listNode.val);
			listNode = listNode.next;
		}
		while (!stack.isEmpty()) {
			result.add(stack.pop());
		}
		return result;
	}

	/**
	 * 删除链表中的重复节点
	 */
	public static ListNode deleteDuplication(ListNode pHead) {
		if (pHead == null || pHead.next == null) return pHead;
		if (pHead.val == pHead.next.val) {
			ListNode next = pHead.next;
			while (next != null && next.val == pHead.val) {
				next = next.next;
			}
			return deleteDuplication(next);
		} else {
			pHead.next = deleteDuplication(pHead.next);
			return pHead;
		}
	}

	/**
	 * 链表中检测是否有环
	 */
	public static Boolean IsLoop(ListNode pHead) {
		if (pHead == null || pHead.next == null) return false;
		ListNode p1 = pHead;
		ListNode p2 = pHead;
		while (p2 != null && p2.next != null) {
			p1 = p1.next;
			p2 = p2.next.next;
			if (p1 == p2) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 链表中环的入口节点
	 */
	public static ListNode EntryNodeOfLoop(ListNode pHead) {
		if (pHead == null || pHead.next == null) return null;
		ListNode p1 = pHead;
		ListNode p2 = pHead;
		while (p2 != null && p2.next != null) {
			p1 = p1.next;
			p2 = p2.next.next;
			if (p1 == p2) {
				p1 = pHead;
				while (p1 != p2) {
					p1 = p1.next;
					p2 = p2.next;
				}
				return p1;
			}
		}
		return null;
	}

	/**
	 * 用两个栈实现队列
	 */
	public static Stack<Integer> stack1 = new Stack<Integer>();
	public static Stack<Integer> stack2 = new Stack<Integer>(); // 辅助栈

	public static void push(int node) {
		stack1.push(node);
	}

	public static int pop() {
		if (stack2.isEmpty()) {
			while (!stack1.isEmpty()) {
				stack2.push(stack1.pop());
			}
		}
		return stack2.pop();
	}

	/**
	 * 两个链表的第一个公共结点
	 */
	public static ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
		ListNode p1 = pHead1;
		ListNode p2 = pHead2;
		while (p1 != p2) {
			p1 = p1 == null ? pHead1 : p1.next;
			p2 = p2 == null ? pHead2 : p2.next;
		}
		return p1;
	}

	/**
	 * 链表中倒数第k个结点
	 */
	public static ListNode FindKthToTail(ListNode head, int k) {
		if (head == null || k == 0) return null;
		ListNode temp = head;
		while (--k > 0) {
			if (temp.next == null) return null;
			temp = temp.next;
		}
		while (temp.next != null) {
			temp = temp.next;
			head = head.next;
		}
		return head;
	}

	/**
	 * 合并两个排序的链表
	 */
	public static ListNode Merge(ListNode list1, ListNode list2) {
		if (list1 == null) return list2;
		if (list2 == null) return list1;
		ListNode head = null;
		ListNode temp = null;
		while (list1 != null && list2 != null) {
			if (list1.val < list2.val) {
				if (head == null) {
					head = temp = list1;
				} else {
					temp.next = list1;
					temp = temp.next;
				}
				list1 = list1.next;
			} else {
				if (head == null) {
					head = temp = list2;
				} else {
					temp.next = list2;
					temp = temp.next;
				}
				list2 = list2.next;
			}
		}
		if (list1 != null) temp.next = list1;
		if (list2 != null) temp.next = list2;
		return head;
	}

	/**
	 * 反转链表
	 */
	public static ListNode ReverseList(ListNode head) {
		if (head == null) return null;
		ListNode prev = null;
		ListNode next = null;
		while (head != null) {
			next = head.next;
			head.next = prev;
			prev = head;
			head = next;
		}
		return prev;
	}

	/**
	 * 复杂链表的复制
	 */
	public static RandomListNode Clone(RandomListNode pHead) {
		if (pHead == null) return null;
		RandomListNode current = pHead;
		while (current != null) {
			RandomListNode temp = new RandomListNode(current.label);
			temp.next = current.next;
			current.next = temp;
			current = temp.next;
		}
		current = pHead;
		while (current != null) {
			RandomListNode temp = current.next;
			temp.random = current.random == null ? null : current.random.next;
			current = temp.next;
		}
		current = pHead;
		RandomListNode result = pHead.next;
		while (current != null) {
			RandomListNode temp = current.next;
			current.next = temp.next;
			temp.next = temp.next == null ? null : temp.next.next;
			current = current.next;
		}
		return result;
	}

	/******************** 字符串 ********************/

	/**
	 * 第一个只出现一次的字符
	 */
	public static int FirstNotRepeatingChar(String str) {
		Map<Character, Integer> map = new LinkedHashMap<>(16, 0.75f, true);
		for (int i = str.length() - 1; i >= 0; i--) {
			if (map.get(str.charAt(i)) == null) {
				map.put(str.charAt(i), 1);
			} else {
				map.put(str.charAt(i), map.get(str.charAt(i)) + 1);
			}
		}
		for (int i = 0; i < str.length(); i++) {
			if (map.get(str.charAt(i)) == 1) return i;
		}
		return -1;
	}

	/**
	 * 翻转单词顺序列
	 */
	public static String ReverseSentence(String str) {
		if (str == null || str.length() == 0) {
			return "";
		}
		StringBuilder stringBuilder = new StringBuilder();
		Stack<Character> bin = new Stack<>();
		for (int i = str.length() - 1; i >= 0; i--) {
			if (str.charAt(i) == ' ') {
				// 出桶
				while (!bin.empty()) {
					stringBuilder.append(bin.pop());
				}
				stringBuilder.append(' ');
			} else {
				// 入桶
				bin.push(str.charAt(i));
			}
		}
		// 清桶
		while (!bin.empty()) {
			stringBuilder.append(bin.pop());
		}
		return stringBuilder.toString();
	}

	/**
	 * 把字符串转换成整数
	 */
	public static int StrToInt(String str) {
		if (str == null || str.length() == 0) return 0;
		int result = 0, start = 0;
		char[] cs = str.trim().toCharArray();
		if (cs[start] == '+' || cs[start] == '-') ++start;
		for (int i = start; i < cs.length; i++) {
			if (cs[i] < '0' || cs[i] > '9') {
				result = 0;
				break;
			}
			result = (result * 10) + (int) (cs[i] - '0');
		}
		if (start != 0) result = cs[0] == '+' ? result : -result;
		return result;
	}

	/**
	 * 左旋转字符串
	 */
	public static String LeftRotateString(String str, int n) {
		int len = str.length();
		if (len == 0) return "";
		n = n % len;
		str += str;
		return str.substring(n, n + len);
	}

	/**
	 * 替换空格
	 */
	public static String replaceSpace(StringBuffer str) {
		char[] dst = new char[str.length()];
		str.getChars(0, str.length(), dst, 0);
		for (int i = dst.length - 1; i >= 0; i--) {
			if (dst[i] == ' ') {
				str.replace(i, i + 1, "%20");
			}
		}
		return str.toString();
	}

	/**
	 * 字符流中第一个不重复的字符
	 */
	public static StringBuffer stringBuffer = new StringBuffer();
	public static int[] hash = new int[256];

	public static void Insert(char ch) {
		stringBuffer.append(ch);
		hash[ch]++;
	}

	public static char FirstAppearingOnce() {
		char[] str = stringBuffer.toString().toCharArray();
		for (char c : str) {
			if (hash[c] == 1) return c;
		}
		return '#';
	}

	/**
	 * 表示数值的字符串
	 */
	public static boolean isNumeric(char[] str) {
		boolean sign = false, decimal = false, hasE = false;
		int len = str.length;
		for (int i = 0; i < len; i++) {
			if (str[i] == 'e' || str[i] == 'E') {
				if (i == len - 1) return false; // 不能为最后一位
				if (hasE) return false; // 不能有两个E
				hasE = true;
			} else if (str[i] == '+' || str[i] == '-') {
				if (sign && str[i - 1] != 'e' && str[i - 1] != 'E') return false; // 第二次出现符号，但前一位不为E
				if (!sign && i > 0 && str[i - 1] != 'e' && str[i - 1] != 'E') return false;// 第一次出现符号，但不在开头，且前一位不为E
				sign = true;
			} else if (str[i] == '.') {
				if (decimal || hasE) return false; // 只能出现一次，并且不能与E一起用
				decimal = true;
			} else if (str[i] < '0' || str[i] > '9') {
				return false; // 其他不合法字符
			}
		}
		return true;
	}

	/**
	 * 正则表达式匹配
	 */
	public boolean match(char[] str, char[] pattern) {
		if (str == null || pattern == null) return false;
		return matchRec(str, 0, pattern, 0);
	}

	public boolean matchRec(char[] str, int strIndex, char[] pattern, int patternIndex) {
		if (patternIndex == pattern.length) { // pattern到尾，检验
			if (strIndex == str.length) return true; // str也到尾，匹配成功
			if (strIndex != str.length) return false; // 否则匹配失败
		}
		// 模式中第2位为'*'，且字符串第1位跟模式第1位匹配，分3种匹配模式；如不匹配，模式后移2位
		if (patternIndex + 1 < pattern.length && pattern[patternIndex + 1] == '*') {
			if (strIndex != str.length
					&& (pattern[patternIndex] == str[strIndex] || pattern[patternIndex] == '.')) {
				return matchRec(str, strIndex, pattern, patternIndex + 2) // 视为模式x*匹配0个字符
						|| matchRec(str, strIndex + 1, pattern, patternIndex + 2) // 视为模式x*匹配1位
						|| matchRec(str, strIndex + 1, pattern, patternIndex); // 视为模式x*匹配1位，再匹配下1位
			} else {
				return matchRec(str, strIndex, pattern, patternIndex + 2);
			}
		}
		// 模式中第2位不为'*'，若字符串第1位跟模式第1位匹配（模式可为'.'），则继续匹配下一位
		if (strIndex != str.length
				&& (str[strIndex] == pattern[patternIndex] || pattern[patternIndex] == '.')) {
			return matchRec(str, strIndex + 1, pattern, patternIndex + 1);
		}
		return false;
	}

	/******************** 树 ********************/

	/**
	 * 二叉树的深度
	 */
	public static int TreeDepth(TreeNode root) {
		if (root == null) return 0;
		return Math.max(TreeDepth(root.left), TreeDepth(root.right)) + 1;
	}

	/**
	 * 平衡二叉树1
	 */
	public static boolean IsBalanced_Solution1(TreeNode root) {
		if (root == null) return true;
		return (Math.abs(TreeDepth(root.left) - TreeDepth(root.right)) <= 1)
				&& (IsBalanced_Solution1(root.left) && IsBalanced_Solution1(root.right));
	}

	/**
	 * 平衡二叉树2
	 */
	public static boolean IsBalanced_Solution2(TreeNode root) {
		return getDepth(root) != -1;
	}

	public static int getDepth(TreeNode root) {
		if (root == null) return 0;
		int leftDepth = getDepth(root.left);
		if (leftDepth == -1) return -1;
		int rightDepth = getDepth(root.right);
		if (rightDepth == -1) return -1;
		return Math.abs(leftDepth - rightDepth) > 1 ? -1 : Math.max(leftDepth, rightDepth) + 1;
	}

	/**
	 * 二叉树的下一个结点
	 */
	public static TreeLinkNode GetNext(TreeLinkNode node) {
		if (node == null) return null;
		if (node.right != null) {
			node = node.right;
			while (node.left != null) {
				node = node.left;
			}
			return node;
		}
		while (node.parent != null) {
			if (node.parent.left == node) return node.parent;
			node = node.parent;
		}
		return null;
	}

	/**
	 * 对称的二叉树
	 */
	public static boolean isSymmetrical(TreeNode root) {
		if (root == null) return true;
		return symmetricTree(root.left, root.right);
	}

	public static boolean symmetricTree(TreeNode left, TreeNode right) {
		if (left == null) return right == null;
		if (right == null) return false;
		return left.val == right.val && symmetricTree(left.left, right.right)
				&& symmetricTree(left.right, right.left);
	}

	/**
	 * 二叉树的镜像
	 */
	public static void Mirror(TreeNode root) {
		if (root == null) return;
		TreeNode temp = root.right;
		root.right = root.left;
		root.left = temp;
		Mirror(root.left);
		Mirror(root.right);
	}

	/**
	 * 把二叉树打印成多行（递归）
	 */
	public static ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<>();
		Print(pRoot, 1, result);
		return result;
	}

	public static void Print(TreeNode pRoot, int depth, ArrayList<ArrayList<Integer>> result) {
		if (pRoot == null) return;
		if (depth > result.size()) result.add(new ArrayList<Integer>());
		result.get(depth - 1).add(pRoot.val);
		Print(pRoot.left, depth + 1, result);
		Print(pRoot.right, depth + 1, result);
	}

	/**
	 * 按之字形顺序打印二叉树
	 */
	public static ArrayList<ArrayList<Integer>> ReversedPrint(TreeNode pRoot) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<>();
		Stack<TreeNode> s1 = new Stack<>();
		Stack<TreeNode> s2 = new Stack<>();
		int layer = 1;
		s1.push(pRoot);
		while (!s1.isEmpty() || !s2.isEmpty()) {
			if (layer % 2 != 0) {
				ArrayList<Integer> temp = new ArrayList<>();
				while (!s1.isEmpty()) {
					TreeNode node = s1.pop();
					if (node != null) {
						temp.add(node.val);
						s2.push(node.left);
						s2.push(node.right);
					}
				}
				if (!temp.isEmpty()) {
					result.add(temp);
					layer++;
				}
			} else {
				ArrayList<Integer> temp = new ArrayList<>();
				while (!s2.isEmpty()) {
					TreeNode node = s2.pop();
					if (node != null) {
						temp.add(node.val);
						s1.push(node.right);
						s1.push(node.left);
					}
				}
				if (!temp.isEmpty()) {
					result.add(temp);
					layer++;
				}
			}
		}
		return result;
	}

	/**
	 * 序列化二叉树
	 */
	public static String Serialize(TreeNode root) {
		StringBuilder stringBuilder = new StringBuilder();
		if (root == null) {
			stringBuilder.append("#,");
			return stringBuilder.toString();
		}
		stringBuilder.append(root.val + ",");
		stringBuilder.append(Serialize(root.left));
		stringBuilder.append(Serialize(root.right));
		return stringBuilder.toString();
	}

	/**
	 * 反序列化二叉树
	 */
	public static TreeNode Deserialize(String str) {
		String[] array = str.split(",");
		TreeNode node = null;
		index++;
		if (!array[index].equals("#")) {
			node = new TreeNode(Integer.valueOf(array[index]));
			node.left = Deserialize(str);
			node.right = Deserialize(str);
		}
		return node;
	}

	public static int index = -1;

	/**
	 * 二叉搜索树的第k个节点
	 */
	public static TreeNode KthNode(TreeNode pRoot, int k) {
		if (pRoot != null) {
			TreeNode temp = KthNode(pRoot.left, k);
			if (temp != null) return temp;
			if (++acc == k) return pRoot;
			return KthNode(pRoot.right, k);
		}
		return null;
	}

	public static int acc;

	/**
	 * 重建二叉树
	 */
	public static TreeNode ReconstructBinaryTree(int[] pre, int[] in) {
		return ReconstructBinaryTree(pre, 0, pre.length - 1, in, 0, in.length - 1);
	}

	public static TreeNode ReconstructBinaryTree(int[] pre, int preStart, int preEnd, int[] in,
			int inStart, int inEnd) {
		if (preStart > preEnd || inStart > inEnd) return null;
		TreeNode root = new TreeNode(pre[preStart]);
		for (int i = inStart; i <= inEnd; i++) {
			if (pre[preStart] == in[i]) {
				int shift = i - inStart;
				root.left = ReconstructBinaryTree(pre, preStart + 1, preStart + shift, in, inStart,
						i - 1);
				root.right = ReconstructBinaryTree(pre, preStart + shift + 1, preEnd, in, i + 1,
						inEnd);
				break;
			}
		}
		return root;
	}

	/**
	 * 二叉搜索树与双向链表
	 */
	public static TreeNode leftLast = null;

	public static TreeNode Convert(TreeNode pRootOfTree) {
		if (pRootOfTree == null) {
			return null;
		}
		if (pRootOfTree.left == null && pRootOfTree.right == null) {
			leftLast = pRootOfTree;
			return pRootOfTree;
		}
		TreeNode left = Convert(pRootOfTree.left);
		if (left != null) {
			leftLast.right = pRootOfTree;
			pRootOfTree.left = leftLast;
		}
		leftLast = pRootOfTree;
		TreeNode right = Convert(pRootOfTree.right);
		if (right != null) {
			right.left = pRootOfTree;
			pRootOfTree.right = right;
		}
		return left != null ? left : pRootOfTree;
	}

	/**
	 * 树的子结构
	 */
	public static boolean HasSubtree(TreeNode root1, TreeNode root2) {
		if (root1 == null || root2 == null) return false;
		return isSubtree(root1, root2) || HasSubtree(root1.left, root2)
				|| HasSubtree(root1.right, root2);
	}

	public static boolean isSubtree(TreeNode root1, TreeNode root2) {
		if (root2 == null) return true;
		if (root1 == null) return false;
		if (root1.val == root2.val)
			return isSubtree(root1.left, root2.left) && isSubtree(root1.right, root2.right);
		return false;
	}

	/******************** 辅助类 ********************/

	/**
	 * 树节点1
	 */
	private static class TreeNode {
		int val = 0;
		TreeNode left = null;
		TreeNode right = null;

		public TreeNode(int val) {
			this.val = val;
		}
	}

	/**
	 * 树节点2
	 */
	private static class TreeLinkNode {
		int val;
		TreeLinkNode left = null;
		TreeLinkNode right = null;
		TreeLinkNode parent = null;

		TreeLinkNode(int val) {
			this.val = val;
		}
	}

	/**
	 * 链表节点
	 */
	private static class ListNode {
		int val;
		ListNode next = null;

		ListNode(int val) {
			this.val = val;
		}
	}

	/**
	 * 随机链表节点
	 */
	private static class RandomListNode {
		int label;
		RandomListNode next = null;
		RandomListNode random = null;

		RandomListNode(int label) {
			this.label = label;
		}
	}

	/******************** 辅助方法 ********************/

	public static void SquareSort(int[] array) {
		for (int i = 1; i < array.length; i++) {
			for (int j = i; j > 0 && array[j] < array[j - 1]; j--) {
				int temp = array[j];
				array[j] = array[j - 1];
				array[j - 1] = temp;
			}
		}
	}
}