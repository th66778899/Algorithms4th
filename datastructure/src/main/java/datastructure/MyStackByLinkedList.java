package datastructure;

import java.util.Iterator;

//链表实现栈
public class MyStackByLinkedList<Item> implements Iterable<Item> {
    /*
    * 实现迭代
    * */
    public Iterator<Item> iterator() {
        return new MyStackByLinkedListIterator();
    }
    private class MyStackByLinkedListIterator implements Iterator<Item>{
        StackNode temp = top;
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
    //定义结点类型
    static class StackNode<Item>{
        Item val;
        StackNode next;

        public StackNode(Item val) {
            this.val = val;
        }
    }
    StackNode top;
    public MyStackByLinkedList(){
        top = null;
    }
    //入栈
    public void push(Item val){
       StackNode newNode = new StackNode(val);
        if (top==null){
            top = newNode;
        }else {
            StackNode temp = top;
            top = newNode;
            newNode.next = temp;
        }
    }
    //出栈
    public Item pop(){
        if (top==null){
            System.out.println("Stack is Empty");
            return null;
        }else {
            Item result = (Item) top.val;
            top = top.next;
            return result;
        }
    }
    //返回栈顶元素
    public Item peek(){
        if (top==null){
            System.out.println("Stack is Empty");
            return null;
        }else {
            Item result = (Item) top.val;

            return result;
        }
    }
    //判断栈是否为空
    public boolean isEmpty(){
        return top==null;
    }
    //测试链表实现栈
    public static void main(String[] args) {
        MyStackByLinkedList<Integer> stack = new MyStackByLinkedList<Integer>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        Integer peek = stack.peek();
        System.out.println("peek=" +peek);
        for (Integer integer : stack) {
            System.out.print(integer + " ");
        }
        System.out.println();
        Integer pop = stack.pop();
        System.out.println("pop=" +pop);
        boolean empty = stack.isEmpty();
        System.out.println("isEmpty=" + empty);
        for (Integer integer : stack) {
            System.out.print(" " + integer);
        }
    }
}
