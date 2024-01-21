package br.com.spedison.graphland.base;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class PathOfFindNode implements Cloneable, Cloning<PathOfFindNode> {

    private List<Node> listOfNodes = new LinkedList<>();

    @Override
    public Object clone() throws CloneNotSupportedException {
        PathOfFindNode ret = new PathOfFindNode();
        ret.listOfNodes.addAll(this.listOfNodes);
        return ret;
    }

    public void queue(Node node) {
        listOfNodes.add(node);
    }

    public Node dequeue() {

        if (listOfNodes.isEmpty())
            return null;

        int last = listOfNodes.size() - 1;
        Node ret = listOfNodes.get(last); // get last
        listOfNodes.remove(last);
        return ret;
    }

    public void copy(PathOfFindNode path) {
        listOfNodes.clear();
        listOfNodes.addAll(path.listOfNodes);
    }

    public boolean exists(Node<?, ?> node) {
        return listOfNodes.indexOf(node) != -1;
    }

    public boolean exists(String nameNode) {
        return listOfNodes.indexOf(new Node(null, nameNode)) != -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PathOfFindNode that)) return false;

        if (that.listOfNodes.size() != this.listOfNodes.size())
            return false;

        final boolean[] ret = {true};
        IntStream
                .range(0, listOfNodes.size())
                .filter(p -> ret[0])
                .forEach(
                        i -> ret[0] &= that.listOfNodes.equals(listOfNodes));

        return ret[0];
    }

    @Override
    public int hashCode() {
        return listOfNodes != null ? listOfNodes.hashCode() : 0;
    }

    @Override
    public String toString() {

        if (listOfNodes.isEmpty())
            return "Path is Empty";

        StringBuilder sb = new StringBuilder();
        sb.append("[[ " + listOfNodes.get(0).getName());
        listOfNodes.stream().skip(1).forEach(node -> sb.append(" -> " + node.getName() /*+ "(" + node.getContent() + ")"*/));
        sb.append(" ]]");
        return sb.toString();
    }

    @Override
    public PathOfFindNode cloning() {
        try {
            return (PathOfFindNode) this.clone();
        } catch (CloneNotSupportedException cnse) {
            return null;
        }
    }

    public List<Node> getList() {
        return listOfNodes;
    }

    public void reverse() {
        Collections.reverse(listOfNodes);
    }
}
