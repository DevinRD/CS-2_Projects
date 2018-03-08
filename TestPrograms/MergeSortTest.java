public class MergeSortTest {

    public static int[] merge(int a[], int b[]) {
        if (a.length > b.length) {
            return mergeHelper(a, b);
        } else {
            return mergeHelper(b, a);
        }
    }

    public static int[] mergeHelper(int[] larger, int[] smaller) {
        int i = 0;
        int j = 0;
        int[] merged = new int[larger.length + smaller.length];

        while (i < larger.length) {
            while (j < smaller.length && smaller[j] <= larger[i]) {
                merged[i + j] = smaller[j++];
            }
            merged[i + j] = larger[i++];
        }

        while (j < smaller.length) {
            merged[i + j] = smaller[j++];
        }

        return merged;
    }

    public static void main(String[] args) {
        int[] array1 = {1};
        int[] array2 = {3};

        int[] merged = merge(array1, array2);

        for (int i = 0; i < merged.length; i++) {
            System.out.println(merged[i]);
        }
       }

}
