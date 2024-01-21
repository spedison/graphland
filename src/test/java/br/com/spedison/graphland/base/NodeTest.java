package br.com.spedison.graphland.base;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {
    private static String name = "Node name test";
    private static Integer content = 10;
    private Node<Integer,Integer> getNodeTest() {
        return getNodeTest(0);
    }

    private Node<Integer,Integer> getNodeTest(Integer rev) {
        Node<Integer,Integer> nodeTest = new Node<>(content+rev, name);
        return nodeTest;
    }

    @Test
    void getName() {
        assertEquals(name,getNodeTest().getName());
    }

    @Test
    void getChecked() {
        assertFalse(getNodeTest().getChecked());
    }

    @Test
    void clearChecked() {
        Node<Integer,Integer> nodeTest = getNodeTest();
        nodeTest.setVisited();
        assertTrue(nodeTest.getChecked());
        nodeTest.clearChecked();
        assertFalse(nodeTest.getChecked());
    }


    @Test
    void getCountCheckNode() {
        Node<Integer,Integer> nodeTest = getNodeTest();
        nodeTest.setVisited();
        nodeTest.setVisited();
        nodeTest.setVisited();
        assertEquals(3, nodeTest.getCountVisitedNode());
        nodeTest.clearChecked();
        assertEquals(0, nodeTest.getCountVisitedNode());
    }

    @Test
    void getListEdge() {
        Node<Integer,Integer> nodeTestp1 = getNodeTest(100);
        Node<Integer,Integer> nodeTestp2 = getNodeTest(200);
        Edge<Integer,Integer> edge = new Edge<>(null,"teste",Orientation.FULL_CONNECTED,nodeTestp1,nodeTestp2);
        assertEquals(1,nodeTestp1.getListEdges().size());
        assertEquals(1,nodeTestp2.getListEdges().size());
        assertSame(nodeTestp2.getListEdges().get(0).getNodeEnd(),nodeTestp1);
        assertSame(nodeTestp1.getListEdges().get(0).getNodeEnd(),nodeTestp2);
    }

    @Test
    void getContent() {
        assertEquals(content, getNodeTest().getContent());
    }

    @Test
    void testToString() {
        Node<Integer,Integer> node = getNodeTest();
        assertEquals("Node{ content = 10, name= 'Node name test', isVisited = false, numberOfEdges = 0 }", node.toString());
    }

    @Test
    void testEquals() {
        Node<Integer,Integer> nt1 = getNodeTest();
        Node<Integer,Integer> nt2 = getNodeTest();
        assertNotSame(nt1, nt2);
        assertEquals(nt1, nt2);
        nt1.setName(nt1.getName() + "_test");
        assertNotEquals(nt1, nt2);
    }
}