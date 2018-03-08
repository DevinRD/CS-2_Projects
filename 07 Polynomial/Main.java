/*

    Author: Devin Darnell
    Date: 02/23/18
    Description: This program allows the user to add two polynomials,
        read polynomial from file, read polynomial from user, print
        polynomial, and evaluate polynomial

    IO: User input from standard input, output to standard output
        Input from a file and output to a log file

*/

import java.util.Scanner;

public class Main {

    private Scanner scnr;   // gets input from user
    private char choice;    // records choices that the user makes
    private String stringPoly;  // stores polynomial inputed

    private Polynomial poly;

    public Main() {
        scnr = new Scanner(System.in);
        poly = new Polynomial();
        choice = 0;
    }

    // gets the program going
    public void begin() {
        do {
            System.out.println("Enter i for interactive mode\n"
                             + "      f to read from a file\n"
                             + "      q to quit\n");

            choice = scnr.nextLine().charAt(0);

            System.out.println();

            switch (choice) {
                case 'i':
                    interactiveMode();
                    break;
                case 'f':
                    fileMode();
                    break;
                case 'q':
                    break;
                default:
                    System.out.println("Invalid option, Choose again.\n");
            }

        } while (choice != 'q');
    }

    // loops through interactive mode
    private void interactiveMode() {
        System.out.println("Enter a polynomial:");

        stringPoly = scnr.nextLine();
        poly.readFromUser(stringPoly);

        System.out.println();

        do {
            System.out.println("Enter a to add to another polynomial\n"
                             + "      e to evaluate polynomial\n"
                             + "      p to print polynomial to screen\n"
                             + "      r to return to main menu\n"
                             + "      q to quit\n");

            choice = scnr.nextLine().charAt(0);

            System.out.println();

            switch (choice) {
                case 'a':
                    Polynomial polynomial2 = new Polynomial();
                    String polyString = "";

                    System.out.println("Enter polynomial to add:");
                    polyString = scnr.nextLine();
                    System.out.println();

                    polynomial2.readFromUser(polyString);
                    poly.add(polynomial2);

                    System.out.println();
                    break;
                case 'e':
                    System.out.println("Enter value to eveluate at:");
                    int value = scnr.nextInt();
                    scnr.nextLine();
                    System.out.println();

                    System.out.println("Evaluates to: " + poly.evaluate(value) + "\n");
                    break;
                case 'p':
                    poly.print();
                    System.out.println();
                    break;
                case 'r':
                    break;
                case 'q':
                    break;
                default:
                    System.out.println("Invalid option, Choose again.\n");
            }
        } while (choice != 'r' && choice != 'q');
        poly.clear();
    }

    // scans file for operations and polynomials
    private void fileMode() {
        System.out.println("Enter file to read from:");
        String fileName = scnr.nextLine();
        System.out.println();

        System.out.println("Enter file to output to:");
        String logName = scnr.nextLine();
        System.out.println();

        poly.readFromFile(fileName, logName);
        poly.clear();
    }

    public static void main(String[] args) {
        Main main = new Main();

        main.begin();
    }

}
