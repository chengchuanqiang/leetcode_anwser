package com.ccq.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description: 无重复字符的最长子串
 * @Author: ChengChuanQiang
 * @Date: 2019/12/16 0:20
 */
public class LeetCode3 {

    public static void main(String[] args) {

        LeetCode3 leetCode3 = new LeetCode3();
        System.out.println(leetCode3.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(leetCode3.lengthOfLongestSubstring("bbbbb"));
        System.out.println(leetCode3.lengthOfLongestSubstring("pwwkew"));
        System.out.println(leetCode3.lengthOfLongestSubstring("aab"));
    }

    /**
     * 题解：时间复杂度：O(N) 空间复杂度：O(N)
     * <p>
     * 滑动窗口，使用一个set存储当前窗口中所有的值，然后先将 r 指针一直向后走，直到当前的字符在set中存在
     * 这样，r-l 的值即为当前情况窗口的最大值，然后移动 l 指针，直到不包含当前字符即可
     * 然后依次循环以上两个条件，直到 r 遍历完所有的字符
     *
     * @param s 字符串
     * @return 最长不重复子序列
     */
    public int lengthOfLongestSubstring(String s) {

        if (null == s || s.length() == 0) {
            return 0;
        }

        Set<String> result = new HashSet<>();

        Set<Character> set = new HashSet<>();
        int l = 0;
        int r = 0;
        int max = 0;
        while (r < s.length()) {
            while (r < s.length() && !set.contains(s.charAt(r))) {
                set.add(s.charAt(r));
                r++;
            }

            if (r - l > max) {
                max = r - l;
                result = new HashSet<>();
                result.add(s.substring(l, r));
            } else if (r - l == max) {
                result.add(s.substring(l, r));
            }

            while (l <= r && r < s.length() && set.contains(s.charAt(r))) {
                set.remove(s.charAt(l));
                l++;
            }
        }

        // 输出所有的字串
        System.out.println(result.toString());

        return max;
    }

}
