package com.ccq.leetcode;

/**
 * @Description: 两数相加
 * @Author: ChengChuanQiang
 * @Date: 2019/12/15 23:23
 */
public class LeetCode2 {

    public static void main(String[] args) {
        ListNode node11 = new ListNode(2);
        ListNode node12 = new ListNode(4);
        ListNode node13 = new ListNode(3);

        node11.next = node12;
        node12.next = node13;

        ListNode node21 = new ListNode(5);
        ListNode node22 = new ListNode(6);
        ListNode node23 = new ListNode(4);

        node21.next = node22;
        node22.next = node23;

        LeetCode2 leetCode2 = new LeetCode2();
        leetCode2.print(node11);
        leetCode2.print(node21);

        ListNode res = leetCode2.addTwoNumbers(node11, node21);

        leetCode2.print(res);
    }

    public void print(ListNode listNode) {
        StringBuilder res = new StringBuilder();
        while (null != listNode) {
            res.append(listNode.val).append("->");
            listNode = listNode.next;
        }

        System.out.println(res.substring(0, res.length() - 2));
    }


    /**
     * 题解：时间复杂度 O(max(len(l1), len(l2))) 空间复杂度：O(max(len(l1), len(l2)))
     * 模拟整数相加，可以理解为两个整数的竖式相加，使用一个temp记录是否进位，最后两个链表都遍历完成后，还是要考虑temp的值
     * 链表处理技巧：设置一个空的头节点，然后所有计算的结果放在空的头节点之后，最后返回头节点的下一个链表即可
     *
     * @param l1 链表1
     * @param l2 链表2
     * @return 求和
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode res = new ListNode(0);
        ListNode head = res;

        // 进位的值
        int temp = 0;
        while (null != l1 && null != l2) {

            ListNode curr = new ListNode(0);
            int sum = l1.val + l2.val + temp;
            if (sum >= 10) {
                curr.val = sum % 10;
                temp = 1;
            } else {
                curr.val = sum;
                temp = 0;
            }

            l1 = l1.next;
            l2 = l2.next;

            res.next = curr;
            res = res.next;
        }

        while (null != l1) {
            ListNode curr = new ListNode(0);
            int sum = l1.val + temp;
            if (sum >= 10) {
                curr.val = sum % 10;
                temp = 1;
            } else {
                curr.val = sum;
                temp = 0;
            }
            l1 = l1.next;

            res.next = curr;
            res = res.next;
        }

        while (null != l2) {
            ListNode curr = new ListNode(0);
            int sum = l2.val + temp;
            if (sum >= 10) {
                curr.val = sum % 10;
                temp = 1;
            } else {
                curr.val = sum;
                temp = 0;
            }
            l2 = l2.next;
            res.next = curr;
            res = res.next;
        }

        if (temp != 0) {
            ListNode curr = new ListNode(0);
            curr.val = temp;
            res.next = curr;
        }

        return head.next;
    }

}
