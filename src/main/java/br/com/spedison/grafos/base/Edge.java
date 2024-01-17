package br.com.spedison.grafos.base;



public class Edge<T> {
    private T content;

    private String name;

    private Orientation orientation;

    private Node<?> nodeLeft;
    private Node<?> nodeRight;

    public Edge(T content, String name, Orientation orientation, Node<?> nodeLeft, Node<?> nodeRight) {
        this.content = content;
        this.name = name;
        this.orientation = orientation;
        this.nodeLeft = nodeLeft;
        this.nodeRight = nodeRight;

        if (orientation == Orientation.FULL_CONNECTED){
            this.nodeLeft.addNextNode(this.nodeRight);
            this.nodeRight.addNextNode(this.nodeLeft);
        } else{
            this.nodeLeft.addNextNode(this.nodeRight);
        }
    }


    public T getContent() {
        return content;
    }

    public Node<?> getNodeLeft() {
        return nodeLeft;
    }

    public Node<?> getNodeRight() {
        return nodeRight;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public String getName() {
        return name;
    }
}