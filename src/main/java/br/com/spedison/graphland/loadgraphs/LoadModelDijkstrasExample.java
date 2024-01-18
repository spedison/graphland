package br.com.spedison.graphland.loadgraphs;

import br.com.spedison.graphland.base.Edge;
import br.com.spedison.graphland.base.Graph;
import br.com.spedison.graphland.base.Node;
import br.com.spedison.graphland.base.Orientation;

public class LoadModelDijkstrasExample implements LoaderGraphs {

    @Override
    public Graph load() {
        Graph<Integer,Integer> g = new Graph<>("Simple Graph for Dijkstras Example");
        var a = new Node<Integer>(Integer.MAX_VALUE, "a");
        var b = new Node<Integer>(Integer.MAX_VALUE, "b");
        var c = new Node<Integer>(Integer.MAX_VALUE, "c");
        var d = new Node<Integer>(Integer.MAX_VALUE, "d");
        var e = new Node<Integer>(Integer.MAX_VALUE, "e");
        var f = new Node<Integer>(Integer.MAX_VALUE, "f");

        g.setStartNode(a);
        g.addEdge(new Edge<Integer>(2, "a_b", Orientation.FULL_CONNECTED, a, b));
        g.addEdge(new Edge<Integer>(8, "a_d", Orientation.FULL_CONNECTED, a, d));
        g.addEdge(new Edge<Integer>(5, "d_b", Orientation.FULL_CONNECTED, d, b));
        g.addEdge(new Edge<Integer>(3, "d_e", Orientation.FULL_CONNECTED, d, e));
        g.addEdge(new Edge<Integer>(6, "b_e", Orientation.FULL_CONNECTED, b, e));
        g.addEdge(new Edge<Integer>(2, "d_f", Orientation.FULL_CONNECTED, d, f));
        g.addEdge(new Edge<Integer>(9, "e_c", Orientation.FULL_CONNECTED, e, c));
        g.addEdge(new Edge<Integer>(3, "f_c", Orientation.FULL_CONNECTED, f, c));
        g.addEdge(new Edge<Integer>(9, "e_f", Orientation.FULL_CONNECTED, e, f));

        g.addNode(a);
        g.addNode(b);
        g.addNode(c);
        g.addNode(d);
        g.addNode(e);
        g.addNode(f);

        return g;
    }

    @Override
    public void save() {

    }
}
