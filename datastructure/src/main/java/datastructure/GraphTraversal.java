package datastructure;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

// 图的遍历
public class GraphTraversal {
    MyGraphByLinkedList graph;
    boolean[] visited;// 保存节点有没有被访问

    public GraphTraversal(MyGraphByLinkedList graph) {
        this.graph = graph;
        visited = new boolean[graph.graphs.size()];
    }
    public void DFS(){
        for (int i = 0; i < graph.graphs.size(); i++) {
            if (!visited[i]){
                DFSTraversal(i);
            }
        }
    }
    // 深度优先搜索
    public void DFSTraversal(int value){
        if (visited[value]) return;
        visited[value] = true;// 标记当前节点已被访问
        System.out.println(value + "->");
        Iterator<Integer> neighbors = graph.graphs.get(value).iterator();
        while (neighbors.hasNext()){
            Integer nextNode = neighbors.next();
            if (!visited[nextNode]){
                // 邻居节点没被访问 去访问邻居节点
                DFSTraversal(nextNode);
            }
        }
    }
    /*BFS 广度优先遍历 需先遍历完节点的相邻节点*/
    public void BFS(){
        for (int i = 0; i < graph.graphs.size(); i++) {
            if (!visited[i]){
                BFSTraversal(i);
            }
        }
    }
    public void BFSTraversal(int value){
        // 使用队列来存储某节点的所有相邻节点
        Deque<Integer> queue = new ArrayDeque<Integer>();
        visited[value] = true;
        queue.offerFirst(value);
        while (queue.size()!=0){
            Integer cur = queue.pollFirst();
            System.out.println(cur + "->");
            Iterator<Integer> neighbors = graph.graphs.get(value).iterator();
            while (neighbors.hasNext()){
                Integer nextNode = neighbors.next();
                if (!visited[nextNode]){
                    queue.offerLast(nextNode);
                    visited[nextNode] = true;
                }
            }
        }
    }
}
