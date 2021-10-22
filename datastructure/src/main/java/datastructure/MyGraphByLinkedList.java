package datastructure;

import java.util.ArrayList;
import java.util.Arrays;

// 链表实现有向图
public class MyGraphByLinkedList {
    ArrayList<ArrayList<Integer>> graphs;

    /**
     * 初始化存储图节点的容器
     * @param value 图节点的个数
     */
    public MyGraphByLinkedList(int value) {
        graphs = new ArrayList<ArrayList<Integer>>(value);
        for (int i = 0; i < value; i++) {
            graphs.add(new ArrayList<Integer>());
        }
    }
    // 图中添加边
    public void addEdge(int start,int end) {
        graphs.get(start).add(end);
    }
    // 删除图中的边
    public void removeEdge(int start,int end){
        graphs.get(start).remove((Integer)end);
    }



}
