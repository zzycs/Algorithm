import java.util.*;

/**
 * 数组算法
 * 
 * @author zzy
 *
 */
public class Algorithm_Array {

	public static void main(String[] args) {}

	/****************** 区间合并 ******************/

	static int[][] mergeIntervals(int[][] intervals) {
		if (intervals.length <= 1) return intervals;
		Arrays.sort(intervals, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		ArrayList<int[]> result = new ArrayList<>();
		int[] temp = intervals[0];
		result.add(temp);
		for (int[] interval : intervals) {
			if (interval[0] <= temp[1]) {
				temp[1] = Math.max(temp[1], interval[1]);
			} else {
				temp = interval;
				result.add(temp);
			}
		}
		return result.toArray(new int[result.size()][]);
	}

	/****************** 最长递增子序列 ******************/

	/**
	 * 求最长递增子序列长度 Longest Increasing Subsequence
	 */
	static int getLengthOfLIS(int[] array) {
		temp = new int[array.length];
		temp[0] = array[0];
		int length = 1;
		for (int i = 1; i < array.length; i++) {
			if (array[i] > temp[length - 1]) {
				temp[length] = array[i];
				length++;
			} else {
				int position = getInsertPosition(temp, array[i], 0, length - 1);
				temp[position] = array[i];
			}
		}
		return length;
	}

	/**
	 * 打印最长递增子序列 Longest Increasing Subsequence
	 */
	static void printLIS(int[] array) {
		int[] sortedArray = new int[array.length];
		for (int i = 0; i < array.length; i++)
			sortedArray[i] = array[i];
		Arrays.sort(sortedArray);
		getLengthOfLCS(array, sortedArray);
		printLCS(array, sortedArray, array.length, sortedArray.length);
	}

	/******************** 最长公共子序列 ********************/

	/**
	 * 求最长公共子序列长度 Longest Common Sequence
	 */
	static void getLengthOfLCS(int[] a1, int[] a2) {
		dp = new int[a1.length + 1][a2.length + 1];
		for (int i = 1; i <= a1.length; i++) {
			for (int j = 1; j <= a2.length; j++) {
				if (a1[i - 1] == a2[j - 1]) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else if (dp[i][j - 1] >= dp[i - 1][j]) {
					dp[i][j] = dp[i][j - 1];
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}
	}

	/**
	 * 打印最长公共子序列 Longest Common Sequence
	 */
	static void printLCS(int[] a1, int[] a2, int i, int j) {
		if (i == 0 || j == 0) return;
		if (dp[i][j] == dp[i - 1][j]) {
			printLCS(a1, a2, i - 1, j);
		} else if (dp[i][j] == dp[i][j - 1]) {
			printLCS(a1, a2, i, j - 1);
		} else {
			printLCS(a1, a2, i - 1, j - 1);
			System.out.print(a1[i - 1]);
		}
	}

	/******************** 数组的排序 ********************/

	/**
	 * 冒泡排序
	 */
	static int[] bubbleSort(int[] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = array.length - 1; j > i; j--) {
				if (array[j] < array[j - 1]) {
					exchange(array, j, j - 1);
				}
			}
		}
		return array;
	}

	/**
	 * 选择排序
	 */
	static int[] selectionSort(int[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			int min = i;
			for (int j = i + 1; j < array.length; j++) {
				if (array[j] < array[min]) {
					min = j;
				}
			}
			exchange(array, i, min);
		}
		return array;
	}

	/**
	 * 插入排序
	 */
	static int[] insertSort(int[] array) {
		for (int i = 1; i < array.length; i++) {
			for (int j = i; j > 0 && array[j] < array[j - 1]; j--) {
				exchange(array, j, j - 1);
			}
		}
		return array;
	}

	/**
	 * 希尔排序
	 */
	static int[] shellSort(int[] array) {
		int h = 1;
		while (h < array.length / 3)
			h = 3 * h + 1;
		while (h > 0) {
			for (int i = h; i < array.length; i++) {
				for (int j = i; j >= h && array[j] < array[j - h]; j -= h) {
					exchange(array, j, j - h);
				}
			}
			h = h / 3;
		}
		return array;
	}

	/**
	 * 归并排序（自顶向下）
	 */
	static int[] mergeSort1(int[] array) {
		temp = new int[array.length]; // 分配空间
		mergeSortRecursion(array, 0, array.length - 1);
		return array;
	}

	/**
	 * 归并排序（递归）
	 */
	static void mergeSortRecursion(int[] array, int low, int high) {
		if (low >= high) return;
		int mid = (low + high) / 2;
		mergeSortRecursion(array, low, mid);
		mergeSortRecursion(array, mid + 1, high);
		merge(array, low, mid, high);
	}

	/**
	 * 归并排序（自底向上）
	 */
	static int[] mergeSort2(int[] array) {
		temp = new int[array.length]; // 分配空间
		for (int size = 1; size < array.length; size += size) { // 每一次归并的数组大小，1、2、4、8、16...
			for (int low = 0; low < array.length - size; low += size * 2) {
				merge(array, low, low + size - 1, Math.min(low + size * 2 - 1, array.length - 1));
			}
		}
		return array;
	}

	/**
	 * 快速排序
	 */
	static int[] quickSort(int[] array) {
		quickSortRecursion(array, 0, array.length - 1);
		return array;
	}

	/**
	 * 快速排序（递归）
	 */
	static void quickSortRecursion(int[] array, int low, int high) {
		if (low >= high) return;
		int mid = partition(array, low, high);
		quickSortRecursion(array, low, mid - 1);
		quickSortRecursion(array, mid + 1, high);
	}

	/**
	 * 三向切分快速排序
	 */
	static int[] threeWayQuickSort(int[] array) {
		threeWayQuickSortRecursion(array, 0, array.length - 1);
		return array;
	}

	/**
	 * 三向切分快速排序（递归）
	 */
	static void threeWayQuickSortRecursion(int[] array, int low, int high) {
		if (low >= high) return;
		int element = array[low]; // 切分元素
		int index = low + 1, lt = low, gt = high; // 三指针索引
		while (index <= gt) {
			if (array[index] < element) {
				exchange(array, index++, lt++);
			} else if (array[index] > element) {
				exchange(array, index, gt--);
			} else {
				index++;
			}
		}
		threeWayQuickSortRecursion(array, low, lt - 1);
		threeWayQuickSortRecursion(array, gt + 1, high);
	}

	/**
	 * 堆排序
	 */
	static int[] heapSort(int[] array) {
		heapConstructor(array);
		int size = array.length;
		while (size > 0) {
			exchange(array, 0, --size);
			sink(array, 0, size);
		}
		return array;
	}

	/**
	 * 桶排序
	 */
	static int[] bucketSort(int[] array) {
		int min = array[0], max = array[0];
		for (int element : array) {
			max = Math.max(element, max);
			min = Math.min(element, min);
		}
		int[] bucket = new int[max - min + 1];
		for (int i = 0; i < array.length; i++) {
			bucket[array[i] - min]++;
		}
		int index = 0;
		for (int i = 0; i < bucket.length; i++) {
			for (int j = 0; j < bucket[i]; j++) {
				array[index++] = i + min;
			}
		}
		return array;
	}

	/**
	 * 计数排序
	 */
	static int[] countSort(int[] array) {
		int min = array[0], max = array[0];
		for (int element : array) {
			max = Math.max(element, max);
			min = Math.min(element, min);
		}
		int[] temp = new int[array.length];
		int[] count = new int[max - min + 1];
		for (int i = 0; i < array.length; i++) {
			count[array[i] - min]++;
		}
		for (int i = 1; i < array.length; i++) {
			count[i] += count[i - 1];
		}
		for (int i = array.length - 1; i >= 0; i--) {
			temp[--count[array[i] - min]] = array[i];
		}
		return temp;
	}

	/**
	 * 鸡尾酒排序
	 */
	static int[] cocktailSort(int[] array) {
		int i, left = 0, right = array.length - 1;
		while (left < right) {
			for (i = left; i < right; i++) {
				if (array[i] > array[i + 1]) {
					exchange(array, i, i + 1);
				}
			}
			right--;
			for (i = right; i > left; i--) {
				if (array[i - 1] > array[i]) {
					exchange(array, i - 1, i);
				}
			}
			left++;
		}
		return array;
	}

	/**
	 * 侏儒排序
	 */
	static int[] gnomeSort(int[] array) {
		for (int i = 0; i < array.length;) {
			if (i == 0 || array[i] >= array[i - 1]) {
				i++;
			} else {
				exchange(array, i, i - 1);
				i--;
			}
		}
		return array;
	}

	/**
	 * 臭皮匠排序
	 */
	static int[] stoogeSort(int[] array) {
		return stoogeSortRecursion(array, 0, array.length - 1);
	}

	static int[] stoogeSortRecursion(int[] array, int low, int high) {
		if (array[high] < array[low]) {
			exchange(array, low, high);
		}
		if (high - low + 1 >= 3) {
			int t = (high - low + 1) / 3;
			stoogeSortRecursion(array, low, high - t);
			stoogeSortRecursion(array, low + t, high);
			stoogeSortRecursion(array, low, high - t);
		}
		return array;
	}

	/******************** 数组查找 ********************/

	/**
	 * 线性查找
	 */
	static boolean linearSearch(int[] array, int target, int low, int high) {
		for (int i = low; i < high; i++) {
			if (array[i] == target) return true;
		}
		return false;
	}

	/**
	 * 二分查找（无重复数组）
	 */
	static int binarySearch(int[] array, int target, int low, int high) {
		int mid;
		while (low < high) {
			mid = (low + high) / 2;
			if (array[mid] < target) {
				low = mid + 1;
			} else if (array[mid] > target) {
				high = mid - 1;
			} else {
				return mid;
			}
		}
		return -1;
	}

	/**
	 * 查找重复有序数组中k的插入位置
	 */
	static int getInsertPosition(int[] array, int k, int low, int high) {
		int mid;
		while (low <= high) {
			mid = (low + high) / 2;
			if (array[mid] < k) {
				low = mid + 1;
			} else if (array[mid] > k) {
				high = mid - 1;
			} else {
				return mid;
			}
		}
		return low;
	}

	/**
	 * 查找重复有序数组中k出现的次数
	 */
	static int getNumberOfK(int[] array, int k, int low, int high) {
		int mid;
		while (low < high) {
			mid = (low + high) / 2;
			if (array[mid] < k) {
				while (array[mid] == array[mid + 1]) {
					mid++;
				}
				low = mid + 1;
			} else if (array[mid] > k) {
				while (array[mid] == array[mid - 1]) {
					mid--;
				}
				high = mid - 1;
			} else {
				int start = mid, end = mid;
				while (start >= low && array[start] == array[start - 1]) {
					start--;
				}
				while (end <= high && array[end] == array[end + 1]) {
					end++;
				}
				return start - end + 1;
			}
		}
		return 0;
	}

	/******************** 辅助方法 ********************/

	/**
	 * 交换数组中的两个元素
	 */
	static void exchange(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	/**
	 * 原地归并左右数组
	 */
	static int[] merge(int[] array, int low, int mid, int high) {
		for (int k = low; k <= high; k++)
			temp[k] = array[k]; // 更新数组
		int i = low, j = mid + 1; // 双指针索引
		for (int k = low; k <= high; k++) {
			if (i > mid) { // 左边耗尽
				array[k] = temp[j++];
			} else if (j > high) { // 右边耗尽
				array[k] = temp[i++];
			} else if (temp[i] <= temp[j]) { // 左小取左
				array[k] = temp[i++];
			} else { // 右小取右
				array[k] = temp[j++];
			}
		}
		return array;
	}

	/**
	 * 切分
	 */
	static int partition(int[] array, int low, int high) {
		int element = array[low]; // 切分元素
		int i = low, j = high + 1; // 双指针索引
		while (true) {
			while (element >= array[++i]) {
				if (i == high) break;
			}
			while (element <= array[--j]) {
				if (j == low) break;
			}
			if (i >= j) break;
			exchange(array, i, j);
		}
		exchange(array, j, low);
		return j;
	}

	/**
	 * 堆构造
	 */
	static int[] heapConstructor(int[] array) {
		for (int i = array.length / 2 - 1; i >= 0; i--) {
			sink(array, i, array.length);
		}
		return array;
	}

	/**
	 * 堆下沉
	 */
	static void sink(int[] array, int i, int size) {
		while (2 * (i + 1) <= size) {
			int j = 2 * i + 1; // 子节点索引
			if (j < size - 1 && array[j] < array[j + 1]) // 选出子节点中较大值
				j++; // 子节点索引+1
			if (array[i] >= array[j]) // 与子节点中较大值比较
				break;
			exchange(array, i, j);
			i = j;
		}
	}

	/**
	 * 堆上浮
	 */
	static void swim(int[] array, int i) {
		while (i > 0) {
			int j = (i - 1) / 2; // 父节点索引
			if (array[i] <= array[j]) break;
			exchange(array, i, j);
			i = j;
		}
	}

	/******************** 辅助字段 ********************/

	static int[] temp;
	static int[][] dp;
}
