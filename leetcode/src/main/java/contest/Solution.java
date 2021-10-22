package contest;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Solution {
    // 检查出现字符是否相同

    public boolean areOccurrencesEqual(String s) {
        char[] chars = s.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        for (char aChar : chars) {
            map.put(aChar,map.getOrDefault(aChar,1) + 1);
        }
        //Collection<Integer> values = map.values();
        Set<Map.Entry<Character, Integer>> entries = map.entrySet();
        int[] arr= new int[map.size()];
        int i = 0;
        for (Map.Entry<Character, Integer> entry : entries) {

            arr[i] = entry.getValue();
            i++;
        }
        int x =arr[0];
        for (int j = 0; j < arr.length; j++) {
            if (x!=arr[j]){
                return false;
            }
        }

        return true;

    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean ababc = solution.areOccurrencesEqual("abacbc");
        System.out.println(ababc);
    }
}
