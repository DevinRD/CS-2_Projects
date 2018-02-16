/*

    Author: Devin Darnell
    Date: 02/11/18
    Description: This is a singely-linked list, no tail reference, non-circular,
        containing integers

*/

public class List1 {

    private Node head;      // Always references the front of the list
    private Node current;   // Walks through the list to perform operations and retriev data

    public List1() {
        System.out.println(this);
    }

    /*
        One piece of the list containing a data value and a reference to the next node in the list
    */
    public class Node {
        public Node(int value) {
            data = value;
        }
        int data;
        Node next;
    }   // end of node class

    /*
        Builds a list based off of an integer array
    */
    public void build(int[] intArray) {
        if (intArray.length != 0) {
            head = new Node(intArray[0]);
            current = head;

            for (int i = 1; i < intArray.length; i ++) {
                current.next = new Node(intArray[i]);
                current = current.next;
            }
        } else {
            System.out.println("List needs to have values\n");
        }

        System.out.println(this);
    }   // end of build

    /*
        clears the list
    */
    public void clear() {
        head = null;
        current = null;
        System.out.println(this);
    }   // Clears the list

    /*
        returns a boolean based on if the list is in acending order
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
        adds a value to the beginning of the list
    */
    public void prepend(int data) {
        current = head;
        head = new Node(data);
        head.next = current;

        System.out.println(this);
    }   // end of prepend method

    /*
        adds a value to the end of the list
    */
    public void append(int data) {
        if (head != null) {
            current = head;

            while (current.next != null) {
                current = current.next;
            }

            current.next = new Node(data);
        } else {
            head = new Node(data);
        }

        System.out.println(this);
    }   // end of append method

    /*
        inserts a value based off of that value in a sorted list
    */
    public void insertInOrder(int value) {
        if (isSorted() && head != null) {
            current = head;
            if (value < head.data) {
                head = new Node(value);
                head.next = current;

                System.out.println(this);
            } else {
                while (current.next != null) {
                    if (value >= current.data && value <= current.next.data) {
                        Node temp = current.next;
                        current.next = new Node(value);
                        current.next.next = temp;

                        System.out.println(this);
                        return;
                    }
                    current = current.next;
                }
                    current.next = new Node(value);
                    System.out.println(this);
            }
        } else if (head == null) {
            head = new Node(value);
            System.out.println(this);
        } else {
            System.out.println("List is not in order!\n");
        }
    }   // end of insertInOrder method

    /*
        deletes a value in the list based off of its value
    */
    public void delete(int value) {
        if (head == null) {
            System.out.println("\nThe list is empty!");
            System.out.println(this);
        } else if(head.next == null) {
            if (value == head.data) {
                head = null;
                System.out.println(this);
            } else {
                System.out.println("\n" + value + " is not in the list");
                System.out.println(this);
            }
        } else {
            current = head;
            if (value == head.data) {
                head = head.next;
                System.out.println(this);
            } else {
                while (current.next != null) {
                    if (current.next.data == value) {
                        current.next = current.next.next;
                        System.out.println(this);
                        return;
                    }
                    current = current.next;
                }
                System.out.println("\n" + value + " was not in the list");
                System.out.println(this);
            }
        }
    }   // end of delete method

    /*
        prints the items in the list to the screen
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

        output += " (singly-linked, no tail, non-circular)\n";

        return output;
    }   // end of toString method

}
