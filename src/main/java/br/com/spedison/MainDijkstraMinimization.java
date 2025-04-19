package br.com.spedison;

import br.com.spedison.graphland.base.Graph;
import br.com.spedison.graphland.base.PathOfFindNode;
import br.com.spedison.graphland.dijkstra.DijkstraMinimization;
import br.com.spedison.graphland.dijkstra.LoadFromFileDijkstra;
import br.com.spedison.graphland.dijkstra.base.DijkstraContent;
import br.com.spedison.logger.ConfigLogger;

import java.util.logging.Logger;

public class MainDijkstraMinimization {

    static private Logger log = ConfigLogger.getLogger(MainDijkstraMinimization.class);

    public static void main(String[] args) {

        String dest = args.length > 0 ? args[0] : "G";

        Graph<DijkstraContent, Integer> graph = (new LoadFromFileDijkstra("./schemas/graphs/IntGraph2.txt")).load();
        var dijkstraMinimization = new DijkstraMinimization();

        PathOfFindNode pon = dijkstraMinimization.processAlg(graph, dest);

        log.fine("=================== START OF GRAPH ==================");
        log.fine("Graph Loaded");
        log.fine(graph.toString());
        log.fine("=================== END OF GRAPH =====================");

        log.fine("Min Path found        = " + pon.toString());
        log.fine("Value Sum Of Distance = "+graph.findNodeByName("G").getContent().getDistancePath());
    }
}
