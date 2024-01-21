package br.com.spedison.graphland.base;


import java.util.Objects;

public class Edge<TE extends Object, TN extends Object> {
    private TE content;

    private String name;

    private Orientation orientation;

    private Node<TN, TE> nodeStart;
    private Node<TN, TE> nodeEnd;

    /**
     * Contructor used for find Edge based in Nodes.
     *
     * @param nodeStart
     * @param nodeEnd
     */
    public Edge(Node<TN, TE> nodeStart, Node<TN, TE> nodeEnd) {
        this.nodeStart = nodeStart;
        this.nodeEnd = nodeEnd;
    }

    /**
     * Contructor used for find Edge based in Nodes.
     *
     * @param name
     * @param nodeStart
     * @param nodeEnd
     */
    public Edge(String name, Node<TN, TE> nodeStart, Node<TN, TE> nodeEnd) {
        this.name = name;
        this.nodeStart = nodeStart;
        this.nodeEnd = nodeEnd;
    }


    public Edge(TE content, String name, Orientation orientation, Node<TN, TE> nodeStart, Node<TN, TE> nodeEnd) {

        this.content = content;
        this.name = name;
        this.orientation = orientation;
        this.nodeStart = nodeStart;
        this.nodeEnd = nodeEnd;

        // Atach Edge in Nodes.
        if (orientation == Orientation.FULL_CONNECTED) {
            this.nodeStart.addEdge(this);
            Edge rev = new Edge(this.content, this.name, Orientation.SIMPLE_CONNECTED, nodeEnd, nodeStart);
            rev.orientation = Orientation.FULL_CONNECTED;
        } else {
            this.nodeStart.addEdge(this);
        }
    }

    public TE getContent() {
        return content;
    }

    public Node<TN, TE> getNodeStart() {
        return nodeStart;
    }

    public Node<TN, TE> getNodeEnd() {
        return nodeEnd;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Edge edge) {
            if (this.orientation == Orientation.FULL_CONNECTED) {
                return (this.nodeEnd.equals(edge.nodeEnd) &&
                        this.nodeStart.equals(edge.nodeStart)) ||
                        (this.nodeEnd.equals(edge.nodeStart) &&
                                this.nodeStart.equals(edge.nodeEnd));
            } else {
                return this.nodeEnd.equals(edge.nodeEnd) &&
                        this.nodeStart.equals(edge.nodeStart);
            }
        } else
            return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getNodeStart(), getNodeEnd(), orientation);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Edge{");
        sb.append("content=").append(content);
        sb.append(", name='").append(name).append('\'');
        sb.append(", orientation=").append(orientation);
        sb.append(", nodeLeft=").append(nodeStart);
        sb.append(", nodeRight=").append(nodeEnd);
        sb.append('}');
        return sb.toString();
    }
}