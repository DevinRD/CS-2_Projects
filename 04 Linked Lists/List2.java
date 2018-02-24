/*

    Author: Devin Darnell
    Date: 02/11/18
    Description: This is a doubly-linked list, no tail reference, non-circular,
        containing integers

*/


public class List2 {

    Node head;      // defines the beggining of the list
    Node tail;      // defines the end of the list
    Node current;   // traverses the list to help perform operations on the list

    public List2() {
        System.out.println(this);
    }


    /*
        One piece of the list containing a data value and a reference to the next node in the list
    */
    public class Node {
        public Node(double value) {
            data = value;
        }
        double data;
        Node last;
        Node next;
    }   // end of node class

    /*
        builds a list based off of an array of doubles
    */
    public void build(double[] doubleArray) {
        if (doubleArray.length > 0) {
            head = new Node(doubleArray[0]);
            current = head;

            for (int i = 1; i < doubleArray.length; i++) {
                current.next = new Node(doubleArray[i]);
                current.next.last = current;
                current = current.next;
            }

            tail = current;
        } else {
            System.out.println("List needs to have values!!");
        }

        System.out.println(this);
    }   // end of build method

    /*
        returns a boolean value based on if the list is in acending order
    */
    public boolean isSorted() {
        current = head;

        if (!isEmpty() && head.next != null) {  // checks if there is 1 or no values in the list
            while (current.next != null) {
                if (current.data > current.next.data) {
                    return false;
                }
                current = current.next;
            }
        }

        return true;
    }   // end of isSorted method

    /*
        inserts a value based on a position given !!does not protect agained
        invalid inputs
    */
    public void insertValuePosition(double value, int position) {
        current = head;

        if (isEmpty()) { // checks for empty list
                if (position == 1) {
                    head = new Node(value);
                    tail = head;
                } else {
                    System.out.println("Invalid position");
                }
        } else {

            if (position == 1) {    // handles a change at the beginning of the list
                head = new Node(value);
                head.next = current;
                current.last = head;
            } else {
                for (int i = 1; i < position; i ++) {   // places current at the position (1-based)
                    current = current.next;
                }

                if (current != null) {      // handles if inserted at end of list
                    Node temp = current;
                    current = new Node(value);
                    current.last = temp.last;
                    current.last.next = current;
                    current.next = temp;
                    temp.last = current;
                } else {
                    current = new Node(value);
                    tail.next = current;
                    current.last = tail;
                    tail = tail.next;
                }
            }
        }

        System.out.println(this);
    }   // end of insertValuePosition method

    /*
        inserts a value based on the order of the list
    */
    public void insertInOrder(double value) {
        current = head;

        if (isEmpty()) { // if list is empty
            head = new Node(value);
            tail = head;
        } else {
            if (value <= head.data) {   // inserted at beginning of list
                head = new Node(value);
                head.next = current;
                current.last = head;
            } else {
                while (current.next != null) {
                    if (current.data <= value && current.next.data >= value) {
                        Node temp = new Node(value);
                        temp.next = current.next;
                        current.next = temp;
                        temp.last = current;
                        temp.next.last = temp;
                        break;
                    }
                    current = current.next;
                }

                if (current.next == null) {  // inserts at end of list
                    current.next = new Node(value);
                    tail = current.next;
                    tail.last = current;
                }
            }
        }

        System.out.println(this);
    }   // end of insertInOrder method

    /*
        deletes the first value in the list
    */
    public void deleteFirst() {
        if (isEmpty()) {     // if list is empty
            System.out.println("The list is empty");
        } else if (head.next == null) {     // singleton
                clear();
        } else {     // avaerage list
            head = head.next;
            head.last = null;
        }

        System.out.println(this);
    }   // end of deleteFirst method

    /*
        deletes the last item in the list
    */
    public void deleteLast() {
        if (isEmpty()) {
            System.out.println("The list is empty.");
        } else {
            if (head.next == null) {
                clear();
            } else {
                tail = tail.last;
                tail.next = null;
            }
        }

        System.out.println(this);
    }   // end of deleteLast method

    /*
        prints the list in reverse order !! does not alter list
    */
    public void printInReverse() {
        current = tail;
        String output = "\ntail";

        while (current != null) {
            output += (" > " + current.data);
            current = current.last;
        }

        if (tail == null) {
            output += " > null";
        }

        output += " < head (doubly-linked)\n";

        System.out.println(output);
    }   // end of printInReverse

    /*
        clears the list
    */
    public void clear() {
        head = null;
        current = null;
        tail = null;

        System.out.println(this);
    }   // end of clear method

    public boolean isEmpty() {
        return head == null;
    }

    /*
        prints the values in the list to the screen
    */
    public String toString() {
        current = head;
        String output = "\nhead";

        while (current != null) {
            output += (" > " + current.data);
            current = current.next;
        }

        if (head == null) {
            output += " > null";
        }

        output += " < tail (doubly-linked, tail, non-circular)\n";


        return output;
    }   // end of toString method

}
