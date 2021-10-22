package datastructure;

import com.sun.org.apache.regexp.internal.RE;

import java.util.Iterator;

//数组实现队列
public class MyQueueByArray<Item> implements Iterable<Item> {
    int front,rear,size;
    int CAPACITY=1000;
    Item queue[];



    public MyQueueByArray() {
        front = rear = size = 0;
        queue = (Item[])new Object[1000];
    }

    public Iterator<Item> iterator() {
        return new MyQueueByArrayIterator();
    }
    private class MyQueueByArrayIterator implements Iterator<Item>{
        int tempSize = size;
        int tempFront = front;
        public boolean hasNext() {
            return tempSize>0;
        }

        public Item next() {
            Item result = queue[tempFront];
            tempFront = (tempFront+1)%CAPACITY;
            tempSize--;

            return result;
        }


        public void remove() {

        }
    }
    //入队
    public void enqueue(Item var){
        if (size >= CAPACITY){
            System.out.println("Queue is already full");
            return;
        }else {
            queue[rear] = var;
            rear = (rear + 1)% CAPACITY;
            size++;

        }
    }
    //出队
    public Item dequeue(){
        if (size <= 0){
            System.out.println("Queue is empty");
            return null;
        }else {
            Item result = queue[front];
            front = (front+1)%CAPACITY;
            size--;
            return result;
        }
    }
    //拿到队列最前端的元素
    public Item peek(){
        if (size<=0){
            System.out.println("Queue is empty");
            return null;
        }else {
            return queue[front];
        }
    }
    //测试数组实现队列
    public static void main(String[] args) {
        MyQueueByArray<Integer> queue = new MyQueueByArray<Integer>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        for (Integer integer : queue) {
            System.out.print(integer + " ");
        }
        System.out.println();
        Integer dequeue = queue.dequeue();
        System.out.println("dequque = "+dequeue);
        Integer peek = queue.peek();
        System.out.println("peek="+peek);
        for (Integer integer : queue) {
            System.out.print(" " + integer);
         }
    }



}
