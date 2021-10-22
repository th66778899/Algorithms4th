package datastructure;

// 优先级队列
public class PriorityQueue {
    static class Node{
        int value;// 值
        int priority;// 优先级
        Node next;// 指针 指向下一个节点
        public Node(int value,int priority){
            this.value = value;
            this.priority = priority;
        }
    }
    Node head = null;

    /**
     * 优先级队列的push操作
     * @param value
     * @param priority
     */
    public void push(int value,int priority){
        if (head == null){
            head = new Node(value,priority);
            return;
        }
        Node cur = head;
        Node newNode = new Node(value, priority);
        if (head.priority < priority){
            // 新结点优先级高于头结点优先级
            newNode.next = head;
            this.head = newNode;
        }else {
            while (cur.next != null && cur.next.priority > priority){
                // 链表不为空 且 链表当前节点优先级 高于 新插入节点优先级
                cur = cur.next; // 遍历链表 找到插入的位置
            }
            newNode.next = cur.next;
            cur.next = newNode;
        }
    }

    /**
     * 查看头结点
     * @return
     */
    public Node peek(){
        return head;
    }

    /**
     * 出优先级队列
     * @return
     */
    public Node pop(){
        if (head == null) return null;
        Node temp = head;
        head = head.next;
        return temp;
    }

    /**
     * 判断优先级队列的是否为空
     * @return
     */
    public boolean isEmpty(){
        return head == null;
    }

}
