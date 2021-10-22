package datastructure;

import java.util.ArrayList;

// 实现自己的hashMap
public class MyHashMap {
    static class HashNode<String,Integer>{
        String key;
        Integer value;
        HashNode<String,Integer> next;
        public HashNode(String key,Integer value){
            this.key = key;
            this.value = value;
        }
    }
    private ArrayList<HashNode<String,Integer>> bucketArray;// hashMap 所依赖的数组
    private int numBuckets; // 数组长度
    private int size;// hashMap个键值对个数
    public MyHashMap(){
        bucketArray = new ArrayList<HashNode<String, Integer>>();
        numBuckets = 10;
        size = 0;
        for (int i = 0; i < numBuckets; i++) {
            bucketArray.add(null);
        }
    }
    // 拿到一个key 计算key对应的bucketIndex
    private int getNumBucketsIndex(String key){
        int hashCode = key.hashCode();
        int index = hashCode % numBuckets;
        return index;
    }
    // 添加元素
    public void add(String key,Integer value){
        int numBucketsIndex = getNumBucketsIndex(key);
        HashNode<String, Integer> head = bucketArray.get(numBucketsIndex);
        while (head!=null){
            // 遍历这个bucket 看看该key是否已经存在
            // 如果key已经存在，更新value
            if (head.key.equals(key)){
                head.value = value;
                return;
            }
            head = head.next;
        }
        // 如果key不存在
        head = bucketArray.get(numBucketsIndex);// 对应index上的链表的头结点
        HashNode<String, Integer> newNode = new HashNode<String, Integer>(key, value);
        newNode.next = head;
        bucketArray.set(numBucketsIndex,newNode);// 将bucket链表的头结点设为新结点
        size++;
        // 考虑hashMap的扩容
        if ((1.0 * size) / numBuckets >= 0.7){
            generateBiggerArray();
        }
    }
    // hashMap数组的扩容
    private void generateBiggerArray(){
        ArrayList<HashNode<String,Integer>> temp = bucketArray;// 存放就hashMap内的元素
        bucketArray = new ArrayList<HashNode<String, Integer>>();
        numBuckets = 2 * numBuckets;
        // 新的bucketArray的初始化
        for (int i = 0; i < numBuckets; i++) {
            bucketArray.add(null);
        }
        size = 0;
        for (HashNode<String, Integer> hashNode : temp) {
            while (hashNode!=null){
                add(hashNode.key,hashNode.value);
                hashNode = hashNode.next;
            }
        }

    }
    // 获取hashMap中的元素
    public Integer get(String key){
        int numBucketsIndex = getNumBucketsIndex(key);
        HashNode<String, Integer> head = bucketArray.get(numBucketsIndex);
        while (head!=null){
            if (head.key.equals(key)){
                return head.value;
            }
            head = head.next;
        }
        // 没有在hashMap中找到 这个key对应的值
        return null;
    }
    // hashMap删除key
    public Integer remove(String key){
        int numBucketsIndex = getNumBucketsIndex(key);
        HashNode<String, Integer> head = bucketArray.get(numBucketsIndex);
        HashNode<String,Integer> pre = null;// 链表删除结点 双指针
        while (head!=null){
            if (head.key.equals(key)) break;// 链表中找到了这个key 直接return
            pre = head;// pre 就是要出删除结点的先驱结点
            head = head.next;
        }
        if (head == null){
            // 在这个key该存在的位置没有找到该key
            return null;// 删除失败 该key - value 不存在
        }
        // 该key存在于hashMap中 有两种情况
        // 1.该key是链表头结点
        // 2.该key不是链表头结点
        if(pre != null){
            // 2.不是链表头结点
            pre.next = head.next;
        }else {
            // 1.该结点是链表头结点
            bucketArray.set(numBucketsIndex,head.next);// 将链表头结点设置其下一个结点

        }
        size--;
        return head.value;// 通过head保存了链表头结点的信息

    }

    /**
     * hashMap 内元素个数
     * @return int
     */
    public int size(){
        return size;
    }

    /**
     * hashMap是否为空
     * @return boolean
     */
    public boolean isEmpty(){
        return size == 0;
    }

}
