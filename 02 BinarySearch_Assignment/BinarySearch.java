import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileOutputStream;

public class BinarySearch {

    private Scanner scnr;

    private String userNumber;

    public void printInfo() {
        scnr = new Scanner(System.in);
        String choice;
        System.out.println("\n\nHello! This program takes in a string that may be an"
                + "unsigned binary number, \nchecks that there are 8 digits and"
                + "that all digits are valid, \nthen calculates the decimal"
                + "equivalent of the binary number.");
        do {
            System.out.println("\nEnter \"file\" to read data from a file, \n"
            + "\"manual\" to enter data from the console, \nor \"quit\" to terminate program.\n");
            choice = scnr.nextLine();
            if (choice.equalsIgnoreCase("file")) {
                processFileNumbers();
            } else if(choice.equalsIgnoreCase("manual")) {
                processUserNumbers();
            } else if(!choice.equalsIgnoreCase("quit")){
                System.out.println("Choice not accepted");
            }
        } while(!choice.equalsIgnoreCase("quit"));

        System.out.println("\nGoodbye");
    }

    public void processUserNumbers() {
        System.out.println("\nEnter quit to exit");
        do {
            System.out.println("\nEnter value\n");
            userNumber = scnr.nextLine();
            System.out.print("\nstring: " + userNumber + "\nstatus: ");
            if (validateNumber()) {
                evaluateNumber();
            } else {
                System.out.println("invalid");
            }
        } while(!userNumber.equalsIgnoreCase("quit"));
    }

    public boolean validateNumber() {
        if (userNumber.length() == 8) {
            for (int i = 0; i < userNumber.length(); i++) {
                if (userNumber.charAt(i) != '0' && userNumber.charAt(i) != '1') {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public void evaluateNumber() {
        System.out.println("valid\ndecimal value: " + Integer.parseInt(userNumber, 2));
    }

    public void processFileNumbers() {
        System.out.println("\nEnter file to read from\n");
        String fileIn = scnr.nextLine();
        System.out.println("\nEnter file to output to\n");
        String output = scnr.nextLine();
        try {
            File file = new File("./" + fileIn);
            FileOutputStream outStream = new FileOutputStream("./" + output);

            Scanner sc = new Scanner(file);
            PrintWriter writer = new PrintWriter(outStream);

            writer.println("Devin Darnell - Project #2: BinarySearch\n");

            while (sc.hasNextLine()) {
                userNumber = sc.nextLine();

                writer.print("string: " + userNumber + "\nstatus: ");
                if (validateNumber()) {
                    writer.println("valid" + "\ndecimal value: " + Integer.parseInt(userNumber, 2) + "\n");
                } else {
                    writer.println("invalid\n");
                }
            }
            writer.flush();
            writer.close();

            System.out.println("\nComplete");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BinarySearch search = new BinarySearch();
        search.printInfo();
    }

}
