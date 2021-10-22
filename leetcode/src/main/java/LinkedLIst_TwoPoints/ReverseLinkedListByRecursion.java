package LinkedLIst_TwoPoints;

import java.util.List;

//使用递归逆转链表
public class ReverseLinkedListByRecursion {
    static class ListNode{
        int val;
        ListNode next = null;

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode() {
        }
    }
    public static ListNode reverseLinkedList(ListNode head){
        if (head==null||head.next==null){
            return head;
        }
        ListNode reversed_head = reverseLinkedList(head.next);
        head.next.next = head;
        head.next = null;
        return reversed_head;
    }
    //测试逆转链表
    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = null;
        ListNode head = listNode1;
        while (head!=null){
            System.out.println(head.val + " ");
            head = head.next;
        }
        ListNode newHead = listNode1;
        ListNode listNode = reverseLinkedList(newHead);

        while (listNode!=null){
            System.out.println(listNode.val + " ");
            listNode = listNode.next;
        }

    }


}
