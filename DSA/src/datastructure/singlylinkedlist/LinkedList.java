package datastructure.singlylinkedlist;

import java.util.HashSet;
import java.util.Set;

public class LinkedList {
    // A linked list basically has a node and a value but that value is contained inside of a Node
    Node head;
    Node tail;
    int length;

    public LinkedList(int value) {
        // A Linked List when comes to life, it first needs to construct a node and then it needs to have a head,
        // a tail and a value, a head/tail are of type Node. An attribute 'length' is set to 1 when LL starts out
        Node newNode = new Node(value);
        head = newNode;
        tail = newNode;
        length = 1; // A Linked List starts it's journey with a single node, hence length = 1
    }

    public class Node { // A Node is combination of a value and the next node contained inside the node
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public void getHead() { // A method to print the value of Head
        if (head == null) {
            System.out.println("Head: null");
        } else {
            System.out.println("Head: " + head.value);
        }
    }

    public void getTail() { // A method to print the value of Tail
        if (tail == null) {
            System.out.println("Tail: null");
        } else {
            System.out.println("Tail: " + tail.value);
        }
    }

    public void getLength() {
        System.out.println("Length: " + this.length);
    }

    public void printList() {
        // visit each node from the beginning i.e. head and then move to the next node
        // down the list
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.value);
            temp = temp.next;
        }

    }

    public void append(int value) { // add a node to the end of the list with TC of O(1)

        Node newNode = new Node(value);

        if (this.length == 0) {
            head = newNode;
            tail = newNode;
            length = 1;
        } else {
            tail.next = newNode; // link the newNode
            tail = newNode; // then move the tail over
            newNode.next = null; // make sure that last node's next points to null
            length++;
        }
    }

    public Node removeLast() {

        // Remove the last node and return that node with TC of O(n) because of all of
        // the traversal from the beginning

        if (length == 0)
            return null;
        Node temp = head; // Need two variables pre and temp of type Node
        Node pre = head;

        while (temp.next != null) {
            pre = temp;
            temp = temp.next;
        }

        tail = pre; // get the tail to point to pre from the last node
        tail.next = null; // unlink the next node effectively setting it to null
        length--; // important to decrement the length after temp is removed

        if (length == 0) { // Do it again for the edge case if there was only one node in the LL
            head = null;
            tail = null;
        }
        return temp;

    }

    public void prepend(int value) { // add a node to the beginning with TC of O(1)
        Node newNode = new Node(value);
        if (length == 0) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head; // Link the newNode to the Head
            head = newNode; // Move the head back over to the newNode
        }
        length++;

    }

    public Node removeFirst() {
        if (length == 0)
            return null;
        Node temp = head;
        head = head.next;
        temp.next = null;
        length--;
        if (length == 0) { // edge case if LL started with a single node
            tail = null; // Needless to set head equals to null because at this point it's already null
        }
        return temp;
    }

    public Node get(int index) { // this is O(n)
        if (index < 0 || index >= length)
            return null;
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;

    }

    public boolean set(int index, int value) { // this method makes use of get() methode code above
        Node temp = get(index);
        if (temp != null) {
            temp.value = value;
            return true;
        }
        return false;

    }

    public boolean insert(int index, int value) {
        if (index < 0 || index > length)
            return false; // Can't insert at index greater than length
        if (index == 0) {
            prepend(value);
            return true;
        }
        if (index == length) { // At this moment there's nothing at index = length
            append(value);
            return true;
        }
        Node newNode = new Node(value);
        Node temp = get(index - 1);
        newNode.next = temp.next;
        temp.next = newNode;
        length++;
        return true;
    }

    public Node remove(int index) {
        if (index < 0 || index >= length)
            return null;
        if (index == 0) {
            removeFirst();
        }
        if (index == length - 1) {
            removeLast();
        }
        Node prev = get(index - 1);
        Node temp = prev.next; // Node temp = get(index) is a bad idea because of O(n)

        prev.next = temp.next;
        temp.next = null;
        length--;
        return temp;
    }

    public void reverse() { // very critical difficult method
        // begin by swapping head with tail and reverse the links to get a reversed LL
        // notice we're rather better off not referring to head and tail in the subsequent code
        Node temp = head;
        head = tail;
        tail = temp;
        Node after = temp.next;
        Node before = null;
        for (int i = 0; i < length; i++) {
            after = temp.next; // this 'after' variable bridges the gap in subsequent iterations
            temp.next = before; // this is the essence of reversal, this flips the arrow the other way, this
            // line creates the gap
            before = temp; // moves 'before' linearly to next node which is 'temp'
            temp = after; // this is where we have temp jump across the gap
            // so we're moving before up and temp over on previous two lines
        }
    }

    /**
     * Problem # 1 Implement a method called findMiddleNode that returns the middle
     * node of the linked list.
     * In this problem, you should use the slow and fast pointer technique
     * (also known as Floyd's Tortoise and Hare algorithm) to find the middle
     * element of the linked list efficiently.
     * The key idea is to have two pointers, one that moves twice as fast as the
     * other. By the time the fast pointer
     * reaches the end of the linked list, the slow pointer will be at the middle
     * node.
     * This method uses two pointers, slow and fast, and advances them at different
     * speeds through the list.
     * https://www.udemy.com/course/data-structures-and-algorithms-java/learn/quiz/5814490#overview
     */

    public Node findMiddleNode() {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) { // debug this in isolation of conditions to understand better

            /**
             * fast.next.next is allowed to become null, we need to check it's previous two
             * nodes stay under the bounds
             * while( fast != null && fast.next != null)
             * Don't be intimidated by seeing two of the conditions being checked with &&.
             * This is because in the code
             * block you could see that fast pointers jumps two steps ahead along the linked
             * list, and you want to avoid
             * a case when it jumps out of bounds by becoming the last node in which case
             * the fast.next would become null
             */
            // this condition checks the fast pointer to have it's next pointer under check
            // as it jumps two steps ahead at a time

            // slow moves one node at a time, while fast moves two nodes at a time

            slow = slow.next; // Move slow pointer to the next node

            fast = fast.next.next; // Move fast pointer to the next two nodes
        }
        return slow; // Return the Node object representing the middle node of the linked list
    }

    /**
     * Problem #2 Has Loop?
     * Write a method called hasLoop that is part of the linked list class.
     * The method should be able to detect if there is a cycle or loop present in the linked list.
     * <p>
     * Question:
     * Why are fast != null and fast.next != null both necessary in the while loop?
     * <p>
     * Answer:
     * In the while condition, fast != null and fast.next != null are both necessary
     * to ensure that the code doesn't throw a NullPointerException.
     * <p>
     * Here's why each condition is necessary specifically for problem#2:
     * <p>
     * fast != null: This condition checks if the fast pointer has reached the end
     * of the list. If the list doesn't have a loop,
     * the fast pointer will eventually reach the end of the list (i.e., it will
     * become null).
     * <p>
     * fast.next != null: Since the fast pointer moves two nodes ahead at a time, this
     * condition checks if there is at least
     * one more node after the current fast node before trying to move to the next
     * node. If this condition wasn't there
     * and fast was at the last node in the list, trying to move two nodes ahead
     * with fast.next.next would result in a NullPointerException.
     */
    public boolean hasLoop() {
        boolean result = false;
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {

            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                result = true;
                break;
            }
        }

        return result;
    }

    /**
     * Coding Exercise 14: Problem#4 LL: Find Kth Node From End ( ** Interview Question)
     * Pseudo Code:
     * <p>
     * (https://www.udemy.com/course/data-structures-and-algorithms-java/learn/quiz/5832124#overview)
     * </p>
     * The algorithm uses two pointers, called 'slow' and 'fast'. Both of these
     * pointers start at the head of the list
     * (the beginning of the chain).
     * First, 'fast' is moved 'k' steps along the list. If 'fast' encounters the end
     * of the list (represented by 'null')
     * before it has taken 'k' steps, the function returns 'null' because the list
     * is shorter than 'k' elements.
     * If 'fast' successfully takes 'k' steps without reaching the end of the list,
     * then the game changes. Now,
     * both 'slow' and 'fast' start moving along the list at the same pace, one step
     * at a time.
     * Here's the clever bit: by the time 'fast' hits the end of the list, 'slow'
     * will be 'k' steps behind it -
     * - and therefore 'k' steps from the end of the list. So the function returns
     * 'slow'.
     */

    public Node findKthFromEnd(int k) {
        Node slow = head; // Initialize slow pointer at head
        Node fast = head; // Initialize fast pointer at head

        for (int i = 0; i < k; i++) { // Watch out: To move k steps ahead, i is 0 to begin with and moves up to i<k
            if (fast == null)
                return null; // if k is out of bounds, return null
            fast = fast.next; // move the fast pointer to next node until k steps from head
        }

        // At this point slow pointer is k steps behind the fast, now advance both
        // pointers ahead one step along the LL
        // Notice how slow pointer is k steps relatively away from the fast pointer,
        // while fast does not become null:
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow; // slow pointer is now Kth node from the end of the list where is fast is
        // pointing at the last node
    }

    /**
     * LL: Partition List ( ** Interview Question)
     * Given a value x you need to rearrange the linked list such that all nodes
     * with a value less than x come before all nodes with a value greater than or
     * equal to x.
     * Additionally, the relative order of nodes in both partitions should remain
     * unchanged.
     * This code defines a method named partitionList that takes an integer x as
     * input and rearranges a singly linked list such that all nodes with values
     * less than x come before nodes with values greater than or equal to x.
     * <p>
     * Here's an explanation of each part of the code:
     * <p>
     * <p>
     * <p>
     * if (head == null) return;: This checks if the linked list is empty. If the
     * head is null, it means the list is empty, and there is nothing to partition,
     * so the method returns.
     * <p>
     * Node dummy1 = new Node(0); and Node dummy2 = new Node(0);: These lines create
     * two dummy nodes with values 0. They are used as placeholders to track the
     * start of two separate lists: one for nodes with values less than x and
     * another for nodes with values greater than or equal to x.
     * <p>
     * Node prev1 = dummy1; and Node prev2 = dummy2;: These lines initialize two
     * variables, prev1 and prev2, to track the last nodes in the two lists
     * mentioned above.
     * <p>
     * Node current = head;: This line initializes a variable current to the head of
     * the input list. This variable is used to iterate through the list.
     * <p>
     * while (current != null) { ... }: This while loop iterates through the linked
     * list until the end is reached (i.e., current becomes null).
     * <p>
     * Inside the loop: a. if (current.value < x) { ... }: If the current node's
     * value is less than x, the code appends the current node to the list tracked
     * by prev1. b. else { ... }: If the current node's value is greater than or
     * equal to x, the code appends the current node to the list tracked by prev2.
     * c. current = current.next;: This line moves the current pointer to the next
     * node in the input list.
     * <p>
     * prev2.next = null;: After the loop, this line sets the next pointer of the
     * last node in the list tracked by prev2 to null, marking the end of the list.
     * <p>
     * prev1.next = dummy2.next;: This line connects the two lists by setting the
     * next pointer of the last node in the list tracked by prev1 to the first node
     * in the list tracked by dummy2.
     * <p>
     * head = dummy1.next;: Finally, this line updates the head of the input list to
     * point to the first node in the list tracked by dummy1, completing the
     * partitioning process.
     */


    // 3 -> 8 -> 5 -> 10 -> 2 -> 1, Test for x = 5
    public void partitionList(int x) {
        // Return if the list is empty
        if (head == null)
            return;

        // Create two dummy nodes for the new lists for the sake of simplicity to handle
        // the chains of nodes
        // When creating assign the value 0

        Node dummyLeft = new Node(0);
        Node dummyRight = new Node(0);

        // Initialize pointers for the new nodes
        Node pointer1 = dummyLeft;
        Node pointer2 = dummyRight;


        // this current pointer will advance along the original linked list
        Node current = head;

        // Iterate through the linked list [3 -> 8 -> 5 -> 10 -> 2 -> 1]
        while (current != null) {

            // Add nodes to the new lists based on their value
            if (current.value < x) {
                pointer1.next = current; // [[0] -> [3]] // (first pass)
                pointer1 = pointer1.next; // advance the pointer1 one step ahead
            } else {
                pointer2.next = current; // [0] -> [8]] // (first pass in the second iteration)
                pointer2 = pointer2.next;
            }

            current = current.next;
        }

        pointer2.next = null; // when pointer2 arrives at the end of the dummyRight, set it's next = null
        pointer1.next = dummyRight.next; // concatenate the dummyLeft's end to 2nd node from the beginning of the
        // dummyRight because first node of dummies were 0
        head = dummyLeft.next; // set the head to 2nd node of dummyLeft, remember 1st node of dummyLeft was [0]

    }

    /**
     * LL: Remove Duplicates ( ** Interview Question)
     * You are given a singly linked list that contains integer values, where some
     * of these values may be duplicated.
     * <p>
     * Input:
     * <p>
     * LinkedList: 1 -> 2 -> 3 -> 1 -> 4 -> 2 -> 5
     * <p>
     * Output:
     * <p>
     * LinkedList: 1 -> 2 -> 3 -> 4 -> 5
     * <p>
     * Note: this linked list class does NOT have a tail which will make this method
     * easier to implement.
     */

    public void removeDuplicatesUsingSet() {
        Set<Integer> values = new HashSet();

        Node previous = null;
        // The previous node is a reference to the node that immediately precedes the
        // current node in the list.
        // Initially, there is no preceding node, so previous is set to null.

        Node current = head;
        // This current node serves as our pointer for traversing the linked list,
        // starting from the head.
        // We check if the current node's value is already in the set.
        // The key is prev.next = current.next to skip the current, that's why you need a prev node
        while (current != null) {
            if (values.contains(current.value)) {
                previous.next = current.next; // duplicate found...get the previous node to SKIP the current IMPORTANT
                length--;
            }
                /**
                 * Removing the Node: If current is a duplicate, previous.next is updated to
                 * point to current.next.
                 *
                 * Effect: This action skips the current node, effectively removing it from the
                 * list.
                 */

             else {
                values.add(current.value);
                previous = current;
            }
            current = current.next;
        }

    }

    /**
     * Following method does not make use of SET, hence time complexity is of n squared
     */
    public void removeDuplicates() {
        // LinkedList: 1 -> 2 -> 3 -> 1 -> 4 -> 2 -> 5
        // Step 1: Start at the beginning of the list.
        // Initialize `current` to point to the head node.
        // `current` will be used to navigate through the list,
        // one node at a time, starting from the head node.
        Node current = head;

        // Step 2: Check if we've reached the end of the list.
        // We loop until `current` becomes null, which means
        // we've checked all nodes for duplicates.
        while (current != null) {

            // Step 3: Set up a runner node.
            // Initialize `runner` to the `current` node.
            // We'll use `runner` to scan ahead and find duplicates
            // of the `current` node.
            Node runner = current;

            // Step 4: Loop through the remaining nodes.
            // `runner.next` will be null at the end of the list.
/**
 Watch out: while (current != null) in the outer loop
 and while (runner.next != null) in the inner loop
 because runner starts at the current to scan ahead
*/
            while (runner.next != null) {

                // Step 5: Compare nodes.
                // Check if `runner.next` (the next node) has the
                // same value as `current`.
                if (runner.next.value == current.value) {

                    // Step 6: Remove duplicate.
                    // If we find a duplicate, we skip it by
                    // setting `runner.next` to `runner.next.next`.

                    runner.next = runner.next.next;

                    // same effect as previous.next = current.next on line # 467
                    // Step 7: Update list length.
                    // We removed a node, so decrease the list length
                    // by 1 to keep it accurate.
                    length -= 1;

                } else {

                    // Step 8: Move to the next node.
                    // If the next node is not a duplicate,
                    // move `runner` to the next node to continue.
                    runner = runner.next;
                }
            }

            // Step 9: Move to the next unique node.
            // After checking all nodes for duplicates of the
            // current value, move `current` to the next node.
            current = current.next;
        }

    }

    /**
     * LL: Binary to Decimal ( ** Interview Question)
     * Objective:
     * <p>
     * You have a linked list where each node represents a binary digit (0 or 1). The goal of the binaryToDecimal
     * function is to convert this binary number, represented by the linked list, into its decimal equivalent.
     *
     * @return
     */
    public int binaryToDecimal() {
        int num = 0;
        Node current = head;
        /**
         * num = num * 2 + current.value;: This statement performs the core logic for binary to decimal
         * conversion. For each binary digit in the linked list, we multiply the current value of num by 2
         * (essentially performing a bit-shift to the left by one position),
         * and add the value of the current node (current.value).
         * current = current.next;: This moves current to the next node in the list.
         */
        while (current != null) {
            num = num * 2 + current.value;
            current = current.next;
        }
        return num;
    }

    /**
     * LL: Problem:7 Reverse Between ( ** Interview Question)
     * In the LinkedList class, implement a method called reverseBetween that reverses the nodes of the list
     * between indexes startIndex and endIndex (inclusive).
     * Given the list:
     * <p>
     * 1 → 2 → 3 → 4 → 5.
     * </p>
     * https://www.udemy.com/course/data-structures-and-algorithms-java/learn/quiz/5852040#overview
     */

    public void reverseBetween(int startIndex, int endIndex) {
        // Check: If linked list is empty, nothing to reverse.
        // Exit the method.
        if (head == null) return;

        // Create a 'dummyNode' that precedes the head.
        // Simplifies handling edge cases. The dummy node is a helper to simplify our code,
        // especially if we have to reverse starting from the very first node.
        Node dummyNode = new Node(0);
        dummyNode.next = head;

        // 'previousNode' is used to navigate to the node
        // right before our sublist begins.
        Node previousNode = dummyNode;

        // Move 'previousNode' to node just before sublist.
        for (int i = 0; i < startIndex; i++) {
            previousNode = previousNode.next;
        }

        // 'currentNode' marks the first node of sublist.
        Node currentNode = previousNode.next;

        // Loop reverses the section from startIndex to endIndex.
        for (int i = 0; i < endIndex - startIndex; i++) {

            // 'nodeToMove' is the node we'll move to sublist start.


            Node nodeToMove = currentNode.next;

            // Detach 'nodeToMove' from its current position.
            currentNode.next = nodeToMove.next;

            // Attach 'nodeToMove' at the beginning of the sublist.
            nodeToMove.next = previousNode.next;

            // Move 'nodeToMove' to the start of our sublist.
            previousNode.next = nodeToMove;
        }

        // Adjust 'head' if the first node was part of sublist.
        head = dummyNode.next;
    }


}
