package heap;

import java.util.*;

// leetcode 215 中等
public class Solution215 {
    // 使用 Sorting O(nlogn)
    // max Heap O(n + klogn)
    // min Heap O(nlogk)
    // Online vs Offline Algorithm
    // Online Algorithm(using Heap) 针对一组流数据，没有固定长度，可以随时根据需求scalable
    // Offline Algorithm(using Sorting) 针对一组固定数据，每次scale后要重新计算

    /**
     * Top k 问题 第k个大的数 利用 k个最小堆 可以维持该元素为该堆的第k个小的元素，之后遍历整个数组
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        // 找第K个小的元素
        /*PriorityQueue<Integer> heap = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        for (int num : nums) {
            // heap大小为k个
            if (heap.size() < k || heap.peek() >= num){
                // 当heap达不到k个时 或者 堆顶元素 < num 入堆
                // 维护一个最小堆,不能排除潜在答案,要将大的 获得 相等的 数值加入进来
                heap.offer(num);
            }
            // 堆超过k个时，取出堆顶元素 ，将堆维持在k个
            if ( k < heap.size()){
                heap.poll();
            }
        }*/
        // priorityQueue 默认实现小根堆 要实现大根堆参考上面的代码
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>();
        for (int num : nums) {
            // heap大小为k个
            if (heap.size() < k || heap.peek() <= num){
                // 当heap达不到k个时 或者 堆顶元素 < num 入堆
                // 维护一个最小堆,不能排除潜在答案,要将大的 获得 相等的 数值加入进来
                heap.offer(num);
            }
            // 堆超过k个时，取出堆顶元素 ，将堆维持在k个
            if ( k < heap.size()){
                heap.poll();
            }
        }

        return heap.peek();
    }

    public static void main(String[] args) {
        Solution215 solution215 = new Solution215();
        int kthLargest = solution215.findKthLargest(new int[]{3,2,3,1,2,4,5,5,6}, 4);
        System.out.println(kthLargest);


    }

}
// leetcode 23 困难
// Linear Scan O(k)
// Simple Sorting O(klogk)
// Min Heap O(logk)
class Solution23{
    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
    //
    public ListNode mergeKLists(ListNode[] lists) {
        // 自定义类的优先级队列要自己实现 comparator
        PriorityQueue<ListNode> heap = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        // 定义一个 dummy head 用来存放结果的链表
        // 定义一个虚拟头结点可以减少边界情况的考虑
        ListNode res = new ListNode(0);
        ListNode cur = res;
        // 将k个链表的头结点加入到heap中
        for (ListNode list : lists) {
            if (list!=null){
                heap.offer(list);
            }

        }
        // 堆不为空，取出堆顶元素 ， 如果堆顶元素 后面还有结点，将后面结点入堆，重复
        while (!heap.isEmpty()){
            // 创建新结点 取出堆顶元素 作为结果的下一个结点
            ListNode newNode = new ListNode(heap.peek().val);
            cur.next = newNode;
            // 如果堆顶元素还有后继结点，将后继结点入堆
            if (heap.peek().next!=null){

                heap.offer(heap.poll().next);
            }else {
                // 如果没有后继结点，直接poll堆顶元素
                heap.poll();
            }
            cur = newNode;
        }
        // 返回虚拟头结点的下一个结点
        return res.next;
    }

    public static void main(String[] args) {
        Solution23 solution23 = new Solution23();
        ListNode listNode1 = new ListNode(1);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        listNode1.next = listNode4;
        listNode4.next = listNode5;
        ListNode listNode1x = new ListNode(1);
        ListNode listNode3x = new ListNode(3);
        ListNode listNode4x = new ListNode(4);
        listNode1x.next = listNode3x;
        listNode3x.next = listNode4x;
        ListNode listNode2 = new ListNode(2);
        ListNode listNode6 = new ListNode(6);
        listNode2.next = listNode6;
        ListNode listNode = solution23.mergeKLists(new ListNode[]{});
        while (listNode!=null){
            System.out.print(listNode.val + " ");
            listNode = listNode.next;
        }
    }

}
// leetcode 347 中等
class Solution347{
    public int[] topKFrequent(int[] nums, int k) {
        int[] res = new int[k];
        // 建立一个哈希表来记录数字出现频率
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            // 将数组元素出现频率用哈希表来记录
            // O(n) 时间复杂度
            if (map.containsKey(num)){
                // 如果哈希表内已存在该元素，更新出现频率
                map.replace(num,map.get(num) + 1);
            }else {
                // 如果哈希表内不存在该元素，将该元素加入到哈希表，并将频率设为1
                map.put(num,1);
            }
        }
        // 利用小根堆来将出现频率进行排序
        // 默认是小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                // 小根堆
                return map.get(o1) - map.get(o2);
            }
        });
        Set<Integer> integers = map.keySet();
        
        


        
        for (Integer integer : integers) {
            if (heap.size() < k){
                heap.offer(integer);
            }else if (map.get(integer) >= map.get(heap.peek())){
                heap.offer(integer);
                heap.poll();
            }
        }
        /*for (Integer value : values) {
            // 将出现频率全部加入到堆中
            // 小根堆 保留k个元素 ，k个元素即为题目所要求的频率前K高的元素
            heap.offer(value);
        }
        while (heap.size() > k){
            heap.poll();
        }
        */
        int i = 0;
        while (heap.size() > 0){
            res[i] = heap.poll();
            i++;
        }

        return res;
    }

    public static void main(String[] args) {
        Solution347 solution347 = new Solution347();
        int[] ints = solution347.topKFrequent(new int[]{4,1,-1,2,-1,2,3}, 2);
        for (int anInt : ints) {
            System.out.print(anInt + " ");
        }
    }

}
// leetcode 295 困难
class Solution295{
    int size = 0;
    PriorityQueue<Integer> maxHeap;
    PriorityQueue<Integer> minHeap;
    /** initialize your data structure here. */
    public Solution295() {
        maxHeap = new PriorityQueue<>(new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        minHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {
        size++;
        // O(logn)
        // 将插入的数据流分为前后两端
        // 中位数只会涉及到前半段的最后一个元素 以及后半段的第一个元素
        // 可以使用大根堆来存储前半段元素 使用小根堆来存储后半段元素
        // 如果有奇数个元素 不妨让前半段大根堆多一个元素 中位数就是大根堆的堆顶
        // 如果有偶数个元素,中位数就是大根堆和小根堆堆顶 的平均值
        // 大根堆元素个数 比 小根堆元素个数 大1
        // 大根堆的堆顶 <= 小根堆的堆顶
        // 难点 建立两个堆
        // 1.让流中数据在两个堆中全部过一遍 借助优先级队列进行排序
        // 2.当流中元素个数为奇数个时，将小根堆的堆顶加入到大根堆中
        maxHeap.offer(num);
        minHeap.offer(maxHeap.poll());// 让元素在大根堆 小根堆 全部过一遍整理好各自的顺序
        if (size % 2 == 1){
            maxHeap.offer(minHeap.poll());// 大根堆要比小根堆多一个元素
        }

    }

    public double findMedian() {
        if (size % 2 == 0){
            // 如果流中元素是偶数 中位数是 两个堆的 堆顶之和的均值
            return (double)(maxHeap.peek() + minHeap.peek()) / 2;
        }else {
            // 如果流中元素个数为奇数 中位数是 大根堆的堆顶
            return (double)maxHeap.peek();
        }

    }

}
