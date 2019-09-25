/**
 * 字符串算法
 * 
 * @author zzy
 *
 */
public class Algorithm_String {

	public static void main(String[] args) {
		String string = "BBC ABCDAB ABCDABCDABDE";
		String substring = "ABCDABD";
		System.out.println(BruteForce(string, substring));
		System.out.println(KMP(string, substring));
		System.out.println(LCS("acbcbcef", "abcbced"));
	}

	/******************** 最长回文子串 ********************/
	
	/******************** 括号匹配 ********************/

	/******************** 最长公共子串 ********************/

	/**
	 * 最长公共子串（Longest Common Substring）
	 */
	public static String LCS(String s1, String s2) {
		dp = new int[s1.length()][s2.length()];
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
	public static int BruteForce(String string, String substring) {
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
	public static int KMP(String string, String substring) {
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

	/**
	 * Boyer-Moore算法
	 */
	public static void BM(String substring, String string) {

	}

	/******************** 辅助方法 ********************/

	/**
	 * 构建部分匹配表
	 */
	public static void buildPMT(String string) {
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

	public static int[] PMT; // 部分匹配表
	public static int[][] dp;

}
