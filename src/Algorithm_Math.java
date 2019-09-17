import java.util.*;

/**
 * 数学相关算法
 * 
 * @author zzy
 *
 */
public class Algorithm_Math {

	/******************** 斐波那契数列 ********************/

	/******************** 背包问题 ********************/

	/******************** 硬币问题 ********************/

	/******************** 排列组合 ********************/

	/**
	 * 数字的排列
	 */
	static void permutation(int[] array, int index, int length,
			ArrayList<ArrayList<Integer>> result) {
		if (index == length - 1) {
			ArrayList<Integer> list = new ArrayList<>();
			for (int i = 0; i < length; i++)
				list.add(array[i]);
			result.add(list);
		} else {
			for (int i = index; i < length; i++) {
				swap(array, index, i);
				permutation(array, index + 1, length, result);
				swap(array, index, i);
			}
		}
	}

	static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	/**
	 * 字符串的排列
	 */
	static void permutation(char[] chars, int index, ArrayList<String> result) {
		if (index == chars.length) {
			String s = String.valueOf(chars);
			if (!result.contains(s)) {
				result.add(s);
			}
		}
		for (int j = index; j < chars.length; j++) {
			swap(chars, index, j);
			permutation(chars, index + 1, result);
			swap(chars, j, index);
		}
	}

	static void swap(char[] array, int i, int j) {
		char temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
