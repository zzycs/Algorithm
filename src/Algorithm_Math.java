import java.util.*;

/**
 * 数学相关算法
 * 
 * @author zzy
 *
 */
public class Algorithm_Math {

	public static void main(String[] args) {}

	/******************** 分班问题 ********************/

	/******************** 任务分配问题 ********************/

	/******************** 硬币问题 ********************/

	/**
	 * 最少硬币数量
	 */
	static int[] coin(int[] coins, int money) {
		int[] mins = new int[money + 1];
		int[] number = new int[coins.length];
		for (int x = 1; x <= money; x++) {
			for (int y = 0; y < coins.length; y++) {
				if (coins[y] <= x && mins[x] > mins[x - coins[y]] + 1) {
					
				}
			}
		}
		return number;
	}

	/******************** 背包问题 ********************/

	/**
	 * 0-1背包
	 */
	static int[] ZeroOnePack(int[] weight, int[] value, int resource, int n) {
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
	static int[] CompletePack(int[] weight, int[] value, int resource, int n) {
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
	static int[] MultiPack(int[] weight, int[] value, int[] number, int resource, int n) {
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

	/******************** 排列组合问题 ********************/

	/**
	 * 排列（基于交换）
	 */
	static void permutation(int index, int[] array, ArrayList<Integer> temp,
			ArrayList<ArrayList<Integer>> result) {
		if (index == array.length) {
			result.add(new ArrayList<>(temp));
		} else {
			for (int i = index; i < array.length; i++) {
				swap(array, index, i);
				temp.add(array[index]);
				permutation(index + 1, array, temp, result);
				temp.remove(temp.size() - 1);
				swap(array, index, i);
			}
		}
	}

	/**
	 * 排列
	 */
	static void permutation(int[] array, ArrayList<Integer> temp,
			ArrayList<ArrayList<Integer>> result) {
		if (temp.size() == array.length) {
			result.add(new ArrayList<>(temp));
		} else {
			for (int i = 0; i < array.length; i++) {
				if (temp.contains(array[i])) continue;
				temp.add(array[i]);
				permutation(array, temp, result);
				temp.remove(temp.size() - 1);
			}
		}
	}

	/**
	 * 无重复排列
	 */
	static void uniquePermutation(int[] array, ArrayList<Integer> temp,
			ArrayList<ArrayList<Integer>> result, boolean[] closed) {
		if (temp.size() == array.length) {
			result.add(new ArrayList<>(temp));
		} else {
			for (int i = 0; i < array.length; i++) {
				if (closed[i] || i > 0 && array[i] == array[i - 1] && !closed[i - 1]) continue;
				closed[i] = true;
				temp.add(array[i]);
				uniquePermutation(array, temp, result, closed);
				closed[i] = false;
				temp.remove(temp.size() - 1);
			}
		}
	}

	/**
	 * 子集组合
	 */
	static void combination(int index, int[] array, ArrayList<Integer> temp,
			ArrayList<ArrayList<Integer>> result) {
		result.add(new ArrayList<>(temp));
		for (int i = index; i < array.length; i++) {
			temp.add(array[i]);
			combination(i + 1, array, temp, result);
			temp.remove(temp.size() - 1);
		}
	}

	/**
	 * 无重复子集组合
	 */
	static void uniqueCombination(int index, int[] array, ArrayList<Integer> temp,
			ArrayList<ArrayList<Integer>> result) {
		result.add(new ArrayList<>(temp));
		for (int i = index; i < array.length; i++) {
			if (i > index && array[i] == array[i - 1]) continue; // 跳过重复
			temp.add(array[i]);
			uniqueCombination(i + 1, array, temp, result);
			temp.remove(temp.size() - 1);
		}
	}

	/**
	 * 和为sum的子集组合
	 */
	static void sumCombination(int index, int[] array, ArrayList<Integer> temp,
			ArrayList<ArrayList<Integer>> result, int sum) {
		if (sum < 0) {
			return;
		} else if (sum == 0) {
			result.add(new ArrayList<>(temp));
		} else {
			for (int i = index; i < array.length; i++) {
				temp.add(array[i]);
				sumCombination(i + 1, array, temp, result, sum - array[i]);
				temp.remove(temp.size() - 1);
			}
		}
	}

	/**
	 * 和为sum的无重复子集组合
	 */
	static void sumUniqueCombination(int index, int[] array, ArrayList<Integer> temp,
			ArrayList<ArrayList<Integer>> result, int sum) {
		if (sum < 0) {
			return;
		} else if (sum == 0) {
			result.add(new ArrayList<>(temp));
		} else {
			for (int i = index; i < array.length; i++) {
				if (i > index && array[i] == array[i - 1]) continue; // 跳过重复
				temp.add(array[i]);
				sumCombination(i + 1, array, temp, result, sum - array[i]);
				temp.remove(temp.size() - 1);
			}
		}
	}

	/******************** 数论 ********************/

	/**
	 * 最大公约数
	 */
	static long gcd(long a, long b) {
		if (b == 0) return a;
		else return gcd(b, a % b);
	}

	/**
	 * 最小公倍数
	 */
	static long lcm(long a, long b) {
		return a * b / gcd(a, b);
	}

	/******************** 辅助方法 ********************/

	static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	static void swap(char[] array, int i, int j) {
		char temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	static void printDoubleList(ArrayList<ArrayList<Integer>> list) {
		for (ArrayList<Integer> innerList : list) {
			for (int element : innerList) {
				System.out.print(element);
			}
			System.out.print(" ");
		}
		System.out.println();
	}
}
