package br.com.spedison.graphland.base;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

public class Graph<TN, TE> {

    private String name;

    /***
     * One Graph is set of Edges and Nodes.
     */
    private final List<Node<TN,TE>> listNodes;
    private Node<TN,TE> startNode;
    private final List<Edge<TE,TN>> listEdges;


    public Graph(String name, List<Node<TN,TE>> nodes, List<Edge<TE,TN>> edges, Node<TN,TE> startNode) {
        this.name = name;
        this.listNodes = nodes;
        this.listEdges = edges;
        this.startNode = startNode;
    }

    public Graph(String name) {
        this.name = name;
        this.listNodes = new LinkedList<>();
        this.listEdges = new LinkedList<>();
        setStartNode(null);
    }

    public Node<TN,TE> findNodeByName(String nodeName) {

        Node<TN,TE> nd = new Node<>(null, nodeName);

        int pos = listNodes.indexOf(nd);

        if (pos == -1)
            return null;

        return listNodes.get(pos);
    }

    public Edge<TE,TN> findEdge(Node<TN,TE> nodeSrc, Node<TN,TE> nodeDest) {

        int pos = listEdges.indexOf(new Edge<TE,TN>(null, "", Orientation.FULL_CONNECTED, nodeSrc, nodeDest));

        if (pos == -1)
            return null;

        Edge<TE,TN> ret = listEdges.get(pos);

        if (ret.getOrientation() == Orientation.FULL_CONNECTED)
            return ret;

        if (ret.getNodeStart().equals(nodeSrc) && ret.getNodeEnd().equals(nodeDest))
            return ret;

        return null;
    }


    public void addNode(Node<TN,TE> node) {
        listNodes.add(node);
    }

    public void addEdge(Edge<TE,TN> edge) {
        listEdges.add(edge);
    }

    public List<Edge<TE,TN>> getListEdges() {
        return listEdges;
    }

    public List<Node<TN,TE>> getListNodes() {
        return listNodes;
    }

    public void clearCheckNodes() {
        listNodes.forEach(Node::clearChecked);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartNode(Node<TN,TE> startNode) {
        this.startNode = startNode;
    }

    public String getName() {
        return name;
    }

    public Node<TN,TE> getStartNode() {
        return startNode;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Graph (%s) \n".formatted(getName()));

        sb.append("\n:: List of Nodes ::\n");

        listNodes.forEach(n -> sb.append(n.toString() + "\n"));

        sb.append("\n:: Start Node :: " + startNode.toString() + "\n");

        sb.append("\n:: Path of Nodes ::\n");

        ListIterator<Edge<TE,TN>> edgeIteraror = listEdges.listIterator();

        while (edgeIteraror.hasNext()) {
            Edge<TE,TN> edge = edgeIteraror.next();
            int edgeIndex = edgeIteraror.nextIndex();

            String contentEdge = "";

            if (!Objects.isNull(edge.getContent()))
                contentEdge = " - " + edge.getContent().toString();

            sb.append("%03d) %s< [ %s%s ] %s >%s\n".formatted(
                    edgeIndex,
                    edge.getNodeStart().getName(),
                    edge.getName(),
                    contentEdge,
                    edge.getOrientation() == Orientation.FULL_CONNECTED ? "--" : "->",
                    edge.getNodeEnd().getName()
            ));
        }

        sb.append("\n");
        return sb.toString();
    }

    public Edge<TE,TN> findEdgeByName(String name) {
        Edge<TE,TN> ret = null;
        for (Edge<TE,TN> ed : listEdges) {
            if (ed.getName().equals(name)) {
                ret = ed;
                break;
            }
        }
        return ret;
    }
}