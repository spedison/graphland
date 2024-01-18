package br.com.spedison.graphland.base;


import java.util.Objects;

public class Edge<T> {
    private T content;

    private String name;

    private Orientation orientation;

    private Node<T> nodeLeft;
    private Node<T> nodeRight;

    /**
     * Contructor used for find Edge based in Nodes.
     *
     * @param nodeLeft
     * @param nodeRight
     */
    public Edge(Node<T> nodeLeft, Node<T> nodeRight) {
        this.nodeLeft = nodeLeft;
        this.nodeRight = nodeRight;
    }

    /**
     * Contructor used for find Edge based in Nodes.
     *
     * @param name
     * @param nodeLeft
     * @param nodeRight
     */
    public Edge(String name, Node<T> nodeLeft, Node<T> nodeRight) {
        this.name = name;
        this.nodeLeft = nodeLeft;
        this.nodeRight = nodeRight;
    }

    public Edge(T content, String name, Orientation orientation, Node<T> nodeLeft, Node<T> nodeRight) {
        this.content = content;
        this.name = name;
        this.orientation = orientation;
        this.nodeLeft = nodeLeft;
        this.nodeRight = nodeRight;

        if (orientation == Orientation.FULL_CONNECTED) {
            this.nodeLeft.addNextNode(this.nodeRight);
            this.nodeRight.addNextNode(this.nodeLeft);
        } else {
            this.nodeLeft.addNextNode(this.nodeRight);
        }
    }


    public T getContent() {
        return content;
    }

    public Node<T> getNodeLeft() {
        return nodeLeft;
    }

    public Node<T> getNodeRight() {
        return nodeRight;
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
        if (o instanceof Edge<?> edge) {
            if (this.orientation == Orientation.FULL_CONNECTED) {
                return (this.nodeRight.equals(edge.nodeRight) &&
                        this.nodeLeft.equals(edge.nodeLeft)) ||
                        (this.nodeRight.equals(edge.nodeLeft) &&
                                this.nodeLeft.equals(edge.nodeRight));
            } else {
                return this.nodeRight.equals(edge.nodeRight) &&
                        this.nodeLeft.equals(edge.nodeLeft);
            }
        } else if (o instanceof String edgeName) {
            return this.getName().equals(edgeName);
        } else
            return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getNodeLeft(), getNodeRight(), orientation);
    }


}