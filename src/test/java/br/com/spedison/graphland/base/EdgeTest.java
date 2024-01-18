package br.com.spedison.graphland.base;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EdgeTest {
    private final String name = "Node name test";
    private final String nameEdge = "Edge Name test 1";
    private final Integer content = 10;

    private Node<Integer> getNodeTest(Integer count) {
        Node<Integer> nodeTest = new Node<>(content + count, name + "_" + count);
        return nodeTest;
    }

    private Edge<Integer> getEdgeTest() {
        Integer countP = 10;
        Node<Integer> nodeTest1 = getNodeTest(countP);
        countP += 10;
        Node<Integer> nodeTest2 = getNodeTest(countP);
        Edge<Integer> edge = new Edge<>(30, nameEdge,
                Orientation.FULL_CONNECTED, nodeTest1, nodeTest2);
        return edge;
    }


    @Test
    void getContent() {
        assertEquals(30, getEdgeTest().getContent());
    }

    @Test
    void getNodeLeft() {
        Node<Integer> comp = getNodeTest(10);
        assertEquals(getEdgeTest().getNodeLeft(), comp);
        assertNotSame(getEdgeTest().getNodeLeft(), comp);
    }

    @Test
    void getNodeRight() {
        Node<Integer> comp = getNodeTest(20);
        assertEquals(getEdgeTest().getNodeRight(), comp);
        assertNotSame(getEdgeTest().getNodeLeft(), comp);
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
        Edge<Integer> ed1 = getEdgeTest();
        Edge<Integer> ed2 = getEdgeTest();
        assertEquals(ed1, ed2);
        assertNotSame(ed1, ed2);
    }
}