package LinkedLIst_TwoPoints;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class LinkedList {

}
 class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }

     public ListNode(int val, ListNode next) {
         this.val = val;
         this.next = next;
     }

     public ListNode() {
     }
 }
//leetCode237 简单
class Solution237 {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
//leetcode203 移除链表元素


class Solution203 {

    @Test
    void testList(){
        ArrayList<String> list = new ArrayList<String>();
        list.add("北京");
        list.add("上海");
        list.add("苏州");
        System.out.println(list);
        System.out.println("包含苏州的"+list.contains("苏州"));
        list.remove("上海");
        System.out.println("新的list"+list);
        list.add("苏州");
        System.out.println("新的list"+list);
    }

    public ListNode removeElements(ListNode head, int val) {
        //方法3.递归
        if (head==null){
            return null;
        }
        head.next = removeElements(head.next,val);
        if (head.val==val){
            return head.next;
        }else {
            return head;
        }





        //方法2.建立虚拟头结点
        /*ListNode tempNode = new ListNode(val - 1);
        tempNode.next = head;
        ListNode temp = tempNode;
        while (temp.next!=null){
            if (temp.next.val==val){
                temp.next = temp.next.next;
            }else {
                temp = temp.next;
            }
        }
        return tempNode.next;*/



        //方法1.单独处理头结点
        /*ListNode newHead = head;
        //如果头结点的值是要删除的值
        while  (newHead!=null&&newHead.val==val){
                newHead = newHead.next;
        }
        if (newHead==null){
            return newHead;
        }

        ListNode temp = newHead;
        while (temp.next!=null){
            if (temp.next.val==val){
                temp.next = temp.next.next;

            }
            else {
                temp = temp.next;
            }
        }
        return newHead;*/
    }

    public static void main(String[] args) {
    }
}
//简单 反转链表(Ⅰ)
class Solution206{
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode temp = null;
        while (cur!=null){
            temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;

        }
        return pre;

    }

    public static void main(String[] args) {
        ListNode listNode3 = new ListNode(3);
        ListNode listNode2 = new ListNode(2,listNode3);
        ListNode listNode1 = new ListNode(1,listNode2);
        ListNode node = new ListNode();
        node = listNode1;
        while (node!=null){
            System.out.println(node.val);
            node = node.next;
        }
        ListNode newNode = new ListNode();
        newNode = listNode1;
        Solution206 solution206 = new Solution206();
        ListNode listNode = solution206.reverseList(newNode);
        while (listNode!=null){
            System.out.println(listNode.val);
            listNode = listNode.next;
        }


    }
}
//中等 反转链表Ⅱ
class Solution92{
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode guard;//指向要插入位置的前一个结点
        ListNode point;//指向要插入的位置
        ListNode temp;//要移动的结点
        ListNode temp1;//guard的后一个结点
        //定义一个虚拟的头结点
        ListNode newHead = new ListNode(0);
        guard = newHead;
        newHead.next = head;
        int i = left;
        int j = right;
        while (i>1){
            guard = guard.next;//移动guard结点到指定位置
            i--;
        }
        point = guard.next;//point指针指向对应的结点


        while (right-left>0){
            //移动结点位置
            right--;//移动次数根据 left right确定
            temp = point.next;//temp指向point结点的后一个结点
            point.next = point.next.next;
            temp1 = guard.next;//保存guard结点的后一个结点
            guard.next = temp;//guard指向temp 结点

            temp.next = temp1;//temp结点指针域指向point
        }
        return newHead.next;//新链表的头结点

        //最优解
        /*// 定义一个dummyHead, 方便处理
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        // 初始化指针
        ListNode g = dummyHead;
        ListNode p = dummyHead.next;

        // 将指针移到相应的位置
        for(int step = 0; step < m - 1; step++) {
            g = g.next; p = p.next;
        }

        // 头插法插入节点
        for (int i = 0; i < n - m; i++) {
            ListNode removed = p.next;
            p.next = p.next.next;
            removed.next = g.next;
            g.next = removed;
        }
        return dummyHead.next;*/


    }


    public static void main(String[] args) {
        Solution92 solution92 = new Solution92();
        ListNode listNode5 = new ListNode(5);
        ListNode listNode4 = new ListNode(4,listNode5);
        ListNode listNode3 = new ListNode(3,listNode4);
        ListNode listNode2 = new ListNode(2,listNode3);
        ListNode listNode1 = new ListNode(1,listNode2);
        ListNode newNode = listNode1;
        while (newNode!=null){
            System.out.println(newNode.val);
            newNode = newNode.next;
        }

        ListNode listNode = solution92.reverseBetween(listNode1, 2, 4);
        while (listNode!=null){
            System.out.println(listNode.val);
            listNode = listNode.next;
        }


    }
}
//141.判断链表是否有环  简单
class Solution141{
    public boolean hasCycle(ListNode head) {
        //利用双指针-快慢指针 如果有环，在两个指针都指向null之前快指针会追上慢指针
        //只有一个结点与只有两个结点的情况会出现bug，要么对这两种情况另外考虑 ， 要么 先移动快指针 判断 移动慢指针
        ListNode fast = head;
        ListNode slow = head;
        while (fast!=null){
            //慢指针每次移动一步，快指针每次移动两步
            fast = fast.next;
            if (fast!=null){
                fast = fast.next;
            }

            if (fast==slow){
                //两指针相遇，说明链表有环
                return true;
            }
            slow = slow.next;
        }
        return false;

    }

    /**
     *
     *
     * @param head
     * @author aa
     *
     * @return
     */
    public boolean test(ListNode head){
        while (head!=null||head.next!=null){
            System.out.println("可以");
        }
        return false;
    }


    public static void main(String[] args) {
        /*Solution141 solution141 = new Solution141();
        ListNode listNode5 = new ListNode(5);
        ListNode listNode4 = new ListNode(4,listNode5);
        ListNode listNode3 = new ListNode(3,listNode4);
        ListNode listNode2 = new ListNode(2,listNode3);
        ListNode listNode1 = new ListNode(1,listNode2);
        //listNode5.next = listNode2;
        //boolean b = solution141.hasCycle(listNode5);
        solution141.test(listNode5);*/
       // System.out.println(b);
        int a = 1;

        a=a++;
        System.out.println(a);


    }

}
//面试题 02.04
class Solution0204{
    public static void main(String[] args) {
        int a = 6;
        switch (a){
            case 6:{}break;
            case 7:{
                System.out.println(a);
                break;}
        }
    }

}

