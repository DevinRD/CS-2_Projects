public class RecursionTest {

    private int[] students;

    public RecursionTest() {
        students = {};
    }

    public boolean binSearch(int key) {
        return binSearch(key, 0, students.length - 1);
    }

    // helper method
    private boolean binSearch(int key, int lo, int hi) {
        if(lo > hi) {   // base case #1, not found
            return false;
        }
        int mid = (hi + lo) / 2;
        if (students[mid].getStudentID() == key) {
            return true;
        }
        if (key < students[mid].getStudentID()) {
            return binSearch(key, lo, mid - 1);
        }
        return binSearch(key, mid + 1, hi);
    }

    public int addIt(int[] myArray, int i) {
        if (i == myArray.length - 1) return myArray[i];
        System.out.println(i);
        return myArray[i] + addIt(myArray, ++i);
    }

    public int fib(int n) {
        if (n < 3) return 1;
        return fib(n - 1) + fib(n - 2);
    }

    public static void main(String[] args) {

    }

}
