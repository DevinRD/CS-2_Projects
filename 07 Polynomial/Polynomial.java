/*

    Author: Devin Darnell
    Date: 02/23/18
    Description: This program allows the user to add two polynomials,
        read polynomial from file, read polynomial from user, print
        polynomial, and evaluate polynomial

    IO: User input from standard input, output to standard output
        Input from a file and output to a log file

*/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Math;
import java.util.Scanner;

public class Polynomial {

    private Term beginning;     // first term in the polynomial
    private Term current;       // traverses the polynomial to help perform operations

    // individual terms of the polynomial
    public class Term {

        public Term(int coefficient, int exponent) {
            this.coefficient = coefficient;
            this.exponent = exponent;
        }

        private int coefficient;
        private int exponent;

        private Term next;
    }

    // Adds two polynomials together
    public void add(Polynomial poly) {
        current = beginning;
        poly.current = poly.beginning;
        Polynomial result = new Polynomial();

        //  sets the beginning term of the result
        if (beginning.exponent > poly.beginning.exponent)  {
            result.beginning = beginning;
            current = current.next;
        } else if (beginning.exponent < poly.beginning.exponent) {
            result.beginning = poly.beginning;
            poly.current = poly.current.next;
        } else {
            result.beginning = new Term(beginning.coefficient + poly.beginning.coefficient, beginning.exponent);
            current = current.next;
            poly.current = poly.current.next;
        }

        result.current = result.beginning;

        while (current != null) {
            while (poly.current != null && poly.current.exponent >= current.exponent) {
                if (poly.current.exponent == current.exponent) {
                    result.current.next = new Term(current.coefficient + poly.current.coefficient, current.exponent);
                    current = current.next;
                } else {
                    result.current.next = poly.current;
                }

                poly.current = poly.current.next;
                result.current = result.current.next;
            }
            if (current != null) {
                result.current.next = current;
                result.current = result.current.next;
                current = current.next;
            }
        }

        while (poly.current != null) {
            result.current.next = poly.current;
            poly.current = poly.current.next;
            result.current = result.current.next;
        }

        transferPolynomial(result);
    }

    // copies polynomial from parameter to this polynomial
    private void transferPolynomial(Polynomial poly) {
        beginning = poly.beginning;

        poly.current = poly.beginning;
        current = beginning;

        while (poly.current != null) {
            current.next = poly.current.next;
            current = current.next;
            poly.current = poly.current.next;
        }

    }

    //  inserts a term at the location that current is currently at
    private void insertTerm(Term term) {
        if (current == beginning) { // tests if insertion is at beginning of polynomial
            beginning = term;
            beginning.next = current;
        } else if (current.next != null) {
            Term temp = current.next;
            current.next = term;
            term.next = temp;
        } else {
            current.next = term;
        }

    }

    // reads a polynomial in from the user
    public void readFromUser(String input) {
        String[] terms = input.split(" ");

        if (terms.length != 0) {
            beginning = new Term(getCoefficient(terms[0]), getExponent(terms[0]));
            current = beginning;

            for (int i = 2; i < terms.length; i+= 2) {
                current.next = new Term(getCoefficient(terms[i]), getExponent(terms[i]));
                current = current.next;
            }
        } else {
            System.out.println("No values were entered.");
        }
    }

    // evauates a term in the form of a string and returns its coefficient
    private int getCoefficient(String term) {
        if (term.charAt(0) == 'x') {
            return 1;
        } else if (term.contains("x")) {
            return Integer.parseInt(term.substring(0, term.indexOf('x')));
        } else if (term.contains("^")) {
            return Integer.parseInt(term.substring(0, term.indexOf('^')));
        }
        return Integer.parseInt(term);
    }   // end of helper method

    // evaluates a term in the form of a string and returns its exponent
    private int getExponent(String term) {
        if (!term.contains("^") && term.contains("x")) {
            return 1;
        } else if(!term.contains("x")) {
            return -1;
        }
        return Integer.parseInt(term.substring(term.indexOf('^') + 1, term.length()));
    }   // end of helper method

    // reads in a polynomial from a file given by the user
    public void readFromFile(String fileName, String logName) {
        try {
            FileInputStream inputFile = new FileInputStream("./" + fileName);
            FileOutputStream outStream = new FileOutputStream("./" + logName);

            Scanner scnr = new Scanner(inputFile);
            PrintWriter writer = new PrintWriter(outStream);

            writer.println("Devin Darnell - Cs2 TTh - 03/05/18\n");

            while (scnr.hasNext()) {
                String operation = scnr.nextLine();
                String poly;

                if (operation.equals("add")) {
                    Polynomial polynomial2;
                    String poly2;

                    polynomial2 = new Polynomial();

                    poly = scnr.nextLine();
                    poly2 = scnr.nextLine();

                    readFromUser(poly);
                    polynomial2.readFromUser(poly2);

                    writer.println("add");
                    writer.println(this);
                    writer.println(poly2);

                    add(polynomial2);

                    writer.println(this + "\n");
                } else if (operation.equals("evaluate")) {
                    poly = scnr.nextLine();
                    int value = scnr.nextInt();

                    readFromUser(poly);

                    writer.println("evaluate");
                    writer.println(this);
                    writer.println(value);
                    writer.println(evaluate(value) + "\n");
                }
            }

            System.out.println("Output to " + logName + "\n");

            scnr.close();
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("Failed to read from file");
            e.printStackTrace();
        }

    }

    // prints the polynomial to the screen
    public void print() {
        System.out.println("-->" + this);
    }

    // evaluates a polynomial with a given value
    public int evaluate(int value) {
        int function = 0;
        current = beginning;

        while (current != null) {
            if (current.exponent >= 0) {
                function += current.coefficient * Math.pow(value, current.exponent);
            } else {
                function += current.coefficient;
            }
            current = current.next;
        }
        return function;
    }

    // clears polynomial
    public void clear() {
        beginning = null;
        current = null;
    }

    public String toString() {
        String print = "";
        boolean firstTerm = true;
        current = beginning;

        while (current != null) {
            if (current.coefficient != 0) {

                if (firstTerm) {
                    firstTerm = false;
                } else if (current != null){
                    print += " + ";
                }

                if (current.coefficient != 1) {
                    print += current.coefficient;
                } else if (current.exponent == -1) {
                    print += current.coefficient;
                }

                //if (current.exponent == 0) {
                //    if (current.coefficient == 1) print += current.coefficient;
                //} else {
                if (current.exponent > -1) print += "x";

                    if (current.exponent != 1 && current.exponent != -1) {
                        print += "^" + current.exponent;
                    }
                //}
            }

            current = current.next;
        }

        return print;
    }

}
