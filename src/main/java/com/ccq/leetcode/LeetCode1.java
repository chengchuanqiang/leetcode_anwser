package com.ccq.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 两数之和
 * @Author: ChengChuanQiang
 * @Date: 2019/12/15 22:57
 */
class LeetCode1 {

    public static void main(String[] args) {
        LeetCode1 leetCode1 = new LeetCode1();

        int[] nums = {2, 7, 11, 15};
        int target = 9;

        int[] res = leetCode1.twoSum(nums, target);
        System.out.println(res[0] + ", " + res[1]);
    }

    /**
     * 题解：时间复杂度：O(n) 空间复杂度 O(n)
     * 使用哈希表的思想，先把需要寻找的字符串存到 哈希表中，也就是 map中，
     * 然后遍历数组，只要数组中的数字存在于哈希表中，即找到对应的值
     *
     * @param nums   输入数组
     * @param target 目标数值和
     * @return 相加和为target的两个数字下标
     */
    public int[] twoSum(int[] nums, int target) {

        int[] res = new int[2];

        Map<Integer, Integer> tempMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            tempMap.put(target - nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            if (tempMap.containsKey(nums[i]) && tempMap.get(nums[i]) != i) {
                res[0] = i;
                res[1] = tempMap.get(nums[i]);
                break;
            }
        }

        return res;
    }

}
