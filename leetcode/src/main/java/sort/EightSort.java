package sort;

public class EightSort {
    public static void main(String[] args) {
        int[] x = {3,1,6,-6,9,1,2};
        // 简单插入排序
        //new InsertionSort().insertionSort(x);
        // 希尔排序
        //new ShellSort().shellSort(x);
        // 选择排序
        //new SelectSort().selectSort(x);
        // 堆排序
        //new HeapSort().heapSort(x,x.length);
        // 快速排序
        //new quickSort().quickSort(x,x.length);
        // 归并排序
        new mergeSort().mergeSort(x,x.length);
        for (int i : x) {
            System.out.print(i + " ");
        }
    }
}
// 插入排序(简单插入排序)
class InsertionSort{
    public void insertionSort(int[] arr){
        int length = arr.length;
        for (int i = 1; i < length; i++) {
            int x = arr[i];// 待插入的值 设置为哨兵
            // 如果该值不能直接加到前面有序数组末尾,需要进行插入
            if (x < arr[i - 1]){
                // arr[i] = arr[i-1];
                int j = i - 1;
                while ( j >= 0 && x < arr[j]){
                    // 将前面有序数组的值进行后移,找到要插入值的插入位置
                    arr[j+1] = arr[j];
                    j--;
                }
                // 将要插入的值插入到其位置上
                arr[j + 1] = x;
            }
        }

    }
}
class ShellSort{
    public void shellSort(int[] arr){
        int length = arr.length;
        // h是希尔排序增量
        for (int h = length / 2; h > 0 ; h = h / 2) {
            //
            for (int i = h; i < length; i++) {
                // 由第h个位置开始,将其与前面的元素相比较
                for (int j = i; j >= h && arr[j] < arr[j - h] ;  j = j - h) {
                    // 交换两个元素的值
                    int temp = arr[j];
                    arr[j] = arr[j - h];
                    arr[j - h] = temp;
                }
            }
        }
    }
}
// 选择排序
class SelectSort{
    public void selectSort(int[] arr){
        int length = arr.length;

        for (int i = 0; i < length; i++) {
            int min = i;
            for (int j = i + 1; j < length; j++) {
                if (arr[j] < arr[min]){
                    min = j;
                }
            }
            if (min != i){
                int temp = arr[min];
                arr[min] = arr[i];
                arr[i] = temp;

            }
        }

    }
}
class HeapSort{
    /**
     * 维护堆的性质
     * @param arr 要排序的数组
     * @param length 数组长度
     * @param i 要维护的数组下标
     */
    private void heapify(int[] arr,int length,int i){
        int largest = i;// 要维护的二叉树的根节点(将堆看作是一个二叉树)
        int lchild = i * 2 + 1;// 左孩子
        int rchild = i * 2 + 2;// 右孩子
        if (lchild < length && arr[largest] <= arr[lchild]){
            largest = lchild;
        }
        if (rchild < length && arr[largest] < arr[rchild]){
            largest = rchild;
        }
        if (largest != i){
            // 交换根节点与 较大孩子节点的值
            int temp = arr[largest];
            arr[largest] = arr[i];
            arr[i] = temp;
            // 同时要考虑到 以较大孩子节点为根节点的二叉树是不是满足堆的定义,处理下面的二叉树
            heapify(arr,length,largest);
        }

    }

    /**
     * 堆排序实现 交换最后一个数组元素 与 第0个元素 之后将最后一个元素从堆中剔除出去,构成一个数组
     * @param arr
     * @param length
     */
    public void heapSort(int[] arr,int length){
        // 建立堆
        // 10个元素  10 - 1 / 2 向下取整为 4 正好对应最后一个二叉树的根节点
        for (int i = length / 2 - 1; i >= 0 ; i--) {
            heapify(arr,length,i);
        }
        // 取出堆顶元素,并将堆顶元素从堆中去掉
        for (int i = length - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0]  = arr[i];
            arr[i] = temp;
            heapify(arr,i - 1,0);
        }

    }
}
// 快速排序
class quickSort{
    public void quickSort(int[] arr,int length){
        qSort(arr,0,length - 1);
    }

    /**
     * 快排的逻辑
     * @param arr 数组
     * @param left 左指针
     * @param right 右指针
     */
    private void qSort(int[] arr,int left,int right){

        if (left < right){
            int mid = partition(arr,left,right);
            qSort(arr,left,mid - 1);
            qSort(arr,mid + 1,right);
        }
    }

    /**
     * 分割待排序数组 arr
     * @param arr
     */
    private int partition(int[] arr,int low,int high){
        int i = low;
        int j = high - 1;
        int pivot = arr[high];// 数组最后一个元素 作为哨兵
        while (true){
            while (i < high &&arr[i] <= pivot){
                i++;
            }
            while (j >= 0 && arr[j] > pivot){
                j--;
            }
            if (i < j){
                // 交换 i j 指向的值
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }else {
                // 退出循环
                break;
            }
        }
        // 找到了合适的位置  将 哨兵元素与i指向元素交换
        int temp = arr[i];
        arr[i] = arr[high];
        arr[high] = temp;
        return i;// i为该元素插入位置
    }

    public static void main(String[] args) {
        quickSort quickSort = new quickSort();
        int[] ints = {1, 0, 1, 1};
        quickSort.quickSort(ints,4);
        for (int anInt : ints) {
            System.out.print(anInt + " ");
        }
    }
}
// 归并排序
class mergeSort{
    public void mergeSort(int[] arr,int length){
        int[] tempArr = new int[length];
        mSort(arr,tempArr,0,length - 1);
    }
    private void mSort(int[] arr,int[] tempArr,int left,int right){
        if (left < right){
              int mid = left + (right - left) / 2;
              mSort(arr,tempArr,left,mid);
              mSort(arr,tempArr,mid + 1,right);
              merge(arr,tempArr,left,mid,right);
        }
    }
    private void merge(int[] arr,int[] tempArr,int left,int mid,int right){
        // 左半边数组起始位置
        int l_pos = left;
        // 右半边起始位置
        int r_pos = mid + 1;
        // 临时数组起始位置
        int pos = left;
        while (l_pos <= mid && r_pos <= right){
            if (arr[l_pos] <= arr[r_pos]){
                // 左半边数组元素 <= 右半边数组元素 稳定的归并排序
                // 左半边数组较小 移动到临时数组
                tempArr[pos] = arr[l_pos];
                pos++;
                l_pos++;
            }
            if (arr[l_pos] > arr[r_pos]){
                // 右半边数组较小,移动到临时数组
                tempArr[pos] = arr[r_pos];
                pos++;
                r_pos++;
            }
        }
        // 左半边数组还未全部赋值到临时数组
        while (l_pos <= mid){
            // 左半边数组较小 移动到临时数组
            tempArr[pos] = arr[l_pos];
            pos++;
            l_pos++;
        }
        // 右半边数组还未全部赋值到临时数组
        while (r_pos <= right){
            // 右半边数组较小,移动到临时数组
            tempArr[pos] = arr[r_pos];
            pos++;
            r_pos++;
        }
        // 将临时数组的值 覆盖原数组
        while (left <= right){
            arr[left] = tempArr[left];
            left++;
        }
    }
}
