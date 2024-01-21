package br.com.spedison.graphland.loadgraphs;

import br.com.spedison.graphland.base.Edge;
import br.com.spedison.graphland.base.Graph;
import br.com.spedison.graphland.base.Node;
import br.com.spedison.graphland.base.Orientation;

public class LoadModelDijkstrasExample implements LoaderGraphs<Integer, Integer> {
    String fileName;

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    @Override
    public Graph load() {
        Graph<Integer, Integer> g = new Graph<>("Simple Graph for Dijkstras Example");
        var a = new Node<Integer,Integer>(Integer.MAX_VALUE, "A");
        var b = new Node<Integer,Integer>(Integer.MAX_VALUE, "B");
        var c = new Node<Integer,Integer>(Integer.MAX_VALUE, "C");
        var d = new Node<Integer,Integer>(Integer.MAX_VALUE, "D");
        var e = new Node<Integer,Integer>(Integer.MAX_VALUE, "E");
        var f = new Node<Integer,Integer>(Integer.MAX_VALUE, "F");

        g.setStartNode(a);
        g.addEdge(new Edge<>(2, "A_B", Orientation.FULL_CONNECTED, a, b));
        g.addEdge(new Edge<>(8, "A_D", Orientation.FULL_CONNECTED, a, d));
        g.addEdge(new Edge<>(5, "D_B", Orientation.FULL_CONNECTED, d, b));
        g.addEdge(new Edge<>(3, "D_E", Orientation.FULL_CONNECTED, d, e));
        g.addEdge(new Edge<>(6, "B_E", Orientation.FULL_CONNECTED, b, e));
        g.addEdge(new Edge<>(2, "D_F", Orientation.FULL_CONNECTED, d, f));
        g.addEdge(new Edge<>(9, "E_C", Orientation.FULL_CONNECTED, e, c));
        g.addEdge(new Edge<>(3, "F_C", Orientation.FULL_CONNECTED, f, c));
        g.addEdge(new Edge<>(9, "E_F", Orientation.FULL_CONNECTED, e, f));

        g.addNode(a);
        g.addNode(b);
        g.addNode(c);
        g.addNode(d);
        g.addNode(e);
        g.addNode(f);

        return g;
    }

    @Override
    public void save(Graph<Integer, Integer> tg) {

    }
}
