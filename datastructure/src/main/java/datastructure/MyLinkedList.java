package datastructure;

import java.util.Iterator;


//链表-单链表
//时间复杂度：
//插入:O(n)
//删除:O(n)
//查找:O(n)
//更新:O(n)
public class MyLinkedList<Item> implements Iterable<Item> {


    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<Integer>();
        list.append(3);
        list.append(2);
        list.append(1);

        /*Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.print(iterator.next() + " ");
        }*/
        //下面代码为以上代码的简写形式  迭代遍历链表
        for (Integer i : list) {
            System.out.print(i + " ");
        }
        //链表删除
        System.out.println();
        System.out.println("链表删除值为3结点");
        list.delete(3);
        for (Integer i : list) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("链表再次删除值为3结点");
        list.delete(3);
        System.out.println("------------------");
        //链表查找
        int search = list.search(2);
        System.out.println("2在链表的位置,由0开始" +"\t"+ search);
        System.out.println("------------------");
        //链表插入
        list.insert(0,6);
        System.out.println("链表0号位置插入6");
        for (Integer i : list) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("链表1号位置插入-6");
        list.insert(1,-6);
        for (Integer i : list) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("链表最后插入7");
        list.insert(4,7);
        for (Integer i : list) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("链表插入位置不合法,插入9");
        list.insert(7,9);
        for (Integer i : list) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("------------------");
        //链表更新

        System.out.println("链表更新,-6更新为16");
        list.update(-6,16);
        for (Integer i : list) {
            System.out.print(i + " ");
        }


    }


    public Iterator<Item> iterator() {
        return new MyLinkedListIterator();
    }
    private class MyLinkedListIterator implements Iterator<Item>{

        private ListNode cur = head;
        public boolean hasNext() {
            return cur!=null;
        }

        public Item next() {
            Item item = cur.item;
            cur = cur.next;
            return item;
        }

        public void remove() {

        }
    }

    private class ListNode {
        Item item;
        ListNode next;


        public ListNode(Item item) {
            this.item = item;
        }
    }

    ListNode head;
    ListNode tail;
    int size;

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    //链表尾部添加新元素
    public void append(Item item) {
        ListNode newNode = new ListNode(item);
        //链表为空，newNode就是链表的最后一个元素
        if (tail == null) {
            tail = newNode;
            head = tail;
        }
        else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    //链表插入元素
    public void insert(int position, Item item) {
        if (position > size) return;
        ListNode newNode = new ListNode(item);
        //插入链表首部
        if (position == 0) {
            newNode.next = head;
            head = newNode;
            if (tail == null) {
                tail = newNode;
            }
            size++;
            //链表有3个元素 0 1 2 插入3号位置，就是插入链表最后
        } else if (position == size) {
            this.append(item);
            //插入链表中间位置
        } else {
            //指向插入位置前一个结点的引用
            ListNode prev = head;
            for (int i = 0; i < position - 1; i++) {
                prev = prev.next;
            }
            //next为插入位置后面的元素
            ListNode next = prev.next;
            newNode.next = next;
            prev.next = newNode;
            size++;
        }
    }

    //链表删除
    public void delete(Item item) {
        if (head != null && head.item == item) {
            head = head.next;
            size--;
            System.out.println("删除成功!");
            //删除完之后，链表没有其它元素，链表是空的
            if (size == 0) {
                tail = head;
            }
        } else {
            ListNode prev = head;
            ListNode cur = head;
            while (prev != null && cur != null) {
                if (cur.item == item) {
                    //要删除的元素位于链表末尾
                    if (cur == tail) {
                        tail = prev;
                    }
                    //要删除的元素位于链表中间
                    //删除元素的后面一个结点的地址赋给 删除元素前一个结点的引用
                    prev.next = cur.next;
                    size--;
                    System.out.println("删除成功!");
                    return;

                }
                //移动两个引用，分别指向删除结点的前一个结点和当前要删除的结点
                prev = cur;
                cur = cur.next;
            }
            System.out.println("删除失败！");


        }
    }
    //链表查找
    //返回该数在链表中的位置

    public int search(Item item) {
        ListNode cur = head;
        for (int index = 0;cur!=null;index++){
            if (cur.item ==item){
                return index;
            }
            cur =cur.next;
        }
        //返回值为-1说明链表中不存在此元素
        return -1;
    }
    //链表更新
    //返回该数在链表中的位置
    public int update(Item oldItem,Item newItem) {
        ListNode cur = head;
        for (int index = 0;cur!=null;index++){
            if (cur.item ==oldItem){
                cur.item = newItem;
                return index;
            }
            cur =cur.next;
        }
        //返回值为-1说明链表中不存在此元素,无法正常更新链表元素
        return -1;
    }
}
