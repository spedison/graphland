package br.com.spedison;

import br.com.spedison.graphland.searcher.FindNodeInGraph;
import br.com.spedison.graphland.base.Graph;
import br.com.spedison.graphland.base.PathOfFindNode;
import br.com.spedison.graphland.loadgraphs.LoadSimpleExample;
import br.com.spedison.logger.ConfigLogger;

import java.util.logging.Logger;

public class MainFindNodeInGraph {

    static private Logger log = ConfigLogger.getLogger(MainFindNodeInGraph.class);
    public static void main(String[] args) {
        Graph<Void, Void> g = (new LoadSimpleExample()).load();
        FindNodeInGraph fn = new FindNodeInGraph();
        PathOfFindNode path = new PathOfFindNode();
        boolean ret = fn.findFirstNodeInGraph(g, g.getStartNode(),
                path,
                node -> node.getName().trim().equalsIgnoreCase("anuj"));

        log.fine("Finded      ::: " + ret);
        log.fine("Path Finded ::: " + path);
    }
}
