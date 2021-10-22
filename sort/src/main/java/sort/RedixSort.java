package sort;

//基数排序
//有负数的数组使用基数排序需要 19 个桶
//bucket数组即存储 原数组元素 的 数组不需要全部置为0，而记录各个桶元素个数的数组需要全部置为0
//时间复杂度:O(d*(n+r))
//空间复杂度:O(n+r)
//稳定性:稳定
//其中，d 为位数，r 为基数，n 为原数组个数。 在基数排序中，因为没有比较操作，
// 所以在复杂上，最好的情况与最坏的情况在时间上是一致的，均为 O(d * (n + r))。
//原地排序算法
public class RedixSort {
    public static void main(String[] args) {
        int []arr = new int[]{1,6,9,16,6,3};
        //bubbleSort(arr);

        redixSort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
    public static void redixSort(int[] arr){
        //桶里面所存的具体数值
        int [][] bucket = new int[10][arr.length-1];
        //每个桶所存元素个数
        int[] bucketElementCounts = new int[10];
        //求最大数的位数
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (max < arr[i]) max =arr[i];
        }
        //求最大数的位数
        int maxCount = (max + "").length();
        //进行多少次基数排序
        for (int i = 0; i < maxCount; i++) {
              //把数组中元素放在桶里
            for (int j = 0; j < arr.length; j++) {
                //确定该数放到几号桶里
                int value =( arr[j] / (int)Math.pow(10,i) )%10;
                bucket[value][bucketElementCounts[value]] =arr[j];
                //bucket[7][0] = 7;     假设arr[j] = 7
                bucketElementCounts[value]++;//记录桶内元素个数的数组要递增
            }
            int index = 0;
            //把桶内的元素取出，放到原来数组中
            for (int j = 0; j < bucketElementCounts.length; j++) {
                if (bucketElementCounts[j] != 0){
                    //说明 j 号 桶里 有元素
                    //把每个桶里的元素取出，放到原数组中
                    for (int k = 0; k < bucketElementCounts[j]; k++) {
                        //遍历每个桶的元素
                        arr[index] = bucket[j][k];
                        index++;
                    }
                }
                //把每个桶内的元素个数置为0，方便下次桶排序
                bucketElementCounts[j] = 0;
            }


        }

    }
}
