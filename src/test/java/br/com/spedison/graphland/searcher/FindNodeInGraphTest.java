package br.com.spedison.graphland.searcher;

import br.com.spedison.graphland.base.Graph;
import br.com.spedison.graphland.base.PathOfFindNode;
import br.com.spedison.graphland.loadgraphs.LoadSimpleExample;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FindNodeInGraphTest {


    @Test
    void findFirstNodeInGraph() {

        Graph<Void, Void> graph = (new LoadSimpleExample()).load();
        FindNodeInGraph findNodeInGraph = new FindNodeInGraph();
        PathOfFindNode pathOfFindNode = new PathOfFindNode();
        Boolean ret = findNodeInGraph.findFirstNodeInGraph(graph,
                graph.getStartNode(), pathOfFindNode,
                node -> node.getName().equals("edi"));

        assertTrue(ret);
        int size = pathOfFindNode.getList().size();
        assertTrue(size >= 2);
        assertEquals("edi", pathOfFindNode.getList().get(size - 1).getName());
    }

    @Test
    void findNodeInAllPathsPossible() {

        Graph<Void, Void> graph = (new LoadSimpleExample()).load();
        FindNodeInGraph findNodeInGraph = new FindNodeInGraph();
        PathOfFindNode pathOfFindNode = new PathOfFindNode();
        List<PathOfFindNode> result = new LinkedList<>();

        Short maxCheck = 5;
        findNodeInGraph.findNodeInAllPathsPossible(
                graph, graph.getStartNode(),
                pathOfFindNode, result, maxCheck,
                node -> node.getName().toLowerCase().startsWith("a"));

        for (PathOfFindNode fn : result) {
            int size = fn.getList().size();
            assertTrue(size >= 2);
            assertEquals("a", fn.getList().get(size - 1).getName().toLowerCase().trim().substring(0,1));
        }

        assertEquals(5,result.size());

        System.out.println("----Results-----");

        result.stream().forEach(System.out::println);

    }
}