package datastructure.singlylinkedlist;

public class Main {

    public static void main(String[] args) {


/**
 * Partition a linked list upto Line 49

        // Create a new LinkedList and append values to it
        LinkedList ll = new LinkedList(3);
        ll.append(5);
        ll.append(8);
        ll.append(10);
        ll.append(2);
        ll.append(1);

        // Print the list before partitioning
        System.out.println("LL before partitionList:");
        ll.printList(); // Output: 3 5 8 10 2 1

        // Call the partitionList method with x = 5
        ll.partitionList(5);

        // Print the list after partitioning
        System.out.println("LL after partitionList:");
        ll.printList(); // Output: 3 2 1 5 8 10


            EXPECTED OUTPUT:
            ----------------
            LL before partition_list:
            3
            5
            8
            10
            2
            1
            LL after partition_list:
            3
            2
            1
            5
            8
            10


          **/

        /*System.out.println("Removing: " + myLinkedList.removeLast().value);
        // myLinkedList.append(3);

        System.out.println("Lets print the list now");

        myLinkedList.printList();
        myLinkedList.getLength();
        System.out.println("---------------------");
        myLinkedList.prepend(1);
        myLinkedList.getLength();

        //myLinkedList.printList();

        // myLinkedList.append(2);
        myLinkedList.printList();


        // (2) Items - Returns 2 Node
        // System.out.println(myLinkedList.removeLast().value);
        // (1) Item - Returns 1 Node
        // System.out.println(myLinkedList.removeLast().value);
        // (0) Items = Returns null
        // System.out.println(myLinkedList.removeLast());*/

        // Problem: Find duplicates in a list

        LinkedList myLinkedList = new LinkedList(1);

        /*myLinkedList.append(1);
        myLinkedList.append(2);
        myLinkedList.append(2);
        myLinkedList.append(3);
        myLinkedList.append(3);
        myLinkedList.append(3);
        myLinkedList.append(4);*/
        myLinkedList.append(2);
        myLinkedList.append(3);
        myLinkedList.append(4);
        myLinkedList.append(5);

        System.out.println("*************************");
        myLinkedList.printList();
        System.out.println("*************************\n");

       // myLinkedList.removeDuplicates();
        myLinkedList.reverse();

        myLinkedList.printList();

        /*
            EXPECTED OUTPUT:
            ----------------
            1
            2
            3
            4

        */
    }
}
