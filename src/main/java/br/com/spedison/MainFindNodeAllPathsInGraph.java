package br.com.spedison;

import br.com.spedison.graphland.searcher.FindNodeInGraph;
import br.com.spedison.graphland.base.Graph;
import br.com.spedison.graphland.base.PathOfFindNode;
import br.com.spedison.graphland.loadgraphs.LoadSimpleExample;
import br.com.spedison.logger.ConfigLogger;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class MainFindNodeAllPathsInGraph {
    static private Logger log = ConfigLogger.getLogger(MainFindNodeAllPathsInGraph.class);
    public static void main(String[] args) {

        Graph<Void,Void> g = (new LoadSimpleExample()).load();
        FindNodeInGraph fn = new FindNodeInGraph();
        PathOfFindNode path = new PathOfFindNode();
        List<PathOfFindNode> results = new LinkedList<>();

        short maxCheck = 5;

        fn.findNodeInAllPathsPossible(g, g.getStartNode(),
                path, results, maxCheck,
                node -> node.getName().trim().toLowerCase().equals("anuj"));

        log.fine("Paths finded ::: " + results.size() + " path(s) found.");
        results.stream().map(PathOfFindNode::toString).forEach(log::fine);
    }
}
