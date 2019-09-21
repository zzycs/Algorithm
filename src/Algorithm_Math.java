import java.util.*;

/**
 * 数学相关算法
 * 
 * @author zzy
 *
 */
public class Algorithm_Math {

	public static void main(String[] args) {
		
	}

	/******************** 分班问题 ********************/

	/******************** 任务分配问题 ********************/

	/******************** 硬币问题 ********************/

	/******************** 背包问题 ********************/

	/**
	 * 0-1背包
	 */
	public static int[] ZeroOnePack(int[] weight, int[] value, int resource, int n) {
		int[][] dp = new int[n][resource + 1];
		int[] knapsnack = new int[n];
		int x = 0, y;
		// 初始化第一行
		for (y = 1; y <= resource; y++) {
			dp[x][y] = weight[x] > y ? 0 : value[x];
		}
		// 动态规划
		for (x = 1; x < n; x++) {
			for (y = 1; y <= resource; y++) {
				if (y < weight[x]) {
					dp[x][y] = dp[x - 1][y];
				} else {
					dp[x][y] = Math.max(dp[x - 1][y], dp[x - 1][y - weight[x]] + value[x]);
				}
			}
		}
		// 回溯
		x = n - 1;
		y = resource;
		while (x > 0) {
			if (dp[x][y] == dp[x - 1][y - weight[x]] + value[x]) {
				knapsnack[x] = 1;
				y -= weight[x];
			}
			x--;
		}
		if (dp[x][y] > 0) knapsnack[x] = 1;
		// 最大价值总和
		int max = dp[n - 1][resource];
		System.out.println("Max Value: " + max);
		return knapsnack;
	}

	/**
	 * 完全背包
	 */
	public static int[] CompletePack(int[] weight, int[] value, int resource, int n) {
		int[][] dp = new int[n][resource + 1];
		int[] knapsnack = new int[n];
		int x = 0, y;
		// 初始化第一行
		for (y = 1; y <= resource; y++) {
			dp[x][y] = y / weight[x] * value[x];
		}
		// 动态规划
		for (x = 1; x < n; x++) {
			for (y = 1; y <= resource; y++) {
				if (y < weight[x]) {
					dp[x][y] = dp[x - 1][y];
				} else {
					dp[x][y] = Math.max(dp[x - 1][y], dp[x][y - weight[x]] + value[x]);
				}
			}
		}
		// 回溯
		x = n - 1;
		y = resource;
		while (x > 0) {
			while (y >= weight[x] && dp[x][y] == dp[x][y - weight[x]] + value[x]) {
				knapsnack[x]++;
				y -= weight[x];
			}
			x--;
		}
		if (dp[x][y] > 0) knapsnack[x] = 1;
		// 最大价值总和
		int max = dp[n - 1][resource];
		System.out.println("Max Value: " + max);
		return knapsnack;
	}

	/**
	 * 多重背包
	 */
	public static int[] MultiPack(int[] weight, int[] value, int[] number, int resource, int n) {
		int[][] dp = new int[n][resource + 1];
		int[] knapsnack = new int[n];
		int x = 0, y;
		// 初始化第一行
		for (y = 1; y <= resource; y++) {
			dp[x][y] = number[x] < (y / weight[x]) ? number[x] * value[x]
					: y / weight[x] * value[x];
		}
		// 动态规划
		for (x = 1; x < n; x++) {
			for (y = 1; y <= resource; y++) {
				if (y < weight[x]) {
					dp[x][y] = dp[x - 1][y];
				} else {
					int max = Math.min(number[x], y / weight[x]);
					dp[x][y] = dp[x - 1][y];
					for (int k = 1; k <= max; k++) {
						dp[x][y] = Math.max(dp[x][y], dp[x - 1][y - weight[x] * k] + value[x] * k);
					}
				}
			}
		}
		// 回溯
		x = n - 1;
		y = resource;
		while (x > 0) {
			int max = Math.min(number[x], y / weight[x]);
			for (int k = max; k > 0; k--) {
				if (dp[x][y] == dp[x - 1][y - weight[x] * k] + value[x] * k) {
					knapsnack[x] = k;
					y -= weight[x] * k;
					break;
				}
			}
			x--;
		}
		knapsnack[x] = dp[x][y] / value[x];
		// 最大价值总和
		int max = dp[n - 1][resource];
		System.out.println("Max Value: " + max);
		return knapsnack;
	}

	/******************** 排列问题 ********************/

	/**
	 * 数字的排列
	 */
	public static void permutation(int[] array, int index, int length,
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

	public static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	/**
	 * 字符串的排列
	 */
	public static void permutation(char[] chars, int index, ArrayList<String> result) {
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

	public static void swap(char[] array, int i, int j) {
		char temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
