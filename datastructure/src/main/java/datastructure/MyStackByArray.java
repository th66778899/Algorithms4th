package datastructure;

import java.util.Iterator;

//数组实现 栈
public class MyStackByArray<Item> implements Iterable<Item> {
    static final int CAPACITY = 1000;//数组栈的大小为1000
    int top;
    Item stack[];

    public MyStackByArray() {
        top = -1;
        stack = (Item[])new Object[1000];
    }
    //入栈
    public boolean push(Item val){
        if (top>=(CAPACITY-1)){
            System.out.println("Stack OverFlow");
            return false;
        }else {
            top++;
            stack[top] = val;
            return true;
        }
    }
    //出栈
    public Item pop(){
        if (top < 0){
            System.out.println("Stack UnderFlow");
            return null;
        }else {
            Item result = stack[top];
            top--;
            //不需要将出栈位置的值设为null，之后会自动将该位置的值进行覆盖
            return result;
        }
    }
    //获取栈顶元素
    public Item peek(){
        if (top < 0){
            System.out.println("Stack UnderFlow");
            return null;
        }else {
            Item result = stack[top];

            //不需要将出栈位置的值设为null，之后会自动将该位置的值进行覆盖
            return result;
        }
    }
    //判断栈是否为空
    public boolean isEmpty(){
        return top < 0;
        //上面语句为以下语句的简化
        /*if (top < 0 ){
            return true;
        }else {
            return false;
        }*/
    }

    /*
    * 迭代遍历实现
    * */
    public Iterator<Item> iterator() {
        return new MyStackByArrayIterator();
    }
    private class MyStackByArrayIterator implements Iterator<Item>{
        int i = top;
        public boolean hasNext() {
            return !(i < 0);
        }

        public Item next() {

            Item result = stack[i];
            i--;
            return result;
        }

        public void remove() {

        }
    }
    //测试数组栈
    public static void main(String[] args) {
        MyStackByArray<Integer> stack = new MyStackByArray<Integer>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        for (Integer integer : stack) {
            System.out.print(integer+ " ");
        }
        System.out.println();
        Object peek = stack.peek();
        System.out.println("peek = " + peek);
        Object pop = stack.pop();
        System.out.println("pop = " + pop);
        boolean empty = stack.isEmpty();
        System.out.println("empty = " + empty);
        for (Integer integer : stack) {
            System.out.print( " " + integer);
        }
    }
}
