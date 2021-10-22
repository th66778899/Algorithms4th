package datastructure;

import java.util.Arrays;
import java.util.NoSuchElementException;

// 最大堆 数组实现
public class MaxHeap {
    private int capacity;// 堆容量
    private int size = 0;// 堆内元素个数
    private int[] array;
    public MaxHeap(int capacity){
        this.capacity = capacity;
        array = new int[capacity];
    }
    // 辅助函数

    // 获得左孩子结点
    private int getLeftChildIndex(int parentIndex){
        return parentIndex * 2 + 1;
    }
    // 获得右孩子结点
    private int getRightChildIndex(int parentIndex){
        return parentIndex * 2 + 2;
    }
    // 获得父结点
    private int getParentIndex(int childIndex){
        return (childIndex - 1) / 2;
    }
    // 判断有没有左孩子结点
    private boolean hasLeftChild(int index){
        return getLeftChildIndex(index) < size;
    }
    // 判断有没有右孩子结点
    private boolean hasRightChild(int index){
        return getRightChildIndex(index) < size;
    }
    // 判断有没有父结点
    private boolean hasParent(int index){
        return getParentIndex(index) >= 0;
    }
    // 获得左孩子结点的值
    private int leftChild(int parentIndex){
        return array[getLeftChildIndex(parentIndex)];
    }
    // 获得右孩子结点的值
    private int rightChild(int parentIndex){
        return array[getRightChildIndex(parentIndex)];
    }
    // 获得父节点的值
    private int parent(int childIndex){
        return array[getParentIndex(childIndex)];
    }

    // 将元素插入到堆中
    public void insert(int item){
        // 扩容
        if (size == capacity){
            array = Arrays.copyOf(array,capacity * 2);
            capacity = 2 * capacity;
        }
        array[size] = item;
        heapifyUp();
        size++;

    }
    // 将堆进行处理，得到符合要求的堆
    private void heapifyUp(){
        int index = size - 1;
        while (hasParent(index) && parent(index) < array[index]){
            int temp = array[index];
           array[index]= array[getParentIndex(index)];
            array[getParentIndex(index)] = temp;
            index = getParentIndex(index);
        }
    }
    // 取出堆顶元素
    public int poll(){
        if (size == 0){
            throw new NoSuchElementException();
        }
        int element = array[0];
        array[0] = array[size - 1];
        size--;
        heapifyDown();
        return element;
    }
    // 取出栈顶元素后 将堆整理为要求的状态
    public void heapifyDown(){
        int index = 0;
        while (hasLeftChild(index)){
            int largerChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) && rightChild(index) > leftChild(index)){
                largerChildIndex = getRightChildIndex(index);
            }
            if (array[index] < array[largerChildIndex]){
                int temp = array[index];
                array[index] = array[largerChildIndex];
                array[largerChildIndex] = temp;
            }else break;
            index = largerChildIndex;
        }

    }
    public int peek(){
        if (size == 0){
            throw new NoSuchElementException();
        }
        return array[0];
    }


}
