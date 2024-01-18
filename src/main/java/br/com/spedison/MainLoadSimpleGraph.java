package br.com.spedison;

import br.com.spedison.graphland.base.Graph;
import br.com.spedison.graphland.loadgraphs.LoadSimpleExample;
import br.com.spedison.logger.ConfigLogger;

import java.util.logging.Logger;

public class MainLoadSimpleGraph {

    static private Logger log = ConfigLogger.getLogger(MainLoadSimpleGraph.class);
    public static void main(String[] args) {
        LoadSimpleExample lse = new LoadSimpleExample();
        Graph<Void,Void> graph = lse.load();
        log.fine(graph.toString());
    }

}
