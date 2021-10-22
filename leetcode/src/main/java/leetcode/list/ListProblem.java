package leetcode.list;


import java.util.List;

class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
public class ListProblem {
}
// 移除链表元素
class Solution209 {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null){
            return new ListNode();
        }

        ListNode dummyNode = new ListNode(-1,head);
        ListNode pre = dummyNode;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val == val){
                pre.next = cur.next;
            }else {
                pre = cur;
            }
            cur = cur.next;
        }
        return dummyNode.next;
    }
}
// 设计链表

class MyLinkedList {
    int val;
    ListNode dummyNode;

    int length = 0;
    public MyLinkedList() {
        dummyNode = new ListNode(-1);
    }

    public int get(int index) {
        if (index >= length){
            return -1;
        }
        ListNode cur = dummyNode.next;
        while (index-- > 0){
            cur = cur.next;
        }
        return cur.val;
    }

    public void addAtHead(int val) {
        ListNode head = dummyNode.next;
        ListNode newNode = new ListNode(val);
        length++;
        newNode.next = head;
        dummyNode.next = newNode;
    }

    public void addAtTail(int val) {

        ListNode tail = dummyNode.next;
        while (tail.next != null){
            tail = tail.next;
        }
        ListNode newNode = new ListNode(val);

        tail.next = newNode;
        length++;
    }

    public void addAtIndex(int index, int val) {
        if (index == length){
            addAtTail(val);
        }else if (index > length){

        }else if (index < length && index > 0){
            ListNode cur = dummyNode.next;
            ListNode pre = dummyNode;
            while (index-- > 0){
                pre = cur;
                cur = cur.next;
            }
            ListNode newNode = new ListNode(val);

            pre.next = newNode;
            newNode.next = cur;
            length++;


        }else {
            addAtHead(val);
        }
    }

    public void deleteAtIndex(int index) {
        if (index >= 0 && index < length){
            ListNode pre = dummyNode;
            ListNode cur = dummyNode.next;
            while (index -- > 0){
                pre = cur;
                cur = cur.next;
            }
            pre.next = cur.next;
            length--;
        }
    }
}
class Solution206 {
    public ListNode reverseList(ListNode head) {
        // 迭代法
       /*ListNode cur = head;
       ListNode pre = null;
       ListNode temp = null;
       while (cur != null){
           temp = cur.next;
           cur.next = pre;
           pre = cur;
           cur = temp;
       }
       return pre;*/

        // 递归
        return reverse(null,head);

    }
    private ListNode reverse(ListNode pre,ListNode cur){
        if (cur == null){
            return pre;
        }
        ListNode temp = cur.next;
        cur.next = pre;
        pre = cur;
        cur = temp;
        return reverse(pre,cur);
    }
    /*private ListNode reverse(ListNode prev, ListNode cur){
        if (cur == null){
            return prev;
        }
        ListNode temp = null;
        temp = cur.next;
        cur.next = prev;
        prev = cur;
        cur = temp;
        return reverse(prev,cur);
    }*/
}
class Solution24 {
    public ListNode swapPairs(ListNode head) {
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode cur = dummyNode;
        while (cur.next != null && cur.next.next != null){
            ListNode temp1 = cur.next;
            ListNode temp2 = cur.next.next.next;

            // 交换两节点顺序
            cur.next = cur.next.next;
            cur.next.next = temp1;
            cur.next.next.next = temp2;

            // cur 移动两位,为下一次交换做准备
            cur = cur.next.next;
        }
        return dummyNode.next;
    }
}
// 链表 双指针
class Solution19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyNode = new ListNode(-1,head);
        ListNode pre = dummyNode;
        ListNode slow = head;
        ListNode fast = head;
        while (n > 0){
            fast = fast.next;
            n--;
        }
        while (fast != null){
            pre = pre.next;
            fast = fast.next;
            slow = slow.next;
        }
        // pre指向删除节点的前一个节点
        // slow 指向要删除的节点
        pre.next = slow.next;
        return dummyNode.next;
    }

}
class Solution02_07 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int aLength = 0;
        int bLength = 0;
        ListNode curA = headA;
        ListNode curB = headB;
        if (headA == null || headB == null){
            return null;
        }
        while (curA != null){
            aLength++;
            curA = curA.next;
        }
        while (curB != null){
            bLength++;
            curB = curB.next;
        }
        int num = 0;
        curA = headA;
        curB = headB;
        if (aLength > bLength){
            num = aLength - bLength;
            while (num > 0){
                curA = curA.next;
                num--;
            }
            while (curA != null && curB != null){
                if (curA == curB){
                    return curA;
                }
                curA = curA.next;
                curB = curB.next;
            }
        }else if (bLength > aLength){
            num = bLength - aLength;
            while (num > 0){
                curB = curB.next;
                num--;
            }
            while (curA != null && curB != null){
                if (curA == curB){
                    return curA;
                }
                curA = curA.next;
                curB = curB.next;
            }
        }else {
            while (curA != null && curB != null){
                if (curA == curB){
                    return curA;
                }
                curA = curA.next;
                curB = curB.next;
            }
        }
        return null;

    }
}
// 双指针
class Solution142 {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast){
                // 有环存在
                fast = head;
                while (fast != slow){
                    fast = fast.next;
                    slow = slow.next;
                }
                return slow;
            }
        }

        return null;

    }
}
