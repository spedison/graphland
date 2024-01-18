package br.com.spedison.graphland.base;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Graph<TN, TE> {

    private String name;

    /***
     * One Graph is set of Edges and Nodes.
     */
    private final List<Node<TN>> listNodes;
    private Node<TN> startNode;
    private final List<Edge<TE>> listEdges;


    public Graph(String name, List<Node<TN>> nodes, List<Edge<TE>> edges, Node<TN> startNode) {
        this.name = name;
        this.listNodes = nodes;
        this.listEdges = edges;
        this.startNode = startNode;
    }

    public Graph(String name) {
        this.name = name;
        this.listNodes = new LinkedList<>();
        this.listEdges = new LinkedList<>();
    }

    public Node<TN> findNodeByName(String nodeName) {

        int pos = listNodes.indexOf(nodeName);

        if (pos == -1)
            return null;

        return listNodes.get(pos);
    }

    public Edge<TE> findEdge(Node<TN> nodeSrc, Node<TN> nodeDest) {

        int pos = listEdges.indexOf(new Edge<TN>(nodeSrc, nodeDest));

        if (pos == -1)
            return null;

        return listEdges.get(pos);
    }


    public void addNode(Node<TN> node) {
        listNodes.add(node);
    }

    public void addEdge(Edge<TE> edge) {
        listEdges.add(edge);
    }

    public List<Edge<TE>> getListEdges() {
        return listEdges;
    }

    public List<Node<TN>> getListNodes() {
        return listNodes;
    }

    public void clearCheckNodes() {
        listNodes.forEach(Node::clearChecked);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartNode(Node<TN> startNode) {
        this.startNode = startNode;
    }

    public String getName() {
        return name;
    }

    public Node<TN> getStartNode() {
        return startNode;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Graph (%s) \n".formatted(getName()));

        sb.append("\n\n:: List of Nodes ::\n");

        listNodes.forEach(n -> sb.append(n.toString() + "\n"));

        sb.append("Start Node ::" + startNode.toString() + "\n");

        sb.append("\n\n:: Path of Nodes ::\n");

        for (Edge<TE> edge : listEdges) {
            String contentEdge = "";

            if (!Objects.isNull(edge.getContent()))
                contentEdge = " - " + edge.getContent().toString();

            sb.append("%s< [ %s%s ] %s >%s\n".formatted(
                    edge.getNodeLeft().getName(),
                    edge.getName(),
                    contentEdge,
                    edge.getOrientation() == Orientation.FULL_CONNECTED ? "--" : "->",
                    edge.getNodeRight().getName()
            ));
        }

        sb.append("\n");
        return sb.toString();
    }
}
