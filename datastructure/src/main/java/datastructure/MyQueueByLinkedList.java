package datastructure;

import com.sun.org.apache.regexp.internal.RE;

import java.util.Iterator;

//链表实现队列
public class MyQueueByLinkedList<Item> implements Iterable<Item> {
    //实现迭代
    public Iterator<Item> iterator() {
        return new MyQueueByLinkedListIterator();
    }
    private class MyQueueByLinkedListIterator<Item> implements Iterator<Item>{
        QueueNode temp = front;
        public boolean hasNext() {
            return !(temp==null);
        }

        public Item next() {
            Item result = (Item) temp.val;
            temp = temp.next;
            return result;
        }

        public void remove() {

        }
    }

    //定义结点数据结构
    static class QueueNode<Item>{
        Item val;
        QueueNode next;

        public QueueNode() {
        }

        public QueueNode(Item val) {
            this.val = val;
        }
    }

    //指向队列头部指针
    QueueNode front;
    //指向队列尾部指针
    QueueNode rear;

    //入队
    public void enqueue(Item val){
        QueueNode<Item> newNode = new QueueNode<Item>(val);
        if (front==null){
            //如果队列为空
            this.front = newNode;
            this.rear = newNode;
            return;
        }else {
            //将新结点加到队列末尾
            this.rear.next = newNode;
            this.rear = newNode;
        }
    }
    //出队
    public Item dequeue(){
        if (this.front==null){
            System.out.println("Queue is empty");
            return null;
        }else {
            Item result = (Item) this.front.val;
            this.front = this.front.next;
            //如果队列已空，需要把两个指针全部设为null
            if (this.front==null){
                this.rear=null;
            }
            return result;

        }
    }
    //取出队列首部元素
    public Item peek(){
        if (this.front ==null){
            System.out.println("Queue is empty");
            return null;
        }else {
            Item result = (Item) this.front.val;
            return result;
        }
    }
    //isEmpty
    public boolean isEmpty(){
        return this.front == null;
    }
    //测试链表栈实现
    public static void main(String[] args) {
        MyQueueByLinkedList<Integer> queue = new MyQueueByLinkedList<Integer>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        for (Integer integer : queue) {
            System.out.print(integer+" ");
        }
        System.out.println();
        Integer peek = queue.peek();
        System.out.println("peek="+peek);
        boolean empty = queue.isEmpty();
        System.out.println("isEmpty=" + empty);
        Integer dequeue = queue.dequeue();
        System.out.println("dequeue="+dequeue);
        for (Integer integer : queue) {
            System.out.print(" "+integer);
        }
    }

}
