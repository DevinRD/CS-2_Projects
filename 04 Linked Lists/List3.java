/*

    Author: Devin Darnell
    Date: 02/11/18
    Description: This is a singaly-linked list, no tail reference, non-circular,
        containing integers

*/

public class List3 {

    private Node head;  // defines the beginning of the list
    private Node tail; // defines the "end" of the list or rather where it loops
    private Node current;   //traverses the list

    public List3() {
        System.out.println(this);
    }

    /*
        a single element of the list
    */
    class Node {
        public Node(String value) {
            data = value;
        }

        String data;
        Node next;
    }   // end of Node class

    /*
        builds a list of strings based off of an array of strings
    */
    public void build(String[] strArray) {
        head = new Node(strArray[0]);
        current = head;

        for (int i = 1; i < strArray.length; i++) {
            current.next = new Node(strArray[i]);
            current = current.next;
        }

        tail = current;
        tail.next = head;

        System.out.println(this);
    }   // end of build method

    /*
        clears the list
    */
    public void clear() {
        head = null;
        current = head;
        tail = head;

        System.out.println(this);
    }   // end of clear method

    /*
        returns a boolean value if the list is in alphabetical order
    */
    public boolean isSorted() {
        if (!isEmpty()) {
            current = head;

            do {
                if (current.data.compareTo(current.next.data) > 0) {
                    System.out.println(this);
                    return false;
                }
                current = current.next;
            } while (current.next != head);

        }

        return true;
    }   // end of isSorted method

    /*
        inserts a string at the end of the list
    */
    public void append(String value) {
        if (!isEmpty()) {
            tail.next = new Node(value);
            tail = tail.next;
            tail.next = head;
        } else {
            head = new Node(value);
            tail = head;
            tail.next = head;
        }

        System.out.println(this);
    }   // end of append method

    /*
        inserts a string at the front of the list if that string is not already
        in the list
    */
    public void prependNoDuplicate(String value){

        if (!isEmpty()) {
            current = head;
            boolean inList = false;
            do {
                if (current.data.equals(value)) {
                    inList = true;
                    break;
                }
                current = current.next;
            } while (current != head);
            if (!inList) {  // if not in list
                // prepend value
                current = head;
                head = new Node(value);
                head.next = current;
                tail.next = head;
            } else {
                System.out.println("\nThat value is already in the list");
            }
        } else {
            head = new Node(value);
            tail = head;
            tail.next = head;
        }

        System.out.println(this);
    }   // end of prependNoDuplicate method

    /*
        prints all strings in the list than have a value that is lessthan
        the parameter length
    */
    public void printLessThan(int length) {
        String output = "\n-->";
        current = head;
        if (!isEmpty()) {
            do {
                if (current.data.length() < length) {
                    output += (current.data + ", ");
                }
                current = current.next;
            } while (current != head);

            System.out.println(output);
        } else {
            System.out.println("\nThere are no values in the string!");
        }

        System.out.println(this);
    }   // end of printLessThan method

    /*
        prints the min and max strings based on length. If there is a tie,
        both strings are printed
    */
    public void printMinAndMax() {
        if (!isEmpty()) {
            current = head;

            String min = "";
            String max = "";

            int minLength = head.data.length();
            int maxLength = head.data.length();

            do {
                if (current.data.length() < minLength) {
                    minLength = current.data.length();
                }
                if (current.data.length() > maxLength) {
                    maxLength = current.data.length();
                }
                current = current.next;
            } while (current != head);

            System.out.println(minLength + "  " + maxLength);

            do {
                if (current.data.length() == minLength) {
                    min += (current.data + ", ");
                }
                if (current.data.length() == maxLength) {
                    max += (current.data + ", ");
                }
                current = current.next;
            } while (current != head);

            System.out.println("\nMinimum Length(s)-->" + min + " - " + minLength + " character(s) long");
            System.out.println("Maximum Length(s)-->" + max + " - " + maxLength + " character(s) long");

        } else {
            System.out.println("The list is empty!");
        }

        System.out.println(this);
    }   // end of printMinAndMax method

    /*
        Helper method: checks if the list is empty
    */
    private boolean isEmpty() {
        return head == null;
    }   // end of isEmpty method

    /*
        prints the items of the list to the screen
    */
    public String toString() {
        current = head;
        String output = "\nhead";

        if (head != null) {
            do {
                output += (" > " + current.data);
                current = current.next;
            } while (current != head);
        } else {
            output += " > null";
        }

        output += " < tail (singly-linked, tail, circular)\n";

        return output;
    }   //end of toString method

}
