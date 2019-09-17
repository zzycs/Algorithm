/**
 * 数组算法
 * 
 * @author zzy
 *
 */
public class Algorithm_Array {

	public static void main(String[] args) {
		int[] array = new int[] { 20, 6, 22, 16, 3, 14, 21, 12, 8, 1, 17, 5, 23, 10, 9, 2, 18, 7,
				4, 24, 19, 11, 15, 25, 13 };
		for (int i : countSort(array)) {
			System.out.print(i + " ");
		}
	}

	/******************** 最长递增子序列 ********************/

	/******************** 最长公共子序列 ********************/

	/******************** 数组的排序 ********************/

	/**
	 * 冒泡排序
	 */
	public static int[] bubbleSort(int[] array) {
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
	 * 鸡尾酒排序
	 */
	public static int[] cocktailSort(int[] array) {
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
	public static int[] gnomeSort(int[] array) {
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
	 * 选择排序
	 */
	public static int[] selectionSort(int[] array) {
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
	 * 臭皮匠排序
	 */
	public static int[] stoogeSort(int[] array) {
		return stoogeSortRecursion(array, 0, array.length - 1);
	}

	public static int[] stoogeSortRecursion(int[] array, int low, int high) {
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

	/**
	 * 插入排序
	 */
	public static int[] insertSort(int[] array) {
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
	public static int[] shellSort(int[] array) {
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
	public static int[] mergeSort1(int[] array) {
		temp = new int[array.length]; // 分配空间
		mergeSortRecursion(array, 0, array.length - 1);
		return array;
	}

	/**
	 * 归并排序（递归）
	 */
	public static void mergeSortRecursion(int[] array, int low, int high) {
		if (low >= high) return;
		int mid = (low + high) / 2;
		mergeSortRecursion(array, low, mid);
		mergeSortRecursion(array, mid + 1, high);
		merge(array, low, mid, high);
	}

	/**
	 * 归并排序（自底向上）
	 */
	public static int[] mergeSort2(int[] array) {
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
	public static int[] quickSort(int[] array) {
		quickSortRecursion(array, 0, array.length - 1);
		return array;
	}

	/**
	 * 快速排序（递归）
	 */
	public static void quickSortRecursion(int[] array, int low, int high) {
		if (low >= high) return;
		int mid = partition(array, low, high);
		quickSortRecursion(array, low, mid - 1);
		quickSortRecursion(array, mid + 1, high);
	}

	/**
	 * 三向切分快速排序
	 */
	public static int[] threeWayQuickSort(int[] array) {
		threeWayQuickSortRecursion(array, 0, array.length - 1);
		return array;
	}

	/**
	 * 三向切分快速排序（递归）
	 */
	public static void threeWayQuickSortRecursion(int[] array, int low, int high) {
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
	public static int[] heapSort(int[] array) {
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
	public static int[] bucketSort(int[] array) {
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
	public static int[] countSort(int[] array) {
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

	/******************** 辅助方法 ********************/

	/**
	 * 交换数组中的两个元素
	 */
	public static void exchange(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	/**
	 * 原地归并左右数组
	 */
	public static int[] merge(int[] array, int low, int mid, int high) {
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
	public static int partition(int[] array, int low, int high) {
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
	public static int[] heapConstructor(int[] array) {
		for (int i = array.length / 2 - 1; i >= 0; i--) {
			sink(array, i, array.length);
		}
		return array;
	}

	/**
	 * 堆下沉
	 */
	public static void sink(int[] array, int i, int size) {
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
	public static void swim(int[] array, int i) {
		while (i > 0) {
			int j = (i - 1) / 2; // 父节点索引
			if (array[i] <= array[j]) break;
			exchange(array, i, j);
			i = j;
		}
	}

	/******************** 辅助字段 ********************/

	public static int[] temp;
}
