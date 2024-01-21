package br.com.spedison.graphland.base;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EdgeTest {
    private final String name = "Node name test";
    private final String nameEdge = "Edge Name test 1";
    private final Integer content = 10;

    private Node<Integer,Integer> getNodeTest(Integer count) {
        Node<Integer,Integer> nodeTest = new Node<>(content + count, name + "_" + count);
        return nodeTest;
    }

    private Edge<Integer,Integer> getEdgeTest() {
        Integer countP = 10;
        Node<Integer,Integer> nodeTest1 = getNodeTest(countP);
        countP += 10;
        Node<Integer,Integer> nodeTest2 = getNodeTest(countP);
        Edge<Integer,Integer> edge = new Edge<>(30, nameEdge,
                Orientation.FULL_CONNECTED, nodeTest1, nodeTest2);
        return edge;
    }


    @Test
    void getContent() {
        assertEquals(30, getEdgeTest().getContent());
    }

    @Test
    void getNodeLeft() {
        Node<Integer,Integer> comp = getNodeTest(10);
        assertEquals(getEdgeTest().getNodeStart(), comp);
        assertNotSame(getEdgeTest().getNodeStart(), comp);
    }

    @Test
    void getNodeRight() {
        Node<Integer,Integer> comp = getNodeTest(20);
        assertEquals(getEdgeTest().getNodeEnd(), comp);
        assertNotSame(getEdgeTest().getNodeStart(), comp);
    }

    @Test
    void getOrientation() {
        assertEquals(Orientation.FULL_CONNECTED, getEdgeTest().getOrientation());
    }

    @Test
    void getName() {
        assertEquals(nameEdge, getEdgeTest().getName());
    }

    @Test
    void testEquals() {
        Edge<Integer,Integer> ed1 = getEdgeTest();
        Edge<Integer,Integer> ed2 = getEdgeTest();
        assertEquals(ed1, ed2);
        assertNotSame(ed1, ed2);
    }
}