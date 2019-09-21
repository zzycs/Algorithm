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
	}

	/******************** 最长回文子串 ********************/

	/******************** 最长公共子串 ********************/

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

}
