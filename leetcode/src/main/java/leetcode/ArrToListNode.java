package leetcode;

public class ArrToListNode {
    public static ListNode BuildNodeList(int[] arr){
        return buildNode(0, arr);
    }
    private static ListNode buildNode(int index, int[] arr) {
        if (index < arr.length) {
            return new ListNode(arr[index], buildNode(index + 1, arr));
        }
        return null;
    }
}
