package datastructure.singlylinkedlist;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LinkedListTest {

    @Test
    void testAppend() {
        LinkedList linkedList = new LinkedList(5);
        linkedList.append(10);
        linkedList.append(15);

        LinkedList.Node tail = linkedList.removeLast();
        assertEquals(15, tail.value);
    }

    @Test
    void testRemoveLast() {
        LinkedList linkedList = new LinkedList(5);
        linkedList.append(10);
        linkedList.append(15);

        LinkedList.Node removedNode = linkedList.removeLast();
        assertEquals(15, removedNode.value);

        LinkedList.Node newTail = linkedList.removeLast();
        assertEquals(10, newTail.value);

        LinkedList.Node singleNode = linkedList.removeLast();
        assertNull(singleNode);
    }

    @Test
    void testPrepend() {
        LinkedList linkedList = new LinkedList(5);
        linkedList.prepend(2);

        assertEquals(2, linkedList.head.value);
        assertEquals(5, linkedList.tail.value);
    }

    // Add more test cases here

}
