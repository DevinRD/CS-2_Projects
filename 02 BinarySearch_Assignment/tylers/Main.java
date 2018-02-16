/*  Program name: Main.java
    Author: Tyler Furby
    Date: February 2018
    Description: This program takes a string that may be an
    unsigned binary number (an integer), checks that there are
    eight digits and that the digits are all valid, then calculates
    the decimal equivalent of the binary number.
    Output: screen (console)
    Limitation: this version of Binary Numbers only calculates
    the decimal representation of 8-bit binary number responses.
*/

import java.io.*;
import java.util.Scanner;
import java.awt.Desktop;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printInfo();
        String input = scanner.nextLine();
            if (input.equalsIgnoreCase("user input")) {
                    processUserNumbers();
            } else if (input.equalsIgnoreCase("file input")) {
                    processFileNumbers();
            } else {
                    System.out.print("!-- INVALID - COMMAND NOT RECOGNIZED --!");
            }
    }

    public static void printInfo() {
        System.out.print("Binary Numbers: A small application that takes 8-bit\n" +
                         "binary numbers and prints the decimal value, if valid.\n\n" +
                         "Commands:\n" +
                         "'USER INPUT' - convert binary to its decimal value based on user input.\n" +
                         "'FILE INPUT' - convert binary to its decimal value based on file input.\n\n" +
                         "USER: ");
    }

    public static boolean validateNumber(String valueToCheck) {
        return (valueToCheck.length() == 8) && (valueToCheck.matches("[01]+"));
    }

    public static void processFileNumbers() {
        try {
            if (Desktop.isDesktopSupported()) {
                File file = new File(".//testfile.txt");;
                Desktop desktop = Desktop.getDesktop();
                desktop.open(file);
                processBinary(file.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void processUserNumbers() {
        String file = ".\\userInput.txt";
        int counter = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter 8-bit binary numbers to be evaluated, followed by the return key...\n" +
                         "Type 'DONE' followed by the return key after adding all the values.\n\n" +
                         "USER: ");
        try (PrintWriter fw = new PrintWriter(new File(file))) {
            String input = null;
            while ((input = scanner.nextLine()) != null) {
                System.out.print("USER: ");
                if (input.trim().equalsIgnoreCase("done")) {
                    break;
                }
                fw.write(input + "\n");
                counter += 1;
            }
            scanner.close();
            fw.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getClass());
        }
        System.out.printf("You entered %d values", counter);
        sleep(500);
        System.out.print(".");
        sleep(500);
        System.out.print(".");
        sleep(500);
        System.out.print(".\n\n");
        sleep(500);
        processBinary(file);
    }

    public static int evaluateNumber(String binary) {
        return Integer.parseInt(binary, 2);
    }

    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void processBinary(String file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                if (validateNumber(line)) {
                    System.out.printf("string: " + line + "\nstatus: valid\ndecimal value: " + evaluateNumber(line) + "\n\n");
                } else {
                    System.out.printf("string: " + line + "\nstatus: invalid\n\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getInputAsString(InputStream is){
        try(java.util.Scanner s = new java.util.Scanner(is)) {
            return s.useDelimiter("\\A").hasNext() ? s.next() : "";
        }
    }
}
