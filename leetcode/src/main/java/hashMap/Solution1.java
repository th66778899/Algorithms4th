package hashMap;

import java.util.*;

// leetcode 1 简单 hashMap
public class Solution1 {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        // 题目要求所需元素的下标 需要用for循环来遍历
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])){
                // hashmap中存在所需答案
                return new int[]{map.get(target - nums[i]),i};
            }else {
                map.put(nums[i],i);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        /*Solution1 solution1 = new Solution1();
        int[] ints = solution1.twoSum(new int[]{2, 7, 11, 15}, 9);
        for (int anInt : ints) {
            System.out.println(anInt);
        }*/
        String s = "1";
        try {
            String str = s.substring(s.indexOf("/images"));
        } catch (Exception e) {
            System.out.println("");
        }


    }
}
// leetcode560 中等
class Solution560{
    public int subarraySum(int[] nums, int k) {
        // 求出数组的前缀和 借助hashMap 来完成对多有情况的遍历
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,1);// 边界情况处理如果数组的第一个元素符合要求，就是k，需要进行处理
        int preSum = 0;// 前缀和数组
        int count = 0;// 最终答案
        for (int num : nums) {
            preSum += num;
            if  (map.containsKey(preSum - k)){
                count = count + map.get(preSum - k);
            }
            map.put(preSum,map.getOrDefault(preSum, 0) + 1);// 如果前缀和已经存在 将前缀和出现次数 + 1,
                                                                        // 如果不存在 将该前缀和出现次数置为1
        }
        return count;


    }

    public static void main(String[] args) {
        Solution560 solution560 = new Solution560();
        int i = solution560.subarraySum(new int[]{1, 3, 5}, 8);
        System.out.println(i);
    }


}
// leetcode 544 中等
class Solution544{
    public int leastBricks(List<List<Integer>> wall) {
        // 求出墙的总宽度
        // 求出每层墙的前缀和 判断前缀和 与 垂直线 将涉及到的砖块存入hashMap中
        // 遍历 [1 - 总宽度-1]
        // 填充hashMap
        int preSum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < wall.size(); i++) {
            // 遍历每一行
            for (Integer integer : wall.get(i)) {
                preSum += integer;
                map.put(preSum,map.getOrDefault(preSum,0) + 1);
            }

            map.remove(preSum);// 前缀和的最后一个是墙壁的宽度 该缝隙不能穿过
            preSum = 0;// 将前缀和置0 进入下一行砖进行计算
        }
        Set<Integer> integers1 = map.keySet();
        int max = 0;
        for (Integer integer : integers1) {
            Integer integer1 = map.get(integer);
            if (integer1 > max) max = integer1;  // 找到可以穿过最多的缝隙
                                                 // 行数 - 这个最大值  =  穿过的最少的砖块数量
        }

        return wall.size() - max;
    }

    public static void main(String[] args) {
        Solution544 solution544 = new Solution544();
        int[][] x=new int[][]{{1,2,2,1},{3,1,2},{1,3,2},{2,4},{3,1,2},{1,3,1,1}};


        // 使用匿名内部类初始化
        List<List<Integer>> arrayLists = new ArrayList<List<Integer>>(){{add(new ArrayList<>(Arrays.asList(1,2,2,1)));
            add(new ArrayList<>(Arrays.asList(3,1,2)));
            add(new ArrayList<>(Arrays.asList(1,3,2)));
            add(new ArrayList<>(Arrays.asList(2,4)));
            add(new ArrayList<>(Arrays.asList(3,1,2)));
            add(new ArrayList<>(Arrays.asList(1,3,1,1)));

        }};

        int i = solution544.leastBricks(arrayLists);
        System.out.println(i);
        /*// 使用匿名内部类初始化
        List<String> initial1 = new ArrayList<String>() {
        {
            add("apple");
            add("banana");
            add("orange");}
        };
        // 直接初始化
        ArrayList<String> initial2 = new ArrayList<>(Arrays.asList("apple", "banana", "orange"));
        // add方法初始化
        ArrayList<String> initial3 = new ArrayList<>();
        initial3.add("apple");
        initial3.add("banana");
        initial3.add("orange");
        for (String s : initial3) {
            System.out.print(s + " ");
        }
        new ArrayList<String>() {
            {add("x");}
        };*/


    }
}
// leetcode49 中等
class Solution49{
    public List<List<String>> groupAnagrams(String[] strs) {
        // 字母异位词 转换为char 数组 将该数组进行排序，互为字母异位词的 char数组 排序后的结果是一样的
        // 用hashMap 来存储结果 key 为 字母异位词 排序 后的结果 value为字母异位词 的各个结果
        if (strs.length < 0){
            return null;
        }
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();

            Arrays.sort(chars);
            String key = String.valueOf(chars);

            if (map.containsKey(key)){
                // map中已存在该类型 字母异位词
                map.get(key).add(str);
            }else {
                map.put(key,new ArrayList<String>(){{add(str);}});
            }
        }
        // 遍历hashMap 取出结果集
        ArrayList<List<String>> res = new ArrayList<>(map.values());
        /*Set<String> strings = map.keySet();
        for (String string : strings) {
            List<String> strings1 = map.get(string);
            res.add(strings1);
        }*/
        return res;


    }

    public static void main(String[] args) {
        Solution49 solution49 = new Solution49();
        List<List<String>> lists = solution49.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});
        for (List<String> list : lists) {
            for (String s : list) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }

}
// leetcode138 中等
class Solution138{
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }}
    public Node copyRandomList(Node head) {
        // 使用两个指针来 深拷贝 链表
        // 一个指针遍历旧链表 一个指针来建立新链表
        // 新链表建立完之后 再处理 另外一个随机指针 使用hashMap 来存储旧链表结点和新链表的结点
        // 新链表还未建立完时，处理随机指针很困难
        // 使用虚拟头结点来建立新链表
        Node dummy = new Node(-1);
        Node temp = head,cur = dummy;
        // 存储新旧链表的映射关系
        HashMap<Node, Node> map = new HashMap<>();
        while (temp!=null){
            // 建立新链表
            Node newNode = new Node(temp.val);
            // 存储旧 新 结点的映射关系,待新链表建立完之后，来处理随机指针的问题
            map.put(temp,newNode);
            // 将新结点插入新链表尾部
            cur.next = newNode;
            // 新链表指针向后移动
            cur = cur.next;
            // 旧链表指针向后移动
            temp = temp.next;

        }
        // 处理随机指针的问题
        temp = head;
        cur = dummy.next;
        while (temp!=null){
            if (temp.random!=null){
                cur.random = map.get(temp.random);
            }
            temp = temp.next;
            cur = cur.next;

        }

        return dummy.next;

    }

}
