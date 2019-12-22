package com.ccq.leetcode;

/**
 * @Description: 最长回文子串
 * @Author: ChengChuanQiang
 * @Date: 2019/12/22 20:52
 */
public class LeetCode5 {

    public static void main(String[] args) {
        LeetCode5 leetCode5 = new LeetCode5();

        System.out.println(leetCode5.longestPalindrome("babad"));
        System.out.println(leetCode5.longestPalindrome("cbbd"));
        System.out.println(leetCode5.longestPalindrome("bb"));
        System.out.println(leetCode5.longestPalindrome("abcda"));
        System.out.println(leetCode5.longestPalindrome("ccc"));

        System.out.println(leetCode5.longestPalindrome2("babad"));
        System.out.println(leetCode5.longestPalindrome2("cbbd"));
        System.out.println(leetCode5.longestPalindrome2("bb"));
        System.out.println(leetCode5.longestPalindrome2("abcda"));
        System.out.println(leetCode5.longestPalindrome2("ccc"));


    }

    /**
     * 题解：时间复杂度：O(N^2) 空间复杂度 O(1)
     * **********中心扩展法************
     * (1) 一共有 n+n-1 个中心
     * (2) 遍历字符串，初始化最长回文串为第一个字符，然后从1开始遍历
     * (3) 以当前字符为中心
     * (4) 以当前字符前的空格为中心
     *
     * @param s 字符串
     * @return 最长回文串
     */
    public String longestPalindrome(String s) {

        if (s == null || s.length() == 0) {
            return s;
        }

        int len = 1;
        int l = 0;
        int r = 0;
        for (int i = 1; i < s.length(); i++) {

            int j;
            int k;
            // 以当前字符为中心
            for (j = i - 1, k = i + 1; j >= 0 && k < s.length(); j--, k++) {
                if (s.charAt(j) != s.charAt(k)) {
                    break;
                }
            }
            if (k - j - 1 > len) {
                len = k - j - 1;
                l = j + 1;
                r = k - 1;
            }

            // 以当前字符前的空格为中心
            for (j = i - 1, k = i; j >= 0 && k < s.length(); j--, k++) {
                if (s.charAt(j) != s.charAt(k)) {
                    break;
                }
            }
            if (k - j - 1 > len) {
                len = k - j - 1;
                l = j + 1;
                r = k - 1;
            }
        }

        return s.substring(l, r + 1);

    }

    /**
     * 题解：时间复杂度：O(N^2) 空间复杂度 O(1)
     * **********动态规划************
     * 1、isPalindrome[i][j] 表示从字符串 s[i -> j]是否为回文串
     * 2、初始化 一个字符必是回文串，两个字符回文串初始化
     * 3、枚举字符串的长度，从0开始，遍历所有的字符串是否为回文串
     *
     * @param s 字符串
     * @return 最长回文串
     */
    public String longestPalindrome2(String s) {

        if (s == null || s.length() == 0) {
            return s;
        }

        int l = 0;
        int r = 0;
        int len = 1;
        boolean[][] isPalindrome = new boolean[s.length()][s.length()];

        // 初始化
        for (int i = 0; i < s.length(); i++) {
            isPalindrome[i][i] = true;
        }
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                isPalindrome[i][i + 1] = true;
                if (len < 2) {
                    len = 2;
                    l = i;
                    r = i + 1;
                }
            }
        }


        // 字符串长度
        for (int i = 3; i <= s.length(); i++) {
            for (int j = 0; j <= s.length() - i; j++) {
                if (isPalindrome[j + 1][j + i - 2] && s.charAt(j) == s.charAt(j + i - 1)) {
                    isPalindrome[j][j + i - 1] = true;

                    if (i > len) {
                        len = i;
                        l = j;
                        r = j + i - 1;
                    }
                }
            }
        }

        return s.substring(l, r + 1);
    }

}
