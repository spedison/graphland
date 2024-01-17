package br.com.spedison.grafos.loadgraphs;

import br.com.spedison.grafos.base.Edge;
import br.com.spedison.grafos.base.Graph;
import br.com.spedison.grafos.base.Node;
import br.com.spedison.grafos.base.Orientation;

public class LoadSimpleExample implements LoaderGraphs {

    @Override
    public Graph load() {
        Graph g = new Graph("Simple Graph of Example");
        var anuj = new Node<Void>(Void.TYPE.cast(null), "anuj");
        var bob = new Node<Void>(Void.TYPE.cast(null), "bob");
        var you = new Node<Void>(Void.TYPE.cast(null), "you");
        var claire = new Node<Void>(Void.TYPE.cast(null), "claire");
        var thom = new Node<Void>(Void.TYPE.cast(null), "thom");
        var jonny = new Node<Void>(Void.TYPE.cast(null), "jonny");
        var peggy = new Node<Void>(Void.TYPE.cast(null), "peggy");
        var alice = new Node<Void>(Void.TYPE.cast(null), "alice");
        var edi = new Node<Void>(Void.TYPE.cast(null), "edi");

        g.setStartNode(you);
        g.addEdge(new Edge(Void.TYPE.cast(null), "a1", Orientation.SIMPLE_CONNECTED, you, claire));
        g.addEdge(new Edge(Void.TYPE.cast(null), "a2", Orientation.SIMPLE_CONNECTED, you, bob));
        g.addEdge(new Edge(Void.TYPE.cast(null), "a3", Orientation.SIMPLE_CONNECTED, you, alice));
        g.addEdge(new Edge(Void.TYPE.cast(null), "a4", Orientation.SIMPLE_CONNECTED, claire, thom));
        g.addEdge(new Edge(Void.TYPE.cast(null), "a5", Orientation.SIMPLE_CONNECTED, claire, jonny));
        g.addEdge(new Edge(Void.TYPE.cast(null), "a6", Orientation.SIMPLE_CONNECTED, bob, peggy));
        g.addEdge(new Edge(Void.TYPE.cast(null), "a7", Orientation.SIMPLE_CONNECTED, bob, anuj));
        g.addEdge(new Edge(Void.TYPE.cast(null), "a8", Orientation.SIMPLE_CONNECTED, alice, peggy));
        g.addEdge(new Edge(Void.TYPE.cast(null), "a9", Orientation.SIMPLE_CONNECTED, thom, edi));
        g.addEdge(new Edge(Void.TYPE.cast(null), "a10", Orientation.SIMPLE_CONNECTED, edi, bob));
        g.addEdge(new Edge(Void.TYPE.cast(null), "a11", Orientation.SIMPLE_CONNECTED, bob, jonny));
        g.addEdge(new Edge(Void.TYPE.cast(null), "a12", Orientation.SIMPLE_CONNECTED, jonny, anuj));

        g.addNode(anuj);
        g.addNode(bob);
        g.addNode(you);
        g.addNode(claire);
        g.addNode(thom);
        g.addNode(jonny);
        g.addNode(peggy);
        g.addNode(alice);
        g.addNode(edi);


        return g;
    }

    @Override
    public void save() {

    }
}
