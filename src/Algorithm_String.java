/**
 * 字符串算法
 * 
 * @author zzy
 *
 */
public class Algorithm_String {

	public static void main(String[] args) {
		String string = "ABCDCBA";
		System.out.println(longestPalindrome(string));
		System.out.println(Manacher(string));
	}

	/******************** 括号匹配 ********************/

	/******************** 回文串 ********************/

	/**
	 * 判断是否是回文串
	 */
	static boolean isPalindrome1(String string) {
		int i = 0, j = string.length() - 1;
		while (i < j) {
			if (string.charAt(i) != string.charAt(j)) {
				return false;
			}
			i++;
			j--;
		}
		return true;
	}

	/**
	 * 判断是否是回文串
	 */
	static boolean isPalindrome2(String string) {
		int i, j;
		if (string.length() % 2 == 0) {
			i = string.length() / 2 - 1;
			j = string.length() / 2;
		} else {
			i = j = string.length() / 2;
		}
		while (i >= 0 && j < string.length()) {
			if (string.charAt(i) != string.charAt(j)) {
				return false;
			}
			i--;
			j++;
		}
		return true;
	}

	/**
	 * 最长回文子串
	 */
	static int longestPalindrome(String string) {
		int count = 0, maxLength = 0;
		for (int i = 0; i < string.length(); i++) {
			for (int j = 0; i - j >= 0 && i + j < string.length(); j++) {
				if (string.charAt(i - j) != string.charAt(i + j)) break;
				count = j * 2 + 1;
			}
			maxLength = Math.max(maxLength, count);
			for (int j = 0; i - j >= 0 && i + j + 1 < string.length(); j++) {
				if (string.charAt(i - j) != string.charAt(i + j + 1)) break;
				count = j * 2 + 2;
			}
			maxLength = Math.max(maxLength, count);
		}
		return maxLength;
	}

	/**
	 * 最长回文子串（Manacher算法）
	 */
	static int Manacher(String string) {
		// 构造辅助字符串，例: abc123成为#a#b#c#1#2#3#2#1#
		char[] charArr = new char[string.length() * 2 + 1];
		for (int i = 0, j = 0; i < charArr.length; i++) {
			charArr[i] = (i % 2 == 0 ? '#' : string.charAt(j++));
		}
		int[] pArr = new int[charArr.length]; // 辅助回文长度数组
		int id = -1, right = -1, max = Integer.MIN_VALUE;
		for (int i = 0; i < charArr.length; i++) {
			if (right > i) { // 在右边界内
				pArr[i] = Math.min(pArr[id - (i - id)], right - i);
			} else {
				pArr[i] = 1;
			}
			while (0 <= i - pArr[i] && i + pArr[i] < charArr.length) { // 扩张
				if (charArr[i - pArr[i]] != charArr[i + pArr[i]]) break; // 停止扩张
				pArr[i]++;
			}
			if (i + pArr[i] > right) { // 当前位置的回文长度超过之前的最大长度
				id = i;
				right = i + pArr[i];
			}
			max = Math.max(max, pArr[i]); // 更新当前最大回文长度
		}
		return max - 1;
	}

	/******************** 最长公共子串 ********************/

	/**
	 * 最长公共子串（Longest Common Substring）
	 */
	static String LCS(String s1, String s2) {
		int[][] dp = new int[s1.length()][s2.length()];
		int max = 0, end = 0;
		for (int i = 0; i < s1.length(); i++)
			if (s1.charAt(i) == s2.charAt(0)) dp[i][0] = 1;
		for (int j = 0; j < s2.length(); j++)
			if (s2.charAt(j) == s1.charAt(0)) dp[0][j] = 1;
		for (int i = 1; i < s1.length(); i++) {
			for (int j = 1; j < s2.length(); j++) {
				if (s1.charAt(i) == s2.charAt(j)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = 0;
				}
				if (dp[i][j] > max) {
					max = dp[i][j];
					end = i + 1;
				}
			}
		}
		return s1.substring(end - max, end);
	}

	/******************** 子字符串的搜索 ********************/

	/**
	 * 暴力算法
	 */
	static int BruteForce(String string, String substring) {
		int i = 0, j = 0;
		while (i < string.length() && j < substring.length()) {
			if (string.charAt(i) == substring.charAt(j)) {
				i++;
				j++;
			} else {
				i = i - j + 1;
				j = 0;
			}
		}
		if (j == substring.length()) {
			return i - j;
		} else {
			return -1;
		}
	}

	/**
	 * 克努斯-莫里斯-普拉特算法 https://blog.csdn.net/v_july_v/article/details/7041827
	 */
	static int KMP(String string, String substring) {
		buildPMT(substring);
		int i = 0, j = 0;
		while (i < string.length() && j < substring.length()) {
			if (j == -1 || string.charAt(i) == substring.charAt(j)) {
				i++;
				j++;
			} else {
				j = PMT[j];
			}
		}
		if (j == substring.length()) {
			return i - j;
		} else {
			return -1;
		}
	}

	/******************** 辅助方法 ********************/

	/**
	 * 构建部分匹配表
	 */
	static void buildPMT(String string) {
		PMT = new int[string.length()];
		PMT[0] = -1;
		int i = 0, k = -1;
		while (i < string.length() - 1) {
			if (k == -1 || string.charAt(i) == string.charAt(k)) {
				i++;
				k++;
				PMT[i] = k;
			} else {
				k = PMT[k];
			}
		}
	}

	/******************** 辅助字段 ********************/

	static int[] PMT; // 部分匹配表

}
