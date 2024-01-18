package br.com.spedison.graphland.base;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {
    private static String name = "Node name test";
    private static Integer content = 10;

    private Node<Integer> getNodeTest() {
        Node<Integer> nodeTest = new Node<>(content, name);
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
        Node<Integer> nodeTest = getNodeTest();
        nodeTest.setChecked();
        assertTrue(nodeTest.getChecked());
        nodeTest.clearChecked();
        assertFalse(nodeTest.getChecked());
    }


    @Test
    void getCountCheckNode() {
        Node<Integer> nodeTest = getNodeTest();
        nodeTest.setChecked();
        nodeTest.setChecked();
        nodeTest.setChecked();
        assertEquals(3, nodeTest.getCountCheckNode());
        nodeTest.clearChecked();
        assertEquals(0, nodeTest.getCountCheckNode());
    }

    @Test
    void getNextNodes() {
        Node<Integer> nodeTest = getNodeTest();
        nodeTest.getNextNodes().add(getNodeTest());
        nodeTest.getNextNodes().add(getNodeTest());
        nodeTest.getNextNodes().add(getNodeTest());
        assertEquals(3, nodeTest.nextNodes.size());
    }

    @Test
    void getContent() {
        assertEquals(content, getNodeTest().getContent());
    }

    @Test
    void testToString() {
        Node<Integer> node = getNodeTest();
        assertEquals("Node{content=10, name='Node name test', isChecked=false}", node.toString());
    }

    @Test
    void testEquals() {
        Node<Integer> nt1 = getNodeTest();
        Node<Integer> nt2 = getNodeTest();
        assertNotSame(nt1, nt2);
        assertEquals(nt1, nt2);
        nt1.setName(nt1.getName() + "_test");
        assertNotEquals(nt1, nt2);
    }
}