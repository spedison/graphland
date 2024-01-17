package br.com.spedison;

import br.com.spedison.grafos.searcher.FindNode;
import br.com.spedison.grafos.base.Graph;
import br.com.spedison.grafos.base.PathOfFindNode;
import br.com.spedison.grafos.loadgraphs.LoadSimpleExample;

import java.util.LinkedList;
import java.util.List;

public class MainFindNodeAllPathsInGraph {
    public static void main(String[] args) {
        Graph g = (new LoadSimpleExample()).load();
        FindNode fn = new FindNode();
        PathOfFindNode path = new PathOfFindNode();
        List<PathOfFindNode> results = new LinkedList<>();
        short maxCheck = 5;
        fn.findNodeInAllPathsPossible(g, g.getStartNode(),
                path, results, maxCheck,
                node -> node.getName().trim().toLowerCase().equals("anuj"));

        System.out.println("\n\nPaths finded ::: ");
        results.stream().map(PathOfFindNode::toString).forEach(System.out::println);
    }
}
