import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class ArrayVsArrayList {

    private final int SIZE;

    private double begin, arrayTime, arrayListTime;

    private int[] array;
    private ArrayList<Integer> arrayList;

    private Random random;

    DecimalFormat realFormatter;

    public ArrayVsArrayList() {
        SIZE = 10000000;
        array = new int[SIZE];
        arrayList = new ArrayList<Integer>();
        random = new Random();
        realFormatter = new DecimalFormat("0,000");
    }

    public void fill() {
        System.out.println("Fill race, N = " + realFormatter.format(SIZE));

        arrayList.clear();

        begin = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            array[i] = random.nextInt(SIZE);
        }
        arrayTime = (System.currentTimeMillis() - begin)/1000;

        begin = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            arrayList.add(random.nextInt(SIZE));
        }
        arrayListTime = (System.currentTimeMillis() - begin)/1000;

        System.out.println("  array fill:     " + arrayTime + " seconds");
        System.out.println("  ArrayList fill: " + arrayListTime + " seconds" + "\n");
    }

    public void increment() {
        System.out.println("Increment race, N = " + realFormatter.format(SIZE));

        arrayList.clear();

        for (int i = 0; i < SIZE; i++) {
            array[i] = random.nextInt(SIZE);
            arrayList.add(random.nextInt(SIZE));
        }

        begin = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            array[i] = array[i] + 1;
        }
        arrayTime = (System.currentTimeMillis() - begin)/1000;

        begin = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            arrayList.set(i, arrayList.get(i) + 1);
        }
        arrayListTime = (System.currentTimeMillis() - begin)/1000;

        System.out.println("  array increment:     " + arrayTime + " seconds");
        System.out.println("  ArrayList increment: " + arrayListTime + " seconds" + "\n");    }

    public void search() {
        System.out.println("Search race, N = " + realFormatter.format(SIZE));

        arrayList.clear();

        int[] searchList = new int[50]; // search list

        for (int i = 0; i < searchList.length; i++) {
            searchList[i] = random.nextInt(SIZE);
        }

        for (int i = 0; i < SIZE; i++) {
            int randomInt = random.nextInt(SIZE);
            array[i] = randomInt;
            arrayList.add(randomInt);
        } // adding values to search list

        // search array
        begin = System.currentTimeMillis();
        for (int s = 0; s < searchList.length; s++) {
            for (int i = 0; i < SIZE; i++) {
                if (array[i] == searchList[s]) {
                    break;
                }
            }
        }
        arrayTime = (System.currentTimeMillis() - begin)/1000;

        // search arrayList
        begin = System.currentTimeMillis();
        for (int s = 0; s < searchList.length; s++) {
            for (int i = 0; i < SIZE; i++) {
                if (arrayList.get(i) == searchList[s]) {
                    break;
                }
            }
        }
        arrayListTime = (System.currentTimeMillis() - begin)/1000;

        System.out.println("  array search, " + searchList.length + " values:     " + arrayTime + " seconds");
        System.out.println("  ArrayList search, " + searchList.length + " values: " + arrayListTime + " seconds" + "\n");
    }

    public void expand() {
        System.out.println("Expand race, N = " + realFormatter.format(SIZE));

        arrayList.clear();

        arrayList = new ArrayList<Integer>(SIZE);
        ArrayList<Integer> testArrayList = new ArrayList<Integer>();

        begin = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            arrayList.add(random.nextInt());
        }
        arrayTime = (System.currentTimeMillis() - begin)/1000;

        begin = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            testArrayList.add(random.nextInt());
        }
        arrayListTime = (System.currentTimeMillis() - begin)/1000;

        System.out.println("  ArrayList w/o expansion:     " + arrayTime + " seconds");
        System.out.println("  ArrayList with expansion:    " + arrayListTime + " seconds" + "\n");
    }

    public static void main(String[] args) {
        ArrayVsArrayList race = new ArrayVsArrayList();

        System.out.println("\nList race beginning...\n");

        race.fill();
        race.increment();
        race.search();
        race.expand();
    }

}
