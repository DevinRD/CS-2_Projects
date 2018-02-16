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
        clears the list
    */
    public void clear() {
        head = null;
        current = null;
        tail = null;
        System.out.println(this);
    }   // end of clear method

    /*
        returns a boolean value based on if the list is in acending order
    */
    public boolean isSorted() {
        current = head;

        if (head != null && head.next != null) {
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

        if (head == null) { // checks for empty list
                if (position == 1) {
                    head = new Node(value);
                    tail = head;
                } else {
                    System.out.println("Invalid position");
                }
        } else {

            if (position == 1) {
                head = new Node(value);
                head.next = current;
                head.next.last = head;
            } else {
                for (int i = 1; i < position - 1; i ++) {
                    current = current.next;
                }

                Node temp = current.next;
                current.next = new Node(value);
                current.next.last = current;
                current.next.next = temp;
                temp.last = current.next;

                if (current.next.next != null) {
                    current.next.next.last = current.next;
                } else {
                    tail = current.next;
                    tail.last = current;
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

        if (head == null) { // if list is empty
            head = new Node(value);
            tail = head;
        } else if (head.next == null) { // singleton
            if (value >= head.data) {
                head.next = new Node(value);
            } else {
                head = new Node(value);
                head.next = current;
            }

            tail = head.next;
            tail.last = head;

        } else {
            if (value <= current.data) {
                head = new Node(value);
                head.next = current;
                current.last = head;
                System.out.println(this);
                return;
            }
            while (current.next != null) {
                if (current.data <= value && current.next.data >= value) {
                    Node temp = current.next;
                    current.next = new Node(value);
                    current.next.next = temp;
                    temp.last = current.next;
                    current.next.last = current;
                    System.out.println(this);
                    return;
                }
                current = current.next;
            }
            current.next = new Node(value);
            tail = current.next;
            tail.last = current;
        }

        System.out.println(this);
    }   // end of insertInOrder method

    /*
        deletes the first value in the list
    */
    public void deleteFirst() {
        if (head == null) {     // if list is empty
            System.out.println("The list is empty");
        } else if (head.next == null) {     // singleton
                head = null;
                tail = head;
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
        if (tail != null) {
            if (tail.last != null) {
                tail.last.next = null;
                tail = tail.last;
            } else {
                tail.last = null;
                tail = null;
                head = null;
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
