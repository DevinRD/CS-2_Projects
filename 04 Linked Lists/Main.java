/*

    Author: Devin Darnell
    Date: 02/11/18
    Description: This program allows the user to create 3 types of lists
        and perform various operation on them such as append, prepend, and
        clear.

    IO: User input from standard input, output to standard output

*/

import java.util.Scanner;

public class Main {

    private Scanner scnr;           // gets user input

    private int[] data;             // stores values to build lists
    private int choice;             // determines which list to create and what operation to perform on that list based off of user input
    private int intValue;           // stores integer values from user to use for operations
    private double doubleValue;     // stores double values from user to use for operations
    private String strValue;        // stores string values from user to use for operations

    public Main() {
        scnr = new Scanner(System.in);
    }

    /*This method is the driver for the entire program. I promts the user and
        distinguishes between the users choice of lists and operations
    */
    public void begin() {
        do {
        System.out.print("Pick a list type:");
        System.out.println("\n     1. singly-linked, no tail reference, non-circular, Integers"
                            + "\n     2. doubly-linked, tail reference, non-circular, Doubles"
                            + "\n     3. singly-linked, tail reference, circular, Strings"
                            + "\n     4. exit the program\n");

        choice = scnr.nextInt();
        System.out.println();

        switch(choice) {
            case 1:     // Options for list 1
                List1 list1= new List1();
                do {
                    System.out.println("---Type 1 operations:"
                                        + "\n        1. build a list"
                                        + "\n        2. clear the list"
                                        + "\n        3. check if the list is sorted"
                                        + "\n        4. prepend"
                                        + "\n        5. append"
                                        + "\n        6. insert in order"
                                        + "\n        7. delete value from list"
                                        + "\n        8. return to top-level menu\n");

                    choice = scnr.nextInt();
                    System.out.println();

                    switch (choice) {
                        case 1:
                            System.out.println("Enter values with a comma and a space separating each value");
                            String line = scnr.nextLine();
                            line = scnr.nextLine();     // !! don't know why i need two of these
                            String[] numberStrs = line.split(", ");
                            int[] numbers = new int[numberStrs.length];

                            for (int i = 0;i < numberStrs.length;i++) {
                               numbers[i] = Integer.parseInt(numberStrs[i]);
                            }

                            list1.build(numbers);
                            break;
                        case 2:
                            list1.clear();
                            break;
                        case 3:
                            if (list1.isSorted()) {
                                System.out.println("The list is sorted\n");
                            } else {
                                System.out.println("The list is not sorted\n");
                            }
                            System.out.println(list1);
                            break;
                        case 4:
                            System.out.println("Enter value to prepend:");
                            intValue = scnr.nextInt();
                            list1.prepend(intValue);
                            break;
                        case 5:
                            System.out.println("Enter value to append:");
                            intValue = scnr.nextInt();
                            list1.append(intValue);
                            break;
                        case 6:
                            System.out.println("Enter value to insert:");
                            intValue = scnr.nextInt();
                            list1.insertInOrder(intValue);
                            break;
                        case 7:
                            System.out.println("Enter value to delete:");
                            intValue = scnr.nextInt();
                            list1.delete(intValue);
                            break;
                    }
                } while(choice <= 7);

                break;

            case 2:     // options for list 2
                List2 list2 = new List2();
                do {
                    System.out.println("---Type 2 operations:"
                                        + "\n        1. build a list"
                                        + "\n        2. clear the list"
                                        + "\n        3. check if the list is sorted"
                                        + "\n        4. insert by position"
                                        + "\n        5. insert in order"
                                        + "\n        6. delete first value"
                                        + "\n        7. delete last value"
                                        + "\n        8. print in reverse"
                                        + "\n        9. return to top-level menu\n");

                    choice = scnr.nextInt();
                    System.out.println();

                    switch (choice) {
                        case 1:
                            System.out.println("Enter values with a comma and a space separating each value");
                            String line = scnr.nextLine();
                            line = scnr.nextLine();     // !! don't know why i need two of these !!
                            String[] numberStrs = line.split(", ");
                            double[] numbers = new double[numberStrs.length];

                            for (int i = 0;i < numberStrs.length;i++) {
                               numbers[i] = Double.parseDouble(numberStrs[i]);
                            }

                            list2.build(numbers);
                            break;
                        case 2:
                            list2.clear();
                            System.out.println(list2);
                            break;
                        case 3:
                            if (list2.isSorted()) {
                                System.out.println("The list is sorted");
                            } else {
                                System.out.println("The list is not sorted");
                            }

                            System.out.println(list2);
                            break;
                        case 4:
                            System.out.println("Enter value to insert:");
                            doubleValue = scnr.nextDouble();

                            System.out.println("Enter position to insert at");
                            int position = scnr.nextInt();

                            list2.insertValuePosition(doubleValue, position);
                            break;
                        case 5:
                            if (list2.isSorted()) {
                            System.out.println("Enter value to insert:");
                            doubleValue = scnr.nextDouble();
                            } else {
                                System.out.println("The list is not sorted.");
                            }

                            list2.insertInOrder(doubleValue);
                            break;
                        case 6:
                            list2.deleteFirst();
                            break;
                        case 7:
                            list2.deleteLast();
                            break;
                        case 8:
                            list2.printInReverse();
                            break;

                    }
                } while(choice <= 8);

                break;

            case 3:     // options for list 3
                List3 list3 = new List3();
                do {
                    System.out.println("---Type 3 operations:"
                                        + "\n        1. build a list"
                                        + "\n        2. clear the list"
                                        + "\n        3. check if the list is sorted"
                                        + "\n        4. insert at the front without duplication"
                                        + "\n        5. insert at end of list"
                                        + "\n        6. print all strings less that a certain value"
                                        + "\n        7. output minimum and maximum length strings"
                                        + "\n        8. return to top-level menu\n");

                    choice = scnr.nextInt();
                    System.out.println();

                    switch (choice) {
                        case 1:
                            System.out.println("Enter strings with a comma and a space separating each string");
                            String line = scnr.nextLine();
                            line = scnr.nextLine();     // !! don't know why i need two of these
                            String[] numberStrs = line.split(", ");
                            list3.build(numberStrs);
                            break;
                        case 2:
                            list3.clear();
                            break;
                        case 3:
                            if (list3.isSorted()) {
                                System.out.println("The list is sorted\n");
                                System.out.println(list3);
                            } else {
                                System.out.println("The list is not sorted\n");
                                System.out.println(list3);
                            }
                            break;
                        case 4:
                            System.out.println("Enter value to prepend (will only prepend if value is not already in list):");
                            strValue = scnr.nextLine();
                            strValue = scnr.nextLine();     // !! don't know why I need two of these !!
                            list3.prependNoDuplicate(strValue);
                            break;
                        case 5:
                            System.out.println("Enter value to append:");
                            strValue = scnr.nextLine();
                            strValue = scnr.nextLine();     // !! don't know why I need two of these !!
                            list3.append(strValue);
                            break;
                        case 6:
                            System.out.println("Enter length of string");
                            intValue = scnr.nextInt();
                            list3.printLessThan(intValue);
                            break;
                        case 7:
                            list3.printMinAndMax();
                            break;
                    }
                } while(choice <= 7);

                break;

            case 4:
                System.out.println("See ya");
                System.exit(0);
                return;
        }

        choice = 0;

        } while (choice != 4);
    }   // end of begin method

    /*
        This method was used for testing and debugging during developement
    */
    public void test() {
        List2 list2 = new List2();
        double[] test = {5, 6, 7};

        list2.build(test);
        list2.printInReverse();
    }   // end of test method

    public static void main(String[] args) {
        System.out.println(); // gimme some space
        Main main = new Main();
        main.begin();
        //main.test();
    }

}
